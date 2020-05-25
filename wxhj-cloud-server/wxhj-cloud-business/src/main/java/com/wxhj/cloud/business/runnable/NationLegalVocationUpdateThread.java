package com.wxhj.cloud.business.runnable;

import com.google.common.base.Strings;
import com.wxhj.cloud.business.domain.NationLegalVocationDO;
import com.wxhj.cloud.business.service.NationLegalVocationService;
import com.wxhj.cloud.core.enums.LegalVocationTypeEnum;
import com.wxhj.cloud.core.enums.WebResponseState;
import com.wxhj.cloud.core.exception.WuXiHuaJieCommonException;
import com.wxhj.cloud.core.model.nation.vocation.HolidayInfoModel;
import com.wxhj.cloud.core.model.nation.vocation.HolidayModel;
import com.wxhj.cloud.core.model.nation.vocation.OriginNationLegalVocationModel;
import com.wxhj.cloud.core.model.nation.vocation.VocationDataModel;
import com.wxhj.cloud.core.statics.RedisKeyStaticClass;
import com.wxhj.cloud.core.statics.SystemStaticClass;
import com.wxhj.cloud.core.utils.DateFormat;
import com.wxhj.cloud.core.utils.HttpClientUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;

import javax.annotation.Resource;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.TreeMap;
import java.util.concurrent.Callable;

/**
 * @author daxiong
 * @date 2020/5/15 1:20 下午
 */
@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Data
@Slf4j
public class NationLegalVocationUpdateThread implements Callable<String> {

    @Resource
    NationLegalVocationService nationLegalVocationService;
    @Resource
    RedisTemplate redisTemplate;

    private static final String BASE_API = "https://sp0.baidu.com/8aQDcjqpAAV3otqbppnN2DJv/api.php?query=%s%s&resource_id=6018&format=json";
    private static final String TIMEOUT_API = "http://www.easybots.cn/holiday_api.net";

    @Override
    public String call() {
        int destYear = LocalDate.now().getYear();
        // 获取今年和明年的节假日信息
        List<NationLegalVocationDO> list = new ArrayList<>(SystemStaticClass.INIT_CAPACITY);
        for (int j = 0; j < 2; j++) {
            int newYear = destYear + j;
            for (int i = 1; i <= 12; i++) {
                String month = Strings.padStart(String.valueOf(i), 2, '0');
                String requestApi = String.format(BASE_API, newYear, month);
                System.out.println(requestApi);
                OriginNationLegalVocationModel originNationLegalVocationModel;
                try {
                    originNationLegalVocationModel = HttpClientUtil.doGetTestOne(requestApi, OriginNationLegalVocationModel.class);
                } catch (AsyncRequestTimeoutException | IOException e) {
                    log.error("请求超时");
                    throw new WuXiHuaJieCommonException(WebResponseState.REQUEST_TIMEOUT);
                } catch (RuntimeException e) {
                    log.error("请求失败");
                    throw new WuXiHuaJieCommonException(WebResponseState.REQUEST_THIRD_FAIL);
                }

                List<HolidayModel> holidayModels = Optional.ofNullable(originNationLegalVocationModel).map(OriginNationLegalVocationModel::getData)
                        .filter(q -> q.size() > 0)
                        .map(q -> q.get(0))
                        .map(VocationDataModel::getHoliday).orElse(new ArrayList<>());
                holidayModels.forEach(q -> {
                    List<HolidayInfoModel> holidayInfoModels = q.getList();
                    if (holidayInfoModels != null) {
                        holidayInfoModels.forEach(item -> {
                            Integer year = item.getDate().getYear();
                            // 更新数据库
                            Integer status = item.getStatus();
                            String name = status == 2 ? q.getName() + "调休" : q.getName();
                            status = status == 2 ? LegalVocationTypeEnum.LEGAL_ON_WORK.getCode() : LegalVocationTypeEnum.LEGAL_OFF_WORK.getCode();
                            NationLegalVocationDO nationLegalVocation = NationLegalVocationDO.builder().day(item.getDate())
                                    .status(status).year(year).name(name).memo(q.getDesc()).build();
                            list.add(nationLegalVocation);
                        });
                    }
                });
            }
        }

        // 生成周六日数据
        list.addAll(nationLegalVocationService.generateWeekends(destYear, destYear + 1));
        TreeMap<String, NationLegalVocationDO> map = new TreeMap<>();
        // 去重+排序
        list.forEach(q -> {
            String key = DateFormat.getStringDate(q.getDay());
            NationLegalVocationDO nationLegalVocationDO = map.get(key);
            if (nationLegalVocationDO != null && nationLegalVocationDO.getStatus() >= q.getStatus()) {
                return;
            }
            map.put(key, q);
        });
        Collection<NationLegalVocationDO> values = map.values();
        values.forEach(q -> {
            nationLegalVocationService.insert(q);
            // 写入redis缓存
            String redisKey = RedisKeyStaticClass.NATION_LEGAL_VOCATION + q.getYear();
            redisTemplate.opsForHash().put(redisKey, DateFormat.getStringDate(q.getDay()), q);
        });
        return null;
    }
}

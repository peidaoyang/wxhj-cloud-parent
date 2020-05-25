package com.wxhj.cloud.business.service.impl;

import com.wxhj.cloud.business.domain.NationLegalVocationDO;
import com.wxhj.cloud.business.mapper.NationLegalVocationMapper;
import com.wxhj.cloud.business.service.NationLegalVocationService;
import com.wxhj.cloud.core.enums.LegalVocationTypeEnum;
import com.wxhj.cloud.core.statics.RedisKeyStaticClass;
import com.wxhj.cloud.core.statics.SystemStaticClass;
import com.wxhj.cloud.core.utils.DateFormat;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author daxiong
 * @date 2020/5/8 5:46 下午
 */
@Service
public class NationLegalVocationServiceImpl implements NationLegalVocationService {

    @Resource
    NationLegalVocationMapper nationLegalVocationMapper;
    @Resource
    RedisTemplate redisTemplate;

    @Override
    public List<NationLegalVocationDO> generateWeekends(Integer beginYear, Integer endYear) {
        List<NationLegalVocationDO> result = new ArrayList<>(SystemStaticClass.INIT_CAPACITY);
        LocalDate yearFirst = DateFormat.getYearFirst(beginYear);
        LocalDate yearLast = DateFormat.getYearLast(endYear);
        int termDays = (int) yearFirst.until(yearLast, ChronoUnit.DAYS);
        for (int i = 0; i <= termDays; i++) {
            LocalDate date = DateFormat.growDateIgnoreHMS(yearFirst, i);
            int dateNumber = date.getDayOfWeek().getValue();
            if (dateNumber == 6 || dateNumber == 7) {
                NationLegalVocationDO nationLegalVocation = NationLegalVocationDO.builder().day(date).year(date.getYear()).createTime(new Date())
                        .name("周末").memo("正常休息").status(LegalVocationTypeEnum.NORMAL_OFF_WORK.getCode()).build();
                result.add(nationLegalVocation);
            }
        }
        return result;
    }

    @Override
    public List<NationLegalVocationDO> listNationLegalVocation(LocalDate beginTime, LocalDate endTime) {
        Integer beginYear = beginTime.getYear();
        Integer endYear = endTime.getYear();

        // 先从缓存中取
        String redisKey = RedisKeyStaticClass.NATION_LEGAL_VOCATION + beginYear;
        List<NationLegalVocationDO> result = redisTemplate.opsForHash().values(redisKey);
        if (endYear > beginYear) {
            redisKey = RedisKeyStaticClass.NATION_LEGAL_VOCATION + endYear;
            result.addAll(redisTemplate.opsForHash().values(redisKey));
        }
        if (result.size() > 0) {
            // 筛选
            result = result.stream()
                    .filter(q -> DateFormat.range(q.getDay(), DateFormat.growDateIgnoreHMS(beginTime, -1), DateFormat.growDateIgnoreHMS(endTime, 1)))
                    .sorted(Comparator.comparing(NationLegalVocationDO::getDay))
                    .collect(Collectors.toList());
            return result;
        }
        // 缓存中没有，从数据库中读取
        Example example = new Example(NationLegalVocationDO.class);
        example.createCriteria().andBetween("day", beginTime, endTime);
        return nationLegalVocationMapper.selectByExample(example);
    }

    @Override
    public void insert(NationLegalVocationDO nationLegalVocation) {
        Example example = new Example(NationLegalVocationDO.class);
        example.createCriteria().andEqualTo("day", nationLegalVocation.getDay());
        nationLegalVocationMapper.deleteByExample(example);
        nationLegalVocation.setCreateTime(new Date());
        nationLegalVocationMapper.insert(nationLegalVocation);
    }
}

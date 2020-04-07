package com.wxhj.cloud.business.service.impl.visitor;

import com.github.pagehelper.PageInfo;
import com.wxhj.cloud.business.domain.VisitorInfoDO;
import com.wxhj.cloud.business.mapper.view.VisitorInfoMapper;
import com.wxhj.cloud.business.service.visitor.VisitorInfoService;
import com.wxhj.cloud.core.model.pagination.IPageRequestModel;
import com.wxhj.cloud.core.model.pagination.IPageResponseModel;
import com.wxhj.cloud.core.model.pagination.PageDefResponseModel;
import com.wxhj.cloud.core.utils.DateUtil;
import com.wxhj.cloud.driud.pagination.PageUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @ClassName: visitorInfoServiceImpl.java
 * @author: cya
 * @Date: 2020年2月11日 下午3:30:24
 */
@Service
public class VisitorInfoServiceImpl implements VisitorInfoService {
    @Resource
    VisitorInfoMapper visitorInfoMapper;

    @Override
    public PageInfo<VisitorInfoDO> listPage(IPageRequestModel pageRequestModel, String organizeId,
                                            String nameValue, Integer isCheck, String field) {
        Example example = new Example(VisitorInfoDO.class);
        example.createCriteria().andEqualTo("organizeId", organizeId).andEqualTo("isCheck", isCheck).andLike(field,
                "%" + nameValue + "%");
        PageInfo<VisitorInfoDO> pageList = PageUtil.selectPageList(pageRequestModel,
                () -> visitorInfoMapper.selectByExample(example));
        return pageList;
    }

//	@Override
//	public PageInfo<VisitorInfoDO> selectByNameAndIscheck(IPageRequestModel pageRequestModel, String organizeId,
//			String nameValue, Integer isCheck) {
//		Example example = new Example(VisitorInfoDO.class);
//		example.createCriteria().andEqualTo("organizeId", organizeId).andEqualTo("isCheck", isCheck).andLike("name",
//				"%" + nameValue + "%");
//		PageInfo<VisitorInfoDO> pageList = PageUtil.selectPageList(pageRequestModel,
//				() -> visitorInfoMapper.selectByExample(example));
//		return pageList;
//	}
//
//	@Override
//	public PageInfo<VisitorInfoDO> selectByAccoutIdAndIscheck(IPageRequestModel pageRequestModel, String organizeId,
//			String nameValue, Integer isCheck) {
//		Example example = new Example(VisitorInfoDO.class);
//		example.createCriteria().andEqualTo("organizeId", organizeId).andEqualTo("isCheck", isCheck)
//				.andLike("accountId", "%" + nameValue + "%");
//		PageInfo<VisitorInfoDO> pageList = PageUtil.selectPageList(pageRequestModel,
//				() -> visitorInfoMapper.selectByExample(example));
//		return pageList;
//	}

    @Override
    public IPageResponseModel select(IPageRequestModel pageRequestModel, String accountId, Date startTime,
                                     Date endTime) {
        //pjf
        endTime = DateUtil.calcDate(endTime, Calendar.DAY_OF_YEAR, 1);
        //
        Example example = new Example(VisitorInfoDO.class);
        example.createCriteria().andEqualTo("accountId", accountId).andBetween("creatorTime", startTime, endTime);
        PageInfo<VisitorInfoDO> pageAttendanceData = PageUtil.selectPageList(pageRequestModel,
                () -> visitorInfoMapper.selectByExample(example));
        PageDefResponseModel pageDefResponseModel = new PageDefResponseModel();
        pageDefResponseModel = (PageDefResponseModel) PageUtil.initPageResponseModel(pageAttendanceData,
                pageDefResponseModel, VisitorInfoDO.class);
        return pageDefResponseModel;
    }

    @Override
    public String insert(VisitorInfoDO visitorInfo) {
        visitorInfo.setId(UUID.randomUUID().toString());
        visitorInfo.setIsCheck(0);
        visitorInfo.setCreatorTime(new Date());
        visitorInfoMapper.insert(visitorInfo);
        return visitorInfo.getId();
    }

    @Override
    public void update(VisitorInfoDO visitorInfo) {
        visitorInfoMapper.updateByPrimaryKeySelective(visitorInfo);
    }

    @Override
    public void check(String id, Integer isCheck) {
        VisitorInfoDO visitorInfoDO = new VisitorInfoDO();
        visitorInfoDO.setId(id);
        visitorInfoDO.setIsCheck(isCheck);
        visitorInfoMapper.updateByPrimaryKeySelective(visitorInfoDO);
    }

    @Override
    public List<VisitorInfoDO> selectByIdNumberAndSceneId(String isNumber, String sceneId, Date dateTime) {
        Example example = new Example(VisitorInfoDO.class);
        example.createCriteria().andLessThanOrEqualTo("beginTime", dateTime)
                .andGreaterThanOrEqualTo("endTime", dateTime)
                .andEqualTo("idNumber", isNumber).andEqualTo("sceneId", sceneId);
        return visitorInfoMapper.selectByExample(example);
    }

    @Override
    @Transactional
    public void delete(List<String> idList) {
        idList.forEach(q -> visitorInfoMapper.deleteByPrimaryKey(q));
    }

}

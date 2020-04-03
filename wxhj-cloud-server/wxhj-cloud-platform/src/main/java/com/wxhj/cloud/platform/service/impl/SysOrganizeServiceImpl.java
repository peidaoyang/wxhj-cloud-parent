/**
 * @fileName: SysOrganizeServiceImpl.java
 * @author: pjf
 * @date: 2019年10月14日 下午4:39:49
 */

package com.wxhj.cloud.platform.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Strings;
import com.wxhj.cloud.core.config.LocalIdConfig;
import com.wxhj.cloud.core.utils.CommUtil;
import com.wxhj.cloud.platform.bo.SysOrgOptimizeBO;
import com.wxhj.cloud.platform.domain.SysOrganizeDO;
import com.wxhj.cloud.platform.mapper.SysOrganizeMapper;
import com.wxhj.cloud.platform.service.SysOrganizeService;

import tk.mybatis.mapper.entity.Example;

/**
 * @author pjf
 * @className SysOrganizeServiceImpl.java
 * @date 2019年10月14日 下午4:39:49
 */

@Service
public class SysOrganizeServiceImpl implements SysOrganizeService {
    @Resource
    private SysOrganizeMapper sysOrganizeMapper;
    @Resource
    LocalIdConfig localIdConfig;
    @Resource
    DozerBeanMapper dozerBeanMapper;

    static final String ORGANIZE_SEQUENCE = "organize_sequence_generator";
    static final String ORGANIZE_SEQUENCE_NO = "organize_sequence_no";

    //	@Override
//	public List<SysOrganizeDO> selectByParentIdRecursion(String parentid) {
//		List<SysOrganizeDO> allOrganizeEntityList = select();
//		List<SysOrganizeDO> organizeEntityList = new ArrayList<>();
//
//		List<SysOrganizeDO> organizeTempList = allOrganizeEntityList.stream()
//				.filter(q -> parentid.equals(q.getParentId())).collect(Collectors.toList());
//		for (SysOrganizeDO organizeDO : organizeTempList) {
//			organizeEntityList.addAll(selectByParentIdRecursion(organizeDO.getId()));
//			organizeEntityList.add(organizeDO);
//		}
//		return organizeEntityList;
//	}
    @Override
    // 向下查询不包含自己
    public List<SysOrganizeDO> selectByParentIdRecursion(String parentId) {
        SysOrganizeDO selectById = selectById(parentId);
        Example example = new Example(SysOrganizeDO.class);
        example.createCriteria().andLike("noList", "%|" + selectById.getNo().toString() + "|%").andEqualTo("isDeleteMark",
                0);
        return sysOrganizeMapper.selectByExample(example);

//		SysOrganizeDO selectById = selectById(parentid);
//		List<String> split = Arrays.asList(selectById.getNoList().split("|")).stream()
//				.filter(q -> !Strings.isNullOrEmpty(q)).collect(Collectors.toList());
//		Example example = new Example(SysOrganizeDO.class);
//		example.createCriteria().andIn("no", split).andEqualTo("isDeleteMark", 0);
//		return sysOrganizeMapper.selectByExample(example);
    }

    @Override
    // 向上查询包含自己
    public List<SysOrganizeDO> selectByIdRecursion(String id, String maxId) {
        SysOrganizeDO nowSysOrganizeId = selectById(id);
        SysOrganizeDO maxSysOrganizeId = selectById(maxId);

        if (nowSysOrganizeId.getNo() == 1) {
            //如果当前组织id是运营商，直接返回本身
            return Arrays.asList(nowSysOrganizeId);
        }
        List<String> split = Arrays.asList(nowSysOrganizeId.getNoList().split("|")).stream()
                .filter(q -> !Strings.isNullOrEmpty(q)).collect(Collectors.toList());
        Example example = new Example(SysOrganizeDO.class);
        example.createCriteria().andIn("no", split).andGreaterThanOrEqualTo("layers", maxSysOrganizeId.getLayers())
                .andEqualTo("isDeleteMark", 0);
        List<SysOrganizeDO> selectByExample = sysOrganizeMapper.selectByExample(example);
        selectByExample.add(nowSysOrganizeId);
        return selectByExample;

//		List<SysOrganizeDO> allOrgList = select();
//		List<SysOrganizeDO> organizeEntityList = new ArrayList<>();
//
//		List<SysOrganizeDO> parentOrg = allOrgList.stream().filter(q -> q.getId().equals(id))
//				.collect(Collectors.toList());
//		for (SysOrganizeDO organizeDO : parentOrg) {
//			organizeEntityList.add(organizeDO);
//			if (organizeDO.getId().equals(maxId)) {
//				break;
//			}
//			organizeEntityList.addAll(selectByIdRecursion(organizeDO.getParentId(), maxId));
//		}
//		return organizeEntityList;
    }

    @Override
    public void update(SysOrganizeDO sysOrganizeDO, String userid) {
        sysOrganizeDO.modify(userid);
        sysOrganizeMapper.updateByPrimaryKeySelective(sysOrganizeDO);

    }

//	@Transactional
//	@Override
//	public String insert(SysOrganizeDO sysOrganizeDO, String userid) {
//		sysOrganizeDO.initialization();
//		sysOrganizeDO.create(userid);
//		if (sysOrganizeDO.getLayers() <= 1) {
//			sysOrganizeDO.setEncode(CommUtil.padLeftStr(selectOrganizeSequence().toString(), 6));
//		}
//
//		sysOrganizeMapper.insert(sysOrganizeDO);
//		return sysOrganizeDO.getId();
//	}


    @Transactional
    @Override
    public String insertCascade(SysOrgOptimizeBO sysOrgOpt, String userid) {
        SysOrganizeDO sysOrganize = dozerBeanMapper.map(sysOrgOpt, SysOrganizeDO.class);
        sysOrganize.initialization();
        sysOrganize.create(userid);
        if (sysOrganize.getLayers() <= 1) {
            sysOrganize.setEncode(CommUtil.padLeftStr(selectOrganizeSequence().toString(), 6));
        }
        //
        sysOrganize.setNo(selectOrganizeNoSequence());
        if (!Strings.isNullOrEmpty(sysOrganize.getParentId())) {
            SysOrganizeDO selectById = selectById(sysOrganize.getParentId());
            String noList = selectById.getNoList();
            if (!Strings.isNullOrEmpty(noList)) {
                sysOrganize.setNoList(noList + selectById.getNo().toString() + "|");
            } else {
                sysOrganize.setNoList("|" + selectById.getNo().toString() + "|");
            }
        }
        //
        sysOrganizeMapper.insert(sysOrganize);
        return sysOrganize.getId();
    }

    // @Override
//	private List<SysOrganizeDO> select() {
//		Example example = new Example(SysOrganizeDO.class);
//		example.createCriteria().andEqualTo("isDeleteMark", 0);
//		return sysOrganizeMapper.selectByExample(example);
//	}

    @Override
    public List<SysOrganizeDO> listByLayers(String parentid, Integer layers) {
        Example example = new Example(SysOrganizeDO.class);
        example.createCriteria().andEqualTo("parentId", parentid).andEqualTo("isDeleteMark", 0)
                .andLessThanOrEqualTo("layers", layers);
        return sysOrganizeMapper.selectByExample(example);
    }

    @Override
    public int selectCountByParentId(String parentId) {

        Example example = new Example(SysOrganizeDO.class);
        example.createCriteria().andEqualTo("parentId", parentId).andEqualTo("isDeleteMark", 0);
        return sysOrganizeMapper.selectCountByExample(example);
    }

    @Override
    public SysOrganizeDO selectById(String id) {
//		List<SysOrganizeDO> allOrganizeEntityList = select();
//		allOrganizeEntityList = allOrganizeEntityList.stream().filter(q -> q.getId().equals(id))
//				.collect(Collectors.toList());
//		return allOrganizeEntityList.size() > 0 ? allOrganizeEntityList.get(0) : null;

        return sysOrganizeMapper.selectByPrimaryKey(id);
    }

    @Transactional
    @Override
    public void shamDeleteCascade(String id, String userId) {
        SysOrganizeDO sysOrganizeDO = new SysOrganizeDO();
        sysOrganizeDO.setId(id);
        sysOrganizeDO.remove(userId);
        sysOrganizeMapper.updateByPrimaryKeySelective(sysOrganizeDO);
    }

//	@Override
//	public List<SysOrganizeDO> selectByFullNameAndOrgIdList(String fullName, List<String> organizeIdList,
//			SysOrganizeDO mainSysOrganize) {
//
//		Example example = new Example(SysOrganizeDO.class);
//		example.createCriteria().andIn("id", organizeIdList).andEqualTo("isDeleteMark", 0);
//		List<SysOrganizeDO> selectByIdList = sysOrganizeMapper.selectByExample(example);
//
//		return ViewControlUtil.treeElementFilterRecursion(selectByIdList, new Predicate<SysOrganizeDO>() {
//			@Override
//			public boolean test(SysOrganizeDO t) {
//				return t.getFullName().indexOf(fullName) > -1;
//			}
//		}, mainSysOrganize.getParentId());
//	}

    // 商户号的流水号
    private Long selectOrganizeSequence() {
        String terminal = localIdConfig.getLocalId();
        sysOrganizeMapper.replaceSequence(ORGANIZE_SEQUENCE, terminal);
        return sysOrganizeMapper.selectSequence(ORGANIZE_SEQUENCE, terminal);
    }

    // 商户编码
    private Long selectOrganizeNoSequence() {
        String terminal = localIdConfig.getLocalId();
        sysOrganizeMapper.replaceSequence(ORGANIZE_SEQUENCE_NO, terminal);
        return sysOrganizeMapper.selectSequence(ORGANIZE_SEQUENCE_NO, terminal);
    }

//	@Override
//	public IPageResponseModel selectByFullNameAndParentIdPage(IPageRequestModel pageRequestModel, String fullName,
//			String parentId) {
//		Example example = new Example(SysOrganizeDO.class);
//		example.createCriteria().andEqualTo("parentId", parentId).andLike("fullName", "%" + fullName + "%")
//				.andEqualTo("isDeleteMark", 0);
//		PageInfo<SysOrganizeDO> pageList = PageUtil.selectPageList(pageRequestModel, () -> {
//			return sysOrganizeMapper.selectByExample(example);
//		});
//		PageDefResponseModel pageDefResponseModel = new PageDefResponseModel();
//		pageDefResponseModel = (PageDefResponseModel) PageUtil.initPageResponseModel(pageList, pageDefResponseModel,
//				SysOrganizeDO.class);
//		return pageDefResponseModel;
//	}

//
//	@Override
//	public List<String> listByOrgId(String organizeId) {
//		Example example = new Example(SysOrganizeDO.class);
//		example.createCriteria().andEqualTo("parentId", organizeId).andEqualTo("isDeleteMark", 0);
//		List<String> listOrganizeId = new ArrayList<>();
//		sysOrganizeMapper.selectByExample(example).forEach(q -> {
//			listOrganizeId.add(q.getId());
//		});
//		listOrganizeId.add(organizeId);
//		return listOrganizeId;
//	}

    @Override
    public List<SysOrganizeDO> listByOrgIdList(List<String> organizeIdList) {
        Example example = new Example(SysOrganizeDO.class);
        example.createCriteria().andIn("id", organizeIdList);
        return sysOrganizeMapper.selectByExample(example);
    }

//	@Override
//	public List<SysOrganizeDO> listByIdMinToMax(String minId, String maxId) {
//		List<SysOrganizeDO> sysOrganizeList = select();
//		List<SysOrganizeDO> retSysOrganizeList = listByIdMinToMaxRecursion(sysOrganizeList, minId, maxId);
//		return retSysOrganizeList;
//	}
//
//	private List<SysOrganizeDO> listByIdMinToMaxRecursion(List<SysOrganizeDO> sysOrganizeList, String minId,
//			String maxId) {
//		List<SysOrganizeDO> retSysOrganizeList = new ArrayList<>();
//		Optional<SysOrganizeDO> findFirst = sysOrganizeList.stream().filter(q -> q.getId().equals(minId)).findFirst();
//		if (!findFirst.isPresent()) {
//			return null;
//		}
//
//		if (findFirst.get().getId().equals(maxId)) {
//			retSysOrganizeList.add(findFirst.get());
//			return retSysOrganizeList;
//		}
//		retSysOrganizeList = listByIdMinToMaxRecursion(sysOrganizeList, findFirst.get().getParentId(), maxId);
//		if (retSysOrganizeList == null) {
//			return null;
//		} else {
//			retSysOrganizeList.add(findFirst.get());
//		}
//		return retSysOrganizeList;
//	}

}

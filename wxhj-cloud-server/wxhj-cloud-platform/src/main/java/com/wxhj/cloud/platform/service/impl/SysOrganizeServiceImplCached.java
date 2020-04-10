package com.wxhj.cloud.platform.service.impl;

import com.github.pagehelper.PageInfo;
import com.wxhj.cloud.core.config.LocalIdConfig;
import com.wxhj.cloud.core.model.pagination.IPageRequestModel;
import com.wxhj.cloud.core.model.pagination.IPageResponseModel;
import com.wxhj.cloud.core.model.pagination.PageDefResponseModel;
import com.wxhj.cloud.core.utils.CommUtil;
import com.wxhj.cloud.driud.pagination.PageUtil;
import com.wxhj.cloud.platform.domain.SysOrganizeDO;
import com.wxhj.cloud.platform.mapper.SysOrganizeMapper;
import com.wxhj.cloud.platform.service.SysOrganizeServiceCached;
import com.wxhj.cloud.platform.util.ViewControlUtil;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 *  使用缓存访问
 */
//@Service
public class SysOrganizeServiceImplCached implements SysOrganizeServiceCached {

    @Resource
    private SysOrganizeMapper sysOrganizeMapper;

    @Resource
    LocalIdConfig localIdConfig;

    static final String ORGANIZE_SEQUENCE = "organize_sequence_generator";

    @Override
    @Cacheable(value="organizations",key="sys-organizations-tree", unless = "#result eq null ")
    public List<SysOrganizeDO> selectByParentIdRecursion(String parentid) {

        List<SysOrganizeDO> allOrganizeEntityList = select();
        List<SysOrganizeDO> organizeEntityList = new ArrayList<>();

        List<SysOrganizeDO> organizeTempList = allOrganizeEntityList.stream()
                .filter(q -> parentid.equals(q.getParentId())).collect(Collectors.toList());
        for (SysOrganizeDO organizeDO : organizeTempList) {
            organizeEntityList.addAll(selectByParentIdRecursion(organizeDO.getId()));
            organizeEntityList.add(organizeDO);
        }
        return organizeEntityList;
    }

    @Override
    @CachePut(value = "organizations", key = "sys-organizations-#userid", unless = "#userid eq null ")
    public void update(SysOrganizeDO sysOrganizeDO, String userid) {
        sysOrganizeDO.modify(userid);
        sysOrganizeMapper.updateByPrimaryKeySelective(sysOrganizeDO);

    }

    @Transactional
    @Override
    @CachePut(value = "organizations", key = "sys-organizations-#userid", unless = "#userid eq null ")
    public String insert(SysOrganizeDO sysOrganizeDO, String userid) {
        sysOrganizeDO.initialization();
        sysOrganizeDO.create(userid);
        if (sysOrganizeDO.getLayers() <= 1) {
            sysOrganizeDO.setEncode(CommUtil.padLeftStr(selectOrganizeSequence().toString(), 6));
        }
        sysOrganizeMapper.insert(sysOrganizeDO);
        return sysOrganizeDO.getId();
    }

    @Override
    @Cacheable(value="organizations",key="sys-organizations", unless = "#result eq null ")
    public List<SysOrganizeDO> select() {
        Example example = new Example(SysOrganizeDO.class);
        example.createCriteria().andEqualTo("isDeleteMark", 0);
        return sysOrganizeMapper.selectByExample(example);
    }

    @Override
    @Cacheable(value="organizations",key="sys-organizations-children", unless = "#result eq null ")
    public List<SysOrganizeDO> selectByParentId(String parentid) {

        List<SysOrganizeDO> allOrganizeEntityList = select();
        List<SysOrganizeDO> organizeTempList = allOrganizeEntityList.stream()
                .filter(q -> parentid.equals(q.getParentId())).collect(Collectors.toList());
        return organizeTempList;
    }

    @Override
    public int selectCountByParentId(String parentid) {

        Example example = new Example(SysOrganizeDO.class);
        example.createCriteria().andEqualTo("parentId", parentid).andEqualTo("isDeleteMark", 0);
        return sysOrganizeMapper.selectCountByExample(example);
    }

    @Override
    @Cacheable(value="organizations",key="sys-organizations", unless = "#result eq null ")
    public SysOrganizeDO selectById(String id) {
        List<SysOrganizeDO> allOrganizeEntityList = select();
        allOrganizeEntityList = allOrganizeEntityList.stream().filter(q -> q.getId().equals(id))
                .collect(Collectors.toList());
        return allOrganizeEntityList.size() > 0 ? allOrganizeEntityList.get(0) : null;
    }

    @Transactional
    @Override
    public void shamDeleteByKey(String id, String userid) {
        SysOrganizeDO sysOrganizeDO = new SysOrganizeDO();
        sysOrganizeDO.setId(id);
        sysOrganizeDO.remove(userid);
        sysOrganizeMapper.updateByPrimaryKeySelective(sysOrganizeDO);
    }

    @Override
    public List<SysOrganizeDO> selectByFullNameAndOrgIdList(String fullName, List<String> organizeIdList,
                                                            SysOrganizeDO mainSysOrganize) {

        Example example = new Example(SysOrganizeDO.class);
        example.createCriteria().andIn("id", organizeIdList).andEqualTo("isDeleteMark", 0);
        List<SysOrganizeDO> selectByIdList = sysOrganizeMapper.selectByExample(example);

        return ViewControlUtil.treeElementFilterRecursion(selectByIdList, new Predicate<SysOrganizeDO>() {
            @Override
            public boolean test(SysOrganizeDO t) {
                return t.getFullName().indexOf(fullName) > -1;
            }
        }, mainSysOrganize.getParentId());
    }

    private Long selectOrganizeSequence() {
        String terminal = localIdConfig.getLocalId();
        sysOrganizeMapper.replaceSequence(ORGANIZE_SEQUENCE, terminal);
        return sysOrganizeMapper.selectSequence(ORGANIZE_SEQUENCE, terminal);
    }

    @Override
    public IPageResponseModel selectByFullNameAndParentIdPage(IPageRequestModel pageRequestModel, String fullName,
                                                              String parentId) {

        Example example = new Example(SysOrganizeDO.class);
        example.createCriteria().andEqualTo("parentId", parentId).andLike("fullName", "%" + fullName + "%")
                .andEqualTo("isDeleteMark", 0);
        PageInfo<SysOrganizeDO> pageList = PageUtil.selectPageList(pageRequestModel, () ->
        {return sysOrganizeMapper.selectByExample(example);});
        PageDefResponseModel pageDefResponseModel = new PageDefResponseModel();
        pageDefResponseModel = (PageDefResponseModel) PageUtil.initPageResponseModel(
                pageList, pageDefResponseModel,SysOrganizeDO.class);
        return pageDefResponseModel;
    }

    @Override
    public List<String> listByOrgId(String organizeId) {
        Example example = new Example(SysOrganizeDO.class);
        example.createCriteria().andEqualTo("parentId", organizeId).andEqualTo("isDeleteMark", 0);
        List<String> listOrganizeId = new ArrayList<>();
        sysOrganizeMapper.selectByExample(example).forEach(q -> {
            listOrganizeId.add(q.getId());
        });
        listOrganizeId.add(organizeId);
        return listOrganizeId;
    }

    @Override
    public List<SysOrganizeDO> listByOrgIdList(List<String> organizeIdList) {
        Example example = new Example(SysOrganizeDO.class);
        example.createCriteria().andIn("id", organizeIdList);
        return sysOrganizeMapper.selectByExample(example);
    }
}

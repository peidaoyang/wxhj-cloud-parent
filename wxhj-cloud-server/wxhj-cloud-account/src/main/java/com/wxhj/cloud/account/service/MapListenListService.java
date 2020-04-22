/**
 * @fileName: MapListenListService.java
 * @author: pjf
 * @date: 2019年10月31日 下午1:45:34
 */

package com.wxhj.cloud.account.service;

/**
 * @className MapListenListService.java
 * @author pjf
 * @date 2019年10月31日 下午1:45:34   
 */

import com.github.pagehelper.PageInfo;
import com.wxhj.cloud.account.domain.MapListenListDO;

import java.util.List;

public interface MapListenListService {

    void insertListCascade(List<MapListenListDO> mapListenListList);

    PageInfo<MapListenListDO> selectByNoSync(int selectCount);

    int updateByIdSetSync(List<Long> idList);


    // int insertForMapAccountAuthority(int operateType, String sceneId, String
    // authorityId);

    // int insertForMapAuthorityScene(int operateType, String accountId, String
    // authorityId);
}

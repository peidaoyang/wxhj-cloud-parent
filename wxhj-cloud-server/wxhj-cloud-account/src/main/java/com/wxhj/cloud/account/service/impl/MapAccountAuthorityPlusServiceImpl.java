package com.wxhj.cloud.account.service.impl;

import com.wxhj.cloud.account.domain.MapAccountAuthorityDO;
import com.wxhj.cloud.account.service.MapAccountAuthorityPlusService;
import com.wxhj.cloud.account.service.MapAccountAuthorityService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MapAccountAuthorityPlusServiceImpl implements MapAccountAuthorityPlusService {
    @Resource
    MapAccountAuthorityService mapAccountAuthorityService;

    @Override
    public void update(String authorityId, List<String> accountIdList) {
        List<MapAccountAuthorityDO> newlistMap = accountIdList.stream()
                .map(q -> new MapAccountAuthorityDO(null, authorityId, q)).collect(Collectors.toList());
        MapAccountAuthorityDO mapAccountAuthority = new MapAccountAuthorityDO();
        mapAccountAuthority.setAuthorityGroupId(authorityId);
        List<MapAccountAuthorityDO> oldlistMap = mapAccountAuthorityService.list(mapAccountAuthority);
        List<MapAccountAuthorityDO> addlistMap = newlistMap.stream().filter(q -> !oldlistMap.contains(q))
                .collect(Collectors.toList());
        List<MapAccountAuthorityDO> dellistMap = oldlistMap.stream().filter(q -> !newlistMap.contains(q))
                .collect(Collectors.toList());
        addlistMap.forEach(q -> {
            mapAccountAuthorityService.insertCascade(q);
        });
        dellistMap.forEach(q -> {
            mapAccountAuthorityService.deleteCascade(q.getAuthorityGroupId(), q.getAccountId());
        });

    }

    @Override
    public List<MapAccountAuthorityDO> deleteByAccountId(String accountId) {
        List<MapAccountAuthorityDO> listByAccountId = mapAccountAuthorityService
                .listByAccountId(accountId);
        for (MapAccountAuthorityDO mapAccountAuthorityTemp : listByAccountId) {
            mapAccountAuthorityService.deleteCascade(mapAccountAuthorityTemp.getAuthorityGroupId(),
                    mapAccountAuthorityTemp.getAccountId());
        }
        return listByAccountId;
    }
}

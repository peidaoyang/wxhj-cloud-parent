package com.wxhj.cloud.business.entrance;

import com.wxhj.cloud.business.bo.EntranceGroupBO;
import com.wxhj.cloud.business.domain.EntranceGroupDO;

public interface EntranceRuleService {
	EntranceGroupBO createMapEntranceAuthorize(EntranceGroupDO entranceGroupTemp);
}

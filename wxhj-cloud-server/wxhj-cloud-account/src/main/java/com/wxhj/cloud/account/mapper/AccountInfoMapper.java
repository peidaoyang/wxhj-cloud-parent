/** 
 * @fileName: AccountInfoMapper.java  
 * @author: pjf
 * @date: 2019年10月28日 下午3:12:14 
 */

package com.wxhj.cloud.account.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

import com.wxhj.cloud.account.domain.AccountInfoDO;
import com.wxhj.cloud.driud.common.BaseMapper;

/**
 * @className AccountInfoMapper.java
 * @author pjf
 * @date 2019年10月28日 下午3:12:14
 */
@Mapper
public interface AccountInfoMapper extends BaseMapper<AccountInfoDO> {
	@Update("Update account_info set recharge_total_amount=recharge_total_amount+#{amount},"
			+ "account_balance=account_balance+#{amount} where account_id=#{id}")
	void recharge(Integer amount,String id);
	@Update("Update account_info set consume_total_amount=consume_total_amount+#{amount},"
			+ "consume_total_frequency=consume_total_frequency+1,"
			+ "account_balance = account_balance -#{amount} where account_id=#{id}")
	void charging(Integer amount,String id);
}

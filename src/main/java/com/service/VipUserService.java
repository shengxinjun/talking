package com.service;

import com.domain.VipUser;
import com.util.Paging;

public interface VipUserService {

	Paging<VipUser> vipUserList(Paging<VipUser> paging,Integer type);
	
	int addVipUser(VipUser vipUser);
	
	void updateVipUser(VipUser vipUser);
	
	VipUser findVipById(Integer id);
	
	/**
	 * 会员充值
	 * @param id
	 * @param money
	 */
	void invest(Integer id,String money);
}

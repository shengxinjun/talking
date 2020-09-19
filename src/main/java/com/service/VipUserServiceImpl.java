package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.ConsumptionRecordDao;
import com.dao.VipUserDao;
import com.domain.ConsumptionRecord;
import com.domain.VipUser;
import com.util.Paging;
@Service
public class VipUserServiceImpl implements VipUserService{

	@Autowired
	private VipUserDao vipUserDao;
	
	@Autowired
	private ConsumptionRecordDao consumptionRecordDao;
	@Override
	public Paging<VipUser> vipUserList(Paging<VipUser> paging,Integer type) {
		Paging<VipUser> page =  vipUserDao.vipList(paging,type);
		for(VipUser vipUser:page.getList()){
			if(vipUser.getLevel()==1)
				vipUser.setLevelName("普通会员");
			else if(vipUser.getLevel()==2){
				vipUser.setLevelName("黄金会员");
			}else if(vipUser.getLevel()==3){
				vipUser.setLevelName("钻石会员");
			}else if(vipUser.getLevel()==4){
				vipUser.setLevelName("星耀会员");
			}
		}
		return page;
	}
	@Override
	public int  addVipUser(VipUser vipUser) {
		moneyToLevel(vipUser);
		return vipUserDao.insertAndReturnId(vipUser);
	}
	@Override
	public VipUser findVipById(Integer id) {
		// TODO Auto-generated method stub
		VipUser vip =  vipUserDao.findById(id);
		List<ConsumptionRecord> list = consumptionRecordDao.findVipConsumptionRecords(id);
		vip.setRecords(list);
		return vip;
	}
	@Override
	public void updateVipUser(VipUser vipUser) {
		moneyToLevel(vipUser);
		vipUserDao.update(vipUser);
	}
	
	/**
	 * 新增、更新会员时余额生成等级
	 * 大于10000元：星耀会员
	 * 大于5000元：钻石会员
	 * 大于1000元：黄金会员
	 * 小于1000元：普通会员
	 * @param vip
	 * @return
	 */
	VipUser moneyToLevel(VipUser vip){
		if(vip.getMoney()>=10000)
			vip.setLevel(4);
		else if(vip.getMoney()>=5000)
			vip.setLevel(3);
		else if(vip.getMoney()>=1000)
			vip.setLevel(2);
		else
			vip.setLevel(1);
		return vip;
	}
	
	/**
	 * 会员充值时积分生成等级
	 * 大于10000：星耀会员
	 * 大于5000：钻石会员
	 * 大于1000：黄金会员
	 * 小于1000：普通会员
	 * @param vip
	 * @return
	 */
	VipUser integrateToLevel(VipUser vip){
		if(vip.getIntegrate()>=10000)
			vip.setLevel(4);
		else if(vip.getIntegrate()>=5000)
			vip.setLevel(3);
		else if(vip.getIntegrate()>=1000)
			vip.setLevel(2);
		else
			vip.setLevel(1);
		return vip;
	}
	@Override
	public void invest(Integer id, String money) {
		VipUser vip = vipUserDao.findById(id);
		Double moneyD = Double.parseDouble(money);
		vip.setMoney(vip.getMoney()+moneyD);
		vip.setIntegrate(vip.getIntegrate()+Integer.parseInt(money));
		integrateToLevel(vip);
		vipUserDao.update(vip);
	}

}

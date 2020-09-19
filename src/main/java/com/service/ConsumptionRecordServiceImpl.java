package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.dao.CodeMessageDao;
import com.dao.ConsumptionRecordDao;
import com.dao.VipUserDao;
import com.domain.ConsumptionRecord;
import com.domain.VipUser;
import com.util.MyException;

@Service
public class ConsumptionRecordServiceImpl implements ConsumptionRecordService {
	
	@Autowired
	private ConsumptionRecordDao consumptionRecordDao;
	
	@Autowired
	private VipUserDao vipUserDao;
	
	@Autowired
	private CodeMessageDao codeMessageDao;

	@Override
	public void insert(ConsumptionRecord c) {
		VipUser vip = vipUserDao.findById(c.getVipId());
		if(ObjectUtils.isEmpty(vip))
			throw new MyException(codeMessageDao.findById(104));
		if(vip.getMoney()<c.getMoney())
			throw new MyException(codeMessageDao.findById(101));
		consumptionRecordDao.insert(c);
		vip.setMoney(vip.getMoney()-c.getMoney());
		vipUserDao.update(vip);
	}
	
	
}

package com.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.AppointmentDao;
import com.dao.CodeMessageDao;
import com.dao.ConsumptionRecordDao;
import com.dao.VipUserDao;
import com.domain.Appointment;
import com.domain.ConsumptionRecord;
import com.domain.VipUser;
import com.util.MyException;
import com.util.Paging;
@Service
public class AppointmentServiceImpl implements AppointmentService {

	@Autowired
	private AppointmentDao appointmentDao;
	
	@Autowired
	private VipUserDao vipUserDao;
	
	@Autowired
	private CodeMessageDao codeMessageDao;
	
	@Autowired
	private ConsumptionRecordDao consumptionRecordDao;
	
	@Override
	public void insert(Appointment appointment) {
		if(appointment.getAppointTime().before(new Date()))
			throw new MyException(codeMessageDao.findById(105));
		appointmentDao.insert(appointment);
	}

	@Override
	public Paging<Appointment> list(Paging<Appointment> paging) {
		paging = appointmentDao.list(paging);
		VipUser vip = new VipUser();
		for(Appointment a :paging.getList()){
			vip = vipUserDao.findById(a.getVipId());
			a.setLeftMoney(vip.getMoney());
			a.setName(vip.getName());
			a.setTelephone(vip.getTelephone());
		}
		return paging;
	}

	@Override
	public void ensureConsumption(Integer id) {
		Appointment a = appointmentDao.findById(id);
		VipUser vip = vipUserDao.findById(a.getVipId());
		//1.余额不足，抛出异常
		if(vip.getMoney()<a.getMoney()){
			throw new MyException(codeMessageDao.findById(101));
		}
		//2.余额充足，将预约确认消费
		a.setFlag(1);
		appointmentDao.update(a);
		//3.更新会员余额
		vip.setMoney(vip.getMoney()-a.getMoney());
		vipUserDao.update(vip);
		//4.创建本次消费记录
		ConsumptionRecord c = new ConsumptionRecord();
		c.setContext(a.getContext());
		c.setMoney(a.getMoney());
		c.setTime(new Date());
		c.setVipId(a.getVipId());
		consumptionRecordDao.insert(c);
	} 

}

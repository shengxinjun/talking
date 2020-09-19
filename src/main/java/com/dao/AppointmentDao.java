package com.dao;

import static sxj.db.Tables.APPOINTMENT;
import static sxj.db.Tables.VIP_USER;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.jooq.Condition;
import org.jooq.Configuration;
import org.jooq.DSLContext;
import org.jooq.impl.DAOImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.domain.Appointment;
import com.domain.ConsumptionRecord;
import com.domain.VipUser;
import com.util.Paging;

import sxj.db.tables.records.AppointmentRecord;

@Repository
public class AppointmentDao extends DAOImpl<AppointmentRecord, Appointment, Integer> {

	@Autowired
	private DSLContext dslContext;

	@Autowired
    public AppointmentDao(Configuration configuration) {
        super(APPOINTMENT, Appointment.class, configuration);
    }

	@Override
	protected Integer getId(Appointment appointment) {
		return appointment.getId();
	}
	
	public Paging<Appointment> list(Paging<Appointment> paging){
		List<Condition> conditions = new ArrayList<Condition>();
		conditions.add(APPOINTMENT.FLAG.eq(0));
		conditions.add(APPOINTMENT.APPOINT_TIME.ge(new Date()));
		if(!StringUtils.isEmpty(paging.getKeyword())){
			conditions.add(VIP_USER.NAME.like("%"+paging.getKeyword()+"%").or(VIP_USER.TELEPHONE.like("%"+paging.getKeyword()+"%")));
		}
		List<Appointment> list = dslContext.select(APPOINTMENT.fields()).from(APPOINTMENT).leftJoin(VIP_USER).on(APPOINTMENT.VIP_ID.eq(VIP_USER.ID)).where(conditions).orderBy(APPOINTMENT.APPOINT_TIME.desc())
				.limit(paging.offset(), paging.getPageSize()).fetchInto(Appointment.class);
		int totalCount = dslContext.fetchCount(dslContext.select(APPOINTMENT.fields()).from(APPOINTMENT).leftJoin(VIP_USER).on(APPOINTMENT.VIP_ID.eq(VIP_USER.ID)).where(conditions));
		paging.setTotalCount(totalCount);
		paging.setList(list);
		return paging;
	}

}
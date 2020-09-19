package com.dao;


import static sxj.db.Tables.VIP_USER;

import java.util.ArrayList;
import java.util.List;

import org.jooq.Condition;
import org.jooq.Configuration;
import org.jooq.DSLContext;
import org.jooq.impl.DAOImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.domain.VipUser;
import com.util.Paging;

import sxj.db.tables.records.VipUserRecord;
@Repository
public class VipUserDao extends DAOImpl<VipUserRecord,VipUser, Integer> {

	@Autowired
	private DSLContext dslContext;
    @Autowired
    public VipUserDao(Configuration configuration) {
        super(VIP_USER, VipUser.class, configuration);
    }
    @Override
    protected Integer getId(VipUser vipUser) {
        return vipUser.getId();
    }
    
    public Paging<VipUser> vipList(Paging<VipUser> paging,Integer type) {
		List<Condition> conditions = new ArrayList<>();
		if (!StringUtils.isEmpty(paging.getKeyword())) {
			conditions.add(VIP_USER.NAME.like("%"+paging.getKeyword()+"%").or(VIP_USER.TELEPHONE.like("%"+paging.getKeyword()+"%")));
		}
		if(type!=0){
			conditions.add(VIP_USER.LEVEL.eq(type));
		}
		List<VipUser> list = dslContext.select().from(VIP_USER).where(conditions).orderBy(VIP_USER.ID.desc())
				.limit(paging.offset(), paging.getPageSize()).fetchInto(VipUser.class);
		int totalCount = dslContext.fetchCount(dslContext.select().from(VIP_USER).where(conditions));
		
		paging.setTotalCount(totalCount);
		paging.setList(list);
		return paging;
	}
    public Integer insertAndReturnId(VipUser vipUser) {
    	VipUserRecord record = dslContext.newRecord(VIP_USER,vipUser);
    	record.insert();
    	return record.getId();
    }
    
    
}

package com.dao;


import org.jooq.Condition;
import org.jooq.Configuration;
import org.jooq.DSLContext;
import org.jooq.impl.DAOImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.domain.AdminUser;

import static sxj.db.Tables.ADMIN_USER;
import sxj.db.tables.records.AdminUserRecord;
@Repository
public class AdminUserDao extends DAOImpl<AdminUserRecord,AdminUser, Integer> {

	@Autowired
	private DSLContext dslContext;
    @Autowired
    public AdminUserDao(Configuration configuration) {
        super(ADMIN_USER, AdminUser.class, configuration);
    }
    @Override
    protected Integer getId(AdminUser adminUser) {
        return adminUser.getId();
    }
    
    public AdminUser findAdminByName(String name){
    	Condition condition = ADMIN_USER.USERNAME.eq(name);
    	return dslContext.select().from(ADMIN_USER).where(condition).fetchOneInto(AdminUser.class);
    }

    
}

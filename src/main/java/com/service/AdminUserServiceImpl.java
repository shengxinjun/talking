package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.dao.AdminUserDao;
import com.dao.CodeMessageDao;
import com.domain.AdminUser;
import com.util.MyException;

@Service
public class AdminUserServiceImpl implements AdminUserService {

	@Autowired
	private AdminUserDao adminUserDao;
	
	@Autowired
	private CodeMessageDao codeMessageDao;
	
	
	public boolean checkAdminUser(String name,String password){
		AdminUser adminUser  = adminUserDao.findAdminByName(name);
		if(ObjectUtils.isEmpty(adminUser)){
			throw new MyException(codeMessageDao.findById(102));
		}
		if(adminUser.getPassword().equals(password)){
			return true;
		}else{
			throw new MyException(codeMessageDao.findById(103));
		}
	}
}

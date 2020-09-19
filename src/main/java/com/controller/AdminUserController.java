package com.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.domain.AdminUser;
import com.model.Result;
import com.service.AdminUserService;
import com.util.MyException;
import com.util.WebUtils;

@Controller
@RequestMapping("/login")
public class AdminUserController {

	@Autowired
	private AdminUserService adminUserService;
	@ResponseBody
	@RequestMapping("/login")
	Result login(HttpServletRequest request,HttpServletResponse response,String name,String password,String remember) {
		Result result = new Result();
		AdminUser user = new AdminUser();
		try {
			adminUserService.checkAdminUser(name, password);
			result.setCode(1);
		} catch (MyException e) {
			result.setCode(e.getCode());
			result.setMessage(e.getMessage());
		}
		if (remember.equals("1")) {
			WebUtils.setCookie(request, response, "name", name, Integer.MAX_VALUE);
			WebUtils.setCookie(request, response, "password", password, Integer.MAX_VALUE);
		}
		WebUtils.setCookie(request, response, "remember", remember, Integer.MAX_VALUE);
		return result;
	}
	
}

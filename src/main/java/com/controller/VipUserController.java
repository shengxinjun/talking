package com.controller;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.domain.VipUser;
import com.model.Result;
import com.service.VipUserService;
import com.util.Paging;

@Controller
@RequestMapping("/vipUser")
public class VipUserController {

	@Autowired
	private VipUserService vipUserService;
	
	@RequestMapping("/list")
	String vipList(@RequestParam(required = false,defaultValue="")String keyword,@RequestParam(required = false,defaultValue="0")Integer type,@RequestParam(defaultValue="1")Integer pageNumber,Model model){
		Paging<VipUser> paging = new Paging<>();
		paging.setKeyword(keyword);
		paging.setPageSize(10);
		if(pageNumber!=null){
			paging.setPageNumber(pageNumber);
		}else{
			paging.setPageNumber(1);
		}
		Paging<VipUser> page = vipUserService.vipUserList(paging,type);
		model.addAttribute("paging", page);
		model.addAttribute("pageNumber", pageNumber);
		model.addAttribute("type", type);
		model.addAttribute("keyword", keyword);
		return "/vip_user/vip_user";
	}

	/**
	 * 跳转到新增会员的界面
	 * @return
	 */
	@RequestMapping("/turnToAdd")
	String turnToAdd(){
		
		return "vip_user/add_vip";
	}
	
	/**
	 * 新增会员
	 * @param username
	 * @param telephone
	 * @param age
	 * @param sex
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/add")
	Result add(String username,String telephone,Integer age,Integer sex,String money){
		Result result = new Result();
		VipUser vip = new VipUser();
		vip.setAge(age);
		vip.setTelephone(telephone);
		vip.setName(username);
		vip.setMoney(Double.valueOf(money));
		vip.setSex(sex);
		vip.setLevel(1);
		vip.setIntegrate(Integer.valueOf(money));
		try {
			vipUserService.addVipUser(vip);
			result.setCode(1);
		} catch (Exception e) {
			result.setCode(100);
		}
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/update")
	Result update(Integer id,String username,String telephone,Integer age,Integer sex,String money){
		Result result = new Result();
		VipUser vip = vipUserService.findVipById(id);
		vip.setAge(age);
		vip.setTelephone(telephone);
		vip.setName(username);
		vip.setMoney(Double.valueOf(money));
		vip.setSex(sex);
		try {
			vipUserService.updateVipUser(vip);
			result.setCode(1);
		} catch (Exception e) {
			result.setCode(100);
		}
		return result;
	}
	
	@RequestMapping("/editVipById/{id}")
	String editOrderById(Model model,@PathVariable Integer id) {
		
		VipUser vip = vipUserService.findVipById(id); 
		model.addAttribute("vip", vip);
		return "vip_user/vip_edit";
	}
	
	@RequestMapping("/findVipById/{id}")
	String findOrderById(Model model,@PathVariable Integer id) {
		
		VipUser vip = vipUserService.findVipById(id); 
		model.addAttribute("vip", vip);
		return "vip_user/vip_detail";
	}
	
	@ResponseBody
	@RequestMapping("/invest")
	Result invest(Integer id,String money){
		Result result = new Result();
		try {
			vipUserService.invest(id, money);
			result.setCode(1);
		} catch (Exception e) {
			result.setCode(100);
		}
		return result;
	}

}

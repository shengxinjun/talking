package com.controller;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.domain.Appointment;
import com.model.Result;
import com.service.AppointmentService;
import com.util.DateUtil;
import com.util.MyException;
import com.util.Paging;

@Controller
@RequestMapping("/appointment")
public class AppointmentController {

	@Autowired
	private AppointmentService appointmentService;

	@RequestMapping("/list")
	String list(Model model, @RequestParam(required = false, defaultValue = "") String keyword,
			@RequestParam(defaultValue = "1") Integer pageNumber) {
		Paging<Appointment> paging = new Paging<>();
		paging.setPageNumber(pageNumber);
		paging.setPageSize(10);
		paging.setKeyword(keyword);
		paging = appointmentService.list(paging);
		model.addAttribute("paging", paging);
		model.addAttribute("keyword", keyword);
		model.addAttribute("pageNumber", pageNumber);
		return "/appointment/appointment_list";
	}

	@ResponseBody
	@RequestMapping("/insert")
	Result insert(String context, String dateStr, String money, Integer vipId) {
		Result result = new Result();
		Appointment appointment = new Appointment();
		appointment.setMoney(Double.parseDouble(money));
		try {
			appointment.setAppointTime(DateUtil.date(dateStr));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		appointment.setFlag(0);
		appointment.setVipId(vipId);
		appointment.setContext(context);
		try {
			appointmentService.insert(appointment);
			result.setCode(1);
		} catch (MyException e) {
			result.setCode(e.getCode());
			result.setMessage(e.getMessage());
		}
		return result;

	}
	
	@ResponseBody
	@RequestMapping("/ensureConsumption")
	Result ensureConsumption(Integer id){
		Result result = new Result();
		try {
			appointmentService.ensureConsumption(id);
			result.setCode(1);
		} catch (MyException e) {
			result.setCode(e.getCode());
			result.setMessage(e.getMessage());
		}
		return result;
	}
}

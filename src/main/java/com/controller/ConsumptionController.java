package com.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.domain.ConsumptionRecord;
import com.model.Result;
import com.service.ConsumptionRecordService;
import com.util.MyException;

@Controller
@RequestMapping("/consumption")
public class ConsumptionController {

	@Autowired
	ConsumptionRecordService consumptionRecordService;
	
	@ResponseBody
	@RequestMapping("/insert")
	Result insert(Integer id,String money,String context){
		Result result = new Result();
		ConsumptionRecord c = new ConsumptionRecord();
		c.setVipId(id);
		c.setMoney(Double.parseDouble(money));
		c.setContext(context);
		c.setTime(new Date());
		try {
			consumptionRecordService.insert(c);
			result.setCode(1);
		} catch (MyException e) {
			result.setCode(e.getCode());
			result.setMessage(e.getMessage());
		}
		return result;
	}
}

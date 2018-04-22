package com.ssm.crud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ssm.crud.bean.Department;
import com.ssm.crud.bean.Msg;
import com.ssm.crud.service.DepartmentService;
@Controller
public class DepartmentControll {
		
	@Autowired
	DepartmentService departmentService;
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/getAllDepartment")
	@ResponseBody
	public Msg getAllDepartment(){
		
		List <Department>list = departmentService.getAllDeparment();
		
		return  Msg.success().add("depts", list);
	}
	
}

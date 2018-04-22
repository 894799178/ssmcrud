package com.ssm.crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssm.crud.bean.Department;
import com.ssm.crud.dao.DepartmentMapper;
@Service
public class DepartmentService {
		
	@Autowired
	DepartmentMapper departmentMapper;
	
	public List<Department> getAllDeparment(){
		return  departmentMapper.selectByExample(null);
	}
}

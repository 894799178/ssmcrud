package com.ssm.crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssm.crud.bean.Department;
import com.ssm.crud.bean.Emplovee;
import com.ssm.crud.bean.EmploveeExample;
import com.ssm.crud.bean.EmploveeExample.Criteria;
import com.ssm.crud.dao.DepartmentMapper;
import com.ssm.crud.dao.EmploveeMapper;

import net.sf.jsqlparser.statement.create.index.CreateIndex;

@Service
public class EmploveeService {

	@Autowired
	EmploveeMapper emploveeMapper;
	

	/**
	 * 查询所有员工
	 * @return
	 */
	public List<Emplovee> getAll() {
		// TODO Auto-generated method stub
		return emploveeMapper.selectByExampleWithDept(null);
	}


	public void sevaEmployee(Emplovee emplovee) {
		
		 emploveeMapper.insertSelective(emplovee);
	}


	public int queryEmpNameAmount(String empName) {
		EmploveeExample emploveeExample = new EmploveeExample();
		emploveeExample.createCriteria().andEmpNameLike(empName);
		if(emploveeMapper.selectByExample(emploveeExample).size()>0) {
			return 1;
		}
			
		return 0;
		
	
		
	}

	/**
	 * 按照员工id查询员工
	 * @param id
	 * @return
	 */
	public Emplovee getEmp(Integer id) {
		// TODO Auto-generated method stub
		return emploveeMapper.selectByPrimaryKey(id);
	}

	/**
	 * 更新员工数据
	 * @param emplovee
	 */
	public void updateEmp(Emplovee emplovee) {
		emploveeMapper.updateByPrimaryKeySelective(emplovee);
		
	}

	/**
	 * 按照id删除员工
	 * @param empId
	 */
	public void deleteEmpById(Integer empId) {
		
		emploveeMapper.deleteByPrimaryKey(empId);
		
	}


	public void deleteBatch(List<Integer> ids) {
		EmploveeExample emploveeExample = new EmploveeExample();
		Criteria criteria= emploveeExample.createCriteria();
		criteria.andEmpIdIn(ids);
		emploveeMapper.deleteByExample(emploveeExample);
	}
	
	

}

package com.ssm.crud.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ssm.crud.bean.Department;
import com.ssm.crud.bean.Emplovee;
import com.ssm.crud.bean.EmploveeExample;
import com.ssm.crud.dao.DepartmentMapper;
import com.ssm.crud.dao.EmploveeMapper;


/**
 * 测试DAO层的工作
 * 1.导入SpringTest模块
 * 2.@ContextConfiguration指定Spring配置文件的位置
 * @author 007
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationcontext.xml"})
public class DAOTest {
	
	@Autowired
	DepartmentMapper departmentMapper;
	
	@Autowired
	EmploveeMapper emploveeMapper;
	
	@Autowired
	SqlSession sqlSession;

	public DAOTest() throws IOException {
//		ApplicationContext ioc =  new ClassPathXmlApplicationContext("applicationcontext.xml");
//		ioc.getBean(DepartmentMapper.class);
		
		
	}
	
	
	@Test
	public void testEmp() throws IOException {
		
		
//		departmentMapper.insertSelective(new Department(null,"开发部"));
//		departmentMapper.insertSelective(new Department(null,"测试部"));
		
		//2.单个插入员工
		//emploveeMapper.insertSelective(new Emplovee(null,"小李2","0","xiaoli@qq.com",1));
		EmploveeExample emploveeExample = new EmploveeExample();
		emploveeExample.createCriteria().andEmpNameLike("小明");
		System.out.println(emploveeMapper.selectByExample(emploveeExample));
	}
	
	
	
	

}

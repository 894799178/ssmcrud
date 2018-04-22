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
 * ����DAO��Ĺ���
 * 1.����SpringTestģ��
 * 2.@ContextConfigurationָ��Spring�����ļ���λ��
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
		
		
//		departmentMapper.insertSelective(new Department(null,"������"));
//		departmentMapper.insertSelective(new Department(null,"���Բ�"));
		
		//2.��������Ա��
		//emploveeMapper.insertSelective(new Emplovee(null,"С��2","0","xiaoli@qq.com",1));
		EmploveeExample emploveeExample = new EmploveeExample();
		emploveeExample.createCriteria().andEmpNameLike("С��");
		System.out.println(emploveeMapper.selectByExample(emploveeExample));
	}
	
	
	
	

}

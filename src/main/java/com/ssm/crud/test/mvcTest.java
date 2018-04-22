package com.ssm.crud.test;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.portlet.MockRenderRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.github.pagehelper.PageInfo;
import com.ssm.crud.bean.Emplovee;

/**
 * ʹ��Spring����ģ���ṩ��������
 * @author 007
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations={"classpath:applicationcontext.xml","file:src/main/webapp/WEB-INF/dispatcherServlet-servlet.xml"})
public class mvcTest {
	//�����mvc����
	MockMvc mockMvc;
	//����Springmvc��ioc
	@Autowired
	WebApplicationContext context;
	@Before
	public void initMockMvc() {
		mockMvc=MockMvcBuilders.webAppContextSetup(context).build();
	}
	@Test
	public void testPage() throws Exception {
		//ģ�������õ�����ֵ
		MvcResult result=mockMvc.perform(MockMvcRequestBuilders.get("/emps").param("pageNumber", "2")).andReturn();
		
		//����ɹ�������õ��������е�����
		    MockHttpServletRequest mockRenderRequest=result.getRequest();
		    PageInfo obj=(PageInfo) mockRenderRequest.getAttribute("pageInfo");
		    System.out.println("��ǰҳ��:"+obj.getPageNum());
		    System.out.println("��ҳ��:"+obj.getPages());
		    System.out.println("�ܼ�¼��:"+obj.getTotal());
		    System.out.println("===��Ҫ������ʾ��ҳ��===");
		    int[] nums=obj.getNavigatepageNums();
		    for(int i:nums) {
		    	System.out.print(" "+i);
		    } 
		    List<Emplovee>list=obj.getList();
		    for(Emplovee emp:list) {
		    	System.out.println("Id"+emp.getEmpId()+"-->"+"name:"+emp.getEmpName());
		    }
		
	}
	

}

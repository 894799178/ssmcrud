package com.ssm.crud.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ssm.crud.bean.Emplovee;
import com.ssm.crud.bean.Msg;
import com.ssm.crud.service.EmploveeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * ����Ա������ɾ�Ĳ�
 * @author 007
 *
 */
@Controller
public class EmploveeController {
	
	@Autowired
	EmploveeService emploveeService;
	/**
	 * ��Ҫ����jackson��
	 * @return
	 */
	@RequestMapping("/emps")
	@ResponseBody
	public Msg getEmpwithJson(@RequestParam(value="pageNumber",defaultValue="1") Integer pageNumber) {
		//��ѯǰ����,����ҳ���Լ�ÿҳ��С
				PageHelper.startPage(pageNumber, 5);
				//startPage��������������ѯ����һ����ҳ��ѯ
				List <Emplovee> list =emploveeService.getAll();
				//ʹ��pageInfo���а�װ,ֻ��Ҫ��PageInfo����ҳ�����
				//��װ����ϸ�ķ�ҳ��Ϣ,�������ǲ�ѯ�����Ľ��.�ڶ���������������ʾ��ҳ��
				PageInfo pageInfo = new PageInfo(list,5); 
				return Msg.success().add("PageInfo", pageInfo);
	}
	/**
	 * ����Ա��
	 * @param emplovee
	 * @return
	 */
	@RequestMapping(value= "/sevaEmployee",method=RequestMethod.POST)
	@ResponseBody
	public Msg sevaEmployee(@Valid Emplovee emplovee,BindingResult result) {
		if(result.hasErrors()) {
			//У��ʧ��,Ӧ�÷���ʧ��,��ģ̬������ʾУ��ʧ�ܵĴ�����Ϣ.
			Map<String,Object> map = new HashMap<>();
			List<FieldError> errors = result.getFieldErrors();
			for(FieldError fiedError : errors) {
				System.out.println("������ֶ���:"+fiedError.getField());
				System.out.println(fiedError.getDefaultMessage());
				map.put(fiedError.getField(), fiedError.getDefaultMessage());
			}
			return Msg.fail().add("errorFields", map);
		}
		System.out.println(emplovee);
		emploveeService.sevaEmployee(emplovee);
		return  Msg.success();
	}
	//�û�����У��
	@RequestMapping(value="/validateEmpName",method=RequestMethod.POST)
	@ResponseBody
	public Msg validateEmpName(String empName) {
		//System.out.println(empName);
		
		if(emploveeService.queryEmpNameAmount(empName)==1) {
			Msg m = Msg.fail();
			m.setMsg("�û����ظ�");
			return m;
		}
		
		return Msg.success();
	}
	
	/*
	 * ��ѯԱ������,��ajax�ύ,����
	 * @return
	 */
	//@RequestMapping("/emps")
	/*public String getEmps(@RequestParam(value="pageNumber",defaultValue="1") Integer pageNumber,Model model) {
		//����PageHelper
		//��ѯǰ����,����ҳ���Լ�ÿҳ��С
		PageHelper.startPage(pageNumber, 5);
		//startPage��������������ѯ����һ����ҳ��ѯ
		List <Emplovee> list =emploveeService.getAll();
		//ʹ��pageInfo���а�װ,ֻ��Ҫ��PageInfo����ҳ�����
		//��װ����ϸ�ķ�ҳ��Ϣ,�������ǲ�ѯ�����Ľ��.�ڶ���������������ʾ��ҳ��
		PageInfo pageInfo = new PageInfo(list,5);
		model.addAttribute("pageInfo", pageInfo);
		return "list";
	}*/
	
	@RequestMapping(value="/emp/{id}",method=RequestMethod.GET)
	@ResponseBody
	public Msg getEmp(@PathVariable("id")Integer id) {
		Emplovee emplovee = emploveeService.getEmp(id);
		return Msg.success().add("emp", emplovee);
	}
	/**
	 * Ա������
	 * @param emplovee
	 * @return
	 */
	@RequestMapping(value="/emp/{empId}",method=RequestMethod.PUT)
	@ResponseBody
	public Msg seveUpdateEmp(Emplovee emplovee) {
		emploveeService.updateEmp(emplovee);
		return Msg.success();
	}
	
	/**
	 * ɾ������Ա��������ɾ������
	 * @param ids
	 * @return
	 */
	@RequestMapping(value="/emp/{ids}",method=RequestMethod.DELETE)
	@ResponseBody
	public Msg deleteEmp(@PathVariable("ids") String ids) {
		System.out.println("empId:"+ids);
		
		if(ids.contains("-")) {
			//����ɾ������
			List<Integer> delList = new ArrayList<>();
			String[] str_ids = ids.split("-");
			//��װId
			for (String string : str_ids) {
				delList.add(Integer.parseInt(string));
			}
			emploveeService.deleteBatch(delList);
		}else {
			//����idʱ��ɾ������
			Integer id = Integer.parseInt(ids);
			emploveeService.deleteEmpById(id);
		}
			
		
		return Msg.success();
	}
}

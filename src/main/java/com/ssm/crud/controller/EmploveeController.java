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
 * 处理员工的增删改查
 * @author 007
 *
 */
@Controller
public class EmploveeController {
	
	@Autowired
	EmploveeService emploveeService;
	/**
	 * 需要导入jackson包
	 * @return
	 */
	@RequestMapping("/emps")
	@ResponseBody
	public Msg getEmpwithJson(@RequestParam(value="pageNumber",defaultValue="1") Integer pageNumber) {
		//查询前调用,传入页码以及每页大小
				PageHelper.startPage(pageNumber, 5);
				//startPage后面紧跟着这个查询就是一个分页查询
				List <Emplovee> list =emploveeService.getAll();
				//使用pageInfo进行包装,只需要将PageInfo交给页面就行
				//封装了详细的分页信息,包括我们查询出来的结果.第二个参数是连续显示的页数
				PageInfo pageInfo = new PageInfo(list,5); 
				return Msg.success().add("PageInfo", pageInfo);
	}
	/**
	 * 保存员工
	 * @param emplovee
	 * @return
	 */
	@RequestMapping(value= "/sevaEmployee",method=RequestMethod.POST)
	@ResponseBody
	public Msg sevaEmployee(@Valid Emplovee emplovee,BindingResult result) {
		if(result.hasErrors()) {
			//校验失败,应该返回失败,在模态框中显示校验失败的错误信息.
			Map<String,Object> map = new HashMap<>();
			List<FieldError> errors = result.getFieldErrors();
			for(FieldError fiedError : errors) {
				System.out.println("错误的字段名:"+fiedError.getField());
				System.out.println(fiedError.getDefaultMessage());
				map.put(fiedError.getField(), fiedError.getDefaultMessage());
			}
			return Msg.fail().add("errorFields", map);
		}
		System.out.println(emplovee);
		emploveeService.sevaEmployee(emplovee);
		return  Msg.success();
	}
	//用户名的校验
	@RequestMapping(value="/validateEmpName",method=RequestMethod.POST)
	@ResponseBody
	public Msg validateEmpName(String empName) {
		//System.out.println(empName);
		
		if(emploveeService.queryEmpNameAmount(empName)==1) {
			Msg m = Msg.fail();
			m.setMsg("用户名重复");
			return m;
		}
		
		return Msg.success();
	}
	
	/*
	 * 查询员工数据,非ajax提交,弃用
	 * @return
	 */
	//@RequestMapping("/emps")
	/*public String getEmps(@RequestParam(value="pageNumber",defaultValue="1") Integer pageNumber,Model model) {
		//引用PageHelper
		//查询前调用,传入页码以及每页大小
		PageHelper.startPage(pageNumber, 5);
		//startPage后面紧跟着这个查询就是一个分页查询
		List <Emplovee> list =emploveeService.getAll();
		//使用pageInfo进行包装,只需要将PageInfo交给页面就行
		//封装了详细的分页信息,包括我们查询出来的结果.第二个参数是连续显示的页数
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
	 * 员工更新
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
	 * 删除单个员工和批量删除整合
	 * @param ids
	 * @return
	 */
	@RequestMapping(value="/emp/{ids}",method=RequestMethod.DELETE)
	@ResponseBody
	public Msg deleteEmp(@PathVariable("ids") String ids) {
		System.out.println("empId:"+ids);
		
		if(ids.contains("-")) {
			//批量删除方法
			List<Integer> delList = new ArrayList<>();
			String[] str_ids = ids.split("-");
			//组装Id
			for (String string : str_ids) {
				delList.add(Integer.parseInt(string));
			}
			emploveeService.deleteBatch(delList);
		}else {
			//单个id时的删除方法
			Integer id = Integer.parseInt(ids);
			emploveeService.deleteEmpById(id);
		}
			
		
		return Msg.success();
	}
}

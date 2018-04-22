<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>员工列表</title>
<%
	application.setAttribute("APP_PATH", request.getContextPath());
%>
<script type="text/javascript"
	src="${APP_PATH}/static/js/jquery-3.3.1.min.js"></script>
<!--引入样式  -->
<link
	href="${APP_PATH}/static/bootstrap-3.3.7-dist/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="${APP_PATH}/static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
</head>
<body>
	<!-- 员工修改的模态框 -->
	<div class="modal fade" id="empUpdateModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">员工修改</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal">
						<div class="form-group">
							<label class="col-sm-2 control-label">empName</label>
							<div class="col-sm-10">
								<p class="form-control-static" name="empName"
									id="empName_update_static"></p>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">email</label>
							<div class="col-sm-10">
								<input type="text" name="email" class="form-control"
									id="email_update_input" placeholder="email@qq.com"> <span
									class="help-block"></span>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">gender</label>
							<div class="col-sm-10">
								<label class="radio-inline"> <input type="radio"
									name="gender" id="gender1_update_input" value="0"
									checked="checked"> 男
								</label> <label class="radio-inline"> <input type="radio"
									name="gender" id="gender2_update_input" value="1"> 女
								</label>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">deptName</label>
							<div class="col-sm-4">
								<!--提交部门Id即可  -->
								<select class="form-control" name="dId" id="dept_update_name">
								</select>
							</div>
						</div>

					</form>

				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary"
						id="seva_Employee_update_data_btn">保存</button>
				</div>
			</div>
		</div>
	</div>

	<!-- 员工添加的模态框 -->
	<div class="modal fade" id="empAddModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">员工添加</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal">
						<div class="form-group">
							<label class="col-sm-2 control-label">empName</label>
							<div class="col-sm-10">
								<input type="text" name="empName" class="form-control"
									id="empName_input" placeholder="empName"> <span
									class="help-block"></span>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">email</label>
							<div class="col-sm-10">
								<input type="text" name="email" class="form-control"
									id="email_input" placeholder="email@qq.com"> <span
									class="help-block"></span>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">gender</label>
							<div class="col-sm-10">
								<label class="radio-inline"> <input type="radio"
									name="gender" id="gender1_input" value="0" checked="checked">
									男
								</label> <label class="radio-inline"> <input type="radio"
									name="gender" id="gender2_input" value="1"> 女
								</label>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">deptName</label>
							<div class="col-sm-4">
								<!--提交部门Id即可  -->
								<select class="form-control" name="dId" id="dept_name">
								</select>
							</div>
						</div>

					</form>

				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary"
						id="seva_Employee_data_btn">保存</button>
				</div>
			</div>
		</div>
	</div>



	<!--搭建显示页面  -->
	<div class="container">
		<!--标题  -->
		<div class="row">
			<div class="col-md-12">
				<h1>SSM-CRUD</h1>
			</div>
		</div>
		<!--按钮  -->
		<div class="row">
			<div class="col-md-4 col-md-offset-8">
				<button class="btn btn-primary" id="emp_add_modal_btn">新增</button>
				<button class="btn btn-danger" id = "emp_delete_all">删除</button>
			</div>
		</div>
		<!--显示表格  -->
		<div class="row">
			<div class="col-md-12">
				<table class="table table-hover" id="emp_table">
					<thead>
						<tr>
						
							<th>
								<input type="checkbox" id="checkbox_all"/>
							</th>
							<th>#</th>
							<th>empName</th>
							<th>gender</th>
							<th>email</th>
							<th>deptName</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>

					</tbody>
				</table>
			</div>
		</div>
		<!--显示分页数据  -->
		<div class="row" id="page_data">
			<!--显示分页文字信息  -->
			<div class="col-md-6" id="page_data_totalPage"></div>
			<!--显示条信息  -->
			<div class="col-md-6" id="page_nav_data"></div>
		</div>
	</div>
	<script type="text/javascript">
		var totalRecord, currentNum;
		//1.页面加载完成以后,直接发送ajax请求,要到分页数据
		$(function() {
			to_page(1);
		});
		function to_page(pageNumber) {
			$.ajax({
				url : "${APP_PATH}/emps",
				data : "pageNumber=" + pageNumber,
				type : "GET",
				success : function(result) {
					console.log(result)
					//1.解析并显示员工数据
					build_emps_table(result);
					//2.解析并显示分页信息
					build_page_nav(result);
				}
			});
		}
		function build_emps_table(result) {
			$("#emp_table tbody").empty();
			var emps = result.extend.PageInfo.list;
			$.each(emps, function(index, item) {
				//alert(item.empName)
				var checkBoxTd = $("<td><input type='checkbox' class='check_item'/></td>") 
				var empIdTd = $("<td></td>").append(item.empId);
				var empNameTd = $("<td></td>").append(item.empName);
				var gender = item.gender == '0' ? "男" : "女";
				var genderTd = $("<td></td>").append(gender);
				var emailTd = $("<td></td>").append(item.email);
				var deptName = $("<td></td>").append(item.department.deptName);
				/*
					<button class="btn btn-primary btn-sm">
						<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>编辑
					</button>
				 */

				var editBtn = $("<button></button>").addClass(
						"btn btn-primary btn-sm edit_btn").append(
						"<span></span>").addClass("glyphicon glyphicon-pencil")
						.append("编辑");
				editBtn.attr("edit-id", item.empId)
				var delBtn = $("<button></button>").addClass(
						"btn btn-danger btn-sm del_btn")
						.append("<span></span>").addClass(
								"glyphicon glyphicon-trash").append("删除");
				delBtn.attr("del-id", item.empId)
				var bthTd = $("<td></td>").append(editBtn).append(" ").append(
						delBtn);
				//append方法执行完成以后依旧返回原来的元素
				$("<tr></tr>").append(checkBoxTd).append(empIdTd).append(empNameTd).append(
						genderTd).append(emailTd).append(deptName)
						.append(bthTd).appendTo("#emp_table tbody");

			})
		}

		function build_page_nav(result) {
			//当前 页,总 页,总 纪录</div>
			$("#page_data_totalPage").empty();
			$("#page_nav_data").empty();
			var pageInfo = result.extend.PageInfo
			var pageNum = pageInfo.pageNum;
			var pages = pageInfo.pages;
			var total = pageInfo.total;
			totalRecord = total;
			currentNum = pageNum;
			var temp = "当前 " + pageNum + "页,总" + pages + "页,总" + total + "纪录";
			$("#page_data_totalPage").append(temp);
			var ul = $("<ul></ul>").addClass("pagination");
			var firstPageLi = $("<li></li>").append(
					$("<a></a>").append("首页").attr("href", "#"));
			var prePageLi = $("<li></li>").append(
					$("<a></a>").append("&laquo;"));
			if (pageInfo.hasPreviousPage == false) {
				firstPageLi.addClass("disabled");
				prePageLi.addClass("disabled");
			} else {
				//为元素添加单击事件
				firstPageLi.click(function() {
					to_page(1);
				})
				prePageLi.click(function() {

					to_page(pageInfo.pageNum - 1);
				})
			}
			var nextPageLi = $("<li></li>").append(
					$("<a></a>").append("&raquo;"));
			var lastPageLi = $("<li></li>").append(
					$("<a></a>").append("末页").attr("href", "#"));
			if (pageInfo.hasNextPage == false) {
				nextPageLi.addClass("disabled");
				lastPageLi.addClass("disabled");
			} else {
				//元素添加点击事件
				nextPageLi.click(function() {
					to_page(pageInfo.pageNum + 1);
				})
				lastPageLi.click(function() {
					to_page(pageInfo.pages);
				})
			}

			ul.append(firstPageLi).append(prePageLi)
			//遍历给ul中添加页码提示
			$.each(pageInfo.navigatepageNums, function(index, item) {
				var numLi = $("<li></li>").append(
						$("<a></a>").append(item).attr("href", "#"));
				if (pageInfo.pageNum == item) {
					numLi.addClass("active")
				}
				numLi.click(function() {
					to_page(item);
				})
				ul.append(numLi);
			})
			//添加下一页和末页的提示
			ul.append(nextPageLi).append(lastPageLi);
			var navEle = $("<nav></nav>").append(ul);
			navEle.appendTo("#page_nav_data");
			//console.log($("asd").appendTo("#page_data"));
		}
		//表单重置
		function reset_form(ele) {

			$(ele)[0].reset();
			$(ele).find("*").removeClass("has-error has-success");
			$(ele).find(".help-block").text("");
		}
		//点击"新增"按钮的时候触发的ajax请求.
		$("#emp_add_modal_btn").click(function() {
			//表单的完整重置
			reset_form("#empAddModal form");
			//$("#empAddModal")[0].reset();
			$("#empAddModal select").empty();
			getDept("#dept_name")

			//创建模态框
			$("#empAddModal").modal({
				backdrop : "static"
			});

		});

		function getDept(ele) {
			//提前发送ajax请求获取部门信息
			$.ajax({
				url : "${APP_PATH}/getAllDepartment",
				type : "GET",
				success : function(result) {
					console.log(result)
					$.each(result.extend.depts, function(idnex, item) {
						$("<option></option>").append(item.deptName).attr(
								"name", "deptId").attr("value", item.deptId)
								.appendTo(ele);

					})
				}
			});
		}

		//校验表单
		function validate_add_form() {
			var empName = $("#empName_input").val();
			var regName = /(^[a-zA-Z0-9_-]{6,16}$)|(^[\u2E80-\u9FFF]{2,5}$)/;
			if (!regName.test(empName)) {
				//alert("用户名可以是2-5位中文或者6-16位英文和数字的组合");
				show_validate_mag("#empName_input", "error",
						"用户名可以是2-5位中文或者6-16位英文和数字的组合");
				return false;
			} else {
				show_validate_mag("#empName_input", "success", "");

			}
			var empEmail = $("#email_input").val();
			var regEmail = /^([a-z0-9_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$/;
			if (!regEmail.test(empEmail)) {
				//alert("邮箱不正常!");
				show_validate_mag("#email_input", "error", "邮箱不正确!");
				return false;
			} else {
				show_validate_mag("#email_input", "success", "");
			}
			if (!empName_validate()) {
				return false;
			}
			return true;
		}
		//用户名输入框发生变化的时候
		/*$("#empName_input").change(function(){
			empName_validate()
		})*/

		//用户名的重复校验
		function empName_validate() {
			var result1;
			$.ajax({
				url : "${APP_PATH}/validateEmpName",
				type : "POST",
				data : $("#empName_input").serialize(),
				async : false,
				success : function(result) {
					console.log(result)
					result1 = result;
				}
			});
			if (result1.code == 100) {
				show_validate_mag("#empName_input", "success", "");
			} else if (result1.code == 200) {
				show_validate_mag("#empName_input", "error", result1.msg);
				return false;
			}
			return true;
		}
		//显示数据
		function show_validate_mag(ele, status, msg) {
			//移除元素的当前状态
			$(ele).parent().removeClass("has-success has-error");
			$(ele).next("span").text();
			//数据校验主体
			if (status == "success") {
				$(ele).parent().addClass("has-success");
				$(ele).next("span").text(msg);
			} else if (status == "error") {
				$(ele).parent().addClass("has-error");
				$(ele).next("span").text(msg)
			}

		}
		//新增按钮下的模态框的_保存按钮点击事件
		$("#seva_Employee_data_btn")
				.click(
						function() {

							if (!validate_add_form()) {
								return false;
							}
							$
									.ajax({
										url : "${APP_PATH}/sevaEmployee",
										type : "POST",
										data : $("#empAddModal form")
												.serialize(),
										success : function(result) {

											if (result.code == 100) {
												//关闭模态框
												$("#empAddModal").modal("hide");
												to_page(totalRecord);
											} else {
												//显示失败信息
												if (undefined != result.extend.errorFields.empName) {
													//员工邮箱错误
													show_validate_mag(
															"#empName_input",
															"error",
															result.extend.errorFields.empName);
												} else if (undefined != result.extend.errorFields.email) {
													show_validate_mag(
															"#email_input",
															"error",
															result.extend.errorFields.email);
												}

											}

										}
									});
						})

		function getEmp(id) {
			$("#empUpdateModal select").empty();
			$.ajax({
				url : "${APP_PATH}/emp/" + id,
				type : "GET",
				success : function(result) {
					var empData = result.extend.emp;
					console.log(empData);
					$("#empName_update_static").text(empData.empName);
					$("#email_update_input").val(empData.email);
					//	alert($("#empUpdateModal input[name=gender]").val())
					$("#empUpdateModal input[name=gender]").val(
							[ empData.gender ])
					$("#empUpdateModal select").val([ empData.dId ])
				}
			})
		}

		$(document).on("click", ".edit_btn", function() {
			//alert("aa");
			//查询员工信息
			var empId = $(this).attr("edit-id");
			getEmp(empId);
			//查询部门信息
			getDept("#dept_update_name")
			//传递员工id到模态框的保存按钮
			$("#seva_Employee_update_data_btn").attr("edit-id", empId)
			$("#empUpdateModal").modal({
				backdrop : "static"
			});
		})

		//点击更新,更新员工数据
		$("#seva_Employee_update_data_btn").click(function() {
			var empEmail = $("#email_update_input").val();
			var regEmail = /^([a-z0-9_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$/;
			if (!regEmail.test(empEmail)) {
				//alert("邮箱不正常!");
				show_validate_mag("#email_update_input", "error", "邮箱不正确!");
				return false;
			} else {
				show_validate_mag("#email_update_input", "success", "");
			}
			//2.发送ajax请求
			$.ajax({
				url : "${APP_PATH}/emp/" + $(this).attr("edit-id"),
				type : "POST",
				data : $("#empUpdateModal form").serialize() + "&_method=PUT",
				success : function(result) {
					console.log(result);
					//关闭模态框
					$("#empUpdateModal").modal("hide");
					to_page(currentNum);
				}

			});
		})
		//删除单个元素,绑定删除按钮的点击事件
		$(document).on("click", ".del_btn", function() {
			var empName= $(this).parents("tr").find("td:eq(2)").text();
			if (confirm("你确定要删除"+empName+"吗?")) {
				var id = $(this).attr("del-id");
				$.ajax({
					url : "${APP_PATH}/emp/" + id,
					type : "DELETE",
					success : function(result) {
						alert(result.msg);
						to_page(currentNum);
					}
				})
			}else{
				return false;
			}

		})
		//多选框的全选和全不选事件
		$("#checkbox_all").click(function(){
			//读取checkBox的选中状态
			var status=$(this).prop("checked");
			$(".check_item").prop("checked",status);
		})
		
		$(document).on("click",".check_item",function(){
			
			if($(".check_item:checked").length == $(".check_item").length){
				$("#checkbox_all").prop("checked",true);
			}else{
				$("#checkbox_all").prop("checked",false);
			}
		})
		//点击批量删除
		$("#emp_delete_all").click(function(){
			var empName ="";
			var del_strId = "";
			//遍历列表中的checkBox是勾选的对象
			$.each($(".check_item:checked"),function(){
				empName+=$(this).parents("tr").find("td:eq(2)").text()+",";
				del_strId+=$(this).parents("tr").find("td:eq(1)").text()+"-";
			})
			//去除逗号.
			empName=empName.substring(0,empName.length-1);
			del_strId=del_strId.substring(0,del_strId.length-1);
			if(confirm("确认要删除["+empName+"]吗?")){
				 $.ajax({
					url:"${APP_PATH}/emp/"+del_strId,
					type:"DELETE",
					success:function(result){
						alert(result.msg);
						to_page(currentNum);
					}
				 });
			}else{
				
			}
		})
		
		
	</script>
</body>
</html>
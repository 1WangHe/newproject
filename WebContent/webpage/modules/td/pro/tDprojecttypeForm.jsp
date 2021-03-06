<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>保存项目栏目成功管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		var validateForm;
		function doSubmit(){//回调函数，在编辑和保存动作时，供openDialog调用提交表单。
		  if(validateForm.form()){
			  $("#inputForm").submit();
			  return true;
		  }
	
		  return false;
		}
		$(document).ready(function() {
			validateForm = $("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
			
		});
		function changnumber(obj){
			var a=obj.value;
			if(a === "" || a ==null){   
		        a=0;
		    }
			if(!isNaN(a)){
		        return true;
		    }else{
		    	layer.open({
		   		    content: "该处只能填写数字！"
		   		       ,btn: '确定'
		   		     }); 
		        obj.value=0;
		    }
		}
	</script>
</head>
<body class="hideScroll">
		<form:form id="inputForm" modelAttribute="tDprojecttype" action="${ctx}/td/pro/tDprojecttype/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>	
		<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
		   <tbody>
				<tr>
					<td class="width-15 active"><label class="pull-right">栏目名称：</label></td>
					<td class="width-35">
						<form:input path="name" htmlEscape="false"    class="form-control " />
					</td>
					<td class="width-15 active"><label class="pull-right">排序：</label></td>
					<td class="width-35">
						<form:input path="sort" htmlEscape="false"    class="form-control " onchange="changnumber(this)"/>
					</td>
				</tr>
		 	</tbody>
		</table>
	</form:form>
</body>
</html>
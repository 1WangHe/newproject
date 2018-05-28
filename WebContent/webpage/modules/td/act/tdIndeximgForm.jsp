<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>保存推广图片成功管理</title>
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
		function setImagePreview(imgID,fileId){
			var form = new FormData();
			var fileimg=document.getElementById(fileId);
			var fileobj=fileimg.files[0];
			form.append("file",fileobj);
			$.ajax({
			     url:"${ctx}/td/act/tdActivitycontent/upimage",
			     type:"post",
			     data:form,
			     dataType:"text",
			     processData:false,
			     contentType:false,
			     success:function(data){	
			    	 var id="file"+imgID;
			         document.getElementById(id).value=data;
			         document.getElementById(imgID).src=data;
			     },
			     error:function(data){
			         layer.open({
			   		    content: "图片上传失败！"
			   		       ,btn: '确定'
			   		     });   
			     },
			 });  
		};
	</script>
</head>
<body class="hideScroll">
		<form:form id="inputForm" modelAttribute="tdIndeximg" action="${ctx}/td/act/tdIndeximg/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>	
		<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
		   <tbody>
		   <tr>
					<td class="width-15 active"><label class="pull-right">内容封面图:</label></td>
					<td class="width-35">
						<div class="div-up">
							<c:if test="${not empty tdIndeximg.img}">
								<img  id="img9" src="${tdIndeximg.img}" height="50" width="80"/>
							</c:if>
							<c:if test="${empty tdIndeximg.img}">
								<img  id="img9" height="50" width="80"/>
							</c:if>
							 <input class="input_img" id="file9" type="file" style="height: 50px;width: 80px;top:-50px;position:relative;opacity:0;"  onchange="setImagePreview('img9','file9')" />
						</div>
						<form:hidden id="fileimg9" path="img" />
					</td>	
					<td class="width-15 active"><label class="pull-right">路径类型：</label></td>
					<td class="width-35">
						<form:input path="urltype" htmlEscape="false"    class="form-control "/>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">排序：</label></td>
					<td class="width-35">
						<form:input path="sort" htmlEscape="false"    class="form-control " onchange="changnumber(this)"/>
					</td>
					<td class="width-15 active"><label class="pull-right">备注信息：</label></td>
					<td class="width-35">
						<form:textarea path="remarks" htmlEscape="false" rows="4"    class="form-control "/>
					</td>
					
					
		  		</tr>
		 	</tbody>
		</table>
	</form:form>
</body>
</html>
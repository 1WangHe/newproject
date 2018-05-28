<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>保存项目内容成功管理</title>
	<meta name="decorator" content="default"/>
	<style type="text/css">
		.div-up{
		width: 80px;
		height: 50px;
		}
		
		.div-down{
		position: absolute;
		width: 80;
		height: 50;
		}
		.form-control1{
		height: 50px;
		width: 80px;
		opacity:0;
		position: absolute;
		top:-50px;
		}
		.div-time{
		width: 200px;
		height: 40px;
		}
		
	</style>
	<link href="${ctxStatic}/summernote/summernote.css" rel="stylesheet">
	 <link href="${ctxStatic}/summernote/summernote-bs3.css" rel="stylesheet">
	 <script src="${ctxStatic}/summernote/summernote.min.js"></script>
	 <script src="${ctxStatic}/summernote/summernote-zh-CN.js"></script>
	<script type="text/javascript">
		var validateForm;
		function doSubmit(){//回调函数，在编辑和保存动作时，供openDialog调用提交表单。
		  if(validateForm.form()){
			  $("#content").val($("#rich_content").next().find(".note-editable").code());//取富文本的值
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
			 
        	//富文本初始化
			$(".summernote").summernote({
				height: 300,
                lang: 'zh-CN', 
                toolbar: [
                          ['style', ['bold', 'italic', 'underline', 'clear']],
                          ['fontsize', ['fontsize']],
                          ['color', ['color']],
                          ['para', ['ul', 'ol', 'paragraph']],
                          ['height', ['height']],
                          ['insert', ['picture', 'video']]
                         ],
                onImageUpload: function(files, editor, $editable) {  
                sendFile(files[0],editor,$editable);  
				}  
                 
			
            });
			$("#rich_content").next().find(".note-editable").html(  $("#content").val());
 			$("#rich_content").next().find(".note-editable").html(  $("#rich_content").next().find(".note-editable").text());
 			
			
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
		function sendFile(file,editor,$editable){
			var filename=false;
			try{
				filename=file['name'];
			}catch(e){
				filename=false;
			}
			if(!filename){
				$(".note-alarm").remove();
			}
			data=new FormData();
			data.append("file",file);
			$.ajax({
			     url:"${ctx}/td/act/tdActivitycontent/upimage",
			     type:"post",
			     data:data,
			     dataType:"text",
			     processData:false,
			     contentType:false,
			     success:function(data){	
			    	 var path=data;
			    	 editor.insertImage($editable, path);	    
			                   
			     },
			     error:function(data){
			    	 layer.open({
				   		    content: "图片上传失败！"
				   		       ,btn: '确定'
				   		     });   
			     }
			 });  
		}
		
		
		function addTime(val,objid){
			document.getElementById(objid).value=val;
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
		}
	</script>
</head>
<body class="hideScroll">
		<form:form id="inputForm" modelAttribute="tDprojectcontent" action="${ctx}/td/pro/tDprojectcontent/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>	
		<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
		   <tbody>
				 <tr>
		   			<td class="width-15 active"><label class="pull-right">内容标题：</label></td>
					<td class="width-35">
						<form:input path="title" htmlEscape="false"    class="form-control "/>
					</td>
					<td class="width-15 active"><label class="pull-right">内容封面图:</label></td>
					<td class="width-35">
						<div class="div-up">
							<c:if test="${not empty tDprojectcontent.pic}">
								<img  id="img9" src="${tDprojectcontent.pic}" height="50" width="80"/>
							</c:if>
							<c:if test="${empty tDprojectcontent.pic}">
								<img  id="img9" height="50" width="80"/>
							</c:if>
							 <input class="input_img" id="file9" type="file" style="height: 50px;width: 80px;top:-50px;position:relative;opacity:0;"  onchange="setImagePreview('img9','file9')" />
						</div>
						<form:hidden id="fileimg9" path="pic" />
					</td>	
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">内容：</label></td>
					<td class="width-35" colspan="3">
					<form:hidden path="content"/>
						<div class="summernote" id="rich_content"></div>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">开始时间：</label></td>
					<td class="width-35">
						<div class="div-time">
						<input  type="date" style="width: 100px;height:40px;left:150px;position:relative;opacity:0.6;" onchange="addTime(this.value,'startdate')">
						<form:input  id="startdate" path="starttime" htmlEscape="false"    class="form-control " style="width: 220px;height:40px;top:-40px;position:relative;" />
						</div>
					</td>
					<td class="width-15 active"><label class="pull-right">结束时间：</label></td>
					<td class="width-35">
						<div class="div-time">
						<input  type="date" style="width: 100px;height:40px;left:150px;position:relative;opacity:0.6;" onchange="addTime(this.value,'enddate')">
						<form:input  id="enddate" path="endtime" htmlEscape="false"    class="form-control " style="width: 220px;height:40px;top:-40px;position:relative;" />
						</div>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">排序：</label></td>
					<td class="width-35">
						<form:input path="sort" htmlEscape="false"    class="form-control " onchange="changnumber(this)"/>
					</td>
					<td class="width-15 active"><label class="pull-right">栏目类别：</label></td>
					<td class="width-35">	
						<form:select path="typeid" htmlEscape="false"    class="form-control ">
							<form:option value="">--请选择--</form:option>
							<c:forEach items="${typelist}" var="type">
								<form:option value="${type.id}">${type.name}</form:option>
							</c:forEach>
						</form:select>
					</td>
				</tr>
				<tr>
					
					<td class="width-15 active"><label class="pull-right">地点：</label></td>
					<td class="width-35">
						<form:input path="address" htmlEscape="false"    class="form-control " />
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
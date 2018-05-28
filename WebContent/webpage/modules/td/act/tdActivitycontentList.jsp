<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>保存活动内容成功管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
		});
	</script>
</head>
<body class="gray-bg">
	<div class="wrapper wrapper-content">
	<div class="ibox">
	<div class="ibox-title">
		<h5>保存活动内容成功列表 </h5>
		<div class="ibox-tools">
			<a class="collapse-link">
				<i class="fa fa-chevron-up"></i>
			</a>
			<a class="dropdown-toggle" data-toggle="dropdown" href="#">
				<i class="fa fa-wrench"></i>
			</a>
			<ul class="dropdown-menu dropdown-user">
				<li><a href="#">选项1</a>
				</li>
				<li><a href="#">选项2</a>
				</li>
			</ul>
			<a class="close-link">
				<i class="fa fa-times"></i>
			</a>
		</div>
	</div>
    
    <div class="ibox-content">
	<sys:message content="${message}"/>
	
	<!--查询条件-->
	<div class="row">
	<div class="col-sm-12">
	<form:form id="searchForm" modelAttribute="tdActivitycontent" action="${ctx}/td/act/tdActivitycontent/" method="post" class="form-inline">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<table:sortColumn id="orderBy" name="orderBy" value="${page.orderBy}" callback="sortOrRefresh();"/><!-- 支持排序 -->
		<div class="form-group">
			<span>内容标题：</span>
				<form:input path="title"  htmlEscape="false" maxlength="20"  class=" form-control input-sm"/>
			<span>栏目名称：</span>
			<form:select  path="typeid"  htmlEscape="false" maxlength="20"  class=" form-control input-sm">
				<form:option value="">-请选择-</form:option>
				 <c:forEach items="${typelist}" var="type">
				 	<form:option value="${type.id}">${type.name}</form:option>
				 </c:forEach>
			</form:select>
		 </div>	
	</form:form>
	<br/>
	</div>
	</div>
	
	<!-- 工具栏 -->
	<div class="row">
	<div class="col-sm-12">
		<div class="pull-left">
		
				<table:addRow url="${ctx}/td/act/tdActivitycontent/form" title="保存活动内容成功"></table:addRow><!-- 增加按钮 -->
	
			    <table:editRow url="${ctx}/td/act/tdActivitycontent/form" title="保存活动内容成功" id="contentTable"></table:editRow><!-- 编辑按钮 -->
		
				<table:delRow url="${ctx}/td/act/tdActivitycontent/deleteAll" id="contentTable"></table:delRow><!-- 删除按钮 -->
			
	       <button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="sortOrRefresh()" title="刷新"><i class="glyphicon glyphicon-repeat"></i> 刷新</button>
		
			</div>
		<div class="pull-right">
			<button  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()" ><i class="fa fa-search"></i> 查询</button>
			<button  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()" ><i class="fa fa-refresh"></i> 重置</button>
		</div>
	</div>
	</div>
	
	<!-- 表格 -->
	<table id="contentTable" class="table table-striped table-bordered table-hover table-condensed dataTables-example dataTable">
		<thead>
			<tr>
				<th> <input type="checkbox" class="i-checks"></th>
				<th  class="sort-column title">标题</th>
				<th  class="sort-column typename">栏目名称</th>
				<th  class="sort-column pic">封面图片</th>
				<th  class="sort-column content">内容</th>
				<th  class="sort-column number">报名数量</th>
				<th  class="sort-column starttime">开始时间</th>
				<th  class="sort-column endtime">结束时间</th>
				<th  class="sort-column sort">排序</th>
				<th  class="sort-column state">活动状态</th>
				<th  class="sort-column remarks">备注信息</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="tdActivitycontent">
			<tr>
				<td> <input type="checkbox" id="${tdActivitycontent.id}" class="i-checks"></td>
				<td><a  href="#" onclick="openDialogView('查看保存活动内容成功', '${ctx}/td/act/tdActivitycontent/form?id=${tdActivitycontent.id}','800px', '500px')">
					${tdActivitycontent.title}
				</a></td>
				<td>
					${tdActivitycontent.typename}
				</td>
				<td>
					<c:if test="${not empty tdActivitycontent.pic}">
						<a href="${tdActivitycontent.pic}" target="view_window"><img src="${tdActivitycontent.pic}" height="50" width="80"/></a>
					</c:if>
				</td>
				<td>
					 <c:if test="${fn:length(tdActivitycontent.content)>12 }">  
                         ${fn:substring(tdActivitycontent.content, 0, 12)}...  
                   </c:if>  
                  <c:if test="${fn:length(tdActivitycontent.content)<=12 }">  
                         ${tdActivitycontent.content}  
                   </c:if> 
				</td>
				<td>
					${tdActivitycontent.number}
				</td>
				<td>
					<fmt:formatDate value="${tdActivitycontent.starttime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${tdActivitycontent.endtime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${tdActivitycontent.sort}
				</td>
				<td>
					<c:if test="${tdActivitycontent.state eq 1}">
					 	活动未开始
					</c:if>
					<c:if test="${tdActivitycontent.state eq 2}">
					 	活动进行中
					</c:if>
					<c:if test="${tdActivitycontent.state eq 3}">
					 	活动已结束
					</c:if>
				</td>
				<td>
					${tdActivitycontent.remarks}
				</td>
				<td>
					
						<a href="#" onclick="openDialogView('查看保存活动内容成功', '${ctx}/td/act/tdActivitycontent/form?id=${tdActivitycontent.id}','800px', '500px')" class="btn btn-info btn-xs" ><i class="fa fa-search-plus"></i> 查看</a>
					
					
    					<a href="#" onclick="openDialog('修改保存活动内容成功', '${ctx}/td/act/tdActivitycontent/form?id=${tdActivitycontent.id}','800px', '500px')" class="btn btn-success btn-xs" ><i class="fa fa-edit"></i> 修改</a>
    			
    				
						<a href="${ctx}/td/act/tdActivitycontent/delete?id=${tdActivitycontent.id}" onclick="return confirmx('确认要删除该保存活动内容成功吗？', this.href)"   class="btn btn-danger btn-xs"><i class="fa fa-trash"></i> 删除</a>
					
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	
		<!-- 分页代码 -->
	<table:page page="${page}"></table:page>
	<br/>
	<br/>
	</div>
	</div>
</div>
</body>
</html>
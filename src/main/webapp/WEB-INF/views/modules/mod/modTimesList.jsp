<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>模版次数管理管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/mod/modTimes/">模版次数管理列表</a></li>
		<%--<shiro:hasPermission name="mod:modTimes:edit"><li><a href="${ctx}/mod/modTimes/form">模版次数管理添加</a></li></shiro:hasPermission>--%>
	</ul>
	<form:form id="searchForm" modelAttribute="modTimes" action="${ctx}/mod/modTimes/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>公司编号：</label>
				<form:input path="comid" htmlEscape="false" maxlength="10" class="input-medium"/>
			</li>
			<li><label>次数：</label>
				<form:input path="times" htmlEscape="false" maxlength="2" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>公司编号</th>
				<th>次数</th>
				<th>备注信息</th>
				<shiro:hasPermission name="mod:modTimes:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="modTimes">
			<tr>
				<td><a href="${ctx}/mod/modTimes/form?id=${modTimes.id}">
					${modTimes.comid}
				</td>
				<td><a href="${ctx}/mod/modTimes/form?id=${modTimes.id}">
					${modTimes.times}
				</td>
				<td>
					${modTimes.remarks}
				</td>
				<shiro:hasPermission name="mod:modTimes:edit"><td>
    				<a href="${ctx}/mod/modTimes/form?id=${modTimes.id}">修改</a>
					<a href="${ctx}/mod/modTimes/delete?id=${modTimes.id}" onclick="return confirmx('确认要删除该模版次数管理吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>
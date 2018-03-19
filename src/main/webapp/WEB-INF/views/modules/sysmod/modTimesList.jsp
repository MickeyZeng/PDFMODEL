<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>设计模版次数管理</title>
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
		<li class="active"><a href="${ctx}/sysmod/modTimes/">设计模版次数列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="modTimes" action="${ctx}/sysmod/modTimes/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>归属公司：</label>
				<sys:treeselect id="company" name="company.id" value="${modTimes.company.id}" labelName="company.name" labelValue="${modTimes.company.name}"
					title="部门" url="/sys/office/treeData?type=1" cssClass="input-small" allowClear="true" notAllowSelectParent="true"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>编号</th>
				<th>归属公司</th>
				<th>次数</th>
				<th>更新时间</th>
				<shiro:hasPermission name="sysmod:modTimes:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="modTimes">
			<tr>
				<td>
					${modTimes.id}
				</td>
				<td>
					${modTimes.company.name}
				</td>
				<td>
					${modTimes.times}
				</td>
				<td>
					<fmt:formatDate value="${modTimes.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="sysmod:modTimes:edit"><td>
    				<a href="${ctx}/sysmod/modTimes/form?id=${modTimes.id}">修改</a>
					<a href="${ctx}/sysmod/modTimes/delete?id=${modTimes.id}" onclick="return confirmx('确认要删除该设计模版次数吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>
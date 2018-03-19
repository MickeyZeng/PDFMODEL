<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>模版元素管理</title>
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
		<li class="active"><a href="${ctx}/modelement/sysModelement/">模版元素列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="sysModelement" action="${ctx}/modelement/sysModelement/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>元素名字:</label>
				<form:input path="elementname" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>归属公司:</label>
				<sys:treeselect id="company" name="company.id" value="${sysModelement.company.id}" labelName="company.name" labelValue="${sysModelement.company.name}"
					title="部门" url="/sys/office/treeData?type=1" cssClass="input-small" allowClear="true" notAllowSelectParent="true"/>
			</li>
			<li><label>归属部门:</label>
				<sys:treeselect id="office" name="office.id" value="${sysModelement.office.id}" labelName="office.name" labelValue="${sysModelement.office.name}"
					title="部门" url="/sys/office/treeData?type=2" cssClass="input-small" allowClear="true" notAllowSelectParent="true"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>元素名字</th>
				<th>元素PDF里的名字</th>
				<th>归属公司</th>
				<th>归属部门</th>
				<th>创建者</th>
				<shiro:hasPermission name="modelement:sysModelement:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="sysModelement">
			<tr>
				<td><a href="${ctx}/modelement/sysModelement/form?id=${sysModelement.id}">
					${sysModelement.elementname}
				</a></td>
				<td>
					${sysModelement.pdfelementname}
				</td>
				<td>
					${sysModelement.company.name}
				</td>
				<td>
					${sysModelement.office.name}
				</td>
				<td>
					${sysModelement.createBy.id}
				</td>
				<shiro:hasPermission name="modelement:sysModelement:edit"><td>
    				<a href="${ctx}/modelement/sysModelement/form?id=${sysModelement.id}">修改</a>
					<a href="${ctx}/modelement/sysModelement/delete?id=${sysModelement.id}" onclick="return confirmx('确认要删除该模版元素吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>
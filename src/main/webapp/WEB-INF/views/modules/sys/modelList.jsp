<%--
  Created by IntelliJ IDEA.
  User: mickey
  Date: 2018/3/23
  Time: 14:22
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>模版查看</title>
    <meta name="decorator" content="default"/>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="robots" content="noindex, nofollow" />
    <link href="${ctxStatic}/ckfinder/_samples/sample.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="${ctxStatic}/ckfinder/ckfinder.js"></script>
    <script type="text/javascript">
        function page(n,s){
            if(n) $("#pageNo").val(n);
            if(s) $("#pageSize").val(s);
            $("#searchForm").attr("action","${ctx}/sys/model/list");
            $("#searchForm").submit();
            return false;
        }

        function BrowseServer()
        {
            // You can use the "CKFinder" class to render CKFinder in a page:
            var finder = new CKFinder();
            finder.basePath = '../';	// The path for the installation of CKFinder (default = "/ckfinder/").
            finder.selectActionFunction = SetFileField;
            finder.resourceType="PDF";
            finder.popup();

            // It can also be done in a single line, calling the "static"
            // popup( basePath, width, height, selectFunction ) function:
            // CKFinder.popup( '../', null, null, SetFileField ) ;
            //
            // The "popup" function can also accept an object as the only argument.
            // CKFinder.popup( { basePath : '../', selectActionFunction : SetFileField } ) ;
        }

        // This is a sample function which is called when a file is selected in CKFinder.
        function SetFileField( fileUrl )
        {
            document.getElementById( 'xFilePath' ).value = fileUrl;
        }
    </script>
</head>
<body>
    <ul class="nav nav-tabs">
        <li class="active"><a href="${ctx}/sys/model/list">模版查看</a></li>
    </ul>
    <form:form id="searchForm" modelAttribute="user" action="${ctx}/sys/model/list" method="post" class="breadcrumb form-search ">
        <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
        <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
        <sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}" callback="page();"/>
        <ul class="ul-form">
            <li><label>归属公司：</label><sys:treeselect id="company" name="company.id" value="${user.company.id}" labelName="company.name" labelValue="${user.company.name}"
                                                    title="公司" url="/sys/office/treeData?type=1" cssClass="input-small" allowClear="true"/></li>
            <li><label>登录名：</label><form:input path="loginName" htmlEscape="false" maxlength="50" class="input-medium"/></li>
            <li class="clearfix"></li>
            <li><label>归属部门：</label><sys:treeselect id="office" name="office.id" value="${user.office.id}" labelName="office.name" labelValue="${user.office.name}"
                                                    title="部门" url="/sys/office/treeData?type=2" cssClass="input-small" allowClear="true" notAllowSelectParent="true"/></li>
            <li><label>姓&nbsp;&nbsp;&nbsp;名：</label><form:input path="name" htmlEscape="false" maxlength="50" class="input-medium"/></li>
            <li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" onclick="return page();"/></li>
            <li class="clearfix"></li>
        </ul>
    </form:form>
    <sys:message content="${message}"/>
    <table id="contentTable" class="table table-striped table-bordered table-condensed">
        <thead><tr><th>归属公司</th><th>归属部门</th><th class="sort-column login_name">登录名</th><th class="sort-column name">姓名</th><th>操作</th></tr></thead>
        <tbody>
        <c:forEach items="${page.list}" var="user">
            <tr>
                <td><a onclick="BrowseServer()">${user.company.name}</td>
                <td>${user.office.name}</td>
                <td>${user.loginName}</td>
                <td>${user.name}</td>
                <td>
                    <a onclick="BrowseServer()" type="button" value="Browse Server" >查 看</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <div class="pagination">${page}</div>
</body>
</html>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <title>用户管理</title>
    <meta name="decorator" content="default"/>
</head>
<body>
<ul class="nav nav-tabs">
    <li><a href="${ctx}/sys/user/checklist">待审核用户列表</a></li>
    <li class="active"><a href="${ctx}/sys/user/checkForm?id=${user.id}">用户审核</a></li>
</ul><br/>
<form:form id="inputForm" modelAttribute="user"   method="post" class="form-horizontal">
    <form:hidden path="id"/>
    <sys:message content="${message}"/>
    <div class="control-group">
        <label class="control-label">头像:</label>
        <div class="controls">
            <form:hidden id="nameImage" path="photo" htmlEscape="false" maxlength="255" class="input-xlarge"/>
            <sys:ckfinder input="nameImage" type="images" uploadPath="/photo" selectMultiple="false" maxWidth="100" maxHeight="100"/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">归属公司:</label>
        <div class="controls">
            <input id="company" name="company.id" value="${user.company.name}" readonly />
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">归属部门:</label>
        <div class="controls">
            <input id="office" name="office.id" value="${user.office.name}" readonly />
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">工号:</label>
        <div class="controls">
            <form:input path="no" htmlEscape="false" maxlength="50" class="required" readonly="true" />
            <span class="help-inline"><font color="red">*</font> </span>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">姓名:</label>
        <div class="controls">
            <form:input path="name" htmlEscape="false" maxlength="50" class="required" readonly="true" />
            <span class="help-inline"><font color="red">*</font> </span>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">登录名:</label>
        <div class="controls">
            <input id="oldLoginName" name="oldLoginName" type="hidden" value="${user.loginName}">
            <form:input path="loginName" htmlEscape="false" maxlength="50" class="required userName" readonly="true"/>
            <span class="help-inline"><font color="red">*</font> </span>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">邮箱:</label>
        <div class="controls">
            <form:input path="email" htmlEscape="false" maxlength="100" class="email" readonly="true" />
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">电话:</label>
        <div class="controls">
            <form:input path="phone" htmlEscape="false" maxlength="100" readonly="true" />
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">手机:</label>
        <div class="controls">
            <form:input path="mobile" htmlEscape="false" maxlength="100" readonly="true" />
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">用户类型:</label>
        <div class="controls">
            <form:select path="userType" class="input-xlarge" disabled="true">
                <form:option value="" label="请选择"/>
                <form:options items="${fns:getDictList('sys_user_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
            </form:select>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">用户角色:</label>
        <div class="controls">
            <form:checkboxes path="roleIdList" items="${allRoles}" itemLabel="name" itemValue="id" htmlEscape="false" class="required" disabled="true"/>
            <span class="help-inline"><font color="red">*</font> </span>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">备注:</label>
        <div class="controls">
            <form:textarea path="remarks" htmlEscape="false" rows="3" maxlength="200" class="input-xlarge" readonly="true" />
        </div>
    </div>
    <c:if test="${not empty user.id}">
        <div class="control-group">
            <label class="control-label">创建时间:</label>
            <div class="controls">
                <label class="lbl"><fmt:formatDate value="${user.createDate}" type="both" dateStyle="full"/></label>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">最后登陆:</label>
            <div class="controls">
                <label class="lbl">IP: ${user.loginIp}&nbsp;&nbsp;&nbsp;&nbsp;时间：<fmt:formatDate value="${user.loginDate}" type="both" dateStyle="full"/></label>
            </div>
        </div>
    </c:if>
    <div class="form-actions">
        <shiro:hasPermission name="sys:user:edit">
            <a href="${ctx}/sys/user/updateCheck?id=${user.id}"><input id="btnSubmit" class="btn btn-primary"  value="通 过"/></a>&nbsp;
            <a href="${ctx}/sys/user/delete?id=${user.id}" onclick="return confirmx('确认不允许该用户通过吗？', this.href)"><input id="btnDelete" class="btn btn-primary"  value="不 通 过"/></a>
        </shiro:hasPermission>
        <input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
    </div>
</form:form>
</body>
</html>

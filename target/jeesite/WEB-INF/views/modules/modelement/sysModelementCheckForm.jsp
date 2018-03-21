<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <title>模版元素管理</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript">
        $(document).ready(function() {
            //$("#name").focus();
            $("#inputForm").validate({
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
    </script>
</head>
<body>
<ul class="nav nav-tabs">
    <li><a href="${ctx}/modelement/sysModelement/checkList">待审核用户列表</a></li>
    <li class="active"><a href="${ctx}/modelement/sysModelement/checkForm?id=${sysModelement.id}">模版元素审核</a></li>
</ul><br/>
<form:form id="inputForm" modelAttribute="sysModelement" method="post" class="form-horizontal">
    <form:hidden path="id"/>
    <sys:message content="${message}"/>
    <div class="control-group">
        <label class="control-label">元素名字:</label>
        <div class="controls">
            <form:input path="elementname" htmlEscape="false" maxlength="100" class="input-xlarge required" readonly="true"/>
            <span class="help-inline"><font color="red">*</font> </span>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">PDF元素名:</label>
        <div class="controls">
            <form:input path="pdfelementname" htmlEscape="false" maxlength="100" class="input-xlarge required" readonly="true"/>
            <span class="help-inline"><font color="red">*</font> </span>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">归属公司:</label>
        <div class="controls">
            <input id="company" name="company.id" value="${sysModelement.company.name}" readonly />
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">归属部门:</label>
        <div class="controls">
            <input id="office" name="office.id" value="${sysModelement.office.name}" readonly />
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">备注信息:</label>
        <div class="controls">
            <form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge " readonly="true"/>
        </div>
    </div>
    <div class="form-actions">
        <shiro:hasPermission name="modelement:sysModelement:edit">
            <a href="${ctx}/modelement/sysModelement/updateCheck?id=${sysModelement.id}"><input id="btnSubmit" class="btn btn-primary" type="submit" value="通过"/></a>&nbsp;
            <a href="${ctx}/modelement/sysModelement/delete?id=${sysModelement.id}" onclick="return confirmx('确认要删除该模版元素吗？', this.href)"><input id="btnDelete" class="btn btn-primary" value="不通过"></a>
        </shiro:hasPermission>
        <input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
    </div>
</form:form>
</body>
</html>
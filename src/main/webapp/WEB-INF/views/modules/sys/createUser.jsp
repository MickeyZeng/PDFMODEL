<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <title>用户管理</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript">
        $(document).ready(function() {
            getOfficeJson();
            $("#no").focus();
            $("#inputForm").validate({
                rules: {
                    loginName: {remote: "${font}/sys/user/checkLoginName?oldLoginName=" + encodeURIComponent('${user.loginName}')}
                },
                messages: {
                    loginName: {remote: "用户登录名已存在"},
                    confirmNewPassword: {equalTo: "输入与上面相同的密码"}
                },
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

        function getOfficeJson(){
            $.ajax({
                type:'post',
                url:'${font}/sys/user/officeChoice',
                data:{
                },
                dataType: "JSON",
                success:function(responseData){
                    $.each(responseData, function (i) {
                        $("<option></option>")
                            .val(responseData[i].id)
                            .text(responseData[i].name)
                            .appendTo($("#comChoice"));             //现在的状态是可以显示下拉列表内容，但是不能选中
                    });
                },
                error:function(e){
                    alert(e);
                }
            });
        }

        $(function(){
           $("#comChoice").change(function(){
               var option = $("#comChoice option:selected");
               var parentId = option.val();
               if(parentId != null && parentId != ""){
                   $.ajax({
                       type:'post',
                       url:'${font}/sys/user/groupChoice?comValue='+parentId,
                       data:{
                       },
                       dataType: "JSON",
                       success:function(responseData1){
                           $.each(responseData1, function (i) {
                               $("<option></option>")
                                   .val(responseData1[i].id)
                                   .text(responseData1[i].name)
                                   .appendTo($("#groupChoice"));             //现在的状态是可以显示下拉列表内容，但是不能选中
                           });
                       },
                       error:function(e){
                           alert(e);
                       }
                   });
               }
           })
        });

    </script>
</head>
<body>
<ul class="nav nav-tabs">
    <li><a href="${font}/sys/user/form">用户列表</a></li>
</ul><br/>
<form:form id="inputForm" modelAttribute="user" action="${font}/sys/user/save" method="post" class="form-horizontal">
    <form:hidden path="id"/>
    <sys:message content="${message}"/>
    <div class="control-group">
        <label class="control-label">归属公司:</label>
        <div class="controls">
            <select id="comChoice" class="comChoice" style="width:200px;" name="company.id">
                <option value="-1" selected="selected" >==请选择公司==</option>
            </select>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">归属部门:</label>
        <div class="controls">
            <select id="groupChoice" class="groupChoice" style="width:200px;" name="office.id"></select>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">工号:</label>
        <div class="controls">
            <form:input path="no" htmlEscape="false" maxlength="50" class="required"/>
            <span class="help-inline"><font color="red">*</font> </span>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">姓名:</label>
        <div class="controls">
            <form:input path="name" htmlEscape="false" maxlength="50" class="required"/>
            <span class="help-inline"><font color="red">*</font> </span>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">登录名:</label>
        <div class="controls">
            <input id="oldLoginName" name="oldLoginName" type="hidden" value="${user.loginName}">
            <form:input path="loginName" htmlEscape="false" maxlength="50" class="required userName"/>
            <span class="help-inline"><font color="red">*</font> </span>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">密码:</label>
        <div class="controls">
            <input id="newPassword" name="newPassword" type="password" value="" maxlength="50" minlength="3" class="${empty user.id?'required':''}"/>
            <c:if test="${empty user.id}"><span class="help-inline"><font color="red">*</font> </span></c:if>
            <c:if test="${not empty user.id}"><span class="help-inline">若不修改密码，请留空。</span></c:if>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">确认密码:</label>
        <div class="controls">
            <input id="confirmNewPassword" name="confirmNewPassword" type="password" value="" maxlength="50" minlength="3" equalTo="#newPassword"/>
            <c:if test="${empty user.id}"><span class="help-inline"><font color="red">*</font> </span></c:if>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">邮箱:</label>
        <div class="controls">
            <form:input path="email" htmlEscape="false" maxlength="100" class="email required"/>
            <span class="help-inline"><font color="red">*</font> </span>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">电话:</label>
        <div class="controls">
            <form:input path="phone" htmlEscape="false" maxlength="100"/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">手机:</label>
        <div class="controls">
            <form:input path="mobile" htmlEscape="false" maxlength="100"/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">备注:</label>
        <div class="controls">
            <form:textarea path="remarks" htmlEscape="false" rows="3" maxlength="200" class="input-xlarge"/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">用户类型:</label>
        <div class="controls">
            <form:select path="userType" class="input-xlarge">
                <form:option value="" label="请选择"/>
                <form:options items="${fns:getDictList('sys_user_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
            </form:select>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">用户角色:</label>
        <div class="controls">
            <form:checkboxes path="roleIdList" items="${allRoles}" itemLabel="name" itemValue="id" htmlEscape="false" class="required"/>
            <span class="help-inline"><font color="red">*</font> </span>
        </div>
    </div>

    <div>
        <div class="controls">
        </div>
        <span class="help-inline"><font color="red">*</font>该账号还没经过审核，所以无法登录</span>
        <input value="0" type="hidden" name="loginFlag">
    </div>
    <div class="form-actions">
        <input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
        <input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
    </div>
</form:form>
</body>
</html>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <title>设计模版次数管理</title>
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

        $(function(){
            var t = $("#times");
            $("#add").click(function(){
                t.val(parseInt(t.val())+1)
            })
            $("#min").click(function(){
                if(t.val() > 1 || t.val() == 1) {
                    t.val(parseInt(t.val()) - 1);
                }
            })
        })

    </script>
</head>
<body>
<ul class="nav nav-tabs">
    <li class="active"><a href="${ctx}/sysmod/modTimes/form?id=${modTimes.id}">申请设计模版次数</a></li>
</ul><br/>
<form:form id="inputForm" modelAttribute="modTimes" action="${ctx}/sysmod/modTimes/updateTimes" method="post" class="form-horizontal">
    <form:hidden path="id"/>
    <sys:message content="${message}"/>
    <div class="control-group">
        <label class="control-label">归属公司：</label>
        <div class="controls">
            <sys:treeselect id="company" name="company.id" value="${modTimes.company.id}" labelName="company.name" labelValue="${modTimes.company.name}"
                            title="部门" url="/sys/office/treeData?type=1" cssClass="required" allowClear="true" notAllowSelectParent="true"/>
            <span class="help-inline"><font color="red">*</font> </span>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">公司负责人：</label>
        <div class="controls">
            <sys:treeselect id="user" name="user.id" value="${modTimes.user.id}" labelName="user.name" labelValue="${modTimes.user.name}"
                            title="用户" url="/sys/office/treeData?type=3" cssClass="required" allowClear="true" notAllowSelectParent="true"/>
            <span class="help-inline"><font color="red">*</font> </span>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">次数：</label>
        <div class="controls">
            <input type="button" id="min" value="-" />
            <form:input path="times" htmlEscape="false" maxlength="2" class="input-xlarge required" id="times" cssStyle="width: 20px" value="0" readonly="true" />
            <input type="button" id="add" value="+" />
            <span class="help-inline"><font color="red">*</font> </span>
        </div>
    </div>
    <div class="form-actions">
        <shiro:hasPermission name="sysmod:modTimes:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
        <input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
    </div>
</form:form>
</body>
</html>
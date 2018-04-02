<%--
  Created by IntelliJ IDEA.
  User: mickey
  Date: 2018/3/30
  Time: 14:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>Title</title>
<meta name="decorator" content="default"/>
<script type="text/javascript" src="${ctxStatic}/ckfinder/ckfinder.js"></script>
<script type="text/javascript" src="${ctxStatic}/ckfinder/jquery-v1-ui.js"></script>
<script type="text/javascript" src="${ctxStatic}/ckfinder/jquery-1.7.2.js"></script>
<script type="text/javascript" src="${ctxStatic}/ckfinder/jquery.media.js"></script>
</head>
<body>
<script type="text/javascript">
    $(document).ready(function() {
        var arryList = "${IDs}".split(",");
        var div1 = document.getElementById('inputForm');
        var code = '<form id="dataForm" action=" ${ctx}/PDFData/Data/receive?path=${path}" method="post" class="form-search" >';
        for(var i = 0 ; i < arryList.length ; i++){
            //alert(arryList[i]);
            code += '<div class="control-group"><label class="control-label">'+  arryList[i]
                +'</label><div class="controls"><input id="'+ arryList[i]
                +'" name="'+ arryList[i] +'" htmlEscape="false" maxlength="100"/></div></div>';
        }
        code += '<div class="form-actions"><input id="btnSubmit" class="btn btn-primary" type="submit" value="导 入"/>&nbsp;' +
        '<input class="btn" type="button" value="返 回" onclick="history.go(-1)"/></form></div>';
        div1.innerHTML = code ;

        $('a.media').media({width:800, height:600});

    });

</script>
<h1>导入表格</h1>
<div id="pdfview">
    <a class="media" href="${path}"></a>
</div>
<div id="inputForm"></div>
</body>
</html>

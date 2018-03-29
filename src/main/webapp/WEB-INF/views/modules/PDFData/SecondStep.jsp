<%--
  Created by IntelliJ IDEA.
  User: mickey
  Date: 2018/3/28
  Time: 08:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
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
    <script type="text/javascript">
//        $(function(){
//            $('a.media').media({width:800, height:600});
//        });
        $(document).ready(function() {

            $('a.media').media({width:800, height:600});

        });
    </script>
</head>

<body>
<h1>第二步：上传数据</h1>
    <div id="importBox">
        <form id="importForm" action="${ctx}/PDFData/Data/upload?path=${path}" method="post" enctype="multipart/form-data" class="form-search" >
            <h3>上传Excel数据</h3>
            <input id="uploadFile" name="file" type="file" style="width:200px"/>　
            <input id="btnImportSubmit" class="btn btn-primary" type="submit" value=" 导  入 "/>
        </form>
    </div>
    <h3>选中的模版</h3>
    <div id="pdfview">
        <a class="media" href="${path}"></a>
    </div>
</body>
</html>

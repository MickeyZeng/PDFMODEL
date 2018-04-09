<%--
  Created by IntelliJ IDEA.
  User: mickey
  Date: 2018/4/9
  Time: 15:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<meta name="decorator" content="default"/>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="robots" content="noindex, nofollow" />
<head>
    <title>Title</title>
</head>
<body>
<h1>联系我们 Contact Us</h1>
    <table class="table table-striped table-bordered" style="width: 1036px;margin: 10px auto">
        <thead>
        <tr>
            <th><img src="${ctxStatic}/images/qq.png" alt=""/>QQ在线联系</th>
            <th><img src="${ctxStatic}/images/wechat.png" alt=""/>&nbsp;微信联系</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td><center><a target="_blank" href="http://wpa.qq.com/msgrd?v=3&uin=${fns:getConfig("connect.qq")}&site=qq&menu=yes"><img border="0" src="http://wpa.qq.com/pa?p=2:${fns:getConfig("connect.qq")}:53" alt="点击这里给我发消息" title="点击这里给我发消息"/></a></center></td>
            <td><center><img src="${ctxStatic}/images/MyWechat.jpg" alt="" height="200" width="150" /></center></td>
        </tr>
        </tbody>
    </table>
</body>
</html>

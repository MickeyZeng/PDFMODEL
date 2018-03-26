<%--
  Created by IntelliJ IDEA.
  User: mickey
  Date: 2018/3/22
  Time: 14:58
  To change this template use File | Settings | File Templates.

--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
        <title>CKFinder - Sample - Standalone</title>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <meta name="robots" content="noindex, nofollow" />
        <link href="${ctxStatic}/ckfinder/_samples/sample.css" rel="stylesheet" type="text/css" />
        <script type="text/javascript" src="${ctxStatic}/ckfinder/ckfinder.js"></script>
</head>
<body>
<p style="padding-left: 30px; padding-right: 30px;">
    <script type="text/javascript">

        // This is a sample function which is called when a file is selected in CKFinder.
        function showFileInfo( fileUrl, data, allFiles )
        {
            var msg = 'The last selected file is: <a href="' + fileUrl + '">' + fileUrl + '</a><br /><br />';
            // Display additional information available in the "data" object.
            // For example, the size of a file (in KB) is available in the data["fileSize"] variable.
            if ( fileUrl != data['fileUrl'] )
                msg += '<b>File url:</b> ' + data['fileUrl'] + '<br />';
            msg += '<b>File size:</b> ' + data['fileSize'] + 'KB<br />';
            msg += '<b>Last modified:</b> ' + data['fileDate'];

            if ( allFiles.length > 1 )
            {
                msg += '<br /><br /><b>Selected files:</b><br /><br />';
                msg += '<ul style="padding-left:20px">';
                for ( var i = 0 ; i < allFiles.length ; i++ )
                {
                    // See also allFiles[i].url
                    msg += '<li>' + allFiles[i].data['fileUrl'] + ' (' + allFiles[i].data['fileSize'] + 'KB)</li>';
                }
                msg += '</ul>';
            }
            // this = CKFinderAPI object
            this.openMsgDialog( "Selected file", msg );
        }



        // You can use the "CKFinder" class to render CKFinder in a page:
        var finder = new CKFinder();
        // The path for the installation of CKFinder (default = "/ckfinder/").
        finder.basePath = '../';
        // The default height is 400.
        finder.height = 700;
        finder.width = 950;
        // This is a sample function which is called when a file is selected in CKFinder.
        finder.selectActionFunction = showFileInfo;
        //get the parameter and decide the file path
//        finder.startupPath = "PDF:/中山大学珠海分校/计算机学院/";
//        finder.callback = function(api){
//            api.open('Images','/');
//        }
        //finder.resourceType = "PDF:"+ ${comName} +"/"+ ${officeName};
        finder.resourceType = "Group";
        finder.create();


        // It can also be done in a single line, calling the "static"
        // create( basePath, width, height, selectActionFunction ) function:
        // CKFinder.create( '../', null, null, showFileInfo );

        // The "create" function can also accept an object as the only argument.
        // CKFinder.create( { basePath : '../', selectActionFunction : showFileInfo } );

    </script>
</p>
</body>
</html>

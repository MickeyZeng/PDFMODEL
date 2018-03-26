<%--
  Created by IntelliJ IDEA.
  User: mickey
  Date: 2018/3/23
  Time: 14:22
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
    <script type="text/javascript" src="${ctxStatic}/ckfinder/jquery-1.7.2.js"></script>
    <script type="text/javascript" src="${ctxStatic}/ckfinder/jquery-v1-ui.js"></script>
    <script type="text/javascript" src="${ctxStatic}/ckfinder/jquery.media.js"></script>

</head>
<body>
<div id="pdf_viewer"></div>
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

        function closeWindow() {
            document.getElementById('pdf_viewer').innerHTML = "";
        }

        function showFile(fileUrl, data) {
            var sFileName = this.getSelectedFile().name;
            //alert(fileUrl);
            var url = "/file" + fileUrl.substring(38);
            document.getElementById('pdf_viewer').innerHTML =
                '<span class="close cursor" onclick="closeWindow()">' + '&times;' +
                '</span>' +
                '<a class="media" href="' + url + '">' +
                '</a>' + '<div class="caption">' +
                '<a href="' + data["fileUrl"] + '" target="_blank">' + sFileName + '</a> (' + data["fileSize"] + 'KB)' +
                '</div>';

            $('a.media').media({width: 800, height: 600});
            return true;
        }

        // You can use the "CKFinder" class to render CKFinder in a page:
        var finder = new CKFinder();
        // The path for the installation of CKFinder (default = "/ckfinder/").
        finder.basePath = '../';
        // The default height is 400.
        finder.height = 700;
        finder.width = 950;
        finder.readOnly = true;
        // This is a sample function which is called when a file is selected in CKFinder.
        finder.selectActionFunction = showFile;
        //get the parameter and decide the file path
        //        finder.startupPath = "PDF:/中山大学珠海分校/计算机学院/";
        //        finder.callback = function(api){
        //            api.open('Images','/');
        //        }
        //finder.resourceType = "PDF:"+ ${comName} +"/"+ ${officeName};

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
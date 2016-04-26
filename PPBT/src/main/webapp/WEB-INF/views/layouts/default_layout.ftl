<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>CSE530 Final Project</title>
    <meta charset="utf-8"/>
    <meta name=”viewport” content=”width=device-width, initial-scale=1″>
    <meta name="keywords" content=""/>
    <meta name="description" content=""/>
    <meta name="author" content=""/>
    <meta name="format-detection" content="telephone=no,address=no,email=no"/>
    <meta name='mobileOptimized' content='width'/>
    <meta name='handheldFriendly' content='true'/>
    <meta name='apple-mobile-web-app-capable' content='yes'/>
    <meta name='apple-mobile-web-app-status-bar-style' content='black'/>
    <#--<@yield to="css_define_default"/>-->
    <#--<link rel="stylesheet" type="text/css" href="/Public/dist/styles/layout_lib.css?t=${static_resource_version!}"/>-->
    <#--<@yield to="css_define"/>-->
    <#--<@yield to="js_define_head"/>-->
</head>

<body>
    ${page_content}
    <div id="sink-bg-blank" class="container">&nbsp;</div>
    <div id="sink-footer"></div>
    <div class="ld-footer-wrapper" id="ld-footer-wrapper">
    <#include "footer.ftl" >
    </div>
    <@yield to="js_define"/>
</body>
</html>

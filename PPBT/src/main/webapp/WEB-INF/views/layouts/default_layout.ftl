<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>CSE503 Final Project</title>
    <meta name="keywords" content=""/>
    <meta name="description" content=""/>
    <meta name="author" content=""/>
    <meta name="format-detection" content="telephone=no,address=no,email=no"/>
    <meta name='mobileOptimized' content='width'/>
    <meta name='handheldFriendly' content='true'/>
    <meta name='apple-mobile-web-app-capable' content='yes'/>
    <meta name='apple-mobile-web-app-status-bar-style' content='black'/>

    <link rel="stylesheet" type="text/css" href="../../../bootstrap/css/bootstrap-theme.min.css">
    <link rel="stylesheet" type="text/css" href="../../../bootstrap/css/bootstrap.min.css">

    <script type="text/javascript" src="../../../jquery/jquery-1.12.0.min.js"></script>
    <script type="text/javascript" src="../../../bootstrap/js/bootstrap.min.js"></script>

</head>

<body>
<div style="margin-right: 30px;float: right;">
    <form class="form-horizontal" method="get" action="login/logout">
        <div class="col-sm-3">
            <button type="submit" class="btn btn-default">Log out</button>
        </div>
    </form>
</div>
${page_content}
<div id="sink-bg-blank" class="container">&nbsp;</div>

<div id="sink-footer"></div>
<div class="ld-footer-wrapper" id="ld-footer-wrapper">
<#include "footer.ftl" >
</div>
<@yield to="js_define"/>
</body>
</html>

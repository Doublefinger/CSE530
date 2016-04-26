<#--<@content for="js_define">-->
<#--<script type="text/javascript" src="/Public/bower/requirejs/require.js?t=${static_resource_version!}" data-main="/app_pages/main.js"></script>-->
<#--</@content>-->
<#--<@content for="css_define">-->
<#--<link rel="stylesheet" type="text/css" href="/Public/dist/styles/director/index.css?${static_resource_version!}"/>-->
<#--</@content>-->

<#--<div class="crm-c" ng-controller="mainCtrl" ng-init="role='handler';tabActive='all';">-->
<#--<div class="crm-nav-ontop">-->
<#--<div class="system-logo">积木CRM系统 - 申请人页面</div>-->
<#--<div class="infos pull-right">-->
<#--<span>${session.loginInfo.userName}</span>-->
<#--<span ng-if="${session.loginInfo.rights?size} == 2"><a href="/director#/">切换角色</a></span>-->
<#--<span><a href="/Login/loginOut">退出</a></span>-->
<#--</div>-->
<#--</div>-->

<#--<div class="views" id="views">-->
<#--<div ng-view></div>-->
<#--</div>-->
<#--</div>-->
<link rel="stylesheet" type="text/css" href="../../../bootstrap/css/bootstrap-theme.min.css">
<link rel="stylesheet" type="text/css" href="../../../bootstrap/css/bootstrap.min.css">

<script type="text/javascript" src="../../../jquery/jquery-1.12.0.min.js"></script>
<script type="text/javascript" src="../../../bootstrap/js/bootstrap.min.js"></script>

<@content for="title">CSE530 Final Project</@content>

<h2>Demo</h2>

<script>
    $(document).ready(function () {
        //getCount();
        getPlace();
    });

    function getCount() {
        $.ajax({
            url: "/tweet/getCount",
            method: "GET",
            data: {candidates: "1234"},
            success: function (result) {
                console.log(result);
            }
        });
    }

    function getPlace() {
        $.ajax({
            url: "/tweet/getPlace",
            method: "GET",
            data: {candidates: "124"},
            success: function(result){
                console.log(result);
            }
        })
    }
</script>

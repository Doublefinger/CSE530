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
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<script type="text/javascript" src="../../../jquery/jquery-1.12.0.min.js"></script>
<script type="text/javascript" src="../../../bootstrap/js/bootstrap.min.js"></script>

<@content for="title">CSE530 Final Project</@content>

<h2 style="margin-left: 30px">Presentation Demo</h2>
<table id="pie-chart" class="table">
    <tr>
        <td><div id="pie_div_1"></div></td>
        <td><div id="pie_div_2"></div></td>
    </tr>
</table>

<table id="bar-chart" class="table">
    <tr>
        <td><div id="bar_div_1"></div></td>
        <td><div id="bar_div_2"></div></td>
    </tr>
</table>

<script type="text/javascript">
    google.charts.load('current', {'packages': ['corechart']});
    // Set a callback to run when the Google Visualization API is loaded.
//    google.charts.setOnLoadCallback(drawPie);
    $(document).ready(function () {
        //getCount();
        getCount();
        getCountByRetweetCount();
        getPlace();
    });

    function getCount() {
        $.ajax({
            url: "/tweet/getCount",
            method: "GET",
            data: {candidates: "12"},
            success: function (result) {
                var data = JSON.parse(result);
                drawBar(data, 1);
            }
        });
    }

    function getCountByRetweetCount(){
        $.ajax({
            url: "/tweet/getCountByRetweetCount",
            method: "GET",
            data: {candidates: "12"},
            success: function (result) {
                var data = JSON.parse(result);
                drawBar(data, 2);
            }
        });
    }

    function getPlace() {
        $.ajax({
            url: "/tweet/getPlace",
            method: "GET",
            data: {candidates: "12"},
            success: function (result) {
                var data = JSON.parse(result);
                drawPie(data.trump, 1, "Trump");
                drawPie(data.cruz, 2, "Cruz");
            }
        })
    }

    function drawPie(placeData, index, name) {
        var data = new google.visualization.DataTable();
        data.addColumn('string', 'State');
        data.addColumn('number', 'Number of Tweets');
        var rows = [];
        for (var key in placeData)
        {
            var element = [];
            element.push(key);
            element.push(placeData[key]);
            rows.push(element);
        }
        data.addRows(rows);

        // Set chart options
        var options = {
            'title': 'How Many Tweets of ' + name + ' in Each State?',
            'width': 600,
            'height': 400
        };

        // Instantiate and draw our chart, passing in some options.
        var chart = new google.visualization.PieChart(document.getElementById('pie_div_'+index));
        chart.draw(data, options);
    }

    function drawBar(placeData, index) {
        var data = new google.visualization.DataTable();
        data.addColumn('string', 'Candidate');
        data.addColumn('number', 'Number of Tweets');
        var rows = [];
        for (var key in placeData)
        {
            var element = [];
            element.push(key);
            element.push(placeData[key]);
            rows.push(element);
        }
        data.addRows(rows);

        // Set chart options
        var title;
        if(index == 1){
            title = 'Total Tweets of Each Candidate on Apr, 24, 2016';
        } else if(index == 2) {
            title = "Number of Tweets which has been retweeted more than 100 on Apr, 24, 2016";
        }
        var options = {
            'title': title,
            'width': 600,
            'height': 400
        };

        // Instantiate and draw our chart, passing in some options.
        var chart = new google.visualization.BarChart(document.getElementById('bar_div_' + index));
        chart.draw(data, options);
    }
</script>

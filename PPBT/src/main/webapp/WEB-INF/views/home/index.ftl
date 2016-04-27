<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>

<@content for="title">CSE503 Final Project</@content>

<h2 style="margin-left: 30px">President Election Statistic of Twitter</h2>
<table id="pie-chart" class="table">
    <tr>
        <td>
            <div id="pie_div_1"></div>
        </td>
        <td>
            <div id="pie_div_2"></div>
        </td>
    </tr>
    <tr>
        <td style="text-align: center">
            <div id="png_1"></div>
        </td>
        <td style="text-align: center">
            <div id="png_2"></div>
        </td>
    </tr>
</table>

<table id="bar-chart" class="table">
    <tr>
        <td>
            <div id="bar_div_1"></div>
        </td>
        <td>
            <div id="bar_div_2"></div>
        </td>
    </tr>
    <tr>
        <td></td>
        <td style="text-align: center">
            Number of RetweetCount<input id="retweetCount" type="number"><button id="retweetCountSubmit">submit</button>
        </td>
    </tr>
</table>

<script type="text/javascript">
    google.charts.load('current', {'packages': ['corechart']});

    $(document).ready(function () {
        //getCount();
        getCount();
        getCountByRetweetCount(100);
        getPlace();
    });

    $("#retweetCountSubmit").click(function () {
        var count = $("#retweetCount").val();
        if(count <= 0 || count > 1000000) {
            return;
        }
        getCountByRetweetCount(count);
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

    function getCountByRetweetCount(count) {
        $.ajax({
            url: "/tweet/getCountByRetweetCount",
            method: "GET",
            data: {candidates: "12", count: count},
            success: function (result) {
                var data = JSON.parse(result);
                drawBar(data, 2, count);
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
        });
    }

    function drawPie(placeData, index, name) {
        var data = new google.visualization.DataTable();
        data.addColumn('string', 'State');
        data.addColumn('number', 'Number of Tweets');
        var rows = [];
        for (var key in placeData) {
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
        var chart = new google.visualization.PieChart(document.getElementById('pie_div_' + index));
        chart.draw(data, options);
        document.getElementById('png_' + index).outerHTML = '<a href="' + chart.getImageURI() + '">Printable version</a>';
    }

    function drawBar(placeData, index, count) {
        var data = new google.visualization.DataTable();
        data.addColumn('string', 'Candidate');
        data.addColumn('number', 'Number of Tweets');
        var rows = [];
        for (var key in placeData) {
            var element = [];
            element.push(key);
            element.push(placeData[key]);
            rows.push(element);
        }
        data.addRows(rows);

        // Set chart options
        var title;
        if (index == 1) {
            title = 'Total Tweets of Each Candidate on Apr, 26, 2016';
        } else if (index == 2) {
            title = "Number of Tweets which has been retweeted more than " + count + " on Apr, 26, 2016";
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

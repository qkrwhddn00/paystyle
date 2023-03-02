<!--
	THIS EXAMPLE WAS DOWNLOADED FROM https://echarts.apache.org/examples/en/editor.html?c=line-simple&lang=js
-->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="layout/header.jsp" %>
<!DOCTYPE html>
<html lang="en" style="height: 100%">
<head>
  <meta charset="utf-8">
</head>
<body style="height: 100%; margin: 0">
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
  <div id="chart_div">
     
  </div>
  <input type="number" value="${withdraw.value}" id="value">
  <div>${withdraw.value}</div>
  <script>
  google.charts.load('current', {packages: ['corechart', 'bar']});
  google.charts.setOnLoadCallback(drawBasic);

  function drawBasic() {

        var data = google.visualization.arrayToDataTable([
          ['City', '2010 Population'],
          ['New York City, NY', 200000],
          ['Los Angeles, CA', 3792000],
          ['Chicago, IL', 2695000],
          ['Houston, TX', 2099000],
          ['Philadelphia, PA', 1526000]
        ]);

        var options = {
          title: 'Population of Largest U.S. Cities',
          chartArea: {width: '50%'},
          hAxis: {
            title: 'Total Population',
            minValue: 0
          },
          vAxis: {
            title: 'City'
          }
        };

        var chart = new google.visualization.BarChart(document.getElementById('chart_div'));

        chart.draw(data, options);
      }</script>
  <script src="js/account.js"></script>
</body>
</html>
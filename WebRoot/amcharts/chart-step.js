/* 初始化绘制月访问量3D柱状图数据 */
   var chart;
   var monthData=["一、二月","三、四月","五、六月","七、八月","九、十月","十一、十二月"];
   var curVisitData =[2025,1582,2809,2322,3122,2814];
   var columnChartData = [
       {
           "month":monthData[0],
           "visits": curVisitData[0],
           "color": "#FF0F00"
      },
      {
          "month": monthData[1],
          "visits": curVisitData[1],
          "color": "#FF6600"
      },
      {
          "month": monthData[2],
          "visits": curVisitData[2],
         "color": "#FF9E01"
      },
      {
           "month": monthData[3],
          "visits": curVisitData[3],
          "color": "#FCD202"
      },
      {
           "month": monthData[4],
          "visits": curVisitData[4],
          "color": "#F8FF01"
      },      {         "month": monthData[5],
          "visits": curVisitData[5],
          "color": "#B0DE09"
      },          
  ];
  
  /*绘制月访问量3D柱状图 */
   AmCharts.ready(function () {
     column3D();
     timeVisitedChart();
   });
  function column3D(){  
     chart = new AmCharts.AmSerialChart();
     chart.dataProvider = columnChartData;
     chart.categoryField = "month";
     chart.startDuration = 1;
     chart.depth3D = 50;
     chart.angle = 30;
     chart.marginRight = -45;
     var categoryAxis = chart.categoryAxis;
     categoryAxis.gridAlpha = 0;
     categoryAxis.axisAlpha = 0;
	 
	 var valueAxis = new AmCharts.ValueAxis();
	   
	  chart.addValueAxis(valueAxis);
	  var graph = new AmCharts.AmGraph();
	  graph.valueField = "visits";//dataProvider
	  graph.colorField = "color";
	  graph.balloonText = "<b>[[category]]: [[value]]</b>";
	  graph.type = "column";
	  graph.lineAlpha = 0.5;
	  graph.lineColor = "#FFFFFF";
	  graph.topRadius = 1;
	  graph.fillAlphas = 0.9;
	  chart.addGraph(graph);
  	var chartCursor = new AmCharts.ChartCursor();
    chartCursor.cursorAlpha = 1;
    chartCursor.zoomable = false;
    chartCursor.categoryBalloonEnabled = true;
    chartCursor.valueLineEnabled = true;         
    chartCursor.valueLineBalloonEnabled = true;
    chartCursor.valueLineAlpha = 1;
    chart.addChartCursor(chartCursor);
    chart.write("chartdiv");
  }
  
  /* 初始化绘制月访问量3D柱状图数据 */
   var timeVisitedChartData = [];
  function generateTimeVisitedChartData() {
       var firstDate = new Date();            
       firstDate.setMinutes(firstDate.getDate()-1000);
       for (var i = 0; i < 1000; i++) {
           var newDate = new Date(firstDate);
           newDate.setMinutes(newDate.getMinutes() + i);      
           var visits = Math.round(Math.random() * 40) + 10;                          
           timeVisitedChartData.push({
             date: newDate,
              visits: visits
          });
      }
  }
  /* 初始化绘制月访问量3D柱状图数据 */
  function timeVisitedChart() {
   	generateTimeVisitedChartData();
   	chart = new AmCharts.AmSerialChart();
   	chart.dataProvider = timeVisitedChartData;
   	chart.categoryField = "date";
   	var categoryAxis = chart.categoryAxis;
   	categoryAxis.parseDates = true; 
   	categoryAxis.minPeriod = "mm"; 			 
   	categoryAxis.gridAlpha = 0.07;
  	categoryAxis.axisColor = "#DADADA";
	var valueAxis = new AmCharts.ValueAxis();
   valueAxis.gridAlpha = 0.07;
   valueAxis.title = "Time Visted Chart";
   chart.addValueAxis(valueAxis);
   var graph = new AmCharts.AmGraph();
   graph.type = "line";
   graph.valueField = "visits";
   graph.lineAlpha = 1;
   graph.lineColor = "#d1cf2a";
  graph.fillAlphas = 0.3; 
   chart.addGraph(graph);
   var chartCursor = new AmCharts.ChartCursor();
   chartCursor.cursorPosition = "mouse";
   chartCursor.categoryBalloonDateFormat = "JJ:NN, DD MMMM";
   chart.addChartCursor(chartCursor);
   var chartScrollbar = new AmCharts.ChartScrollbar();
   chart.pathToImages = "amcharts/images/";
   chart.addChartScrollbar(chartScrollbar);
   chart.write("timeVisited");
  }
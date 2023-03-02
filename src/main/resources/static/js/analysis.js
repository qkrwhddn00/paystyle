//
var date = new Date();

y = date.getFullYear();

m = date.getMonth();

var firstDay = new Date(y, m, 1);//현재날짜 기준 1월 1일

var lastDay = new Date(y, m + 12, 0);//현재날짜 기준 12월 마지막날

let chartReport = {
	init: function() {
		$("#annualReport-body").ready(function() {
				chartReport.annualReportChart();
		});
		$("#annualReport-body").ready(function() {
				chartReport.monthlyReportChart();
		});
	},
	annualReportChart: function() {
		//지출 총합 데이터
		var WjsonData = jsonMontlyWithdraw;
		var WjsonObject = JSON.stringify(WjsonData);
		var WjData = JSON.parse(WjsonObject);
		//소득 총합 데이터
		var DjsonData = jsonMontlyDeposit;
		var DjsonObject = JSON.stringify(DjsonData);
		var DjData = JSON.parse(DjsonObject);

		var labelList = new Array();
		var valueList = new Array();
		var colorList = new Array();

		var wtotal=0;
		var dtotal=0;
		
		for (var i = 0; i < WjData.length; i++) {
			var d = WjData[i];
			wtotal+=d.value
		}
		
		for (var i = 0; i < DjData.length; i++) {
			var d = DjData[i];
			dtotal+=d.profit
		}
		valueList=[dtotal,wtotal];//수익 지출 총합
		labelList=["수익","지출"];
		// 그래프
		colorList.push(colorize());
		var data = {
			labels: labelList,
			datasets: [{
				backgroundColor: colorList,
				data: valueList
			}],
			options: {
				responsive: false ,
				title: {
					display: true,
					text: '지출내역 확인'
				}
			}
		};
		var ctx1 = document.getElementById('reportChart1').getContext('2d');
		new Chart(ctx1, {
			type: 'bar',
			data: data,
			options:{
				indexAxis:'y',
			}
		});
		function colorize() {
			var r = Math.floor(Math.random() * 200);
			var g = Math.floor(Math.random() * 200);
			var b = Math.floor(Math.random() * 200);
			var color = 'rgba(' + r + ', ' + g + ', ' + b + ', 0.7)';
			return color;
		}; //그래프
	},
	//string.slice(0,4)
	
	
	
	
	monthlyReportChart: function() {
		//지출 총합 데이터
		var WjsonData = jsonMontlyWithdraw;
		var WjsonObject = JSON.stringify(WjsonData);
		var WjData = JSON.parse(WjsonObject);
		//소득 총합 데이터
		var DjsonData = jsonMontlyDeposit;
		var DjsonObject = JSON.stringify(DjsonData);
		var DjData = JSON.parse(DjsonObject);

		var labelList = new Array();		
		var colorList = new Array();
		var wList = new Array();
		var dList=new Array();

		var wtotal=0;
		var dtotal=0;
		for(var j=1;j<=12;j++){
			
			for (var i = 0; i < WjData.length; i++) {
				var d = WjData[i];
				if (d.month==String(j)) {					
					wtotal+=d.value;
				}
				
			}wList.push(wtotal)
			for (var i = 0; i < DjData.length; i++) {
				var d = DjData[i];
				
				if (d.month==String(j)) {
					dtotal+=d.profit;
					
				}
			}dList.push(dtotal)
			wtotal=0;
			dtotal=0;
		}
		
		dataList=[wList[1],dList[1]];
		//console.log(dataList);
		//수익 지출 총합
		labelList=["수익","지출"];
		// 그래프
		colorList.push(colorize());
				
		for(var i=0;i<12;i++){
		var data = {
			labels: labelList,
			datasets: [{
				backgroundColor: colorList,
				data: [dList[i],wList[i]]
			}],
			options: {
				responsive: false ,
				title: {
					display: true,
					text: '지출내역 확인'
				}
			}
		};
		
		console.log("aa",String(i))
		var nctx1 = document.getElementById(String(i)+'Chart').getContext('2d');	
		
		new Chart(nctx1, {
			type: 'bar',
			data: data,
		});
		}
		
		function colorize() {
			var r = Math.floor(Math.random() * 200);
			var g = Math.floor(Math.random() * 200);
			var b = Math.floor(Math.random() * 200);
			var color = 'rgba(' + r + ', ' + g + ', ' + b + ', 0.7)';
			return color;
		}; //그래프
	},
	



	

}

chartReport.init();
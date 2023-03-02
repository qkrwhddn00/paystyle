

let account = {
	init: function() {

		$("#btn-addAccount").on("click", () => {
			this.addAccount();
		});
		$("#btn-addWithdraw").on("click", () => {
			this.addWithdraw();
		});
		//		$("#btn-chartCate").on("click", () => {
		//			this.chartWithdraw();
		//		});
		///////////////////////////////////////////////////////
		$("#btn-addDeposit").on("click", () => {
			this.addDeposit();
			
		});
		$("#btn-deleteWithdraw").on("click", () => {
			let check = confirm("삭제하시겠습니까");
			if (check) {
				this.deleteWithdrawById();
			} else {
				alert("취소되었습니다");
			}

		});
		$("#btn-deleteDeposit").on("click", () => {
			let check = confirm("삭제하시겠습니까");
			if (check) {
				this.deleteDepositById();
			} else {
				alert("취소되었습니다");
			}

		});
	},
	deleteWithdrawById: function() {
		var id = $("#deleteWithdrawId").text();

		$.ajax({
			type: "DELETE",
			url: "/withdraw/" + id,
			dataType: "json"
		}).done(function(resp) {
			alert("삭제가 완료되었습니다.");
			location.href = "/withdraw";
		}).fail(function(error) {
			alert(JSON.stringify(error));
		});
	},
	deleteDepositById: function() {
		var id = $("#deleteDepositId").text();

		$.ajax({
			type: "DELETE",
			url: "/deposit/" + id,
			dataType: "json"
		}).done(function(resp) {
			alert("삭제가 완료되었습니다.");
			location.href = "/deposit";
		}).fail(function(error) {
			alert(JSON.stringify(error));
		});
	},
	addWithdraw: function() {
		//alert('user의 save함수 호출됨');
		let data = {
			createDate: $("#createDate").val(),
			payMethod: $("#bankCate").val(),
			category: $("#withdrawCate").val(),
			value: $("#valuett").val()
		};
		$.ajax({
			type: "POST",
			url: "/addWithdraw",
			data: JSON.stringify(data),
			contentType: "application/json; charset=utf-8",
			dataType: "json"
		}).done(function(resp) {
			alert("지출추가가 완료되었습니다.");
			location.href = "/withdraw";
		}).fail(function(error) {
			alert(JSON.stringify(error));
		});
	},
	////////////////////////////////////////////////
	addDeposit: function() {
		//alert('user의 save함수 호출됨');
		let data = {
			createDate: $("#createDate").val(),
			payMethod: $("#bankSort").val(),
			category: $("#depositSort").val(),
			profit: $("#valuettt").val()
		};
		$.ajax({
			type: "POST",
			url: "/addDeposit",
			data: JSON.stringify(data),
			contentType: "application/json; charset=utf-8",
			dataType: "json"
		}).done(function(resp) {
			alert("수익추가가 완료되었습니다.");
			location.href = "/deposit";
		}).fail(function(error) {
			alert(JSON.stringify(error));
		});
	},
	/////////////////////////////////////
	addAccount: function() {
		//alert('user의 save함수 호출됨');
		let data = {
			payMethod: $("#bankName").val(),
			accountNum: $("#accountNum").val()
		};
		$.ajax({
			type: "POST",
			url: "/addAccount",
			data: JSON.stringify(data),
			contentType: "application/json; charset=utf-8",
			dataType: "json"
		}).done(function(resp) {
			alert("은행추가가 완료되었습니다.");
			location.href = "/withdraw";
		}).fail(function(error) {
			alert(JSON.stringify(error));
		});
	},


}


let chartAccount = {
	init: function() {



		$("#de-body").ready(function() {

			var jsonData = intData;
			var jsonObject = JSON.stringify(jsonData);
			var jData = JSON.parse(jsonObject);
			//$("#btn-chartCate").on("click", () => {

			if (jData[0].selectChart == "chartCate") {
				chartAccount.chartWithdrawCate();
			} else if (jData[0].selectChart == "chartBankName") {
				chartAccount.chartWithdrawBankName();
			}


			//	});
		});

	},
	chartWithdrawBankName: function() {


		var jsonData = intData;
		var jsonObject = JSON.stringify(jsonData);
		var jData = JSON.parse(jsonObject);

		var labelList = new Array();
		var valueList = new Array();
		var colorList = new Array();



		for (var i = 0; i < jData.length; i++) {
			var d = jData[i];

			if (d.startDate <= d.checkDate && d.endDate >= d.checkDate) {
				//				console.log(d.bankName);
				//				console.log(d.bankValue);
				
				valueList.push(d.bankValue);
				labelList.push(d.bankName);
			}
		}
		
		//중복제거
		//value가 같을때 지워지는것 방지
		var setLabel = new Set(labelList);
//		var setValue = new Set(valueList);
		var uniqueArrL = [...setLabel];
//		var uniqueArrV = [...setValue];
		//value가 같을때 지워지는것 방지
		var uniqueArrV = new Array();
		var countJ=0;
		for(var i=0;i<jData.length; i++){
			var d = jData[i];
			if(d.bankName==uniqueArrL[countJ]){
				
				uniqueArrV[countJ]=d.bankValue;
				countJ++;
				
			}
		}
		// console.log(timeList);
		// console.log(posList);  	
		// 그래프
		colorList.push(colorize());
		var data = {
			labels: uniqueArrL,
			datasets: [{
				backgroundColor: colorList,
				data: uniqueArrV
			}],
			options: {
				title: {
					display: true,
					text: '지출내역 확인'
				}
			}
		};
		var ctx1 = document.getElementById('myChart').getContext('2d');
		new Chart(ctx1, {
			type: 'bar',
			data: data
		});
		function colorize() {
			var r = Math.floor(Math.random() * 200);
			var g = Math.floor(Math.random() * 200);
			var b = Math.floor(Math.random() * 200);
			var color = 'rgba(' + r + ', ' + g + ', ' + b + ', 0.7)';
			return color;
		}; //그래프
	},



	chartWithdrawCate: function() {

		var jsonData = intData;
		var jsonObject = JSON.stringify(jsonData);
		var jData = JSON.parse(jsonObject);

		var labelList = new Array();
		var valueList = new Array();
		var colorList = new Array();

		let transValue = 0;
		let foodValue = 0;
		let eduValue = 0;
		let taxValue = 0;
		let duesValue = 0;
		let clothesValue = 0;
		let houseValue = 0;


		for (var i = 0; i < jData.length; i++) {
			var d = jData[i];

			console.log(jData[i]);
			if (d.startDate <= d.checkDate && d.endDate >= d.checkDate) {
				switch (d.cate) {
					case '교통':
						transValue += d.value;

						console.log(transValue)
						break;
					case '식비':
						foodValue += d.value
						console.log(foodValue)
						break;
					case '주거':
						houseValue += d.value;
						console.log(houseValue)
						break;
					case '교육':
						eduValue += d.value;
						console.log(eduValue)
						break;
					case '세금':
						taxValue += d.value;
						console.log(taxValue)
						break;
					case '회비':
						duesValue += d.value;
						console.log(duesValue)
						break;
					case '의류':
						clothesValue += d.value;
						console.log(clothesValue)
						break;
				}
			}
		}
		valueList = [transValue, foodValue, houseValue, eduValue, taxValue, duesValue, clothesValue];
		labelList = ["교통", "식비", "주거", "교육", "세금", "회비", "의류"];
		colorList.push(colorize());


		// console.log(timeList);
		// console.log(posList);  	
		// 그래프
		console.log(valueList);
		var data = {
			labels: labelList,
			datasets: [{
				backgroundColor: colorList,
				data: valueList
			}],
			options: {
				title: {
					display: true,
					text: '지출내역 확인'
				}
			}
		};
		console.log(labelList);
		var ctx1 = document.getElementById('myChart').getContext('2d');
		new Chart(ctx1, {
			type: 'bar',
			data: data
		});
		function colorize() {
			var r = Math.floor(Math.random() * 200);
			var g = Math.floor(Math.random() * 200);
			var b = Math.floor(Math.random() * 200);
			var color = 'rgba(' + r + ', ' + g + ', ' + b + ', 0.7)';
			return color;
		}; //그래프
	},

}
let chartDeposit = {
	init: function() {
		$("#be-body").ready(function() {

			var jsonData = intData;
			var jsonObject = JSON.stringify(jsonData);
			var jData = JSON.parse(jsonObject);
			//$("#btn-chartCate").on("click", () => {

			if (jData[0].selectChart == "chartProfit") {
				chartDeposit.chartDepositSort();
			} else if (jData[0].selectChart == "chartBankName") {
				chartDeposit.chartDepositBankName();
			}


			//});
		});

	},
	chartDepositBankName: function() {
		var jsonData = intData;
		var jsonObject = JSON.stringify(jsonData);
		var jData = JSON.parse(jsonObject);

		var labelList = new Array();
		var valueList = new Array();
		var colorList = new Array();
		



		for (var i = 0; i < jData.length; i++) {
			var d = jData[i];

			if (d.startDate <= d.checkDate && d.endDate >= d.checkDate) {
				//console.log(jData[i])
				valueList.push(d.bankProfit);
				labelList.push(d.bankName);
			}
		}

		//중복제거
		var setLabel = new Set(labelList);
//		var setValue = new Set(valueList);
		var uniqueArrL = [...setLabel];
//		var uniqueArrV = [...setValue];
		var uniqueArrV = new Array();
		var countJ=0;
		for(var i=0;i<jData.length; i++){
			var d = jData[i];
			if(d.bankName==uniqueArrL[countJ]){
				
				uniqueArrV[countJ]=d.bankProfit;
				countJ++;
				
			}
		}
		

		// console.log(timeList);
		// console.log(posList);  	
		// 그래프
		colorList.push(colorize());
		var data = {
			labels: uniqueArrL,
			datasets: [{
				backgroundColor: colorList,
				data: uniqueArrV
			}],
			options: {
				title: {
					display: true,
					text: '수익내역 확인'
				}
			}
		};
		var ctx1 = document.getElementById('myChart2').getContext('2d');
		new Chart(ctx1, {
			type: 'bar',
			data: data
		});
		function colorize() {
			var r = Math.floor(Math.random() * 200);
			var g = Math.floor(Math.random() * 200);
			var b = Math.floor(Math.random() * 200);
			var color = 'rgba(' + r + ', ' + g + ', ' + b + ', 0.7)';
			return color;
		}; //그래프
	},
	chartDepositSort: function() {
		var jsonData = intData;
		var jsonObject = JSON.stringify(jsonData);
		var jData = JSON.parse(jsonObject);

		var labelList = new Array();
		var valueList = new Array();
		var colorList = new Array();

		let salaryValue = 0;
		let investmentValue = 0;
		let pocketmoneyValue = 0;
		let othersValue = 0;


		for (var i = 0; i < jData.length; i++) {
			var d = jData[i];

			

			if (d.startDate <= d.checkDate && d.endDate >= d.checkDate) {
				switch (d.sort) {
					case '월급':
						salaryValue += d.profit;
						break;
					case '주식/투자':
						investmentValue += d.profit;
						break;
					case '용돈':
						pocketmoneyValue += d.profit;
						break;
					case '기타':
						othersValue += d.profit;
						break;
				}
			}
		}
		valueList = [salaryValue, investmentValue, pocketmoneyValue, othersValue];
		labelList = ["월급", "주식/투자", "용돈", "기타"];
		colorList.push(colorize());


		// console.log(timeList);
		// console.log(posList);  	
		// 그래프
		var data = {
			labels: labelList,
			datasets: [{
				backgroundColor: colorList,
				data: valueList
			}],
			options: {
				title: {
					display: true,
					text: '수익내역 확인'
				}
			}
		};
		
		var ctx1 = document.getElementById('myChart2').getContext('2d');
		new Chart(ctx1, {
			type: 'bar',
			data: data
		});
		function colorize() {
			var r = Math.floor(Math.random() * 200);
			var g = Math.floor(Math.random() * 200);
			var b = Math.floor(Math.random() * 200);
			var color = 'rgba(' + r + ', ' + g + ', ' + b + ', 0.7)';
			return color;
		}; //그래프
	},
}




chartAccount.init();



chartDeposit.init();


account.init();

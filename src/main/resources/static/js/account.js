

let account={
	init: function(){
		
		$("#btn-addAccount").on("click",()=>{
			//화살표 함수사용 이유:this를 바인딩하기 위해 사용한다.
			this.addAccount();
		});
		$("#btn-addWithdraw").on("click",()=>{
			//화살표 함수사용 이유:this를 바인딩하기 위해 사용한다.
			this.addWithdraw();
		});
	},
	addWithdraw: function(){
		//alert('user의 save함수 호출됨');
		let data={
			createDate:$("#createDate").val(),
			bankName:$("#bankCate").val(),
			cate:$("#withdrawCate").val(),
			value:$("#value").val()
		};
		$.ajax({
			type:"POST",
			url:"/addWithdraw",
			data:JSON.stringify(data),
			contentType:"application/json; charset=utf-8",
			dataType:"json"
		}).done(function(resp){
			alert("지출추가가 완료되었습니다.");
			location.href="/withdraw";
		}).fail(function(error){
			alert(JSON.stringify(error));
		});
	},
	addAccount: function(){
		//alert('user의 save함수 호출됨');
		let data={
			bankName:$("#bankName").val(),
			accountNum:$("#accountNum").val()
		};
		$.ajax({
			type:"POST",
			url:"/addAccount",
			data:JSON.stringify(data),
			contentType:"application/json; charset=utf-8",
			dataType:"json"
		}).done(function(resp){
			alert("은행추가가 완료되었습니다.");
			location.href="/withdraw";
		}).fail(function(error){
			alert(JSON.stringify(error));
		});
	},
	
}

account.init();



const labels = [
  '식비',
  '주거',
  '생활',
  'April',
  'May',
  'June',
];

const gdata = {
  labels: labels,
  datasets: [{
    label: 'My First dataset',
    backgroundColor: 'rgb(255, 99, 132)',
    borderColor: 'rgb(255, 99, 132)',
    data: [0, 1000, 5, 2, 20, 30, 45],
  }]
};

const config = {
  type: 'line',
  data: gdata,
  options: {
    responsive:false,
    maintainAspectRatio:true,
    title: {
      display: true,
      text: 'Chart.js Line Chart'
    }},
};

const myChart = new Chart(
  
  document.getElementById('myChart'),
  config
);
// 달력 생성
const makeCalendar = (date) => {
    // 현재 년도와 월 받아오기
    const currentYear = new Date(date).getFullYear();
    const currentMonth = new Date(date).getMonth() + 1; //월은 0이 1월이기때문에 +1해줌
    const currentDay = new Date(date).getDate();
    // 첫날의 요일 구하기 - 초기 시작위치를 위해서
    const firstDay = new Date(date.setDate(1)).getDay();
    // 마지막 날짜 구하기
    const lastDay = new Date(currentYear, currentMonth, 0).getDate(); //yyyy,mm,0으로하면 전월의 마지막날짜

    // 남은 박스만큼 다음달 날짜 표시
    const limitDay = firstDay + lastDay;
    const nextDay = Math.ceil(limitDay / 7) * 7;
	function 일별지출수익(){
    	
    	
    	
	}
    let htmlDummy = '';

    // 한달전 날짜 표시하기
    for (let i = 0; i < firstDay; i++) {
        htmlDummy += `<div class="noColor"></div>`;
    }
     if(currentMonth<=9){
        var currentMonthformat = `0${currentMonth}`;
    }else{
        var currentMonthformat = `${currentMonth}`;
    }
    
    
    document.querySelector(`.dateTitle`).innerText = `${currentYear}-${currentMonthformat}`;
	var nowMonth = new Date().getMonth()+1;
	if(nowMonth<=9){
		nowMonth = `0${nowMonth}`;
	}
    // 이번달 날짜 표시하기
    for (let i = 1; i <= lastDay; i++) {
        let day;
        let getDay = new Date().getDate();
        if(i<10||getDay<10){
            day="0"+i;
            getDay="0"+getDay;
        }else{
            day=i;
        }
        console.log(currentYear+"-"+currentMonthformat+"-"+day)
        console.log(new Date().getFullYear()+"-"+nowMonth+"-"+new Date().getDate())
        if(currentYear+"-"+currentMonthformat+"-"+day==new Date().getFullYear()+"-"+nowMonth+"-"+getDay){
            htmlDummy+=
                `<div class="lastDays toDay">`
        }else{
            htmlDummy +=
                `<div class="lastDays">`
        }
        htmlDummy +=
            `<span class="calDay">${day}</span>
        <section id="${day}dayIncome" class="daysIncome">
          +
        </section>
        <section id="${day}dayExpense" class="daysExpense">
          -
        </section>
      </div>`;
    }

    // 다음달 날짜 표시하기
    for (let i = limitDay; i < nextDay; i++) {
        htmlDummy += `<div class="noColor"></div>`;
    }
   document.querySelector(`.dateBoard`).innerHTML = htmlDummy;
    dayclick();
}


const date = new Date();

makeCalendar(date);


// 이전달 이동
document.querySelector(`.prevDay`).onclick = () => {
    let arr = $(".dateTitle").text()
    if(arr.split("-").includes("2022")){ //2022년 12월이 최소.
        return false;
    }else{
        makeCalendar(new Date(date.setMonth(date.getMonth() - 1)));
    }
}

// 다음달 이동
document.querySelector(`.nextDay`).onclick = () => {
    makeCalendar(new Date(date.setMonth(date.getMonth() + 1)));
}
$(document).on('change','.inoutType',function(){
    $("#inoutTag").empty();
    if($("#typeIn").is(":checked")){
        document.querySelector("#inoutTag").innerHTML =
            "<option value=\"월급\">월급</option>" +
            "<option value=\"주식/투자\">주식/투자</option>" +
            "<option value=\"용돈\">용돈</option>" +
            "<option value=\"기타\">기타</option>"
    }else if($("#typeOut").is(":checked")){
        document.querySelector("#inoutTag").innerHTML =
            "<option value=\"식비\">식비</option>"+
        "<option value=\"주거\">주거</option>"+
        "<option value=\"교통\">교통</option>"+
        "<option value=\"교육\">교육</option>"+
        "<option value=\"세금\">세금</option>"+
        "<option value=\"회비\">회비</option>"+
        "<option value=\"의류\">의류</option>"

    }
})
//달력 클릭하면 입력창으로 변경
function dayclick(){
    $(".toDay").click(function(){
        var year = $(".dateTitle").text().split("-")[0];
        var month = $(".dateTitle").text().split("-")[1];
        var day = "";
        if("0"+this.children[0].innerText.length==1){
            day = "0"+this.children[0].innerText;
        }else{
            day = this.children[0].innerText;
        }
        $("#calendar").toggleClass("dnone");
        $("#insertWrap").toggleClass("dnone");
        $("#selectedDay").text(year+"-"+month+"-"+day);
        $("#calSubmit").click(function (){
            if($("#typeIn").is(":checked")){

            }else if($("#typeOut").is(":checked")){

            }
        })


    })
}
function 거래(){
    let text = "";
    $.ajax({
        type: "POST",
        url: "/callPayMethod",
        contentType: "application/json; charset=utf-8"
    }).done(function (resp) {
        for (let i=0;i<resp.length;i++){
            text += `<option value="${resp[i]}">${resp[i]}</option>`
        }
        document.querySelector("#payMethod").innerHTML=text;
    }).fail(function (error) {
        alert(JSON.stringify(error));
    });
}
거래();
$("#calSubmit").click(function(){
	
	
	
	if($("#typeIn").is(":checked")){
		let data = {
			payMethod: $("#payMethod").val(),
			category: $("#inoutTag").val(),
			profit: $("#money").val()
		};
		$.ajax({
			type: "POST",
			url: "/addDeposit",
			data: JSON.stringify(data),
			contentType: "application/json; charset=utf-8",
			dataType: "json"
		}).done(function(resp) {
			alert("수익추가가 완료되었습니다.");
			location.href = "/";
		}).fail(function(error) {
			alert(JSON.stringify(error));
		});
	}else if($("#typeOut").is(":checked")){
		let data = {
			payMethod: $("#payMethod").val(),
			category: $("#inoutTag").val(),
			value: $("#money").val()
		};
		$.ajax({
			type: "POST",
			url: "/addWithdraw",
			data: JSON.stringify(data),
			contentType: "application/json; charset=utf-8",
			dataType: "json"
		}).done(function(resp) {
			alert("지출추가가 완료되었습니다.");
			location.href = "/";
		}).fail(function(error) {
			alert(JSON.stringify(error));
		});
	}else{
		alert("수익 / 지출 체크해주세요.")
	}
	
	
	////////////////////////////////////////////////
	
		//alert('user의 save함수 호출됨');
		
})
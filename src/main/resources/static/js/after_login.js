if (document.readyState == 'loading') {
    document.addEventListener('DOMContentLoaded', onload)
}else{
    onload()
}
function onload() {
    function 지출카테고리(){
        $.ajax({
            type: "POST",
            url: "/withdrawCategory",
            contentType: "application/json; charset=utf-8"
        }).done(function (resp) {
            if (resp[0][1]!="회원가입"){
                $("#mostWithdraw").text(resp[0][1]);
            }

            let inputText = "";
            for(let i=0;i<resp.length;i++){
                if(resp[i][1]!="회원가입") {
                    inputText += `<li>${resp[i][1]}</li>`;
                }
            }
            document.querySelector('#withdrawCateList').innerHTML = inputText;
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    }
    지출카테고리();
    function 수익카테고리(){
        $.ajax({
            type: "POST",
            url: "/depositCategory",
            contentType: "application/json; charset=utf-8"
        }).done(function (resp) {
            let inputText = "";
            for(let i=0;i<resp.length;i++){
                if(resp[i][1]!="회원가입") {
                    inputText += `<li>${resp[i][1]}</li>`;
                }
            }
            document.querySelector('#depositCateList').innerHTML = inputText;
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    }
    수익카테고리();
    function 대시보드불러오기() {
        $.ajax({
            type: "POST",
            url: "/thisMonthTotalAccount",
            contentType: "application/json; charset=utf-8"
        }).done(function (resp) {
            let arr = resp.split("!")
            $("#totalAsset").text(Number(arr[0]).toLocaleString('ko-KR'));
            console.log("arr[0]"+arr[0])
            $("#이번달수익").text(Number(arr[1]).toLocaleString('ko-KR'));
            $("#이번달지출").text(Number(arr[2]).toLocaleString('ko-KR'));
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    }

    대시보드불러오기();


    var ctx1 = document.getElementById('myChart1').getContext('2d');
    var biggest = 0;
//월별 총 수입
    var getdata = [
        0, , , , , , , , , , ,
    ];

    function insertGetdata() {
        $.ajax({
            type: "POST",
            url: "/monthDeposit",
            contentType: "application/json; charset=utf-8"
        }).done(function (resp) {
            console.log(resp)
            for (let i = 0; i < resp.length; i++) {
				if(resp[i][1].split("-")[1].slice(-1).split("")[0]==0){
					getdata[resp[i][1].split("-")[1].slice(-1) - 1] = resp[i][0];
				}else{
					getdata[resp[i][1].split("-")[1].slice(-2) - 1] = resp[i][0];
				}
                
                console.log(getdata[resp[i][1].split("-")[1].slice(-1) - 1])
                console.log(getdata)
                for (let i = 0; i < getdata.length; i++) {
                    if (biggest < getdata[i]) {
                        biggest = getdata[i]
                    }
                }
                ;

                var chart1 = new Chart(ctx1, {
                        // 챠트 종류를 선택
                        type: 'line',
                        // 챠트를 그릴 데이타
                        data: {
                            labels: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
                            datasets: [{
                                label: "총 수입",
                                backgroundColor: 'rgba(0, 0, 255, 0.282)',
                                borderColor: 'blue',
                                data: getdata,
                            }]
                        },
                        // 옵션
                        options: {
                            spanGaps: true,
                            grouped: true,
                            interaction: {
                                mode: 'index',
                            },
                            legend: {
                                display: false
                            },
                            tooltop: {
                                padding: 10,
                                bodySpacing: 20,
                            },
                            responsive: true,
                            scales: {
                                yAxes: [{
                                    display: false,
                                    ticks: {
                                        beginAtZero: true,
                                        max: biggest + 10000,
                                    }
                                }]
                            }
                        },
                    }
                );
            }
            insertOutdata();
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    }

    insertGetdata();
    var ctx2 = document.getElementById('myChart2').getContext('2d');
    var biggest1 = 0;
    var outdata = [0, , , , , , , , , , ,
    ];

    function insertOutdata() {
        $.ajax({
            type: "POST",
            url: "/monthWithdraw",
            dataType: "json",
            contentType: "application/json; charset=utf-8"
        }).done(function (resp) {
            console.log(resp)
            for (let i = 0; i < resp.length; i++) {
                outdata[resp[i][1].split("-")[1].slice(-1) - 1] = resp[i][0];
                console.log(outdata[resp[i][1].split("-")[1].slice(-1) - 1])
                console.log(outdata)
                for (let i = 0; i < outdata.length; i++) {
                    if (biggest1 < outdata[i]) {
                        biggest1 = outdata[i]
                    }
                }
                ;

                var chart1 = new Chart(ctx2, {

                        // 챠트 종류를 선택
                        type: 'line',
                        // 챠트를 그릴 데이타
                        data: {
                            labels: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
                            datasets: [{
                                label: "총 수입",
                                backgroundColor: 'rgba(0,255,0,0.282)',
                                borderColor: 'green',
                                data: outdata,
                            }]
                        },
                        // 옵션
                        options: {
                            spanGaps: true,
                            grouped: true,
                            interaction: {
                                mode: 'index',
                            },
                            legend: {
                                display: false
                            },
                            tooltop: {
                                padding: 10,
                                bodySpacing: 20,
                            },
                            responsive: true,
                            scales: {
                                yAxes: [{
                                    display: false,
                                    ticks: {
                                        beginAtZero: true,
                                        max: biggest1 + 10000,
                                    }
                                }]
                            }
                        },
                    }
                );
            }
            insertTotaldata();
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    }

//월별 총 지출
    function insertTotaldata() {
        var ctx = document.getElementById('myChart').getContext('2d');
        var biggest2 = 0;
        //월별 총 자산
        var totaldata = [
            , , , , , , , , , , ,
        ];
        for (let i = 0; i < totaldata.length; i++) {
            totaldata[i] = getdata[i] - outdata[i];

            if (biggest2 < totaldata[i]) {
                biggest2 = totaldata[i]
            }
        }
        ;

        var chart = new Chart(ctx, {
                // 챠트 종류를 선택
                type: 'line',
                // 챠트를 그릴 데이타
                data: {
                    labels: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
                    datasets: [{
                        label: '총 자산',
                        backgroundColor: 'rgba(255, 0, 0, 0.211)',
                        borderColor: 'red',
                        data: totaldata,
                    }]
                },
                // 옵션
                options: {
                    spanGaps: true,
                    grouped: true,
                    legend: {
                        display: false
                    },
                    tooltop: {
                        padding: 10,
                        bodySpacing: 20,
                    },
                    responsive: true,
                    scales: {
                        yAxes: [{
                            display: false,
                            ticks: {
                                beginAtZero: true,
                                max: biggest2 + 10000,
                            }
                        }]
                    }
                },
            }
        );
    };
}
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="../layout/common.jsp"%>
    <%@ include file="../layout/header.jsp"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
        <!-- DataTable Styles -->
        <link rel="stylesheet" href="../css/style.css">
        <!-- Demo Styles -->
        <link rel="stylesheet" href="../css/demo.css">
</head>

<body>
<h2>지출순위 TOP</h2>
    <form>
		<input type="date" id="reportStartDate"  name="startDate"  required="required">&nbsp; - &nbsp;
		<button type="submit">보기</button>
	</form>
    <div>
	<canvas id="reportChart1"style="width: 700px;height:80px;"></canvas><!--수익총합 지출총합 차트-->
	</div>
	<table class="table">
	    <thead>
		<tr>
			<th>순위</th>
			<th>사용내역</th>
			<th>금액</th>
		</tr>
		</thead>
		<tbody>
		<c:forEach var="analysis" items="${analysisPages}" varStatus="index">
	    <tr>
	        <td>${index.index+1}</td>
	        <td>${analysis[0]}</td>
	        <td>${analysis[1]}</td>  
	    </tr> 	
	  	</c:forEach>
		</tbody>
		
	</table>
   
    <!-- CDN -->
    <script src="https://cdn.jsdelivr.net/npm/simple-datatables@latest"></script>
    <!-- Custom Code -->
    <script>
        new window.simpleDatatables.DataTable("table")
    </script>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    <script src="../js/analysis.js" ></script>

<script>
	let jsonMontlyWithdraw = ${jsonMontlyWithdraw};
	let jsonMontlyDeposit = ${jsonMontlyDeposit};
</script>
        
</body>
</html>

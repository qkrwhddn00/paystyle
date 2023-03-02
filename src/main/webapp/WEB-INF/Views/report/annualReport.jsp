<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ include file="../layout/common.jsp"%>
    <%@ include file="../layout/header.jsp"%>

<div id="annualReport-body">


	<form>
		<input type="date" id="reportStartDate" name="startDate"
			required="required">&nbsp; - &nbsp;
		<button type="submit">보기</button>
	</form>
	<div>
		<canvas id="reportChart" style="width: 700px; height: 80px;"></canvas>
		<!--수익총합 지출총합 차트-->
	</div>
	<!-- 연도 구분은 service, 월별구분은 js -->
	<table class="table table-bordered border-$orange-800">
		<thead>
			<tr class="table-active">
				<th scope="col">기간</th>
				<th scope="col">1월</th>
				<th scope="col">2월</th>
				<th scope="col">3월</th>
				<th scope="col">4월</th>
				<th scope="col">5월</th>
				<th scope="col">6월</th>
				<th scope="col">7월</th>
				<th scope="col">8월</th>
				<th scope="col">9월</th>
				<th scope="col">10월</th>
				<th scope="col">11월</th>
				<th scope="col">12월</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>수익,지출 합계</td>
				<td><canvas id="0Chart" style="width: 7vw; height: 20vh;"></canvas></td>
				<td><canvas id="1Chart" style="width: 7vw; height: 20vh;"></canvas></td>
				<td><canvas id="2Chart" style="width: 7vw; height: 20vh;"></canvas></td>
				<td><canvas id="3Chart" style="width: 7vw; height: 20vh;"></canvas></td>
				<td><canvas id="4Chart" style="width: 7vw; height: 20vh;"></canvas></td>
				<td><canvas id="5Chart" style="width: 7vw; height: 20vh;"></canvas></td>
				<td><canvas id="6Chart" style="width: 7vw; height: 20vh;"></canvas></td>
				<td><canvas id="7Chart" style="width: 7vw; height: 20vh;"></canvas></td>
				<td><canvas id="8Chart" style="width: 7vw; height: 20vh;"></canvas></td>
				<td><canvas id="9Chart" style="width: 7vw; height: 20vh;"></canvas></td>
				<td><canvas id="10Chart" style="width: 7vw; height: 20vh;"></canvas></td>
				<td><canvas id="11Chart" style="width: 7vw; height: 20vh;"></canvas></td>
			</tr>
				
			
			<tr class="table-active">
				<td>지출 합계</td>
				<c:forEach var="values" items="${monthlyWithdrawData}">	
					<td>${values.totalValue}</td>
				</c:forEach>
			</tr>
			
			<tr>
				<td>식품 합계</td>
				<c:forEach var="values" items="${monthlyWithdrawData}">	
					<td>${values.food}</td>
				</c:forEach>
			</tr>
			<tr>
				<td>주거 합계</td>
				<c:forEach var="values" items="${monthlyWithdrawData}">	
					<td>${values.house}</td>
				</c:forEach>
			</tr>
			<tr>
				<td>교통 합계</td>
				<c:forEach var="values" items="${monthlyWithdrawData}">	
					<td>${values.transport}</td>
				</c:forEach>
			</tr>
			<tr>
				<td>세금 합계</td>
				<c:forEach var="values" items="${monthlyWithdrawData}">	
					<td>${values.tax}</td>
				</c:forEach>
			</tr>
			<tr>
				<td>회비 합계</td>
				<c:forEach var="values" items="${monthlyWithdrawData}">	
					<td>${values.dues}</td>
				</c:forEach>
			</tr>
			<tr>
				<td>의류 합계</td>
				<c:forEach var="values" items="${monthlyWithdrawData}">	
					<td>${values.clothes}</td>
				</c:forEach>
			</tr>
			<tr class="table-active">
				<td>소득 합계</td>
				<c:forEach var="profit" items="${monthlyDepositData}">	
					<td>${profit.totalProfit}</td>
				</c:forEach>
			</tr>
			<tr>
				<td>월급 합계</td>
				<c:forEach var="profit" items="${monthlyDepositData}">	
					<td>${profit.salary}</td>
				</c:forEach>
			</tr>
			<tr>
				<td>투자 합계</td>
				<c:forEach var="profit" items="${monthlyDepositData}">	
					<td>${profit.invest}</td>
				</c:forEach>
			</tr>
			<tr>
				<td>용돈 합계</td>
				<c:forEach var="profit" items="${monthlyDepositData}">	
					<td>${profit.pinmoney}</td>
				</c:forEach>
			</tr>
			<tr>
				<td>기타 합계</td>
				<c:forEach var="profit" items="${monthlyDepositData}">	
					<td>${profit.etc}</td>
				</c:forEach>
			</tr>
						
					
					
					
					
				
			
			</tr>
		</tbody>
	</table>
</div>
<script>
	let jsonMontlyWithdraw = ${jsonMontlyWithdraw};
	let jsonMontlyDeposit = ${jsonMontlyDeposit};
</script>

<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
<script src="/js/report.js"></script>


</body>
</html>
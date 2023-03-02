<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ include file="layout/common.jsp"%>
<%@ include file="layout/header.jsp"%>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.1/css/all.min.css" />

<style>
#myChart2 {
 width: 100%; 
 height: 500px;
 margin-top:10%;
}
</style>

<div id="be-body">
	<form style="display: flex">
	    <input type="date" name="startDate"  id="startDate" required="required">&nbsp; - &nbsp;
		<input type="date" name="endDate"  id="endDate" required="required">
		<select name="selectChart" id="selectChart" class="form-select"
			aria-label="Default select example" style="width: 200px">
			<option value="chartProfit" selected>수익</option>
			<option value="chartBankName">은행</option>
		</select>
		<button id="btn-chartProfit" type="submit"
			class="btn btn-outline-primary" style="margin-bottom: 4px;">보기</button>
	</form>
	<div style="width: 100%; height: 500px;">
		<canvas id="myChart2"></canvas>
	</div>

	<button type="button" class="btn btn-outline-primary"
		data-bs-toggle="modal" data-bs-target="#exampleModal"
		style="margin-bottom: 4px;">add account</button>

	<div class="modal fade" id="exampleModal" tabindex="-1"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h1 class="modal-title fs-5" id="exampleModalLabel">New
						account</h1>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<form>
						<div class="mb-3">
							<label for="recipient-name" class="col-form-label">은행:</label> <input
								id="bankName" type="text" class="form-control"
								id="recipient-name">
						</div>
						<div class="mb-3">
							<label for="message-text" class="col-form-label">계좌번호:</label> <input
								id="accountNum" type="text" class="form-control"
								id="message-text">
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-bs-dismiss="modal">Close</button>
					<button type="submit" id="btn-addAccount" class="btn btn-primary">save</button>
				</div>
			</div>
		</div>
	</div>


	<button id="btn-addDeposit" type="submit"
		class="btn btn-outline-secondary">저장</button>
	<table class="table table-bordered border-$orange-800">
		<thead>
			<tr class="table-active">
				<th scope="col">날짜</th>
				<th scope="col">은행</th>
				<th scope="col">수익내역</th>
				<th scope="col">입금금액(\)</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<form>
					<th scope="row"><%=sf.format(nowTime)%></th>
					<td style="padding: 0"><select id="bankSort"
						style="width: 100%; height: 41px; border: 0px">
							<c:forEach var="account" items="${modelBankName}">
								<option>${account.payMethod}</option>
							</c:forEach>

					</select></td>
					<td style="padding: 0"><select id="depositSort"
						style="width: 100%; height: 61px; border: 0px">
							<option value="월급">월급</option>
							<option value="주식/투자">주식/투자</option>
							<option value="용돈">용돈</option>
							<option value="기타">기타</option>
					</select></td>
					<td style="padding: 0"><input id="valuettt" type="number"
						style="width: 100%; height: 41px; border: 0px"></td>
				</form>
			</tr>


			<c:forEach var="deposit" items="${depositPage.content}">
				<c:if test="${principal.user.userid eq deposit.users.userid}">
					<tr>

						<th scope="row"><fmt:formatDate pattern="yyyy/MM/dd"
								value="${deposit.createDate}" /></th>
						<td>${deposit.payMethod}</td>
						<td>${deposit.category}<span id="deleteDepositId" style="display:none">${deposit.id}</span></td>
						<td style="display:flex;justify-content:space-between">${deposit.profit}   <button id="btn-deleteDeposit" type="button" class="btn btn-danger">삭제</button></td>

					</tr>
				</c:if>
			</c:forEach>

		</tbody>

	</table>
	<nav aria-label="Page navigation example">
		<ul class="pagination justify-content-center">
			<c:choose>
				<c:when test="${boards.first}">
					<li class="page-item disabled"><a class="page-link"
						href="?page=${boards.number-1}">Previous</a></li>
				</c:when>
				<c:otherwise>
					<li class="page-item"><a class="page-link"
						href="?page=${boards.number-1}">Previous</a></li>
				</c:otherwise>
			</c:choose>
			<c:choose>
				<c:when test="${boards.last}">
					<li class="page-item disabled"><a class="page-link"
						href="?page=${boards.number+1}">Next</a></li>
				</c:when>
				<c:otherwise>
					<li class="page-item"><a class="page-link"
						href="?page=${boards.number+1}">Next</a></li>
				</c:otherwise>
			</c:choose>
		</ul>
	</nav>
</div>
<script>
let intData = ${json};

</script>

<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
<script src="js/account.js"></script>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="layout/common.jsp"%>
<%@ include file="layout/sidebar.jsp"%>
<main>
    <section id="menu-nav">
        <button id="open-side" onclick="sidenav()"><i class="fa-solid fa-bars"></i></button>
    </section>
    <section id="insertWrap" class="row dnone">
        <table id="insertTable">
            <colgroup>
                <col><col> <col><col>
            </colgroup>
            <tr>
                <th colspan="4" id="selectedDay"></th>
            </tr>
            <tr>
                <th ><label for="typeIn">수익</label></th>
                <td ><input type="radio" name="inoutType" class="inoutType" id="typeIn"></td>
                <th ><label for="typeOut">지출</label></th>
                <td ><input type="radio" name="inoutType"  class="inoutType" id="typeOut"></td>
            </tr>
            <tr>
                <th>
                    거래
                </th>
                <td colspan="3">
                    <select name="payMethod" id="payMethod" style="width: 80%;">

                    </select>
                </td>
            </tr>
            <tr>
                <th>금액</th>
                <td colspan="3"><input style="width: 80%" type="text" id="money">&nbsp;원</td>
            </tr>
            <tr>
                <th>태그</th>
                <td colspan="3" id="tagArea">
                    <select  name="inoutTag" id="inoutTag" style="width: 80%;">

                    </select>
                </td>
            </tr>
        </table>
        <button id="calSubmit" class="tableBtn" type="button" style="width: 20%; height: 50px;">확인</button>
        <button class="tableBtn" type="button" style="width: 20%; height: 50px;" onclick="$('#insertWrap').toggleClass('dnone');$('#calendar').toggleClass('dnone')">취소</button>
    </section>
    <section id="calendar" class="backcalendar row">
        <div class='rap'>
            <div class="header">
                <div class="btn prevDay">←</div>
                <h2 class='dateTitle'></h2>
                <div class="btn nextDay">→</div>
            </div>
            <div>
                <div class="grid dateHead">
                    <div>일</div>
                    <div>월</div>
                    <div>화</div>
                    <div>수</div>
                    <div>목</div>
                    <div>금</div>
                    <div>토</div>
                </div>

                <div class="grid dateBoard"></div>
            </div>
        </div>
        <script src="js/cal.js"></script>
    </section>
    <%@ include file="layout/footer.jsp"%>
</main>
</body>
</html>
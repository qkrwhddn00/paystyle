<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<%@ include file="layout/sidebar.jsp"%>
<script src="https://cdn.jsdelivr.net/npm/chart.js@2.8.0"></script>
<main>
  <section id="menu-nav">
    <button id="open-side" onclick="sidenav()"><i class="fa-solid fa-bars"></i></button>
  </section>
  <section id="miniCards" class="row">
    <div class="miniCard bg_white">
      <div class="cardText">
        <h5 class="cardTitle">총 자산</h5>
        <h6><span id="totalAsset"></span>&nbsp;<span>원</span></h6>
        <script src="js/after_login.js">
        </script>
      </div>
      <div>
        <div class="cardIcon" style="background-image: linear-gradient(310deg,#f5365c,#f56036);"><i
                class="fa-solid fa-wallet"></i></div>
      </div>
    </div>
    <div class="miniCard bg_white">
      <div class="cardText">
        <h5 class="cardTitle">이번달 수익</h5>
        <h6><span id="이번달수익"></span>&nbsp;<sapn>원</sapn>
        </h6>
      </div>
      <div>
        <div class="cardIcon" style="background-image:linear-gradient(310deg,#2dce89,#2dcecc);"><i
                class="fa-solid fa-sack-dollar"></i></div>
      </div>
    </div>
    <div class="miniCard bg_white">
      <div class="cardText">
        <h5 class="cardTitle">이번달 지출</h5>
        <h6><span id="이번달지출"></span>&nbsp;<span>원</span></h6>
      </div>
      <div>
        <div class="cardIcon" style="background-image: linear-gradient(310deg,#5e72e4,#825ee4);"><i
                class="fa-solid fa-cart-shopping"></i></div>
      </div>
    </div>
    <div class="miniCard bg_white">
      <div class="cardText">
        <h5 class="cardTitle">최다 지출</h5>
        <h6 id="mostWithdraw"></h6>
      </div>
      <div>
        <div class="cardIcon" style="background-image: linear-gradient(310deg,#fb6340,#fbb140);"><i
                class="fa-solid fa-tag"></i></div>
      </div>
    </div>
  </section>
  <section class="row">
    <div id="month-total" class="bg_white">
      <h1>월별 총 자산</h1>
      <div class="container">
        <canvas id="myChart" width="100%" height="40px"></canvas>
      </div>
    </div>

  </section>
  <section class="month_get row">
    <div class="m_get m_chart bg_white">
      <h5>월별 총 수익</h5>
      <div class="container overhidden">
        <canvas id="myChart1" width="100%" height="50px"></canvas>
      </div>
    </div>
    <div class="m_get m_tag bg_white">
      <h6>수익 카테고리 순위</h6>
      <ul id="depositCateList" style="overflow: auto">
      </ul>
    </div>
  </section>
  <section class="month_get row">
    <div class="m_get m_chart bg_white">
      <h5>월별 총 지출</h5>
      <div class="container overhidden">
        <canvas id="myChart2" width="100%" height="50px"></canvas>
      </div>
    </div>
    <div class="m_get m_tag bg_white">
      <h6>지출 카테고리 순위</h6>
      <ul id="withdrawCateList" style="overflow: auto">
      </ul>
    </div>
  </section>
  <%@ include file="layout/footer.jsp"%>
</main>
</body>
</html>
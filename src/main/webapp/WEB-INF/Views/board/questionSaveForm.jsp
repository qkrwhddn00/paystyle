<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@ include file="../layout/common.jsp"%>
    <%@ include file="../layout/header.jsp"%>
<style>
#container{
   width:100%;
}
#Inquiry_wrap{
   width:80%;
   margin-left:25%;
   margin-top:10%;
   
}
.Inquiry_wrap_title{
   font-size: 30px;
   margin-bottom:5%;
   margin-left: 20%;
}
</style>
<div id=container>
        <div id="Inquiry_wrap">    
          <div class="Inquiry_wrap_title">문의 하기</div>
          <form>
              <div>
             제목&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
              <input type="text" name="Inquiry_title" id="title">
              </div>              
              <div>
                이름&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <input type="text" name="Inquiry_name" id="user"value="${principal.user.username}" disabled>
              </div>      
              <div id="Inquiry_content">
                <div>문의내용&nbsp;&nbsp;</div>
                <textarea id="contents" rows="10" cols="100"></textarea>
              </div>           
          </form>
          <div id="Inquiry_submit">
          <button  id="btn-question-save" value="저장">저장</button>
          
          </div>
      </div>
    </div>
    
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script type="text/javascript" src="/js/board.js"></script>	

</body>
</html> 
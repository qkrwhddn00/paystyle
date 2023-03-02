let index = {
   init: function() {
      $("#register-btn-save").on("click", () => {
         this.save();
      });
      $("#register-btn-update").on("click", () => {
         this.update();
      });
      $("#register-btn-delete").on("click", () => {
         this.delete();
      });
  },
  save: function() {
	if(checkAll()){
      let data = {
         userid: $("#userid").val(),
         username: $("#username").val(),
         email: $("#email").val(),
         password: $("#password").val()
      };
      $.ajax({
            type: "POST",
            url: "/auth/joinProc",
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType: "json"
         }).done(function(data) {
	console.log("save ajax"+data);
	if(data.data==1){
		 alert("회원가입 성공.");
	}else if(data.data==-1){
		 alert("회원가입 실패. 중복아이디 입니다.");
	}
           alert("회원가입 절차 완료");
           location.href = "/";
         }).fail(function(error) {
            alert(JSON.stringify(error));
         });
    	}
	},
	update: function(){
		let data={
			userid: $("#userid").val(),
			username: $("username").val(),
			password: $("#password").val(),
			email: $("#email").val(),
			createDate: $("#createDate").val()
		};
		console.log(userid);
		console.log(email);
		$.ajax({ 
			type:"PUT",
			url:"/userUpdate",
			data:JSON.stringify(data),
			contentType:"application/json; charset=utf-8",
			dataType:"json"
		}).done(function(resp){
			alert("수정이 완료되었습니다.");
			location.href="/";
			
			//응답이 정상
		}).fail(function(error){
			alert(JSON.stringify(error));
			location.href="/  ";		
			//응답이 비정상
		});
	},
	delete: function(){
		//alert('user의 delete 함수 호출됨');
		let id=$("#id").val();
		$.ajax({
			type:"DELETE",
			url:"/delete/"+id,
			
			dataType:"json" //자동으로 변환해주기 때문에 생략 가능
			//응답의 결과가 문자열이 아닌 json으로 변환
		}).done(function(resp){
			alert("삭제되었습니다");
			location.href="/";
			//응답이 정상
		}).fail(function(error){
			alert(JSON.stringify(error));
			//응답이 비정상
		});
	},
	
	   checkAll:  function () {
        if (!checkUserId(joinForm.userId.value)) {
            return false;
        }
        if (!checkName(joinForm.name.value)) {
            return false;
        }  
        if (!checkMail(joinForm.mail.value)) {
            return false;
        }
        if (!checkPassword(joinForm.userId.value, joinForm.password1.value, joinForm.password2.value)) {
            return false;
        }

        return true;
    }

}
index.init();

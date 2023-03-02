 function ajaxTest(){
	 let data = {
	         userid: $("#userid").val(),
	         username: $("#username").val(),
	         email: $("#email").val(),
	         password: $("#password").val()
	      };
      $.ajax({
        type : "POST",
        url : "/auth/idCheckView",
        dataType : "json",
        data: JSON.stringify(data),
        contentType: "application/json; charset=utf-8",
        error : function() {
          alert('통신실패!!');
        },
        success : function(data) {
        	if(data){
        		 $('#Context').html(data);
        	}else{
        		$('#Context').html(data);
        	}
        }
 
      })
      .done(function(resp){
			const element=document.getElementById('Context');
			if(resp.data==1){
				element.innerText="이 아이디는 중복되지 않았습니다.";
       	}else if(resp.data==-1){
       		element.innerText="이미 있는 아이디입니다.";
       	}

			
			//응답이 정상
		}).fail(function(error){
			alert(JSON.stringify(error));
			location.href="/  ";		
			//응답이 비정상
		});
    }

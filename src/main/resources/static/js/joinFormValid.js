
    function checkAll() {
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
 
    // 공백확인 함수
    function checkExistData(value, dataName) {
        if (value == "") {
            alert(dataName + " 입력해주세요!");
            return false;
        }
        return true;
    }
 
    function checkUserId(id,idEx) {
        //Id가 입력되었는지 확인하기
        if (!checkExistData(id, "아이디를")){
            return false;
 			}
        var idRegExp = /^[a-zA-z0-9]{3,12}$/; //아이디 유효성 검사
        if (!idRegExp.test(id)) {			
	       alert("아이디는 영문 대소문자와 숫자 3~12자리로 입력해야합니다!");
	       joinForm.userId.value = "";
			return false;		
        }
        return true; //확인이 완료되었을 때
    }
 
    function checkPassword(id, password1, password2) {
        //비밀번호가 입력되었는지 확인하기
        if (!checkExistData(password1, "비밀번호를"))
            return false;
        //비밀번호 확인이 입력되었는지 확인하기
        if (!checkExistData(password2, "비밀번호 확인을"))
            return false;
 
        var password1RegExp = /^[a-zA-z0-9]{3,12}$/; //비밀번호 유효성 검사
        if (!password1RegExp.test(password1)) {
            alert("비밀번호는 영문 대소문자와 숫자 3~12자리로 입력해야합니다!");
            joinForm.password1.value = "";
            joinForm.password1.focus();
            return false;
        }
        //비밀번호와 비밀번호 확인이 맞지 않다면..
        if (password1 != password2) {
            alert("두 비밀번호가 맞지 않습니다.");
            joinForm.password1.value = "";
            joinForm.password2.value = "";
            joinForm.password2.focus();
            return false;
        }
 
        //아이디와 비밀번호가 같을 때..
        if (id == password1) {
            alert("아이디와 비밀번호는 같을 수 없습니다!");
            joinForm.password1.value = "";
            joinForm.password2.value = "";
            joinForm.password2.focus();
            return false;
        }
        return true; //확인이 완료되었을 때
    }
 
    function checkMail(mail) {
        //mail이 입력되었는지 확인하기
        if (!checkExistData(mail, "이메일을"))
            return false;
 
        var emailRegExp = /^[A-Za-z0-9_]+[A-Za-z0-9]*[@]{1}[A-Za-z0-9]+[A-Za-z0-9]*[.]{1}[A-Za-z]{1,3}$/;
        if (!emailRegExp.test(mail)) {
            alert("이메일 형식이 올바르지 않습니다!");
            joinForm.mail.value = "";
            joinForm.mail.focus();
            return false;
        }
        return true; //확인이 완료되었을 때
    }
 
    function checkName(name) {
        if (!checkExistData(name, "이름을"))
            return false;
 
        var nameRegExp = /^[가-힣]{2,4}$/;
        if (!nameRegExp.test(name)) {
            alert("이름이 올바르지 않습니다.");
            return false;
        }
        return true; //확인이 완료되었을 때
    }
	
	

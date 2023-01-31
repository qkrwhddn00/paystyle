let index = {
	init: function() {
		$("#register-btn-save").on("click", () => {
			this.save();
		});
	},
	save: function() {
		let data = {
			userid: $("#userid").val(),
			username: $("#username").val(),
			email: $("#email").val(),
			password: $("#password").val(),
		};
		console.log(data.email);
		$.ajax({
			type: "POST",
			url: "/auth/joinProc",
			data: JSON.stringify(data),
			contentType: "application/json; charset=utf-8",
			dataType: "json"
		}).done(function(resp) {
				console.log("성공11");
				alert("회원가입 성공");
				location.href = "/";
		}).fail(function(error) {
			alert(JSON.stringify(error));
		});
	},
}
index.init();
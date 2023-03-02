let index = {
	init: function() {
		$("#btn-save").on("click", () => {
			this.save();
		});
		$("#btn-delete").on("click", () => {
			let check = confirm("삭제하시겠습니까");
			if (check) {
				this.deleteById();
			} else {
				alert("취소되었습니다");
			}

		});
		$("#btn-update").on("click", () => {
			this.update();
		});
		$("#btn-question-save").on("click", () => {
			this.questionSave();
		});
		$("#btn-reply-save").on("click", () => {
			this.replySave();
		});

		$("#btn-question-update").on("click", () => {
			this.questionupdate();
		});

	},

	save: function() {

		let data = {
			title: $("#title").val(),
			contents: $("#contents").val(),
			users: $("#user").val()

		};

		$.ajax({
			type: "POST",
			url: "/api/board",
			data: JSON.stringify(data),
			contentType: "application/json; charset=utf-8",
			dataType: "json"
		}).done(function(resp) {
			alert("글쓰기가 완료되었습니다.");
			location.href = "/board/csboard";
		}).fail(function(error) {
			alert(JSON.stringify(error));
		});
	},

	questionSave: function() {

		let data = {
			title: $("#title").val(),
			contents: $("#contents").val(),
			
		};
console.log("aa")
		$.ajax({
			type: "POST",
			url: "/api/question",
			data: JSON.stringify(data),
			contentType: "application/json; charset=utf-8",
			dataType: "json"
		}).done(function(resp) {
			alert("글쓰기가 완료되었습니다.");
			location.href = "/inquire";
		}).fail(function(error) {
			alert(JSON.stringify(error));
		});
	},


	deleteById: function() {
		var id = $("#id").text();

		$.ajax({
			type: "DELETE",
			url: "/api/board/" + id,
			dataType: "json"
		}).done(function(resp) {
			alert("삭제가 완료되었습니다.");
			location.href = "/board/csboard";
		}).fail(function(error) {
			alert(JSON.stringify(error));
		});
	},

	update: function() {
		let id = $("#id").val();

		let data = {
			title: $("#title").val(),
			contents: $("#contents").val()
		};


		$.ajax({
			type: "PUT",
			url: "/api/board/" + id,
			data: JSON.stringify(data),
			contentType: "application/json; charset=utf-8",
			dataType: "json"
		}).done(function(resp) {
			alert("수정이 완료되었습니다.");
			location.href = "/board/csboard";
		}).fail(function(error) {
			alert(JSON.stringify(error));
		});
	},

	replySave: function() {
		let questionId = $("#questionId").val();
		let data = {
			content: $("#reply-content").val()
		};

		$.ajax({
			type: "POST",
			url: '/api/board/reply/' + questionId,
			data: JSON.stringify(data),
			contentType: "application/json; charset=utf-8",
			dataType: "json"
		}).done(function(resp) {
			alert("댓글작성이 완료되었습니다.");
			location.href = "/question/" + questionId;
		}).fail(function(error) {
			alert(JSON.stringify(error));
		});

	},

	replyDelete: function(questionId, replyId) {

		$.ajax({
			type: "DELETE",
			url: `/api/board/${questionId}/reply/${replyId}`,
			dataType: "json"
		}).done(function(resp) {
			alert("삭제가 완료되었습니다.");
			location.href = "/question/" + questionId;
		}).fail(function(error) {
			alert(JSON.stringify(error));
		});
	},


	questionupdate: function() {
		let id = $("#id").val();

		let data = {
			title: $("#title").val(),
			contents: $("#contents").val()
		};


		$.ajax({
			type: "PUT",
			url: "/api/question/" + id,
			data: JSON.stringify(data),
			contentType: "application/json; charset=utf-8",
			dataType: "json"
		}).done(function(resp) {
			alert("수정이 완료되었습니다.");
			location.href = "/inquire";
		}).fail(function(error) {
			alert(JSON.stringify(error));
		});
	},


}
index.init();
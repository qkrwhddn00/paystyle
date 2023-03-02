let index = {
	init: function() {

		$("#btn-question-save").on("click", () => {
			this.questionSave();
		});
		$("#btn-question-update").on("click", () => {
			this.questionupdate();
		});
		$("#btn-question-delete").on("click", () => {
			let check = confirm("삭제하시겠습니까");
			if (check) {
				this.questionDelete();
			} else {
				alert("취소되었습니다");
			}

		});

		$("#btn-reply-save").on("click", () => {
			this.replySave();
		});

	},


	questionSave: function() {
		let data = {
			title: $("#title").val(),
			contents: $("#contents").val(),
		};

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

	questionDelete: function() {
		var id = $("#id").text();

		$.ajax({
			type: "DELETE",
			url: "/api/question/" + id,
			dataType: "json"
		}).done(function(resp) {
			alert("삭제가 완료되었습니다.");
			location.href = "/inquire";
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




	replySave: function() {
		let questionId = $("#questionId").val();
		let data = {
			content: $("#reply-content").val()
		};

		$.ajax({
			type: "POST",
			url: '/api/reply/' + questionId,
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

		let check = confirm("삭제하시겠습니까");
		if (check) {
			$.ajax({
				type: "DELETE",
				url: `/api/${questionId}/reply/${replyId}`,
				dataType: "json"
			}).done(function(resp) {
				alert("삭제가 완료되었습니다.");
				location.href = "/question/" + questionId;
			}).fail(function(error) {
				alert(JSON.stringify(error));
			});
		} else {
			alert("취소되었습니다");
		}


	},





}
index.init();
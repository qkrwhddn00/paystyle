package com.payStyle.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.payStyle.model.Reply;
import com.payStyle.model.Users;
import com.payStyle.model.question;
import com.payStyle.repository.QuestionRepository;
import com.payStyle.repository.ReplyRepository;

@Service
public class ReplyService {
	
	@Autowired
	private QuestionRepository questionRepository;

	@Autowired
	private ReplyRepository replyRepository;

	// 댓글쓰기
	@Transactional
	public void 댓글쓰기(Users user, int questionId, Reply requestReply) {

		question question = questionRepository.findById(questionId).orElseThrow(() -> {
			return new IllegalArgumentException("글 찾기 실패");
		});

		requestReply.setQuestion(question);
		requestReply.setUsers(user);

		replyRepository.save(requestReply);
	}

	// 댓글삭제
	@Transactional
	public void 댓글삭제(int replyId) {
		replyRepository.deleteById(replyId);
	}

}

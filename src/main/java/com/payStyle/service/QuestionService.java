package com.payStyle.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.payStyle.model.Users;
import com.payStyle.model.question;
import com.payStyle.repository.QuestionRepository;
import com.payStyle.repository.UserRepository;

@Service
public class QuestionService {
	
	@Autowired
	private QuestionRepository questionRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	//문의글 조회
	public Page<question> 질문목록(Pageable pageable) {
		return questionRepository.findAll(pageable);
	}	
	
	//문의글 작성
	@Transactional
	public void 질문하기(question question,Users user) {			
		
		question.setUser(user);
		questionRepository.save(question);
	}	
	

	//문의글 상세보기
	public question 문의상세보기(int id) {
		return questionRepository.findById(id)
				.orElseThrow(()->{
					return new IllegalArgumentException("글상세보기 실패");
				});
	}		

	//문의글 수정
	@Transactional
	public void 질문수정하기(int id,question requestBoard) {
		question board = questionRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("글찾기 실패");
		});
	board.setTitle(requestBoard.getTitle());
	board.setContents(requestBoard.getContents());
	}	
	
	//문의글 삭제
	@Transactional
	public void 글삭제하기(int id) {
		questionRepository.deleteById(id);
	}
	
	//문의글 조회수 증가
	@Transactional
    public int updateCount(int id) {
        return questionRepository.updateCount(id);
    }
	
	

}

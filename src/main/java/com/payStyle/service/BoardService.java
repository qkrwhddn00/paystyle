package com.payStyle.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.payStyle.model.Notice;
import com.payStyle.model.Users;
import com.payStyle.repository.BoardRepository;
import com.payStyle.repository.UserRepository;

@Service
public class BoardService {
	
	@Autowired
	private BoardRepository boardRepository;

	@Autowired
	private UserRepository userRepository;
	
	
	public Page<Notice> 글목록(Pageable pageable) {
		return boardRepository.findAll(pageable);
	}
	// 게시글 작성
		@Transactional
		public void 글쓰기(Notice board, Users user) {
			board.setCount(0);
			board.setUser(user);
			boardRepository.save(board);
			System.out.println("aa");
		}
	// 게시글 상세보기
		public Notice 글상세보기(int id) {
			return boardRepository.findById(id).orElseThrow(() -> {
				return new IllegalArgumentException("글상세보기 실패");
			});
		}
		// 게시글 수정
		@Transactional
		public void 글수정하기(int id, Notice requestBoard) {
			Notice board = boardRepository.findById(id).orElseThrow(() -> {
				return new IllegalArgumentException("글찾기 실패");
			});
			board.setTitle(requestBoard.getTitle());
			board.setContents(requestBoard.getContents());
		}

		// 게시글 삭제
		@Transactional
		public void 글삭제하기(int id) {
			boardRepository.deleteById(id);
		}

		// 게시글 조회수 증가
		@Transactional
		public int updateCount(int id) {
			return boardRepository.updateCount(id);
		}

		

}

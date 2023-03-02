package com.payStyle.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.payStyle.service.QuestionService;

@Controller
public class QuestionController {
	
	@Autowired
	private QuestionService questionService;
	
	//문의게시판 조회
	@GetMapping("/inquire")
	public String inquire(Model model,@PageableDefault(size= 10, sort = "id",
	direction = Sort.Direction.DESC) Pageable pageable) {		
		model.addAttribute("questions",questionService.질문목록(pageable));
		return "board/question";
	}
	@GetMapping({"/board/questionSaveForm"})
	public String questionSaveForm() {
		return "board/questionSaveForm";
	}	
	//문의게시글 상세보기	
	@GetMapping("/question/{id}")
	public String findquestionById(@PathVariable int id, Model model) {
		model.addAttribute("question",questionService.문의상세보기(id));
		questionService.updateCount(id);
		return "board/questiondetail";
	}
	
	//문의게시글 수정
	@GetMapping("/question/{id}/questionUpdateForm")
	public String questionUpdateForm(@PathVariable int id,Model model) {
		model.addAttribute("question",questionService.문의상세보기(id));
		return "board/questionUpdateForm";
	}
	
	
	
}

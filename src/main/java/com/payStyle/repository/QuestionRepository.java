package com.payStyle.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.payStyle.model.question;

public interface QuestionRepository extends JpaRepository<question,Integer> {
	@Modifying
	 @Query("update question p set p.count = p.count + 1 where p.id = :id")
	 int updateCount(int id);

	List<question> findByTitleContaining(String keyword);

	List<question> findByContentsContaining(String keyword);

	Page<question> findByTitleContaining(String keyword, Pageable pageable);

	Page<question> findByContentsContaining(String keyword, Pageable pageable);

}

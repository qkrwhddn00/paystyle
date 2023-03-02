package com.payStyle.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.payStyle.model.Notice;
import com.payStyle.model.Users;

public interface BoardRepository extends JpaRepository<Notice,Integer>{
	
	 @Modifying
	 @Query("update Notice p set p.count = p.count + 1 where p.id = :id")
	 int updateCount(int id);

	List<Notice> findByContentsContaining(String keyword);

	List<Notice> findByTitleContaining(String keyword);

	Page<Notice> findByTitleContaining(String keyword, Pageable pageable);

	
	Page<Notice> findByContentsContaining(String keyword, Pageable pageable);

	Page<Notice> findByUser(Users searchuser, Pageable pageable);
	
	List<Notice> findByUser(Users searchuser);

}

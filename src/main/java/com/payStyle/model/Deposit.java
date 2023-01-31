package com.payStyle.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import org.springframework.data.annotation.CreatedDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@SequenceGenerator(
		name = "USER_SEQ_GENERATO_D"
		, sequenceName = "USER_SEQ_D"
		, initialValue = 1
		, allocationSize = 1
		)
public class Deposit {//소득
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="USER_SEQ_GENERATOR_D")
	private int id;
	
	
	@ManyToOne
	@JoinColumn(name="userId")
	private Users users;
	
	@Column
	private String cate;
	
	@Column
	private int value;
	
	
//	@Column
//	private int	deposit;//예금
//	@Column
//	private int	savings;//적금
//	@Column
//	private int	fund;//펀드
//	@Column
//	private int	insurance;//보험
//	@Column
//	private int	invest;//투자
	


	@CreatedDate
	private LocalDate createDate;

}
package com.payStyle.model;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name="deposits")
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
	private String payMethod;
	
	@Column
	private String category;
	
	@Column
	private int profit;
	
	@Column
	private Date inputdate;

	@DateTimeFormat(pattern="yyyy/MM/dd")
	@CreationTimestamp
	private Timestamp createDate;

}
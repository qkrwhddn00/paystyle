package com.payStyle.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name="accounts")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@SequenceGenerator(
		name="USER_SEQ_GENERATOR_A"
		, sequenceName= "USER_SEQ_A"
		, initialValue= 1
		, allocationSize= 1
		)
public class Account {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="USER_SEQ_GENERATOR_A")
	private int id;
	
	@ManyToOne
	@JoinColumn(name="userId")
	private Users users;
	

	@Column(nullable=false, length=30)
	private String payMethod;
	

	@Column(length=30)
	private int accountNum;
	
	@Column
	private int balance;
	@Column
	private Date inputdate;
}

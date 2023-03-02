package com.payStyle.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name="Notice")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@SequenceGenerator(
		name = "NOTICE_SEQ_GENERATOR"
		, sequenceName = "NOTICE_SEQ"
		, initialValue = 1
		, allocationSize = 1
		)

public class Notice {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="NOTICE_SEQ_GENERATOR")
	private int id;
	@Column(nullable=false, length=30)
	private String title; //글제목
	@Lob
	private String contents;//내용
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="userid")
	private Users user; //게시자	
	
	@Column(nullable=false, length=30)
	private String users;
	
	
	@CreationTimestamp
	private Timestamp createDate;  //작성일
	@ColumnDefault("0")
	private int count;

}

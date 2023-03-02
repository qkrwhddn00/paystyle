package com.payStyle.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name="users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@SequenceGenerator(
		name="USER_SEQ_GENERATOR"
		, sequenceName= "USER_SEQ"
		, initialValue= 1
		, allocationSize= 1
		)
public class Users {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="USER_SEQ_GENERATOR")
	private int id;
	
	private String oauth;
	
	@Column(nullable=true, length=30)
	private String userid;		
	
	@Column(nullable=true, length=30)
	private String username;
	
	@Column(nullable=true, length=100)
	private String password;
	
	@Column(nullable=true, length=50)
	private String email;
	
	@ColumnDefault("'user'")
	@Enumerated(EnumType.STRING)
	private RoleType roles;
	
	@JsonFormat(pattern = "YYYY-MM-dd")
	@CreationTimestamp
	private Timestamp createDate;
	
}


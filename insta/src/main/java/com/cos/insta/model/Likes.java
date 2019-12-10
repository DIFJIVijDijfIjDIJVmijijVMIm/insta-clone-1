package com.cos.insta.model;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

//ORM, IOC, DI 에 대해 공부 하기

@Data
@Entity
public class Likes {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JoinColumn(name="userId")
	@JsonIgnoreProperties({"images", "password", "name", "website", "bio", "email", "phone", "gender", "createDate", "updateDate"})	//password는 들고오지 않는다.
	private User user;
	
	@ManyToOne
	@JoinColumn(name="imageId")	//원래 기본 이름은 image_id인데 이거 꼴보기 싫어서 joinColumn넣는거임
	@JsonIgnoreProperties({"tag", "user", "likes"})
	private Image image;
		
	@CreationTimestamp
	private Timestamp createDate;
	@CreationTimestamp
	private Timestamp updateDate;
}

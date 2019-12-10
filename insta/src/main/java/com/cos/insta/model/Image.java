package com.cos.insta.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;

@Data
@Entity
public class Image {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String location;	//사진 찍은 위치
	private String caption;		//사진 설명
	private String postImage;	//포스팅 사진 경로 + 이름
	
	@ManyToOne //여기에 mappedby를 걸어주면 외래키로 만들어지지 않는다.
	@JoinColumn(name="userId")
	@JsonIgnoreProperties({"password", "images"})	//user객체를 들고올 때 password는 가지고 오지 않는다. (보안)
	private User user;
	
	//(1) Tag List
	@OneToMany(mappedBy = "image")
	@JsonManagedReference
	private List<Tag> tags = new ArrayList<>();
	
	//(2) Like List
	@OneToMany(mappedBy = "image")
	private List<Likes> likes = new ArrayList<>();
	
	@Transient	// DB에 영향을 미치지 않게 한다. (DB에 안 만들어 진다.)
	private int likeCount;
	
	@CreationTimestamp
	private Timestamp createDate;
	@CreationTimestamp
	private Timestamp updateDate;
	
}

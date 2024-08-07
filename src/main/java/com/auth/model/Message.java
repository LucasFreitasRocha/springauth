package com.auth.model;



import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Message {
	
	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	private Long id;
	
	private String content;
	
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private Usuario user;

	public Message() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Message(String content, Usuario user) {
		this.content = content;
		this.user = user;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Usuario getUser() {
		return user;
	}

	public void setUser(Usuario user) {
		this.user = user;
	}
	
	

}

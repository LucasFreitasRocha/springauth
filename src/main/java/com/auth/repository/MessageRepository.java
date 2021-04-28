package com.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.auth.model.Message;

public interface MessageRepository  extends JpaRepository<Message, Long> {
	
	
}


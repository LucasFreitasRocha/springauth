package com.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auth.dto.in.MessageDto;
import com.auth.model.Message;
import com.auth.model.User;
import com.auth.repository.MessageRepository;

@Service
public class MessageService {

	@Autowired private UserService userService;
	@Autowired private MessageRepository repo;
	
	public Message create(String token, MessageDto messageDto) {
		User user = userService.findByToken(token.substring(7, token.length()));
		Message message = new Message(messageDto.getContent(), user);
		return repo.save(message);
		
	}

}

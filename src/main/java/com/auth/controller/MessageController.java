package com.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auth.dto.in.MessageDto;
import com.auth.model.Message;
import com.auth.service.MessageService;

@RestController
@RequestMapping("/message")
public class MessageController {
	
	@Autowired private MessageService service;
	
	@PostMapping
	public ResponseEntity<Message> createMessage(@RequestHeader("Authorization") String token, @RequestBody MessageDto messageDto ){
	  Message message = service.create(token, messageDto);
		return ResponseEntity.ok(message);
		
	}
	

}

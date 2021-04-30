package com.auth.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	
	@GetMapping
	public ResponseEntity<List<Message>> findAll(){
		return ResponseEntity.ok(service.findAll());
	}
	
	@PostMapping
	public ResponseEntity<Message> createMessage(@RequestHeader("Authorization") String token, @RequestBody MessageDto messageDto ){
	  Message message = service.create(token, messageDto);
		return ResponseEntity.ok(message);
		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Void> updateMessage(@RequestHeader("Authorization") String token, @PathVariable Long id , @RequestBody MessageDto messageDto){
		service.update(token,id, messageDto);
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteMessage(@RequestHeader("Authorization") String token, @PathVariable Long id){
		
		service.delete(id);
		return ResponseEntity.noContent().build();
		
	}

}

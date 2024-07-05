package com.auth.controller;

import com.auth.dto.in.MessageDto;
import com.auth.model.Message;
import com.auth.service.MessageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/message")
@AllArgsConstructor
public class MessageController {
	
	private final MessageService service;
	
	@GetMapping
	public ResponseEntity<List<Message>> findAll(){
		return ResponseEntity.ok(service.findAll());
	}
	@Operation(summary = "create Message",security = { @SecurityRequirement(name = "bearer-key") })
	@PostMapping
	public ResponseEntity<Message> createMessage(@Parameter(hidden = true) @RequestHeader("Authorization") String token, @RequestBody MessageDto messageDto ){
	  Message message = service.create(token, messageDto);
		return ResponseEntity.ok(message);
	}
	
	@PutMapping("/{id}")
	@Operation(summary = "edit Message",security = { @SecurityRequirement(name = "bearer-key") })
	public ResponseEntity<Void> updateMessage(@Parameter(hidden = true) @RequestHeader("Authorization") String token, @PathVariable Long id , @RequestBody MessageDto messageDto){
		service.update(token,id, messageDto);
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/{id}")
	@Operation(summary = "delete Message",security = { @SecurityRequirement(name = "bearer-key") })
	public ResponseEntity<Void> deleteMessage(@Parameter(hidden = true) @RequestHeader("Authorization") String token, @PathVariable Long id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

}

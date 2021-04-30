package com.auth.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auth.dto.in.MessageDto;
import com.auth.exception.BadRequestException;
import com.auth.exception.ObjectNotFoundException;
import com.auth.model.Message;
import com.auth.model.Usuario;
import com.auth.repository.MessageRepository;

@Service
public class MessageService {

	@Autowired private UserService userService;
	@Autowired private MessageRepository repo;
	public List<Message> findAll(){
		return repo.findAll();
	}
	
	public Message create(String token, MessageDto messageDto) {
		Usuario user = userService.findByToken(token.substring(7, token.length()));
		Message message = new Message(messageDto.getContent(), user);
		return repo.save(message);
		
	}

	
	public void delete(Long id) {
		Message message = find(id);
		repo.delete(message);
	}


	private Message find(Long id) {
		 Optional<Message> obj = repo.findById(id);
			return obj.orElseThrow(() -> new ObjectNotFoundException(
					"Messagem não encontrada com esse id: " + id));
	}

	public void update(String token, Long id, MessageDto messageDto) {
		Usuario user = userService.findByToken(token.substring(7, token.length()));
		Message message = find(id);
		if(message.getUser().equals(user)) {
			message.setContent(messageDto.getContent());
			repo.save(message);
			
			
		}else {
			user.getAuthorities().forEach(a -> {
				  System.out.println("roles" + a.getAuthority());
				  if(a.getAuthority().equals("ROLE_MODERADOR")) {
					  message.setContent(messageDto.getContent());
						repo.save(message);
				  }else {
					  throw new BadRequestException("Você não pode atualizar essa mensagem");
				  }
				});
			
		}
		
	}

}

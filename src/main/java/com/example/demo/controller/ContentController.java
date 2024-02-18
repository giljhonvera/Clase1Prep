package com.example.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.model.Content;
import com.example.demo.repository.ContentRepository;

@RestController
@RequestMapping("/api/content")
public class ContentController {

		private final ContentRepository repository;

		public ContentController(ContentRepository respository) {
			super();
			this.repository = respository;
		}
		
		@GetMapping("")
		public List<Content> findAll(){
			return repository.findAll();
		}
		
		@GetMapping("/{id}")
		public Content findById(@PathVariable Integer id) {
			return repository.findById(id)
					.orElseThrow(
							()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Content not found!"));
		}
		
		@ResponseStatus(HttpStatus.CREATED)
		@PostMapping("")
		public void create(@RequestBody Content content) {
			if(repository.existsById(content.id())) {
				throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE,"Content with id repeated!");
			}
			repository.save(content);
		}
		
		@ResponseStatus(HttpStatus.ACCEPTED)
		@PutMapping("/{id}")
		public void update(@RequestBody Content content,
				@PathVariable Integer id) {
			if(!repository.existsById(id)) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND,
						"Content not found!");
			}
			repository.save(content);
		}
		
		@ResponseStatus(HttpStatus.ACCEPTED)
		@DeleteMapping("/{id}")
		public void delete(@PathVariable Integer id) {
			if(!repository.existsById(id)) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND,
						"Content not found!");
			}
			repository.delete(id);
		}	
}

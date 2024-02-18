package com.example.demo.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.example.demo.model.Content;

@Repository
public class ContentRepository {
	
	private final List<Content> contentList = new ArrayList<>();
	
	public List<Content> findAll(){
		return contentList;
	}
	
	public Optional<Content> findById(Integer id){
		return contentList.stream().filter(c -> c.id().equals(id)).findFirst();
	}
	
	public void save(Content content) {
		contentList.removeIf(c -> c.id().equals(content.id()));
		contentList.add(content);
	}
	
	public boolean existsById(Integer id) {
		return contentList.stream().filter(c -> c.id().equals(id)).count() == 1;
	}
	
	public void delete(Integer id) {
		contentList.removeIf(c -> c.id().equals(id));
	}
	
}

package com.devracoon.redis.service;

import com.devracoon.redis.controller.ContentDTO;
import com.devracoon.redis.entity.Content;
import com.devracoon.redis.repository.ContentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ContentServiceImpl {

    private final ContentRepository repo;

    @Transactional
    @CacheEvict(value="content" , allEntries=true)
    public ContentDTO addContent(ContentDTO dto) throws Exception{
        Content content = Content.builder().contentName(dto.getContentName()).contentType(dto.getContentType()).build();
        return ContentDTO.createDTO(repo.save(content));
    }

    @Transactional
    public void removeContent(String contentId) throws Exception{
        repo.deleteById(contentId);
    }

    @Cacheable(value = "content")
    public List<ContentDTO> findContent(String contentName) throws Exception{
        List<ContentDTO> contentDTOs = new ArrayList<ContentDTO>();
        List<Content> contents = repo.findByContentName(contentName);
        for(Content content : contents){
            contentDTOs.add(ContentDTO.createDTO(content));
        }
        return contentDTOs;
    }
}

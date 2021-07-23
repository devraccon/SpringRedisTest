package com.devracoon.redis.service;

import com.devracoon.redis.controller.ContentDTO;
import com.devracoon.redis.entity.Content;
import com.devracoon.redis.repository.ContentRepository;
import com.devracoon.redis.util.RedisListCacheEvict;
import com.devracoon.redis.util.RedisListCacheable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ContentServiceImpl {

    private final ContentRepository repo;

    @Transactional
    @RedisListCacheEvict(value="CONTENTLIST")
    public ContentDTO addContent(ContentDTO dto) throws Exception{
        Content content = Content.builder().contentName(dto.getContentName()).contentType(dto.getContentType()).build();
        return ContentDTO.createDTO(repo.save(content));
    }

//    @Transactional
//    @CacheEvict(value = "content" , key="{#p0.contentId}")
    @RedisListCacheEvict(value="CONTENTLIST")
    public void removeContent(ContentDTO dto) throws Exception{
        repo.deleteById(dto.getContentId());
    }

    @Cacheable(value = "content" , key="{#p0.contentId}")
    public ContentDTO findContent(ContentDTO dto) throws Exception{
        log.info("start findContent .. ");
        Content content = repo.findById(dto.getContentId()).orElse(null);
//        List<ContentDTO> contentDTOs = contents.stream().map(c -> ContentDTO.createDTO(c)).collect(Collectors.toList());
        ContentDTO contentDTO = ContentDTO.createDTO(content);
        return contentDTO;
    }

    @RedisListCacheable(value="CONTENTLIST")
    public List<ContentDTO> findAll() throws Exception{
        List<ContentDTO> contentDTOs = new ArrayList<ContentDTO>();
        List<Content> contents = repo.findAll();
        for(Content content : contents){
            contentDTOs.add(ContentDTO.createDTO(content));
        }
        return contentDTOs;
    }
}

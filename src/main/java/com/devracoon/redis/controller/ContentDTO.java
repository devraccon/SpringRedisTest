package com.devracoon.redis.controller;

import com.devracoon.redis.entity.Content;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@RedisHash("contentDTO")
public class ContentDTO {
    @Id
    String contentId;
    String contentName;
    String contentType;

    public static ContentDTO createDTO(Content content){
        return ContentDTO.builder()
                .contentId(content.getContentId())
                .contentName(content.getContentName())
                .contentType(content.getContentType())
                .build();
    }

}

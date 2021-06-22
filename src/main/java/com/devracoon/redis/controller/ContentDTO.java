package com.devracoon.redis.controller;

import com.devracoon.redis.entity.Content;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@RedisHash("contentDTO")
public class ContentDTO implements Serializable {

    private static final long serialVersionUID = 3077465140312258030L;

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

package com.devracoon.redis.repository;

import com.devracoon.redis.entity.Content;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContentRepository extends JpaRepository<Content, String> {
    @Query("SELECT c FROM Content c WHERE c.contentName like %:contentName%")
    public List<Content> findByContentName(@Param("contentName") String contentName);
}

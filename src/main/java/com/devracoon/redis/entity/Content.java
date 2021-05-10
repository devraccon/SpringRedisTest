package com.devracoon.redis.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
public class Content implements Serializable {

    private static final long serialVersionUID = 3077465140312258030L;

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(columnDefinition="VARCHAR(255)")
    private String contentId;

    @NonNull
    @Column(columnDefinition="VARCHAR(255)")
    private String contentName;

    @NonNull
    @Column(columnDefinition="VARCHAR(255)")
    private String contentType;

}

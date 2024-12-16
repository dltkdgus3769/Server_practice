package com.busanit501.boot501.domain;


import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
abstract public class BaseEntity {

    @CreatedDate
    @Column(name="regDate",updatable = false)
    private LocalDateTime regDate;

    @LastModifiedDate
    @Column(name="modDate",updatable = true)
    private LocalDateTime modDate;

}

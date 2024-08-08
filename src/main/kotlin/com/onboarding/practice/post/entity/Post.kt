package com.onboarding.practice.post.entity

import jakarta.persistence.*
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@Entity
@EntityListeners(AuditingEntityListener::class)
class Post (
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id:Long ?= null,

    @Column(nullable = true, length = 200)
    var title:String?,

    @Column(nullable = true, length = 1000)
    var content:String?,

    @Column(nullable = false)
    var createdBy: String,

    @Column(nullable = false)
    var updatedBy: String,

    @Column
    var deleted: Boolean = false,

    @CreatedDate
    @Column(nullable = false, updatable = false)
    var createdAt: LocalDateTime?= null,

    @LastModifiedDate
    @Column(nullable = false)
    var updateAt: LocalDateTime? =null,

    @OneToMany(mappedBy = "post", fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    var comments: List<Comment> = mutableListOf()
){
    init {
        title?.let {
            require(it.length <= 200) { "Title length must be 200 characters or less" }
        }
        content?.let {
            require(it.length <= 1000) { "Content length must be 1000 characters or less" }
        }
    }
}

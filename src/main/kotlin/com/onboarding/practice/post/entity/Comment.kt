package com.onboarding.practice.post.entity

import jakarta.persistence.*

@Entity
class Comment (
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id:Long ?= null,

    @Column(nullable = false)
    var content:String,

    @Column(nullable = false)
    var createdBy: String,

    @Column(nullable = false)
    var updatedBy: String,

    @ManyToOne
    @JoinColumn(name = "post_id")
    val post: Post
)
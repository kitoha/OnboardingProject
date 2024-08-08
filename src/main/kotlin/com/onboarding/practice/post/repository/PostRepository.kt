package com.onboarding.practice.post.repository

import com.onboarding.practice.post.entity.Post
import org.springframework.data.jpa.repository.JpaRepository

interface PostRepository : JpaRepository<Post,Long> {
}
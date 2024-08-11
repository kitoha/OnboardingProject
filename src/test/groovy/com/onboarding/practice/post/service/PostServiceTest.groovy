package com.onboarding.practice.post.service

import com.onboarding.practice.post.dto.PostDto
import com.onboarding.practice.post.entity.Comment
import com.onboarding.practice.post.entity.Post
import com.onboarding.practice.post.repository.PostRepository
import spock.lang.Specification

import java.time.LocalDateTime

class PostServiceTest extends Specification {
    PostRepository postRepository = Mock()
    PostService postService = new PostService(postRepository)

    def "생성일 기준 10일 이후 수정불가 테스트"() {
        given:
        LocalDateTime currentTime = LocalDateTime.now().minusDays(11)
        Post post = new Post(
                1L,
                "제목",
                "내용",
                "test",
                "test",
                false,
                currentTime,
                currentTime,
        new ArrayList<Comment>())

        PostDto postDto = new PostDto("제목", "내용")

        postRepository.findById(_) >> Optional.of(post)

        when:
        def result = postService.updatePost(1L, postDto, "test")

        then:
        result == false
    }
}

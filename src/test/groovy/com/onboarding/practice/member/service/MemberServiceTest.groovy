package com.onboarding.practice.member.service

import com.onboarding.practice.authority.provider.JwtTokenProvider
import com.onboarding.practice.member.dto.MemberDto
import com.onboarding.practice.member.repository.MemberRepository
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.crypto.password.PasswordEncoder
import spock.lang.Specification

class MemberServiceTest extends Specification {
    MemberRepository memberRepository = Mock()
    JwtTokenProvider jwtTokenProvider = Mock()
    AuthenticationManager authenticationManager = Mock()
    PasswordEncoder passwordEncoder = Mock()
    MemberService memberService = new MemberService(memberRepository, jwtTokenProvider, authenticationManager, passwordEncoder)

    def "유효성 검증 테스트"() {
        given:
        MemberDto memberDto = new MemberDto(EMAIL, PASSWORD, PHONE_NUMBER, NAME)

        when:
        memberService.signUp(memberDto)

        then:
        def exception = thrown(IllegalArgumentException.class)
        exception.getMessage() == RESULT

        where:
        EMAIL            || PASSWORD        || PHONE_NUMBER    || NAME     || RESULT
        "testnaver.com"  || "abcdef12345!@" || "010-1234-5678" || "test"   || "이메일 형식이 잘못되었습니다."
        "test@naver.com" || "abcdef12345!@" || "01012345678"   || "test"   || "휴대폰 번호 형식이 잘못되었습니다."
        "test@naver.com" || "abcdef12345!@" || "010-1234-5678" || "test!!" || "이름 형식이 잘못되었습니다."
        "test@naver.com" || "abcdef123!"    || "010-1234-5678" || "test"   || "비밀번호 형식이 잘못되었습니다."
    }
}


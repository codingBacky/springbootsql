package com.backy.repository;

import com.backy.dto.MemberFormDto;
import com.backy.entity.Member;
import com.backy.service.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
@TestPropertySource(locations = "classpath:application-test.properties")
public class MemberServiceTest {
    @Autowired
    MemberService memberService;
    @Autowired
    PasswordEncoder passwordEncoder;

    public Member createMember(){
        MemberFormDto memberFormDto = new MemberFormDto();
        memberFormDto.setEmail("back1@do.com");
        memberFormDto.setName("backy");
        memberFormDto.setAddress("suwon");
        memberFormDto.setPassword("1234");
        return MemberService.createMember(memberFormDto,passwordEncoder);
    }

    @Test
    @DisplayName("회원가입테스트")
    public void saveMemberTest(){
        Member member = createMember();
        Member saveMember = memberService.saveMember(member);
        assertEquals(member.getEmail(), saveMember.getEmail());
        assertEquals(member.getName(), saveMember.getName());
        assertEquals(member.getAddress(), saveMember.getAddress());
        assertEquals(member.getPassword(), saveMember.getPassword());
        assertEquals(member.getRole(), saveMember.getRole());
        System.out.println(member);
        System.out.println(saveMember);
    }

    @Test
    @DisplayName("중복 회원 가입 테스트")
    public void saveDuplicateMemberTest(){
        Member mem1 = createMember();
        Member mem2 = createMember();
        memberService.saveMember(mem1);

        Throwable e = assertThrows(IllegalStateException.class, ()->{
            memberService.saveMember(mem2);});
        assertEquals("이미 가입된 회원입니다.", e.getMessage());
    }
}

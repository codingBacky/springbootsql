/*
package com.backy.repository;

import com.backy.dto.MemberFormDto;
import com.backy.entity.Cart;
import com.backy.entity.Member;
import com.backy.service.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
@SpringBootTest
@Transactional

@TestPropertySource(locations = "classpath:application.properties")
class CartRepositoryTest {
    @Autowired
    CartRepository cartRepository;
    @PersistenceContext
    EntityManager em;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Test
    public void notNullTest(){
        assertNotNull(passwordEncoder);
    }
    public Member createMember(){
        MemberFormDto dto = new MemberFormDto();
        dto.setAddress("서울시 강남구 대치동");
        dto.setPassword("1234");
        dto.setName("둥둥");
        dto.setEmail("dung1@a.com");
        return MemberService.createMember(dto,passwordEncoder);
    }
    @Test
    public void findCartAndMemberTest(){
        Member member = createMember();
        memberRepository.save(member);

        Cart cart = new Cart();
        cart.setMember(member);

        cartRepository.save(cart);
        em.flush();
        em.clear();

        Cart savedCart = cartRepository.findById(cart.getId())
                .orElseThrow(EntityNotFoundException::new);
        assertEquals(savedCart.getMember().getId(), member.getId());

    }
}*/

package com.backy.entity;

import com.backy.constant.Role;
import com.backy.dto.MemberFormDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;

@Entity
@Getter @Setter @ToString
@Table(name = "member")
public class Member {
    @Id
    @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    @Column(unique = true)
    private String email;
    //암호화 되는 컬럼의 크기는 최소 50 사이즈 이상으로 설정 default 255

    private String password;

    private String address;
    @Enumerated(EnumType.STRING)
    private Role role;


}

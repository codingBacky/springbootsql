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
@Table(name = "members")
public class Member {
    @Id
    @Column(name = "MEMID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "MEMNAME")
    private String name;
    @Column(unique = true)
    private String email;
    //암호화 되는 컬럼의 크기는 최소 50 사이즈 이상으로 설정 default 255
    @Column(name = "MEMPWD")
    private String password;
    @Column(name = "DETAILADDRESS")
    private String address;
    @Enumerated(EnumType.STRING)
    private Role role;


}

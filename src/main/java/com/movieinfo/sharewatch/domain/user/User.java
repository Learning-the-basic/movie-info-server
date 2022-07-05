package com.movieinfo.sharewatch.domain.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.movieinfo.sharewatch.domain.BaseTimeEntity;
import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;

@Getter
@NoArgsConstructor
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = "email")
})
@Entity
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(nullable = false)
    private String name;

    @Email
    @Column(nullable = false)
    private String email;

    @Column
    private String imageUrl;

    @JsonIgnore
    private String password;

    @NotNull
    @Enumerated(EnumType.STRING)
    private AuthProvider provider;

    private String providerId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Builder
    public User(String name, String email, String imageUrl, String password, AuthProvider provider, String providerId, Role role) {
        this.name = name;
        this.email = email;
        this.imageUrl = imageUrl;
        this.password = password;
        this.provider = provider;
        this.providerId = providerId;
        this.role = role;
    }

    public User update(String name, String email, String imageUrl) {
        this.name = name;
        this.email=email;
        this.imageUrl=imageUrl;

        return this;
    }

    public String getRoleKey() {
        return this.role.getKey();
    }

}
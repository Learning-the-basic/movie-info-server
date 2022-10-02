package com.movieinfo.sharewatch.domain.subscription;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.movieinfo.sharewatch.domain.user.User;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.ALL;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class SubscriptionGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sub_group_id")
    private Long subGroupId;

    @JsonIgnore
    @OneToMany(mappedBy = "subGroup")
    private List<UserSubGroup> userList = new ArrayList<>();

//    @OneToMany(targetEntity = User.class, fetch = FetchType.EAGER)
//    @JoinColumn(name = "user_id")
//    private List<User> userList = new ArrayList<>();

    @JsonIgnore
    @OneToOne(mappedBy = "subGroup", cascade = ALL)
    private Subscription subscription;

//    public void addUser(User user){userList.add(user);
//        System.out.println("======================== 유저 등록 함수 실행");}

    public void addUser(UserSubGroup us){userList.add(us);
        System.out.println("======================== 유저 등록 함수 실행");}

    //public void deleteUser(User user){UserList.remove(user);}

    public void addGroup(Subscription subscription) {
        this.subscription = subscription;
    }
}

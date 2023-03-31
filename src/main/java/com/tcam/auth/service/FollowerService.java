package com.tcam.auth.service;

import com.tcam.auth.model.Follower;
import com.tcam.auth.model.User;
import com.tcam.auth.repository.FollowerRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class FollowerService {
    private final FollowerRepository followerRepository;


    public void follow(Long follower, Long followed) {
        Follower newFollower = Follower.builder()
                .follower(User.builder().id(follower).build())
                .followed(User.builder().id(followed).build())
                .createdAt(LocalDateTime.now())
                .build();
        followerRepository.save(newFollower);
    }

    @Transactional
    public void unfollow(Long follower, Long followed) {
      User userName = User.builder().id(follower).build();
      User followedUser = User.builder().id(followed).build();
      followerRepository.deleteByFollower(userName, followedUser);
    }
}

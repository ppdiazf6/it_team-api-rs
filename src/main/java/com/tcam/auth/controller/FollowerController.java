package com.tcam.auth.controller;

import com.tcam.auth.domain.FollowRequest;
import com.tcam.auth.service.FollowerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v001/followers")
@RequiredArgsConstructor
public class FollowerController {

    private final FollowerService followerService;



    @PostMapping
    public ResponseEntity<Void> followUser(@RequestBody FollowRequest followRequest) {
        Long follower = followRequest.getFollower();
        Long followed = followRequest.getFollowed();

        followerService.follow(follower, followed);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<Void> unfollowUser(@RequestBody FollowRequest followRequest) {
        Long follower = followRequest.getFollower();
        Long followed = followRequest.getFollowed();

        followerService.unfollow(follower, followed);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

package com.tcam.auth.repository;

import com.tcam.auth.model.Follower;
import com.tcam.auth.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface FollowerRepository extends JpaRepository<Follower, Long> {

    @Modifying
    @Query("delete from Follower f where f.followed = ?1 and f.follower = ?2")
    void deleteByFollower(User followed,User follower);

}

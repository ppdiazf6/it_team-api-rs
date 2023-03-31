package com.tcam.auth.repository;

import com.tcam.auth.model.Publication;
import com.tcam.auth.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PublicationRepository extends JpaRepository<Publication, Long> {

    List<Publication> findAllByUser(User user);
}

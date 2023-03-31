package com.tcam.auth.controller;

import com.tcam.auth.domain.PublicationRequestDTO;
import com.tcam.auth.domain.PublicationResponseDTO;
import com.tcam.auth.model.Publication;
import com.tcam.auth.model.User;
import com.tcam.auth.service.PublicationService;
import com.tcam.auth.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v001/publications")
@RequiredArgsConstructor
public class PublicationController {

    private final PublicationService publicationService;
    private final UserService userService;


    @GetMapping("/user/{userId}")
    public ResponseEntity<List<PublicationResponseDTO>> getPublicationsByUser(@PathVariable Long userId) {
       User user = userService.getUserById(userId);
        List<PublicationResponseDTO> publications = publicationService.getPublicationsByUser(user);

        return new ResponseEntity<>(publications, HttpStatus.OK);

    }

    @GetMapping("/followed/{userId}")
    public ResponseEntity<List<Publication>> getPublicationsByFollowedUsers(@PathVariable Long userFollowerId) {
        User user = userService.getUserById(userFollowerId);
        List<Publication> publications = publicationService.getPublicationsByFollowedUsers(user);

        return new ResponseEntity<>(publications, HttpStatus.OK);
    }

    @PostMapping("/{userId}")
    public ResponseEntity<PublicationResponseDTO> createPublication(@PathVariable Long userId,
                                                         @RequestBody PublicationRequestDTO content) {
        User user = userService.getUserById(userId);
        PublicationResponseDTO publication = publicationService.createPublication(user, content);
        return new ResponseEntity<PublicationResponseDTO>(publication, HttpStatus.CREATED);

    }
}

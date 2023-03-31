package com.tcam.auth.service;

import com.tcam.auth.domain.PublicationRequestDTO;
import com.tcam.auth.domain.PublicationResponseDTO;
import com.tcam.auth.model.Publication;
import com.tcam.auth.model.User;
import com.tcam.auth.repository.PublicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PublicationService {

    private final PublicationRepository publicationRepository;

    public List<PublicationResponseDTO> getPublicationsByUser(User user) {
        List<Publication> listPublication =  publicationRepository.findAllByUser(user);
        return  listPublication.stream().map(publication -> {
            return PublicationResponseDTO.builder()
                    .publicationId(publication.getId())
                    .userId(publication.getUser().getId())
                    .content(publication.getContent())
                    .image(publication.getImage())
                    .title(publication.getTitle()).build();
        }).toList();

    }

    public List<Publication> getPublicationsByFollowedUsers(User user) {
        return publicationRepository.findAllByUser(user);
    }

    public PublicationResponseDTO createPublication(User user, PublicationRequestDTO content) {
        Publication newPublication = Publication.builder()
                .user(user)
                .content(content.getContent())
                .title(content.getTitle())
                .image(content.getImage())
                .createdAt(LocalDateTime.now())
                .build();
        publicationRepository.save(newPublication);
        return PublicationResponseDTO.builder()
                .content(newPublication.getContent())
                .image(newPublication.getImage())
                .title(newPublication.getTitle())
                .userId(newPublication.getUser().getId())
                .publicationId(newPublication.getId())
                .build();
    }
}

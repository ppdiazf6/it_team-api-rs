package com.tcam.auth.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PublicationResponseDTO {
    private String content;
    private String title;
    private String image;
    private Long userId;
    private Long publicationId;
}

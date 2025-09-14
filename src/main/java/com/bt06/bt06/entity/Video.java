package com.bt06.bt06.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="video")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Video {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long videoId;

    @Column(name="title", columnDefinition = "NVARCHAR(255)")
    private String title;

    @Column(name="description", columnDefinition = "NVARCHAR(255)")
    private String description;

    // Đường dẫn ảnh public (ví dụ: /images/abc.jpg)
    @Column(name="thumbnail", columnDefinition = "NVARCHAR(255)")
    private String thumbnail;

    // Link nguồn video (YouTube/Vimeo/internal…)
    @Column(name="source_url", columnDefinition = "NVARCHAR(255)")
    private String sourceUrl;
}

package com.bt06.bt06.repository;

import com.bt06.bt06.entity.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VideoRepository extends JpaRepository<Video,Integer> {
    List<Video> findAllBy();
    List<Video> findVideoByTitleContainingIgnoreCase(String title);
    Video findVideoByVideoId(Long videoId);
}

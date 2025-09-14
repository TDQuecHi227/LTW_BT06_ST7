package com.bt06.bt06.service.impl;

import com.bt06.bt06.entity.Video;
import com.bt06.bt06.repository.VideoRepository;
import com.bt06.bt06.service.VideoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class VideoServiceImpl implements VideoService {
    private final VideoRepository  videoRepository;
    @Override
    public List<Video> getAllVideos() {
        return videoRepository.findAllBy();
    }

    @Override
    public List<Video> searchVideoByTitle(String title) {
        return videoRepository.findVideoByTitleContainingIgnoreCase(title);
    }

    @Override
    public void saveVideo(Video video) {
        videoRepository.save(video);
    }

    @Override
    public void deleteVideo(Video video) {
        videoRepository.delete(video);
    }

    @Override
    public Video getVideoByVideoId(Long videoId) {
        return videoRepository.findVideoByVideoId(videoId);
    }
}

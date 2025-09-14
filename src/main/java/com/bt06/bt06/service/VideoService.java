package com.bt06.bt06.service;

import com.bt06.bt06.entity.Video;

import java.util.List;

public interface VideoService {
    List<Video> getAllVideos();
    List<Video> searchVideoByTitle(String title);
    void saveVideo(Video video);
    void deleteVideo(Video video);
    Video getVideoByVideoId(Long videoId);
}

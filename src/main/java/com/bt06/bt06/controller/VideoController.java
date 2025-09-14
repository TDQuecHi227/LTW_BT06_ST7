package com.bt06.bt06.controller;

import com.bt06.bt06.entity.Category;
import com.bt06.bt06.entity.Video;
import com.bt06.bt06.repository.VideoRepository;
import com.bt06.bt06.service.VideoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("admin/videos")
public class VideoController {
    private final VideoService  videoService;
    @GetMapping("")
    public String getVideo(Model model, @RequestParam(value="q", required = false) String keyword){
        List<Video> videos;
        if(keyword != null && !keyword.trim().equals("")){
            videos = videoService.searchVideoByTitle(keyword);
            model.addAttribute("q",  keyword);
        }
        else{
            videos = videoService.getAllVideos();
        }
        model.addAttribute("videos",videos);
        return "video/video";
    }
    @GetMapping("add")
    public String getAdd(Model model){
        return "video/add";
    }
    @PostMapping("add")
    public String addVideo(Model model,
                           @RequestParam("title") String title,
                           @RequestParam("sourceUrl") String sourceUrl,
                           @RequestParam("description") String  description,
                           @RequestParam("thumbnailFile")MultipartFile thumbnail) throws IOException{
        String thumbnailFile = null;
        if (!thumbnail.isEmpty()) {
            thumbnailFile = thumbnail.getOriginalFilename();
            Path uploadDir = Paths.get("images/video");
            Files.createDirectories(uploadDir);
            thumbnail.transferTo(uploadDir.resolve(thumbnailFile));
        }
        Video video = new Video();
        video.setTitle(title);
        video.setSourceUrl(sourceUrl);
        video.setDescription(description);
        video.setThumbnail(thumbnailFile);
        videoService.saveVideo(video);
        return "redirect:/admin/videos";
    }
    @GetMapping("delete/{id}")
    public String getDelete(Model model, @PathVariable("id") long id){
        Video video = videoService.getVideoByVideoId(id);
        videoService.deleteVideo(video);
        return "redirect:/admin/videos";
    }
    @GetMapping("edit/{id}")
    public String getEdit(Model model, @PathVariable("id") long id){
        Video video = videoService.getVideoByVideoId(id);
        model.addAttribute("video", video);
        return "video/edit";
    }
    @PostMapping("edit/{id}")
    public String postEdit(Model model,
                           @PathVariable("id") long id,
                           @RequestParam("title") String title,
                           @RequestParam("sourceUrl") String sourceUrl,
                           @RequestParam("description") String description,
                           @RequestParam("thumbnailFile") MultipartFile thumbnail) throws IOException {
        String thumbnailFile = null;
        if (!thumbnail.isEmpty()) {
            thumbnailFile = thumbnail.getOriginalFilename();
            Path uploadDir = Paths.get("images/video");
            Files.createDirectories(uploadDir);
            thumbnail.transferTo(uploadDir.resolve(thumbnailFile));
        }
        Video video = videoService.getVideoByVideoId(id);
        video.setTitle(title);
        video.setSourceUrl(sourceUrl);
        video.setDescription(description);
        if(thumbnailFile != null && !thumbnailFile.isEmpty()){
            video.setThumbnail(thumbnailFile);
        }
        videoService.saveVideo(video);
        return "redirect:/admin/videos";
    }
}

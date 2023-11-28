package com.example.image_manager.controller;

import com.example.image_manager.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/image")
public class ImageController {

    @Autowired
    ImageService imageService;


    @GetMapping("/list")
    //@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_EMPLOYEE') or hasRole('ROLE_USER') ")
    public ResponseEntity<?> listImage(@RequestParam("index-page") int indexPage, @RequestParam("size") int size) {
        return ResponseEntity.ok(imageService.getAllImages(indexPage, size));
    }

}

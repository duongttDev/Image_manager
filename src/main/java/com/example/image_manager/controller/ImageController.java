package com.example.image_manager.controller;

import com.example.image_manager.request.ImageRequest;
import com.example.image_manager.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{id}")
    //@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_EMPLOYEE') or hasRole('ROLE_USER') ")
    public ResponseEntity<?> getImageDetail(@PathVariable("id") Long id) {
        return ResponseEntity.ok(imageService.getDetailImage(id));
    }

    @PutMapping("/add")
    //@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_EMPLOYEE') or hasRole('ROLE_USER') ")
    public ResponseEntity<?> addImage(@RequestBody ImageRequest imageRequest) {
        return ResponseEntity.ok(imageService.addImage(imageRequest));
    }

}

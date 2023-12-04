package com.example.image_manager.request;


import com.example.image_manager.dto.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ImageRequest {

    private Long id;

    private String name;

    private int view;

    private String url;

    private String size;

    private int tagId;

    private String format;

    private String description;

}

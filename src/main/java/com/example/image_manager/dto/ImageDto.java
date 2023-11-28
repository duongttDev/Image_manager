package com.example.image_manager.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ImageDto extends BaseDto {

    private String name;

    private int view;

    private String url;

    private String size;

    private int tagId;

    private String Format;

    private String Description;

}

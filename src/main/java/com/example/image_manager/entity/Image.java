package com.example.image_manager.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "image")
public class Image extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "view")
    private int view;

    @Column(name = "url")
    private String url;

    @Column(name = "size")
    private String size;

    @Column(name = "tagId")
    private int tagId;

    @Column(name = "format")
    private String format;

    @Column(name = "description")
    private String description;

}

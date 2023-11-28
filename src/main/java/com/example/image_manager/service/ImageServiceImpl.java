package com.example.image_manager.service;

import com.example.image_manager.dto.ImageDto;
import com.example.image_manager.entity.Image;
import com.example.image_manager.exception.BusinessException;
import com.example.image_manager.repository.ImageRepository;
import com.example.image_manager.response.CustomPageResponse;
import com.example.image_manager.response.EntityCustomResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    ImageRepository imageRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public EntityCustomResponse getAllImages(int indexPage, int size) {
        CustomPageResponse<ImageDto> pageResponse = null;
        int page = indexPage - 1;
        Pageable pageable = PageRequest.of(page, size);

        try {
            Page<Image> pageImage = imageRepository.findAll(pageable);
            if (pageImage.getTotalElements() == 0) {
                throw new BusinessException(404, "No Date");
            }
            List<ImageDto> imageDtoList = pageImage.getContent()
                    .stream()
                    .map(item -> modelMapper.map(item, ImageDto.class))
                    .collect(Collectors.toList());

            pageResponse = new CustomPageResponse<>(imageDtoList, indexPage, size,
                    pageImage.getTotalElements(), pageImage.getTotalPages());

        } catch (BusinessException businessException) {
            return new EntityCustomResponse(0, businessException.getMessage(), businessException.getStatusCode(), List.of());
        } catch (Exception exception) {
            return new EntityCustomResponse(0, "Error system ", 500, exception.getMessage());
        }
        return new EntityCustomResponse(1, "Image list", 200, pageResponse);
    }
}

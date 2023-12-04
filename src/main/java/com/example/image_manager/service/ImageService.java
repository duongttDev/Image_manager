package com.example.image_manager.service;

import com.example.image_manager.request.ImageRequest;
import com.example.image_manager.response.EntityCustomResponse;

public interface ImageService {

    EntityCustomResponse getAllImages(int page,int size);

    EntityCustomResponse addImage(ImageRequest imageRequest);

    EntityCustomResponse getDetailImage(Long id);
}

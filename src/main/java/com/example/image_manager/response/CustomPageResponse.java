package com.example.image_manager.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomPageResponse<T> {
	private List<T> content;
	private int page;
	private int size;
	private Long totalElements;
	private int totalPage;

}

package com.example.image_manager.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.ObjectUtils;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

    private String timeUpload;

    public String getTimeUpload() {
        if (!ObjectUtils.isEmpty(getCreateAt())) {
            LocalDateTime currentTime = LocalDateTime.now();
            Duration duration = Duration.between(getCreateAt(), currentTime);

            long seconds = duration.getSeconds();

            if (seconds < 60) {
                return seconds + " giây trước";
            } else if (seconds < 3600) {
                long minutes = seconds / 60;
                return minutes + " phút trước";
            } else if (seconds < 86400) {
                long hours = seconds / 3600;
                return hours + " giờ trước";
            } else if (seconds < 86400 * 2) {
                return "1 ngày trước";
            } else {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
                return getCreateAt().format(formatter);
            }
        }
        return "không có thời gian";
    }
}

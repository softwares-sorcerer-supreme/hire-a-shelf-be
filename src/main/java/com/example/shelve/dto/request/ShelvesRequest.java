package com.example.shelve.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ShelvesRequest {

    private String name;
    private String description;
    private boolean status;
    private Long storeId;
    private Long shelvesTypeId;
    private MultipartFile imgMultipart;

//    private Set<Long> shelvesProducts;
//    private Set<Image> images;
}

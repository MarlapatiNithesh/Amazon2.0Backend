package com.purna.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ListingResponseDTO {
    private Long id;
    private Long productId;
    private Long sellerId;
    private ProductResponseDTO product;
    private Double price;
    private Double minAcceptablePrice;
    private Integer quantity;
    private String status;
}

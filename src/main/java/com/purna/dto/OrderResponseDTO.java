package com.purna.dto;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Builder
public class OrderResponseDTO {
    private Long id;
    private Long buyerId;
    private Long productId;
    private Double purchasePrice;
    private String status;
    private LocalDateTime createdAt;
}

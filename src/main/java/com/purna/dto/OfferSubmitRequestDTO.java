package com.purna.dto;

import lombok.Data;

@Data
public class OfferSubmitRequestDTO {
    private Long listingId;
    private Double offeredPrice;
    private Integer quantity;
}

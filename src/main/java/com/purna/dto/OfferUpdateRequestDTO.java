package com.purna.dto;

import lombok.Data;

@Data
public class OfferUpdateRequestDTO {
    private Long offerId;
    private String action; // accept, reject, counter, cancel
    private Double counterPrice; // Opt
}

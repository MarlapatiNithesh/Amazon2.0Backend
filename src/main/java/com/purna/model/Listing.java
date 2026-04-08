package com.purna.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Index;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.FetchType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name= "amazon_listings", indexes = {
    @Index(name = "idx_listing_seller", columnList = "sellerId"),
    @Index(name = "idx_listing_product", columnList = "productId")
})
@SQLDelete(sql = "UPDATE amazon_listings SET is_deleted = true WHERE id=?")
@SQLRestriction("is_deleted=false")
public class Listing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "productId", referencedColumnName = "id")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sellerId", referencedColumnName = "id")
    private UserObj seller;

    private Double price;

    private Double minAcceptablePrice;

    private Integer quantity;

    private String status; // active, sold_out, inactive

    @Builder.Default
    private Boolean isDeleted = false;
}

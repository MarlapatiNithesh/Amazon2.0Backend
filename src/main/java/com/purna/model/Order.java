package com.purna.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "amazon_orders", indexes = {
    @Index(name = "idx_order_buyer", columnList = "buyerId"),
    @Index(name = "idx_order_listing", columnList = "listingId")
})
@SQLDelete(sql = "UPDATE amazon_orders SET is_deleted = true WHERE id=?")
@SQLRestriction("is_deleted=false")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "buyerId", referencedColumnName = "id")
    private UserObj buyer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "listingId", referencedColumnName = "id")
    private Listing listing;

    private Double purchasePrice;

    private String status;

    @Builder.Default
    private Boolean isDeleted = false;

    @CreationTimestamp
    private LocalDateTime createdAt;
}

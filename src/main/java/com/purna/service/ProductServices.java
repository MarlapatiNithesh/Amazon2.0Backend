package com.purna.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.purna.model.Product;
import com.purna.model.Listing;
import com.purna.model.UserObj;
import com.purna.dto.ProductResponseDTO;
import com.purna.dto.ProductRequestDTO;
import com.purna.repository.ProductsRepository;
import com.purna.repository.ListingRepository;
import com.purna.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServices {
	
	private final ProductsRepository productRepository;
	private final ListingRepository listingRepository;
	private final UserRepository userRepository;
	
	public Page<ProductResponseDTO> getAvailableProducts(Pageable pageable){
        // Returns the global catalog. A true multi-seller map usually queries Active Listings here instead, 
        // but mapping Products directly to satisfy basic feed rules.
		return productRepository.findAll(pageable).map(this::mapToProductResponseDTO);
	}
	
	public ProductResponseDTO getProductById(Long id) {
		return productRepository.findById(id).map(this::mapToProductResponseDTO).orElseThrow(() -> new RuntimeException("Product not found"));
	}
	
	public Listing addProductWithListing(ProductRequestDTO request, Long sellerId) {
		Product globalProduct = productRepository.findByTitle(request.getTitle());

        if (globalProduct == null) {
            // Register new Global Catalog Mapping
            globalProduct = Product.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .imageUrl(request.getImageUrl())
                .category(request.getCategory())
                .build();
            globalProduct = productRepository.save(globalProduct);
        }
        
        UserObj seller = userRepository.findById(sellerId.intValue()).orElseThrow(() -> new RuntimeException("Seller not found"));

        Listing listing = Listing.builder()
                .product(globalProduct)
                .seller(seller)
                .price(request.getPrice())
                .minAcceptablePrice(request.getMinAcceptablePrice())
                .quantity(request.getQuantity())
                .status("active")
                .build();
                
		return listingRepository.save(listing);
	}

	public ProductResponseDTO mapToProductResponseDTO(Product product) {
        return ProductResponseDTO.builder()
                .id(product.getId())
                .title(product.getTitle())
                .description(product.getDescription())
                .category(product.getCategory())
                .imageUrl(product.getImageUrl())
                .build();
    }
}

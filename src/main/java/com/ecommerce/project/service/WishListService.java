package com.ecommerce.project.service;

import com.ecommerce.project.exceptions.APIException;
import com.ecommerce.project.exceptions.ResourceNotFoundException;
import com.ecommerce.project.model.Product;
import com.ecommerce.project.model.WishList;
import com.ecommerce.project.repositories.ProductRepository;
import com.ecommerce.project.repositories.WishListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishListService {
    @Autowired
    private WishListRepository wishListRepository;

    @Autowired
    private ProductRepository productRepository;

    // Create Wishlist
    public void createWishlist(WishList wishList) {
        // Validate product exists
        Product product = productRepository.findById(wishList.getProductId())
                .orElseThrow(() -> new ResourceNotFoundException("Product", "productId", wishList.getProductId()));

        // Check if product already in wishlist
        List<WishList> existingWishlist = wishListRepository.findAllByUserIdOrderByCreatedDateDesc(wishList.getUserId());
        boolean alreadyExists = existingWishlist.stream()
                .anyMatch(w -> w.getProductId().equals(wishList.getProductId()));

        if (alreadyExists) {
            throw new APIException("Product already exists in wishlist");
        }

        wishListRepository.save(wishList);
    }

    // Read Wishlist
    public List<WishList> readWishList(Long userId) {
        List<WishList> wishList = wishListRepository.findAllByUserIdOrderByCreatedDateDesc(userId);
        if (wishList.isEmpty()) {
            throw new APIException("Wishlist is empty");
        }
        return wishList;
    }

    // Delete from Wishlist
    public void deleteWishlist(Integer wishlistId) {
        WishList wishList = wishListRepository.findById(wishlistId)
                .orElseThrow(() -> new ResourceNotFoundException("WishList", "wishlistId", wishlistId.longValue()));
        wishListRepository.delete(wishList);
    }

}

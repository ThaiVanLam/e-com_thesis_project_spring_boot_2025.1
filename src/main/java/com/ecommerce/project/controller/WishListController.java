package com.ecommerce.project.controller;

import com.ecommerce.project.model.WishList;
import com.ecommerce.project.payload.APIResponse;
import com.ecommerce.project.service.WishListService;
import com.ecommerce.project.util.AuthUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class WishListController {
    @Autowired
    private WishListService wishListService;

    @Autowired
    private AuthUtil authUtil;

    @PostMapping("/wishlist/products/{productId}")
    public ResponseEntity<APIResponse> addProductToWishlist(@PathVariable Long productId) {
        Long userId = authUtil.loggedInUserId();
        wishListService.createWishlist(new WishList(userId, productId));
        return new ResponseEntity<>(new APIResponse("Product added to wishlist successfully", true), HttpStatus.CREATED);
    }

    @GetMapping("/wishlist")
    public ResponseEntity<List<WishList>> getUserWishlist() {
        Long userId = authUtil.loggedInUserId();
        List<WishList> wishList = wishListService.readWishList(userId);
        return new ResponseEntity<>(wishList, HttpStatus.OK);
    }

    @DeleteMapping("/wishlist/{wishlistId}")
    public ResponseEntity<APIResponse> removeFromWishlist(@PathVariable Integer wishlistId) {
        wishListService.deleteWishlist(wishlistId);
        return new ResponseEntity<>(new APIResponse("Product removed from wishlist successfully", true), HttpStatus.OK);
    }
}

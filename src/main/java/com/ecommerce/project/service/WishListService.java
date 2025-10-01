package com.ecommerce.project.service;

import com.ecommerce.project.model.WishList;
import com.ecommerce.project.repositories.WishListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishListService {
    @Autowired
    private WishListRepository wishListRepository;

    //Create Wishlist
    public void createWishlist(WishList wishList) {
        wishListRepository.save(wishList);
    }

    //ReadWishlist
    public List<WishList> readWishList(Long userId) {
        return wishListRepository.findAllByUserIdOrderByCreatedDateDesc(userId);
    }
}

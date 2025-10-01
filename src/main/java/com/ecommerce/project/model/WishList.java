package com.ecommerce.project.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
@Table(name = "wishlist")
@Data
@NoArgsConstructor
public class WishList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "wishlist_id")
    private Integer wishlistId;

    @Column(name = "user_id")
    @NotNull
    private Long userId;

    @NotNull
    @Column(name = "product_id")
    private Long productId;

    @Column(name = "created_date")
    @CreationTimestamp
    private Date createdDate;

    public WishList(Long userId, Long productId) {
        this.userId = userId;
        this.productId = productId;
    }
}

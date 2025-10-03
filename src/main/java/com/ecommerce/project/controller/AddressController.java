package com.ecommerce.project.controller;

import com.ecommerce.project.payload.AddressDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AddressController {
    @Autowired
    private AddressService addressService;

    @PostMapping("/api/addresses")
    public ResponseEntity<AddressDTO> createAddress(@RequestBody AddressDTO addressDTO){

    }
}

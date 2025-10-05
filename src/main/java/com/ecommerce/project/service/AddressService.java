package com.ecommerce.project.service;

import com.ecommerce.project.payload.AddressDTO;
import jakarta.validation.Valid;

public interface AddressService {
    AddressDTO createAddress(@Valid AddressDTO addressDTO);
}

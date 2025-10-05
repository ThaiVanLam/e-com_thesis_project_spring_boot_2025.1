package com.ecommerce.project.service;

import com.ecommerce.project.exceptions.APIException;
import com.ecommerce.project.model.Address;
import com.ecommerce.project.model.User;
import com.ecommerce.project.payload.AddressDTO;
import com.ecommerce.project.repositories.AddressRepository;
import com.ecommerce.project.repositories.UserRepository;
import com.ecommerce.project.util.AuthUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {
    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AuthUtil authUtil;

    @Autowired
    private UserRepository userRepository;

    @Override
    public AddressDTO createAddress(AddressDTO addressDTO) {
        // Map DTO to Entity
        Address address = modelMapper.map(addressDTO, Address.class);

        // Get current logged-in user
        User user = authUtil.loggedInUser();

        // Check if address already exists for this user
        List<Address> userAddresses = user.getAddresses();
        boolean addressExists = userAddresses.stream()
                .anyMatch(a ->
                        a.getStreet().equalsIgnoreCase(address.getStreet()) &&
                                a.getBuildingName().equalsIgnoreCase(address.getBuildingName()) &&
                                a.getCity().equalsIgnoreCase(address.getCity()) &&
                                a.getPincode().equalsIgnoreCase(address.getPincode())
                );
        if (addressExists) {
            throw new APIException("Address already exists for this user");
        }

        // Save address
        Address savedAddress = addressRepository.save(address);

        // Add address to user's address list
        user.getAddresses().add(savedAddress);
        userRepository.save(user);

        // Map entity back to DTO and return
        return modelMapper.map(savedAddress, AddressDTO.class);
    }
}

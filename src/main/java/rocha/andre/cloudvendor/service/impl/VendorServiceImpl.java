package rocha.andre.cloudvendor.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import rocha.andre.cloudvendor.domain.Vendor.UseCase.*;
import rocha.andre.cloudvendor.domain.Vendor.VendorDto;
import rocha.andre.cloudvendor.domain.Vendor.VendorDtoReturn;
import rocha.andre.cloudvendor.domain.Vendor.VendorUpdateDto;
import rocha.andre.cloudvendor.service.VendorService;

@Service
public class VendorServiceImpl implements VendorService {
    @Autowired
    private GetVendorByIdUseCase getVendorByIdUseCase;
    @Autowired
    private GetAllVendorUseCase getAllVendorUseCase;
    @Autowired
    private CreateVendorUseCase createVendorUseCase;
    @Autowired
    private UpdateVendorUseCase updateVendorUseCase;
    @Autowired
    private DeleteVendorUseCase deleteVendorUseCase;


    @Override
    public VendorDtoReturn getVendorById(long id) {
        var vendorById = getVendorByIdUseCase.getVendorById(id);
        return vendorById;
    }

    @Override
    public Page<VendorDtoReturn> getAllVendors(Pageable pagination) {
        var vendors = getAllVendorUseCase.getAllVendors(pagination);
        return vendors;
    }

    @Override
    public VendorDtoReturn createvendor(VendorDto data) {
        var newVendor = createVendorUseCase.createvendor(data);
        return newVendor;
    }

    @Override
    public VendorDtoReturn updateVendor(VendorUpdateDto data, Long id) {
        var updatedVendor = updateVendorUseCase.updateVendor(data, id);
        return updatedVendor;
    }

    @Override
    public void deleteVendor(Long id) {
        deleteVendorUseCase.deleteVendor(id);
    }
}

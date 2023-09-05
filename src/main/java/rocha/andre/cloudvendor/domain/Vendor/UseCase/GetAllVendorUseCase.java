package rocha.andre.cloudvendor.domain.Vendor.UseCase;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import rocha.andre.cloudvendor.domain.Vendor.VendorDtoReturn;
import rocha.andre.cloudvendor.domain.Vendor.VendorRepository;

import java.util.List;

@Component
public class GetAllVendorUseCase {
    @Autowired
    private VendorRepository repository;

    public Page<VendorDtoReturn> getAllVendors(Pageable pagination) {
        return repository.findAll(pagination).map(VendorDtoReturn::new);
    }

}

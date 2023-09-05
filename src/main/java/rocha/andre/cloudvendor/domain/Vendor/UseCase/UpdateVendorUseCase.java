package rocha.andre.cloudvendor.domain.Vendor.UseCase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rocha.andre.cloudvendor.domain.Vendor.Vendor;
import rocha.andre.cloudvendor.domain.Vendor.VendorRepository;
import rocha.andre.cloudvendor.domain.Vendor.VendorUpdateDto;

@Component
public class UpdateVendorUseCase {
    @Autowired
    private VendorRepository repository;

    public Vendor updateVendor(VendorUpdateDto data, Long id) {
        Vendor vendorToUpdate = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Vendor with the provided ID does not exist."));

        vendorToUpdate.updateVendor(data);

        return vendorToUpdate;
    }
}

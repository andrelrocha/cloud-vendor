package rocha.andre.cloudvendor.domain.Vendor.UseCase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rocha.andre.cloudvendor.domain.Vendor.VendorRepository;

@Component
public class DeleteVendorUseCase {
    @Autowired
    private VendorRepository repository;

    public void deleteVendor(Long id) {
        var vendorToExclude = repository.getReferenceById(id);

        if (vendorToExclude == null) {
            new IllegalArgumentException("Vendor with the provided ID does not exist.");
        }

        repository.delete(vendorToExclude);
    }

}

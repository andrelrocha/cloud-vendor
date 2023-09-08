package rocha.andre.cloudvendor.domain.Vendor.UseCase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rocha.andre.cloudvendor.domain.Vendor.Vendor;
import rocha.andre.cloudvendor.domain.Vendor.VendorDto;
import rocha.andre.cloudvendor.domain.Vendor.VendorDtoReturn;
import rocha.andre.cloudvendor.domain.Vendor.VendorRepository;
import rocha.andre.cloudvendor.infra.exceptions.ValidationException;

@Component
public class CreateVendorUseCase {
    @Autowired
    private VendorRepository vendorRepository;

    public VendorDtoReturn createvendor(VendorDto data) {
        var vendor = vendorRepository.findByNameAndCity(data.name(), data.city());

        //boolean vendorExists = vendorRepository.existsByName(data.name());

        if (!vendor.isEmpty()) {
            throw new ValidationException("Vendor with name " + data.name() + " located in " + data.city() + " already exists");
        }

        Vendor newVendor = new Vendor(data);

        vendorRepository.save(newVendor);

        return new VendorDtoReturn(newVendor);
    }

}

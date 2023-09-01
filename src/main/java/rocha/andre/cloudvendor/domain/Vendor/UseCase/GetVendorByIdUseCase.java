package rocha.andre.cloudvendor.domain.Vendor.UseCase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rocha.andre.cloudvendor.domain.Vendor.VendorDtoReturn;
import rocha.andre.cloudvendor.domain.Vendor.VendorRepository;
import rocha.andre.cloudvendor.infra.exceptions.ValidationException;

@Component
public class GetVendorByIdUseCase {
    @Autowired
    private VendorRepository vendorRepository;

    public VendorDtoReturn getVendorById(long id) {
        var vendor = vendorRepository.findById(id)
                .orElseThrow(() -> new ValidationException("No vendor was found for the provided ID"));

        return new VendorDtoReturn(vendor);
    }
}

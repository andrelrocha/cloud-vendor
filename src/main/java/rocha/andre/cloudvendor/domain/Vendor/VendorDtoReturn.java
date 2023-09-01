package rocha.andre.cloudvendor.domain.Vendor;

import rocha.andre.cloudvendor.domain.User.User;
import rocha.andre.cloudvendor.domain.Vendor.Vendor;

public record VendorDtoReturn(Long id, String name, String phone, String street, String complement, String city, String state) {

    public VendorDtoReturn(Vendor vendor) {
        this(vendor.getId(), vendor.getName(), vendor.getPhone(), vendor.getStreet(), vendor.getComplement(), vendor.getCity(), vendor.getState());
    }
}

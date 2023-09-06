package rocha.andre.cloudvendor.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import rocha.andre.cloudvendor.domain.Vendor.VendorDto;
import rocha.andre.cloudvendor.domain.Vendor.VendorDtoReturn;
import rocha.andre.cloudvendor.domain.Vendor.VendorUpdateDto;

public interface VendorService {
    public VendorDtoReturn getVendorById(long id);
    public Page<VendorDtoReturn> getAllVendors(Pageable pagination);
    public VendorDtoReturn createvendor(VendorDto data);
    public VendorDtoReturn updateVendor(VendorUpdateDto data, Long id);
    public void deleteVendor(Long id);
}

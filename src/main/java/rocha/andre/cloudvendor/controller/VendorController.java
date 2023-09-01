package rocha.andre.cloudvendor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rocha.andre.cloudvendor.domain.Vendor.UseCase.GetVendorByIdUseCase;
import rocha.andre.cloudvendor.domain.Vendor.UseCase.VendorDtoReturn;

@RestController
@RequestMapping("/vendor")
public class VendorController {

    @Autowired
    private GetVendorByIdUseCase getVendorByIdUseCase;

    @GetMapping("/{id}")
    public ResponseEntity getVendorDetails(@PathVariable Long id) {
        VendorDtoReturn vendorById = getVendorByIdUseCase.getVendorById(id);
        return ResponseEntity.ok().body(vendorById);
    }

}

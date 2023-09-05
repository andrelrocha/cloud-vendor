package rocha.andre.cloudvendor.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import rocha.andre.cloudvendor.domain.Vendor.UseCase.CreateVendorUseCase;
import rocha.andre.cloudvendor.domain.Vendor.UseCase.GetVendorByIdUseCase;
import rocha.andre.cloudvendor.domain.Vendor.UseCase.UpdateVendorUseCase;
import rocha.andre.cloudvendor.domain.Vendor.VendorDto;
import rocha.andre.cloudvendor.domain.Vendor.VendorUpdateDto;

@RestController
@RequestMapping("/vendor")
@SecurityRequirement(name = "bearer-key")
public class VendorController {
    @Autowired
    private GetVendorByIdUseCase getVendorByIdUseCase;
    @Autowired
    private CreateVendorUseCase createVendorUseCase;
    @Autowired
    private UpdateVendorUseCase updateVendorUseCase;

    @GetMapping("/{id}")
    public ResponseEntity getVendorDetails(@PathVariable Long id) {
        var vendorById = getVendorByIdUseCase.getVendorById(id);
        return ResponseEntity.ok().body(vendorById);
    }

    @PostMapping
    @Transactional
    public ResponseEntity createVendor(@RequestBody @Valid VendorDto data) {
        var newVendor = createVendorUseCase.createvendor(data);
        return ResponseEntity.status(HttpStatusCode.valueOf(201)).body(newVendor);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity updateVendor(@RequestBody @Valid VendorUpdateDto data, @PathVariable Long id) {
        var updatedVendor = updateVendorUseCase.updateVendor(data, id);
        return ResponseEntity.ok(updatedVendor);
    }
}

package rocha.andre.cloudvendor.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import rocha.andre.cloudvendor.domain.Vendor.VendorDto;
import rocha.andre.cloudvendor.domain.Vendor.VendorDtoReturn;
import rocha.andre.cloudvendor.domain.Vendor.VendorUpdateDto;
import rocha.andre.cloudvendor.response.ResponseHandler;
import rocha.andre.cloudvendor.service.VendorService;

@RestController
@RequestMapping("/vendor")
@SecurityRequirement(name = "bearer-key")
public class VendorController {
    @Autowired
    private VendorService vendorService;

    @GetMapping("/{id}")
    public ResponseEntity<Object> getVendorDetails(@PathVariable Long id) {
        var vendorById = vendorService.getVendorById(id);
        var response = ResponseHandler.responseBuilder("Requested Vendor Details are given here", HttpStatus.OK, vendorById);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping
    public ResponseEntity<Page<VendorDtoReturn>> getAllVendors(@PageableDefault(size = 5, sort = {"name"}) Pageable pagination) {
        Page <VendorDtoReturn> allVendors = vendorService.getAllVendors(pagination);
        return ResponseEntity.ok(allVendors);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Object> createVendor(@RequestBody @Valid VendorDto data) {
        var newVendor = vendorService.createvendor(data);
        var response = ResponseHandler.responseBuilder("Vendor created successfully", HttpStatus.CREATED, newVendor);
        return ResponseEntity.status(HttpStatusCode.valueOf(201)).body(response);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<Object> updateVendor(@RequestBody @Valid VendorUpdateDto data, @PathVariable Long id) {
        var updatedVendor = vendorService.updateVendor(data, id);
        var response = ResponseHandler.responseBuilder("Vendor updated successfully", HttpStatus.OK, updatedVendor);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deleteVendor(@PathVariable Long id) {
        vendorService.deleteVendor(id);
        return ResponseEntity.noContent().build();
    }
}

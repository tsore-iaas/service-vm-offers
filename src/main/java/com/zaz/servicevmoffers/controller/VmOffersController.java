package com.zaz.servicevmoffers.controller;

import com.zaz.servicevmoffers.dto.ApiResponse;
import com.zaz.servicevmoffers.model.VmOffers;
import com.zaz.servicevmoffers.service.VmOffersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/vm-offers")
public class VmOffersController {

    @Autowired
    private VmOffersService vmOffersService;

    @PostMapping
    public ResponseEntity<ApiResponse<VmOffers>> createVmOffer(@RequestBody VmOffers vmOffers) {
        VmOffers createdOffer = vmOffersService.createVmOffer(vmOffers);
        return ResponseEntity.ok(ApiResponse.success("VM offer created successfully", createdOffer));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<VmOffers>>> getAllVmOffers() {
        List<VmOffers> offers = vmOffersService.getAllVmOffers();
        return ResponseEntity.ok(ApiResponse.success("VM offers retrieved successfully", offers));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<VmOffers>> getVmOfferById(@PathVariable Long id) {
        Optional<VmOffers> vmOffer = vmOffersService.getVmOfferById(id);
        return vmOffer
                .map(offer -> ResponseEntity.ok(ApiResponse.success("VM offer found", offer)))
                .orElse(ResponseEntity.ok(ApiResponse.error("VM offer not found")));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<VmOffers>> updateVmOffer(@PathVariable Long id, @RequestBody VmOffers vmOfferDetails) {
        VmOffers updatedOffer = vmOffersService.updateVmOffer(id, vmOfferDetails);
        if (updatedOffer != null) {
            return ResponseEntity.ok(ApiResponse.success("VM offer updated successfully", updatedOffer));
        }
        return ResponseEntity.ok(ApiResponse.error("VM offer not found"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteVmOffer(@PathVariable Long id) {
        boolean deleted = vmOffersService.deleteVmOffer(id);
        if (deleted) {
            return ResponseEntity.ok(ApiResponse.success("VM offer deleted successfully", null));
        }
        return ResponseEntity.ok(ApiResponse.error("VM offer not found"));
    }
}

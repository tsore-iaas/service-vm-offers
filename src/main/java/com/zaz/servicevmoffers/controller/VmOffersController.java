package com.zaz.servicevmoffers.controller;

import com.zaz.servicevmoffers.dto.ApiResponse;
import com.zaz.servicevmoffers.model.VmOffers;
import com.zaz.servicevmoffers.service.VmOffersService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/vm-offers")
@Tag(name = "VM Offers", description = "VM Offers management API")
public class VmOffersController {

    @Autowired
    private VmOffersService vmOffersService;

    @Operation(summary = "Create a new VM offer", description = "Creates a new VM offer with the provided details")
    @ApiResponses(value = {
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "VM offer created successfully",
                content = @Content(schema = @Schema(implementation = VmOffers.class))),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @PostMapping
    public ResponseEntity<com.zaz.servicevmoffers.dto.ApiResponse<VmOffers>> createVmOffer(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "VM offer details", required = true,
                    content = @Content(schema = @Schema(implementation = VmOffers.class)))
            @RequestBody VmOffers vmOffers) {
        VmOffers createdOffer = vmOffersService.createVmOffer(vmOffers);
        return ResponseEntity.ok(com.zaz.servicevmoffers.dto.ApiResponse.success("VM offer created successfully", createdOffer));
    }

    @Operation(summary = "Get all VM offers", description = "Returns a list of all available VM offers")
    @ApiResponses(value = {
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Successfully retrieved VM offers")
    })
    @GetMapping
    public ResponseEntity<com.zaz.servicevmoffers.dto.ApiResponse<List<VmOffers>>> getAllVmOffers() {
        List<VmOffers> offers = vmOffersService.getAllVmOffers();
        return ResponseEntity.ok(com.zaz.servicevmoffers.dto.ApiResponse.success("VM offers retrieved successfully", offers));
    }

    @Operation(summary = "Get a VM offer by ID", description = "Returns a VM offer based on the provided ID")
    @ApiResponses(value = {
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Successfully retrieved VM offer"),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "VM offer not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<com.zaz.servicevmoffers.dto.ApiResponse<VmOffers>> getVmOfferById(
            @Parameter(description = "ID of the VM offer to retrieve", required = true)
            @PathVariable Long id) {
        Optional<VmOffers> vmOffer = vmOffersService.getVmOfferById(id);
        return vmOffer
                .map(offer -> ResponseEntity.ok(com.zaz.servicevmoffers.dto.ApiResponse.success("VM offer found", offer)))
                .orElse(ResponseEntity.ok(com.zaz.servicevmoffers.dto.ApiResponse.error("VM offer not found")));
    }

    @Operation(summary = "Update a VM offer", description = "Updates a VM offer based on the provided ID and details")
    @ApiResponses(value = {
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "VM offer updated successfully"),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "VM offer not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<com.zaz.servicevmoffers.dto.ApiResponse<VmOffers>> updateVmOffer(
            @Parameter(description = "ID of the VM offer to update", required = true)
            @PathVariable Long id,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Updated VM offer details", required = true,
                    content = @Content(schema = @Schema(implementation = VmOffers.class)))
            @RequestBody VmOffers vmOfferDetails) {
        VmOffers updatedOffer = vmOffersService.updateVmOffer(id, vmOfferDetails);
        if (updatedOffer != null) {
            return ResponseEntity.ok(com.zaz.servicevmoffers.dto.ApiResponse.success("VM offer updated successfully", updatedOffer));
        }
        return ResponseEntity.ok(com.zaz.servicevmoffers.dto.ApiResponse.error("VM offer not found"));
    }

    @Operation(summary = "Delete a VM offer", description = "Deletes a VM offer based on the provided ID")
    @ApiResponses(value = {
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "VM offer deleted successfully"),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "VM offer not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<com.zaz.servicevmoffers.dto.ApiResponse<Void>> deleteVmOffer(
            @Parameter(description = "ID of the VM offer to delete", required = true)
            @PathVariable Long id) {
        boolean deleted = vmOffersService.deleteVmOffer(id);
        if (deleted) {
            return ResponseEntity.ok(com.zaz.servicevmoffers.dto.ApiResponse.success("VM offer deleted successfully", null));
        }
        return ResponseEntity.ok(com.zaz.servicevmoffers.dto.ApiResponse.error("VM offer not found"));
    }
}

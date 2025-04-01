package com.zaz.servicevmoffers.service;

import com.zaz.servicevmoffers.broker.VmOfferProducer;
import com.zaz.servicevmoffers.model.VmOffers;
import com.zaz.servicevmoffers.repository.VmOffersRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class VmOffersService {

    @Autowired
    private VmOffersRepository vmOffersRepository;
    
    @Autowired
    private VmOfferProducer vmOfferProducer;

    public VmOffers createVmOffer(VmOffers vmOffers) {
        vmOffers.setCreatedAt(LocalDateTime.now());
        VmOffers savedOffer = vmOffersRepository.save(vmOffers);
        
        // Publier l'événement de création
        vmOfferProducer.publishVmOffers(VmOfferProducer.EVENT_CREATE, savedOffer);
        
        return savedOffer;
    }

    public List<VmOffers> getAllVmOffers() {
        return vmOffersRepository.findAll();
    }

    public Optional<VmOffers> getVmOfferById(Long id) {
        return vmOffersRepository.findById(id);
    }

    public VmOffers updateVmOffer(Long id, VmOffers vmOfferDetails) {
        Optional<VmOffers> vmOffer = vmOffersRepository.findById(id);
        if (vmOffer.isPresent()) {
            VmOffers existingVmOffer = vmOffer.get();
            // Update fields with new values
            if (vmOfferDetails.getName() != null) {
                existingVmOffer.setName(vmOfferDetails.getName());
            }
            if (vmOfferDetails.getDescription() != null) {
                existingVmOffer.setDescription(vmOfferDetails.getDescription());
            }
            if (vmOfferDetails.getCpuCount() != null) {
                existingVmOffer.setCpuCount(vmOfferDetails.getCpuCount());
            }
            if (vmOfferDetails.getMemorySizeMib() != null) {
                existingVmOffer.setMemorySizeMib(vmOfferDetails.getMemorySizeMib());
            }
            if (vmOfferDetails.getDiskSizeGb() != null) {
                existingVmOffer.setDiskSizeGb(vmOfferDetails.getDiskSizeGb());
            }
            if (vmOfferDetails.getPrice() != null) {
                existingVmOffer.setPrice(vmOfferDetails.getPrice());
            }
            if (vmOfferDetails.getIsActive() != null) {
                existingVmOffer.setIsActive(vmOfferDetails.getIsActive());
            }
            VmOffers updatedOffer = vmOffersRepository.save(existingVmOffer);
            
            // Publier l'événement de mise à jour
            vmOfferProducer.publishVmOffers(VmOfferProducer.EVENT_UPDATE, updatedOffer);
            
            return updatedOffer;
        }
        return null;
    }

    public boolean deleteVmOffer(Long id) {
        Optional<VmOffers> vmOffer = vmOffersRepository.findById(id);
        if (vmOffer.isPresent()) {
            VmOffers offerToDelete = vmOffer.get();
            
            // Supprimer l'offre de la base de données
            vmOffersRepository.deleteById(id);
            
            // Publier l'événement de suppression
            vmOfferProducer.publishVmOffers(VmOfferProducer.EVENT_DELETE, offerToDelete);
            
            return true;
        }
        return false;
    }
}

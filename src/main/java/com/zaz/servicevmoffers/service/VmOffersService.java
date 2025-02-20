package com.zaz.servicevmoffers.service;

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

    public VmOffers createVmOffer(VmOffers vmOffers) {
        vmOffers.setCreatedAt(LocalDateTime.now());
        return vmOffersRepository.save(vmOffers);
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
            existingVmOffer.setName(vmOfferDetails.getName());
            existingVmOffer.setDescription(vmOfferDetails.getDescription());
            existingVmOffer.setCpu_count(vmOfferDetails.getCpu_count());
            existingVmOffer.setMemory_size_mib(vmOfferDetails.getMemory_size_mib());
            existingVmOffer.setDisk_size_gb(vmOfferDetails.getDisk_size_gb());
            existingVmOffer.setPrice(vmOfferDetails.getPrice());
            existingVmOffer.setIs_active(vmOfferDetails.getIs_active());
            return vmOffersRepository.save(existingVmOffer);
        }
        return null;
    }

    public boolean deleteVmOffer(Long id) {
        if (vmOffersRepository.existsById(id)) {
            vmOffersRepository.deleteById(id);
            return true;
        }
        return false;
    }
}

package com.zaz.servicevmoffers.repository;

import com.zaz.servicevmoffers.model.VmOffers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VmOffersRepository extends JpaRepository<VmOffers, Long> {
}

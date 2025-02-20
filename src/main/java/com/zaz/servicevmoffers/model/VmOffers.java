package com.zaz.servicevmoffers.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Entity
@Table(name = "vm_offers")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class VmOffers {

    @Id
    private Long id;
    private String name;
    private String description;
    private String cpu_count;
    private String memory_size_mib;
    private String disk_size_gb = null;
    private String price;
    @Enumerated(EnumType.STRING)
    private Status is_active; // Type de la commande (cash, credit)

    private LocalDateTime createdAt;
}

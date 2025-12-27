package com.rentacar.model;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "ekhizmetler")
public class EkHizmet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hizmet_id")
    private Integer id;

    @Column(name = "hizmet_adi", length = 255)
    private String ad;

    @Column(name = "ucret", precision = 10, scale = 2)
    private BigDecimal ucret;
}

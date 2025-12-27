package com.rentacar.model;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "sigortalar")
public class Sigorta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sigorta_id")
    private Integer id;

    @Column(name = "police_no", length = 100)
    private String policeNo;

    @Column(name = "sirket_adi", length = 255)
    private String sirketAdi;

    @Column(name = "ucret", precision = 10, scale = 2)
    private BigDecimal ucret;
}

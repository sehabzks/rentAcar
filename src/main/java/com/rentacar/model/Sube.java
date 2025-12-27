package com.rentacar.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "subeler")
public class Sube {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sube_id")
    private Integer id;

    @Column(name = "sube_adi", length = 255)
    private String ad;

    @Column(name = "adres", columnDefinition = "TEXT")
    private String adres;
}

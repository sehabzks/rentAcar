package com.rentacar.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "siniflar")
public class Sinif {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sinif_id")
    private Integer id;

    @Column(name = "sinif_adi", length = 100)
    private String ad;
}

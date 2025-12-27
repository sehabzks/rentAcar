package com.rentacar.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "musteriler")
public class Musteri {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "musteri_id")
    private Integer id;

    @Column(name = "ad", length = 100)
    private String ad;

    @Column(name = "soyad", length = 100)
    private String soyad;

    @Column(name = "ehliyet_no", length = 50)
    private String ehliyetNo;

    @Column(name = "telefon", length = 20)
    private String telefon;

    @Column(name = "email", length = 100)
    private String email;

    @Column(name = "sifre", length = 255)
    private String sifre;
}

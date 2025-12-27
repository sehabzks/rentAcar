package com.rentacar.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "personel")
public class Personel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "personel_id")
    private Integer id;

    @Column(name = "ad", length = 100)
    private String ad;

    @Column(name = "soyad", length = 100)
    private String soyad;

    @Column(name = "rol", length = 50)
    private String rol;

    @Column(name = "kullanici_adi", length = 50)
    private String kullaniciAdi;

    @Column(name = "sifre", length = 255)
    private String sifre;

    @ManyToOne
    @JoinColumn(name = "sube_id")
    private Sube sube;
}

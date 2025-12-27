package com.rentacar.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "araclar")
public class Arac {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "arac_id")
    private Integer id;

    @Column(name = "plaka", length = 20)
    private String plaka;

    @Column(name = "yil")
    private Integer yil;

    @Column(name = "durum", length = 50)
    private String durum;

    @ManyToOne
    @JoinColumn(name = "model_id")
    private Model model;

    @ManyToOne
    @JoinColumn(name = "sube_id")
    private Sube sube;

    @ManyToOne
    @JoinColumn(name = "sinif_id")
    private Sinif sinif;
}

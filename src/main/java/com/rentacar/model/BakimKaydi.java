package com.rentacar.model;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "bakimkayitlari")
public class BakimKaydi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bakim_id")
    private Integer id;

    @Column(name = "aciklama", columnDefinition = "TEXT")
    private String aciklama;

    @Column(name = "tarih")
    private LocalDate tarih;

    @Column(name = "maliyet", precision = 10, scale = 2)
    private BigDecimal maliyet;

    @ManyToOne
    @JoinColumn(name = "arac_id")
    private Arac arac;
}

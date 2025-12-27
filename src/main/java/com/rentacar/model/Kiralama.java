package com.rentacar.model;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "kiralama_kayitlari")
public class Kiralama {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "kiralama_id")
    private Integer id;

    @Column(name = "alis_tarihi")
    private LocalDateTime alisTarihi;

    @Column(name = "donus_tarihi")
    private LocalDateTime donusTarihi;

    @Column(name = "toplam_ucret", precision = 10, scale = 2)
    private BigDecimal toplamUcret;

    @ManyToOne
    @JoinColumn(name = "musteri_id")
    private Musteri musteri;

    @ManyToOne
    @JoinColumn(name = "arac_id")
    private Arac arac;

    @ManyToOne
    @JoinColumn(name = "personel_id")
    private Personel personel;

    @ManyToOne
    @JoinColumn(name = "sigorta_id")
    private Sigorta sigorta;

    @ManyToMany
    @JoinTable(name = "kiralama_ekhizmetleri", joinColumns = @JoinColumn(name = "kiralama_id"), inverseJoinColumns = @JoinColumn(name = "hizmet_id"))
    private List<EkHizmet> ekHizmetler;
}

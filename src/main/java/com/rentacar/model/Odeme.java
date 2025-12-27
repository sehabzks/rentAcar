package com.rentacar.model;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "odemeler")
public class Odeme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "odeme_id")
    private Integer id;

    @Column(name = "tutar", precision = 10, scale = 2)
    private BigDecimal tutar;

    @Column(name = "odeme_tipi", length = 50)
    private String odemeTipi;

    @Column(name = "odeme_tarihi")
    private LocalDateTime odemeTarihi;

    @ManyToOne
    @JoinColumn(name = "kiralama_id")
    private Kiralama kiralama;
}

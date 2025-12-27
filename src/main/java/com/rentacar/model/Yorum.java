package com.rentacar.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "yorumlar")
public class Yorum {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "yorum_id")
    private Integer id;

    @Column(name = "puan")
    private Integer puan;

    @Column(name = "metin", columnDefinition = "TEXT")
    private String metin;

    @Column(name = "tarih")
    private LocalDateTime tarih;

    @OneToOne
    @JoinColumn(name = "kiralama_id")
    private Kiralama kiralama;
}

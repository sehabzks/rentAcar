---
trigger: always_on
---

Veritabanı şemam (SQL) ektedir. Lütfen JPA Entity sınıflarını (Model) birebir bu tablo yapısına uygun olarak, gerekli @Column ve @ManyToOne ilişkileriyle üret.

-- Database: rentAcar

-- DROP DATABASE IF EXISTS "rentAcar";

CREATE DATABASE "rentAcar"
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'Turkish_T�rkiye.1254'
    LC_CTYPE = 'Turkish_T�rkiye.1254'
    LOCALE_PROVIDER = 'libc'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1
    IS_TEMPLATE = False;



package org.ipn.mx.galeriaarte.Infrastructure.adapter.out.persistence.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "ArchivoDigital")
public class ArchivoDigital {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idArchivo")
    private Integer idArchivo;

    @Column(name = "ruta", nullable = false, length = 512)
    private String ruta;

    @Column(name = "formato", nullable = false, length = 20)
    private String formato;

    @Column(name = "checksum", nullable = false, length = 128)
    private String checksum;

    @Column(name = "idObraDigital", nullable = false)
    private Integer idObraDigital;

    @ManyToOne
    @JoinColumn(name = "idObraDigital", insertable = false, updatable = false)
    private ObraDigital obraDigital;

    public ArchivoDigital() {}

    public ArchivoDigital(String ruta, String formato, String checksum, Integer idObraDigital) {
        this.ruta = ruta;
        this.formato = formato;
        this.checksum = checksum;
        this.idObraDigital = idObraDigital;
    }

    public Integer getIdArchivo() { return idArchivo; }
    public void setIdArchivo(Integer idArchivo) { this.idArchivo = idArchivo; }

    public String getRuta() { return ruta; }
    public void setRuta(String ruta) { this.ruta = ruta; }

    public String getFormato() { return formato; }
    public void setFormato(String formato) { this.formato = formato; }

    public String getChecksum() { return checksum; }
    public void setChecksum(String checksum) { this.checksum = checksum; }

    public Integer getIdObraDigital() { return idObraDigital; }
    public void setIdObraDigital(Integer idObraDigital) { this.idObraDigital = idObraDigital; }

    public ObraDigital getObraDigital() { return obraDigital; }
    public void setObraDigital(ObraDigital obraDigital) { this.obraDigital = obraDigital; }
}
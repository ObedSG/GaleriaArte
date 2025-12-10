package org.ipn.mx.galeriaarte.Infrastructure.adapter.out.persistence.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "ObraCategoria")
public class ObraCategoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idObraCategoria")
    private Integer idObraCategoria;

    @Column(name = "idObraDigital", nullable = false)
    private Integer idObraDigital;

    @Column(name = "idCategoria", nullable = false)
    private Integer idCategoria;

    @ManyToOne
    @JoinColumn(name = "idObraDigital", insertable = false, updatable = false)
    private ObraDigital obraDigital;

    @ManyToOne
    @JoinColumn(name = "idCategoria", insertable = false, updatable = false)
    private Categoria categoria;

    public ObraCategoria() {}

    public ObraCategoria(Integer idObraDigital, Integer idCategoria) {
        this.idObraDigital = idObraDigital;
        this.idCategoria = idCategoria;
    }

    public Integer getIdObraCategoria() { return idObraCategoria; }
    public void setIdObraCategoria(Integer idObraCategoria) { this.idObraCategoria = idObraCategoria; }

    public Integer getIdObraDigital() { return idObraDigital; }
    public void setIdObraDigital(Integer idObraDigital) { this.idObraDigital = idObraDigital; }

    public Integer getIdCategoria() { return idCategoria; }
    public void setIdCategoria(Integer idCategoria) { this.idCategoria = idCategoria; }

    public ObraDigital getObraDigital() { return obraDigital; }
    public void setObraDigital(ObraDigital obraDigital) { this.obraDigital = obraDigital; }

    public Categoria getCategoria() { return categoria; }
    public void setCategoria(Categoria categoria) { this.categoria = categoria; }
}
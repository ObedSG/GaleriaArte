package org.ipn.mx.galeriaarte.Infrastructure.adapter.out.persistence.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Categoria")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCategoria")
    private Integer idCategoria;

    @Column(name = "nombreCategoria", nullable = false, length = 50)
    private String nombreCategoria;

    @Column(name = "descripcionCategoria", length = 250)
    private String descripcionCategoria;

    @ManyToMany(mappedBy = "categorias")
    private List<ObraDigital> obras = new ArrayList<>();

    public Categoria() {}

    public Categoria(String nombreCategoria, String descripcionCategoria) {
        this.nombreCategoria = nombreCategoria;
        this.descripcionCategoria = descripcionCategoria;
    }

    public Integer getIdCategoria() { return idCategoria; }
    public void setIdCategoria(Integer idCategoria) { this.idCategoria = idCategoria; }

    public String getNombreCategoria() { return nombreCategoria; }
    public void setNombreCategoria(String nombreCategoria) { this.nombreCategoria = nombreCategoria; }

    public String getDescripcionCategoria() { return descripcionCategoria; }
    public void setDescripcionCategoria(String descripcionCategoria) {
        this.descripcionCategoria = descripcionCategoria;
    }

    public List<ObraDigital> getObras() { return obras; }
    public void setObras(List<ObraDigital> obras) { this.obras = obras; }
}
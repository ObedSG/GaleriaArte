package org.ipn.mx.galeriaarte.Dominio;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Autor")
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idAutor")
    private Integer idAutor;

    @Column(name = "nombreCompleto", nullable = false, length = 100)
    private String nombreCompleto;

    @Column(name = "correoContacto", length = 100)
    private String correoContacto;

    @Column(name = "avatar", length = 255)
    private String avatar;

    @OneToMany(mappedBy = "autor")
    private List<ObraDigital> obras = new ArrayList<>();

    public Autor() {}

    public Autor(String nombreCompleto, String correoContacto, String avatar) {
        this.nombreCompleto = nombreCompleto;
        this.correoContacto = correoContacto;
        this.avatar = avatar;
    }

    public Integer getIdAutor() { return idAutor; }
    public void setIdAutor(Integer idAutor) { this.idAutor = idAutor; }

    public String getNombreCompleto() { return nombreCompleto; }
    public void setNombreCompleto(String nombreCompleto) { this.nombreCompleto = nombreCompleto; }

    public String getCorreoContacto() { return correoContacto; }
    public void setCorreoContacto(String correoContacto) { this.correoContacto = correoContacto; }

    public String getAvatar() { return avatar; }
    public void setAvatar(String avatar) { this.avatar = avatar; }

    public List<ObraDigital> getObras() { return obras; }
    public void setObras(List<ObraDigital> obras) { this.obras = obras; }
}
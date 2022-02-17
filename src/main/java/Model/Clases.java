package Model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Clases")
public class Clases {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long idClases;

    @Column(name = "Clase")
    private String nomClase;

    @Column(name = "Controversia")
    private String controversia;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "lasClases")
    private Set<Razas> lasRazas;

    public Clases() {
    }

    public Clases(Long idClases, String nomClase, String controversia, Razas lasRazas) {
        this.idClases = idClases;
        this.nomClase = nomClase;
        this.controversia = controversia;
    }

    public Long getIdClases() {
        return idClases;
    }

    public void setIdClases(Long idClases) {
        this.idClases = idClases;
    }

    public String getNomClase() {
        return nomClase;
    }

    public void setNomClase(String nomClase) {
        this.nomClase = nomClase;
    }

    public String getControversia() {
        return controversia;
    }

    public void setControversia(String controversia) {
        this.controversia = controversia;
    }

    public Set<Razas> getLasRazas() {
        return lasRazas;
    }

    public void setLasRazas(Set<Razas> lasRazas) {
        this.lasRazas = lasRazas;
    }

    @Override
    public String toString() {
        return getIdClases()+", "+getNomClase()+", "+getControversia();
    }
}

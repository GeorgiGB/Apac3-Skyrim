package Model;

import net.bytebuddy.matcher.StringMatcher;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Razas")
public class Razas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idRazas")
    private Long idRazas;

    @Column(name = "nomRazas")
    private String nomRazas;

    @Column(name = "ventaja")
    private String ventaja;

    @OneToMany(mappedBy = "lasRazas", cascade = CascadeType.ALL)
    private Set<Jugador> losJugadores;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "Especialidad",
            joinColumns = {@JoinColumn(name = "Razas_idRazas")},
            inverseJoinColumns = {@JoinColumn(name = "Clases_idClases")}
    )
    private Set<Clases> lasClases;

    public Razas() {
    }

    public Razas(String nomRazas, String ventaja) {
        this.nomRazas = nomRazas;
        this.ventaja = ventaja;
    }

    public Long getIdRazas() {
        return idRazas;
    }

    public void setIdRazas(Long idRazas) {
        this.idRazas = idRazas;
    }

    public String getNomRazas() {
        return nomRazas;
    }

    public void setNomRazas(String nomRazas) {
        this.nomRazas = nomRazas;
    }

    public String getVentaja() {
        return ventaja;
    }

    public void setVentaja(String ventaja) {
        this.ventaja = ventaja;
    }

    public Set<Jugador> getLosJugadores() {
        return losJugadores;
    }

    public void setLosJugadores(Set<Jugador> losJugadores) {
        this.losJugadores = losJugadores;
    }

    public Set<Clases> getLasClases() {
        return lasClases;
    }

    public void setLasClases(Set<Clases> lasClases) {
        this.lasClases = lasClases;
    }

    @Override
    public String toString() {
        return idRazas+" "+nomRazas+" "+ventaja;
    }
}

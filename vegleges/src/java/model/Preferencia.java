/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Robi
 */
@Entity
@Table(name = "preferencia")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Preferencia.findAll", query = "SELECT p FROM Preferencia p")
    , @NamedQuery(name = "Preferencia.findByIdPreferencia", query = "SELECT p FROM Preferencia p WHERE p.idPreferencia = :idPreferencia")
    , @NamedQuery(name = "Preferencia.findByMegnevezes", query = "SELECT p FROM Preferencia p WHERE p.megnevezes = :megnevezes")})
public class Preferencia implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "preferencia")
    private List<UtasPreferencia> utasPreferenciaList;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idPreferencia")
    private Integer idPreferencia;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "megnevezes")
    private String megnevezes;
    @JoinTable(name = "ules_preferencia", joinColumns = {
        @JoinColumn(name = "idPreferencia", referencedColumnName = "idPreferencia")}, inverseJoinColumns = {
        @JoinColumn(name = "idULES", referencedColumnName = "idULES")
        , @JoinColumn(name = "idVAGON", referencedColumnName = "idVAGON")
        , @JoinColumn(name = "idVONAT", referencedColumnName = "idVONAT")})
    @ManyToMany
    private List<Ules> ulesList;
    @JoinTable(name = "utas_preferencia", joinColumns = {
        @JoinColumn(name = "idPreferencia", referencedColumnName = "idPreferencia")}, inverseJoinColumns = {
        @JoinColumn(name = "idUtas", referencedColumnName = "idUtas")})
    @ManyToMany
    private List<Utas> utasList;

    public Preferencia() {
    }

    public Preferencia(Integer idPreferencia) {
        this.idPreferencia = idPreferencia;
    }

    public Preferencia(Integer idPreferencia, String megnevezes) {
        this.idPreferencia = idPreferencia;
        this.megnevezes = megnevezes;
    }

    public Integer getIdPreferencia() {
        return idPreferencia;
    }

    public void setIdPreferencia(Integer idPreferencia) {
        this.idPreferencia = idPreferencia;
    }

    public String getMegnevezes() {
        return megnevezes;
    }

    public void setMegnevezes(String megnevezes) {
        this.megnevezes = megnevezes;
    }

    @XmlTransient
    public List<Ules> getUlesList() {
        return ulesList;
    }

    public void setUlesList(List<Ules> ulesList) {
        this.ulesList = ulesList;
    }

    @XmlTransient
    public List<Utas> getUtasList() {
        return utasList;
    }

    public void setUtasList(List<Utas> utasList) {
        this.utasList = utasList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPreferencia != null ? idPreferencia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Preferencia)) {
            return false;
        }
        Preferencia other = (Preferencia) object;
        if ((this.idPreferencia == null && other.idPreferencia != null) || (this.idPreferencia != null && !this.idPreferencia.equals(other.idPreferencia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Preferencia[ idPreferencia=" + idPreferencia + " ]";
    }

    @XmlTransient
    public List<UtasPreferencia> getUtasPreferenciaList() {
        return utasPreferenciaList;
    }

    public void setUtasPreferenciaList(List<UtasPreferencia> utasPreferenciaList) {
        this.utasPreferenciaList = utasPreferenciaList;
    }
    
}

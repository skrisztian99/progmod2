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
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Robi
 */
@Entity
@Table(name = "ules")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ules.findAll", query = "SELECT u FROM Ules u")
    , @NamedQuery(name = "Ules.findByIdULES", query = "SELECT u FROM Ules u WHERE u.ulesPK.idULES = :idULES")
    , @NamedQuery(name = "Ules.findByIdVAGON", query = "SELECT u FROM Ules u WHERE u.ulesPK.idVAGON = :idVAGON")
    , @NamedQuery(name = "Ules.findByIdVONAT", query = "SELECT u FROM Ules u WHERE u.ulesPK.idVONAT = :idVONAT")
    , @NamedQuery(name = "Ules.findByEgysegar", query = "SELECT u FROM Ules u WHERE u.egysegar = :egysegar")
    , @NamedQuery(name = "Ules.findUles", query = "SELECT u FROM Ules u WHERE u.ulesPK.idULES = :idULES AND u.ulesPK.idVAGON = :idVAGON AND u.ulesPK.idVONAT = :idVONAT")})
public class Ules implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected UlesPK ulesPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "egysegar")
    private int egysegar;
    @ManyToMany(mappedBy = "ulesList")
    private List<Preferencia> preferenciaList;
    @JoinColumns({
        @JoinColumn(name = "idVAGON", referencedColumnName = "idVAGON", insertable = false, updatable = false)
        , @JoinColumn(name = "idVONAT", referencedColumnName = "idVONAT", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Vagon vagon;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ules")
    private List<Jegy> jegyList;

    public Ules() {
    }

    public Ules(UlesPK ulesPK) {
        this.ulesPK = ulesPK;
    }

    public Ules(UlesPK ulesPK, int egysegar) {
        this.ulesPK = ulesPK;
        this.egysegar = egysegar;
    }

    public Ules(int idULES, int idVAGON, int idVONAT) {
        this.ulesPK = new UlesPK(idULES, idVAGON, idVONAT);
    }

    public UlesPK getUlesPK() {
        return ulesPK;
    }

    public void setUlesPK(UlesPK ulesPK) {
        this.ulesPK = ulesPK;
    }

    public int getEgysegar() {
        return egysegar;
    }

    public void setEgysegar(int egysegar) {
        this.egysegar = egysegar;
    }

    @XmlTransient
    public List<Preferencia> getPreferenciaList() {
        return preferenciaList;
    }

    public void setPreferenciaList(List<Preferencia> preferenciaList) {
        this.preferenciaList = preferenciaList;
    }

    public Vagon getVagon() {
        return vagon;
    }

    public void setVagon(Vagon vagon) {
        this.vagon = vagon;
    }

    @XmlTransient
    public List<Jegy> getJegyList() {
        return jegyList;
    }

    public void setJegyList(List<Jegy> jegyList) {
        this.jegyList = jegyList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ulesPK != null ? ulesPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ules)) {
            return false;
        }
        Ules other = (Ules) object;
        if ((this.ulesPK == null && other.ulesPK != null) || (this.ulesPK != null && !this.ulesPK.equals(other.ulesPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Ules[ ulesPK=" + ulesPK + " ]";
    }
    
}

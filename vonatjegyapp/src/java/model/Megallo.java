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
@Table(name = "megallo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Megallo.findAll", query = "SELECT m FROM Megallo m")
    , @NamedQuery(name = "Megallo.findByIdMEGALLO", query = "SELECT m FROM Megallo m WHERE m.idMEGALLO = :idMEGALLO")
    , @NamedQuery(name = "Megallo.findByNev", query = "SELECT m FROM Megallo m WHERE m.nev = :nev")})
public class Megallo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idMEGALLO")
    private Integer idMEGALLO;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "nev")
    private String nev;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "megallo")
    private List<JaratMegallo> jaratMegalloList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idHonnan")
    private List<Jegy> jegyList;

    public Megallo() {
    }

    public Megallo(Integer idMEGALLO) {
        this.idMEGALLO = idMEGALLO;
    }

    public Megallo(Integer idMEGALLO, String nev) {
        this.idMEGALLO = idMEGALLO;
        this.nev = nev;
    }

    public Integer getIdMEGALLO() {
        return idMEGALLO;
    }

    public void setIdMEGALLO(Integer idMEGALLO) {
        this.idMEGALLO = idMEGALLO;
    }

    public String getNev() {
        return nev;
    }

    public void setNev(String nev) {
        this.nev = nev;
    }

    @XmlTransient
    public List<JaratMegallo> getJaratMegalloList() {
        return jaratMegalloList;
    }

    public void setJaratMegalloList(List<JaratMegallo> jaratMegalloList) {
        this.jaratMegalloList = jaratMegalloList;
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
        hash += (idMEGALLO != null ? idMEGALLO.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Megallo)) {
            return false;
        }
        Megallo other = (Megallo) object;
        if ((this.idMEGALLO == null && other.idMEGALLO != null) || (this.idMEGALLO != null && !this.idMEGALLO.equals(other.idMEGALLO))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Megallo[ idMEGALLO=" + idMEGALLO + " ]";
    }
    
}

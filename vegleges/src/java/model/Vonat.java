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
import javax.persistence.Id;
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
@Table(name = "vonat")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Vonat.findAll", query = "SELECT v FROM Vonat v")
    , @NamedQuery(name = "Vonat.findByIdVONAT", query = "SELECT v FROM Vonat v WHERE v.idVONAT = :idVONAT")})
public class Vonat implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idVONAT")
    private Integer idVONAT;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "vonat")
    private List<Vagon> vagonList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "vonat")
    private List<VonatJaratIndulas> vonatJaratIndulasList;

    public Vonat() {
    }

    public Vonat(Integer idVONAT) {
        this.idVONAT = idVONAT;
    }

    public Integer getIdVONAT() {
        return idVONAT;
    }

    public void setIdVONAT(Integer idVONAT) {
        this.idVONAT = idVONAT;
    }

    @XmlTransient
    public List<Vagon> getVagonList() {
        return vagonList;
    }

    public void setVagonList(List<Vagon> vagonList) {
        this.vagonList = vagonList;
    }

    @XmlTransient
    public List<VonatJaratIndulas> getVonatJaratIndulasList() {
        return vonatJaratIndulasList;
    }

    public void setVonatJaratIndulasList(List<VonatJaratIndulas> vonatJaratIndulasList) {
        this.vonatJaratIndulasList = vonatJaratIndulasList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idVONAT != null ? idVONAT.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Vonat)) {
            return false;
        }
        Vonat other = (Vonat) object;
        if ((this.idVONAT == null && other.idVONAT != null) || (this.idVONAT != null && !this.idVONAT.equals(other.idVONAT))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Vonat[ idVONAT=" + idVONAT + " ]";
    }
    
}

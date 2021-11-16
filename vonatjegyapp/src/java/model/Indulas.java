/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Robi
 */
@Entity
@Table(name = "indulas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Indulas.findAll", query = "SELECT i FROM Indulas i")
    , @NamedQuery(name = "Indulas.findByIdINDULAS", query = "SELECT i FROM Indulas i WHERE i.idINDULAS = :idINDULAS")
    , @NamedQuery(name = "Indulas.findByIdopont", query = "SELECT i FROM Indulas i WHERE i.idopont = :idopont")})
public class Indulas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idINDULAS")
    private Integer idINDULAS;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idopont")
    @Temporal(TemporalType.TIMESTAMP)
    private Date idopont;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "indulas")
    private List<VonatJaratIndulas> vonatJaratIndulasList;

    public Indulas() {
    }

    public Indulas(Integer idINDULAS) {
        this.idINDULAS = idINDULAS;
    }

    public Indulas(Integer idINDULAS, Date idopont) {
        this.idINDULAS = idINDULAS;
        this.idopont = idopont;
    }

    public Integer getIdINDULAS() {
        return idINDULAS;
    }

    public void setIdINDULAS(Integer idINDULAS) {
        this.idINDULAS = idINDULAS;
    }

    public Date getIdopont() {
        return idopont;
    }

    public void setIdopont(Date idopont) {
        this.idopont = idopont;
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
        hash += (idINDULAS != null ? idINDULAS.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Indulas)) {
            return false;
        }
        Indulas other = (Indulas) object;
        if ((this.idINDULAS == null && other.idINDULAS != null) || (this.idINDULAS != null && !this.idINDULAS.equals(other.idINDULAS))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Indulas[ idINDULAS=" + idINDULAS + " ]";
    }
    
}

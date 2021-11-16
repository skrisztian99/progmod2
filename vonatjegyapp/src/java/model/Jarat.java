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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Robi
 */
@Entity
@Table(name = "jarat")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Jarat.findAll", query = "SELECT j FROM Jarat j")
    , @NamedQuery(name = "Jarat.findByIdJARAT", query = "SELECT j FROM Jarat j WHERE j.idJARAT = :idJARAT")
    , @NamedQuery(name = "Jarat.findByUtazastipus", query = "SELECT j FROM Jarat j WHERE j.utazastipus = :utazastipus")})
public class Jarat implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idJARAT")
    private Integer idJARAT;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "utazastipus")
    private String utazastipus;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "jarat")
    private List<JaratMegallo> jaratMegalloList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "jarat")
    private List<VonatJaratIndulas> vonatJaratIndulasList;

    public Jarat() {
    }

    public Jarat(Integer idJARAT) {
        this.idJARAT = idJARAT;
    }

    public Jarat(Integer idJARAT, String utazastipus) {
        this.idJARAT = idJARAT;
        this.utazastipus = utazastipus;
    }

    public Integer getIdJARAT() {
        return idJARAT;
    }

    public void setIdJARAT(Integer idJARAT) {
        this.idJARAT = idJARAT;
    }

    public String getUtazastipus() {
        return utazastipus;
    }

    public void setUtazastipus(String utazastipus) {
        this.utazastipus = utazastipus;
    }

    @XmlTransient
    public List<JaratMegallo> getJaratMegalloList() {
        return jaratMegalloList;
    }

    public void setJaratMegalloList(List<JaratMegallo> jaratMegalloList) {
        this.jaratMegalloList = jaratMegalloList;
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
        hash += (idJARAT != null ? idJARAT.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Jarat)) {
            return false;
        }
        Jarat other = (Jarat) object;
        if ((this.idJARAT == null && other.idJARAT != null) || (this.idJARAT != null && !this.idJARAT.equals(other.idJARAT))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Jarat[ idJARAT=" + idJARAT + " ]";
    }
    
}

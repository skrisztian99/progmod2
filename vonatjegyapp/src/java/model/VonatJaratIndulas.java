/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Robi
 */
@Entity
@Table(name = "vonat_jarat_indulas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VonatJaratIndulas.findAll", query = "SELECT v FROM VonatJaratIndulas v")
    , @NamedQuery(name = "VonatJaratIndulas.findByIdVONAT", query = "SELECT v FROM VonatJaratIndulas v WHERE v.vonatJaratIndulasPK.idVONAT = :idVONAT")
    , @NamedQuery(name = "VonatJaratIndulas.findByIdINDULAS", query = "SELECT v FROM VonatJaratIndulas v WHERE v.vonatJaratIndulasPK.idINDULAS = :idINDULAS")
    , @NamedQuery(name = "VonatJaratIndulas.findByJARATidJARAT", query = "SELECT v FROM VonatJaratIndulas v WHERE v.vonatJaratIndulasPK.jARATidJARAT = :jARATidJARAT")})
public class VonatJaratIndulas implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected VonatJaratIndulasPK vonatJaratIndulasPK;
    @JoinColumn(name = "idINDULAS", referencedColumnName = "idINDULAS", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Indulas indulas;
    @JoinColumn(name = "JARAT_idJARAT", referencedColumnName = "idJARAT", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Jarat jarat;
    @JoinColumn(name = "idVONAT", referencedColumnName = "idVONAT", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Vonat vonat;

    public VonatJaratIndulas() {
    }

    public VonatJaratIndulas(VonatJaratIndulasPK vonatJaratIndulasPK) {
        this.vonatJaratIndulasPK = vonatJaratIndulasPK;
    }

    public VonatJaratIndulas(int idVONAT, int idINDULAS, int jARATidJARAT) {
        this.vonatJaratIndulasPK = new VonatJaratIndulasPK(idVONAT, idINDULAS, jARATidJARAT);
    }

    public VonatJaratIndulasPK getVonatJaratIndulasPK() {
        return vonatJaratIndulasPK;
    }

    public void setVonatJaratIndulasPK(VonatJaratIndulasPK vonatJaratIndulasPK) {
        this.vonatJaratIndulasPK = vonatJaratIndulasPK;
    }

    public Indulas getIndulas() {
        return indulas;
    }

    public void setIndulas(Indulas indulas) {
        this.indulas = indulas;
    }

    public Jarat getJarat() {
        return jarat;
    }

    public void setJarat(Jarat jarat) {
        this.jarat = jarat;
    }

    public Vonat getVonat() {
        return vonat;
    }

    public void setVonat(Vonat vonat) {
        this.vonat = vonat;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (vonatJaratIndulasPK != null ? vonatJaratIndulasPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VonatJaratIndulas)) {
            return false;
        }
        VonatJaratIndulas other = (VonatJaratIndulas) object;
        if ((this.vonatJaratIndulasPK == null && other.vonatJaratIndulasPK != null) || (this.vonatJaratIndulasPK != null && !this.vonatJaratIndulasPK.equals(other.vonatJaratIndulasPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.VonatJaratIndulas[ vonatJaratIndulasPK=" + vonatJaratIndulasPK + " ]";
    }
    
}

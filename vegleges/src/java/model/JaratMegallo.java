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
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "jarat_megallo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "JaratMegallo.findAll", query = "SELECT j FROM JaratMegallo j")
    , @NamedQuery(name = "JaratMegallo.findByIdMEGALLO", query = "SELECT j FROM JaratMegallo j WHERE j.jaratMegalloPK.idMEGALLO = :idMEGALLO")
    , @NamedQuery(name = "JaratMegallo.findByMenetido", query = "SELECT j FROM JaratMegallo j WHERE j.menetido = :menetido")
    , @NamedQuery(name = "JaratMegallo.findByTavolsag", query = "SELECT j FROM JaratMegallo j WHERE j.tavolsag = :tavolsag")
    , @NamedQuery(name = "JaratMegallo.findByJARATidJARAT", query = "SELECT j FROM JaratMegallo j WHERE j.jaratMegalloPK.jARATidJARAT = :jARATidJARAT")})
public class JaratMegallo implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected JaratMegalloPK jaratMegalloPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "menetido")
    @Temporal(TemporalType.TIME)
    private Date menetido;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tavolsag")
    private int tavolsag;
    @JoinColumn(name = "JARAT_idJARAT", referencedColumnName = "idJARAT", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Jarat jarat;
    @JoinColumn(name = "idMEGALLO", referencedColumnName = "idMEGALLO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Megallo megallo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "jaratMegallo")
    private List<Jegy> jegyList;

    public JaratMegallo() {
    }

    public JaratMegallo(JaratMegalloPK jaratMegalloPK) {
        this.jaratMegalloPK = jaratMegalloPK;
    }

    public JaratMegallo(JaratMegalloPK jaratMegalloPK, Date menetido, int tavolsag) {
        this.jaratMegalloPK = jaratMegalloPK;
        this.menetido = menetido;
        this.tavolsag = tavolsag;
    }

    public JaratMegallo(int idMEGALLO, int jARATidJARAT) {
        this.jaratMegalloPK = new JaratMegalloPK(idMEGALLO, jARATidJARAT);
    }

    public JaratMegalloPK getJaratMegalloPK() {
        return jaratMegalloPK;
    }

    public void setJaratMegalloPK(JaratMegalloPK jaratMegalloPK) {
        this.jaratMegalloPK = jaratMegalloPK;
    }

    public Date getMenetido() {
        return menetido;
    }

    public void setMenetido(Date menetido) {
        this.menetido = menetido;
    }

    public int getTavolsag() {
        return tavolsag;
    }

    public void setTavolsag(int tavolsag) {
        this.tavolsag = tavolsag;
    }

    public Jarat getJarat() {
        return jarat;
    }

    public void setJarat(Jarat jarat) {
        this.jarat = jarat;
    }

    public Megallo getMegallo() {
        return megallo;
    }

    public void setMegallo(Megallo megallo) {
        this.megallo = megallo;
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
        hash += (jaratMegalloPK != null ? jaratMegalloPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof JaratMegallo)) {
            return false;
        }
        JaratMegallo other = (JaratMegallo) object;
        if ((this.jaratMegalloPK == null && other.jaratMegalloPK != null) || (this.jaratMegalloPK != null && !this.jaratMegalloPK.equals(other.jaratMegalloPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.JaratMegallo[ jaratMegalloPK=" + jaratMegalloPK + " ]";
    }
    
}

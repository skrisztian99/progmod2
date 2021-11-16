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
@Table(name = "vagon")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Vagon.findAll", query = "SELECT v FROM Vagon v")
    , @NamedQuery(name = "Vagon.findByIdVAGON", query = "SELECT v FROM Vagon v WHERE v.vagonPK.idVAGON = :idVAGON")
    , @NamedQuery(name = "Vagon.findByFerohely", query = "SELECT v FROM Vagon v WHERE v.ferohely = :ferohely")
    , @NamedQuery(name = "Vagon.findByCsomag", query = "SELECT v FROM Vagon v WHERE v.csomag = :csomag")
    , @NamedQuery(name = "Vagon.findByOsztaly", query = "SELECT v FROM Vagon v WHERE v.osztaly = :osztaly")
    , @NamedQuery(name = "Vagon.findByIdVONAT", query = "SELECT v FROM Vagon v WHERE v.vagonPK.idVONAT = :idVONAT")})
public class Vagon implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected VagonPK vagonPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ferohely")
    private short ferohely;
    @Basic(optional = false)
    @NotNull
    @Column(name = "csomag")
    private short csomag;
    @Column(name = "osztaly")
    private Character osztaly;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "vagon")
    private List<Ules> ulesList;
    @JoinColumn(name = "idVONAT", referencedColumnName = "idVONAT", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Vonat vonat;

    public Vagon() {
    }

    public Vagon(VagonPK vagonPK) {
        this.vagonPK = vagonPK;
    }

    public Vagon(VagonPK vagonPK, short ferohely, short csomag) {
        this.vagonPK = vagonPK;
        this.ferohely = ferohely;
        this.csomag = csomag;
    }

    public Vagon(int idVAGON, int idVONAT) {
        this.vagonPK = new VagonPK(idVAGON, idVONAT);
    }

    public VagonPK getVagonPK() {
        return vagonPK;
    }

    public void setVagonPK(VagonPK vagonPK) {
        this.vagonPK = vagonPK;
    }

    public short getFerohely() {
        return ferohely;
    }

    public void setFerohely(short ferohely) {
        this.ferohely = ferohely;
    }

    public short getCsomag() {
        return csomag;
    }

    public void setCsomag(short csomag) {
        this.csomag = csomag;
    }

    public Character getOsztaly() {
        return osztaly;
    }

    public void setOsztaly(Character osztaly) {
        this.osztaly = osztaly;
    }

    @XmlTransient
    public List<Ules> getUlesList() {
        return ulesList;
    }

    public void setUlesList(List<Ules> ulesList) {
        this.ulesList = ulesList;
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
        hash += (vagonPK != null ? vagonPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Vagon)) {
            return false;
        }
        Vagon other = (Vagon) object;
        if ((this.vagonPK == null && other.vagonPK != null) || (this.vagonPK != null && !this.vagonPK.equals(other.vagonPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Vagon[ vagonPK=" + vagonPK + " ]";
    }
    
}

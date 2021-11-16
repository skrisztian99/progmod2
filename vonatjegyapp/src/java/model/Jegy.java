/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Robi
 */
@Entity
@Table(name = "jegy")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Jegy.findAll", query = "SELECT j FROM Jegy j")
    , @NamedQuery(name = "Jegy.findByIdJEGY", query = "SELECT j FROM Jegy j WHERE j.idJEGY = :idJEGY")
    , @NamedQuery(name = "Jegy.findByAr", query = "SELECT j FROM Jegy j WHERE j.ar = :ar")
    , @NamedQuery(name = "Jegy.findByIndulasidopont", query = "SELECT j FROM Jegy j WHERE j.indulasidopont = :indulasidopont")
    , @NamedQuery(name = "Jegy.findByKiallitasidatum", query = "SELECT j FROM Jegy j WHERE j.kiallitasidatum = :kiallitasidatum")})
public class Jegy implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idJEGY")
    private Integer idJEGY;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ar")
    private int ar;
    @Basic(optional = false)
    @NotNull
    @Column(name = "indulasidopont")
    @Temporal(TemporalType.TIMESTAMP)
    private Date indulasidopont;
    @Basic(optional = false)
    @NotNull
    @Column(name = "kiallitasidatum")
    @Temporal(TemporalType.TIMESTAMP)
    private Date kiallitasidatum;
    @JoinColumns({
        @JoinColumn(name = "idHova", referencedColumnName = "idMEGALLO")
        , @JoinColumn(name = "idJARAT", referencedColumnName = "JARAT_idJARAT")})
    @ManyToOne(optional = false)
    private JaratMegallo jaratMegallo;
    @JoinColumn(name = "idHonnan", referencedColumnName = "idMEGALLO")
    @ManyToOne(optional = false)
    private Megallo idHonnan;
    @JoinColumns({
        @JoinColumn(name = "idULES", referencedColumnName = "idULES")
        , @JoinColumn(name = "idVAGON", referencedColumnName = "idVAGON")
        , @JoinColumn(name = "idVONAT", referencedColumnName = "idVONAT")})
    @ManyToOne(optional = false)
    private Ules ules;
    @JoinColumn(name = "idUtas", referencedColumnName = "idUtas")
    @ManyToOne(optional = false)
    private Utas idUtas;

    public Jegy() {
    }

    public Jegy(Integer idJEGY) {
        this.idJEGY = idJEGY;
    }

    public Jegy(Integer idJEGY, int ar, Date indulasidopont, Date kiallitasidatum) {
        this.idJEGY = idJEGY;
        this.ar = ar;
        this.indulasidopont = indulasidopont;
        this.kiallitasidatum = kiallitasidatum;
    }

    public Integer getIdJEGY() {
        return idJEGY;
    }

    public void setIdJEGY(Integer idJEGY) {
        this.idJEGY = idJEGY;
    }

    public int getAr() {
        return ar;
    }

    public void setAr(int ar) {
        this.ar = ar;
    }

    public Date getIndulasidopont() {
        return indulasidopont;
    }

    public void setIndulasidopont(Date indulasidopont) {
        this.indulasidopont = indulasidopont;
    }

    public Date getKiallitasidatum() {
        return kiallitasidatum;
    }

    public void setKiallitasidatum(Date kiallitasidatum) {
        this.kiallitasidatum = kiallitasidatum;
    }

    public JaratMegallo getJaratMegallo() {
        return jaratMegallo;
    }

    public void setJaratMegallo(JaratMegallo jaratMegallo) {
        this.jaratMegallo = jaratMegallo;
    }

    public Megallo getIdHonnan() {
        return idHonnan;
    }

    public void setIdHonnan(Megallo idHonnan) {
        this.idHonnan = idHonnan;
    }

    public Ules getUles() {
        return ules;
    }

    public void setUles(Ules ules) {
        this.ules = ules;
    }

    public Utas getIdUtas() {
        return idUtas;
    }

    public void setIdUtas(Utas idUtas) {
        this.idUtas = idUtas;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idJEGY != null ? idJEGY.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Jegy)) {
            return false;
        }
        Jegy other = (Jegy) object;
        if ((this.idJEGY == null && other.idJEGY != null) || (this.idJEGY != null && !this.idJEGY.equals(other.idJEGY))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Jegy[ idJEGY=" + idJEGY + " ]";
    }
    
}

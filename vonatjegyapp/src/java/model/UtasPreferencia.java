/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.persistence.Column;
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
@Table(name = "utas_preferencia")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UtasPreferencia.findAll", query = "SELECT u FROM UtasPreferencia u")
    , @NamedQuery(name = "UtasPreferencia.findByIdUtas", query = "SELECT u FROM UtasPreferencia u WHERE u.utasPreferenciaPK.idUtas = :idUtas")
    , @NamedQuery(name = "UtasPreferencia.findByIdPreferencia", query = "SELECT u FROM UtasPreferencia u WHERE u.utasPreferenciaPK.idPreferencia = :idPreferencia")
    , @NamedQuery(name = "UtasPreferencia.findByFontossag", query = "SELECT u FROM UtasPreferencia u WHERE u.fontossag = :fontossag")})
public class UtasPreferencia implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected UtasPreferenciaPK utasPreferenciaPK;
    @Column(name = "fontossag")
    private Integer fontossag;
    @JoinColumn(name = "idPreferencia", referencedColumnName = "idPreferencia", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Preferencia preferencia;
    @JoinColumn(name = "idUtas", referencedColumnName = "idUtas", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Utas utas;

    public UtasPreferencia() {
    }

    public UtasPreferencia(UtasPreferenciaPK utasPreferenciaPK) {
        this.utasPreferenciaPK = utasPreferenciaPK;
    }

    public UtasPreferencia(int idUtas, int idPreferencia) {
        this.utasPreferenciaPK = new UtasPreferenciaPK(idUtas, idPreferencia);
    }

    public UtasPreferenciaPK getUtasPreferenciaPK() {
        return utasPreferenciaPK;
    }

    public void setUtasPreferenciaPK(UtasPreferenciaPK utasPreferenciaPK) {
        this.utasPreferenciaPK = utasPreferenciaPK;
    }

    public Integer getFontossag() {
        return fontossag;
    }

    public void setFontossag(Integer fontossag) {
        this.fontossag = fontossag;
    }

    public Preferencia getPreferencia() {
        return preferencia;
    }

    public void setPreferencia(Preferencia preferencia) {
        this.preferencia = preferencia;
    }

    public Utas getUtas() {
        return utas;
    }

    public void setUtas(Utas utas) {
        this.utas = utas;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (utasPreferenciaPK != null ? utasPreferenciaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UtasPreferencia)) {
            return false;
        }
        UtasPreferencia other = (UtasPreferencia) object;
        if ((this.utasPreferenciaPK == null && other.utasPreferenciaPK != null) || (this.utasPreferenciaPK != null && !this.utasPreferenciaPK.equals(other.utasPreferenciaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.UtasPreferencia[ utasPreferenciaPK=" + utasPreferenciaPK + " ]";
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Robi
 */
@Embeddable
public class UtasPreferenciaPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "idUtas")
    private int idUtas;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idPreferencia")
    private int idPreferencia;

    public UtasPreferenciaPK() {
    }

    public UtasPreferenciaPK(int idUtas, int idPreferencia) {
        this.idUtas = idUtas;
        this.idPreferencia = idPreferencia;
    }

    public int getIdUtas() {
        return idUtas;
    }

    public void setIdUtas(int idUtas) {
        this.idUtas = idUtas;
    }

    public int getIdPreferencia() {
        return idPreferencia;
    }

    public void setIdPreferencia(int idPreferencia) {
        this.idPreferencia = idPreferencia;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idUtas;
        hash += (int) idPreferencia;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UtasPreferenciaPK)) {
            return false;
        }
        UtasPreferenciaPK other = (UtasPreferenciaPK) object;
        if (this.idUtas != other.idUtas) {
            return false;
        }
        if (this.idPreferencia != other.idPreferencia) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.UtasPreferenciaPK[ idUtas=" + idUtas + ", idPreferencia=" + idPreferencia + " ]";
    }
    
}

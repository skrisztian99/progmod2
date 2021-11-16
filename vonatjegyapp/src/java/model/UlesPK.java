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
public class UlesPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "idULES")
    private int idULES;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idVAGON")
    private int idVAGON;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idVONAT")
    private int idVONAT;

    public UlesPK() {
    }

    public UlesPK(int idULES, int idVAGON, int idVONAT) {
        this.idULES = idULES;
        this.idVAGON = idVAGON;
        this.idVONAT = idVONAT;
    }

    public int getIdULES() {
        return idULES;
    }

    public void setIdULES(int idULES) {
        this.idULES = idULES;
    }

    public int getIdVAGON() {
        return idVAGON;
    }

    public void setIdVAGON(int idVAGON) {
        this.idVAGON = idVAGON;
    }

    public int getIdVONAT() {
        return idVONAT;
    }

    public void setIdVONAT(int idVONAT) {
        this.idVONAT = idVONAT;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idULES;
        hash += (int) idVAGON;
        hash += (int) idVONAT;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UlesPK)) {
            return false;
        }
        UlesPK other = (UlesPK) object;
        if (this.idULES != other.idULES) {
            return false;
        }
        if (this.idVAGON != other.idVAGON) {
            return false;
        }
        if (this.idVONAT != other.idVONAT) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.UlesPK[ idULES=" + idULES + ", idVAGON=" + idVAGON + ", idVONAT=" + idVONAT + " ]";
    }
    
}

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
public class VagonPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "idVAGON")
    private int idVAGON;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idVONAT")
    private int idVONAT;

    public VagonPK() {
    }

    public VagonPK(int idVAGON, int idVONAT) {
        this.idVAGON = idVAGON;
        this.idVONAT = idVONAT;
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
        hash += (int) idVAGON;
        hash += (int) idVONAT;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VagonPK)) {
            return false;
        }
        VagonPK other = (VagonPK) object;
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
        return "model.VagonPK[ idVAGON=" + idVAGON + ", idVONAT=" + idVONAT + " ]";
    }
    
}

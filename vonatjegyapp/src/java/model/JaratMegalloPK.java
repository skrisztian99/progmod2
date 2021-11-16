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
public class JaratMegalloPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "idMEGALLO")
    private int idMEGALLO;
    @Basic(optional = false)
    @NotNull
    @Column(name = "JARAT_idJARAT")
    private int jARATidJARAT;

    public JaratMegalloPK() {
    }

    public JaratMegalloPK(int idMEGALLO, int jARATidJARAT) {
        this.idMEGALLO = idMEGALLO;
        this.jARATidJARAT = jARATidJARAT;
    }

    public int getIdMEGALLO() {
        return idMEGALLO;
    }

    public void setIdMEGALLO(int idMEGALLO) {
        this.idMEGALLO = idMEGALLO;
    }

    public int getJARATidJARAT() {
        return jARATidJARAT;
    }

    public void setJARATidJARAT(int jARATidJARAT) {
        this.jARATidJARAT = jARATidJARAT;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idMEGALLO;
        hash += (int) jARATidJARAT;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof JaratMegalloPK)) {
            return false;
        }
        JaratMegalloPK other = (JaratMegalloPK) object;
        if (this.idMEGALLO != other.idMEGALLO) {
            return false;
        }
        if (this.jARATidJARAT != other.jARATidJARAT) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.JaratMegalloPK[ idMEGALLO=" + idMEGALLO + ", jARATidJARAT=" + jARATidJARAT + " ]";
    }
    
}

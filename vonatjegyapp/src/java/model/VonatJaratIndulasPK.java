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
public class VonatJaratIndulasPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "idVONAT")
    private int idVONAT;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idINDULAS")
    private int idINDULAS;
    @Basic(optional = false)
    @NotNull
    @Column(name = "JARAT_idJARAT")
    private int jARATidJARAT;

    public VonatJaratIndulasPK() {
    }

    public VonatJaratIndulasPK(int idVONAT, int idINDULAS, int jARATidJARAT) {
        this.idVONAT = idVONAT;
        this.idINDULAS = idINDULAS;
        this.jARATidJARAT = jARATidJARAT;
    }

    public int getIdVONAT() {
        return idVONAT;
    }

    public void setIdVONAT(int idVONAT) {
        this.idVONAT = idVONAT;
    }

    public int getIdINDULAS() {
        return idINDULAS;
    }

    public void setIdINDULAS(int idINDULAS) {
        this.idINDULAS = idINDULAS;
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
        hash += (int) idVONAT;
        hash += (int) idINDULAS;
        hash += (int) jARATidJARAT;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VonatJaratIndulasPK)) {
            return false;
        }
        VonatJaratIndulasPK other = (VonatJaratIndulasPK) object;
        if (this.idVONAT != other.idVONAT) {
            return false;
        }
        if (this.idINDULAS != other.idINDULAS) {
            return false;
        }
        if (this.jARATidJARAT != other.jARATidJARAT) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.VonatJaratIndulasPK[ idVONAT=" + idVONAT + ", idINDULAS=" + idINDULAS + ", jARATidJARAT=" + jARATidJARAT + " ]";
    }
    
}

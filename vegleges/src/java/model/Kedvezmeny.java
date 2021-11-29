/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Persistence;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Robi
 */
@Entity
@Table(name = "kedvezmeny")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Kedvezmeny.findAll", query = "SELECT k FROM Kedvezmeny k")
    , @NamedQuery(name = "Kedvezmeny.findByIdKedvezmeny", query = "SELECT k FROM Kedvezmeny k WHERE k.idKedvezmeny = :idKedvezmeny")
    , @NamedQuery(name = "Kedvezmeny.findByMegnevezes", query = "SELECT k FROM Kedvezmeny k WHERE k.megnevezes = :megnevezes")
    , @NamedQuery(name = "Kedvezmeny.findByMertek", query = "SELECT k FROM Kedvezmeny k WHERE k.mertek = :mertek")})
public class Kedvezmeny implements Serializable, Dao {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idKedvezmeny")
    private Integer idKedvezmeny;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "megnevezes")
    private String megnevezes;
    @Basic(optional = false)
    @NotNull
    @Column(name = "mertek")
    private float mertek;
    @OneToMany(mappedBy = "kEDVEZMENYidKedvezmeny")
    private List<Utas> utasList;

    public Kedvezmeny() {
    }

    public Kedvezmeny(Integer idKedvezmeny) {
        this.idKedvezmeny = idKedvezmeny;
    }

    public Kedvezmeny(Integer idKedvezmeny, String megnevezes, float mertek) {
        this.idKedvezmeny = idKedvezmeny;
        this.megnevezes = megnevezes;
        this.mertek = mertek;
    }

    public Integer getIdKedvezmeny() {
        return idKedvezmeny;
    }

    public void setIdKedvezmeny(Integer idKedvezmeny) {
        this.idKedvezmeny = idKedvezmeny;
    }

    public String getMegnevezes() {
        return megnevezes;
    }

    public void setMegnevezes(String megnevezes) {
        this.megnevezes = megnevezes;
    }

    public float getMertek() {
        return mertek;
    }

    public void setMertek(float mertek) {
        this.mertek = mertek;
    }

    @XmlTransient
    public List<Utas> getUtasList() {
        return utasList;
    }

    public void setUtasList(List<Utas> utasList) {
        this.utasList = utasList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idKedvezmeny != null ? idKedvezmeny.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Kedvezmeny)) {
            return false;
        }
        Kedvezmeny other = (Kedvezmeny) object;
        if ((this.idKedvezmeny == null && other.idKedvezmeny != null) || (this.idKedvezmeny != null && !this.idKedvezmeny.equals(other.idKedvezmeny))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Kedvezmeny[ idKedvezmeny=" + idKedvezmeny + " ]";
    }
    
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("vonatjegyappPU");
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    private EntityManager entityManager = getEntityManager();
    @Override
    public List<Kedvezmeny> getAll(){
        return entityManager.createNamedQuery("Kedvezmeny.findAll").getResultList();
    }
    @Override 
    public Kedvezmeny getById(Integer id){
        return entityManager.find(Kedvezmeny.class, id);
    }
    @Override
    public Kedvezmeny getByString(String nev){
        return (Kedvezmeny) entityManager.createNamedQuery("Kedvezmeny.findByMegnevezes").setParameter("megnevezes", nev).getSingleResult();
    }
    
}

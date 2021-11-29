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
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Persistence;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Robi
 */
@Entity
@Table(name = "utas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Utas.findAll", query = "SELECT u FROM Utas u")
    , @NamedQuery(name = "Utas.findByIdUtas", query = "SELECT u FROM Utas u WHERE u.idUtas = :idUtas")
    , @NamedQuery(name = "Utas.findByVezeteknev", query = "SELECT u FROM Utas u WHERE u.vezeteknev = :vezeteknev")
    , @NamedQuery(name = "Utas.findByKersztnev", query = "SELECT u FROM Utas u WHERE u.kersztnev = :kersztnev")
    , @NamedQuery(name = "Utas.findByTelefon", query = "SELECT u FROM Utas u WHERE u.telefon = :telefon")
    , @NamedQuery(name = "Utas.findByEmail", query = "SELECT u FROM Utas u WHERE u.email = :email")
    , @NamedQuery(name = "Utas.findBySzulido", query = "SELECT u FROM Utas u WHERE u.szulido = :szulido")
    , @NamedQuery(name = "Utas.findByIranyitoszam", query = "SELECT u FROM Utas u WHERE u.iranyitoszam = :iranyitoszam")
    , @NamedQuery(name = "Utas.findByVaros", query = "SELECT u FROM Utas u WHERE u.varos = :varos")
    , @NamedQuery(name = "Utas.findByUtca", query = "SELECT u FROM Utas u WHERE u.utca = :utca")
    , @NamedQuery(name = "Utas.findByHazszam", query = "SELECT u FROM Utas u WHERE u.hazszam = :hazszam")
    , @NamedQuery(name = "Utas.findByJelszo", query = "SELECT u FROM Utas u WHERE u.jelszo = :jelszo")
    , @NamedQuery(name = "Utas.checkLogin", query = "SELECT u FROM Utas u WHERE u.email = :email AND u.jelszo = :jelszo")})
public class Utas implements Serializable, Dao{

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "utas")
    private List<UtasPreferencia> utasPreferenciaList;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idUtas")
    private Integer idUtas;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "vezeteknev")
    private String vezeteknev;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "kersztnev")
    private String kersztnev;
    @Size(max = 12)
    @Column(name = "telefon")
    private String telefon;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @NotNull
    @Column(name = "szulido")
    @Temporal(TemporalType.DATE)
    private Date szulido;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4)
    @Column(name = "iranyitoszam")
    private String iranyitoszam;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "varos")
    private String varos;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "utca")
    private String utca;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "hazszam")
    private String hazszam;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "jelszo")
    private String jelszo;
    @ManyToMany(mappedBy = "utasList")
    private List<Preferencia> preferenciaList;
    @JoinColumn(name = "KEDVEZMENY_idKedvezmeny", referencedColumnName = "idKedvezmeny")
    @ManyToOne
    private Kedvezmeny kEDVEZMENYidKedvezmeny;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUtas")
    private List<Jegy> jegyList;

    public Utas() {
    }

    public Utas(Integer idUtas) {
        this.idUtas = idUtas;
    }

    public Utas(Integer idUtas, String vezeteknev, String kersztnev, String email, Date szulido, String iranyitoszam, String varos, String utca, String hazszam, String jelszo) {
        this.idUtas = idUtas;
        this.vezeteknev = vezeteknev;
        this.kersztnev = kersztnev;
        this.email = email;
        this.szulido = szulido;
        this.iranyitoszam = iranyitoszam;
        this.varos = varos;
        this.utca = utca;
        this.hazszam = hazszam;
        this.jelszo = jelszo;
    }

    public Integer getIdUtas() {
        return idUtas;
    }

    public void setIdUtas(Integer idUtas) {
        this.idUtas = idUtas;
    }

    public String getVezeteknev() {
        return vezeteknev;
    }

    public void setVezeteknev(String vezeteknev) {
        this.vezeteknev = vezeteknev;
    }

    public String getKersztnev() {
        return kersztnev;
    }

    public void setKersztnev(String kersztnev) {
        this.kersztnev = kersztnev;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getSzulido() {
        return szulido;
    }

    public void setSzulido(Date szulido) {
        this.szulido = szulido;
    }

    public String getIranyitoszam() {
        return iranyitoszam;
    }

    public void setIranyitoszam(String iranyitoszam) {
        this.iranyitoszam = iranyitoszam;
    }

    public String getVaros() {
        return varos;
    }

    public void setVaros(String varos) {
        this.varos = varos;
    }

    public String getUtca() {
        return utca;
    }

    public void setUtca(String utca) {
        this.utca = utca;
    }

    public String getHazszam() {
        return hazszam;
    }

    public void setHazszam(String hazszam) {
        this.hazszam = hazszam;
    }

    public String getJelszo() {
        return jelszo;
    }

    public void setJelszo(String jelszo) {
        this.jelszo = jelszo;
    }

    @XmlTransient
    public List<Preferencia> getPreferenciaList() {
        return preferenciaList;
    }

    public void setPreferenciaList(List<Preferencia> preferenciaList) {
        this.preferenciaList = preferenciaList;
    }

    public Kedvezmeny getKEDVEZMENYidKedvezmeny() {
        return kEDVEZMENYidKedvezmeny;
    }

    public void setKEDVEZMENYidKedvezmeny(Kedvezmeny kEDVEZMENYidKedvezmeny) {
        this.kEDVEZMENYidKedvezmeny = kEDVEZMENYidKedvezmeny;
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
        hash += (idUtas != null ? idUtas.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Utas)) {
            return false;
        }
        Utas other = (Utas) object;
        if ((this.idUtas == null && other.idUtas != null) || (this.idUtas != null && !this.idUtas.equals(other.idUtas))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Utas[ idUtas=" + idUtas + " ]";
    }

    @XmlTransient
    public List<UtasPreferencia> getUtasPreferenciaList() {
        return utasPreferenciaList;
    }

    public void setUtasPreferenciaList(List<UtasPreferencia> utasPreferenciaList) {
        this.utasPreferenciaList = utasPreferenciaList;
    }
    
    
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("vonatjegyappPU");
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    private EntityManager entityManager = getEntityManager();
    
    @Override
    public List<Utas> getAll(){
        return entityManager.createNamedQuery("Utas.findAll").getResultList();
    }
    @Override
    public Utas getById(Integer id){
        return entityManager.find(Utas.class, id);
    }
    @Override
    public Utas getByString(String email){
        return (Utas) entityManager.createNamedQuery("Utas.findByEmail").setParameter("email", email).getSingleResult();
    }
    
    public static void save(Utas utas){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("vonatjegyappPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(utas);
        Kedvezmeny KEDVEZMENYidKedvezmeny = utas.getKEDVEZMENYidKedvezmeny();
        if (KEDVEZMENYidKedvezmeny != null) {
            KEDVEZMENYidKedvezmeny.getUtasList().add(utas);
            KEDVEZMENYidKedvezmeny = em.merge(KEDVEZMENYidKedvezmeny);
        }
        for (UtasPreferencia utasPreferenciaListUtasPreferencia : utas.getUtasPreferenciaList()) {
            Utas oldUtasOfUtasPreferenciaListUtasPreferencia = utasPreferenciaListUtasPreferencia.getUtas();
            utasPreferenciaListUtasPreferencia.setUtas(utas);
            utasPreferenciaListUtasPreferencia = em.merge(utasPreferenciaListUtasPreferencia);
            if (oldUtasOfUtasPreferenciaListUtasPreferencia != null) {
                oldUtasOfUtasPreferenciaListUtasPreferencia.getUtasPreferenciaList().remove(utasPreferenciaListUtasPreferencia);
                oldUtasOfUtasPreferenciaListUtasPreferencia = em.merge(oldUtasOfUtasPreferenciaListUtasPreferencia);
            }
        }
        em.getTransaction().commit();
        em.close();
    }
    
    public static Utas login(String email){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("vonatjegyappPU");
        EntityManager em = emf.createEntityManager();
        Utas u = (Utas) em.createNamedQuery("Utas.findByEmail").setParameter("email", email).getResultList().get(0);
        return u;
    }
    
}

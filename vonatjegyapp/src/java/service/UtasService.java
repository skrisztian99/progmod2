package service;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import model.Jegy;
import model.Kedvezmeny;
import model.Utas;
import model.UtasPreferencia;

public class UtasService {
    public UtasService(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public Boolean create(Utas utas) throws Exception {
        if (utas.getUtasPreferenciaList() == null) {
            utas.setUtasPreferenciaList(new ArrayList<UtasPreferencia>());
        }
        EntityManager em = null;
        try {
            em = utas.getEntityManager();
            Kedvezmeny KEDVEZMENYidKedvezmeny = utas.getKEDVEZMENYidKedvezmeny();
            if (KEDVEZMENYidKedvezmeny != null) {
                KEDVEZMENYidKedvezmeny = em.getReference(KEDVEZMENYidKedvezmeny.getClass(), KEDVEZMENYidKedvezmeny.getIdKedvezmeny());
                utas.setKEDVEZMENYidKedvezmeny(KEDVEZMENYidKedvezmeny);
            }
            List<UtasPreferencia> attachedUtasPreferenciaList = new ArrayList<UtasPreferencia>();
            for (UtasPreferencia utasPreferenciaListUtasPreferenciaToAttach : utas.getUtasPreferenciaList()) {
                utasPreferenciaListUtasPreferenciaToAttach = em.getReference(utasPreferenciaListUtasPreferenciaToAttach.getClass(), utasPreferenciaListUtasPreferenciaToAttach.getUtasPreferenciaPK());
                attachedUtasPreferenciaList.add(utasPreferenciaListUtasPreferenciaToAttach);
            }
            utas.setUtasPreferenciaList(attachedUtasPreferenciaList);
            Utas.save(utas);
            return Boolean.TRUE;
        } catch (Exception ex) {
            return Boolean.FALSE;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
    public Utas checkLogin(String email, String jelszo){
        EntityManager em = getEntityManager();
        Utas u = null;
        if(!em.createNamedQuery("Utas.findByEmail").setParameter("email", email).getResultList().isEmpty()){
            u = Utas.login(email, jelszo);
        }
        return u;
    }
    
    public Utas findUtas(Integer id) {
        EntityManager em = getEntityManager();
        try {
            Utas u = new Utas();
            if(!em.createNamedQuery("Utas.findByIdUtas").setParameter("id", id).getResultList().isEmpty()){
                u.getById(id);
            }
            return u;
        } finally {
            em.close();
        }
    }
    
    public Utas findUtasByEmail(String email){
        EntityManager em = getEntityManager();
        try{
            Utas u = new Utas();
            if(!em.createNamedQuery("Utas.findByEmail").setParameter("email", email).getResultList().isEmpty()){
                u.getByString(email);
            }
            return u;
        }finally{
            if (em != null) {
                em.close();
            }
        }
    }
    
    public Kedvezmeny findKedvezmeny(String nev){
        EntityManager em = getEntityManager();
        try{
            Kedvezmeny k = new Kedvezmeny();
            if(!em.createNamedQuery("Kedvezmeny.findByMegnevezes").setParameter("megnevezes", nev).getResultList().isEmpty()){
                k.getByString(nev);
            }
            return k;
        }finally{
            if (em != null) {
                em.close();
            }
        }
    }
    
    public List<Jegy> getJegyList(Utas u){
        List<Jegy> jegyList = u.getJegyList();
        return jegyList;
    }
    
    
    public Boolean edit(Utas utas) throws Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Utas persistentUtas = em.find(Utas.class, utas.getIdUtas());
            Kedvezmeny KEDVEZMENYidKedvezmenyOld = persistentUtas.getKEDVEZMENYidKedvezmeny();
            Kedvezmeny KEDVEZMENYidKedvezmenyNew = utas.getKEDVEZMENYidKedvezmeny();
            List<UtasPreferencia> utasPreferenciaListOld = persistentUtas.getUtasPreferenciaList();
            List<UtasPreferencia> utasPreferenciaListNew = utas.getUtasPreferenciaList();
            List<String> illegalOrphanMessages = null;
            for (UtasPreferencia utasPreferenciaListOldUtasPreferencia : utasPreferenciaListOld) {
                if (!utasPreferenciaListNew.contains(utasPreferenciaListOldUtasPreferencia)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain UtasPreferencia " + utasPreferenciaListOldUtasPreferencia + " since its utas field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new Exception((Throwable) illegalOrphanMessages);
            }
            if (KEDVEZMENYidKedvezmenyNew != null) {
                KEDVEZMENYidKedvezmenyNew = em.getReference(KEDVEZMENYidKedvezmenyNew.getClass(), KEDVEZMENYidKedvezmenyNew.getIdKedvezmeny());
                utas.setKEDVEZMENYidKedvezmeny(KEDVEZMENYidKedvezmenyNew);
            }
            List<UtasPreferencia> attachedUtasPreferenciaListNew = new ArrayList<UtasPreferencia>();
            for (UtasPreferencia utasPreferenciaListNewUtasPreferenciaToAttach : utasPreferenciaListNew) {
                utasPreferenciaListNewUtasPreferenciaToAttach = em.getReference(utasPreferenciaListNewUtasPreferenciaToAttach.getClass(), utasPreferenciaListNewUtasPreferenciaToAttach.getUtasPreferenciaPK());
                attachedUtasPreferenciaListNew.add(utasPreferenciaListNewUtasPreferenciaToAttach);
            }
            utasPreferenciaListNew = attachedUtasPreferenciaListNew;
            utas.setUtasPreferenciaList(utasPreferenciaListNew);
            utas = em.merge(utas);
            if (KEDVEZMENYidKedvezmenyOld != null && !KEDVEZMENYidKedvezmenyOld.equals(KEDVEZMENYidKedvezmenyNew)) {
                KEDVEZMENYidKedvezmenyOld.getUtasList().remove(utas);
                KEDVEZMENYidKedvezmenyOld = em.merge(KEDVEZMENYidKedvezmenyOld);
            }
            if (KEDVEZMENYidKedvezmenyNew != null && !KEDVEZMENYidKedvezmenyNew.equals(KEDVEZMENYidKedvezmenyOld)) {
                KEDVEZMENYidKedvezmenyNew.getUtasList().add(utas);
                KEDVEZMENYidKedvezmenyNew = em.merge(KEDVEZMENYidKedvezmenyNew);
            }
            for (UtasPreferencia utasPreferenciaListNewUtasPreferencia : utasPreferenciaListNew) {
                if (!utasPreferenciaListOld.contains(utasPreferenciaListNewUtasPreferencia)) {
                    Utas oldUtasOfUtasPreferenciaListNewUtasPreferencia = utasPreferenciaListNewUtasPreferencia.getUtas();
                    utasPreferenciaListNewUtasPreferencia.setUtas(utas);
                    utasPreferenciaListNewUtasPreferencia = em.merge(utasPreferenciaListNewUtasPreferencia);
                    if (oldUtasOfUtasPreferenciaListNewUtasPreferencia != null && !oldUtasOfUtasPreferenciaListNewUtasPreferencia.equals(utas)) {
                        oldUtasOfUtasPreferenciaListNewUtasPreferencia.getUtasPreferenciaList().remove(utasPreferenciaListNewUtasPreferencia);
                        oldUtasOfUtasPreferenciaListNewUtasPreferencia = em.merge(oldUtasOfUtasPreferenciaListNewUtasPreferencia);
                    }
                }
            }
            em.getTransaction().commit();
            return Boolean.TRUE;
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = utas.getIdUtas();
                if (findUtas(id) == null) {
                    System.out.println("The utas with id " + id + " no longer exists.");
                }
            }
            return Boolean.FALSE;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
}

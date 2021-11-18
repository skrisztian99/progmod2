package service;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import model.Jegy;
import model.Kedvezmeny;
import model.Preferencia;
import model.Utas;

public class UtasService {
    public UtasService(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public Boolean create(Utas utas) throws Exception {
        if (utas.getPreferenciaList() == null) {
            utas.setPreferenciaList(new ArrayList<Preferencia>());
        }
        if (utas.getJegyList() == null) {
            utas.setJegyList(new ArrayList<Jegy>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Kedvezmeny KEDVEZMENYidKedvezmeny = utas.getKEDVEZMENYidKedvezmeny();
            if (KEDVEZMENYidKedvezmeny != null) {
                KEDVEZMENYidKedvezmeny = em.getReference(KEDVEZMENYidKedvezmeny.getClass(), KEDVEZMENYidKedvezmeny.getIdKedvezmeny());
                utas.setKEDVEZMENYidKedvezmeny(KEDVEZMENYidKedvezmeny);
            }
            List<Preferencia> attachedPreferenciaList = new ArrayList<Preferencia>();
            for (Preferencia preferenciaListPreferenciaToAttach : utas.getPreferenciaList()) {
                preferenciaListPreferenciaToAttach = em.getReference(preferenciaListPreferenciaToAttach.getClass(), preferenciaListPreferenciaToAttach.getIdPreferencia());
                attachedPreferenciaList.add(preferenciaListPreferenciaToAttach);
            }
            utas.setPreferenciaList(attachedPreferenciaList);
            List<Jegy> attachedJegyList = new ArrayList<Jegy>();
            for (Jegy jegyListJegyToAttach : utas.getJegyList()) {
                jegyListJegyToAttach = em.getReference(jegyListJegyToAttach.getClass(), jegyListJegyToAttach.getIdJEGY());
                attachedJegyList.add(jegyListJegyToAttach);
            }
            utas.setJegyList(attachedJegyList);
            em.persist(utas);
            if (KEDVEZMENYidKedvezmeny != null) {
                KEDVEZMENYidKedvezmeny.getUtasList().add(utas);
                KEDVEZMENYidKedvezmeny = em.merge(KEDVEZMENYidKedvezmeny);
            }
            for (Preferencia preferenciaListPreferencia : utas.getPreferenciaList()) {
                preferenciaListPreferencia.getUtasList().add(utas);
                preferenciaListPreferencia = em.merge(preferenciaListPreferencia);
            }
            for (Jegy jegyListJegy : utas.getJegyList()) {
                Utas oldIdUtasOfJegyListJegy = jegyListJegy.getIdUtas();
                jegyListJegy.setIdUtas(utas);
                jegyListJegy = em.merge(jegyListJegy);
                if (oldIdUtasOfJegyListJegy != null) {
                    oldIdUtasOfJegyListJegy.getJegyList().remove(jegyListJegy);
                    oldIdUtasOfJegyListJegy = em.merge(oldIdUtasOfJegyListJegy);
                }
            }
            em.getTransaction().commit();
            return Boolean.TRUE;
        } catch (Exception ex) {
            return Boolean.FALSE;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
    public Utas findUtas(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Utas.class, id);
        } finally {
            em.close();
        }
    }
    
    public Utas findUtasByEmail(String email){ //Nem biztos, hogy működik
        EntityManager em = getEntityManager();
        try{
            return em.find(Utas.class, email);
        }finally{
            em.close();
        }
    }
    
    public Boolean edit(Utas utas) throws Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Utas persistentUtas = em.find(Utas.class, utas.getIdUtas());
            Kedvezmeny kedvezmenyidKedvezmenyOld = persistentUtas.getKEDVEZMENYidKedvezmeny();
            Kedvezmeny kedvezmenyidKedvezmenyNew = utas.getKEDVEZMENYidKedvezmeny();
            List<Jegy> jegyListOld = persistentUtas.getJegyList();
            List<Jegy> jegyListNew = utas.getJegyList();
            List<String> illegalOrphanMessages = null;
            for (Jegy jegyListOldJegy : jegyListOld) {
                if (!jegyListNew.contains(jegyListOldJegy)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Jegy " + jegyListOldJegy + " since its idUtas field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new Exception((Throwable) illegalOrphanMessages);
            }
            if (kedvezmenyidKedvezmenyNew != null) {
                kedvezmenyidKedvezmenyNew = em.getReference(kedvezmenyidKedvezmenyNew.getClass(), kedvezmenyidKedvezmenyNew.getIdKedvezmeny());
                utas.setKEDVEZMENYidKedvezmeny(kedvezmenyidKedvezmenyNew);
            }
            List<Jegy> attachedJegyListNew = new ArrayList<Jegy>();
            for (Jegy jegyListNewJegyToAttach : jegyListNew) {
                jegyListNewJegyToAttach = em.getReference(jegyListNewJegyToAttach.getClass(), jegyListNewJegyToAttach.getIdJEGY());
                attachedJegyListNew.add(jegyListNewJegyToAttach);
            }
            jegyListNew = attachedJegyListNew;
            utas.setJegyList(jegyListNew);
            utas = em.merge(utas);
            if (kedvezmenyidKedvezmenyOld != null && !kedvezmenyidKedvezmenyOld.equals(kedvezmenyidKedvezmenyNew)) {
                kedvezmenyidKedvezmenyOld.getUtasList().remove(utas);
                kedvezmenyidKedvezmenyOld = em.merge(kedvezmenyidKedvezmenyOld);
            }
            if (kedvezmenyidKedvezmenyNew != null && !kedvezmenyidKedvezmenyNew.equals(kedvezmenyidKedvezmenyOld)) {
                kedvezmenyidKedvezmenyNew.getUtasList().add(utas);
                kedvezmenyidKedvezmenyNew = em.merge(kedvezmenyidKedvezmenyNew);
            }
            for (Jegy jegyListNewJegy : jegyListNew) {
                if (!jegyListOld.contains(jegyListNewJegy)) {
                    Utas oldIdUtasOfJegyListNewJegy = jegyListNewJegy.getIdUtas();
                    jegyListNewJegy.setIdUtas(utas);
                    jegyListNewJegy = em.merge(jegyListNewJegy);
                    if (oldIdUtasOfJegyListNewJegy != null && !oldIdUtasOfJegyListNewJegy.equals(utas)) {
                        oldIdUtasOfJegyListNewJegy.getJegyList().remove(jegyListNewJegy);
                        oldIdUtasOfJegyListNewJegy = em.merge(oldIdUtasOfJegyListNewJegy);
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

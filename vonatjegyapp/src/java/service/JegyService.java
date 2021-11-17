package service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import model.JaratMegallo;
import model.Jegy;
import model.Megallo;
import model.Ules;
import model.Utas;

public class JegyService {
    public JegyService(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public Boolean create(Jegy jegy) throws Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            JaratMegallo jaratMegallo = jegy.getJaratMegallo();
            if (jaratMegallo != null) {
                jaratMegallo = em.getReference(jaratMegallo.getClass(), jaratMegallo.getJaratMegalloPK());
                jegy.setJaratMegallo(jaratMegallo);
            }
            Megallo idHonnan = jegy.getIdHonnan();
            if (idHonnan != null) {
                idHonnan = em.getReference(idHonnan.getClass(), idHonnan.getIdMEGALLO());
                jegy.setIdHonnan(idHonnan);
            }
            Ules ules = jegy.getUles();
            if (ules != null) {
                ules = em.getReference(ules.getClass(), ules.getUlesPK());
                jegy.setUles(ules);
            }
            Utas idUtas = jegy.getIdUtas();
            if (idUtas != null) {
                idUtas = em.getReference(idUtas.getClass(), idUtas.getIdUtas());
                jegy.setIdUtas(idUtas);
            }
            em.persist(jegy);
            if (jaratMegallo != null) {
                jaratMegallo.getJegyList().add(jegy);
                jaratMegallo = em.merge(jaratMegallo);
            }
            if (idHonnan != null) {
                idHonnan.getJegyList().add(jegy);
                idHonnan = em.merge(idHonnan);
            }
            if (ules != null) {
                ules.getJegyList().add(jegy);
                ules = em.merge(ules);
            }
            if (idUtas != null) {
                idUtas.getJegyList().add(jegy);
                idUtas = em.merge(idUtas);
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
}

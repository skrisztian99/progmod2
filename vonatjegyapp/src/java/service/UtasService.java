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
}

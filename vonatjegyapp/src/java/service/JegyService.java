package service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import model.Indulas;
import model.Jarat;
import model.JaratMegallo;
import model.Jegy;
import model.Megallo;
import model.Preferencia;
import model.Ules;
import model.Utas;
import model.UtasPreferencia;
import model.Vagon;
import model.VonatJaratIndulas;

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
    
    public List<Jegy> getAllJegy(){
        EntityManager em = getEntityManager();
        List<Jegy> osszJegy = em.createNamedQuery("Jegy.findAll").getResultList();        
        return osszJegy;
    }
    public List<Jegy> getAllJegyByIndulas(){
        EntityManager em = getEntityManager();
        List<Jegy> osszJegy = em.createNamedQuery("Jegy.findAll").getResultList();        
        return osszJegy;
    }
    
    public List<VonatJaratIndulas> getJaratIndulasList(String datum, String megallo){ //Időponthoz és megállóhoz tartozó járatok listája
        EntityManager em = getEntityManager();
        String idop = datum + " %";
        List<Indulas> iList = em.createNamedQuery("Indulas.findByDate").setParameter("date", idop).getResultList();
        Megallo m = (Megallo) em.createNamedQuery("Megallo.findByNev").setParameter("nev", megallo).getResultList().get(0);
        List<VonatJaratIndulas> megfelelo = new ArrayList<>();
        for (Indulas i : iList) {
            List<VonatJaratIndulas> vjiList = i.getVonatJaratIndulasList();
            for (VonatJaratIndulas vji : vjiList) {
                List<JaratMegallo> jmList = m.getJaratMegalloList();
                for (JaratMegallo jm : jmList) {
                    if(vji.getJarat().equals(jm.getJarat())){
                        megfelelo.add(vji);
                    }
                }
            }
        }
        return megfelelo;
    }
    
    public JaratMegallo getJaratMegallo(Jarat j, String megallo){
        List<JaratMegallo> jmList = j.getJaratMegalloList();
        JaratMegallo ki = null;
        for (JaratMegallo jm : jmList) {
            if(jm.getMegallo().getNev().equals(megallo)){
                ki = jm;
            }
        }
        return ki;
    }
    
    public List<Ules> listHelyek(VonatJaratIndulas vji, JaratMegallo hova, Utas utas){
        List<Ules> ulesList = new ArrayList<>();
        List<Ules> returnList = new ArrayList<>();
        List<Vagon> vagonok = vji.getVonat().getVagonList();
        for (Vagon v : vagonok) {
            List<Ules> vagonUlesek = v.getUlesList();
            for (Ules u : vagonUlesek) {
                ulesList.add(u);
            }
        }
        for (Ules u : ulesList) {
            List<Preferencia> prefList = u.getPreferenciaList();
            List<UtasPreferencia> utasPrefList = utas.getUtasPreferenciaList();
            utasPrefList.sort(Comparator.comparing(UtasPreferencia::getFontossag).reversed());
            for (UtasPreferencia utasPref : utasPrefList) {
                for (Preferencia p : prefList) {
                    if(utasPref.getPreferencia().equals(p)){
                        returnList.add(u); //Preferencia szerint adja hozzá az üléseket
                    }
                }
            }
        }
        for (Ules u : ulesList) {
            if(!returnList.contains(u)){
                returnList.add(u);
            }
        }
        //Megállóban szabad-e az ülés?
        List<Jegy> jegyek = getAllJegy();
        for (Jegy j : jegyek) {
            Calendar foglalt = Calendar.getInstance();
            foglalt.setTime(vji.getIndulas().getIdopont());
            foglalt.add(Calendar.MINUTE, j.getJaratMegallo().getMenetido().getMinutes());
            foglalt.add(Calendar.HOUR, j.getJaratMegallo().getMenetido().getMinutes());
            Calendar indul = Calendar.getInstance();
            indul.setTime(vji.getIndulas().getIdopont());
            indul.add(Calendar.MINUTE, hova.getMenetido().getMinutes());
            indul.add(Calendar.HOUR, hova.getMenetido().getMinutes());
            if(indul.after(foglalt) || indul.equals(foglalt) || indul.before(j.getIndulasidopont())){
                returnList.remove(j.getUles());
            }
        }
        return returnList;
    }
    
    public Ules findUles(Integer ulesId, Integer vagonId, Integer vonatId){
        EntityManager em = getEntityManager();
        Ules u = (Ules) em.createNamedQuery("Ules.findUles").setParameter("idULES", ulesId).setParameter("idVAGON", vagonId).setParameter("idVONAT", vonatId).getResultList().get(0);
        return u;
    }
    public Jarat findJarat(Integer id){
        EntityManager em = getEntityManager();
        Jarat j = em.find(Jarat.class, id);
        return j;
    }
    public Megallo findMegallo(String nev){
        EntityManager em = getEntityManager();
        Megallo m = (Megallo) em.createNamedQuery("Megallo.findByNev").setParameter("nev", nev).getResultList().get(0);
        return m;
    }
}

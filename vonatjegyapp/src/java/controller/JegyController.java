package controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Jarat;
import model.JaratMegallo;
import model.Jegy;
import model.Megallo;
import model.Ules;
import model.Utas;
import model.VonatJaratIndulas;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.json.JSONArray;
import org.json.JSONObject;
import service.JegyService;
import service.UtasService;

public class JegyController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("application/json");
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("vonatjegyappPU");
        JegyService jService = new JegyService(emf);
        UtasService uService = new UtasService(emf);
        try{
            if(request.getParameter("task") != null){
                if(request.getParameter("task").equals("createJegy")){
                    if(request.getParameter("") != null){
                        Integer idUtas = 1;
                        Utas u = uService.findUtas(idUtas);
                        Integer ulesId = Integer.parseInt(request.getParameter("ulesid"));
                        Integer vagonId = Integer.parseInt(request.getParameter("vagonid"));
                        Integer vonatId = Integer.parseInt(request.getParameter("vonatid"));
                        Ules ules = jService.findUles(ulesId, vagonId, vonatId);
                        String honnanNev = request.getParameter("honnan");
                        Megallo honnan = jService.findMegallo(honnanNev);
                        String hova = request.getParameter("hova");
                        Integer jaratid = Integer.parseInt(request.getParameter("jaratid"));
                        Jarat jarat = jService.findJarat(jaratid);
                        JaratMegallo jm = jService.getJaratMegallo(jarat, hova);
                        String indulasString = request.getParameter("indulas");
                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                        Date indulas = formatter.parse(indulasString);
                        Date kiallitas = new Date(System.currentTimeMillis());
                        Integer ar = ules.getEgysegar() * jm.getTavolsag();
                        Jegy j = new Jegy();
                        j.setIdUtas(u);
                        j.setUles(ules);
                        j.setIdHonnan(honnan);
                        j.setJaratMegallo(jm);
                        j.setKiallitasidatum(kiallitas);
                        j.setIndulasidopont(indulas);
                        j.setAr(ar);
                        String msg = "Hiba";
                        if(jService.create(j)){
                            msg = "Sikeres";
                        }
                        PrintWriter _package = response.getWriter();
                        JSONObject obj = new JSONObject();
                        obj.put("valasz", msg);
                        _package.write(obj.toString());
                        
                        Date date = Calendar.getInstance().getTime();
                        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                        String strDate = dateFormat.format(date);
                        
                        createPDF create = new createPDF();
                        create.createPDF(u.getVezeteknev()+" "+ u.getKersztnev(), request.getParameter("honnan"), hova,"1", vagonId.toString(), ulesId.toString(),request.getParameter("fizetendo"), strDate, request.getParameter("jegyid"), indulasString, request.getParameter("odavissza"));
                        }
                }
                if(request.getParameter("task").equals("listHelyek")){
                    //TODO (Service kész)
                }
                if(request.getParameter("task").equals("listJaratok")){
                    PrintWriter _package = response.getWriter();
                    String datum = request.getParameter("datum");
                    String honnan = request.getParameter("honnan");
                    String hova = request.getParameter("hova");
                    List<VonatJaratIndulas> vjiList = jService.getJaratIndulasList(datum, honnan);
                    JSONArray allVonatJaratIndulas = new JSONArray();
                    for(VonatJaratIndulas vji : vjiList){
                        JaratMegallo jmHonnan = jService.getJaratMegallo(vji.getJarat(), honnan);
                        JaratMegallo jmHova = jService.getJaratMegallo(vji.getJarat(), hova);
                        Calendar c1 = Calendar.getInstance();
                        Calendar c2 = Calendar.getInstance();
                        c1.setTime(vji.getIndulas().getIdopont());
                        c1.add(Calendar.MINUTE, jmHonnan.getMenetido().getMinutes());
                        c1.add(Calendar.HOUR, jmHonnan.getMenetido().getHours());
                        c2.setTime(vji.getIndulas().getIdopont());
                        c2.add(Calendar.MINUTE, jmHova.getMenetido().getMinutes());
                        c2.add(Calendar.HOUR, jmHova.getMenetido().getHours());
                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"); 
                        JSONObject obj = new JSONObject();
                        obj.put("jarat", vji.getJarat());
                        obj.put("indulas", formatter.format(c1.getTime()));
                        obj.put("erkezes", formatter.format(c2.getTime()));
                        allVonatJaratIndulas.put(obj);
                    }
                    _package.write(allVonatJaratIndulas.toString());
                }
            }
        }
        catch(Exception ex){
            
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.SimpleDateFormat;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Utas;
import org.json.JSONObject;
import service.UtasService;

public class UtasController extends HttpServlet {

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
        UtasService uService = new UtasService(emf);
        try {
            if(request.getParameter("task") != null){
                if(request.getParameter("task").equals("createUtas")){
                    if(request.getParameter("vezeteknev") != null &&
                    request.getParameter("keresztnev") != null &&
                    request.getParameter("telefon") != null &&
                    request.getParameter("irsz") != null && 
                    request.getParameter("varos") != null &&
                    request.getParameter("utca") != null && 
                    request.getParameter("hazszam") != null &&
                    request.getParameter("szulido") != null &&
                    request.getParameter("kedvezmeny") != null &&
                    request.getParameter("email") != null && 
                    request.getParameter("password_1") != null && 
                    request.getParameter("password_2") != null){
                        Utas u = new Utas();
                        u.setIdUtas(2);
                        u.setVezeteknev(request.getParameter("vezeteknev"));
                        u.setKersztnev(request.getParameter("keresztnev"));
                        u.setTelefon(request.getParameter("telefon"));
                        u.setIranyitoszam(request.getParameter("irsz"));
                        u.setVaros(request.getParameter("varos"));
                        u.setUtca(request.getParameter("utca"));
                        u.setHazszam(request.getParameter("hazszam"));
                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                        String szul = formatter.parse(request.getParameter("szulido")).toString();
                        u.setSzulido(Date.valueOf(szul));
                        if(!request.getParameter("kedvezmeny").equals("Nincs Kedvezmény")){
                            String kedvezmenyNev = request.getParameter("kedvezmeny");
                            u.setKEDVEZMENYidKedvezmeny(uService.findKedvezmeny(kedvezmenyNev));
                        }
                        u.setEmail(request.getParameter("email"));
                        if(request.getParameter("password_1").equals(request.getParameter("password_2"))){
                            u.setJelszo(request.getParameter("password_1"));
                        }
                        else{
                            //Nem egyezik meg a két jelszó
                        }
                        String msg = "Hiba";
                        if(uService.create(u)){
                            msg = "Siker";
                        }
                        PrintWriter _package = response.getWriter();
                        JSONObject obj = new JSONObject();
                        obj.put("valasz", msg);
                        _package.write(obj.toString());
                    }
                }
                
                if(request.getParameter("task").equals("editUtas")){
                    //Jelenlegi utas szerkesztése
                    Integer id = 0; //Bejelentkezett utas azonosítója
                    Utas u = uService.findUtas(id);
                    //Preferenciák beállítása is itt történik
                }
                
                if(request.getParameter("task").equals("loginUtas")){
                   if(request.getParameter("email") != null && request.getParameter("password") != null){
                       String email = request.getParameter("email");
                       String jelszo = request.getParameter("password");
                       Utas utas = new Utas();
                       String message = "Hiba";
                       utas = uService.checkLogin(email, jelszo);
                       if (utas != null) {
//                               HttpSession session = request.getSession();
//                               session.setAttribute("utas", utas);
                            message = "sikeres";
                       } else {
                           message = "Rossz email/jelszó";
                       }
                       PrintWriter _package = response.getWriter();
                       JSONObject obj = new JSONObject();
                       obj.put("valasz", message);
                       //obj.put("utas", utas);
                       _package.write(obj.toString());
                   }
                }
            }
        }
        catch(Exception ex){
            System.out.println(ex.toString());
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

package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Utas;
import model.UtasPreferencia;
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
                        String vnev = request.getParameter("vezeteknev");
                        String knev = request.getParameter("keresztnev");
                        String telefon = request.getParameter("telefon");
                        String irsz = request.getParameter("irsz");
                        String varos = request.getParameter("varos");
                        String utca = request.getParameter("utca");
                        String hazszam = request.getParameter("hazszam");
                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                        String szulString = formatter.parse(request.getParameter("szulido")).toString();
                        Date szul = Date.valueOf(szulString);
                        if(!request.getParameter("kedvezmeny").equals("Nincs Kedvezmény") && !request.getParameter("kedvezmeny").equals("Nincs+Kedvezmény")){
                            String kedvezmenyNev = request.getParameter("kedvezmeny");
                            //u.setKEDVEZMENYidKedvezmeny(uService.findKedvezmeny(kedvezmenyNev));
                        }
                        String email = request.getParameter("email");
                        String jelszo = "";
                        String msg = "Hiba";
                        if(request.getParameter("password_1").equals(request.getParameter("password_2"))){
                            jelszo = request.getParameter("password_1");
                            Utas u = new Utas(2, vnev, knev, email, szul, irsz, varos, utca, hazszam, jelszo);
                            u.setTelefon(telefon);
                            if(uService.create(u)){
                                msg = "Siker";
                            }
                        }
                        else{
                            msg = "Nem egyezik meg a két jelszó";
                        }
                        PrintWriter _package = response.getWriter();
                        JSONObject obj = new JSONObject();
                        obj.put("valasz", msg);
                        _package.write(obj.toString());
                        
                    
                }
                
                if(request.getParameter("task").equals("setPreferencia")){
                    Integer id = 1;
                    Utas u = uService.findUtas(id);
                    List<UtasPreferencia> utasPrefList = u.getUtasPreferenciaList();
                    if(!request.getParameter("ablakmellett").equals("0")){
                        UtasPreferencia up = new UtasPreferencia(id, 1);
                        up.setFontossag(Integer.parseInt(request.getParameter("ablakmellett")));
                        utasPrefList.add(up);
                    }
                    if(!request.getParameter("ellentetes").equals("0")){
                        UtasPreferencia up = new UtasPreferencia(id, 2);
                        up.setFontossag(Integer.parseInt(request.getParameter("ellentetes")));
                        utasPrefList.add(up);
                    }
                    if(!request.getParameter("asztal").equals("0")){
                        UtasPreferencia up = new UtasPreferencia(id, 3);
                        up.setFontossag(Integer.parseInt(request.getParameter("asztal")));
                        utasPrefList.add(up);
                    }
                    if(!request.getParameter("toltes").equals("0")){
                        UtasPreferencia up = new UtasPreferencia(id, 4);
                        up.setFontossag(Integer.parseInt(request.getParameter("toltes")));
                        utasPrefList.add(up);
                    }
                    if(!request.getParameter("ajtomellett").equals("0")){
                        UtasPreferencia up = new UtasPreferencia(id, 5);
                        up.setFontossag(Integer.parseInt(request.getParameter("ajtomellett")));
                        utasPrefList.add(up);
                    }
                    String msg = "Hiba";
                    if(uService.edit(u)){
                        msg = "Sikeres mentés";
                    }
                    else{
                        msg = "Hiba";
                    }
                    PrintWriter _package = response.getWriter();
                    JSONObject obj = new JSONObject();
                    obj.put("valasz", msg);
                    _package.write(obj.toString());
                }
                
                if(request.getParameter("task").equals("loginUtas")){
                   if(request.getParameter("email") != null && request.getParameter("password") != null){
                       String email = request.getParameter("email");
                       String jelszo = request.getParameter("password");
                       String message = "Hiba";
                       Utas utas = uService.checkLogin(email, jelszo);
                       if (utas != null) {
                               HttpSession session = request.getSession();
                               session.setAttribute("utas", utas);
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

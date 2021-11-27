
package controller;

import java.io.IOException;
import java.io.InputStream;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDField;

public class createPDF {
    public void createPDF(String nev,String honnan,String hova,String Db, String vagon,String ules,String fizetendo, String vasarlasido,String jegyid,String indulas,String odavissza){
      InputStream is = getClass().getClassLoader().getResourceAsStream("Jegy.pdf");
      
      try {
          PDDocument pDDocument = PDDocument.load(is);
          PDAcroForm pDAcroform = pDDocument.getDocumentCatalog().getAcroForm();
          
          PDField fieldName = pDAcroform.getField("nev");
          PDField fieldhonnan = pDAcroform.getField("honnan");
          PDField fieldhova = pDAcroform.getField("hova");
          PDField fielddb = pDAcroform.getField("db");
          PDField fieldvagon = pDAcroform.getField("vagonszam");
          PDField fieldules = pDAcroform.getField("ulesszam");
          PDField fieldFizetendo = pDAcroform.getField("fizetendo");
          PDField fieldVasarlasido = pDAcroform.getField("vasarlasido");
          PDField fieldJegyid = pDAcroform.getField("jegyazonosito");
          PDField fieldIndulas = pDAcroform.getField("indulas");
          PDField fieldOdavissza = pDAcroform.getField("odavissza");
          
          fieldName.setValue(nev);
          fieldhonnan.setValue(honnan);
          fieldhova.setValue(hova);
          fielddb.setValue(Db);
          fieldvagon.setValue(vagon);
          fieldules.setValue(ules);
          fieldFizetendo.setValue(fizetendo);
          fieldVasarlasido.setValue(vasarlasido);
          fieldJegyid.setValue(jegyid);
          fieldIndulas.setValue(indulas);
          fieldOdavissza.setValue(odavissza);
          
          pDDocument.save("Jegy2.pdf");
          pDDocument.close();
          
      }
      catch(IOException e){
          e.printStackTrace();
      }
    }
}
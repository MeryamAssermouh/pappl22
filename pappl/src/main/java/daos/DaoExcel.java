/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import models.DetteSimplifiee;
import models.EcheanceSimplifiee;
import models.Redevable;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Luz
 */
public class DaoExcel {
    
    public File obtenirPath(JPanel optionsCreation){
        try {
            JFileChooser chooser = new JFileChooser();
            chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            chooser.setDialogTitle("Sélectionnez une dossier");
            chooser.showSaveDialog(optionsCreation);
            if(chooser.getSelectedFile() != null){
                String path = chooser.getSelectedFile().getCanonicalPath();
                int i=0;
                String nomFichier = path + "\\Echeancier" + Integer.toString(i) +".xlsx";
                File f = new File(nomFichier);
                while (f.exists()) {
                            i++;
                            nomFichier= path + "\\Echeancier" + Integer.toString(i) +".xlsx";
                            f = new File(nomFichier);
                        }
                return f;
            }else{
                return null;
            }
        } catch (IOException ex) {
           ex.printStackTrace();
        }
          return null;      
    }
            
     
    public void ecrireEcheancier(DetteSimplifiee detSim, JPanel optionsCreation){
       
        File fichier = this.obtenirPath(optionsCreation);
        
        if(fichier != null){
            File originalWb = new File("Test1.xlsx"); 

            try {
                Files.copy(originalWb.toPath(), fichier.toPath());

                FileInputStream inputStream = new FileInputStream(fichier);
                Workbook livre = WorkbookFactory.create(inputStream);
                Sheet feuil = livre.getSheetAt(0);
                Row ligne = feuil.createRow(14);
                Cell cell = ligne.createCell(0);

                cell.setCellValue("Dear, " + detSim.getRedev().getNom());


                ArrayList<EcheanceSimplifiee> echeanceDet = detSim.getEs();
                feuil.shiftRows(28, feuil.getLastRowNum(), echeanceDet.size());


                CellStyle cellStyle1 = livre.createCellStyle();
                cellStyle1.setBorderLeft(BorderStyle.MEDIUM);

                CellStyle cellStyle2 = livre.createCellStyle();
                cellStyle2.setBorderLeft(BorderStyle.THIN);
                cellStyle2.setBorderRight(BorderStyle.THIN);
                cellStyle2.setDataFormat((short)14);

                CellStyle cellStyle3 = livre.createCellStyle();
                cellStyle3.setBorderRight(BorderStyle.MEDIUM);
                cellStyle3.setDataFormat((short)8);

                CellStyle cellStyle4 = livre.createCellStyle();
                cellStyle4.setBorderLeft(BorderStyle.MEDIUM);
                cellStyle4.setBorderBottom(BorderStyle.MEDIUM);

                CellStyle cellStyle5 = livre.createCellStyle();
                cellStyle5.setBorderLeft(BorderStyle.THIN);
                cellStyle5.setBorderRight(BorderStyle.THIN);
                cellStyle5.setBorderBottom(BorderStyle.MEDIUM);

                CellStyle cellStyle6 = livre.createCellStyle();
                cellStyle6.setBorderRight(BorderStyle.MEDIUM);
                cellStyle6.setBorderBottom(BorderStyle.MEDIUM);
                cellStyle6.setDataFormat((short)8);

                CellStyle cellStyle7 = livre.createCellStyle();
                cellStyle7.setDataFormat((short)8);

                int i= 28;
                int j = 1;
                double sum=0;

                for(EcheanceSimplifiee echeSimp: echeanceDet){
                    sum = sum +echeSimp.getMontant();
                    ligne = feuil.createRow(i);

                    cell = ligne.createCell(0);
                    cell.setCellStyle(cellStyle1);
                    cell.setCellValue("Deadline " + j);

                    cell = ligne.createCell(3);
                    cell.setCellValue(echeSimp.getDateDeadLine());
                    cell.setCellStyle(cellStyle2);

                    cell = ligne.createCell(4);
                    cell.setCellValue(echeSimp.getMontant());
                    cell.setCellStyle(cellStyle3);


                    i++;
                    j++;
                }

                ligne = feuil.getRow(22);
                cell = ligne.createCell(1);
                cell.setCellValue(detSim.getLibelle());

                ligne = feuil.createRow(i+1);
                cell = ligne.createCell(0);
                cell.setCellStyle(cellStyle4);
                cell = ligne.createCell(1);
                cell.setCellStyle(cellStyle4);
                cell = ligne.createCell(2);
                cell.setCellStyle(cellStyle4);

                cell = ligne.createCell(3);
                cell.setCellStyle(cellStyle5);

                cell = ligne.createCell(4);
                cell.setCellValue(sum);
                cell.setCellStyle(cellStyle6);

                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");  
                LocalDateTime now = LocalDateTime.now();  
                ligne = feuil.createRow(9);
                cell = ligne.createCell(4);
                cell.setCellValue("Nantes le, " + dtf.format(now));


                i=28;
                for(EcheanceSimplifiee echeSimp: echeanceDet){
                    feuil.addMergedRegion(new CellRangeAddress(i,i,0,2));
                    i=i+1;   
                }

                OutputStream fileOut = new FileOutputStream(fichier);
                livre.write(fileOut);
                livre.close();
            } catch (IOException ex) {

            }
        }
    }
    
    public DetteSimplifiee lireExcel(File fichier){
        DetteSimplifiee detSimp = new DetteSimplifiee();
        try
        {
            FileInputStream file = new FileInputStream(fichier);
 
            XSSFWorkbook livre = new XSSFWorkbook(file);
            XSSFSheet feuil = livre.getSheetAt(0);
            Boolean trouve=false;
            String nom =null;
            String date[]=null;
            String Libelle="";
            int rowEcheance=0;

            for (int j = feuil.getFirstRowNum(); j <= feuil.getLastRowNum(); j++) {
                try{
                    XSSFRow row = feuil.getRow(j);
                       if(trouve){
                           break;
                       }
                    for (int k = row.getFirstCellNum(); k < row.getLastCellNum(); k++) {
                       XSSFCell cell = row.getCell(k);
                          if(cell.getStringCellValue().equals("Date")) {
                             rowEcheance=j+1; 
                          }
                          if(cell.getStringCellValue().equals("Libellé")) {
                             XSSFRow row1 = feuil.getRow(j+1);
                             int colonne = cell.getColumnIndex();
                             Libelle = row1.getCell(colonne).getStringCellValue();
                             
                          }
                          String mot = "Dear";
                          if(cell.getStringCellValue().toLowerCase().contains(mot.toLowerCase())) {
                             nom = cell.getStringCellValue();
                          }
                          mot = "Nantes le"; // ya
                          if(cell.getStringCellValue().toLowerCase().contains(mot.toLowerCase())) {
                             date = cell.getStringCellValue().split(" ");
                          }
                    } 
                    }catch(java.lang.NullPointerException e){
                            
                    }catch(java.lang.IllegalStateException e){
                        
                    }
                
            }
            
            
            Redevable redv = new Redevable();
            if(nom != null){          
                Matcher m = Pattern.compile("[^A-Za-z ]+").matcher(nom);
                if (m.find()) {
                   redv.setNom(nom.substring(m.end()).trim()); 
                }else if(nom.toLowerCase().contains("dear")){
                    redv.setNom(nom.substring(nom.toLowerCase().indexOf("dear") + 4).trim());
                }else{
                    redv.setNom(nom);
                }
                
            }
            
            detSimp.setRedev(redv);
            detSimp.setLibelle(Libelle);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            try{
                int l = date.length;
                if(date[l-1] != null){
                    detSimp.setDateCreation(LocalDate.parse(date[l-1],formatter));
                }
            }catch(java.lang.NullPointerException e){
                
            }
            
            XSSFRow row = feuil.getRow(rowEcheance);
            double sum=0;
            ArrayList<EcheanceSimplifiee> echeances = new ArrayList<>();
            while(rowEcheance != 0 && !(row.getCell(0).getStringCellValue().equals("")) ){
                EcheanceSimplifiee echeance = new EcheanceSimplifiee();
                Date date1 = row.getCell(3).getDateCellValue();
            
                echeance.setDateDeadLine(date1.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
               // echeance.setDateDeadLine(row.getCell(3).getDateCellValue().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
                echeance.setMontant(row.getCell(4).getNumericCellValue());
                echeances.add(echeance);
                sum = sum + row.getCell(4).getNumericCellValue();
                rowEcheance = rowEcheance +1;
                row = feuil.getRow(rowEcheance);
                
            }
            detSimp.setMontant(sum);
            detSimp.setEs(echeances);
            
        } catch (IOException ex) {
            
        }
        return detSimp;    
    }       
}

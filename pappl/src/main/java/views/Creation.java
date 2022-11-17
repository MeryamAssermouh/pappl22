/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;
import Exceptions.MontantException;
import Exceptions.VideException;
import controllers.ConCreation;
import controllers.ConExcel;
import daos.DaoExcel;
import java.awt.CardLayout;
import java.awt.event.KeyEvent;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import models.*;

/**
 *
 * @author 96441
 */
public class Creation extends javax.swing.JPanel {
    private JPanel panel;
    private ConCreation concreation;
    private DaoExcel daoExcel;
    private ConExcel conExcel;

    /**
     * Creates new form DetailActif
     */
    public Creation() {
        initComponents();
        this.daoExcel = new DaoExcel(); 
        this.concreation=new ConCreation();
        this.conExcel=new ConExcel();
      
    }

     public void setPanel(JPanel panel) {
        this.panel = panel;
    }

    public JComboBox<String> getNbEcheances() {
        return nbEcheances;
    }


    public JTextField getActioneffectuee() {
        return actioneffectuee;
    }

    public JTextField getActionentreprendre() {
        return actionentreprendre;
    }

    public JTable getListeEcheances() {
        return listeEcheances;
    }

    public JTextField getInfocomplementaire() {
        return infocomplementaire;
    }

    public void setNbEcheances(JComboBox<String> nbEcheances) {
        this.nbEcheances = nbEcheances;
    }

    public JTextField getLibelle() {
        return libelle;
    }

    public JTextField getMailRedevable() {
        return mailRedevable;
    }
   
    public JTextField getMontant() {
        return montant;
    }

    public JComboBox<String> getListeAgent() {
        return listeAgent;
    }


     public JTextField getNomRedevable() {
        return nomRedevable;
    }

    public void setListeAgent(JComboBox<String> listeAgent) {
        this.listeAgent = listeAgent;
    }
     
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        mailRedevable = new javax.swing.JTextField();
        nomRedevable = new javax.swing.JTextField();
        libelle = new javax.swing.JTextField();
        montant = new javax.swing.JTextField();
        infocomplementaire = new javax.swing.JTextField();
        actionentreprendre = new javax.swing.JTextField();
        actioneffectuee = new javax.swing.JTextField();
        Annuler = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        listeEcheances = new javax.swing.JTable();
        enregistrer = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        listeAgent = new javax.swing.JComboBox<>();
        nbEcheances = new javax.swing.JComboBox<>();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        setBackground(new java.awt.Color(0, 51, 102));
        setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Logiciel Echéancier", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(255, 204, 0))); // NOI18N
        setMaximumSize(new java.awt.Dimension(623, 345));
        setPreferredSize(new java.awt.Dimension(623, 429));
        setRequestFocusEnabled(false);

        jLabel1.setFont(new java.awt.Font("Lucida Fax", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 204, 0));
        jLabel1.setText("Nom:");

        jLabel2.setFont(new java.awt.Font("Lucida Fax", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 204, 0));
        jLabel2.setText("Libellé:");

        jLabel3.setFont(new java.awt.Font("Lucida Fax", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 204, 0));
        jLabel3.setText("Information Complémentaire:  ");

        jLabel4.setFont(new java.awt.Font("Lucida Fax", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 204, 0));
        jLabel4.setText("Adresse mail:");

        jLabel5.setFont(new java.awt.Font("Lucida Fax", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 204, 0));
        jLabel5.setText("Montant dette:");

        jLabel6.setFont(new java.awt.Font("Lucida Fax", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 204, 0));
        jLabel6.setText("Action à entreprendre:");

        jLabel7.setFont(new java.awt.Font("Lucida Fax", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 204, 0));
        jLabel7.setText("Actions effectuées:");

        montant.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                montantKeyTyped(evt);
            }
        });

        Annuler.setText("Annuler");
        Annuler.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AnnulerActionPerformed(evt);
            }
        });

        listeEcheances.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Echéance deadline", "Date", "Montant"
            }
        ));
        listeEcheances.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                listeEcheancesKeyTyped(evt);
            }
        });
        jScrollPane2.setViewportView(listeEcheances);

        enregistrer.setText("Enregistrer");
        enregistrer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enregistrerActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Lucida Fax", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 204, 0));
        jLabel8.setText("Agent Comptable:");

        jLabel10.setFont(new java.awt.Font("Lucida Fax", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 204, 0));
        jLabel10.setText("Nombre d'échéances");

        listeAgent.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        listeAgent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                listeAgentActionPerformed(evt);
            }
        });

        nbEcheances.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nbEcheancesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel7))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(infocomplementaire)
                                    .addComponent(actionentreprendre)
                                    .addComponent(actioneffectuee, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(31, 31, 31)
                                .addComponent(jLabel8)
                                .addGap(18, 18, 18)
                                .addComponent(listeAgent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(libelle, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(jLabel1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(nomRedevable)))
                                .addGap(55, 55, 55)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel4))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(montant, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(mailRedevable, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 547, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(53, 53, 53)
                                .addComponent(nbEcheances, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(68, 68, 68))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(enregistrer)
                .addGap(151, 151, 151)
                .addComponent(Annuler)
                .addGap(240, 240, 240))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(nomRedevable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(mailRedevable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(libelle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(montant, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(infocomplementaire, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(listeAgent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(actionentreprendre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(actioneffectuee, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(nbEcheances, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Annuler)
                    .addComponent(enregistrer))
                .addGap(16, 16, 16))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void AnnulerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AnnulerActionPerformed
        ((CardLayout)panel.getLayout()).show(panel, "p1");
            //Si algo se daña puede ser esto
            this.removeAll();
            this.initComponents();
            revalidate();
            repaint();
    }//GEN-LAST:event_AnnulerActionPerformed

    private void enregistrerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enregistrerActionPerformed
        try {
            
            DetteSimplifiee detSim;
            detSim = concreation.enregistrerRedevable(listeEcheances, mailRedevable, nomRedevable, libelle, montant, infocomplementaire, actionentreprendre, actioneffectuee, listeAgent);
            
             int reponse = JOptionPane.showConfirmDialog(this, "Vous voulez générer excel");
             if(reponse == 0){
                daoExcel.ecrireEcheancier(detSim, panel);
            }
            ((CardLayout)panel.getLayout()).show(panel, "p1");
            this.removeAll();
            this.initComponents();
            revalidate();
            repaint();
        
        }catch(VideException e){
            JOptionPane.showMessageDialog(this, "Il y a des champs vides qui ne peuvent pas l'être,"
                    + " ajoutez les données manquantes");
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(this, "Le montant des écheances doit être une valeur numérique non vide");
        }catch(MontantException e){
            JOptionPane.showMessageDialog(this, "Le montant total de la dette n'est pas égal à la somme des "
                    + "échéances, veuillez modifier les valeurs et sauvegarder à nouveau");
        }
         
      
    }//GEN-LAST:event_enregistrerActionPerformed

    private void montantKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_montantKeyTyped
          char c=evt.getKeyChar();   
          if (!((Character.isDigit(c))||(c=='.')||(c==KeyEvent.VK_BACK_SPACE)||(c==KeyEvent.VK_DELETE))){
              getToolkit().beep();
              evt.consume();  
          }
    }//GEN-LAST:event_montantKeyTyped

    private void nbEcheancesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nbEcheancesActionPerformed
        concreation.nombreDesEcheances(montant, nbEcheances, listeEcheances);
    }//GEN-LAST:event_nbEcheancesActionPerformed

    private void listeAgentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_listeAgentActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_listeAgentActionPerformed

    private void listeEcheancesKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_listeEcheancesKeyTyped
     
    }//GEN-LAST:event_listeEcheancesKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Annuler;
    private javax.swing.JTextField actioneffectuee;
    private javax.swing.JTextField actionentreprendre;
    private javax.swing.JButton enregistrer;
    private javax.swing.JTextField infocomplementaire;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField libelle;
    private javax.swing.JComboBox<String> listeAgent;
    private javax.swing.JTable listeEcheances;
    private javax.swing.JTextField mailRedevable;
    private javax.swing.JTextField montant;
    private javax.swing.JComboBox<String> nbEcheances;
    private javax.swing.JTextField nomRedevable;
    // End of variables declaration//GEN-END:variables
}

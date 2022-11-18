/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

/**
 *
 * @author Luz
 */
public class MainFrame extends javax.swing.JFrame {

    /**
     * Creates new form MainFrame
     */
    public MainFrame() {
        initComponents();
        connexion.setPanel(mainPanel); //funcoon dar cards a todos los paneneles
        menu.setPanel(mainPanel); // pasamso todo el conjuntp de cartas a los paneles para que sepan que carta poner
        rechercheHisto.setPanel(mainPanel); 
        listeHisto.setPanel(mainPanel);
        actif.setPanel(mainPanel);
        detailActif.setPanel(mainPanel);
        editionAH.setPanel(mainPanel);
        creation.setPanel(mainPanel);
        mailRedevable.setPanel(mainPanel);
        mailAgent.setPanel(mainPanel);
        agent.setPanel(mainPanel);
        optionsCreation.setPanel(mainPanel);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFrame1 = new javax.swing.JFrame();
        mainPanel1 = new javax.swing.JPanel();
        connexion1 = new views.Connexion();
        menu1 = new views.Menu();
        rechercheHisto1 = new views.RechercheHisto();
        listeHisto1 = new views.ListeHisto();
        actif1 = new views.Actif();
        detailActif1 = new views.DetailAH();
        editionAH1 = new views.EditionAH();
        creation1 = new views.Creation();
        agent1 = new views.Agent();
        mailRedevable1 = new views.MailRedevable();
        mailAgent1 = new views.MailAgent();
        optionsCreation1 = new views.OptionsCreation();
        mainPanel = new javax.swing.JPanel();
        connexion = new views.Connexion();
        menu = new views.Menu();
        rechercheHisto = new views.RechercheHisto();
        listeHisto = new views.ListeHisto();
        actif = new views.Actif();
        detailActif = new views.DetailAH();
        editionAH = new views.EditionAH();
        creation = new views.Creation();
        agent = new views.Agent();
        mailRedevable = new views.MailRedevable();
        mailAgent = new views.MailAgent();
        optionsCreation = new views.OptionsCreation();

        jFrame1.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        mainPanel1.setLayout(new java.awt.CardLayout());

        connexion1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Logiciel Echéancier", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(255, 204, 0))); // NOI18N
        mainPanel1.add(connexion1, "p0");

        menu1.setToolTipText("Menu");
        mainPanel1.add(menu1, "p1");
        mainPanel1.add(rechercheHisto1, "p2");
        mainPanel1.add(listeHisto1, "p3");
        mainPanel1.add(actif1, "p4");
        mainPanel1.add(detailActif1, "p5");
        mainPanel1.add(editionAH1, "p6");
        mainPanel1.add(creation1, "p7");
        mainPanel1.add(agent1, "p8");
        mainPanel1.add(mailRedevable1, "p9");
        mainPanel1.add(mailAgent1, "p10");
        mainPanel1.add(optionsCreation1, "p11");

        javax.swing.GroupLayout jFrame1Layout = new javax.swing.GroupLayout(jFrame1.getContentPane());
        jFrame1.getContentPane().setLayout(jFrame1Layout);
        jFrame1Layout.setHorizontalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jFrame1Layout.setVerticalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        mainPanel.setLayout(new java.awt.CardLayout());

        connexion.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Logiciel Echéancier", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(255, 204, 0))); // NOI18N
        mainPanel.add(connexion, "p0");

        menu.setToolTipText("Menu");
        mainPanel.add(menu, "p1");
        mainPanel.add(rechercheHisto, "p2");
        mainPanel.add(listeHisto, "p3");
        mainPanel.add(actif, "p4");
        mainPanel.add(detailActif, "p5");
        mainPanel.add(editionAH, "p6");
        mainPanel.add(creation, "p7");
        mainPanel.add(agent, "p8");
        mainPanel.add(mailRedevable, "p9");
        mainPanel.add(mailAgent, "p10");
        mainPanel.add(optionsCreation, "p11");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private views.Actif actif;
    private views.Actif actif1;
    private views.Agent agent;
    private views.Agent agent1;
    private views.Connexion connexion;
    private views.Connexion connexion1;
    private views.Creation creation;
    private views.Creation creation1;
    private views.DetailAH detailActif;
    private views.DetailAH detailActif1;
    private views.EditionAH editionAH;
    private views.EditionAH editionAH1;
    private javax.swing.JFrame jFrame1;
    private views.ListeHisto listeHisto;
    private views.ListeHisto listeHisto1;
    private views.MailAgent mailAgent;
    private views.MailAgent mailAgent1;
    private views.MailRedevable mailRedevable;
    private views.MailRedevable mailRedevable1;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JPanel mainPanel1;
    private views.Menu menu;
    private views.Menu menu1;
    private views.OptionsCreation optionsCreation;
    private views.OptionsCreation optionsCreation1;
    private views.RechercheHisto rechercheHisto;
    private views.RechercheHisto rechercheHisto1;
    // End of variables declaration//GEN-END:variables
}
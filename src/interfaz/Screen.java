/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package interfaz;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import sistema.AI;
import sistema.Admin;
import utilidades.Cola;
import utilidades.Lista;

/**
 *
 * @author emirs
 */
public class Screen extends javax.swing.JFrame {
    
    public Lista winners = new Lista();
    /**
     * Creates new form Pantalla
     */
    public Screen() {
        initComponents();
        setZeldaFont(); 
        setSfFonts();
        
        //colas zelda
        Cola zelda1 = new Cola();
        Cola zelda2 = new Cola();
        Cola zelda3 = new Cola();
        Cola zeldaRefuerzo = new Cola();
        Cola[] zeldaArrCola = {zelda1, zelda2, zelda3};

        //colas SF
        Cola SF1 = new Cola();
        Cola SF2 = new Cola();
        Cola SF3 = new Cola();
        Cola SFRefuerzo = new Cola();
        Cola[] sfArrCola = {SF1, SF2, SF3};

        Semaphore mutex = new Semaphore(1);
        
        JTextArea[] colasZelda = {priority1Z,priority2Z,priority3Z,priorityBackupZ};
        JTextArea[] colasSF = {priority1SF,priority2SF,priority3SF,priorityBackupSF};
        JLabel[] z = {zeldaId, zeldaName, zeldaPriority, zeldaLife, zeldaAbility, zeldaStrenght,zeldaAgility, zeldaImage};
        JLabel[] sf = {sfId, sfName, sfPriority, sfLife, sfAbility, sfStrenght ,sfAgility, sfImage};
        
        //Declaración clases sistema
        
        GUIHandler guiSo = new GUIHandler(colasZelda,colasSF,victoriesZ,victoriesSF, estado, AImagen,saleRefuerzoZ,saleRefuerzoSF);
        GUIHandler guiPro = new GUIHandler(z, sf, adjustSpeed, estado, AImagen);
        Admin so = new Admin(mutex, zeldaArrCola, sfArrCola, zeldaRefuerzo, SFRefuerzo, this.winners,guiSo);
        AI procesador = new AI(mutex, guiPro);

        so.start();
        procesador.start();
    }
    
    public void setZeldaFont(){
         Font zeldaFont = null;
         
         try {
            
            zeldaFont = Font.createFont(Font.TRUETYPE_FONT, getClass().getClassLoader().getResourceAsStream("fonts/The Wild Breath of Zelda.otf"));
            
            Font zeldaMainTitleFont = zeldaFont.deriveFont(Font.PLAIN,36);
            Font zeldaTitleFont = zeldaFont.deriveFont(Font.PLAIN,21);
            Font zeldaQueueFont = zeldaFont.deriveFont(Font.PLAIN,18);
            Font zeldaStatsFont = zeldaFont.deriveFont(Font.PLAIN,14);
            
            //titulos
            mainTitleZelda.setFont(zeldaMainTitleFont);
            zeldaTitle.setFont(zeldaTitleFont); 
            
            //colas
            zPriority1Label.setFont(zeldaQueueFont);
            zPriority2Label.setFont(zeldaQueueFont);
            zPriority3Label.setFont(zeldaQueueFont);
            zBackupLabel.setFont(zeldaQueueFont);
            
            //stats psj elegido
            zeldaIdLabel.setFont(zeldaStatsFont);
            zeldaNameLabel.setFont(zeldaStatsFont);
            zeldaStrenghtLabel.setFont(zeldaStatsFont);
            zeldaPriorityLabel.setFont(zeldaStatsFont);
            zeldaAbilityLabel.setFont(zeldaStatsFont);
            zeldaLifeLabel.setFont(zeldaStatsFont);
            zeldaAgilityLabel.setFont(zeldaStatsFont);
            
            victoriesZLabel.setFont(zeldaStatsFont);
            
        } catch (FontFormatException ex) {
            ex.printStackTrace(System.out);
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
    }
    
    public void setSfFonts(){
        Font sfFont = null;
         
         try {
            
            sfFont = Font.createFont(Font.TRUETYPE_FONT, getClass().getClassLoader().getResourceAsStream("fonts/BrutalFontPro.otf"));
            
            Font sfMainTitleFont = sfFont.deriveFont(Font.BOLD,32);
            Font sfTitleFont = sfFont.deriveFont(Font.BOLD,20);
            Font sfQueueFont = sfFont.deriveFont(Font.PLAIN,18);
            Font sfStatsFont = sfFont.deriveFont(Font.PLAIN,14);
            
            //titulos
            mainTitleSf.setFont(sfMainTitleFont);
            sfTitle.setFont(sfTitleFont); 
            
            //colas
            sfPriority1Label.setFont(sfQueueFont);
            sfPriority2Label.setFont(sfQueueFont);
            sfPriority3Label.setFont(sfQueueFont);
            sfBackupLabel.setFont(sfQueueFont);
            
            //stats psj elegido
            sfIdLabel.setFont(sfStatsFont);
            sfNameLabel.setFont(sfStatsFont);
            sfStrenghtLabel.setFont(sfStatsFont);
            sfPriorityLabel.setFont(sfStatsFont);
            sfAbilityLabel.setFont(sfStatsFont);
            sfLifeLabel.setFont(sfStatsFont);
            sfAgilityLabel.setFont(sfStatsFont);
            
            victoriesSfLabel.setFont(sfStatsFont);
            
        } catch (FontFormatException ex) {
            ex.printStackTrace(System.out);
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane9 = new javax.swing.JScrollPane();
        priorityBackupZ = new javax.swing.JTextArea();
        jScrollPane10 = new javax.swing.JScrollPane();
        priority3Z = new javax.swing.JTextArea();
        jScrollPane11 = new javax.swing.JScrollPane();
        priority2Z = new javax.swing.JTextArea();
        jScrollPane12 = new javax.swing.JScrollPane();
        priority1Z = new javax.swing.JTextArea();
        mainTitleZelda = new javax.swing.JLabel();
        zeldaTitle = new javax.swing.JLabel();
        victoriesZ = new javax.swing.JTextField();
        zBackupLabel = new javax.swing.JLabel();
        victoriesSfLabel = new javax.swing.JLabel();
        sfTitle = new javax.swing.JLabel();
        mainTitleSf = new javax.swing.JLabel();
        victoriesZLabel = new javax.swing.JLabel();
        showWinners = new javax.swing.JButton();
        zPriority1Label = new javax.swing.JLabel();
        zPriority2Label = new javax.swing.JLabel();
        zPriority3Label = new javax.swing.JLabel();
        exitB = new javax.swing.JButton();
        sfPriority1Label = new javax.swing.JLabel();
        jScrollPane13 = new javax.swing.JScrollPane();
        priority1SF = new javax.swing.JTextArea();
        sfPriority2Label = new javax.swing.JLabel();
        jScrollPane14 = new javax.swing.JScrollPane();
        priority2SF = new javax.swing.JTextArea();
        sfPriority3Label = new javax.swing.JLabel();
        jScrollPane16 = new javax.swing.JScrollPane();
        priorityBackupSF = new javax.swing.JTextArea();
        jScrollPane15 = new javax.swing.JScrollPane();
        priority3SF = new javax.swing.JTextArea();
        sfBackupLabel = new javax.swing.JLabel();
        zeldaImage = new javax.swing.JLabel();
        sfIdLabel = new javax.swing.JLabel();
        sfNameLabel = new javax.swing.JLabel();
        sfPriorityLabel = new javax.swing.JLabel();
        sfLifeLabel = new javax.swing.JLabel();
        sfAbilityLabel = new javax.swing.JLabel();
        sfStrenghtLabel = new javax.swing.JLabel();
        sfAgilityLabel = new javax.swing.JLabel();
        sfId = new javax.swing.JLabel();
        sfName = new javax.swing.JLabel();
        sfPriority = new javax.swing.JLabel();
        sfLife = new javax.swing.JLabel();
        sfAbility = new javax.swing.JLabel();
        sfStrenght = new javax.swing.JLabel();
        sfAgility = new javax.swing.JLabel();
        zeldaAgilityLabel = new javax.swing.JLabel();
        zeldaIdLabel = new javax.swing.JLabel();
        zeldaAgility = new javax.swing.JLabel();
        zeldaNameLabel = new javax.swing.JLabel();
        zeldaStrenghtLabel = new javax.swing.JLabel();
        zeldaPriorityLabel = new javax.swing.JLabel();
        zeldaAbilityLabel = new javax.swing.JLabel();
        zeldaLifeLabel = new javax.swing.JLabel();
        zeldaId = new javax.swing.JLabel();
        zeldaStrenght = new javax.swing.JLabel();
        zeldaName = new javax.swing.JLabel();
        zeldaPriority = new javax.swing.JLabel();
        sfImage = new javax.swing.JLabel();
        adjustSpeed = new javax.swing.JSpinner();
        jLabel2 = new javax.swing.JLabel();
        zeldaLife = new javax.swing.JLabel();
        saleRefuerzoZ = new javax.swing.JLabel();
        saleRefuerzoSF = new javax.swing.JLabel();
        victoriesSF = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        AImagen = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        estado = new javax.swing.JTextField();
        zeldaAbility = new javax.swing.JLabel();
        fondoZ = new javax.swing.JLabel();
        fondoSF = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        priorityBackupZ.setColumns(20);
        priorityBackupZ.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        priorityBackupZ.setRows(5);
        priorityBackupZ.setFocusable(false);
        jScrollPane9.setViewportView(priorityBackupZ);

        jPanel1.add(jScrollPane9, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 330, 250, 50));

        priority3Z.setColumns(20);
        priority3Z.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        priority3Z.setRows(5);
        priority3Z.setFocusable(false);
        jScrollPane10.setViewportView(priority3Z);

        jPanel1.add(jScrollPane10, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 250, 250, 50));

        priority2Z.setColumns(20);
        priority2Z.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        priority2Z.setRows(5);
        priority2Z.setFocusable(false);
        jScrollPane11.setViewportView(priority2Z);

        jPanel1.add(jScrollPane11, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 170, 250, 50));

        priority1Z.setColumns(20);
        priority1Z.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        priority1Z.setRows(5);
        priority1Z.setFocusable(false);
        jScrollPane12.setViewportView(priority1Z);

        jPanel1.add(jScrollPane12, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 90, 250, 50));

        mainTitleZelda.setFont(new java.awt.Font("The Wild Breath of Zelda", 1, 36)); // NOI18N
        mainTitleZelda.setForeground(new java.awt.Color(51, 51, 51));
        mainTitleZelda.setText("GAME OF ");
        jPanel1.add(mainTitleZelda, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 70, 140, -1));

        zeldaTitle.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        zeldaTitle.setForeground(new java.awt.Color(51, 51, 51));
        zeldaTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        zeldaTitle.setText("THE LEGEND OF ZELDA: TEARS OF THE KINGDOM");
        jPanel1.add(zeldaTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, -1, -1));

        victoriesZ.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        victoriesZ.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        victoriesZ.setText("0");
        victoriesZ.setFocusable(false);
        jPanel1.add(victoriesZ, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 470, 50, 40));

        zBackupLabel.setFont(new java.awt.Font("The Wild Breath of Zelda", 0, 18)); // NOI18N
        zBackupLabel.setForeground(new java.awt.Color(0, 0, 0));
        zBackupLabel.setText("Refuerzo");
        zBackupLabel.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                zBackupLabelAncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        jPanel1.add(zBackupLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 310, -1, -1));

        victoriesSfLabel.setFont(new java.awt.Font("Brutal Font Pro", 0, 14)); // NOI18N
        victoriesSfLabel.setForeground(new java.awt.Color(255, 255, 255));
        victoriesSfLabel.setText("VICTORIAS SF6");
        jPanel1.add(victoriesSfLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 450, 130, -1));

        sfTitle.setFont(new java.awt.Font("Broadway", 1, 18)); // NOI18N
        sfTitle.setForeground(new java.awt.Color(241, 182, 185));
        sfTitle.setText("STREET FIGHTER 6");
        jPanel1.add(sfTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 40, -1, -1));

        mainTitleSf.setFont(new java.awt.Font("Brutal Font Pro", 1, 32)); // NOI18N
        mainTitleSf.setForeground(new java.awt.Color(255, 255, 255));
        mainTitleSf.setText("THE YEAR");
        jPanel1.add(mainTitleSf, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 70, 190, 40));

        victoriesZLabel.setFont(new java.awt.Font("The Wild Breath of Zelda", 0, 14)); // NOI18N
        victoriesZLabel.setForeground(new java.awt.Color(0, 0, 0));
        victoriesZLabel.setText("VICTORIAS TLOZ");
        jPanel1.add(victoriesZLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 450, -1, -1));

        showWinners.setText("Mostrar ganadores");
        showWinners.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showWinnersActionPerformed(evt);
            }
        });
        jPanel1.add(showWinners, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 470, 150, 50));

        zPriority1Label.setFont(new java.awt.Font("Tempus Sans ITC", 0, 18)); // NOI18N
        zPriority1Label.setForeground(new java.awt.Color(0, 0, 0));
        zPriority1Label.setText("Prioridad 1");
        jPanel1.add(zPriority1Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 70, -1, -1));

        zPriority2Label.setFont(new java.awt.Font("The Wild Breath of Zelda", 0, 18)); // NOI18N
        zPriority2Label.setForeground(new java.awt.Color(0, 0, 0));
        zPriority2Label.setText("Prioridad 2");
        jPanel1.add(zPriority2Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 150, -1, -1));

        zPriority3Label.setFont(new java.awt.Font("The Wild Breath of Zelda", 0, 18)); // NOI18N
        zPriority3Label.setForeground(new java.awt.Color(0, 0, 0));
        zPriority3Label.setText("Prioridad 3");
        jPanel1.add(zPriority3Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 230, -1, -1));

        exitB.setText("Salir");
        exitB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitBActionPerformed(evt);
            }
        });
        jPanel1.add(exitB, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 610, 60, 30));

        sfPriority1Label.setFont(new java.awt.Font("Brutal Font Pro", 0, 18)); // NOI18N
        sfPriority1Label.setForeground(new java.awt.Color(255, 197, 190));
        sfPriority1Label.setText("Prioridad 1");
        jPanel1.add(sfPriority1Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 80, -1, -1));

        priority1SF.setColumns(20);
        priority1SF.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        priority1SF.setRows(5);
        priority1SF.setFocusable(false);
        jScrollPane13.setViewportView(priority1SF);

        jPanel1.add(jScrollPane13, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 100, 250, 50));

        sfPriority2Label.setFont(new java.awt.Font("Brutal Font Pro", 0, 18)); // NOI18N
        sfPriority2Label.setForeground(new java.awt.Color(255, 197, 190));
        sfPriority2Label.setText("Prioridad 2");
        jPanel1.add(sfPriority2Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 160, -1, -1));

        priority2SF.setColumns(20);
        priority2SF.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        priority2SF.setRows(5);
        priority2SF.setFocusable(false);
        jScrollPane14.setViewportView(priority2SF);

        jPanel1.add(jScrollPane14, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 180, 250, 50));

        sfPriority3Label.setFont(new java.awt.Font("Brutal Font Pro", 0, 18)); // NOI18N
        sfPriority3Label.setForeground(new java.awt.Color(255, 197, 190));
        sfPriority3Label.setText("Prioridad 3");
        jPanel1.add(sfPriority3Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 240, -1, -1));

        priorityBackupSF.setColumns(20);
        priorityBackupSF.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        priorityBackupSF.setRows(5);
        priorityBackupSF.setFocusable(false);
        jScrollPane16.setViewportView(priorityBackupSF);

        jPanel1.add(jScrollPane16, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 340, 250, 50));

        priority3SF.setColumns(20);
        priority3SF.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        priority3SF.setRows(5);
        priority3SF.setFocusable(false);
        jScrollPane15.setViewportView(priority3SF);

        jPanel1.add(jScrollPane15, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 260, 250, 50));

        sfBackupLabel.setFont(new java.awt.Font("Brutal Font Pro", 0, 18)); // NOI18N
        sfBackupLabel.setForeground(new java.awt.Color(255, 197, 190));
        sfBackupLabel.setText("Refuerzo");
        jPanel1.add(sfBackupLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 320, -1, -1));

        zeldaImage.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        zeldaImage.setForeground(new java.awt.Color(0, 0, 0));
        zeldaImage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(zeldaImage, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 430, 140, 180));

        sfIdLabel.setFont(new java.awt.Font("Brutal Font Pro", 0, 13)); // NOI18N
        sfIdLabel.setForeground(new java.awt.Color(255, 255, 255));
        sfIdLabel.setText("ID");
        jPanel1.add(sfIdLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 460, -1, -1));

        sfNameLabel.setFont(new java.awt.Font("Brutal Font Pro", 0, 13)); // NOI18N
        sfNameLabel.setForeground(new java.awt.Color(255, 255, 255));
        sfNameLabel.setText("NOMBRE");
        jPanel1.add(sfNameLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 480, -1, -1));

        sfPriorityLabel.setFont(new java.awt.Font("Brutal Font Pro", 0, 13)); // NOI18N
        sfPriorityLabel.setForeground(new java.awt.Color(255, 255, 255));
        sfPriorityLabel.setText("PRIORIDAD");
        jPanel1.add(sfPriorityLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 500, -1, -1));

        sfLifeLabel.setFont(new java.awt.Font("Brutal Font Pro", 0, 13)); // NOI18N
        sfLifeLabel.setForeground(new java.awt.Color(255, 255, 255));
        sfLifeLabel.setText("VIDA");
        jPanel1.add(sfLifeLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 520, -1, -1));

        sfAbilityLabel.setFont(new java.awt.Font("Brutal Font Pro", 0, 13)); // NOI18N
        sfAbilityLabel.setForeground(new java.awt.Color(255, 255, 255));
        sfAbilityLabel.setText("HABILIDADES");
        jPanel1.add(sfAbilityLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 540, -1, -1));

        sfStrenghtLabel.setFont(new java.awt.Font("Brutal Font Pro", 0, 13)); // NOI18N
        sfStrenghtLabel.setForeground(new java.awt.Color(255, 255, 255));
        sfStrenghtLabel.setText("FUERZA");
        jPanel1.add(sfStrenghtLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 560, -1, -1));

        sfAgilityLabel.setFont(new java.awt.Font("Brutal Font Pro", 0, 13)); // NOI18N
        sfAgilityLabel.setForeground(new java.awt.Color(255, 255, 255));
        sfAgilityLabel.setText("AGILIDAD");
        jPanel1.add(sfAgilityLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 580, -1, -1));

        sfId.setForeground(new java.awt.Color(255, 255, 255));
        sfId.setText("...");
        jPanel1.add(sfId, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 460, -1, -1));

        sfName.setForeground(new java.awt.Color(255, 255, 255));
        sfName.setText("...");
        jPanel1.add(sfName, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 480, -1, -1));

        sfPriority.setForeground(new java.awt.Color(255, 255, 255));
        sfPriority.setText("...");
        jPanel1.add(sfPriority, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 500, -1, -1));

        sfLife.setForeground(new java.awt.Color(255, 255, 255));
        sfLife.setText("...");
        jPanel1.add(sfLife, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 520, -1, -1));

        sfAbility.setForeground(new java.awt.Color(255, 255, 255));
        sfAbility.setText("...");
        jPanel1.add(sfAbility, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 540, 20, -1));

        sfStrenght.setForeground(new java.awt.Color(255, 255, 255));
        sfStrenght.setText("...");
        jPanel1.add(sfStrenght, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 560, -1, -1));

        sfAgility.setForeground(new java.awt.Color(255, 255, 255));
        sfAgility.setText("...");
        jPanel1.add(sfAgility, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 580, -1, -1));

        zeldaAgilityLabel.setFont(new java.awt.Font("The Wild Breath of Zelda", 0, 14)); // NOI18N
        zeldaAgilityLabel.setForeground(new java.awt.Color(255, 255, 255));
        zeldaAgilityLabel.setText("AGILIDAD");
        jPanel1.add(zeldaAgilityLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 580, -1, -1));

        zeldaIdLabel.setFont(new java.awt.Font("The Wild Breath of Zelda", 0, 14)); // NOI18N
        zeldaIdLabel.setForeground(new java.awt.Color(255, 255, 255));
        zeldaIdLabel.setText("ID");
        jPanel1.add(zeldaIdLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 460, -1, -1));

        zeldaAgility.setForeground(new java.awt.Color(0, 0, 0));
        zeldaAgility.setText("...");
        jPanel1.add(zeldaAgility, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 580, -1, -1));

        zeldaNameLabel.setFont(new java.awt.Font("The Wild Breath of Zelda", 0, 14)); // NOI18N
        zeldaNameLabel.setForeground(new java.awt.Color(255, 255, 255));
        zeldaNameLabel.setText("NOMBRE");
        jPanel1.add(zeldaNameLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 480, -1, -1));

        zeldaStrenghtLabel.setFont(new java.awt.Font("The Wild Breath of Zelda", 0, 14)); // NOI18N
        zeldaStrenghtLabel.setForeground(new java.awt.Color(255, 255, 255));
        zeldaStrenghtLabel.setText("FUERZA");
        jPanel1.add(zeldaStrenghtLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 560, -1, -1));

        zeldaPriorityLabel.setFont(new java.awt.Font("The Wild Breath of Zelda", 0, 14)); // NOI18N
        zeldaPriorityLabel.setForeground(new java.awt.Color(255, 255, 255));
        zeldaPriorityLabel.setText("PRIORIDAD");
        jPanel1.add(zeldaPriorityLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 500, -1, -1));

        zeldaAbilityLabel.setFont(new java.awt.Font("The Wild Breath of Zelda", 0, 14)); // NOI18N
        zeldaAbilityLabel.setForeground(new java.awt.Color(255, 255, 255));
        zeldaAbilityLabel.setText("HABILIDADES");
        jPanel1.add(zeldaAbilityLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 540, -1, -1));

        zeldaLifeLabel.setFont(new java.awt.Font("The Wild Breath of Zelda", 0, 14)); // NOI18N
        zeldaLifeLabel.setForeground(new java.awt.Color(255, 255, 255));
        zeldaLifeLabel.setText("VIDA");
        jPanel1.add(zeldaLifeLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 520, -1, -1));

        zeldaId.setForeground(new java.awt.Color(0, 0, 0));
        zeldaId.setText("...");
        jPanel1.add(zeldaId, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 460, -1, -1));

        zeldaStrenght.setForeground(new java.awt.Color(0, 0, 0));
        zeldaStrenght.setText("...");
        jPanel1.add(zeldaStrenght, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 560, -1, -1));

        zeldaName.setForeground(new java.awt.Color(0, 0, 0));
        zeldaName.setText("...");
        jPanel1.add(zeldaName, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 480, -1, -1));

        zeldaPriority.setForeground(new java.awt.Color(0, 0, 0));
        zeldaPriority.setText("...");
        jPanel1.add(zeldaPriority, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 500, -1, -1));

        sfImage.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        sfImage.setForeground(new java.awt.Color(255, 255, 255));
        sfImage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(sfImage, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 440, 150, 180));

        adjustSpeed.setModel(new javax.swing.SpinnerNumberModel(3, 3, null, 1));
        jPanel1.add(adjustSpeed, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 550, 80, 30));

        jLabel2.setText("Speed");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 530, -1, -1));

        zeldaLife.setForeground(new java.awt.Color(0, 0, 0));
        zeldaLife.setText("...");
        jPanel1.add(zeldaLife, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 520, -1, -1));

        saleRefuerzoZ.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        saleRefuerzoZ.setForeground(new java.awt.Color(0, 0, 0));
        jPanel1.add(saleRefuerzoZ, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 340, 60, 40));

        saleRefuerzoSF.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        saleRefuerzoSF.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(saleRefuerzoSF, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 350, 60, 40));

        victoriesSF.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        victoriesSF.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        victoriesSF.setText("0");
        victoriesSF.setFocusable(false);
        victoriesSF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                victoriesSFActionPerformed(evt);
            }
        });
        jPanel1.add(victoriesSF, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 470, 50, 40));

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel2.add(AImagen, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 80, 220, 180));

        jLabel1.setText("Estado:");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, 20));

        estado.setEditable(false);
        estado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                estadoActionPerformed(evt);
            }
        });
        jPanel2.add(estado, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 12, 110, 40));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 120, 340, 290));

        zeldaAbility.setForeground(new java.awt.Color(0, 0, 0));
        zeldaAbility.setText("...");
        jPanel1.add(zeldaAbility, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 540, -1, -1));

        fondoZ.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/zelda.jpg"))); // NOI18N
        jPanel1.add(fondoZ, new org.netbeans.lib.awtextra.AbsoluteConstraints(-530, -90, 1110, 750));

        fondoSF.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/sf.jpg"))); // NOI18N
        fondoSF.setText("jLabel8");
        jPanel1.add(fondoSF, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, -190, 1590, 910));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1180, 660));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void showWinnersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showWinnersActionPerformed
       
        (this.winners).imprimirLista();
        

    }//GEN-LAST:event_showWinnersActionPerformed

    private void exitBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitBActionPerformed
        System.exit(0);
    }//GEN-LAST:event_exitBActionPerformed

    private void zBackupLabelAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_zBackupLabelAncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_zBackupLabelAncestorAdded

    private void victoriesSFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_victoriesSFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_victoriesSFActionPerformed

    private void estadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_estadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_estadoActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel AImagen;
    private javax.swing.JSpinner adjustSpeed;
    private javax.swing.JTextField estado;
    private javax.swing.JButton exitB;
    private javax.swing.JLabel fondoSF;
    private javax.swing.JLabel fondoZ;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane16;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JLabel mainTitleSf;
    private javax.swing.JLabel mainTitleZelda;
    private javax.swing.JTextArea priority1SF;
    private javax.swing.JTextArea priority1Z;
    private javax.swing.JTextArea priority2SF;
    private javax.swing.JTextArea priority2Z;
    private javax.swing.JTextArea priority3SF;
    private javax.swing.JTextArea priority3Z;
    private javax.swing.JTextArea priorityBackupSF;
    private javax.swing.JTextArea priorityBackupZ;
    private javax.swing.JLabel saleRefuerzoSF;
    private javax.swing.JLabel saleRefuerzoZ;
    private javax.swing.JLabel sfAbility;
    private javax.swing.JLabel sfAbilityLabel;
    private javax.swing.JLabel sfAgility;
    private javax.swing.JLabel sfAgilityLabel;
    private javax.swing.JLabel sfBackupLabel;
    private javax.swing.JLabel sfId;
    private javax.swing.JLabel sfIdLabel;
    private javax.swing.JLabel sfImage;
    private javax.swing.JLabel sfLife;
    private javax.swing.JLabel sfLifeLabel;
    private javax.swing.JLabel sfName;
    private javax.swing.JLabel sfNameLabel;
    private javax.swing.JLabel sfPriority;
    private javax.swing.JLabel sfPriority1Label;
    private javax.swing.JLabel sfPriority2Label;
    private javax.swing.JLabel sfPriority3Label;
    private javax.swing.JLabel sfPriorityLabel;
    private javax.swing.JLabel sfStrenght;
    private javax.swing.JLabel sfStrenghtLabel;
    private javax.swing.JLabel sfTitle;
    private javax.swing.JButton showWinners;
    private javax.swing.JTextField victoriesSF;
    private javax.swing.JLabel victoriesSfLabel;
    private javax.swing.JTextField victoriesZ;
    private javax.swing.JLabel victoriesZLabel;
    private javax.swing.JLabel zBackupLabel;
    private javax.swing.JLabel zPriority1Label;
    private javax.swing.JLabel zPriority2Label;
    private javax.swing.JLabel zPriority3Label;
    private javax.swing.JLabel zeldaAbility;
    private javax.swing.JLabel zeldaAbilityLabel;
    private javax.swing.JLabel zeldaAgility;
    private javax.swing.JLabel zeldaAgilityLabel;
    private javax.swing.JLabel zeldaId;
    private javax.swing.JLabel zeldaIdLabel;
    private javax.swing.JLabel zeldaImage;
    private javax.swing.JLabel zeldaLife;
    private javax.swing.JLabel zeldaLifeLabel;
    private javax.swing.JLabel zeldaName;
    private javax.swing.JLabel zeldaNameLabel;
    private javax.swing.JLabel zeldaPriority;
    private javax.swing.JLabel zeldaPriorityLabel;
    private javax.swing.JLabel zeldaStrenght;
    private javax.swing.JLabel zeldaStrenghtLabel;
    private javax.swing.JLabel zeldaTitle;
    // End of variables declaration//GEN-END:variables
}

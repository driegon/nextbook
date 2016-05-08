/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package book.next;

import basededatos.conexion;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Deisy
 */
public class Principal extends javax.swing.JFrame {

    /**
     * Creates new form Principal
     */
    public int id_usuario = 0;
    String[] LibrosCarretilla = new String[5];
    String[] LibrosRecomendacion = new String[5];
    //String[] LibrosRecomendacion = new String[5];
    //String[] LibrosRecomendacion = new String[5];
    String[] LibrosAprendizaje = new String[5];
    conexion bdd = new conexion();
    
    public Principal(int id) {
        initComponents();
        Inicializar(id);       
    }
    
    public void Inicializar(int id){
        id_usuario = id;
        MostrarRecomendacion();
        MostrarCarretilla();   
    }

    public void RecalcularPuntajeLibro(String Punteo, String id_Carretilla)
    {
        //Se recalcula el punteo del libro en base al puesto por el usuario        
        //Agregar a leído                
        String retorno = "";
        int Promedio = 0; 
        int Lectores = 0; 
        int id_Libro = 0;         
        ResultSet resultado = null;
        
        //consulta en Libro los datos del libro a partir del id_carretilla
        resultado = bdd.consulta("SELECT l.id, l.Punteo, l.Lectores FROM carretilla c " +
        "INNER JOIN libro l on (l.id = c.id_libro) " + " WHERE c.id = " + id_Carretilla);
               
        try{
            while (resultado.next()) {
                Promedio = resultado.getInt("Punteo");
                Lectores = resultado.getInt("Lectores");
                id_Libro = resultado.getInt("id");
            }
            resultado.close();
        }catch(SQLException ex)
        {
            System.out.println("SQLException: "+ ex.getMessage());        
        }               
        
        Lectores = Lectores + 1;
        Promedio = (Promedio + Integer.parseInt(Punteo));
        Promedio = Promedio / Lectores;
        
        retorno = bdd.ejecutarQuery("UPDATE libro SET Punteo = " + Promedio
            + ", Lectores = " + Lectores + " WHERE id = " + id_Libro);              

        if (retorno.compareTo("") != 0)
        {
            JOptionPane.showMessageDialog(null, "ERROR: "+retorno, "¡Atención!", JOptionPane.ERROR_MESSAGE);
        }                    
    }
    
    public void  MostrarRecomendacion()
    {
        //Segun sea el caso se muestra la recomendacion adecuada        
        //Si es usuario nuevo se muestra la primaria
        MostrarPrimaria();
        
        //Si ya ha hecho búsquedas se muestra por similitud
        //MostrarPorSimilitud();
        
        //Si ya ha leído libros
        MostrarPorAprendizaje();
    }
    
    public void MostrarPrimaria()
    {   
        LibrosRecomendacion = null;
        LibrosRecomendacion = BookNext.RecomendacionPrimaria(id_usuario);
                
        if(LibrosRecomendacion[0] == null){
            jLabel9.setVisible(false);
            //jButton7.disable();
        }else{
            jLabel9.setVisible(true);
            jLabel9.setText(LibrosRecomendacion[0].split(",")[1]);
        }
        
        if(LibrosRecomendacion[1] == null){
            jLabel10.setVisible(false);
        }else{
            jLabel10.setVisible(true);
            jLabel10.setText(LibrosRecomendacion[1].split(",")[1]);
        }
        
        if(LibrosRecomendacion[2] == null){
            jLabel11.setVisible(false);
        }else{
            jLabel11.setVisible(true);
            jLabel11.setText(LibrosRecomendacion[2].split(",")[1]);
        }
        
        if(LibrosRecomendacion[3] == null){
            jLabel12.setVisible(false);
        }else{
            jLabel12.setVisible(true);
            jLabel12.setText(LibrosRecomendacion[3].split(",")[1]);
        }
        
        if(LibrosRecomendacion[4] == null){
            jLabel13.setVisible(false);
        }else{
            jLabel13.setVisible(true);
            jLabel13.setText(LibrosRecomendacion[4].split(",")[1]);
        } 
        
    }
    
    public void MostrarPorSimilitud()
    {
        LibrosRecomendacion = null;
        LibrosRecomendacion = BookNext.RecomendacionPorSimilitud(id_usuario);
                
        if(LibrosRecomendacion[0] == null){
            jLabel9.setVisible(false);
        }else{
            jLabel9.setVisible(true);
            jLabel9.setText(LibrosRecomendacion[0].split(",")[1]);
        }
        
        if(LibrosRecomendacion[1] == null){
            jLabel10.setVisible(false);
        }else{
            jLabel10.setVisible(true);
            jLabel10.setText(LibrosRecomendacion[1].split(",")[1]);
        }
        
        if(LibrosRecomendacion[2] == null){
            jLabel11.setVisible(false);
        }else{
            jLabel11.setVisible(true);
            jLabel11.setText(LibrosRecomendacion[2].split(",")[1]);
        }
        
        if(LibrosRecomendacion[3] == null){
            jLabel12.setVisible(false);
        }else{
            jLabel12.setVisible(true);
            jLabel12.setText(LibrosRecomendacion[3].split(",")[1]);
        }
        
        if(LibrosRecomendacion[4] == null){
            jLabel13.setVisible(false);
        }else{
            jLabel13.setVisible(true);
            jLabel13.setText(LibrosRecomendacion[4].split(",")[1]);
        } 
        
    }
    
    public void MostrarPorAprendizaje()
    {
        LibrosAprendizaje = null;       
        LibrosAprendizaje = BookNext.RecomendacionPorAprendizaje(id_usuario);
                
        if(LibrosAprendizaje[0] == null){
            jLabel14.setVisible(false);
        }else{
            jLabel14.setVisible(true);
            jLabel14.setText(LibrosAprendizaje[0].split(",")[1]);
        }
        
        if(LibrosAprendizaje[1] == null){
            jLabel15.setVisible(false);
        }else{
            jLabel15.setVisible(true);
            jLabel15.setText(LibrosAprendizaje[1].split(",")[1]);
        }
        
        if(LibrosAprendizaje[2] == null){
            jLabel16.setVisible(false);
        }else{
            jLabel16.setVisible(true);
            jLabel16.setText(LibrosAprendizaje[2].split(",")[1]);
        }
        
        if(LibrosAprendizaje[3] == null){
            jLabel17.setVisible(false);
        }else{
            jLabel17.setVisible(true);
            jLabel17.setText(LibrosAprendizaje[3].split(",")[1]);
        }
        
        if(LibrosAprendizaje[4] == null){
            jLabel18.setVisible(false);
        }else{
            jLabel18.setVisible(true);
            jLabel18.setText(LibrosAprendizaje[4].split(",")[1]);
        }         
    }
    
    public void MostrarCarretilla()
    {        
        LibrosCarretilla = null;
        LibrosCarretilla = BookNext.TraerCarretilla(id_usuario);
                
        if(LibrosCarretilla[0] == null){
            jLabel19.setVisible(false);
            tb_punteo1.setVisible(false);
        }else{
            jLabel19.setVisible(true);
            tb_punteo1.setVisible(true);
            jLabel19.setText(LibrosCarretilla[0].split(",")[1]);
        }
        
        if(LibrosCarretilla[1] == null){
            jLabel20.setVisible(false);
            jTextField2.setVisible(false);
        }else{
            jLabel20.setVisible(true);
            jTextField2.setVisible(true);
            jLabel20.setText(LibrosCarretilla[1].split(",")[1]);
        }
        
        if(LibrosCarretilla[2] == null){
            jLabel21.setVisible(false);
            jTextField3.setVisible(false);
        }else{
            jLabel21.setVisible(true);
            jTextField3.setVisible(true);
            jLabel21.setText(LibrosCarretilla[2].split(",")[1]);
        }
        
        if(LibrosCarretilla[3] == null){
            jLabel22.setVisible(false);
            jTextField4.setVisible(false);
        }else{
            jLabel22.setVisible(true);
            jTextField4.setVisible(true);
            jLabel22.setText(LibrosCarretilla[3].split(",")[1]);
        }
        
        if(LibrosCarretilla[4] == null){
            jLabel23.setVisible(false);
            jTextField5.setVisible(false);
        }else{
            jLabel23.setVisible(true);
            jTextField5.setVisible(true);
            jLabel23.setText(LibrosCarretilla[4].split(",")[1]);
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

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        tb_agregar1 = new javax.swing.JButton();
        tb_agregar2 = new javax.swing.JButton();
        tb_agregar3 = new javax.swing.JButton();
        tb_agregar4 = new javax.swing.JButton();
        tb_agregar5 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jButton17 = new javax.swing.JButton();
        tb_punteo2 = new javax.swing.JButton();
        tb_punteo3 = new javax.swing.JButton();
        tb_punteo4 = new javax.swing.JButton();
        tb_punteo5 = new javax.swing.JButton();
        tb_punteo1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        tb_agregar6 = new javax.swing.JButton();
        tb_agregar7 = new javax.swing.JButton();
        tb_agregar8 = new javax.swing.JButton();
        tb_agregar9 = new javax.swing.JButton();
        tb_agregar10 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel1.setText("Book.Next");
        jLabel1.setToolTipText("");

        jLabel2.setText("¡Bienvenido!");

        jButton1.setText("Buscar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Te Recomendamos:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N

        jLabel9.setText("Libro1");
        jLabel9.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel10.setText("Libro2");
        jLabel10.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel11.setText("Libro3");
        jLabel11.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel12.setText("Libro4");
        jLabel12.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel13.setText("Libro5");
        jLabel13.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tb_agregar1.setText("Agregar");
        tb_agregar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tb_agregar1ActionPerformed(evt);
            }
        });

        tb_agregar2.setText("Agregar");
        tb_agregar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tb_agregar2ActionPerformed(evt);
            }
        });

        tb_agregar3.setText("Agregar");
        tb_agregar3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tb_agregar3ActionPerformed(evt);
            }
        });

        tb_agregar4.setText("Agregar");
        tb_agregar4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tb_agregar4ActionPerformed(evt);
            }
        });

        tb_agregar5.setText("Agregar");
        tb_agregar5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tb_agregar5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(tb_agregar1, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(tb_agregar2, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(tb_agregar3, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(tb_agregar4, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(tb_agregar5, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(102, 102, 102))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tb_agregar1)
                    .addComponent(tb_agregar2)
                    .addComponent(tb_agregar3)
                    .addComponent(tb_agregar4)
                    .addComponent(tb_agregar5))
                .addContainerGap())
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Carretilla:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N

        jLabel19.setText("Libro1");
        jLabel19.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel20.setText("Libro2");
        jLabel20.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel21.setText("Libro3");
        jLabel21.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel22.setText("Libro4");
        jLabel22.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel23.setText("Libro5");
        jLabel23.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jButton17.setText("Leído");
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });

        tb_punteo2.setText("Leído");
        tb_punteo2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tb_punteo2ActionPerformed(evt);
            }
        });

        tb_punteo3.setText("Leído");
        tb_punteo3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tb_punteo3ActionPerformed(evt);
            }
        });

        tb_punteo4.setText("Leído");
        tb_punteo4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tb_punteo4ActionPerformed(evt);
            }
        });

        tb_punteo5.setText("Leído");
        tb_punteo5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tb_punteo5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton17, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tb_punteo1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(tb_punteo2, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(tb_punteo3, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(tb_punteo4, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(tb_punteo5, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(102, 102, 102))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE)
                            .addComponent(jTextField2))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE)
                            .addComponent(jTextField3))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE)
                            .addComponent(jTextField4))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE)
                            .addComponent(jTextField5))
                        .addContainerGap())))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE, false)
                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tb_punteo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton17)
                    .addComponent(tb_punteo2)
                    .addComponent(tb_punteo3)
                    .addComponent(tb_punteo4)
                    .addComponent(tb_punteo5))
                .addGap(22, 22, 22))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Por tus lecturas:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N

        jLabel14.setText("Libro1");
        jLabel14.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel15.setText("Libro2");
        jLabel15.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel16.setText("Libro3");
        jLabel16.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel17.setText("Libro4");
        jLabel17.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel18.setText("Libro5");
        jLabel18.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tb_agregar6.setText("Agregar");
        tb_agregar6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tb_agregar6ActionPerformed(evt);
            }
        });

        tb_agregar7.setText("Agregar");
        tb_agregar7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tb_agregar7ActionPerformed(evt);
            }
        });

        tb_agregar8.setText("Agregar");
        tb_agregar8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tb_agregar8ActionPerformed(evt);
            }
        });

        tb_agregar9.setText("Agregar");
        tb_agregar9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tb_agregar9ActionPerformed(evt);
            }
        });

        tb_agregar10.setText("Agregar");
        tb_agregar10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tb_agregar10ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(tb_agregar6, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(tb_agregar7, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(tb_agregar8, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(tb_agregar9, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(tb_agregar10, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(102, 102, 102))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tb_agregar6)
                    .addComponent(tb_agregar7)
                    .addComponent(tb_agregar8)
                    .addComponent(tb_agregar9)
                    .addComponent(tb_agregar10))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 538, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 538, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 538, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        Buscador FrmBuscador= new Buscador(id_usuario);//"jFrame2" Tu colocas el nombre que le hayas puesto a tu segundo jFrame 

        FrmBuscador.setVisible(true); //muestra el segundo jFrame
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
            
        if(tb_punteo1.getText().isEmpty() == true)
        {
            JOptionPane.showMessageDialog(null, "¡Debe intresar el punteo del libro!", 
                    "¡Atención!", JOptionPane.ERROR_MESSAGE);
        }
        else
        {
            //Agregar a leído                
            String retorno = "";
            retorno = bdd.ejecutarQuery("update carretilla set leido = 1, Fecha_leido = now(), punteo = "
                    + tb_punteo1.getText() + " where id = " + LibrosCarretilla[0].split(",")[0]);              
            
            if (retorno.compareTo("") != 0)
            {
                JOptionPane.showMessageDialog(null, "ERROR: "+retorno, "¡Atención!", JOptionPane.ERROR_MESSAGE);
            }
            else
            {
                JOptionPane.showMessageDialog(null, "¡Libro leído!", "¡Atención!", JOptionPane.INFORMATION_MESSAGE);                
                RecalcularPuntajeLibro(tb_punteo1.getText(), LibrosCarretilla[0].split(",")[0]);
                tb_punteo1.setText("");
                MostrarCarretilla();                   
            }       
        }            
    }//GEN-LAST:event_jButton17ActionPerformed

    private void tb_punteo2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tb_punteo2ActionPerformed
        if(tb_punteo2.getText().isEmpty() == true)
        {
            JOptionPane.showMessageDialog(null, "¡Debe intresar el punteo del libro!", 
                    "¡Atención!", JOptionPane.ERROR_MESSAGE);
        }
        else
        {
            //Agregar a leído                
            String retorno = "";
            retorno = bdd.ejecutarQuery("update carretilla set leido = 1, Fecha_leido = now(), punteo = "
                    + tb_punteo2.getText() + " where id = " + LibrosCarretilla[1].split(",")[0]);              
            
            if (retorno.compareTo("") != 0)
            {
                JOptionPane.showMessageDialog(null, "ERROR: "+retorno, "¡Atención!", JOptionPane.ERROR_MESSAGE);
            }
            else
            {
                JOptionPane.showMessageDialog(null, "¡Libro leído!", "¡Atención!", JOptionPane.INFORMATION_MESSAGE);                
                RecalcularPuntajeLibro(tb_punteo2.getText(), LibrosCarretilla[1].split(",")[0]);
                tb_punteo2.setText("");
                MostrarCarretilla();                   
            }       
        } 
    }//GEN-LAST:event_tb_punteo2ActionPerformed

    private void tb_punteo3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tb_punteo3ActionPerformed
        if(tb_punteo3.getText().isEmpty() == true)
        {
            JOptionPane.showMessageDialog(null, "¡Debe intresar el punteo del libro!", 
                    "¡Atención!", JOptionPane.ERROR_MESSAGE);
        }
        else
        {
            //Agregar a leído                
            String retorno = "";
            retorno = bdd.ejecutarQuery("update carretilla set leido = 1, Fecha_leido = now(), punteo = "
                    + tb_punteo3.getText() + " where id = " + LibrosCarretilla[2].split(",")[0]);              
            
            if (retorno.compareTo("") != 0)
            {
                JOptionPane.showMessageDialog(null, "ERROR: "+retorno, "¡Atención!", JOptionPane.ERROR_MESSAGE);
            }
            else
            {
                JOptionPane.showMessageDialog(null, "¡Libro leído!", "¡Atención!", JOptionPane.INFORMATION_MESSAGE);                
                RecalcularPuntajeLibro(tb_punteo3.getText(), LibrosCarretilla[2].split(",")[0]);
                tb_punteo3.setText("");
                MostrarCarretilla();                   
            }       
        } 
    }//GEN-LAST:event_tb_punteo3ActionPerformed

    private void tb_punteo4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tb_punteo4ActionPerformed
        if(tb_punteo4.getText().isEmpty() == true)
        {
            JOptionPane.showMessageDialog(null, "¡Debe intresar el punteo del libro!", 
                    "¡Atención!", JOptionPane.ERROR_MESSAGE);
        }
        else
        {
            //Agregar a leído                
            String retorno = "";
            retorno = bdd.ejecutarQuery("update carretilla set leido = 1, Fecha_leido = now(), punteo = "
                    + tb_punteo4.getText() + " where id = " + LibrosCarretilla[3].split(",")[0]);              
            
            if (retorno.compareTo("") != 0)
            {
                JOptionPane.showMessageDialog(null, "ERROR: "+retorno, "¡Atención!", JOptionPane.ERROR_MESSAGE);
            }
            else
            {
                JOptionPane.showMessageDialog(null, "¡Libro leído!", "¡Atención!", JOptionPane.INFORMATION_MESSAGE);                
                RecalcularPuntajeLibro(tb_punteo4.getText(), LibrosCarretilla[3].split(",")[0]);
                tb_punteo4.setText("");
                MostrarCarretilla();                   
            }       
        } 
    }//GEN-LAST:event_tb_punteo4ActionPerformed

    private void tb_punteo5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tb_punteo5ActionPerformed
        if(tb_punteo5.getText().isEmpty() == true)
        {
            JOptionPane.showMessageDialog(null, "¡Debe intresar el punteo del libro!", 
                    "¡Atención!", JOptionPane.ERROR_MESSAGE);
        }
        else
        {
            //Agregar a leído                
            String retorno = "";
            retorno = bdd.ejecutarQuery("update carretilla set leido = 1, Fecha_leido = now(), punteo = "
                    + tb_punteo5.getText() + " where id = " + LibrosCarretilla[4].split(",")[0]);              
            
            if (retorno.compareTo("") != 0)
            {
                JOptionPane.showMessageDialog(null, "ERROR: "+retorno, "¡Atención!", JOptionPane.ERROR_MESSAGE);
            }
            else
            {
                JOptionPane.showMessageDialog(null, "¡Libro leído!", "¡Atención!", JOptionPane.INFORMATION_MESSAGE);                
                RecalcularPuntajeLibro(tb_punteo5.getText(), LibrosCarretilla[4].split(",")[0]);
                tb_punteo5.setText("");
                MostrarCarretilla();                   
            }       
        }
    }//GEN-LAST:event_tb_punteo5ActionPerformed

    private void tb_agregar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tb_agregar1ActionPerformed
        //INSERT INTO carretilla (id, id_usuario, id_libro, leido, punteo, estatus, Fecha_leido) 
        //VALUES (NULL,id_usuario,id_libro,b'0','0','1','');
        String retorno = "";
        retorno = bdd.ejecutarQuery("INSERT INTO carretilla (id, id_usuario, id_libro, leido, punteo, estatus, "
                + "Fecha_leido) VALUES (NULL, " + id_usuario + ", " + LibrosRecomendacion[0].split(",")[0] 
                + ", 0, 0, 1, '0000-00-00 00:00:00')");        
        

        if (retorno.compareTo("") != 0)
        {
            JOptionPane.showMessageDialog(null, "ERROR: "+retorno, "¡Atención!", JOptionPane.ERROR_MESSAGE);
        }
        else
        {
            JOptionPane.showMessageDialog(null, "¡Libro agregado a Carretilla!", "¡Atención!", JOptionPane.INFORMATION_MESSAGE);
            MostrarRecomendacion();   
            MostrarCarretilla();
        }
    }//GEN-LAST:event_tb_agregar1ActionPerformed

    private void tb_agregar6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tb_agregar6ActionPerformed
        //INSERT INTO carretilla (id, id_usuario, id_libro, leido, punteo, estatus, Fecha_leido) 
        //VALUES (NULL,id_usuario,id_libro,b'0','0','1','');
        String retorno = "";
        retorno = bdd.ejecutarQuery("INSERT INTO carretilla (id, id_usuario, id_libro, leido, punteo, estatus, "
                + "Fecha_leido) VALUES (NULL, " + id_usuario + ", " + LibrosAprendizaje[0].split(",")[0] 
                + ", 0, 0, 1, '0000-00-00 00:00:00')");        
        

        if (retorno.compareTo("") != 0)
        {
            JOptionPane.showMessageDialog(null, "ERROR: "+retorno, "¡Atención!", JOptionPane.ERROR_MESSAGE);
        }
        else
        {
            JOptionPane.showMessageDialog(null, "¡Libro agregado a Carretilla!", "¡Atención!", JOptionPane.INFORMATION_MESSAGE);
            MostrarRecomendacion();   
            MostrarCarretilla();
        }
    }//GEN-LAST:event_tb_agregar6ActionPerformed

    private void tb_agregar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tb_agregar2ActionPerformed
        //INSERT INTO carretilla (id, id_usuario, id_libro, leido, punteo, estatus, Fecha_leido) 
        //VALUES (NULL,id_usuario,id_libro,b'0','0','1','');
        String retorno = "";
        retorno = bdd.ejecutarQuery("INSERT INTO carretilla (id, id_usuario, id_libro, leido, punteo, estatus, "
                + "Fecha_leido) VALUES (NULL, " + id_usuario + ", " + LibrosRecomendacion[1].split(",")[0] 
                + ", 0, 0, 1, '0000-00-00 00:00:00')");        
        

        if (retorno.compareTo("") != 0)
        {
            JOptionPane.showMessageDialog(null, "ERROR: "+retorno, "¡Atención!", JOptionPane.ERROR_MESSAGE);
        }
        else
        {
            JOptionPane.showMessageDialog(null, "¡Libro agregado a Carretilla!", "¡Atención!", JOptionPane.INFORMATION_MESSAGE);
            MostrarRecomendacion();   
            MostrarCarretilla();
        }
    }//GEN-LAST:event_tb_agregar2ActionPerformed

    private void tb_agregar3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tb_agregar3ActionPerformed
        //INSERT INTO carretilla (id, id_usuario, id_libro, leido, punteo, estatus, Fecha_leido) 
        //VALUES (NULL,id_usuario,id_libro,b'0','0','1','');
        String retorno = "";
        retorno = bdd.ejecutarQuery("INSERT INTO carretilla (id, id_usuario, id_libro, leido, punteo, estatus, "
                + "Fecha_leido) VALUES (NULL, " + id_usuario + ", " + LibrosRecomendacion[2].split(",")[0] 
                + ", 0, 0, 1, '0000-00-00 00:00:00')");        
        

        if (retorno.compareTo("") != 0)
        {
            JOptionPane.showMessageDialog(null, "ERROR: "+retorno, "¡Atención!", JOptionPane.ERROR_MESSAGE);
        }
        else
        {
            JOptionPane.showMessageDialog(null, "¡Libro agregado a Carretilla!", "¡Atención!", JOptionPane.INFORMATION_MESSAGE);
            MostrarRecomendacion();   
            MostrarCarretilla();
        }
    }//GEN-LAST:event_tb_agregar3ActionPerformed

    private void tb_agregar4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tb_agregar4ActionPerformed
        //INSERT INTO carretilla (id, id_usuario, id_libro, leido, punteo, estatus, Fecha_leido) 
        //VALUES (NULL,id_usuario,id_libro,b'0','0','1','');
        String retorno = "";
        retorno = bdd.ejecutarQuery("INSERT INTO carretilla (id, id_usuario, id_libro, leido, punteo, estatus, "
                + "Fecha_leido) VALUES (NULL, " + id_usuario + ", " + LibrosRecomendacion[3].split(",")[0] 
                + ", 0, 0, 1, '0000-00-00 00:00:00')");        
        

        if (retorno.compareTo("") != 0)
        {
            JOptionPane.showMessageDialog(null, "ERROR: "+retorno, "¡Atención!", JOptionPane.ERROR_MESSAGE);
        }
        else
        {
            JOptionPane.showMessageDialog(null, "¡Libro agregado a Carretilla!", "¡Atención!", JOptionPane.INFORMATION_MESSAGE);
            MostrarRecomendacion();   
            MostrarCarretilla();
        }
    }//GEN-LAST:event_tb_agregar4ActionPerformed

    private void tb_agregar5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tb_agregar5ActionPerformed
        //INSERT INTO carretilla (id, id_usuario, id_libro, leido, punteo, estatus, Fecha_leido) 
        //VALUES (NULL,id_usuario,id_libro,b'0','0','1','');
        String retorno = "";
        retorno = bdd.ejecutarQuery("INSERT INTO carretilla (id, id_usuario, id_libro, leido, punteo, estatus, "
                + "Fecha_leido) VALUES (NULL, " + id_usuario + ", " + LibrosRecomendacion[4].split(",")[0] 
                + ", 0, 0, 1, '0000-00-00 00:00:00')");        
        

        if (retorno.compareTo("") != 0)
        {
            JOptionPane.showMessageDialog(null, "ERROR: "+retorno, "¡Atención!", JOptionPane.ERROR_MESSAGE);
        }
        else
        {
            JOptionPane.showMessageDialog(null, "¡Libro agregado a Carretilla!", "¡Atención!", JOptionPane.INFORMATION_MESSAGE);
            MostrarRecomendacion();   
            MostrarCarretilla();
        }
    }//GEN-LAST:event_tb_agregar5ActionPerformed

    private void tb_agregar7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tb_agregar7ActionPerformed
        //INSERT INTO carretilla (id, id_usuario, id_libro, leido, punteo, estatus, Fecha_leido) 
        //VALUES (NULL,id_usuario,id_libro,b'0','0','1','');
        String retorno = "";
        retorno = bdd.ejecutarQuery("INSERT INTO carretilla (id, id_usuario, id_libro, leido, punteo, estatus, "
                + "Fecha_leido) VALUES (NULL, " + id_usuario + ", " + LibrosAprendizaje[1].split(",")[0] 
                + ", 0, 0, 1, '0000-00-00 00:00:00')");        
        

        if (retorno.compareTo("") != 0)
        {
            JOptionPane.showMessageDialog(null, "ERROR: "+retorno, "¡Atención!", JOptionPane.ERROR_MESSAGE);
        }
        else
        {
            JOptionPane.showMessageDialog(null, "¡Libro agregado a Carretilla!", "¡Atención!", JOptionPane.INFORMATION_MESSAGE);
            MostrarRecomendacion();   
            MostrarCarretilla();
        }
    }//GEN-LAST:event_tb_agregar7ActionPerformed

    private void tb_agregar8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tb_agregar8ActionPerformed
        //INSERT INTO carretilla (id, id_usuario, id_libro, leido, punteo, estatus, Fecha_leido) 
        //VALUES (NULL,id_usuario,id_libro,b'0','0','1','');
        String retorno = "";
        retorno = bdd.ejecutarQuery("INSERT INTO carretilla (id, id_usuario, id_libro, leido, punteo, estatus, "
                + "Fecha_leido) VALUES (NULL, " + id_usuario + ", " + LibrosAprendizaje[2].split(",")[0] 
                + ", 0, 0, 1, '0000-00-00 00:00:00')");        
        

        if (retorno.compareTo("") != 0)
        {
            JOptionPane.showMessageDialog(null, "ERROR: "+retorno, "¡Atención!", JOptionPane.ERROR_MESSAGE);
        }
        else
        {
            JOptionPane.showMessageDialog(null, "¡Libro agregado a Carretilla!", "¡Atención!", JOptionPane.INFORMATION_MESSAGE);
            MostrarRecomendacion();   
            MostrarCarretilla();
        }
    }//GEN-LAST:event_tb_agregar8ActionPerformed

    private void tb_agregar9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tb_agregar9ActionPerformed
        //INSERT INTO carretilla (id, id_usuario, id_libro, leido, punteo, estatus, Fecha_leido) 
        //VALUES (NULL,id_usuario,id_libro,b'0','0','1','');
        String retorno = "";
        retorno = bdd.ejecutarQuery("INSERT INTO carretilla (id, id_usuario, id_libro, leido, punteo, estatus, "
                + "Fecha_leido) VALUES (NULL, " + id_usuario + ", " + LibrosAprendizaje[3].split(",")[0] 
                + ", 0, 0, 1, '0000-00-00 00:00:00')");        
        

        if (retorno.compareTo("") != 0)
        {
            JOptionPane.showMessageDialog(null, "ERROR: "+retorno, "¡Atención!", JOptionPane.ERROR_MESSAGE);
        }
        else
        {
            JOptionPane.showMessageDialog(null, "¡Libro agregado a Carretilla!", "¡Atención!", JOptionPane.INFORMATION_MESSAGE);
            MostrarRecomendacion();   
            MostrarCarretilla();
        }
    }//GEN-LAST:event_tb_agregar9ActionPerformed

    private void tb_agregar10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tb_agregar10ActionPerformed
        //INSERT INTO carretilla (id, id_usuario, id_libro, leido, punteo, estatus, Fecha_leido) 
        //VALUES (NULL,id_usuario,id_libro,b'0','0','1','');
        String retorno = "";
        retorno = bdd.ejecutarQuery("INSERT INTO carretilla (id, id_usuario, id_libro, leido, punteo, estatus, "
                + "Fecha_leido) VALUES (NULL, " + id_usuario + ", " + LibrosAprendizaje[4].split(",")[0] 
                + ", 0, 0, 1, '0000-00-00 00:00:00')");        
        

        if (retorno.compareTo("") != 0)
        {
            JOptionPane.showMessageDialog(null, "ERROR: "+retorno, "¡Atención!", JOptionPane.ERROR_MESSAGE);
        }
        else
        {
            JOptionPane.showMessageDialog(null, "¡Libro agregado a Carretilla!", "¡Atención!", JOptionPane.INFORMATION_MESSAGE);
            MostrarRecomendacion();   
            MostrarCarretilla();
        }
    }//GEN-LAST:event_tb_agregar10ActionPerformed

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
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Principal(0).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton17;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JButton tb_agregar1;
    private javax.swing.JButton tb_agregar10;
    private javax.swing.JButton tb_agregar2;
    private javax.swing.JButton tb_agregar3;
    private javax.swing.JButton tb_agregar4;
    private javax.swing.JButton tb_agregar5;
    private javax.swing.JButton tb_agregar6;
    private javax.swing.JButton tb_agregar7;
    private javax.swing.JButton tb_agregar8;
    private javax.swing.JButton tb_agregar9;
    private javax.swing.JTextField tb_punteo1;
    private javax.swing.JButton tb_punteo2;
    private javax.swing.JButton tb_punteo3;
    private javax.swing.JButton tb_punteo4;
    private javax.swing.JButton tb_punteo5;
    // End of variables declaration//GEN-END:variables
}

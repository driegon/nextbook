/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package book.next;

import basededatos.conexion;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.Parent;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.text.DateFormatter;

/**
 *
 * @author digut
 */
public class Usuario extends javax.swing.JFrame {
    private conexion bdd = new conexion();

    /**
     * Creates new form Usuario
     */
    public Usuario() {
        initComponents();
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
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        tb_usuario = new javax.swing.JTextField();
        tb_pass1 = new javax.swing.JPasswordField();
        tb_pass2 = new javax.swing.JPasswordField();
        tb_nombre = new javax.swing.JTextField();
        tb_apellido = new javax.swing.JTextField();
        tb_pais = new javax.swing.JTextField();
        tb_ciudad = new javax.swing.JTextField();
        rb_femenino = new javax.swing.JRadioButton();
        rb_masculino = new javax.swing.JRadioButton();
        jLabel10 = new javax.swing.JLabel();
        btn_guardar = new javax.swing.JButton();
        tb_fechaNac = new javax.swing.JFormattedTextField();

        setType(java.awt.Window.Type.POPUP);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jLabel1.setText("Nombre:");

        jLabel2.setText("Apellido:");

        jLabel3.setText("Usuario:");

        jLabel4.setText("Contraseña:");

        jLabel5.setText("Confirmar Contraseña:");

        jLabel6.setText("Fecha de nacimiento:");

        jLabel7.setText("Sexo:");

        jLabel8.setText("Ciudad:");

        jLabel9.setText("Pais:");

        rb_femenino.setText("Femenino");

        rb_masculino.setSelected(true);
        rb_masculino.setText("Masculino");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel10.setText("Book.Next");
        jLabel10.setToolTipText("");

        btn_guardar.setIcon(new javax.swing.ImageIcon("C:\\Users\\digut\\Desktop\\icons\\disk.png")); // NOI18N
        btn_guardar.setToolTipText("Guardar");
        btn_guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_guardarActionPerformed(evt);
            }
        });

        tb_fechaNac.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("dd/MM/yyyy"))));
        tb_fechaNac.setText("01/01/2016");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel10)
                .addGap(105, 105, 105))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(20, 20, 20)
                            .addComponent(jLabel5))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(70, 70, 70)
                            .addComponent(jLabel4))))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btn_guardar, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tb_usuario, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tb_pass1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tb_pass2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                    .addComponent(tb_nombre, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tb_apellido, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tb_pais, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tb_ciudad, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(rb_masculino)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(rb_femenino)
                        .addGap(22, 22, 22))
                    .addComponent(tb_fechaNac, javax.swing.GroupLayout.Alignment.LEADING))
                .addGap(52, 52, 52))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(tb_usuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(tb_pass1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(tb_pass2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(tb_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(tb_apellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(tb_fechaNac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(rb_femenino)
                    .addComponent(rb_masculino))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(tb_pais, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(tb_ciudad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_guardar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
    
    }//GEN-LAST:event_formWindowClosed

    private void btn_guardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_guardarActionPerformed
        // Validaciones
        if(validaciones() == true)
        {
            String retorno = "";
            String sexo = "0";
            if(rb_masculino.isSelected() == true)
            {
                sexo = "1";
            }
            
            /*<formato de fecha de nacimiento>*/
            String OLD_FORMAT = "dd/MM/yyyy";
            String NEW_FORMAT = "yyyy/MM/dd";

            // August 12, 2010
            String oldDateString = tb_fechaNac.getText();
            String newDateString = tb_fechaNac.getText();
            
            SimpleDateFormat sdf = new SimpleDateFormat(OLD_FORMAT);
            Date d;
            try {
                d = sdf.parse(oldDateString);
                sdf.applyPattern(NEW_FORMAT);
                newDateString = sdf.format(d);
            } catch (ParseException ex) {
                Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
            }
            /* hasta acá */
            
            /* query final*/
            retorno = bdd.ejecutarQuery("INSERT INTO usuario (id, usuario, password, nombre, "
                    + "apellido, fecha_nacimiento, sexo, ciudad, pais) VALUES (NULL, '"+tb_usuario.getText()+"', "
                    + "'"+tb_pass1.getText()+"', '"+tb_nombre.getText()+"', '"+tb_apellido.getText()+"', "
                    + "'" + newDateString + " 00:00', b'" + sexo + "', '"+tb_ciudad.getText()+"', '"+tb_pais.getText()+"');");

            if (retorno.compareTo("") != 0)
            {
                JOptionPane.showMessageDialog(null, "Error al agregar usuario: "+retorno, "¡Atención!", JOptionPane.ERROR_MESSAGE);
            }
            else
            {
                login();
            }
        }
    }//GEN-LAST:event_btn_guardarActionPerformed

    /**
     * Verifica el usuario asignado previo a abrir el form principal
     */
    private void login()
    {
        conexion bdd = new conexion();
        ResultSet resultado = null;
        
        resultado = bdd.consulta("SELECT * FROM usuario WHERE usuario = '"+tb_usuario.getText() + "'");
        
        int contador = 0;
        int id_usuario = 0;
            
        try{
            while (resultado.next()){
                //id_usuario = Integer.parseInt(resultado.getString("id"));
                id_usuario = resultado.getInt("id");
                contador++;
            }
            resultado.close();
        }catch(SQLException ex)
        {
            System.out.println("SQLException: "+ ex.getMessage());        
        }

        if (contador > 0)
        {               
            // TODO add your handling code here:
            Principal FrmPrincipal= new Principal(id_usuario);//"jFrame2" Tu colocas el nombre que le hayas puesto a tu segundo jFrame 

            FrmPrincipal.setVisible(true); //muestra el segundo jFrame

            this.setVisible(false);//oculta el jFrame que estes usando
        }
        
        bdd.cerrarConexion();
    }
    
    private Boolean validaciones()
    {
        Boolean retornar = false;
        if(tb_usuario.getText().length() == 0)
        {
            JOptionPane.showMessageDialog(null, "Ingresa un usuario", "¡Atención!", JOptionPane.ERROR_MESSAGE);
        }else
        {
            if(tb_pass1.getPassword().length == 0)
            {
                JOptionPane.showMessageDialog(null, "Ingresa una contraseña", "¡Atención!", JOptionPane.ERROR_MESSAGE);
            }else
            {
                if(tb_pass2.getPassword().length == 0)
                {
                    JOptionPane.showMessageDialog(null, "Confirma tu contraseña", "¡Atención!", JOptionPane.ERROR_MESSAGE);
                }else
                {
                    if(tb_nombre.getText().length() == 0)
                    {
                        JOptionPane.showMessageDialog(null, "Ingresa tu nombre", "¡Atención!", JOptionPane.ERROR_MESSAGE);
                    }else
                    {
                        if(tb_apellido.getText().length() == 0)
                        {
                            JOptionPane.showMessageDialog(null, "Ingresa tu apellido", "¡Atención!", JOptionPane.ERROR_MESSAGE);
                        }else
                        {
                            if(tb_fechaNac.getText().length() == 0)
                            {
                                JOptionPane.showMessageDialog(null, "Ingresa tu fecha de nacimiento", "¡Atención!", JOptionPane.ERROR_MESSAGE);
                            }else
                            {
                                if(tb_pais.getText().length() == 0)
                                {
                                    JOptionPane.showMessageDialog(null, "Ingresa tu país de origen", "¡Atención!", JOptionPane.ERROR_MESSAGE);
                                }else
                                {
                                    if(tb_ciudad.getText().length() == 0)
                                    {
                                        JOptionPane.showMessageDialog(null, "Ingresa la ciudad donde vives", "¡Atención!", JOptionPane.ERROR_MESSAGE);
                                    }
                                    else
                                    {
                                        //!(Arrays.equals(createPassword.getPassword(), confirmPassword.getPassword()))
                                        if(!(Arrays.equals(tb_pass1.getPassword(), tb_pass2.getPassword())))
                                        {
                                            JOptionPane.showMessageDialog(null, "Las contraseñas ingresadas no coinciden.", "¡Atención!", JOptionPane.ERROR_MESSAGE);
                                        }
                                        else
                                        {
                                            retornar = true;
                                        }
                                    }
                                }
                            }
                        }                        
                    }
                }
            }
        }
        return retornar;
    }
    
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
            java.util.logging.Logger.getLogger(Usuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Usuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Usuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Usuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Usuario().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_guardar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JRadioButton rb_femenino;
    private javax.swing.JRadioButton rb_masculino;
    private javax.swing.JTextField tb_apellido;
    private javax.swing.JTextField tb_ciudad;
    private javax.swing.JFormattedTextField tb_fechaNac;
    private javax.swing.JTextField tb_nombre;
    private javax.swing.JTextField tb_pais;
    private javax.swing.JPasswordField tb_pass1;
    private javax.swing.JPasswordField tb_pass2;
    private javax.swing.JTextField tb_usuario;
    // End of variables declaration//GEN-END:variables
}

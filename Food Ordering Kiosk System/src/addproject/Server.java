package addproject;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.*;
import java.util.logging.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Server extends UnicastRemoteObject implements NewInterface{
    
    public Server()throws RemoteException
        {
            super();
        }
    
    @Override
    public void login2 (String username, String password) throws RemoteException{
        try{
            int flag =1;
            Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/dcoms","dcoms","dcoms");
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select * from CUS_LOGIN");
            while(rs.next())
            {
                if(rs.getString(4).equals(username) && rs.getString(5).equals(password) )                        
                {                   
                    flag =0;
                    break;
                }
            }               
                if (flag ==0){
                    Kiosk2 info = new Kiosk2();
                    info.setVisible(true);
                }
                else{
                JOptionPane.showMessageDialog(null,"Invalid Username or Password");
                }
            } catch (SQLException ex) {
            Logger.getLogger(Customer_Login.class.getName()).log(Level.SEVERE, null, ex);
        }         
    }

 
    @Override
    public void signup(String f_name, String l_name, String ic_number, String username, String password) throws RemoteException {
        
        try{
            Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/dcoms","dcoms","dcoms");
            Statement st = conn.createStatement();
            String sql = "insert into CUS_LOGIN values('"+f_name+"','"+l_name+"','"+ic_number+"','"+username+"','"+password+"')";
            st.executeUpdate(sql);
            
            if( f_name.isEmpty() || l_name.isEmpty() || ic_number.isEmpty() || username.isEmpty() || password.isEmpty()){
            JOptionPane.showMessageDialog(null, "Enter Values In TextField", "Invalid TextFields", JOptionPane.ERROR_MESSAGE);
            return;//return from the method to allow the user to edit the JTextField
            }
            else {
                JOptionPane.showMessageDialog(null,"SignUp is Successful");
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(Customer_Login.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    
    @Override
    public void ad_signup(String username, String password) throws  RemoteException{
        try{
            
            Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/dcoms","dcoms","dcoms");
            Statement st = conn.createStatement();
            String sql = "insert into AD_LOGIN values('"+username+"','"+password+"')";
            st.executeUpdate(sql);
            if( username.isEmpty() || password.isEmpty()){
            JOptionPane.showMessageDialog(null, "Enter Values In TextField", "Invalid TextFields", JOptionPane.ERROR_MESSAGE);
            return;//return from the method to allow the user to edit the JTextField
            }
            else {
                JOptionPane.showMessageDialog(null,"SignUp is Successful");   
            }
            
        }
        catch (SQLException ex) {
            Logger.getLogger(Customer_Login.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    @Override
    public void ad_login(String username, String password) throws  RemoteException{
        try{
            int flag =1;
            Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/dcoms","dcoms","dcoms");
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select * from AD_LOGIN");
            while(rs.next())
            {
                if(rs.getString(1).equals(username) && rs.getString(2).equals(password) )                        
                {                   
                    flag =0;
                    break;
                }
            }               
                if (flag ==0){
                    Admin_Menu info = new Admin_Menu();
                    info.setVisible(true);
                }
                else{
                JOptionPane.showMessageDialog(null,"Invalid Username or Password");
                }
            } catch (SQLException ex) {
            Logger.getLogger(Customer_Login.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    
    
    
    @Override
    public void add_menu(String name, String price, String id) throws RemoteException {
        
        try{
            
            Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/dcoms","dcoms","dcoms");
            Statement st = conn.createStatement();
            String sql = "insert into MENU values('"+id+"','"+name+"','"+price+"')";
            st.executeUpdate(sql);
            JOptionPane.showMessageDialog(null,"Added Successful"); 
        }
        catch (SQLException ex) {
            Logger.getLogger(Customer_Login.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
   
    
    @Override
    public void del_menu(JTable jTable1) throws  RemoteException{
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        try{
            int SelectedRowIndex = jTable1.getSelectedRow();
            model.removeRow(SelectedRowIndex);
            }catch(Exception ex)
            {
                JOptionPane.showMessageDialog(null, ex);
            }
    }
    
    
    @Override
    public void delete(JButton jButton4) throws  RemoteException{
        try{
            
            Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/dcoms","dcoms","dcoms");
            Statement st = conn.createStatement();
            String sql = "DELETE FROM MENU";
            st.executeUpdate(sql);
            JOptionPane.showMessageDialog(null,"Entire Menu is deleted");
 
        }
        catch (SQLException ex) {
            Logger.getLogger(Customer_Login.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    @Override
    public void delete2(JButton jButton2) throws  RemoteException{
        try{
            
            Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/dcoms","dcoms","dcoms");
            Statement st = conn.createStatement();
            String sql = "DELETE FROM MENU";
            st.executeUpdate(sql);
 
        }
        catch (SQLException ex) {
            Logger.getLogger(Customer_Login.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    
    
    @Override
    public void pl_order(String name, String price) throws  RemoteException{
        
        try{
            
            Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/dcoms","dcoms","dcoms");
            conn.setAutoCommit(false);
            Statement st = conn.createStatement();

            String queryco = "Insert into PL_ORDER(Name,Price) values ('"+name+"','"+price+"')";
            st.executeUpdate(queryco);    
            conn.commit();
            JOptionPane.showMessageDialog(null, "Successfully Saved");
          }
        catch (SQLException ex) {
            Logger.getLogger(Customer_Login.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
    

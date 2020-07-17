package addproject;

import java.rmi.*;
import javax.swing.JButton;
import javax.swing.JTable;

public interface NewInterface extends Remote{
 
   
    public void login2(String username, String password) throws  RemoteException;
    
    
    public void signup(String f_name, String l_name, String ic_number, String username, String password) throws  RemoteException;

    
    public void add_menu(String name, String price, String id) throws  RemoteException;
    

    public void pl_order(String name, String price) throws  RemoteException;


    public void del_menu(JTable jTable1) throws  RemoteException;
    
    
    public void ad_login(String username, String password) throws  RemoteException;

    
    public void ad_signup(String username, String password) throws  RemoteException;

    
    public void delete(JButton jButton4) throws  RemoteException;

    
    public void delete2(JButton jButton2) throws  RemoteException;
  
}

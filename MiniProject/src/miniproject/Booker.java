
package miniproject;

import java.sql.*;
import java.time.Instant;
import java.io.*;



public class Booker {
    public int assignRoom(Customer cust,String suite){
        int rNo = 0;
        try{
            
            Class.forName("com.mysql.jdbc.Driver");
            try(Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotelregister1?autoReconnect=true&useSSL=false","root","jerome")){
            
            PreparedStatement ps = con.prepareStatement("SELECT * FROM hotelregister1.ROOMLIST WHERE SUITE=? AND CAPACITY>=? AND CUSTOMER IS NULL Order by CAPACITY");
            ps.setString(1, suite);
            ps.setInt(2, cust.getNoOfPeople());
            ResultSet rs = ps.executeQuery();
            
            rs.next();
                        
            rNo = rs.getInt(1);
            
            if(rNo == 0){
                System.out.println("No room found");
                return rNo;
            }
            
            System.out.println(rNo);
         
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);

            oos.writeObject(cust);
            oos.flush();
            oos.close();
            bos.close();

            byte[] data = bos.toByteArray();
            
            PreparedStatement pst = con.prepareStatement("UPDATE hotelregister1.ROOMLIST SET CUSTOMER = ? WHERE ROOMNO = ? ");
            pst.setObject(1, data);
            pst.setInt(2, rNo);
            pst.executeUpdate();
            System.out.println("done");
            }
            catch(SQLException e){
                e.printStackTrace();
            }
        }
        catch(ClassNotFoundException e){
            e.printStackTrace();
            return 0;
        }
        catch(Exception e){
            e.printStackTrace();
            return 0;
        }
        
        return rNo;
    }
    
    public boolean addBill(int cID,Service s){
        Validator v = new Validator();
        if(v.cidValidation(cID)){
            try{
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotelregister1?autoReconnect=true&useSSL=false","root","jerome");
                
                PreparedStatement ps = con.prepareStatement("SELECT * FROM HOTELREGISTER1.ROOMLIST WHERE CUSTOMER is not NULL");
                ResultSet rs = ps.executeQuery();
                rs.next();
                
                while(rs.getInt(1) != 0){

                    ByteArrayInputStream bais = new ByteArrayInputStream(rs.getBytes("customer"));
                    ObjectInputStream ins = new ObjectInputStream(bais);
                
                    Customer c = (Customer) ins.readObject();
                    int rno = rs.getInt(1);
                    
                    if(c.getcID() == cID){
                        c.addTransaction(s.getBill());
                        c.displayBills();
                        PreparedStatement pst = con.prepareStatement("UPDATE hotelregister1.ROOMLIST SET CUSTOMER = ? WHERE ROOMNO = ?");
                        pst.setObject(1, c);
                        pst.setInt(2, rno);
                        pst.executeUpdate();
                        return true;
                    }

                    rs.next();
                }
            }
            catch(ClassNotFoundException|SQLException|IOException e){
                e.printStackTrace();
            }
            return false;
        }
        else{
            return false;
        }
    }
    
    public Customer checkOut(int cID){
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotelregister1?autoReconnect=true&useSSL=false","root","jerome");
            
            PreparedStatement ps = con.prepareStatement("SELECT * from hotelregister1.roomlist WHERE CUSTOMER is not NULL");
            ResultSet rs = ps.executeQuery();
            rs.next();
            
            while(rs.getInt(1) != 0){
                //System.out.println("Innn "+rs.getInt(1));
                ByteArrayInputStream bais = new ByteArrayInputStream(rs.getBytes("customer"));
                ObjectInputStream ins = new ObjectInputStream(bais);
                
                Customer c = (Customer) ins.readObject();
                
                if(c.getcID() == cID){
                    
                    PreparedStatement pst = con.prepareStatement("UPDATE hotelregister1.roomlist SET Customer=? WHERE RoomNo=?");
                    pst.setObject(1, null);
                    pst.setInt(2, rs.getInt(1));
                    pst.executeUpdate();
                    
                    return c;
                }
                
                rs.next();
            }
            
        }
        catch(SQLException|ClassNotFoundException|IOException e){
            e.printStackTrace();
        }
        
        return null;    
    }
}


package miniproject;
import java.sql.*;
import java.io.*;
import java.util.regex.*;

public class Validator {
    public void emailValidation(String email) throws emailInvalidException{
        Pattern epat = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = epat.matcher(email);
        if(!matcher.matches()){
            throw new emailInvalidException(matcher.toString());
        }
       
    }
    public void ageValidation(int age) throws ageInvalidException{
        if(age < 16 || age > 130){
            throw new ageInvalidException(age);
        }
    }
    
    public void ageValidation(String a) throws ageInvalidException{
        int age = Integer.parseInt(a);
        if(age < 16 || age > 130){
            throw new ageInvalidException(age);
        }
    }
    
    public void phoneNumberValidation(String ph)throws phoneInvalidExpression{
        if(ph.length() != 10){
            throw new phoneInvalidExpression(ph.length());
        }
        
    }
    
        
    public boolean cidValidation(int cID){
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotelregister1?autoReconnect=true&useSSL=false","root","jerome");
            
            PreparedStatement ps = con.prepareStatement("SELECT * from hotelregister1.roomlist WHERE CUSTOMER is not NULL");
            ResultSet rs = ps.executeQuery();
            rs.next();
            
            while(rs.getInt(1) != 0){
                System.out.println("Innn "+rs.getInt(1));
                ByteArrayInputStream bais = new ByteArrayInputStream(rs.getBytes("customer"));
                ObjectInputStream ins = new ObjectInputStream(bais);
                
                Customer c = (Customer) ins.readObject();
                
                if(c.getcID() == cID){
                    return true;
                }
                
                rs.next();
            }
            
        }
        catch(SQLException|ClassNotFoundException|IOException e){
            e.printStackTrace();
        }
        return false;
    }
}

class emailInvalidException extends Exception{
    public emailInvalidException(String message) {
        super(message);
    }
}
class ageInvalidException extends Exception{
    private String msg;
    public ageInvalidException(int age) {
        if(age < 16){
            msg = "Too young (16+)";
        }
        else if(age > 130){
            msg = "too old ( ≤ 130)";
        }
    }
    public String getMsg() {
        return msg;
    }   
}

class phoneInvalidExpression extends Exception{
    private String msg;
    public phoneInvalidExpression(int len) {
        if(len < 10){
            msg = "Missing "+ (10 - len) + " digit(s)";
        }
        else if(len > 10){
            msg = "Extra " + (len - 10) + " digit(s)";
        }
    }
    public String getMsg() {
        return msg;
    }
    
}
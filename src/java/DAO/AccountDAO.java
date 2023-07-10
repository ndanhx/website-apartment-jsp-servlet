/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import Model.Account;
import Model.BaiDang;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Home
 */
public class AccountDAO {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    //ma hoa md5
    public static String MaHoaMD5(String pass) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] messageDigest = md.digest(pass.getBytes());
        BigInteger big = new BigInteger(1, messageDigest);
        return big.toString(16);
    }    //end ma hóa md5
    
    public int GetTotalAccount() {
        String query = "SELECT count(*) FROM Account";
        try {
            conn = new ConnectDB().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while(rs.next()){
               return rs.getInt(1);
            }
            conn.close();
        } catch (Exception e) {System.out.println(e); }
        return 0;
    }
    
    
    public int DeletedAccountbyID(String username) {
        String query = "delete from Account Where username = ?";
        int row = -1;
        try {
            conn = new ConnectDB().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, username);
            System.out.println("saydsadsa==============" + ps);
            row = ps.executeUpdate();
            conn.close();
            return row;
        } catch (Exception e) {
            System.out.println(e);

        }
        return row;
    }

    //Login
    public Account Login(String username, String password) {
        String query = "SELECT * from Account "
                + "WHERE username = ? AND password= ? ";
        try {
            conn = new ConnectDB().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, password);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new Account(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getInt(6),
                        rs.getInt(7),
                        rs.getInt(8));
            }
        } catch (Exception e) {
        }

        return null;
    }//end login

    public Account CheckAccountIsNull(String username) {
        String query = "SELECT * from Account "
                + "WHERE username = ? ";
        try {
            conn = new ConnectDB().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, username);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new Account(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getInt(6),
                        rs.getInt(7),
                        rs.getInt(8));
            }
        } catch (Exception e) {
        }

        return null;
    }//end login

    //Đăng kí
    public void Signup(String username, String password, String name, String sdt,
            String email, int seller) {
        String query = "INSERT INTO Account VALUES (?, ?, ?, ?, ?, 0, ?, 1)";
        try {
            conn = new ConnectDB().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, password);
            ps.setString(3, name);
            ps.setString(4, sdt);
            ps.setString(5, email);
            ps.setInt(6, seller);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }//end class đăng kí

    public void AddAccountByAdmin(String username, String password, String name,
            String sdt, String email, int sodu, int seller, int role) {
        String query = "INSERT INTO Account VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            conn = new ConnectDB().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, password);
            ps.setString(3, name);
            ps.setString(4, sdt);
            ps.setString(5, email);
            ps.setInt(6, sodu);
            ps.setInt(7, seller);
            ps.setInt(8, role);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }//end class đăng kí

    //check mk cũ
    public Account CheckAccountisValue(String tendangnhap) {
        String query = "SELECT  * FROM Account WHERE username = ?";
        try {
            conn = new ConnectDB().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, tendangnhap);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new Account(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getInt(6),
                        rs.getInt(7),
                        rs.getInt(8));
            }
        } catch (Exception e) {
        }

        return null;
    }

    public List<Account> getAllAccount_DAO(String username) {
        List<Account> listac = new ArrayList<>();
        String query = "SELECT * from Account "
                + "WHERE username = ?";
        try {
            conn = new ConnectDB().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, username);
            rs = ps.executeQuery();
            while (rs.next()) {
                String passWord = rs.getString("password");
                String Name = rs.getString("name");
                String SDT = rs.getString("SDT");
                String Email = rs.getString("Email");
                int soDu = rs.getInt("so_du");
                int Seller = rs.getInt("Seller");
                int role = rs.getInt("role");
                Account ac = new Account(username, passWord, Name, SDT, Email, soDu, Seller, role);
                listac.add(ac);

            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return listac;
    }//end login

    public List<Account> getAllAccount() {
        List<Account> listac = new ArrayList<>();
        String query = "SELECT * from Account ";
        try {
            conn = new ConnectDB().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                String username = rs.getString("username");
                String passWord = rs.getString("password");
                String Name = rs.getString("name");
                String SDT = rs.getString("SDT");
                String Email = rs.getString("Email");
                int soDu = rs.getInt("so_du");
                int Seller = rs.getInt("Seller");
                int role = rs.getInt("role");
                Account ac = new Account(username, passWord, Name, SDT, Email, soDu, Seller, role);
                listac.add(ac);

            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return listac;
    }//end
    public List<Account> getAllAccountChoose10Account(int index) {
        List<Account> listac = new ArrayList<>();
        String query = "SELECT * FROM Account \n" +
                        "order by username\n" +
                        "offset ? rows fetch next 10 rows only ";
        try {
            conn = new ConnectDB().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setInt(1, (index-1)*10);
            rs = ps.executeQuery();
            while (rs.next()) {
                String username = rs.getString("username");
                String passWord = rs.getString("password");
                String Name = rs.getString("name");
                String SDT = rs.getString("SDT");
                String Email = rs.getString("Email");
                int soDu = rs.getInt("so_du");
                int Seller = rs.getInt("Seller");
                int role = rs.getInt("role");
                Account ac = new Account(username, passWord, Name, SDT, Email, soDu, Seller, role);
                listac.add(ac);

            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return listac;
    }//end

    public String getSDT_DAO(String username) {
        String sdt = null;
        try {
            AccountDAO daoac = new AccountDAO();
            List<Account> ac = daoac.getAllAccount_DAO(username);
            for (Account a : ac) {
                sdt = a.getSDT();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return sdt;
    }
    public void UpdateAccount(String username, String name, String sdt,
            String email, int sodu, int seller, int role) {
        String query = "update Account SET name = ? , SDT = ? , Email = ?  , so_du = ? , seller = ?, role = ?\n"
                + "Where username = ?";
        try {
            conn = new ConnectDB().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, name);
            ps.setString(2, sdt);
            ps.setString(3, email);
            ps.setInt(4, sodu);
            ps.setInt(5, seller);
            ps.setInt(6, role);
            ps.setString(7, username);
            ps.executeUpdate();
            conn.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public int NapTienSuccess(String username, int soTien) {
        int check = -1;
        String query = "update Account SET  so_du = so_du +  ? Where username = ?";
        try {
            conn = new ConnectDB().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setInt(1, soTien);
            ps.setString(2, username);
            check=ps.executeUpdate();
            conn.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return check;
    }
    public int UpdateAccountByUserProfile(String username, String name, String sdt, String email) {
        int check = -1;
        String query = "UPDATE Account SET [name] = ? , [SDT] = ? , [Email] = ? WHERE username = ?";
        try {
            conn = new ConnectDB().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, name);
            ps.setString(2, sdt);
            ps.setString(3, email);
            ps.setString(4, username);
            check = ps.executeUpdate();
            conn.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return check;
    }
    public int ChangePasswordByUserProfile(String username, String pass) {
        int check = -1;
        String query = "UPDATE Account SET [password] = ?  WHERE username = ? ";
        try {
            conn = new ConnectDB().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, pass);
            ps.setString(2, username);
            check = ps.executeUpdate();
            conn.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return check;
    }
   

    public int UpdateSoDu_AffterPosting(String username) {
        int check = -1;
        String query = "IF (SELECT so_du FROM Account WHERE username = ? ) >= 10000\n"
                + "BEGIN\n"
                + "    UPDATE Account SET so_du = so_du - 10000  Where username = ?\n"
                + "END\n"
                + "ELSE\n"
                + "BEGIN\n"
                + "     UPDATE Account SET so_du = so_du  Where username = ?\n"
                + "END";
        try {
            conn = new ConnectDB().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, username);
            ps.setString(3, username);
            check = ps.executeUpdate();
            conn.close();
            return check;
        } catch (Exception e) {
            System.out.println(e);
        }
        return check;
    }

    public void UpdateSoDu_AffterPosting_IsFail(String username) {

        String query = "update Account SET  so_du = so_du + 10000  Where username = ?";
        try {
            conn = new ConnectDB().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, username);
            ps.executeUpdate();
            conn.close();
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public Account getAccountByID(String username) {
        String query = "SELECT  * FROM Account WHERE username = ?";
        try {
            conn = new ConnectDB().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, username);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new Account(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getInt(6),
                        rs.getInt(7),
                        rs.getInt(8));
            }
        } catch (Exception e) {
        }

        return null;
    }//end 

    public Account getAccountByUserName(String username) {
        String query = "SELECT  * FROM Account WHERE username = ?";
        try {
            conn = new ConnectDB().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, username);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new Account(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getInt(6),
                        rs.getInt(7),
                        rs.getInt(8));
            }
        } catch (Exception e) {
        }

        return null;
    }//end 

    //main
    public static void main(String[] args) {
       
        AccountDAO dao = new AccountDAO();
        List<Account> ac = dao.getAllAccountChoose10Account(10);
        ac.forEach(account -> {
            System.out.println(account.toString());
        });
      
        
        
        
    }

}  //end class

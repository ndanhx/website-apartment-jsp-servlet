/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Home
 */
public class Account {
    private String userName;
    private String passWord;
    private String Name;
    private String SDT;  
    private String Email;
    private int soDu; 
    private int Seller;
    private int role;

    public Account() {
    }

    public Account(String userName, String passWord, String Name, String SDT,
            String Email, int soDu, int Seller, int role) {
        this.userName = userName;
        this.passWord = passWord;
        this.Name = Name;
        this.SDT = SDT;
        this.Email = Email;
        this.soDu = soDu;
        this.Seller = Seller;
        this.role = role;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public int getSoDu() {
        return soDu;
    }

    public void setSoDu(int soDu) {
        this.soDu = soDu;
    }

    public int getSeller() {
        return Seller;
    }

    public void setSeller(int Seller) {
        this.Seller = Seller;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Account{" + "userName=" + userName + ", passWord=" + passWord + ", Name=" + Name + ", SDT=" + SDT + ", Email=" + Email + ", soDu=" + soDu + ", Seller=" + Seller + ", role=" + role + '}';
    }
    
    
    
}

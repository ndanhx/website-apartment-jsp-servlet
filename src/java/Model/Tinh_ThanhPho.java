/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Home
 */
public class Tinh_ThanhPho {
    private String id_tinhTP;
    private String ten_TinhTP;
    
    

    public Tinh_ThanhPho() {
    }

    public Tinh_ThanhPho(String id_tinhTP, String ten_TinhTP) {
        this.id_tinhTP = id_tinhTP;
        this.ten_TinhTP = ten_TinhTP;
    }

    public String getId_tinhTP() {
        return id_tinhTP;
    }

    public void setId_tinhTP(String id_tinhTP) {
        this.id_tinhTP = id_tinhTP;
    }

    public String getTen_TinhTP() {
        return ten_TinhTP;
    }

    public void setTen_TinhTP(String ten_TinhTP) {
        this.ten_TinhTP = ten_TinhTP;
    }

    @Override
    public String toString() {
        return "Tinh_ThanhPho{" + "id_tinhTP=" + id_tinhTP + ", ten_TinhTP=" + ten_TinhTP + '}';
    }

   

    
    
    
    
}

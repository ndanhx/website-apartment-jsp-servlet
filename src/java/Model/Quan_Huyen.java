/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Home
 */
public class Quan_Huyen {
    private String id_QuanHuyen;
    private String ten_QuanHuyen;
    private String id_TinhTP;

    public Quan_Huyen() {
    }

    public String getId_QuanHuyen() {
        return id_QuanHuyen;
    }

    public void setId_QuanHuyen(String id_QuanHuyen) {
        this.id_QuanHuyen = id_QuanHuyen;
    }

    public String getTen_QuanHuyen() {
        return ten_QuanHuyen;
    }

    public void setTen_QuanHuyen(String ten_QuanHuyen) {
        this.ten_QuanHuyen = ten_QuanHuyen;
    }

    public String getId_TinhTP() {
        return id_TinhTP;
    }

    public void setId_TinhTP(String id_TinhTP) {
        this.id_TinhTP = id_TinhTP;
    }

    public Quan_Huyen(String id_QuanHuyen, String ten_QuanHuyen,
            String id_TinhTP) {
        this.id_QuanHuyen = id_QuanHuyen;
        this.ten_QuanHuyen = ten_QuanHuyen;
        this.id_TinhTP = id_TinhTP;
    }

    @Override
    public String toString() {
        return "Quan_Huyen{" + "id_QuanHuyen=" + id_QuanHuyen + ", ten_QuanHuyen=" + ten_QuanHuyen + ", id_TinhTP=" + id_TinhTP + '}';
    }


   
    
    
}

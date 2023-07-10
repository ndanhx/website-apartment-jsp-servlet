/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Phuong_Xa;
import Model.Quan_Huyen;
import Model.Tinh_ThanhPho;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Home
 */
public class DiaChiDAO {
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    
    public  List<Phuong_Xa> getAllPhuongXa_DAO(){
        List<Phuong_Xa> listpx = new ArrayList<>();
        try {
            String truyvan = "select * from Phuong_Xa";
            conn = new ConnectDB().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(truyvan);
            rs = ps.executeQuery();
            while(rs.next()){
                String id_phuong = rs.getString("ten_phuong");
                String ten_phuong = rs.getString("ten_phuong");
                String id_quan = rs.getString("id_quan");
                Phuong_Xa px = new Phuong_Xa(id_phuong, ten_phuong, id_quan);
                listpx.add(px);
            }
        } catch (Exception e) {
           }
        return listpx;
    }// end lay phuong
    public  List<Quan_Huyen> getAllQuanHuyen_DAO(){
        List<Quan_Huyen> listqh = new ArrayList<>();
        try {
            String truyvan = "select * from Quan_Huyen ";
            conn = new ConnectDB().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(truyvan);
            rs = ps.executeQuery();
            while(rs.next()){
                String id_quan = rs.getString("id_quan");
                String ten_quan = rs.getString("ten_quan");
                String id_thanhpho = rs.getString("id_thanhpho");
                Quan_Huyen px = new Quan_Huyen(id_quan, ten_quan, id_thanhpho);
                listqh.add(px);
            }
        } catch (Exception e) {
            System.out.println(e);
           }
        return listqh;
    }// end lay phuong
    
    
    public  List<Phuong_Xa> getPhuongXa_DAO(String id_phuong){
        List<Phuong_Xa> listpx = new ArrayList<>();
        try {
            String truyvan = "select * from Phuong_Xa where id_phuong = " + id_phuong;
            conn = new ConnectDB().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(truyvan);
            rs = ps.executeQuery();
            while(rs.next()){
                String ten_phuong = rs.getString("ten_phuong");
                String id_quan = rs.getString("id_quan");
                Phuong_Xa px = new Phuong_Xa(id_phuong, ten_phuong, id_quan);
                listpx.add(px);
            }
        } catch (Exception e) {
           }
        return listpx;
    }// end lay phuong
    public  List<Quan_Huyen> getQuanHuyen_DAO(String id_quan){
        List<Quan_Huyen> listqh = new ArrayList<>();
        try {
            String truyvan = "select * from Quan_Huyen where id_quan = " + id_quan;
            conn = new ConnectDB().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(truyvan);
            rs = ps.executeQuery();
            while(rs.next()){
                String ten_quan = rs.getString("ten_quan");
                String id_thanhpho = rs.getString("id_thanhpho");
                Quan_Huyen px = new Quan_Huyen(id_quan, ten_quan, id_thanhpho);
                listqh.add(px);
            }
        } catch (Exception e) {
            System.out.println(e);
           }
        return listqh;
    }// end lay phuong
    public  List<Quan_Huyen> getQuanHuyenByIdTinh_TP(String id_thanhpho){
        List<Quan_Huyen> listqh = new ArrayList<>();
        try {
            String truyvan = "select * from Quan_Huyen where id_thanhpho = " + id_thanhpho;
            conn = new ConnectDB().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(truyvan);
            rs = ps.executeQuery();
            while(rs.next()){
                String id_quan = rs.getString("id_quan");
                String ten_quan = rs.getString("ten_quan");
                Quan_Huyen px = new Quan_Huyen(id_quan, ten_quan, id_thanhpho);
                listqh.add(px);
            }
        } catch (Exception e) {
            System.out.println(e);
           }
        return listqh;
    }// end lay phuong
    public  List<Phuong_Xa> getPhuongXaByIdQuanHuyen(String id_quan){
        List<Phuong_Xa> listpx = new ArrayList<>();
        try {
            String truyvan = "select * from Phuong_Xa where id_quan = " + id_quan;
            conn = new ConnectDB().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(truyvan);
            rs = ps.executeQuery();
            while(rs.next()){
                String ten_phuong = rs.getString("ten_phuong");
                String id_phuong = rs.getString("id_phuong");
                Phuong_Xa px = new Phuong_Xa(id_phuong, ten_phuong,id_quan);
                listpx.add(px);
            }
        } catch (Exception e) {
            System.out.println(e);
           }
        return listpx;
    }// end lay phuong
    public  List<Tinh_ThanhPho> getAllTinhTP_DAO(){
        List<Tinh_ThanhPho> listqh = new ArrayList<>();
        try {
            String truyvan = "select * from Tinh_ThanhPho " ;
            conn = new ConnectDB().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(truyvan);
            rs = ps.executeQuery();
            while(rs.next()){
                  String id_thanhpho = rs.getString("id_thanhpho");
                String ten_thanhpho = rs.getString("ten_thanhpho");
                Tinh_ThanhPho px = new Tinh_ThanhPho(id_thanhpho, ten_thanhpho);
                listqh.add(px);
            }
        } catch (Exception e) {
            System.out.println(e);
           }
        return listqh;
    }// end lay phuong
    
    public  List<Tinh_ThanhPho> getTinhTP_DAO(String id_tp){
        List<Tinh_ThanhPho> listqh = new ArrayList<>();
        try {
            String truyvan = "select * from Tinh_ThanhPho where id_thanhpho = " + id_tp;
            conn = new ConnectDB().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(truyvan);
            rs = ps.executeQuery();
            while(rs.next()){
                String ten_thanhpho = rs.getString("ten_thanhpho");
                Tinh_ThanhPho px = new Tinh_ThanhPho(id_tp, ten_thanhpho);
                listqh.add(px);
            }
        } catch (Exception e) {
            System.out.println("loi tp");
           }
        return listqh;
    }// end lay phuong
    //lấy địa chỉ :(( dài
    public String getDiaChi_DAO(String id_phuong){
        String diachi = null;
        String quan = null, phuong = null, tinh = null;
        DiaChiDAO s = new DiaChiDAO();
        List<Phuong_Xa> a = s.getPhuongXa_DAO(id_phuong);
        for(Phuong_Xa x : a ){ // lấy phường
            List<Quan_Huyen> lq = s.getQuanHuyen_DAO(x.getId_QuanHuyen());
            phuong=x.getTen_PhuongXa();
            for(Quan_Huyen q : lq){ // lấy quận uyện
                List<Tinh_ThanhPho> lttp = s.getTinhTP_DAO(q.getId_TinhTP());
                quan =q.getTen_QuanHuyen();
                for(Tinh_ThanhPho tp : lttp){ //lấy tỉnh
                    tinh = tp.getTen_TinhTP();
                }
           }
        }
        return diachi = phuong +", "+ quan +", "+  tinh;
    }

    
    public static void main(String[] args) {
        DiaChiDAO s = new DiaChiDAO();
        List<Phuong_Xa> diachi = s.getPhuongXaByIdQuanHuyen("13");
        for(Phuong_Xa a : diachi){
            System.out.println(a.toString() );
        }
        System.out.println(diachi.isEmpty() ? "loi" : "có");
         

    }
    
    
    
    
    
}

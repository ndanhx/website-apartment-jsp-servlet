/*
     * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
     * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.HinhAnh;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Home
 */
public class HinhAnhDAO {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public int InsertImage(HinhAnh image) {
        int check = -1;
        String query = "INSERT INTO HinhAnh (ten_anh,duong_dan,id_canho)  VALUES \n"
                + "( ? , ? , ?)";
        try {
            conn = new ConnectDB().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, image.getTenHinhAnh());
            ps.setString(2, image.getDuongDan());
            ps.setString(3, image.getIdCanHo());
            check = ps.executeUpdate();
            return check;

        } catch (Exception e) {
            System.out.println(e);
        }
        return check;
    }//end class add

    public List<HinhAnh> getAllImageByIDCanHo(String idcanho) {
        List<HinhAnh> list = new ArrayList<>();
        String query = "SELECT * FROM HinhAnh WHERE id_canho = ?";
        try {
            conn = new ConnectDB().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, idcanho);
            rs = ps.executeQuery();
            while (rs.next()) {
                String id_anh = rs.getString("id_anh");
                String ten_anh = rs.getString("ten_anh");
                String duong_dan = rs.getString("duong_dan");
                String id_canho = rs.getString("id_canho");
                HinhAnh hinhanh = new HinhAnh(id_anh, ten_anh, duong_dan, id_canho);
                list.add(hinhanh);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    } //end
    public List<HinhAnh> getAllImage() {
        List<HinhAnh> list = new ArrayList<>();
        String query = "SELECT * FROM HinhAnh";
        try {
            conn = new ConnectDB().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                String id_anh = rs.getString("id_anh");
                String ten_anh = rs.getString("ten_anh");
                String duong_dan = rs.getString("duong_dan");
                String id_canho = rs.getString("id_canho");
                HinhAnh hinhanh = new HinhAnh(id_anh, ten_anh, duong_dan, id_canho);
                list.add(hinhanh);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    } //end
    public HinhAnh get1ImageByIDCAnHo(String id_canho) {
        HinhAnh hinhanh = new HinhAnh();
        String query = "select top 1 * from HinhAnh where id_canho = ? ";
        try {
            conn = new ConnectDB().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
             ps.setString(1, id_canho);
            rs = ps.executeQuery();
            while (rs.next()) {
                String id_anh = rs.getString("id_anh");
                String ten_anh = rs.getString("ten_anh");
                String duong_dan = rs.getString("duong_dan");
                 hinhanh = new HinhAnh(id_anh, ten_anh, duong_dan, id_canho);
         
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return hinhanh;
    } //end
    public int CountNumImage(String idcanho){
        int count=-1;
        String query = "select count(*) from HinhAnh where id_canho = ?";
        try {
            conn = new ConnectDB().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
             ps.setString(1, idcanho);
            rs = ps.executeQuery();
            while (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return count;
    }
    public static void main(String[] args) {

        HinhAnhDAO hinhanhdao = new HinhAnhDAO();
        int count = hinhanhdao.CountNumImage("1");
        if(count <= 6){
            System.out.println("da qua hinh anh thêm");
        }else{
            System.out.println(count+" hinh anh");
        }
        

        

    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.CanHo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Home
 */
public class CanHoDAO {

    Connection conn = null;

    PreparedStatement ps = null;

    ResultSet rs = null;

    public List<CanHo> getAllCanHo() {
        List<CanHo> list = new ArrayList<>();
        String query = "select * from CanHo ";
        try {
            conn = new ConnectDB().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                String id_canho = rs.getString("id_canho");
                String ten_canho = rs.getString("ten_canho");
                String so_phong = rs.getString("so_phong");
                String dien_tich = rs.getString("dien_tich");
                String gia_tien = rs.getString("gia_tien");
                String mo_ta = rs.getString("mo_ta");
                int trang_thai_dang = rs.getInt("trang_thai_dang");
                int trang_thai_thue = rs.getInt("trang_thai_thue");
                String hinh_anh = rs.getString("hinh_anh");
                String id_phuong = rs.getString("id_phuong");
                String username = rs.getString("username");

                CanHo ch = new CanHo(id_canho, ten_canho, so_phong, dien_tich, gia_tien, mo_ta, trang_thai_dang, trang_thai_thue, hinh_anh, id_phuong, username);
                list.add(ch);
            }
        } catch (Exception e) {
        }
        return list;
    } //end
    public String SelectUsernameByIDCanHo(String id_canho) {
        String result=null;
      
        String query = "SELECT Account.username FROM Account INNER JOIN CanHo ON CanHo.username = Account.username WHERE CanHo.id_canho = ? ";
        try {
            conn = new ConnectDB().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, id_canho);
            rs = ps.executeQuery();
            while (rs.next()) {
                result = rs.getString("username");
            }
        } catch (Exception e) {
        }
        return result;
    } //end

    public List<CanHo> getAllCanHo_DangTin() {
        List<CanHo> list = new ArrayList<>();
        String query = "select * from CanHo where trang_thai_Dang = 1";
        try {
            conn = new ConnectDB().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                String id_canho = rs.getString("id_canho");
                String ten_canho = rs.getString("ten_canho");
                String so_phong = rs.getString("so_phong");
                String dien_tich = rs.getString("dien_tich");
                String gia_tien = rs.getString("gia_tien");
                String mo_ta = rs.getString("mo_ta");
                int trang_thai_dang = rs.getInt("trang_thai_dang");
                int trang_thai_thue = rs.getInt("trang_thai_thue");
                String hinh_anh = rs.getString("hinh_anh");
                String id_phuong = rs.getString("id_phuong");
                String username = rs.getString("username");

                CanHo ch = new CanHo(id_canho, ten_canho, so_phong, dien_tich, gia_tien, mo_ta, trang_thai_dang, trang_thai_thue, hinh_anh, id_phuong, username);
                list.add(ch);
            }
        } catch (Exception e) {
        }
        return list;
    } //end

    public CanHo getAllCanHoByID(String id) {
        CanHo ch = null;
        String query = "select * from CanHo where id_canho = ?";
        try {
            conn = new ConnectDB().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                String id_canho = rs.getString("id_canho");
                String ten_canho = rs.getString("ten_canho");
                String so_phong = rs.getString("so_phong");
                String dien_tich = rs.getString("dien_tich");
                String gia_tien = rs.getString("gia_tien");
                String mo_ta = rs.getString("mo_ta");
                int trang_thai_dang = rs.getInt("trang_thai_dang");
                int trang_thai_thue = rs.getInt("trang_thai_thue");
                String hinh_anh = rs.getString("hinh_anh");
                String id_phuong = rs.getString("id_phuong");
                String username = rs.getString("username");

                ch = new CanHo(id_canho, ten_canho, so_phong, dien_tich, gia_tien, mo_ta, trang_thai_dang, trang_thai_thue, hinh_anh, id_phuong, username);

            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return ch;
    } //end

    public CanHo getCanHo_ByIDPhuong(String id_canho, String id_phuong) {
        CanHo ch = null;
        String query = "select * from CanHo \n"
                + "inner join Phuong_Xa ON CanHo.id_phuong = Phuong_Xa.id_phuong\n"
                + "where id_canho = ? and Phuong_Xa.id_phuong = ? and trang_thai_Dang =1 ";
        try {
            conn = new ConnectDB().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, id_canho);
            ps.setString(2, id_phuong);
            rs = ps.executeQuery();
            while (rs.next()) {
                String ten_canho = rs.getString("ten_canho");
                String so_phong = rs.getString("so_phong");
                String dien_tich = rs.getString("dien_tich");
                String gia_tien = rs.getString("gia_tien");
                String mo_ta = rs.getString("mo_ta");
                int trang_thai_dang = rs.getInt("trang_thai_dang");
                int trang_thai_thue = rs.getInt("trang_thai_thue");
                String hinh_anh = rs.getString("hinh_anh");
                String username = rs.getString("username");
                ch = new CanHo(id_canho, ten_canho, so_phong, dien_tich, gia_tien, mo_ta, trang_thai_dang, trang_thai_thue, hinh_anh, id_phuong, username);

            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return ch;
    } //end

    public CanHo getCanHo_ByIDTinhTP(String id_canho, String id_tinh) {
        CanHo ch = null;
        String query = "select * from CanHo\n"
                + "inner join Phuong_Xa ON CanHo.id_phuong = Phuong_Xa.id_phuong\n"
                + "INNER JOIN Quan_Huyen ON Quan_Huyen.id_quan = Phuong_Xa.id_quan\n"
                + "INNER JOIN Tinh_ThanhPho ON Tinh_ThanhPho.id_thanhpho = Quan_Huyen.id_thanhpho\n"
                + "where id_canho = ? and Tinh_ThanhPho.id_thanhpho = ?	 and trang_thai_Dang =1 ";
        try {
            conn = new ConnectDB().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, id_canho);
            ps.setString(2, id_tinh);
            rs = ps.executeQuery();
            while (rs.next()) {
                String ten_canho = rs.getString("ten_canho");
                String so_phong = rs.getString("so_phong");
                String dien_tich = rs.getString("dien_tich");
                String gia_tien = rs.getString("gia_tien");
                String mo_ta = rs.getString("mo_ta");
                int trang_thai_dang = rs.getInt("trang_thai_dang");
                int trang_thai_thue = rs.getInt("trang_thai_thue");
                String hinh_anh = rs.getString("hinh_anh");
                String id_phuong = rs.getString("id_phuong");
                String username = rs.getString("username");
                ch = new CanHo(id_canho, ten_canho, so_phong, dien_tich, gia_tien, mo_ta, trang_thai_dang, trang_thai_thue, hinh_anh, id_phuong, username);

            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return ch;
    } //end

    public List<CanHo> getCanHo_ByUsername(String username) {
        List<CanHo> list = new ArrayList<>();
        String query = "select * from canho where username = ?";
        try {
            conn = new ConnectDB().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, username);
            rs = ps.executeQuery();
            while (rs.next()) {
                String id_canho = rs.getString("id_canho");
                String ten_canho = rs.getString("ten_canho");
                String so_phong = rs.getString("so_phong");
                String dien_tich = rs.getString("dien_tich");
                String gia_tien = rs.getString("gia_tien");
                String mo_ta = rs.getString("mo_ta");
                int trang_thai_dang = rs.getInt("trang_thai_dang");
                int trang_thai_thue = rs.getInt("trang_thai_thue");
                String hinh_anh = rs.getString("hinh_anh");
                String id_phuong = rs.getString("id_phuong");
                CanHo ch = new CanHo(id_canho, ten_canho, so_phong, dien_tich, gia_tien, mo_ta, trang_thai_dang, trang_thai_thue, hinh_anh, id_phuong, username);
                list.add(ch);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    } //end

    public CanHo getCanHo_ByIdQuan(String id_canho, String id_quan) {
        CanHo ch = null;
        String query = "select * from CanHo\n"
                + "inner join Phuong_Xa ON CanHo.id_phuong = Phuong_Xa.id_phuong\n"
                + "INNER JOIN Quan_Huyen ON Quan_Huyen.id_quan = Phuong_Xa.id_quan\n"
                + "INNER JOIN Tinh_ThanhPho ON Tinh_ThanhPho.id_thanhpho = Quan_Huyen.id_thanhpho\n"
                + "where id_canho = ? and Quan_Huyen.id_quan = ? and trang_thai_Dang =1 ";
        try {
            conn = new ConnectDB().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, id_canho);
            ps.setString(2, id_quan);
            rs = ps.executeQuery();
            while (rs.next()) {
                String ten_canho = rs.getString("ten_canho");
                String so_phong = rs.getString("so_phong");
                String dien_tich = rs.getString("dien_tich");
                String gia_tien = rs.getString("gia_tien");
                String mo_ta = rs.getString("mo_ta");
                int trang_thai_dang = rs.getInt("trang_thai_dang");
                int trang_thai_thue = rs.getInt("trang_thai_thue");
                String hinh_anh = rs.getString("hinh_anh");
                String id_phuong = rs.getString("id_phuong");
                String username = rs.getString("username");
                ch = new CanHo(id_canho, ten_canho, so_phong, dien_tich, gia_tien, mo_ta, trang_thai_dang, trang_thai_thue, hinh_anh, id_phuong, username);

            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return ch;
    } //end

    public int AddApartment(String ten_canho, String so_phong, String dien_tich,
            String gia_tien, String mo_ta, int trang_thai_Thue, String id_phuong,
            String username) {
        int check = -1;
        String query = "insert into CanHo (ten_canho, so_phong, dien_tich, gia_tien, mo_ta, trang_thai_Dang, trang_thai_Thue, hinh_anh, id_phuong, username) values"
                + "( ? , ? , ? , ? ,? , 0 , ? ,? , ? ,?)";
        try {
            conn = new ConnectDB().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, ten_canho);
            ps.setString(2, so_phong);
            ps.setString(3, dien_tich);
            ps.setString(4, gia_tien);
            ps.setString(5, mo_ta);
            ps.setInt(6, trang_thai_Thue);
            ps.setString(7, "");
            ps.setString(8, id_phuong);
            ps.setString(9, username);
            check = ps.executeUpdate();
            System.out.println(ps);
            conn.close();
            return check;

        } catch (Exception e) {
            System.out.println(e);
        }
        return check;
    }//end class add apartment

    public int UpdateApartment(String ten_canho, String so_phong,
            String dien_tich,
            String gia_tien, String mo_ta, int trang_thai_Thue, String id_phuong,
            String id_canho) {
        int check = -1;
        String query = "UPDATE CanHo \n"
                + "SET ten_canho = ?, so_phong = ?, dien_tich = ?, gia_tien = ?, mo_ta = ?, trang_thai_Thue = ?, id_phuong = ?\n"
                + "where id_canho = ?";
        try {
            conn = new ConnectDB().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, ten_canho);
            ps.setString(2, so_phong);
            ps.setString(3, dien_tich);
            ps.setString(4, gia_tien);
            ps.setString(5, mo_ta);
            ps.setInt(6, trang_thai_Thue);
            ps.setString(7, id_phuong);
            ps.setString(8, id_canho);
            check = ps.executeUpdate();
            conn.close();
            return check;

        } catch (Exception e) {
            System.out.println(e);
        }
        return check;
    }//end class add apartment

    public int UpdateTrangThaiDang_Apartment(String id_canho) {
        int check = -1;
        String query = "Update CanHo SET trang_thai_Dang = 2\n"
                + "where id_canho = ?";
        try {
            conn = new ConnectDB().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, id_canho);
            check = ps.executeUpdate();
            conn.close();
            return check;

        } catch (Exception e) {
            System.out.println(e);
        }
        return check;
    }//end class add apartment

    public int AcceptTrangThaiDang_Apartment(String id_canho) {
        int check = -1;
        String query = "Update CanHo SET trang_thai_Dang = 1 \n"
                + "where id_canho = ?";
        try {
            conn = new ConnectDB().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, id_canho);
            check = ps.executeUpdate();
            conn.close();
            return check;

        } catch (Exception e) {
            System.out.println(e);
        }
        return check;
    }//end class add apartment

    public int HuyTrangThaiDang_Apartment(String id_canho) {
        int check = -1;
        String query = "Update CanHo SET trang_thai_Dang = 0 where id_canho = ? ";
        try {
            conn = new ConnectDB().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, id_canho);
            check = ps.executeUpdate();
            conn.close();
            return check;

        } catch (Exception e) {
            System.out.println(e);
        }
        return check;
    }//end class add apartment

    public int DeletedApartbyID(String username) {
        String query = "DELETE from CanHo Where id_canho = ?";
        int row = -1;
        try {
            conn = new ConnectDB().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, username);
            row = ps.executeUpdate();
            conn.close();
            return row;
        } catch (Exception e) {
            System.out.println(e);

        }
        return row;
    }

    public static void main(String[] args) {
        CanHoDAO dao = new CanHoDAO();
        String a =  dao.SelectUsernameByIDCanHo("1");
        System.out.println(a);
    }

}

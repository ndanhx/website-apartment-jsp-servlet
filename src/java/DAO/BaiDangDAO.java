/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import Model.BaiDang;
import Model.Tinh_ThanhPho;

/**
 *
 * @author Home
 */
public class BaiDangDAO {

    public static List<Tinh_ThanhPho> listTP = new ArrayList<>();
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public List<BaiDang> getBaiDangBy_StausPosted(int staus) {
        List<BaiDang> list = new ArrayList<>();
        String query = "select * from BaiDang WHERE trang_thai = ?  "; //1 đã được duyetj đăng bài
        try {
            conn = new ConnectDB().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);

            ps.setInt(1, staus);
            rs = ps.executeQuery();
            while (rs.next()) {
                String id_baidang = rs.getString("id_baidang");
                String tieu_de = rs.getString("tieu_de");
                String ngay_dang = rs.getString("ngay_dang");
                int trangthai = rs.getInt("trang_thai");
                String id_canho = rs.getString("id_canho");
                BaiDang baidang = new BaiDang(id_baidang, tieu_de, ngay_dang, trangthai, id_canho);
                list.add(baidang);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }//eend getall

    public List<BaiDang> ThongKeBaiDangTheoNgayvaTrangThai(String startDate,
            String endDate, int status) {
        List<BaiDang> list = new ArrayList<>();
        String query = "SELECT * FROM BaiDang WHERE \n"
                + "ngay_dang BETWEEN CONVERT(date, ? , 103) AND \n"
                + "CONVERT(date, ?, 103) AND \n"
                + "trang_thai = ? ";
        try {
            conn = new ConnectDB().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, startDate);
            ps.setString(2, endDate);
            ps.setInt(3, status);
            rs = ps.executeQuery();
            while (rs.next()) {
                String id_baidang = rs.getString("id_baidang");
                String tieu_de = rs.getString("tieu_de");
                String ngay_dang = rs.getString("ngay_dang");
                int trangthai = rs.getInt("trang_thai");
                String id_canho = rs.getString("id_canho");
                BaiDang baidang = new BaiDang(id_baidang, tieu_de, ngay_dang, trangthai, id_canho);
                list.add(baidang);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }//eend getall

    public List<BaiDang> ThongKeBaiDangTheoNgayvaTrangThai_GETALL(
            String startDate, String endDate) {
        List<BaiDang> list = new ArrayList<>();
        String query = "SELECT * FROM BaiDang WHERE \n"
                + "ngay_dang BETWEEN CONVERT(date, ? , 103) AND \n"
                + "CONVERT(date, ?, 103)  \n";
        try {
            conn = new ConnectDB().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, startDate);
            ps.setString(2, endDate);
            rs = ps.executeQuery();
            while (rs.next()) {
                String id_baidang = rs.getString("id_baidang");
                String tieu_de = rs.getString("tieu_de");
                String ngay_dang = rs.getString("ngay_dang");
                int trangthai = rs.getInt("trang_thai");
                String id_canho = rs.getString("id_canho");
                BaiDang baidang = new BaiDang(id_baidang, tieu_de, ngay_dang, trangthai, id_canho);
                list.add(baidang);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }//eend getall

    public List<BaiDang> getAll_BaiDang() {
        List<BaiDang> list = new ArrayList<>();
        String query = "select * from BaiDang WHERE trang_thai = 1  "; //1 đã được duyetj đăng bài
        try {
            conn = new ConnectDB().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);

            rs = ps.executeQuery();
            while (rs.next()) {
                String id_baidang = rs.getString("id_baidang");
                String tieu_de = rs.getString("tieu_de");
                String ngay_dang = rs.getString("ngay_dang");
                int trangthai = rs.getInt("trang_thai");
                String id_canho = rs.getString("id_canho");
                BaiDang baidang = new BaiDang(id_baidang, tieu_de, ngay_dang, trangthai, id_canho);
                list.add(baidang);
            }
        } catch (Exception e) {
        }
        return list;
    }//eend getall

    public List<BaiDang> getAll_BaiDangManager() {
        List<BaiDang> list = new ArrayList<>();
        String query = "select * from BaiDang "; //1 đã được duyetj đăng bài
        try {
            conn = new ConnectDB().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);

            rs = ps.executeQuery();
            while (rs.next()) {
                String id_baidang = rs.getString("id_baidang");
                String tieu_de = rs.getString("tieu_de");
                String ngay_dang = rs.getString("ngay_dang");
                int trangthai = rs.getInt("trang_thai");
                String id_canho = rs.getString("id_canho");
                BaiDang baidang = new BaiDang(id_baidang, tieu_de, ngay_dang, trangthai, id_canho);
                list.add(baidang);
            }
        } catch (Exception e) {
        }
        return list;
    }//eend getall

    public List<BaiDang> getAll_BaiDangForSeller(String username) {
        List<BaiDang> list = new ArrayList<>();
        String query = "select * from baidang\n"
                + "inner join canho on canho.id_canho = baidang.id_canho\n"
                + "where username = ? "; //1 đã được duyetj đăng bài
        try {
            conn = new ConnectDB().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, username);
            rs = ps.executeQuery();
            while (rs.next()) {
                String id_baidang = rs.getString("id_baidang");
                String tieu_de = rs.getString("tieu_de");
                String ngay_dang = rs.getString("ngay_dang");
                int trangthai = rs.getInt("trang_thai");
                String id_canho = rs.getString("id_canho");
                BaiDang baidang = new BaiDang(id_baidang, tieu_de, ngay_dang, trangthai, id_canho);
                list.add(baidang);
            }
        } catch (Exception e) {
        }
        return list;
    }//eend getall

    public int getCountForListing(String id_baidang) {
        int count = 0;
        String query = "SELECT COUNT(id_canho) FROM BaiDang WHERE id_canho = ?  AND trang_thai = 1"; //1 đã được duyetj đăng bài
        try {
            conn = new ConnectDB().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, id_baidang);
            rs = ps.executeQuery();
            while (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return count;
    }//eend getall

    public int getCountForListing_Pending(String id_baidang) {
        int count = 0;
        String query = "SELECT COUNT(id_canho) FROM BaiDang WHERE id_canho = ?  AND trang_thai = 0"; //1 đã được duyetj đăng bài
        try {
            conn = new ConnectDB().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, id_baidang);
            rs = ps.executeQuery();
            while (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return count;
    }//eend getall

    public BaiDang getBaiDang_ByID(String id_baidang) {
        BaiDang baidang = null;
        String query = "select * from BaiDang where id_baidang= ? and  trang_thai = 1  "; //1 đã được duyetj đăng bài
        try {
            conn = new ConnectDB().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, id_baidang);
            rs = ps.executeQuery();
            while (rs.next()) {
                String tieu_de = rs.getString("tieu_de");
                String ngay_dang = rs.getString("ngay_dang");
                String id_canho = rs.getString("id_canho");
                baidang = new BaiDang(id_baidang, tieu_de, ngay_dang, id_canho);
            }
        } catch (Exception e) {
        }
        return baidang;
    }//eend getall

    public void CheckListDate_isPost() { //kiểm tra sau 10 ngày bài sẽ được gỡ xuống
        int check = 0;
        String query = "IF EXISTS (SELECT * FROM BaiDang WHERE trang_thai = 1 AND  DATEADD(day, 10,  ngay_dang) < GETDATE())\n"
                + "BEGIN\n"
                + "     DELETE from BaiDang where DATEADD(day, 10, ngay_dang) < GETDATE() \n"
                + "END\n"
                + "ELSE\n"
                + "BEGIN\n"
                + "	 PRINT '1'\n"
                + "END  "; //1 đã được duyetj đăng bài
        try {
            conn = new ConnectDB().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            check = ps.executeUpdate();
            if(check < 0){
                System.out.println("====>>> Co " + 0 + " Bai dang da het han dang bai");
            }else{
                System.out.println("====>>> Co " + check + " Bai dang da het han dang bai");

            }
            
        } catch (Exception e) {
            System.out.println("===>>>> KHONG CO BAI DANG NAO TREN 10 NGAY");
        }
    }//eend getall

    public List<BaiDang> getNext3CanHo(int soluongload) {
        List<BaiDang> list = new ArrayList<>();
        String query = "select  * from BaiDang WHERE trang_thai = 1  \n"
                + "ORDER BY id_baidang OFFSET ? ROW FETCH NEXT 3 ROW ONLY"; //1 đã được duyetj đăng bài
        try {
            conn = new ConnectDB().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setInt(1, soluongload);
            rs = ps.executeQuery();
            while (rs.next()) {
                String id_baidang = rs.getString("id_baidang");
                String tieu_de = rs.getString("tieu_de");
                String ngay_dang = rs.getString("ngay_dang");
                String id_canho = rs.getString("id_canho");
                BaiDang baidang = new BaiDang(id_baidang, tieu_de, ngay_dang, id_canho);
                list.add(baidang);
            }
        } catch (Exception e) {
        }
        return list;
    }//eend getall

    public List<BaiDang> getTop3BaiDang_test() {
        List<BaiDang> list = new ArrayList<>();
        String query = "select TOP 3 * from BaiDang WHERE trang_thai = 1 "; //1 đã được duyetj đăng bài
        try {
            conn = new ConnectDB().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                String id_baidang = rs.getString("id_baidang");
                String tieu_de = rs.getString("tieu_de");
                String ngay_dang = rs.getString("ngay_dang");
                String id_canho = rs.getString("id_canho");
                BaiDang baidang = new BaiDang(id_baidang, tieu_de, ngay_dang, id_canho);
                list.add(baidang);
            }
        } catch (Exception e) {
        }
        return list;
    }//eend getall

    public List<BaiDang> timKiemBaiDangByName(String txtSearch) {
        List<BaiDang> list = new ArrayList<>();
        String query = "select * from BaiDang  where tieu_de like ? and trang_thai = 1 ";
        try {
            conn = new ConnectDB().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, "%" + txtSearch + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                String id_baidang = rs.getString("id_baidang");
                String tieu_de = rs.getString("tieu_de");
                String ngay_dang = rs.getString("ngay_dang");
                String id_canho = rs.getString("id_canho");
                BaiDang baidang = new BaiDang(id_baidang, tieu_de, ngay_dang, id_canho);
                list.add(baidang);

            }
        } catch (Exception e) {
        }
        return list;
    }

    public List<BaiDang> getAllBaiDangByAddress(String id_phuong) {
        List<BaiDang> list = new ArrayList<>();
        String query = "select * from BaiDang inner join CanHo on CanHo.id_canho = BaiDang.id_canho where CanHo.id_phuong= ? ";
        try {
            conn = new ConnectDB().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, id_phuong);
            rs = ps.executeQuery();
            while (rs.next()) {
                String id_baidang = rs.getString("id_baidang");
                String tieu_de = rs.getString("tieu_de");
                String ngay_dang = rs.getString("ngay_dang");
                String id_canho = rs.getString("id_canho");
                BaiDang baidang = new BaiDang(id_baidang, tieu_de, ngay_dang, id_canho);
                list.add(baidang);

            }
        } catch (Exception e) {
        }
        return list;
    }

    public List<BaiDang> getAllBaiDangByUserName(String id_phuong) {
        List<BaiDang> list = new ArrayList<>();
        String query = "SELECT * FROM BaiDang\n"
                + "inner join CanHo on CanHo.id_canho = BaiDang.id_canho\n"
                + "where CanHo.username= ? ";
        try {
            conn = new ConnectDB().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, id_phuong);
            rs = ps.executeQuery();
            while (rs.next()) {
                String id_baidang = rs.getString("id_baidang");
                String tieu_de = rs.getString("tieu_de");
                String ngay_dang = rs.getString("ngay_dang");
                String id_canho = rs.getString("id_canho");
                BaiDang baidang = new BaiDang(id_baidang, tieu_de, ngay_dang, id_canho);
                list.add(baidang);
            }
        } catch (Exception e) {
        }
        return list;
    }

    public String SelectIDCanHoByIDBaiDang(String idbaidang) {
        String check = "-1";
        String query = "select CanHo.id_canho from CanHo \n"
                + "INNER JOIN BaiDang ON CanHo.id_canho = BaiDang.id_canho\n"
                + "WHERE BaiDang.id_baidang = ?";
        try {
            conn = new ConnectDB().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, idbaidang);
            rs = ps.executeQuery();
            while (rs.next()) {
                check = rs.getString(1);
            }
        } catch (Exception e) {
        }
        return check;
    }

    //
    //end function
    public int AddListing(String tieu_de, String id_canho) {
        int check = -1;
        String query = "insert into BaiDang (tieu_de ,ngay_dang,trang_thai ,id_canho) values\n"
                + "(? , GETDATE() , 0 , ?)";
        try {
            conn = new ConnectDB().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, tieu_de);
            ps.setString(2, id_canho);
            check = ps.executeUpdate();
            return check;

        } catch (Exception e) {
            System.out.println(e);
        }
        return check;
    }//end class add

    public int DeleteTrangThaiKhiDoiTrangThaiThue(String idcanho) {
        int check = -1;

        String query = "delete from BaiDang"
                + " Where id_canho = ?";
        try {
            conn = new ConnectDB().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, idcanho);
            check = ps.executeUpdate();

        } catch (Exception e) {
            System.out.println(e);
        }
        return check;
    }//end class add

    public int Accept_Listing(String idbaidang) {
        int check = -1;
        String query = "Update BaiDang SET trang_thai = 1, ngay_dang = GETDATE() where id_baidang = ?  ";
        try {
            conn = new ConnectDB().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, idbaidang);
            check = ps.executeUpdate();
            return check;

        } catch (Exception e) {
            System.out.println(e);
        }
        return check;
    }//end class add

    public int EditListing(String tieude, String idbaidang) {
        int check = -1;
        String query = "Update BaiDang SET tieu_de = ? where id_baidang = ? ";
        try {
            conn = new ConnectDB().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, tieude);
            ps.setString(2, idbaidang);
            check = ps.executeUpdate();
            return check;

        } catch (Exception e) {
            System.out.println(e);
        }
        return check;
    }//end class add

    public int DeleteBaiDangbyID(String id) {
        int check = -1;
        String query = "delete from BaiDang"
                + " Where id_baidang = ?";
        try {
            conn = new ConnectDB().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, id);
            check = ps.executeUpdate();
            conn.close();
            return check;
        } catch (Exception e) {
            System.out.println(e);
        }
        return check;
    }

    public static void main(String[] args) {
        BaiDangDAO dao = new BaiDangDAO();
//        List<BaiDang> das = dao.ThongKeBaiDangTheoNgayvaTrangThai("23/4/2023", "23/4/2023", 1);
//        das.forEach(baidang -> {
//            System.out.println(baidang.toString());
//        });

        String check = dao.SelectIDCanHoByIDBaiDang("1");
        System.out.println(check);

    }

    private void DeleteCanHobyID(int i) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}

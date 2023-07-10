/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Home
 */
public class PhieuThanhToan {
    private String idPhieuTT;
    private String ngayTT;
    private String phuongThucTTT;
    private String tongTien;
    private String moTa;
    private String trangThai;
    private String userName;

    public PhieuThanhToan() {
    }

    public PhieuThanhToan(String idPhieuTT, String ngayTT, String phuongThucTTT,
            String tongTien, String moTa, String trangThai, String userName) {
        this.idPhieuTT = idPhieuTT;
        this.ngayTT = ngayTT;
        this.phuongThucTTT = phuongThucTTT;
        this.tongTien = tongTien;
        this.moTa = moTa;
        this.trangThai = trangThai;
        this.userName = userName;
    }

    public void setIdPhieuTT(String idPhieuTT) {
        this.idPhieuTT = idPhieuTT;
    }

    public void setNgayTT(String ngayTT) {
        this.ngayTT = ngayTT;
    }

    public void setPhuongThucTTT(String phuongThucTTT) {
        this.phuongThucTTT = phuongThucTTT;
    }

    public void setTongTien(String tongTien) {
        this.tongTien = tongTien;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "PhieuThanhToan{" + "idPhieuTT=" + idPhieuTT + ", ngayTT=" + ngayTT + ", phuongThucTTT=" + phuongThucTTT + ", tongTien=" + tongTien + ", moTa=" + moTa + ", trangThai=" + trangThai + ", userName=" + userName + '}';
    }
    
    
}

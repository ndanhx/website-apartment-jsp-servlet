/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Home
 */
public class CanHo {
    private String idCanHo;
    private String tenCanHo;
    private String soPhong;
    private String dienTich;
    private String giaTien;
    private String moTa;
    private int trangThaiDangTin;
    private int trangThaiTHUE;
    private String hinhAnh;
    private String idPhuong;
    private String username;

    public CanHo() {
    }

    public CanHo(String idCanHo, String tenCanHo, String soPhong,
            String dienTich, String giaTien, String moTa,
            int trangThaiDangTin, int trangThaiTHUE, String hinhAnh,
            String idPhuong, String username) {
        this.idCanHo = idCanHo;
        this.tenCanHo = tenCanHo;
        this.soPhong = soPhong;
        this.dienTich = dienTich;
        this.giaTien = giaTien;
        this.moTa = moTa;
        this.trangThaiDangTin = trangThaiDangTin;
        this.trangThaiTHUE = trangThaiTHUE;
        this.hinhAnh = hinhAnh;
        this.idPhuong = idPhuong;
        this.username = username;
    }

    public String getIdCanHo() {
        return idCanHo;
    }

    public void setIdCanHo(String idCanHo) {
        this.idCanHo = idCanHo;
    }

    public String getTenCanHo() {
        return tenCanHo;
    }

    public void setTenCanHo(String tenCanHo) {
        this.tenCanHo = tenCanHo;
    }

    public String getSoPhong() {
        return soPhong;
    }

    public void setSoPhong(String soPhong) {
        this.soPhong = soPhong;
    }

    public String getDienTich() {
        return dienTich;
    }

    public void setDienTich(String dienTich) {
        this.dienTich = dienTich;
    }

    public String getGiaTien() {
        return giaTien;
    }

    public void setGiaTien(String giaTien) {
        this.giaTien = giaTien;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public int getTrangThaiDangTin() {
        return trangThaiDangTin;
    }

    public void setTrangThaiDangTin(int trangThaiDangTin) {
        this.trangThaiDangTin = trangThaiDangTin;
    }

    public int getTrangThaiTHUE() {
        return trangThaiTHUE;
    }

    public void setTrangThaiTHUE(int trangThaiTHUE) {
        this.trangThaiTHUE = trangThaiTHUE;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public String getIdPhuong() {
        return idPhuong;
    }

    public void setIdPhuong(String idPhuong) {
        this.idPhuong = idPhuong;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "CanHo{" + "idCanHo=" + idCanHo + ", tenCanHo=" + tenCanHo + ", soPhong=" + soPhong + ", dienTich=" + dienTich + ", giaTien=" + giaTien + ", moTa=" + moTa + ", trangThaiDangTin=" + trangThaiDangTin + ", trangThaiTHUE=" + trangThaiTHUE + ", hinhAnh=" + hinhAnh + ", idPhuong=" + idPhuong + ", username=" + username + '}';
    }
    
    
}

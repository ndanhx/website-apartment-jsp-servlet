/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Home
 */
public class BaiDang {
    private String idBaidang;
    
    private String tenTieuDe;
    
    private String ngayDangBai;
    
    
    private int trangThai;
    
    private String idCanHo;

    public BaiDang() {
    }

    public BaiDang(String idBaidang, String tenTieuDe, String ngayDangBai,
            String idCanHo) {
        this.idBaidang = idBaidang;
        this.tenTieuDe = tenTieuDe;
        this.ngayDangBai = ngayDangBai;
        this.idCanHo = idCanHo;
    }

    public BaiDang(String idBaidang, String tenTieuDe, String ngayDangBai,
            int trangThai, String idCanHo) {
        this.idBaidang = idBaidang;
        this.tenTieuDe = tenTieuDe;
        this.ngayDangBai = ngayDangBai;
        this.trangThai = trangThai;
        this.idCanHo = idCanHo;
    }
    

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    
    
    public String getIdBaidang() {
        return idBaidang;
    }

    public void setIdBaidang(String idBaidang) {
        this.idBaidang = idBaidang;
    }

    public String getTenTieuDe() {
        return tenTieuDe;
    }

    public void setTenTieuDe(String tenTieuDe) {
        this.tenTieuDe = tenTieuDe;
    }

    public String getNgayDangBai() {
        return ngayDangBai;
    }

    public void setNgayDangBai(String ngayDangBai) {
        this.ngayDangBai = ngayDangBai;
    }

    public String getIdCanHo() {
        return idCanHo;
    }

    public void setIdCanHo(String idCanHo) {
        this.idCanHo = idCanHo;
    }

    @Override
    public String toString() {
        return "BaiDang{" + "idBaidang=" + idBaidang + ", tenTieuDe=" + tenTieuDe + ", ngayDangBai=" + ngayDangBai + ", trangThai=" + trangThai + ", idCanHo=" + idCanHo + '}';
    }

    

    
    
    
    
    
    
    
    
}

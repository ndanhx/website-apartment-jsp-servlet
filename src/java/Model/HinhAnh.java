/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Home
 */
public class HinhAnh {

   private String idHinhAnh;
   private String tenHinhAnh;
   private String duongDan;
   private String idCanHo;

    public HinhAnh() {
    }

    public HinhAnh(String idHinhAnh, String tenHinhAnh, String duongDan,
            String idCanHo) {
        this.idHinhAnh = idHinhAnh;
        this.tenHinhAnh = tenHinhAnh;
        this.duongDan = duongDan;
        this.idCanHo = idCanHo;
    }

    @Override
    public String toString() {
        return "HinhAnh{" + "idHinhAnh=" + idHinhAnh + ", tenHinhAnh=" + tenHinhAnh + ", duongDan=" + duongDan + ", idCanHo=" + idCanHo + '}';
    }

    public String getIdHinhAnh() {
        return idHinhAnh;
    }

    public void setIdHinhAnh(String idHinhAnh) {
        this.idHinhAnh = idHinhAnh;
    }

    public String getTenHinhAnh() {
        return tenHinhAnh;
    }

    public void setTenHinhAnh(String tenHinhAnh) {
        this.tenHinhAnh = tenHinhAnh;
    }

    public String getDuongDan() {
        return duongDan;
    }

    public void setDuongDan(String duongDan) {
        this.duongDan = duongDan;
    }

    public String getIdCanHo() {
        return idCanHo;
    }

    public void setIdCanHo(String idCanHo) {
        this.idCanHo = idCanHo;
    }

    
   
   
   
   
   
}

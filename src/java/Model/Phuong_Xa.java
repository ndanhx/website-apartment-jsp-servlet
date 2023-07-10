
package Model;

/**
 *
 * @author Home
 */
public class Phuong_Xa {
    private String id_PhuongXa;
    private String ten_PhuongXa;
    private String id_QuanHuyen;

    public Phuong_Xa() {
    }

    public Phuong_Xa(String id_PhuongXa, String ten_PhuongXa,
            String id_QuanHuyen) {
        this.id_PhuongXa = id_PhuongXa;
        this.ten_PhuongXa = ten_PhuongXa;
        this.id_QuanHuyen = id_QuanHuyen;
    }
    

    public String getId_PhuongXa() {
        return id_PhuongXa;
    }

    public void setId_PhuongXa(String id_PhuongXa) {
        this.id_PhuongXa = id_PhuongXa;
    }

    public String getTen_PhuongXa() {
        return ten_PhuongXa;
    }

    public void setTen_PhuongXa(String ten_PhuongXa) {
        this.ten_PhuongXa = ten_PhuongXa;
    }

    public String getId_QuanHuyen() {
        return id_QuanHuyen;
    }

    public void setId_QuanHuyen(String id_QuanHuyen) {
        this.id_QuanHuyen = id_QuanHuyen;
    }

    @Override
    public String toString() {
        return "Phuong_Xa{" + "id_PhuongXa=" + id_PhuongXa + ", ten_PhuongXa=" + ten_PhuongXa + ", id_QuanHuyen=" + id_QuanHuyen + '}';
    }
    
    
}

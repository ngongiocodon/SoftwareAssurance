package com.hung.daos;

import com.hung.entities.MucDongBHXH;
import com.hung.entities.NguoiDongBHXH;
import org.testng.Assert;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class MucDongBHXH_DAO {
    private ResultSet rs;
    private PreparedStatement ps;
    private Connection conn;

    public MucDongBHXH_DAO() {
        conn = Config.getConnection();
    }

//    public MucDongBHXH getMucDongBHXH(String ID) {
//        MucDongBHXH m = null;
//        String sql = "select * from MucDong where mucDongID = ?";
//        try {
//            ps = conn.prepareStatement(sql);
//            ps.setString(1, ID);
//            ResultSet rs = ps.executeQuery();
//
//            while (rs.next()) {
//                String maapdung = rs.getString(1);//ld  trong nuoc, nuoc ngoai, tu nguyen
//                String mota = rs.getString(2);
//                Double huutri_tutuat = rs.getDouble(3);
//                Double omdau_thaisan = rs.getDouble(4);
//                Double tainanLD_nghenghiep = rs.getDouble(5);//"tainanLD_nghenghiep");
//                Double yte = rs.getDouble(6);//"yte");
//                String ngaybatdau = rs.getString(7);//"ngaybatdau");
//                String trangthai = rs.getString(8);//"trangthai");
//                m = new MucDongBHXH(maapdung, mota, huutri_tutuat,
//                        omdau_thaisan, tainanLD_nghenghiep, yte, ngaybatdau, trangthai);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return m;
//    }

    public boolean insert(MucDongBHXH md) {
        boolean check = false;
        String sql = "insert into MucDongBHXH values(?,?,?,?,?,?,?,?)";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, md.getMamucdong());
            ps.setString(2, md.getMota());
            ps.setDouble(3, md.getHuutri_tutuat());
            ps.setDouble(4, md.getOmdau_thaisan());
            ps.setDouble(5, md.getTainanLD_nghenghiep());
            ps.setDouble(6, md.getYte());
            ps.setString(7, md.getNgaybatdau());
            ps.setString(8, md.getTrangthai());

            check = ps.execute();
            Assert.assertEquals(true,check);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return check;
    }

    public ArrayList<NguoiDongBHXH> getList() {
        ArrayList<NguoiDongBHXH> list = new ArrayList<>();
        String sql = "select * from NguoiDongBHXH";
        try {
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            boolean check = false;
            ResultSet rs2 = rs;
            Assert.assertEquals(true,rs2.next());

            while (rs.next()) {
                NguoiDongBHXH nguoiDongBHXH = new NguoiDongBHXH(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getDouble(9));

                list.add(nguoiDongBHXH);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void disableMucDong(MucDongBHXH md) {
        String sql = "alter MucDongBHXH set trangthai = ? where maapdung = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, "disable");
            ps.setString(2, md.getMamucdong());

            int check = ps.executeUpdate();
            Assert.assertEquals(1,check);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

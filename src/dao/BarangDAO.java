package dao;

import java.sql.*;
import java.util.ArrayList;
import model.Barang;

public class BarangDAO {
    Connection conn;
    public BarangDAO() {
        conn = Koneksi.getKoneksi();
    }

    public boolean simpan(Barang b) {
        try {
            String sql = "INSERT INTO barang VALUES (?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, b.getKodeBarang());
            ps.setString(2, b.getNamaBarang());
            ps.setString(3, b.getKategori());
            ps.setInt(4, b.getStok());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Gagal simpan: " + e.getMessage());
            return false;
        }
    }

    public ArrayList<Barang> getAll() {
        ArrayList<Barang> list = new ArrayList<>();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM barang");
            while (rs.next()) {
                list.add(new Barang(rs.getString("kode_barang"), rs.getString("nama_barang"), rs.getString("kategori"), rs.getInt("stok")));
            }
        } catch (SQLException e) {}
        return list;
    }

    public boolean ubah(Barang b) {
        try {
            String sql = "UPDATE barang SET nama_barang=?, kategori=?, stok=? WHERE kode_barang=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, b.getNamaBarang());
            ps.setString(2, b.getKategori());
            ps.setInt(3, b.getStok());
            ps.setString(4, b.getKodeBarang());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) { return false; }
    }

    public boolean hapus(String kode) {
        try {
            String sql = "DELETE FROM barang WHERE kode_barang=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, kode);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) { return false; }
    }
}
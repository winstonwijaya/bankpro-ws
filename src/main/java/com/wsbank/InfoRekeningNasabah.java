package com.wsbank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
/**
 * Web Service to get all information about a customer in bank.
 */
@WebService(serviceName = "getDataNasabah")
@SOAPBinding(
    style = SOAPBinding.Style.DOCUMENT, use = SOAPBinding.Use.LITERAL)

public class InfoRekeningNasabah {

    /**
     * Default constructor of class InfoRekeningNasabah.
     */
    public InfoRekeningNasabah() {

    }

    /**
     * Web Method to get all information about a bank customer.
     *
     * @return Information about a bank customer
     *
     * @param noRekening Account number of customer
     */
    @WebMethod
    public DataNasabah getDataRekeningNasabah(@WebParam(
        name = "noRekening") final String noRekening) {

        // Mengambil listTransaksi seorang pengguna
        ArrayList<Transaksi> listTransaksi = new ArrayList<>();
        String pemilikRekening = "";
        int saldo = -1;

        String query1 = "SELECT id_nasabah, jenis_transaksi, "
                + "jumlah_transaksi, rekening_tujuan, no_virtual_account, "
                + "waktu_transaksi FROM transaksi WHERE id_nasabah = "
                + noRekening;
        System.out.println(query1);
        // Mengambil nama pemilik rekening dan saldo dari rekening
        String query2 = "SELECT pemilik_rekening, saldo"
        + " FROM nasabah WHERE no_rekening = " + noRekening;
        System.out.println(query2);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://127.0.0.1/wsbank?autoReconnect=true&"
                    + "useSSL=false&useUnicode=true&"
                    + "useJDBCCompliantTimezoneShift=true&"
                    + "useLegacyDatetimeCode=false&serverTimezone=UTC",
                    "root", "password");
            PreparedStatement preparedStatement = conn.prepareStatement(
                query1);

            ResultSet resultSet = preparedStatement.executeQuery();

            PreparedStatement preparedStatement2 = conn.prepareStatement(
                query2);

            ResultSet resultSet2 = preparedStatement2.executeQuery();
            if (conn != null) {
                System.out.println("Connected to the database!");

                while (resultSet.next()) {
                    Transaksi ts = new Transaksi();
                    ts.setIdnasabah(resultSet.getString("id_nasabah"));
                    ts.setJenistransaksi(resultSet.getString(
                        "jenis_transaksi"));
                    ts.setJumlahtransaksi(resultSet.getInt(
                        "jumlah_transaksi"));
                    ts.setrekeningtujuan(resultSet.getString(
                        "rekening_tujuan"));
                    ts.setNovirtualaccount(resultSet.getString(
                        "no_virtual_account"));
                    ts.setWaktutransaksi(resultSet.getTimestamp(
                        "waktu_transaksi"));
                    listTransaksi.add(ts);
                }
                while (resultSet2.next()) {
                    pemilikRekening = resultSet2.getString(
                        "pemilik_rekening");
                    saldo = resultSet2.getInt("saldo");
                }

            }
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(),
            e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        Transaksi[] transaksi = listTransaksi.toArray(
            new Transaksi[listTransaksi.size()]);
        DataNasabah dataRekNasabah = new DataNasabah(
            transaksi, pemilikRekening, noRekening, saldo);

        return dataRekNasabah;
    }
}

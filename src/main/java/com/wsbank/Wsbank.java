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
// import java.sql.*;
// import com.google.protobuf.Timestamp;
/**
 * WSBank is a service to check a transaction history of customer.
 */
@WebService(serviceName = "WSBank")
@SOAPBinding(
    style = SOAPBinding.Style.DOCUMENT, use = SOAPBinding.Use.LITERAL)
public class Wsbank {

    /**
     * Default constructor of Wsbank.
     */
    public Wsbank() {

    }

    /**
     * getTransaksi is a web method to get transaction list of a customer.
     *
     * @return list of transaction from specific user
     *
     * @param id account number of customer.
     */
    @WebMethod
    public Transaksi[] getTransaksi(@WebParam(
        name = "noRekening") final String id) {
        ArrayList<Transaksi> listTransaksi = new ArrayList<>();

        String query = "SELECT id_nasabah, jenis_transaksi, "
                + "jumlah_transaksi, rekening_tujuan, no_virtual_account, "
                + "waktu_transaksi FROM transaksi WHERE id_nasabah = " + id;
        System.out.println(query);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://127.0.0.1/wsbank?autoReconnect=true&"
                    + "useSSL=false&useUnicode=true&"
                    + "useJDBCCompliantTimezoneShift=true&"
                    + "useLegacyDatetimeCode=false&serverTimezone=UTC",
                    "root", "password");
            PreparedStatement preparedStatement = conn.prepareStatement(
                query);

            ResultSet resultSet = preparedStatement.executeQuery();
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
            }
        } catch (

        SQLException e) {
            System.err.format("SQL State: %s\n%s",
            e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        Transaksi[] transaksi = listTransaksi.toArray(
            new Transaksi[listTransaksi.size()]);
        return transaksi;
    }
}

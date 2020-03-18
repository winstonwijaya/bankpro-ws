package com.wsbank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

/**
 * Web Service to to check credit transaction in a range of time.
 */
@WebService(serviceName = "CekKredit")
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT,
use = SOAPBinding.Use.LITERAL)
public class CekKredit {

    /**
     * Default constructor of class CekKredit.
     */
    public CekKredit() {

    }
    /**
     * cekTransaksiKreditRekening is a web method to check
     * whether there are credit transaction(s) in specific time invterval.
     *
     * @return boolean whether transaction(s) exist or not
     *
     * @param noRekening Source account of transaction
     * @param nominal ammount of transaction
     * @param dari start of time interval
     * @param sampai end of time interval
     */
    @WebMethod
    public Boolean cekTransaksiKreditRekening(@WebParam(
        name = "noRekening") final String noRekening,
            @WebParam(name = "nominal") final int nominal, @WebParam(
                name = "dari") final String dari,
            @WebParam(name = "sampai") final String sampai) {

        String query = "SELECT count(waktu_transaksi) as hasil"
        + " FROM transaksi WHERE id_nasabah  = '" + noRekening
        + "' AND jenis_transaksi = 'Kredit' AND jumlah_transaksi = '"
        + nominal + "' AND waktu_transaksi >= '"
        + dari + "' AND waktu_transaksi <= '" + sampai + "'";
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

                int total = 0;
                while (resultSet.next()) {
                    total = resultSet.getInt("hasil");
                    System.out.println(total);
                }
                return (total > 0);
            }
        } catch (

        SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(),
            e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * cekTransaksiKreditVA is a web method to check
     * whether there are transaction(s) done from a virtual account.
     *
     * @return boolean whether transaction(s) exist or not
     *
     * @param virtualAcc virtual account of customer
     * @param nominal amount of transaction
     * @param dari start of time interval
     * @param sampai end of time interval
     */
    @WebMethod
    public Boolean cekTransaksiKreditVA(@WebParam(
        name = "virtualAcc") final String virtualAcc,
            @WebParam(name = "nominal") final int nominal, @WebParam(
                name = "dari") final String dari,
            @WebParam(name = "sampai") final String sampai) {

        String query = "SELECT count(waktu_transaksi) as hasil"
        + " FROM transaksi WHERE no_virtual_account  = '"
        + virtualAcc + "' AND jenis_transaksi = 'Kredit'"
        + " AND jumlah_transaksi = '" + nominal
        + "' AND waktu_transaksi >= '" + dari + "' AND waktu_transaksi <= '"
        + sampai + "'";
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

                int total = 0;
                while (resultSet.next()) {
                    total = resultSet.getInt("hasil");
                    System.out.println(total);
                }
                return (total > 0);
            }
        } catch (

        SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(),
            e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}

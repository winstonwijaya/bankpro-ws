package com.wsbank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
// import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
/**
 * Web service to transfer from one of the option below.
 * Between two accounts or from account to virtual account.
 *
 */
@WebService(serviceName = "Transfer")
@SOAPBinding(
    style = SOAPBinding.Style.DOCUMENT, use = SOAPBinding.Use.LITERAL)

public class Transfer {

    /**
     * Default constructor of class Transfer.
     */
    public Transfer() {

    }

    /**
     * Web method to transfer between accounts.
     *
     * @return boolean to check whether tranfer success or not
     *
     * @param noRekeningPengirim source account of transfer
     * @param noRekeningPenerima destination account of transfer
     * @param jmlTransfer ammount of transfer
     */
    @WebMethod
    public Boolean transferRekening(@WebParam(
        name = "noRekeningPengirim") final String noRekeningPengirim,
            @WebParam(
                name = "noRekeningPenerima") final String noRekeningPenerima,
            @WebParam(name = "jmlTransfer") final int jmlTransfer) {

        // Mengetahui jumlah saldo pengirim
        String query1 = "SELECT saldo FROM nasabah WHERE no_rekening = '"
        + noRekeningPengirim + "'";
        System.out.println(query1);

        // Mengurangi saldo pengirim
        String query2 = "UPDATE nasabah " + "SET saldo = nasabah.saldo - "
        + jmlTransfer + " WHERE no_rekening = '"
        + noRekeningPengirim + "'";
        System.out.println(query2);

        // Menambah saldo penerima
        String query3 = "UPDATE nasabah " + "SET saldo = nasabah.saldo + "
        + jmlTransfer + " WHERE no_rekening = '"
        + noRekeningPenerima + "'";
        System.out.println(query3);

        // Menambah list transaksi kredit pengirim
        String query4 = "INSERT INTO transaksi( id_nasabah, waktu_transaksi, "
        + "jenis_transaksi, jumlah_transaksi, "
        + "rekening_tujuan, no_virtual_account) " + "VALUES( '"
        + noRekeningPengirim + "', CURRENT_TIMESTAMP, "
        + "\'Debit\', " + jmlTransfer + ", '" + noRekeningPenerima + "' ,'')";
        System.out.println(query4);

        // Menambah list transaksi debit penerima
        String query5 = "INSERT INTO transaksi( id_nasabah, waktu_transaksi, "
        + "jenis_transaksi, jumlah_transaksi, "
        + "rekening_tujuan, no_virtual_account) " + "VALUES( '"
        + noRekeningPenerima + "', CURRENT_TIMESTAMP, "
        + "\'Kredit\', " + jmlTransfer + ", '" + noRekeningPengirim
        + "' ,'')";
        System.out.println(query5);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://127.0.0.1/wsbank?autoReconnect=true&useSSL"
                    + "=false&useUnicode=true&useJDBCCompliantTimezoneShift="
                    + "true&useLegacyDatetimeCode=false&serverTimezone=UTC",
                    "root", "password");
            PreparedStatement preparedStatement = conn.prepareStatement(
                query1);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (conn != null) {
                System.out.println("Connected to the database!");

                int totalSaldo = 0;
                while (resultSet.next()) {
                    totalSaldo = resultSet.getInt("saldo");
                    System.out.println("totalSaldo " + totalSaldo);
                }

                // validasi apakah saldo pengirim cukup untuk transfer
                if (totalSaldo < jmlTransfer) {
                    return false;
                } else {
                    // update saldo pengirim
                    preparedStatement = conn.prepareStatement(query2);
                    int result1 = preparedStatement.executeUpdate();
                    System.out.println("result1 " + result1);

                    // update saldo penerima
                    preparedStatement = conn.prepareStatement(query3);
                    int result2 = preparedStatement.executeUpdate();
                    System.out.println("result2 " + result2);

                    if (result1 == 1 && result2 == 1) {
                        // Menambah list transaksi kredit pengirim
                        preparedStatement = conn.prepareStatement(query4);
                        int result3 = preparedStatement.executeUpdate();
                        System.out.println("result3 " + result3);

                        // Menambah list transaksi debit penerima
                        preparedStatement = conn.prepareStatement(query5);
                        int result4 = preparedStatement.executeUpdate();
                        System.out.println("result4 " + result4);

                        if (result3 == 1 && result4 == 1) {
                            return true;
                        }
                    }

                }
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
     * Web method to transfer from account to virtual account.
     *
     * @return boolean to check whether tranfer success or not
     *
     * @param noRekeningPengirim source account of transfer
     * @param noVirtualAccount destination virtual account of transfer
     * @param jmlTransfer ammount of transfer
     */
    @WebMethod
    public Boolean transferVA(@WebParam(
        name = "noRekeningPengirim") final String noRekeningPengirim,
            @WebParam(name = "noVirtualAccount") final String noVirtualAccount,
            @WebParam(name = "jmlTransfer") final int jmlTransfer) {

        // Mengecek nomor rekening pengguna dari nomor virtual account
        String query = "SELECT no_rekening FROM virtual_account WHERE"
        + " no_virtual_account = '" + noVirtualAccount + "'";
        System.out.println(query);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://127.0.0.1/wsbank?autoReconnect="
                    + "true&useSSL=false&useUnicode=true&"
                    + "useJDBCCompliantTimezoneShift=true&"
                    + "useLegacyDatetimeCode=false&serverTimezone=UTC",
                    "root", "password");
            PreparedStatement preparedStatement = conn.prepareStatement(
                query);
            if (conn != null) {
                System.out.println("Connected to the database!");

                String noRekeningPenerima = "";
                ResultSet noRek = preparedStatement.executeQuery();
                while (noRek.next()) {
                    noRekeningPenerima = noRek.getString("no_rekening");
                    System.out.println("noRekeningPenerima = "
                    + noRekeningPenerima);
                }

                if (noRekeningPenerima != "") {

                    // Mengetahui jumlah saldo pengirim
                    String query1 = "SELECT saldo FROM nasabah "
                    + "WHERE no_rekening = '" + noRekeningPengirim + "'";
                    System.out.println(query1);

                    // Mengurangi saldo pengirim
                    String query2 = "UPDATE nasabah "
                    + "SET saldo = nasabah.saldo - " + jmlTransfer
                    + " WHERE no_rekening = '"
                    + noRekeningPengirim + "'";
                    System.out.println(query2);

                    // Menambah saldo penerima
                    String query3 = "UPDATE nasabah "
                    + "SET saldo = nasabah.saldo + " + jmlTransfer
                            + " WHERE no_rekening = '" + noRekeningPenerima
                            + "'";
                    System.out.println(query3);

                    // Menambah list transaksi kredit pengirim
                    String query4 = "INSERT INTO transaksi("
                        + "id_nasabah, waktu_transaksi, "
                        + "jenis_transaksi, jumlah_transaksi, "
                        + "rekening_tujuan, no_virtual_account) "
                        + "VALUES( '" + noRekeningPengirim
                        + "', CURRENT_TIMESTAMP, " + "\'Debit\', "
                        + jmlTransfer + ", '" + noRekeningPenerima
                        + "' ,'" + noVirtualAccount + "')";
                    System.out.println(query4);

                    // Menambah list transaksi debit penerima
                    String query5 = "INSERT INTO transaksi("
                        + "id_nasabah, waktu_transaksi, "
                        + "jenis_transaksi, jumlah_transaksi, "
                        + "rekening_tujuan, no_virtual_account) "
                        + "VALUES( " + noRekeningPenerima
                        + ", CURRENT_TIMESTAMP, " + "\'Kredit\', "
                        + jmlTransfer + ", '" + noRekeningPengirim
                        + "' ,'" + noVirtualAccount + "')";
                    System.out.println(query5);

                    preparedStatement = conn.prepareStatement(query1);
                    ResultSet resultSet = preparedStatement.executeQuery();

                    int totalSaldo = 0;
                    while (resultSet.next()) {
                        totalSaldo = resultSet.getInt("saldo");
                        System.out.println("totalSaldo " + totalSaldo);
                    }

                    // validasi apakah saldo pengirim cukup untuk transfer
                    if (totalSaldo < jmlTransfer) {
                        return false;
                    } else {
                        // update saldo pengirim
                        preparedStatement = conn.prepareStatement(query2);
                        int result1 = preparedStatement.executeUpdate();
                        System.out.println("result1 " + result1);

                        // update saldo penerima
                        preparedStatement = conn.prepareStatement(query3);
                        int result2 = preparedStatement.executeUpdate();
                        System.out.println("result2 " + result2);

                        if (result1 == 1 && result2 == 1) {
                            // Menambah list transaksi kredit pengirim
                            preparedStatement = conn.prepareStatement(query4);
                            int result3 = preparedStatement.executeUpdate();
                            System.out.println("result3 " + result3);

                            // Menambah list transaksi debit penerima
                            preparedStatement = conn.prepareStatement(query5);
                            int result4 = preparedStatement.executeUpdate();
                            System.out.println("result4 " + result4);

                            if (result3 == 1 && result4 == 1) {
                                return true;
                            }
                        }
                    }

                }
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

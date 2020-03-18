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
 * Web Service to validate an account number.
 */
@WebService(serviceName = "ValidasiRek")
@SOAPBinding(
    style = SOAPBinding.Style.DOCUMENT, use = SOAPBinding.Use.LITERAL)
public class ValidasiRek {
    /**
     * Default constructor of clas ValidasiRek.
     */
    public ValidasiRek() {

    }

    /**
     * Web Method to validate an account number.
     *
     * @return boolean that shows whether an account number is valid
     *
     * @param noRekening account number to validate
     */
    @WebMethod
    public Boolean isRekeningValid(@WebParam(
        name = "noRekening") final String noRekening) {
        ArrayList<String> listRekening = new ArrayList<>();

        String query = "SELECT no_rekening FROM nasabah";
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
                    String rek = resultSet.getString("no_rekening");
                    System.out.println(rek);
                    listRekening.add(rek);
                }
            }
        } catch (

        SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(),
            e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return (listRekening.contains(noRekening));
    }
}

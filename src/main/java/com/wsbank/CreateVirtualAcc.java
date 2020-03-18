package com.wsbank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
/**
 * Web Service to crate a virtual account.
 */
@WebService(serviceName = "CreateVirtualAcc")
@SOAPBinding(
    style = SOAPBinding.Style.DOCUMENT, use = SOAPBinding.Use.LITERAL)
public class CreateVirtualAcc {

    /**
     * Default construction of class CreateVirtualAcc.
     */
    public CreateVirtualAcc() {

    }

    /**
     * Web method to generate a virtual account.
     *
     * @return generated virtual account number
     *
     * @param noRekening account number to associate with virtual account
     */
    @WebMethod
    public String generateVA(@WebParam(
        name = "noRekening") final String noRekening) {
        String virtualAcc = Long.toString(System.currentTimeMillis());

        String query = "INSERT INTO virtual_account VALUES ('" + noRekening
        + "', '" + virtualAcc + "')";
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
            int result = preparedStatement.executeUpdate();

            System.out.println("result = " + result);
            if (conn != null) {
                if (result == 1) {
                    return virtualAcc;
                }
            }
        } catch (

        SQLException e) {
            System.err.format(
                "SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "-1";

    }
}

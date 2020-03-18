package com.wsbank;
// import java.sql.Time;
import java.sql.Timestamp;
// import java.util.Date;
// import java.text.SimpleDateFormat;
// import com.google.protobuf.Timestamp;
/**
 * Transaksi is a class that contain information about a transaction.
 *
 * @author Winston Wijaya
 */
public class Transaksi {
    /**
     * Total of transaction.
     */
    private int jumlahtransaksi;
    /**
     * Account of sender customer.
     */
    private String idnasabah;
    /**
     * Type of transaction.
     */
    private String jenistransaksi;
    /**
     * Destination account.
     */
    private String rekeningtujuan;
    /**
     * Virtual account of destination account.
     */
    private String novirtualaccount;
    /**
     * Time of transaction.
     */
    private Timestamp waktutransaksi;

    /**
     * Default constructor of class Transaksi.
     */
    public Transaksi() {
        this.idnasabah = "";
        this.jumlahtransaksi = 0;
        this.jenistransaksi = "";
        this.rekeningtujuan = "";
        this.novirtualaccount = "";
        this.waktutransaksi = new Timestamp(0);
    }

    /**
     * Parameterized constructor of class Transaksi.
     *
     * @param idnasabah1 Source account
     * @param jumlahtransaksi1 Ammount of transaction
     * @param jenistransaksi1 Type of transaction
     * @param rekeningtujuan1 Destination account of transaction
     * @param novirtualaccount1 Virtual account of destination account
     * @param waktutransaksi1 Time of transaction
     */
    public Transaksi(final String idnasabah1, final int jumlahtransaksi1,
    final String jenistransaksi1, final String rekeningtujuan1,
    final String novirtualaccount1, final Timestamp waktutransaksi1) {
        this.idnasabah = idnasabah1;
        this.jumlahtransaksi = jumlahtransaksi1;
        this.jenistransaksi = jenistransaksi1;
        this.rekeningtujuan = rekeningtujuan1;
        this.novirtualaccount = novirtualaccount1;
        this.waktutransaksi = waktutransaksi1;
    }

    /**
     * method to get account number of customer.
     *
     * @return customer account
     */
    public String getIdnasabah() {
        return idnasabah;
    }

    /**
     * method to set an account number.
     *
     * @param idnasabah1 new account number
     */
    public void setIdnasabah(final String idnasabah1) {
        this.idnasabah = idnasabah1;
    }

    /**
     * method to get ammount of transaction.
     *
     * @return ammount of transaction
     */
    public int getJumlahtransaksi() {
        return jumlahtransaksi;
    }

    /**
     * method to set ammount of transaction.
     *
     * @param jumlahtransaksi1 ammount of transaction
     */
    public void setJumlahtransaksi(final int jumlahtransaksi1) {
        this.jumlahtransaksi = jumlahtransaksi1;
    }

    /**
     * method to get type of transaction.
     *
     * @return type of transaction
     */
    public String getJenistransaksi() {
        return jenistransaksi;
    }

    /**
     * method to set type of transaction.
     *
     * @param jenistransaksi1 type of transaction
     */
    public void setJenistransaksi(final String jenistransaksi1) {
        this.jenistransaksi = jenistransaksi1;
    }

    /**
     * method to get destination account.
     *
     * @return destination account number
     */
    public String getrekeningtujuan() {
        return rekeningtujuan;
    }

    /**
     * method to set destination account.
     *
     * @param rekeningtujuan1 destination account number
     */
    public void setrekeningtujuan(final String rekeningtujuan1) {
        this.rekeningtujuan = rekeningtujuan1;
    }

    /**
     * method to get destination virtual account.
     *
     * @return destination virtual account number
     */
    public String getNovirtualaccount() {
        return novirtualaccount;
    }

    /**
     * method to set destination virtual account number.
     *
     * @param novirtualaccount1 destination virtual account number
     */
    public void setNovirtualaccount(final String novirtualaccount1) {
        this.novirtualaccount = novirtualaccount1;
    }

    /**
     * method to get time of transaction.
     *
     * @return time of transaction
     */
    public Timestamp getWaktutransaksi() {
        return waktutransaksi;
    }

    /**
     * method to set time of transaction.
     *
     * @param waktutransaksi1 time of transaction
     */
    public void setWaktutransaksi(final Timestamp waktutransaksi1) {
        this.waktutransaksi = waktutransaksi1;
    }

}

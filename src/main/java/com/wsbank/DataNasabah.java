/**
    This package support additional information for package-info.java.
 */
package com.wsbank;
/**
 * DataNasabah is a class that contain information about customers.
 *
 * @author Winston Wijaya
 */
public class DataNasabah {
    /**
     * Array of transactions.
     */
    private Transaksi[] transaksi;
    /**
     * Name of customer.
     */
    private String namaNasabah;
    /**
     * Account number.
     */
    private String nomorRekening;
    /**
     * Ballance of customer.
     */
    private int saldo;

    /**
     *
     * Parameterized constructor of class DataNasabah.
     *
     * @param transaksi1 Transaction list of a customer.
     * @param namaNasabah1 Name of customer.
     * @param nomorRekening1 Number of account.
     * @param saldo1 Balance of customer.
     */
    public DataNasabah(
        final Transaksi[] transaksi1, final String namaNasabah1,
    final String nomorRekening1, final int saldo1) {
        this.transaksi = transaksi1;
        this.namaNasabah = namaNasabah1;
        this.nomorRekening = nomorRekening1;
        this.saldo = saldo1;
    }

    /**
     * Get transaction list of a customer.
     *
     * @return transaction list of customer
     */
    public Transaksi[] getTransaksi() {
        return transaksi;
    }

    /**
     * Set transaction list of a customer.
     *
     * @param transaksi1 list of customer's transaction
     */
    public void setTransaksi(final Transaksi[] transaksi1) {
        this.transaksi = transaksi1;
    }

    /**
     * Get the name of customer.
     *
     * @return name of customer
     */
    public String getNamaNasabah() {
        return namaNasabah;
    }

    /**
     * Set the name of customer.
     *
     * @param namaNasabah1 the name of new customer.
     */
    public void setNamaNasabah(final String namaNasabah1) {
        this.namaNasabah = namaNasabah1;
    }

    /**
     * Get the account number of customer.
     *
     * @return customer's account
     */
    public String getNomorRekening() {
        return nomorRekening;
    }

    /**
     * Set the account number of customer.
     *
     * @param nomorRekening1 the new account number of customer
     */
    public void setNomorRekening(final String nomorRekening1) {
        this.nomorRekening = nomorRekening1;
    }

    /**
     * Get the ballance of customer.
     *
     * @return ballance of customer
     */
    public int getSaldo() {
        return saldo;
    }

    /**
     * Set the ballamce of customer.
     *
     * @param saldo1 the new balance of customer
     */
    public void setSaldo(final int saldo1) {
        this.saldo = saldo1;
    }
}

# Web Service - Transaksi
Web service Bank di atas java servlet menggunakan JAX-WS dengan protokol SOAP. Web service ini digunakan untuk Aplikasi Bank Pro dan Engima.

## Fitur
- IP Address API : http://52.201.232.142:8080/ws-bank-1.0/wsbank

| **Link API**                                                          | **Keterangan**                                                                            |
|-----------------------------------------------------------------------|-------------------------------------------------------------------------------------------|
| http://52.201.232.142:8080/ws-bank-1.0/wsbank/validasiRek?wsdl        | Validasi nomor rekening. Jika nomor rekening terdaftar di basis data, maka nomor tersebut valid. |
| http://52.201.232.142:8080/ws-bank-1.0/wsbank/getDataNasabah?wsdl     | Memberikan data rekening seorang nasabah. Data pengguna meliputi nama pengguna, nomor rekening, saldo terakhir, dan riwayat transaksi (debit dan kredit). |
| http://52.201.232.142:8080/ws-bank-1.0/wsbank/transfer?wsdl           | Melakukan transaksi transfer dengan input nomor rekening pengirim, nomor rekening/akun virtual penerima, dan jumlah uang yang ditransfer. Layanan mengembalikan status transfer (berhasil/gagal).|
| http://52.201.232.142:8080/ws-bank-1.0/wsbank/createVirtualAcc?wsdl   | Membuat akun virtual untuk suatu nomor rekening. Layanan mengembalikan nomor unik akun virtual tersebut. |
| http://52.201.232.142:8080/ws-bank-1.0/wsbank/cekKredit?wsdl          | Mengecek ada atau tidak sebuah transaksi kredit dalam suatu rentang waktu. Input yang diterima adalah nomor rekening atau akun virtual tujuan, jumlah nominal yang diharapkan, dan rentang waktu (start datetime dan end datetime) |

## Basis Data

### Nasabah
| **Nama**         | **Jenis** | **Panjang** | **Keterangan**        |
|------------------|-----------|-------------|-----------------------|
| no_rekening      | varchar   | 16          | username Engima       | 
| pemilik_rekening | varchar   | 50          | nomor virtual account |
| saldo            | int       | 255         | id film               |

### Transaksi
| **Nama**           | **Jenis** | **Panjang** | **Keterangan**                 |
|--------------------|-----------|-------------|--------------------------------|
| id_nasabah         | varchar   | 16          | nomor rekening pengirim        | 
| waktu_transaksi    | timestamp | 255         | waktu transaksi                |
| jenis_transaksi    | varchar   | 255         | jenis transaksi (Kredit/Debit) |
| jumlah_transaksi   | datetime  | -           | nominal yang ditransfer        |
| rekening_tujuan    | int       | 3           | nomor rekening penerima        |
| no_virtual_account | datetime  | -           | nomor vitual account           |

### Virtual_account
| **Nama**        | **Jenis** | **Panjang** | **Keterangan**        |
|-----------------|-----------|-------------|-----------------------|
| no_rekening     | varchar   | 16          | nomor rekening        | 
| virtual_account | varchar   | 16          | nomor virtual account |

## Pembagian DPPL
- CI : 13517018, 13517066
- CD : 13517066
- Eksplorasi dan setup mesin deployment : 13517045, 13517066

## Author
- 13517018 - Winston Wijaya
- 13517045 - Suhailie
- 13517066 - Willy Santoso
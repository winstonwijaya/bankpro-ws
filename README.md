# Web Service - Transaksi
Web service Bank di atas java servlet menggunakan JAX-WS dengan protokol SOAP. Web service ini digunakan untuk Aplikasi Bank Pro dan Engima.

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

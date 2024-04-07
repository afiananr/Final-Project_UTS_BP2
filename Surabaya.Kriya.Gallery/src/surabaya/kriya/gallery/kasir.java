package surabaya.kriya.gallery;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Scanner;
import java.util.Arrays;
import java.util.Comparator;
import javax.swing.JOptionPane;

public class kasir {
    static Scanner sc = new Scanner(System.in);
    static cPakaian p[] = new cPakaian[70];
    static cSouvenir sv[] = new cSouvenir[70];
    static cPelanggan m[]= new cPelanggan [100];
    static cTransaksi tr[] = new cTransaksi[100];
    static cLogin loggedin = new cLogin();
    static int pilih = 0, pilih2 = 0, pilih3 = 0, i, totalPendapatan = 0;
    static double rekapNonMember = 0;        
    
    public static void main(String[] args) {           
        // init produk
        p[0] = new cPakaian ("p01","kain batik",20000,56, "L");
        p[1] = new cPakaian ("p02","kemeja",14000,60, "M");
        p[2] = new cPakaian ("p03","dress",130000,32, "XL");
        sv[0] = new cSouvenir ("sv01","hampers wedding",175000,10);
        sv[1] = new cSouvenir ("sv02","gelang manik manik",75000,50);
        
        // init pelanggan
        m [0] = new cPelanggan ("pl01","Nopal","0812","Sidoarjo");
        m [1] = new cPelanggan ("pl02","Ustad Nopal","0897","Surabaya");
        
        new FormLogin(loggedin).setVisible(true);
    }
    
    private static int countFilledLength(Object[] array) {
        int count = 0;
        for (Object element : array) {
            if (element != null) {
                count++;
            }
        }
        return count;
    }
    
    public static String toRupiah(double text) {
        double a = text;
        Locale localeID = new Locale("in", "ID");
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
        return formatRupiah.format(a).replace(",00", "").replace("Rp", "Rp ").trim();
    }
    
    private static int lihatJenisProduk() {
        System.out.println("===========================");
        System.out.printf("%-6s %s\n","","JENIS PRODUK");
        System.out.println("===========================");
        System.out.println("1. Pakaian\n2. Souvenir\n3. Back");
        System.out.println("===========================");
        System.out.print("Ketik disini (1,2): ");
        int temp = sc.nextInt();
        return temp;
    }
    
    private static void lihatAll() {
        System.out.println("\nDAFTAR PRODUK (PAKAIAN)");
        System.out.println("===============================================================================");
        System.out.printf("%-2s %-13s %-23s %-15s %-10s %s\n","#", "KODE PRODUK","NAMA PRODUK","HARGA","STOK","UKURAN");
        for(cPakaian pakaian : p) {
            if(pakaian != null) {
                System.out.printf("%-2s %-13s %-23s %-15s %-10s %s\n",
                        "~ ", pakaian.getKode(),pakaian.getNama(),toRupiah(pakaian.getHarga()),pakaian.getStok(),pakaian.getUkuran());
                i++;
            }
        }
        System.out.println("===============================================================================");
        System.out.println("DAFTAR PRODUK (SOUVENIR)");
        System.out.println("===============================================================================");
        System.out.printf("%-2s %-13s %-23s %-15s %s\n","#", "KODE PRODUK","NAMA PRODUK","HARGA","STOK");
        for(cSouvenir souvenir : sv) {
            if(souvenir != null) {
                System.out.printf("%-2s %-13s %-23s %-15s %s\n",
                        "~ ", souvenir.getKode(),souvenir.getNama(),toRupiah(souvenir.getHarga()),souvenir.getStok());
            }
        }
        System.out.println("===============================================================================");
    }
    
    private static void lihatPakaian() {
        i = 0;
        System.out.println("===============================================================================");
        System.out.printf("%-25s %s\n","","DAFTAR PRODUK (PAKAIAN)"
                + "");
        System.out.println("===============================================================================");
        System.out.printf("%-5s %-13s %-22s %-15s %-10s %s\n","No", "Kode Produk","Nama Produk","Harga","Stok","Ukuran");
        for(cPakaian pakaian : p) {
            if(pakaian != null) {
                System.out.printf("%-5s %-13s %-22s %-15s %-10s %s\n",
                        (i+1)+".", pakaian.getKode(),pakaian.getNama(),toRupiah(pakaian.getHarga()),pakaian.getStok(),pakaian.getUkuran());
                i++;
            }
        }
        System.out.println("===============================================================================\n");
    }
    
    private static void lihatSouvenir() {
        i = 0;
        System.out.println("==================================================================");
        System.out.printf("%-20s %s\n","","DAFTAR PRODUK (SOUVENIR)"
                + "");
        System.out.println("==================================================================");
        System.out.printf("%-5s %-13s %-23s %-14s %s\n","No", "Kode Produk","Nama Produk","Harga","Stok");
        for(cSouvenir souvenir : sv) {
            if(souvenir != null) {
                System.out.printf("%-5s %-13s %-23s %-14s %s\n",
                        (i+1)+".", souvenir.getKode(),souvenir.getNama(),toRupiah(souvenir.getHarga()),souvenir.getStok());
                i++;
            }
        }
        System.out.println("==================================================================\n");
    }
    
    private static void lihatPelanggan() {
        i = 0;
        System.out.println("===============================================================================");
        System.out.printf("%-20s %s\n","","DAFTAR PELANGGAN"
                + "");
        System.out.println("===============================================================================");
        System.out.printf("%-5s %-13s %-22s %-15s %s\n","No", "Kode Produk","Nama","No. HP","Alamat");
        for(cPelanggan pelanggan : m) {
            if(pelanggan != null) {
                System.out.printf("%-5s %-13s %-22s %-15s %s\n",
                        (i+1)+".", pelanggan.getKode(),pelanggan.getNama(),pelanggan.getNomor(),pelanggan.getAlamat());
                i++;
            }
        }
        System.out.println("===============================================================================\n");
    }
    
    private static cProduk[] combineProduk() {
        // combine array pakaian dan souvenir ke array produk
        cProduk[] produk = new cProduk[p.length + sv.length];
        System.arraycopy(p, 0, produk, 0, p.length);
        System.arraycopy(sv, 0, produk, p.length, sv.length);
        // hilangkan null
        produk = Arrays.stream(produk)
                                .filter(s -> s != null)
                                .toArray(cProduk[]::new);
        return produk;
    }
    
    public static void menu() {
        do {
            String user = (loggedin.getLoggedin_username() != null) ? loggedin.getLoggedin_username() : "tester";
            System.out.println("=====================================================");
            System.out.printf("%-18s %-32s %s\n", "|", "PROGRAM KASIR", "|");
            System.out.printf("%-13s %-37s %s\n", "|", "SURABAYA KRIYA GALLERY", "|");
            System.out.println("=====================================================");
            System.out.printf("%-11s %-39s %s\n","|","HALO, SELAMAT DATANG "+ user.toUpperCase(), "|");
            System.out.println("=====================================================");
            System.out.println("MENU UTAMA");
            System.out.println("1. Master Produk\n2. Master Pelanggan\n3. Transaksi\n4. Selesai");
            System.out.println("=====================================================");
            System.out.print("Ketik disini (1,2,3,4): ");
            pilih = sc.nextInt();
            switch (pilih) {
                case 1:
                    masterproduk(); break;
                case 2:
                    masterpelanggan(); break;
                case 3:
                    transaksi(); break;
                case 4:
                    laporan (); 
                    JOptionPane.showMessageDialog(null, "Terima Kasih, Sampai Jumpa Kembali!");
                    System.exit(0);
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Pilihan tidak valid!",
                    "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        } while(pilih != 4);
    }
    
    public static void masterproduk () {
        do {
            System.out.println("=====================================================");
            System.out.printf("%-18s %s\n","", "MASTER PRODUK");
            System.out.println("=====================================================");
            System.out.println("1. Lihat Daftar Produk\n2. Tambah Produk\n3. Ubah Produk\n4. Hapus Produk\n5. Back");
            System.out.println("=====================================================");
            System.out.print("Ketik disini (1-5): ");
            pilih2 = sc.nextInt();
            switch (pilih2){
                case 1:
                    do {
                        pilih3 = lihatJenisProduk();
                        // TAMPILKAN PRODUK SESUAI KATEGORI
                        switch(pilih3) {
                            // PAKAIAN
                            case 1:
                                if(p[0] != null) {
                                    lihatPakaian();
                                } else {
                                    System.out.println("PRODUK (pakaian) KOSONG.");
                                }
                                break;
                            // SOUVENIR
                            case 2:
                                if(sv[0] != null) {
                                    lihatSouvenir();
                                } else {
                                    System.out.println("PRODUK (souvenir) KOSONG.");
                                }
                                break;
                            case 3:
                                masterproduk();
                                break;
                            default:
                                JOptionPane.showMessageDialog(null, "Pilihan tidak valid!",
                                "ERROR", JOptionPane.ERROR_MESSAGE);
                        }      
                    } while(pilih3 != 3);
                    break;
                case 2:
                    do {
                        int length_p = countFilledLength(p);
                        int length_sv = countFilledLength(sv);
                        pilih3 = lihatJenisProduk();
                        if(pilih3 == 1 || pilih3 == 2) {
                            System.out.println("=====================================================");
                            System.out.printf("%-18s %s\n","","TAMBAH PRODUK");
                            System.out.println("=====================================================");
                            
                            if((pilih3 == 1 && length_p < p.length) || (pilih3 == 2 && length_sv < sv.length)) {
                                String label = (pilih3 == 1) ? "pakaian" : "souvenir";
                                System.out.print("Nama Produk ("+ label +") Baru : ");
                                sc = new Scanner(System.in);
                                String n = sc.nextLine();
                                System.out.print("Harga : Rp. ");
                                int h = sc.nextInt();
                                System.out.print("Stok : ");
                                int s = sc.nextInt(); 
                                    
                                int length_item = (pilih3 == 1) ? p.length : sv.length;
                                
                                for (int j = 0; j < length_item; j++) {
                                    if((pilih3 == 1 && p[j] == null) || (pilih3 == 2 && sv[j] == null)) {
                                        String k = ((pilih3 == 1) ? "p" : "sv") + String.format("%02d", j + 1);

                                        if(pilih3 == 1) {
                                            System.out.print("Ukuran : ");
                                            String u = sc.next();
                                            p[j] = new cPakaian(k, n, h, s, u);
                                            lihatPakaian();
                                        } else {
                                            sv[j] = new cSouvenir(k, n, h, s);
                                            lihatSouvenir();
                                        }
                                        break;
                                    }
                                }
                                
                                JOptionPane.showMessageDialog(null, "Produk ("+label+") Baru Berhasil Ditambahkan");
                            } else {
                                System.out.println("Ruang penyimpanan produk penuh.");
                            }
                        } else if(pilih3 == 3) {
                            masterproduk();
                            break;
                        } else {
                            JOptionPane.showMessageDialog(null, "Pilihan tidak valid!",
                            "ERROR", JOptionPane.ERROR_MESSAGE);
                        }  
                    } while(pilih3 != 3);
                    break;
                case 3:                
                    int jawab = 0;
                    do {
                        System.out.println("=====================================================");
                        System.out.printf("%-18s %s\n","","UBAH PRODUK");
                        System.out.println("=====================================================");
                        System.out.print("Masukkan Kode / Nama Produk: ");
                        sc = new Scanner(System.in);
                        String kode_nm = sc.nextLine();
                        
                        cProduk produk[] = combineProduk();
                        cProduk foundProduk = null;
                        for (cProduk pr : produk) {
                            if (pr != null && (pr.getKode().equalsIgnoreCase(kode_nm) || pr.getNama().equalsIgnoreCase(kode_nm))) {
                                foundProduk = pr;
                                break;
                            }
                        }

                        if(foundProduk != null) {
                            System.out.print(foundProduk.ToString());
                            if(foundProduk instanceof cPakaian pakaian) System.out.println(", Ukuran: "+pakaian.getUkuran());
                            else System.out.print("\n");
                            System.out.println("=====================================================");
                            System.out.println("(Ketik '-' dan Klik ENTER Jika Tidak Ingin Mengubah Data)");
                            System.out.print("Harga Baru = Rp. ");
                            String h_baru = sc.next();
                            System.out.print("Stok Baru = ");
                            String s_baru = sc.next();
                            
                            int edit = JOptionPane.showOptionDialog(null, 
                            "Yakin Ingin Mengubah Data?", 
                            "INFO", 
                            JOptionPane.YES_NO_OPTION, 
                            JOptionPane.QUESTION_MESSAGE, null, null, null);

                            if(edit == JOptionPane.YES_OPTION) {
                                
                                if(!h_baru.equals("-") && Integer.parseInt(h_baru) >= 0) foundProduk.setHarga(Integer.parseInt(h_baru));
                                if(!s_baru.equals("-") && Integer.parseInt(s_baru) >= 0) foundProduk.setStok(Integer.parseInt(s_baru));
                                
                                JOptionPane.showMessageDialog(null, "Ubah Produk Berhasil!");
                                System.out.print(foundProduk.ToString());
                                if(foundProduk instanceof cPakaian pakaian) System.out.println(", Ukuran: "+pakaian.getUkuran());
                                else System.out.print("\n");
                                masterproduk();
                                break;
                            } else {
                                JOptionPane.showMessageDialog(null, "Ubah Produk Dibatalkan.");
                                masterproduk();
                            }
                            
                        } else {
                            jawab = JOptionPane.showOptionDialog(null, 
                            "Produk Tidak Ditemukan, Cari Ulang?", 
                            "INFO", 
                            JOptionPane.YES_NO_OPTION, 
                            JOptionPane.QUESTION_MESSAGE, null, null, null);

                            if(jawab == JOptionPane.NO_OPTION) masterproduk();
                        }
                    } while(jawab == JOptionPane.YES_OPTION);
                    break;
                case 4:
                    jawab = 0;
                    do {
                        System.out.println("=====================================================");
                        System.out.printf("%-18s %s\n","","HAPUS PRODUK");
                        System.out.println("=====================================================");
                        System.out.print("Masukkan Kode / Nama Produk: ");
                        sc = new Scanner(System.in);
                        String kode_nm = sc.nextLine();
                        
                        cProduk produk[] = combineProduk();
                        cProduk foundProduk = null;
                        for (cProduk pr : produk) {
                            if (pr != null && (pr.getKode().equalsIgnoreCase(kode_nm) || pr.getNama().equalsIgnoreCase(kode_nm))) {
                                foundProduk = pr;
                                break;
                            }
                        }

                        if(foundProduk != null) {
                            System.out.print(foundProduk.ToString());
                            if(foundProduk instanceof cPakaian pakaian) System.out.println(", Ukuran: "+pakaian.getUkuran());
                            else System.out.print("\n");
                            
                            int hapus = JOptionPane.showOptionDialog(null, 
                            "Yakin Ingin Menghapus Produk?", 
                            "INFO", 
                            JOptionPane.YES_NO_OPTION, 
                            JOptionPane.QUESTION_MESSAGE, null, null, null);

                            if(hapus == JOptionPane.YES_OPTION) {
                                
                                if(Arrays.asList(p).indexOf(foundProduk) >= 0) p[Arrays.asList(p).indexOf(foundProduk)] = null;
                                if(Arrays.asList(sv).indexOf(foundProduk) >= 0) sv[Arrays.asList(sv).indexOf(foundProduk)] = null;
                                
                                JOptionPane.showMessageDialog(null, "Hapus Produk Berhasil!");
                                lihatPakaian();
                                lihatSouvenir();
                                masterproduk();
                                break;
                            } else {
                                JOptionPane.showMessageDialog(null, "Hapus Produk Dibatalkan.");
                                masterproduk();
                                break;
                            }
                            
                        } else {
                            jawab = JOptionPane.showOptionDialog(null, 
                            "Produk Tidak Ditemukan, Cari Ulang?", 
                            "INFO", 
                            JOptionPane.YES_NO_OPTION, 
                            JOptionPane.QUESTION_MESSAGE, null, null, null);

                            if(jawab == JOptionPane.NO_OPTION) masterproduk();
                        }
                    } while(jawab == JOptionPane.YES_OPTION);
                    break;
                case 5:
                    menu();
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Pilihan tidak valid!",
                    "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        } while(pilih2 != 5);
    }

    public static void masterpelanggan (){
        do {
            System.out.println("=====================================================");
            System.out.printf("%-18s %s\n","", "MASTER PELANGGAN");
            System.out.println("=====================================================");
            System.out.println("1. Daftar Member\n2. Tambah Member\n3. Ubah Data Member\n4. Hapus Member\n5. Rekap Biaya Belanja\n6. Back");
            System.out.println("=====================================================");
            System.out.print("Ketik disini (1-5): ");
            pilih2 = sc.nextInt();
            switch (pilih2){
                case 1:
                    if(m[0] != null) {
                        lihatPelanggan();
                    } else {
                        System.out.println("MEMBER KOSONG.");
                    }
                    masterpelanggan ();
                    break;
                case 2:
                    int length_m = countFilledLength(m);
                    System.out.println("=====================================================");
                    System.out.printf("%-18s %s\n","","TAMBAH MEMBER");
                    System.out.println("=====================================================");

                    if(length_m < m.length) {
                        System.out.print("Nama Member Baru : ");
                        sc = new Scanner(System.in);
                        String n = sc.nextLine();
                        System.out.print("No.Hp : ");
                        String no = sc.next();
                        System.out.print("Alamat : ");
                        sc = new Scanner(System.in);
                        String a = sc.nextLine();

                        for (int j = 0; j < m.length; j++) {
                            if(m[j] == null) {
                                String k = "pl" + String.format("%02d", j + 1);

                                m[j] = new cPelanggan(k, n, no, a);
                                break;
                            }
                        }

                            JOptionPane.showMessageDialog(null, "Member Baru Berhasil Ditambahkan");
                            lihatPelanggan();
                        } else {
                            System.out.println("Ruang penyimpanan penuh.");
                        }
                    masterpelanggan ();
                    break;
                case 3:
                    int jawab = 0;
                    do {
                        System.out.println("=====================================================");
                        System.out.printf("%-18s %s\n","","UBAH DATA MEMBER");
                        System.out.println("=====================================================");
                        System.out.print("Masukkan Kode / Nama Member: ");
                        sc = new Scanner(System.in);
                        String kode_nm = sc.nextLine();

                        cPelanggan foundMember = null;
                        for (cPelanggan member : m) {
                            if (member != null && (member.getKode().equalsIgnoreCase(kode_nm) || member.getNama().equalsIgnoreCase(kode_nm))) {
                                foundMember = member;
                                break;
                            }
                        }

                        if(foundMember != null) {
                            System.out.println(foundMember.ToString());
                            System.out.println("=====================================================");
                            System.out.println("(Ketik '-' dan Klik ENTER Jika Tidak Ingin Mengubah Data)");
                            System.out.print("Nama Baru = ");
                            sc = new Scanner(System.in);
                            String n_baru = sc.nextLine();
                            System.out.print("No. HP Baru = ");
                            String no_baru = sc.next();
                            System.out.print("Alamat Baru = ");
                            sc = new Scanner(System.in);
                            String a_baru = sc.nextLine();

                            int edit = JOptionPane.showOptionDialog(null, 
                            "Yakin Ingin Mengubah Data?", 
                            "INFO", 
                            JOptionPane.YES_NO_OPTION, 
                            JOptionPane.QUESTION_MESSAGE, null, null, null);

                            if(edit == JOptionPane.YES_OPTION) {

                                if(!n_baru.equals("-")) foundMember.setNama(n_baru);
                                if(!no_baru.equals("-")) foundMember.setNomor(no_baru);
                                if(!a_baru.equals("-")) foundMember.setAlamat(a_baru);

                                JOptionPane.showMessageDialog(null, "Ubah Data Member Berhasil!");
                                System.out.println(foundMember.ToString());
                                masterpelanggan();
                                break;
                            } else {
                                JOptionPane.showMessageDialog(null, "Ubah Data Member Dibatalkan.");
                                masterpelanggan();
                            }

                        } else {
                            jawab = JOptionPane.showOptionDialog(null, 
                            "Member Tidak Ditemukan, Cari Ulang?", 
                            "INFO", 
                            JOptionPane.YES_NO_OPTION, 
                            JOptionPane.QUESTION_MESSAGE, null, null, null);

                            if(jawab == JOptionPane.NO_OPTION) masterpelanggan();
                        }
                    } while(jawab == JOptionPane.YES_OPTION);
                    break;
                case 4:
                    jawab = 0;
                    do {
                        System.out.println("=====================================================");
                        System.out.printf("%-18s %s\n","","HAPUS MEMBER");
                        System.out.println("=====================================================");
                        System.out.print("Masukkan Kode / Nama Member: ");
                        sc = new Scanner(System.in);
                        String kode_nm = sc.nextLine();
                        
                        cPelanggan foundMember = null;
                        for (cPelanggan member : m) {
                            if (member != null && (member.getKode().equalsIgnoreCase(kode_nm) || member.getNama().equalsIgnoreCase(kode_nm))) {
                                foundMember = member;
                                break;
                            }
                        }

                        if(foundMember != null) {
                            System.out.println(foundMember.ToString());
                            
                            int hapus = JOptionPane.showOptionDialog(null, 
                            "Yakin Ingin Menghapus Member?", 
                            "INFO", 
                            JOptionPane.YES_NO_OPTION, 
                            JOptionPane.QUESTION_MESSAGE, null, null, null);

                            if(hapus == JOptionPane.YES_OPTION) {
                                
                                if(Arrays.asList(m).indexOf(foundMember) >= 0) m[Arrays.asList(m).indexOf(foundMember)] = null;
                                
                                JOptionPane.showMessageDialog(null, "Hapus Member Berhasil!");
                                lihatPelanggan();
                                masterpelanggan();
                                break;
                            } else {
                                JOptionPane.showMessageDialog(null, "Hapus Member Dibatalkan.");
                                masterpelanggan();
                                break;
                            }
                            
                        } else {
                            jawab = JOptionPane.showOptionDialog(null, 
                            "Member Tidak Ditemukan, Cari Ulang?", 
                            "INFO", 
                            JOptionPane.YES_NO_OPTION, 
                            JOptionPane.QUESTION_MESSAGE, null, null, null);

                            if(jawab == JOptionPane.NO_OPTION) masterpelanggan();
                        }
                    } while(jawab == JOptionPane.YES_OPTION);
                    break;
                case 5:
                    System.out.println("===============================================================================");
                    System.out.printf("%-30s %-46s %s\n","|","REKAP BIAYA BELANJA", "|");
                    System.out.println("===============================================================================");

                    int k = 0;
                    m = Arrays.stream(m)
                                .filter(s -> s != null)
                                .toArray(cPelanggan[]::new);
                    Arrays.sort(m, new Comparator<cPelanggan>(){
                        public int compare(cPelanggan p1, cPelanggan p2) {
                        if (p1.getRekapPembelian() < p2.getRekapPembelian()) {
                            return 1;
                        } else if (p1.getRekapPembelian() > p2.getRekapPembelian()) {
                            return -1;
                        }
                            return 0;
                        }
                    });
                    for(cPelanggan pl : m) {
                        if(pl != null) {
                            System.out.println((k+1)+". "+ pl.getNama() + " : "+ toRupiah(pl.getRekapPembelian()));
                            k++;
                        }
                    }
                    System.out.println((k+1)+". Non pelanggan : "+ toRupiah(rekapNonMember));
                    break;
                case 6:
                    menu ();
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Pilihan tidak valid!",
                        "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        } while(pilih2 != 6);
    }
    
    public static void transaksi (){
        do {
            
            int grand_total = 0;
            System.out.println("=====================================================");
            System.out.printf("%-18s %s\n","", "MENU TRANSAKSI");
            System.out.println("=====================================================");
            System.out.println("1. Tambah Item\n2. Hapus Item\n3. Bayar \n4. Back");
            System.out.print("Ketik disini (1,2,3,4): ");
            pilih2 = sc.nextInt();
            cProduk produk[] = combineProduk();
            switch (pilih2){
                case 1:
                    int jawab = 0;
                    System.out.println("===============================================================================");
                    System.out.printf("%-33s %-43s %s\n","|","TAMBAH ITEM", "|");
                    System.out.println("===============================================================================");
                    cPelanggan pb = new cPelanggan();

                    int idx_tr = -1;
                    sc = new Scanner(System.in);
                    System.out.print("Nama Pembeli = ");
                    String nm_pb = sc.nextLine();

                    for(cTransaksi transaksi : tr) {
                        if(transaksi == null) {
                            idx_tr = Arrays.asList(tr).indexOf(transaksi);
                            tr[idx_tr] = new cTransaksi();
                            break;
                        } else if (transaksi.getPembeli().getNama().equalsIgnoreCase(nm_pb)) {
                            idx_tr = Arrays.asList(tr).indexOf(transaksi);
                            break;
                        }
                    }

                    boolean member_found = false;
                    for(cPelanggan pl : m) {
                        if(pl != null && pl.getNama().equalsIgnoreCase(nm_pb)) {
                            member_found = true;
                            pb = pl;
                            break;
                        }
                    }

                    if(!member_found) pb.setNama(nm_pb);
                    tr[idx_tr].setPembeli(pb);

                    do {
                        lihatAll();
                        System.out.println("Ketik '0' & ENTER untuk Kembali / Selesai.");
                        System.out.println("Ketik 'v' & ENTER untuk Lihat Keranjang.");
                        System.out.print("Masukkan Kode / Nama Produk: ");
                        sc = new Scanner(System.in);
                        String kode_nm = sc.nextLine();
                        cProduk foundProduk = null;

                        if(!kode_nm.equals("0") && !kode_nm.equals("v")) {
                            for (cProduk pr : produk) {
                                if (pr != null && (pr.getKode().equalsIgnoreCase(kode_nm) || pr.getNama().equalsIgnoreCase(kode_nm))) {
                                    foundProduk = pr;
                                    break;
                                }
                            }
                        }

                        if(foundProduk != null) {

                            System.out.print("Masukkan Jumlah = ");
                            int jml = sc.nextInt();

                            int confirm_add = JOptionPane.showOptionDialog(null, 
                            "Yakin Ingin Menambah Produk ke Keranjang?", 
                            "QUESTION", 
                            JOptionPane.YES_NO_OPTION, 
                            JOptionPane.QUESTION_MESSAGE, null, null, null);

                            if(confirm_add == JOptionPane.YES_OPTION && foundProduk.getStok() >= jml) {
                                JOptionPane.showMessageDialog(null, 
                                        "== INFORMASI PRODUK ==\nKode : "+ foundProduk.getKode() +"\nProduk : "
                                        + foundProduk.getNama() + "\nJumlah Beli : "+jml);

                                if(tr[idx_tr].tambah_item(foundProduk, jml)) {
                                    JOptionPane.showMessageDialog(null, 
                                        "Sukses Menambah Item ke Keranjang!");

                                    grand_total = tr[idx_tr].getGrandTotal() + (foundProduk.getHarga() * jml);
                                    tr[idx_tr].setGrandTotal(grand_total);

                                    tr[idx_tr].lihat_keranjang("");
                                    jawab = JOptionPane.YES_OPTION;
                                } else {
                                    JOptionPane.showMessageDialog(null, "Maaf, Keranjang Sudah Penuh!",
                                    "ERROR", JOptionPane.ERROR_MESSAGE);
                                    jawab = JOptionPane.NO_OPTION;
                                }
                            } else {   
                                if(foundProduk.getStok() < jml) {
                                    jawab = JOptionPane.showOptionDialog(null, 
                                    "Stok Item Tidak Memadai!\nApakah anda ingin menambah item ulang?", 
                                    "INFO", 
                                    JOptionPane.YES_NO_OPTION, 
                                    JOptionPane.QUESTION_MESSAGE, null, null, null);
                                } else {
                                    jawab = JOptionPane.showOptionDialog(null, 
                                    "Penambahan Item Dibatalkan!\nApakah anda ingin menambah item ulang?", 
                                    "INFO", 
                                    JOptionPane.YES_NO_OPTION, 
                                    JOptionPane.QUESTION_MESSAGE, null, null, null);              
                                }
                            }   
                        } else {
                            if(!kode_nm.equals("0") && !kode_nm.equals("v")) {
                                jawab = JOptionPane.showOptionDialog(null, 
                                "Produk Tidak Ditemukan, Cari Ulang?", 
                                "INFO", 
                                JOptionPane.YES_NO_OPTION, 
                                JOptionPane.QUESTION_MESSAGE, null, null, null);
                            } else if(kode_nm.equals("v")) {
                                tr[idx_tr].lihat_keranjang("");
                                jawab = JOptionPane.YES_OPTION;
                            }
                            else {
                                jawab = JOptionPane.NO_OPTION;
                            }
                        }
                    } while(jawab == JOptionPane.YES_OPTION);
                    break;
                case 2:
                    jawab = 0;
                    System.out.println("===============================================================================");
                    System.out.printf("%-33s %-43s %s\n","|","HAPUS ITEM", "|");
                    System.out.println("===============================================================================");
                    
                    sc = new Scanner(System.in);
                    System.out.print("Nama Pembeli = ");
                    nm_pb = sc.nextLine();
                    
                    idx_tr = -1;
                    for(cTransaksi transaksi : tr) {
                        if (transaksi != null && transaksi.getPembeli().getNama().equalsIgnoreCase(nm_pb)) {
                            idx_tr = Arrays.asList(tr).indexOf(transaksi);
                            break;
                        } else {
                            jawab = JOptionPane.NO_OPTION;
                        }
                    }
                    
                    do {
                        if(idx_tr >= 0 && tr[idx_tr].getProduks()[0] != null) {
                            tr[idx_tr].lihat_keranjang("");
                            System.out.println("Ketik '0' & ENTER untuk Kembali / Selesai.");
                            System.out.print("Masukkan Kode / Nama Produk: ");
                            sc = new Scanner(System.in);
                            String kode_nm = sc.nextLine();
                            
                            if(!kode_nm.equals("0")) {
                                int hapus = JOptionPane.showOptionDialog(null, 
                                    "Yakin Ingin Menghapus Produk (Transaksi)?", 
                                    "INFO", 
                                    JOptionPane.YES_NO_OPTION, 
                                    JOptionPane.QUESTION_MESSAGE, null, null, null);
                                
                                    cProduk foundProduk = null;
                                    for (cProduk pr : produk) {
                                        if (pr != null && (pr.getKode().equalsIgnoreCase(kode_nm) || pr.getNama().equalsIgnoreCase(kode_nm))) {
                                            foundProduk = pr;
                                            break;
                                        }
                                    }
                                
                                if(hapus == JOptionPane.YES_OPTION && tr[idx_tr].hapus_item(kode_nm, foundProduk)) {
                                    // setuju dan ketemu
                                    JOptionPane.showMessageDialog(null, 
                                        "Sukses Menghapus Item dari Keranjang!");
                                    jawab = JOptionPane.YES_OPTION;
                                } else if(hapus == JOptionPane.YES_OPTION && !tr[idx_tr].hapus_item(kode_nm, foundProduk)) {
                                    // setuju / tidak ketemu
                                    jawab = JOptionPane.showOptionDialog(null, 
                                    "Produk Tidak Ditemukan, Cari Ulang?", 
                                    "INFO", 
                                    JOptionPane.YES_NO_OPTION, 
                                    JOptionPane.QUESTION_MESSAGE, null, null, null);
                                } else {
                                    // tidak setuju
                                    jawab = JOptionPane.showOptionDialog(null, 
                                    "Hapus Item Dibatalkan!\nApakah anda ingin cari ulang?", 
                                    "INFO", 
                                    JOptionPane.YES_NO_OPTION, 
                                    JOptionPane.QUESTION_MESSAGE, null, null, null);    
                                }
                            } else {
                                jawab = JOptionPane.NO_OPTION;
                            }
                        } else {
                            if(idx_tr < 0) {
                                JOptionPane.showMessageDialog(null, "Transaksi Pembeli Tidak Ditemukan!",
                                "ERROR", JOptionPane.ERROR_MESSAGE);
                            }
                            
                            if(idx_tr >= 0 && tr[idx_tr].getProduks()[0] == null) {
                                JOptionPane.showMessageDialog(null, "Keranjang Anda Kosong!",
                                "ERROR", JOptionPane.ERROR_MESSAGE);
                            }
                            jawab = JOptionPane.NO_OPTION;
                        }
                    } while(jawab == JOptionPane.YES_OPTION);
                    break;
                case 3:
                    jawab = 0;
                    System.out.println("===============================================================================");
                    System.out.printf("%-33s %-43s %s\n","|","BAYAR BELANJAAN", "|");
                    System.out.println("===============================================================================");
                    
                    sc = new Scanner(System.in);
                    System.out.print("Nama Pembeli = ");
                    nm_pb = sc.nextLine();
                    
                    idx_tr = -1;
                    for(cTransaksi transaksi : tr) {
                        if (transaksi != null && transaksi.getPembeli().getNama().equalsIgnoreCase(nm_pb)) {
                            idx_tr = Arrays.asList(tr).indexOf(transaksi);
                            break;
                        } else {
                            jawab = JOptionPane.NO_OPTION;
                        }
                    }
                    
                    do {
                        if(idx_tr >= 0 && tr[idx_tr].getProduks()[0] != null) {
                            tr[idx_tr].lihat_keranjang("final");
                            
                            System.out.println("[Ketik '-' & ENTER Jika Bukan Member]");
                            System.out.print("Masukkan Kode Member (Opsional) = ");
                            String kd_m = sc.next();
                            
                            double grand_total_final = tr[idx_tr].getGrandTotal();
                            double diskon = 0;
                            boolean is_member = false;
                            
                            for(cPelanggan pl : m) {
                                if(pl != null && pl.getKode().equalsIgnoreCase(kd_m) && pl.getNama().equalsIgnoreCase(nm_pb)) {
                                    diskon = 0.1;
                                    is_member = true; 
                                    break;
                                }
                            }
                            
                            if(diskon > 0) {
                                grand_total_final = tr[idx_tr].getGrandTotal() - (tr[idx_tr].getGrandTotal() * diskon);
                                System.out.println("SELAMAT ANDA MENDAPAT DISKON 10%\n"
                                        + "KARENA ANDA TERDAFTAR SEBAGAI MEMBER!!");
                                System.out.println("Total Setelah Potongan (Diskon) : "+toRupiah(grand_total_final));
                            }
                            double kembalian;
                            System.out.print("Masukkan Uang Pembayaran: ");
                            int uang_bayar = sc.nextInt();
                            
                            if(uang_bayar < grand_total_final) {
                                JOptionPane.showMessageDialog(null, "MAAF UANG ANDA KURANG!",
                                "ERROR", JOptionPane.ERROR_MESSAGE);
                                jawab = JOptionPane.YES_OPTION;
                            } else if(uang_bayar > grand_total_final) {
                                kembalian = uang_bayar - grand_total_final;
                                System.out.println("Uang Kembalian : "+toRupiah(kembalian));
                                jawab = JOptionPane.NO_OPTION;
                            } else {
                                jawab = JOptionPane.NO_OPTION;
                            }
                            
                            if(uang_bayar > grand_total_final || uang_bayar == grand_total_final) {
                                JOptionPane.showMessageDialog(null, 
                                        "Transaksi Berhasil, Terima Kasih!");
                                
                                // Rekap per Jenis Produk
                                for(cProduk tr_pr : tr[idx_tr].getProduks()) {
                                    if(tr_pr != null && Arrays.asList(produk).indexOf(tr_pr) >= 0) {
                                        int idx_tr_pr = Arrays.asList(tr[idx_tr].getProduks()).indexOf(tr_pr);
                                        int idx_pr = Arrays.asList(produk).indexOf(tr_pr);
                                        produk[idx_pr].setRekapPendapatan(produk[idx_pr].getRekapPendapatan() + tr[idx_tr].getTotProduk()[idx_tr_pr]);
                                        totalPendapatan += produk[idx_pr].getRekapPendapatan();
                                    } 
                                }
                                
                                // Rekap per Pembeli
                                if(!is_member) rekapNonMember += grand_total_final;
                                else {
                                    for(cPelanggan pl : m) {
                                        if(pl != null && pl.getKode().equalsIgnoreCase(kd_m)) {
                                            int idx_pl = Arrays.asList(m).indexOf(pl);
                                            m[idx_pl].setRekapPembelian(m[idx_pl].getRekapPembelian() + grand_total_final);
                                        }
                                    }
                                }
                                
                                // Hapus Keranjang Transaksi
                                boolean found = false;
                                
                                int length_tr = countFilledLength(tr);
                                for (int i = 0; i < tr.length; i++) {
                                    if(tr[i] != null && tr[i].getPembeli().getNama().equalsIgnoreCase(nm_pb)) {
                                        found = true;
                                        tr[i] = null;
                                        // geser elemen belakangnya
                                        for (int j = i; j < length_tr; j++) {
                                            if(j == (length_tr-1)) tr[j] = null;
                                            else tr[j] = tr[j+1];
                                        }

                                        length_tr--;
                                    }
                                }
                            } 
                        } else {
                            if(idx_tr < 0) {
                                JOptionPane.showMessageDialog(null, "Transaksi Pembeli Tidak Ditemukan!",
                                "ERROR", JOptionPane.ERROR_MESSAGE);
                            }
                            
                            if(idx_tr >= 0 && tr[idx_tr].getProduks()[0] == null) {
                                JOptionPane.showMessageDialog(null, "Keranjang Anda Kosong!",
                                "ERROR", JOptionPane.ERROR_MESSAGE);
                            }
                            jawab = JOptionPane.NO_OPTION;
                        }
                    } while(jawab == JOptionPane.YES_OPTION);
                    break;
                case 4:
                    menu();
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Pilihan tidak valid!",
                        "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        } while (pilih2 != 4);
    }
    
    public static void laporan() {
        System.out.println("===============================================================================");
        System.out.printf("%-33s %-43s %s\n","|","LAPORAN PEMASUKAN", "|");
        System.out.println("===============================================================================");
        System.out.println("Total Pendapatan : " + toRupiah(totalPendapatan));
        
        cProduk produk[] = combineProduk();
        int k = 0;
        Arrays.sort(produk, new Comparator<cProduk>(){
            public int compare(cProduk p1, cProduk p2) {
            if (p1.getRekapPendapatan() < p2.getRekapPendapatan()) {
                return 1;
            } else if (p1.getRekapPendapatan() > p2.getRekapPendapatan()) {
                return -1;
            }
                return 0;
            }
        });
        for(cProduk pr : produk) {
            if(pr != null) {
                System.out.println((k+1)+". "+ pr.getNama() + " : "+ toRupiah(pr.getRekapPendapatan()));
                k++;
            }
        }
    }
}
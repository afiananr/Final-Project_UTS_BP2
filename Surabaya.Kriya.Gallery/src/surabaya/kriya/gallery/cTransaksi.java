package surabaya.kriya.gallery;
import static surabaya.kriya.gallery.kasir.toRupiah;
import java.util.Arrays;

public class cTransaksi {
    private cPelanggan pembeli;
    private cProduk produks[];
    private int jumProduk[];
    private int totProduk[];
    private int grandTotal;
    private int jm;
    private int maksm;
    
    cTransaksi() {
        this.maksm = 5; this.jm = 0; this.grandTotal = 0;
        this.produks = new cProduk[this.maksm];
        this.jumProduk = new int[this.maksm];
        this.totProduk = new int[this.maksm];
    }
        
    public boolean tambah_item(cProduk produk, int j) {
        if(this.jm < this.maksm) {
            
            boolean exist = false; int idx = -1;
            for (cProduk pr : produks) {
                if(pr != null && pr == produk) {
                    exist = true;
                    idx = Arrays.asList(produks).indexOf(pr);
                }
            }
            
            if(exist) {
                this.jumProduk[idx] += j;
                this.totProduk[idx] = this.produks[idx].getHarga() * this.jumProduk[idx];
            } else {
                this.produks[this.jm] = produk;
                this.jumProduk[this.jm] = j;
                this.totProduk[this.jm] = j * produk.getHarga();
                this.jm++;
            }
            
            produk.setStok(produk.getStok() - j);
            return true;
        }
        
        return false;
    }
    
    public boolean hapus_item(String nm, cProduk produk) {
        boolean found = false;
        for (int i = 0; i < this.jm; i++) {
            if(nm.equalsIgnoreCase(produks[i].getKode()) || nm.equalsIgnoreCase(produks[i].getNama())) {
                found = true;
                this.produks[i] = null;
                produk.setStok(produk.getStok() + this.jumProduk[i]);
                this.jumProduk[i] = 0;
                this.totProduk[i] = 0;
                // geser elemen belakangnya
                for (int j = i; j < this.jm; j++) {
                    if(j == (this.jm-1)) {
                        this.produks[j] = null;
                        this.jumProduk[j] = 0;
                        this.totProduk[j] = 0;
                    } else {
                        this.produks[j] = this.produks[j+1];
                        this.jumProduk[j] = this.jumProduk[j+1];
                        this.totProduk[j] = this.totProduk[j+1];
                    }
                }
                
                
                this.jm--;
                this.grandTotal = 0; 
                for (int a = 0; a < this.jm; a++) {
                    this.grandTotal += this.totProduk[a];
                }
                return true;
            }
        }
        
        return false;
    }
    
    public void lihat_keranjang(String status) {
        System.out.println("\n====================================================================================");
        System.out.println("KERANJANG ("+pembeli.getNama()+")");
        System.out.println("====================================================================================");
        System.out.printf("%-5s %-13s %-23s %-14s %-12s %s \n","No", "Kode Produk","Nama Produk","Harga","Jumlah", "SubTotal");
        for (int i = 0; i < this.produks.length; i++) {
            if(produks[i] != null) {
                System.out.printf("%-5s %-13s %-23s %-14s %-12s %s\n",
                        (i+1)+".", produks[i].getKode(),produks[i].getNama(),
                        toRupiah(produks[i].getHarga()), this.jumProduk[i], toRupiah(this.totProduk[i]));
            }
        }
        System.out.println("====================================================================================");
        if(!status.equals("final")) {
            System.out.println("Total Belanja Sementara : "+ toRupiah(this.grandTotal));
        } else {
            System.out.println("Total Akhir Belanja : "+ toRupiah(this.grandTotal));
        }
    }

    public void setPembeli(cPelanggan pembeli) {
        this.pembeli = pembeli;
    }

    public void setGrandTotal(int grandTotal) {
        this.grandTotal = grandTotal;
    }
    
    public cProduk[] getProduks() {
        return produks;
    }

    public cPelanggan getPembeli() {
        return pembeli;
    }
    
    public int getJm() {
        return jm;
    }
    
    public int[] getTotProduk() {
        return totProduk;
    }

    public int getGrandTotal() {
        return grandTotal;
    }
}

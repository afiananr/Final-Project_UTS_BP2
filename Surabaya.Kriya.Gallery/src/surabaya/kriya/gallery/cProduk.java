package surabaya.kriya.gallery;

public class cProduk {
    private String kode, nama;
    private int harga, stok, rekapPendapatan;
    
    cProduk (String k, String n, int h, int s){
        kode = k; nama = n; harga = h; stok = s; this.rekapPendapatan = 0;
    }
    
    public String getKode(){
        return kode;
    }    
    
    public String getNama(){
        return nama;
    }   
    
    public int getHarga(){
        return harga;
    }
    
    public int getStok (){
        return stok;
    }

    public int getRekapPendapatan() {
        return rekapPendapatan;
    }

    public void setHarga(int h){
        this.harga = h;
    }
    
    public void setStok (int s){
        this.stok = s;
    }

    public void setRekapPendapatan(int rekapPendapatan) {
        this.rekapPendapatan = rekapPendapatan;
    }
    
    public String ToString(){
        return "Kode Produk: "+ this.kode +", Nama: "+ this.nama +", Harga: "+ kasir.toRupiah(this.harga) +", Stok: "+ this.stok;
    }
}
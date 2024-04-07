package surabaya.kriya.gallery;

public class cPelanggan {
    private String kode, nama, nohp, alamat;
    private double rekapPembelian;
    
    cPelanggan() {}
    
    cPelanggan (String k, String n, String no, String a){
        kode = k; nama = n; nohp = no; alamat = a;
    }
    
    public String getKode(){
        return kode;
    }    
    
    public String getNama(){
        return nama;
    }   
    
    public String getNomor(){
        return nohp;
    }
    
    public String getAlamat (){
        return alamat;
    }

    public double getRekapPembelian() {
        return rekapPembelian;
    }
    
    public void setNama(String n) {
        this.nama = n;
    }
    
    public void setNomor(String no){
        this.nohp = no;
    }
    
    public void setAlamat (String a){
        this.alamat = a;
    }

    public void setRekapPembelian(double rekapPembelian) {
        this.rekapPembelian = rekapPembelian;
    }
   
    public String ToString(){
        return "Kode Member: "+ kode +", Nama: "+ nama +", No.HP: "+ this.nohp +", Alamat: "+ this.alamat;     
    }
}
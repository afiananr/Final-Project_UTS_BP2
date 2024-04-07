package surabaya.kriya.gallery;

public class cPakaian extends cProduk {
    private String ukuran;
    
    cPakaian(String k, String n, int h, int s, String ukuran) {
        super(k,n,h,s);
        this.ukuran = ukuran;
    }

    public String getUkuran() {
        return ukuran;
    }

    public void setUkuran(String ukuran) {
        this.ukuran = ukuran;
    }
}

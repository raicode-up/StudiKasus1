
public abstract class Pengguna {
    private String nama;
    private String noTelepon;
    private String nikKtp;
    private String password;

    public Pengguna(String nama, String noTelepon, String nikKtp, String password) {
        this.nama = nama;
        this.noTelepon = noTelepon;
        this.nikKtp = nikKtp;
        this.password = password;
    }

    public String getNama() {
        return nama;
    }

    public String getNoTelepon() {
        return noTelepon; 
    }

    public String getNikKtp() {
        return nikKtp;
    }

    public String getPassword() {
        return password;
    }

    public boolean cekPassword(String inputPassword) {
        return password.equals(inputPassword);
    }

    public abstract double hitungPotonganHarga(double totalSewa);

    public abstract String getStatusTipe();
    public abstract String getDetailMember();
    public abstract boolean isMember();

    public void tambahRiwayat(Pemesanan notaBaru) {
    }

    public void cekHistoriPenyewaan() {
        System.out.println("\nMaaf, fitur histori transaksi hanya tersedia untuk akun Member.");
    }
}
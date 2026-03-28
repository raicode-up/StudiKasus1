import java.util.ArrayList;

public class Member extends Pengguna {
    private String email;
    private String nomorMember; 
    private double persenDiskon; 
    private ArrayList<Pemesanan> riwayatPenyewaan; 

    private static int jumlahMemberTerdaftar = 1; 

    public Member(String nama, String noTelepon, String email, String nikKtp, String password) {
        super(nama, noTelepon, nikKtp, password); 
        this.email = email;
        this.persenDiskon = 0.10; 
        this.riwayatPenyewaan = new ArrayList<>();

        this.nomorMember = String.format("MEM-%03d", jumlahMemberTerdaftar);
        jumlahMemberTerdaftar++; 
    }

    @Override
    public void tambahRiwayat(Pemesanan notaBaru) {
        riwayatPenyewaan.add(notaBaru);
    }

    @Override
    public void cekHistoriPenyewaan() {
        System.out.println("\n=============================================");
        System.out.println("   HISTORI PENYEWAAN MEMBER: " + getNama());
        System.out.println("=============================================");
        
        if (riwayatPenyewaan.isEmpty()) {
            System.out.println(" Belum ada riwayat transaksi penyewaan.");
        } else {
            int nomor = 1;
            for (Pemesanan nota : riwayatPenyewaan) {
                System.out.println("\n--- Transaksi Ke-" + nomor + " ---");
                // ✅ SUDAH DISINKRONKAN DENGAN PEMESANAN.JAVA
                System.out.println("Ambil : " + nota.getTanggalAmbil() + " (Jam " + nota.getJamAmbil() + ")");
                System.out.println("Balik : " + nota.getTanggalSelesai() + " (Jam " + nota.getJamSelesai() + ")");
                System.out.println("Armada: " + nota.getKendaraan().getNamaKendaraan());
                System.out.println("Durasi: " + nota.getLamaSewa() + " Jam");
                System.out.println("Bayar : Rp " + String.format("%,.0f", nota.hitungTotalBayar()));
                nomor++;
            }
        }
        System.out.println("=============================================\n");
    }

    public String getEmail() { return email; }
    public String getNomorMember() { return nomorMember; }

    @Override
    public double hitungPotonganHarga(double totalSewa) {
        return totalSewa * persenDiskon; 
    }

    @Override
    public String getStatusTipe() { return "Member (Diskon 10%)"; }
    
    @Override
    public String getDetailMember() { return nomorMember; }

    @Override
    public boolean isMember() { return true; }
}
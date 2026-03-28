import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Pemesanan {
    private Pengguna pengguna;
    private Kendaraan kendaraan;
    private int lamaSewa;
    private String tanggalAmbil;
    private String jamAmbil;
    private String tanggalSelesai;
    private String jamSelesai;
    private boolean pakaiSupir;
    private String nomorSim;
    private LocalDateTime waktuTransaksi;

    public Pemesanan(Pengguna pengguna, Kendaraan kendaraan, int lamaSewa, 
                     String tanggalAmbil, String jamAmbil, String tanggalSelesai, 
                     String jamSelesai, boolean pakaiSupir, String nomorSim) {
        this.pengguna = pengguna;
        this.kendaraan = kendaraan;
        this.lamaSewa = lamaSewa;
        this.tanggalAmbil = tanggalAmbil;
        this.jamAmbil = jamAmbil;
        this.tanggalSelesai = tanggalSelesai;
        this.jamSelesai = jamSelesai;
        this.pakaiSupir = pakaiSupir;
        this.nomorSim = nomorSim;
        this.waktuTransaksi = LocalDateTime.now();
    }

    // Fungsi matematika bantuan untuk hitung total akhir (dipakai di Struk dan Histori)
    public double hitungTotalBayar() {
        double biayaKotor = kendaraan.hitungBiayaSewa(lamaSewa, pakaiSupir);
        double diskonMember = pengguna.hitungPotonganHarga(biayaKotor);
        return biayaKotor - diskonMember;
    }

    public void cetakStruk() {
        double biayaKotor = kendaraan.hitungBiayaSewa(lamaSewa, pakaiSupir);
        double diskonMember = pengguna.hitungPotonganHarga(biayaKotor);
        double totalAkhir = hitungTotalBayar(); // Gunakan fungsi buatan di atas

        DateTimeFormatter formatWaktu = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        String waktuCetakStruk = waktuTransaksi.format(formatWaktu);

        System.out.println("\n\n");
        System.out.println("╔═══════════════════════════════════════════════════════════╗");
        System.out.println("║                    FILKOM TRAVEL MALANG                   ║");
        System.out.println("║              Bukti Pembayaran Sewa Kendaraan              ║");
        System.out.println("╠═══════════════════════════════════════════════════════════╣");
        System.out.printf("║ Waktu Transaksi : %-39s ║\n", waktuCetakStruk);
        System.out.println("╟───────────────────────────────────────────────────────────╢");
        System.out.println("║                    INFORMASI PELANGGAN                    ║");
        System.out.println("╟───────────────────────────────────────────────────────────╢");
        System.out.printf("║ Nama Penyewa    : %-39s ║\n", pengguna.getNama());
        System.out.printf("║ No. Telepon     : %-39s ║\n", pengguna.getNoTelepon());
        System.out.printf("║ Status Akun     : %-39s ║\n", (pengguna.isMember() ? "Member (Diskon 10%)" : "Non-Member"));
        System.out.println("╟───────────────────────────────────────────────────────────╢");
        System.out.println("║                     RINCIAN PENYEWAAN                     ║");
        System.out.println("╟───────────────────────────────────────────────────────────╢");
        
        // Sesuaikan dengan method nama kendaraan di kelas Kendaraanmu (getNamaKendaraan() atau getNama())
        System.out.printf("║ Kendaraan       : %-39s ║\n", kendaraan.getNamaKendaraan());
        
        System.out.printf("║ Layanan Driver  : %-39s ║\n", (pakaiSupir ? "Ya (Termasuk Jasa Driver)" : "Lepas Kunci (Tanpa Driver)"));
        if (!pakaiSupir) {
            System.out.printf("║ No. SIM Driver  : %-39s ║\n", nomorSim);
        }
        System.out.printf("║ Waktu Pengambilan: %-39s ║\n", tanggalAmbil + " " + jamAmbil);
        System.out.printf("║ Waktu Pengembalian: %-38s ║\n", tanggalSelesai + " " + jamSelesai);
        System.out.printf("║ Durasi Sewa     : %-39s ║\n", lamaSewa + " Jam");
        System.out.println("╠═══════════════════════════════════════════════════════════╣");
        System.out.println("║                    RINCIAN PEMBAYARAN                     ║");
        System.out.println("╠═══════════════════════════════════════════════════════════╣");
        System.out.printf("║ Biaya Sewa Dasar: Rp %-36s ║\n", String.format("%,.0f", biayaKotor));
        if (diskonMember > 0) {
            System.out.printf("║ Diskon Member   : -Rp %-35s ║\n", String.format("%,.0f", diskonMember));
        } else {
            System.out.printf("║ Diskon Member   : Rp %-36s ║\n", "0");
        }
        System.out.println("╟───────────────────────────────────────────────────────────╢");
        System.out.printf("║ TOTAL DIBAYAR   : Rp %-36s ║\n", String.format("%,.0f", totalAkhir));
        System.out.println("╚═══════════════════════════════════════════════════════════╝");
        System.out.println("          Terima Kasih Telah Memilih Filkom Travel!          \n");
    }
    public Kendaraan getKendaraan() { return kendaraan; }
    public String getTanggalAmbil() { return tanggalAmbil; }
    public String getJamAmbil() { return jamAmbil; }
    public String getTanggalSelesai() { return tanggalSelesai; }
    public String getJamSelesai() { return jamSelesai; }
    public int getLamaSewa() { return lamaSewa; }
}
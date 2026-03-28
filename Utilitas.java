import java.util.Scanner;
import java.util.ArrayList;
import java.time.LocalDateTime;

public class Utilitas {
    
    public static int validasiPilihanAngka(Scanner input, String pesanPrompt) {
        while (true) {
            System.out.print(pesanPrompt);
            if (input.hasNextInt()) {
                int angka = input.nextInt();
                input.nextLine(); 
                return angka; 
            } else {
                System.out.println("\nError! Input harus berupa angka bulat! Jangan ketik huruf.");
                input.nextLine(); 
            }
        }
    }

    public static boolean periksaNoTeleponSudahTerdaftar(ArrayList<Pengguna> daftarPengguna, String nomorTelepon) {
        for (Pengguna pengguna : daftarPengguna) {
            if (pengguna.getNoTelepon().equals(nomorTelepon)) {
                return true;
            }
        }
        return false;
    }
    
    public static boolean periksaFormatTanggalValid(String inputTanggal) {
        if (inputTanggal.length() != 10) {
            return false;
        } 
        
        if (inputTanggal.charAt(2) != '-' || inputTanggal.charAt(5) != '-') {
            return false;
        }
        
        for (int i = 0; i < inputTanggal.length(); i++) {
            if (i == 2 || i == 5) {
                continue;
            }
            char karakter = inputTanggal.charAt(i);
            if (karakter < '0' || karakter > '9') {
                return false;
            }
        }

        int tgl = ((inputTanggal.charAt(0) - '0') * 10) + (inputTanggal.charAt(1) - '0');
        int bln = ((inputTanggal.charAt(3) - '0') * 10) + (inputTanggal.charAt(4) - '0');

        if (tgl < 1 || tgl > 31) {
            System.out.println("\nError! Tanggal tidak valid (01-31).");
            return false;
        }
        if (bln < 1 || bln > 12) {
            System.out.println("\nError! Bulan tidak valid (01-12).");
            return false;
        }

        return true; 
    }

    public static boolean periksaFormatJamValid(String inputJam) {
        if (inputJam.length() != 5) {
            return false;
        }

        if (inputJam.charAt(2) != '.') {
            return false;
        }

        for (int i = 0; i < inputJam.length(); i++) {
            if (i == 2) {
                continue;
            }
            char c = inputJam.charAt(i);
            if (c < '0' || c > '9') {
                return false;
            }
        }

        int jam = ((inputJam.charAt(0) - '0') * 10) + (inputJam.charAt(1) - '0');
        int menit = ((inputJam.charAt(3) - '0') * 10) + (inputJam.charAt(4) - '0');

        if (jam < 0 || jam > 23) {
            System.out.println("\nError! Jam tidak valid (00-23).");
            return false;
        }
        if (menit < 0 || menit > 59) {
            System.out.println("\nError! Menit tidak valid (00-59).");
            return false;
        }

        return true;
    }

    public static boolean periksaTanggalMendatang(int hari, int bulan, int tahun) {
        LocalDateTime waktuSekarang = LocalDateTime.now();
        int tahunSekarang = waktuSekarang.getYear();
        int bulanSekarang = waktuSekarang.getMonthValue();
        int hariSekarang = waktuSekarang.getDayOfMonth();

        if (tahun > tahunSekarang) {
            return true;
        }
        if (tahun == tahunSekarang) {
            if (bulan > bulanSekarang) {
                return true;
            }
            if (bulan == bulanSekarang && hari >= hariSekarang) {
                return true;
            }
        }
        return false;
    }

    public static boolean periksaJamMendatang(int hari, int bulan, int tahun, int jam, int menit) {
        LocalDateTime waktuSekarang = LocalDateTime.now();
        
        if (tahun != waktuSekarang.getYear() || bulan != waktuSekarang.getMonthValue() || hari != waktuSekarang.getDayOfMonth()) {
            return true; 
        } 
        
        if (jam > waktuSekarang.getHour()) {
            return true;
        } 
        
        if (jam == waktuSekarang.getHour() && menit >= waktuSekarang.getMinute()) {
            return true;
        }

        return false;
    }

    public static void cetakGarisPembatas(String jenisGaris) {
        if (jenisGaris.equals("atas")) {
            System.out.println("╔══════════════════════════════════════════════╗");
        } else if (jenisGaris.equals("tengah")) {
            System.out.println("╠══════════════════════════════════════════════╣");
        } else if (jenisGaris.equals("tipis")) {
            System.out.println("╟──────────────────────────────────────────────╢");
        } else if (jenisGaris.equals("bawah")) {
            System.out.println("╚══════════════════════════════════════════════╝");
        }
    }

    public static void cetakBarisRangkuman(String label, String isi) {
        System.out.printf("║ %-10s : %-31s ║\n", label, isi);
    }

    public static void tampilkanKatalogArmada(ArrayList<Kendaraan> daftarKendaraan) {
        System.out.println("\n╔═══════════════════════════════════════════════════════════╗");
        System.out.println("║                KATALOG ARMADA FILKOM TRAVEL               ║");
        System.out.println("╠═════╦══════════════════════╦═════════════╦════════════════╣");
        System.out.println("║ No  ║ Nama Armada          ║ Kapasitas   ║ Harga Sewa/Jam ║");
        System.out.println("╠═════╬══════════════════════╬═════════════╬════════════════╣");
        for (int i = 0; i < daftarKendaraan.size(); i++) {
            Kendaraan kendaraan = daftarKendaraan.get(i);
            System.out.printf("║ %-3d ║ %-20s ║ %-3d Orang   ║ Rp %-11s ║\n", 
                (i + 1), 
                kendaraan.getNamaKendaraan(), 
                kendaraan.getKapasitas(), 
                String.format("%,.0f", kendaraan.getHargaSewaDasar()));
        }
        System.out.println("╚═════╩══════════════════════╩═════════════╩════════════════╝");
    }

    public static void tampilkanTabelPaketRental(Kendaraan kendaraanDipilih) {
        double hargaPerJam = kendaraanDipilih.getHargaSewaDasar();
        String estimasiPaket6Jam = String.format("%,.0f", hargaPerJam * 6);
        String estimasiPaket12Jam = String.format("%,.0f", hargaPerJam * 12);

        System.out.println("\n╔═══════════════════════════════════════════════════════════╗");
        System.out.println("║                 PILIHAN PAKET DURASI RENTAL               ║");
        System.out.println("╠═════╦════════════════════╦═════════════╦══════════════════╣");
        System.out.println("║ No  ║ Jenis Paket        ║ Durasi      ║ Estimasi Biaya   ║");
        System.out.println("╠═════╬════════════════════╬═════════════╬══════════════════╣");
        System.out.printf("║ %-3s ║ %-18s ║ %-11s ║ Rp %-13s ║\n", "1", "Paket 6 Jam", "6 Jam", estimasiPaket6Jam);
        System.out.printf("║ %-3s ║ %-18s ║ %-11s ║ Rp %-13s ║\n", "2", "Paket 12 Jam", "12 Jam", estimasiPaket12Jam);
        System.out.printf("║ %-3s ║ %-18s ║ %-11s ║ Rp %-13s ║\n", "3", "Paket Custom", "24-72 Jam", String.format("%,.0f", hargaPerJam) + "/Jam");
        System.out.println("╚═════╩════════════════════╩═════════════╩══════════════════╝");
    }
}
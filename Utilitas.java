import java.util.Scanner;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

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
        try {
            DateTimeFormatter formatTanggal = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate.parse(inputTanggal, formatTanggal);
            return true; 
        } catch (DateTimeParseException e) {
            return false; // Gagal dibaca (Format salah atau tanggal tidak logis)
        }
    }

    public static boolean periksaFormatJamValid(String inputJam) {
        try {
            DateTimeFormatter formatJam = DateTimeFormatter.ofPattern("HH.mm");
            LocalTime.parse(inputJam, formatJam);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public static boolean periksaTanggalMendatang(int hari, int bulan, int tahun) {
        LocalDateTime waktuSekarang = LocalDateTime.now();
        int tahunSekarang = waktuSekarang.getYear();
        int bulanSekarang = waktuSekarang.getMonthValue();
        int hariSekarang = waktuSekarang.getDayOfMonth();

        if (tahun > tahunSekarang) return true;
        if (tahun == tahunSekarang) {
            if (bulan > bulanSekarang) return true;
            if (bulan == bulanSekarang && hari >= hariSekarang) return true;
        }
        return false;
    }

    public static boolean periksaJamMendatang(int hari, int bulan, int tahun, int jam, int menit) {
        LocalDateTime waktuSekarang = LocalDateTime.now();
        
        if (tahun != waktuSekarang.getYear() || bulan != waktuSekarang.getMonthValue() || hari != waktuSekarang.getDayOfMonth()) {
            return true; 
        } 
        
        if (jam > waktuSekarang.getHour()) return true;
        if (jam == waktuSekarang.getHour() && menit > waktuSekarang.getMinute()) return true;

        return false;
    }

    public static void cetakGarisPembatas(String jenisGaris) {
        switch(jenisGaris) {
            case "atas":   System.out.println("╔══════════════════════════════════════════════╗"); break;
            case "tengah": System.out.println("╠══════════════════════════════════════════════╣"); break;
            case "tipis":  System.out.println("╟──────────────────────────────────────────────╢"); break;
            case "bawah":  System.out.println("╚══════════════════════════════════════════════╝"); break;
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
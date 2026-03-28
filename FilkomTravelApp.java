import java.util.Scanner;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class FilkomTravelApp {
    private static final int MAKSIMAL_JAM_SEWA = 72;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        ArrayList<Kendaraan> katalogKendaraan = new ArrayList<>();
        katalogKendaraan.add(new Motor("Honda Vario", 2, 25000));
        katalogKendaraan.add(new KendaraanKecil("Honda Brio", 5, 50000));
        katalogKendaraan.add(new KendaraanSedang("Toyota Avanza", 7, 75000));
        katalogKendaraan.add(new KendaraanBesar("Isuzu Elf Long", 16, 150000));

        ArrayList<Pengguna> dataPengguna = new ArrayList<>();
        dataPengguna.add(new Member("Rai", "081234", "nabila@gmail.com", "350701", "svt17"));
        dataPengguna.add(new NonMember("Ilsa", "089876", "350702", "en7"));

        while (true) {
            Pengguna penggunaAktif = null;

            System.out.println("\n=========================================");
            System.out.println("     Selamat Datang di Filkom Travel      ");
            System.out.println("=========================================");
            System.out.println("1. Sign In (Masuk)\n2. Sign Up (Daftar Akun Baru)\n3. Tutup Aplikasi");
            
            int menuAutentikasi = 0;
            while (true) {
                menuAutentikasi = Utilitas.validasiPilihanAngka(input, "Pilih Menu (1-3): ");
                if (menuAutentikasi >= 1 && menuAutentikasi <= 3) break;
                System.out.println("\nError! Pilihan menu salah. Silakan pilih 1, 2, atau 3.");
            }

            if (menuAutentikasi == 3) {
                System.out.println("\nTerima kasih telah menggunakan Filkom Travel App. Sampai jumpa!");
                break;
            }

            if (menuAutentikasi == 1) { 
                System.out.println("\n=== SIGN IN ===");
                System.out.print("Masukkan Nomor Telepon Anda: "); String loginNomorTelepon = input.nextLine();
                System.out.print("Masukkan Password Anda    : "); String loginPassword = input.nextLine();

                for (Pengguna pengguna : dataPengguna) {
                    if (pengguna.getNoTelepon().equals(loginNomorTelepon) && pengguna.cekPassword(loginPassword)) { 
                        penggunaAktif = pengguna; 
                        break; 
                    }
                }
                if (penggunaAktif == null) { 
                    System.out.println("\nError! Nomor Telepon atau Password salah!"); 
                    continue; 
                }
                System.out.println("\nLogin Berhasil! Selamat datang " + penggunaAktif.getNama());
            } else { 
                System.out.println("\n=== SIGN UP ===");
                System.out.print("Nama Lengkap    : "); String namaBaru = input.nextLine();
                
                String nomorTeleponBaru = "";
                while (true) {
                    System.out.print("Nomor Telepon   : "); nomorTeleponBaru = input.nextLine();
                    if (!nomorTeleponBaru.isBlank() && !Utilitas.periksaNoTeleponSudahTerdaftar(dataPengguna, nomorTeleponBaru)) break;
                    System.out.println("\nError! Tidak boleh kosong atau nomor sudah terdaftar di sistem.");
                }
                System.out.print("NIK KTP         : "); String nikKtpBaru = input.nextLine();
                System.out.print("Password        : "); String passwordBaru = input.nextLine();
                
                System.out.print("Daftar sebagai Member? (y/n): "); String pilihanMember = input.nextLine();
                if (pilihanMember.equalsIgnoreCase("y")) {
                    System.out.print("Email           : "); String emailBaru = input.nextLine();
                    penggunaAktif = new Member(namaBaru, nomorTeleponBaru, emailBaru, nikKtpBaru, passwordBaru);
                } else {
                    penggunaAktif = new NonMember(namaBaru, nomorTeleponBaru, nikKtpBaru, passwordBaru);
                }
                dataPengguna.add(penggunaAktif);
                System.out.println("\nPendaftaran Akun Berhasil!");
            }

            boolean sesiAktif = true;
            while (sesiAktif) {
                System.out.println("\n=== MENU UTAMA FILKOM TRAVEL (" + penggunaAktif.getNama() + ") ===");
                System.out.println("1. Penyewaan Baru\n2. Cek Histori Penyewaan\n3. Upgrade ke Member\n4. Log Out (Keluar Akun)\n5. Tutup Aplikasi");
                
                int menuLayanan = 0;
                while (true) {
                    menuLayanan = Utilitas.validasiPilihanAngka(input, "Pilih Menu Layanan (1-5): ");
                    if (menuLayanan >= 1 && menuLayanan <= 5) break;
                    System.out.println("\nError! Pilihan menu salah! Silakan masukkan angka 1-5.");
                }

                if (menuLayanan == 4) { 
                    System.out.println("\nLogout berhasil! Sesi ditutup."); 
                    sesiAktif = false; 
                } else if (menuLayanan == 5) { 
                    System.out.println("\nTerima kasih telah menyewa di Filkom Travel!"); 
                    sesiAktif = false; 
                    return; 
                } else if (menuLayanan == 2) { 
                    penggunaAktif.cekHistoriPenyewaan(); 
                } else if (menuLayanan == 3) {
                    if (penggunaAktif.isMember()) {
                        System.out.println("\nAnda sudah terdaftar sebagai Member!");
                    } else {
                        System.out.print("Masukkan Email Member: "); String emailUpgrade = input.nextLine();
                        penggunaAktif = new Member(penggunaAktif.getNama(), penggunaAktif.getNoTelepon(), emailUpgrade, penggunaAktif.getNikKtp(), penggunaAktif.getPassword());
                        for (int i = 0; i < dataPengguna.size(); i++) { 
                            if (dataPengguna.get(i).getNoTelepon().equals(penggunaAktif.getNoTelepon())) { 
                                dataPengguna.set(i, penggunaAktif); 
                            } 
                        }
                        System.out.println("\nUpgrade Akun ke Member Berhasil!");
                    }
                } else if (menuLayanan == 1) {
                    Utilitas.tampilkanKatalogArmada(katalogKendaraan);
                    int nomorArmadaDipilih = 0;
                    while (true) {
                        nomorArmadaDipilih = Utilitas.validasiPilihanAngka(input, "\nPilih Nomor Armada: ");
                        if (nomorArmadaDipilih >= 1 && nomorArmadaDipilih <= katalogKendaraan.size()) break;
                    }
                    Kendaraan kendaraanDipilih = katalogKendaraan.get(nomorArmadaDipilih - 1);

                    String tanggalAmbil = "", jamAmbil = "";
                    int hariAmbil = 0, bulanAmbil = 0, tahunAmbil = 0, jamWaktuAmbil = 0, menitWaktuAmbil = 0;

                    while (true) {
                        System.out.println("\n=== JADWAL PENGAMBILAN ===");
                        System.out.print("Tanggal (DD-MM-YYYY): "); tanggalAmbil = input.nextLine();
                        System.out.print("Jam (HH.MM)         : "); jamAmbil = input.nextLine();

                        if (Utilitas.periksaFormatTanggalValid(tanggalAmbil) && Utilitas.periksaFormatJamValid(jamAmbil)) {
                            
                            LocalDate objekTanggal = LocalDate.parse(tanggalAmbil, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                            LocalTime objekJam = LocalTime.parse(jamAmbil, DateTimeFormatter.ofPattern("HH.mm"));

                            hariAmbil = objekTanggal.getDayOfMonth();
                            bulanAmbil = objekTanggal.getMonthValue();
                            tahunAmbil = objekTanggal.getYear();
                            
                            jamWaktuAmbil = objekJam.getHour();
                            menitWaktuAmbil = objekJam.getMinute();

                            if (Utilitas.periksaTanggalMendatang(hariAmbil, bulanAmbil, tahunAmbil) && Utilitas.periksaJamMendatang(hariAmbil, bulanAmbil, tahunAmbil, jamWaktuAmbil, menitWaktuAmbil)) {
                                break;
                            } else {
                                System.out.println("\nError! Waktu rental tidak boleh di masa lalu (Masukkan waktu mendatang!).");
                            }
                        } else {
                            System.out.println("\nError! Format penulisan tanggal atau jam salah!");
                        }
                    }

                    int durasiSewa = 0;
                    Utilitas.tampilkanTabelPaketRental(kendaraanDipilih);
                    while (true) {
                        int paketPilihan = Utilitas.validasiPilihanAngka(input, "Pilih Paket (1-3): ");
                        if (paketPilihan == 1) { durasiSewa = 6; break; }
                        if (paketPilihan == 2) { durasiSewa = 12; break; }
                        if (paketPilihan == 3) {
                            durasiSewa = Utilitas.validasiPilihanAngka(input, "Jam Custom (24-72 Jam): ");
                            if (durasiSewa >= 24 && durasiSewa <= MAKSIMAL_JAM_SEWA) break;
                            System.out.println("\nError! Durasi custom wajib antara 24-72 jam.");
                        }
                    }

                    LocalDateTime waktuSelesaiSewa = LocalDateTime.parse(tanggalAmbil + " " + jamAmbil, DateTimeFormatter.ofPattern("dd-MM-yyyy HH.mm")).plusHours(durasiSewa);
                    String tanggalSelesai = waktuSelesaiSewa.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                    String jamSelesai = waktuSelesaiSewa.format(DateTimeFormatter.ofPattern("HH.mm"));

                    boolean menggunakanSupir = false; String nomorSim = "-";
                    if (!kendaraanDipilih.isSupirTersedia()) { 
                        System.out.print("\nMasukkan Nomor SIM C Anda: "); 
                        nomorSim = input.nextLine(); 
                    } else if (kendaraanDipilih.isSupirWajib()) { 
                        menggunakanSupir = true; 
                    } else {
                        while (true) {
                            System.out.print("\nJasa Supir? (ya/tidak): "); 
                            String opsiSupir = input.nextLine();

                            if (opsiSupir.equalsIgnoreCase("ya")) {
                                menggunakanSupir = true;
                                break; 
                            } else if (opsiSupir.equalsIgnoreCase("tidak")) { 
                                System.out.print("Masukkan Nomor SIM A Anda: "); 
                                nomorSim = input.nextLine(); 
                                break;
                            } else {
                                System.out.println("\nError! Pilihan tidak valid! Ketik 'ya' atau 'tidak'.");
                            }
                        }
                    }

                    double biayaKotorSewa = kendaraanDipilih.hitungBiayaSewa(durasiSewa, menggunakanSupir);
                    double potonganDiskonMember = penggunaAktif.hitungPotonganHarga(biayaKotorSewa);

                    System.out.println();
                    Utilitas.cetakGarisPembatas("atas"); 
                    Utilitas.cetakBarisRangkuman("Kendaraan", kendaraanDipilih.getNamaKendaraan()); 
                    Utilitas.cetakBarisRangkuman("Mulai", tanggalAmbil + " (" + jamAmbil + ")");
                    Utilitas.cetakBarisRangkuman("Selesai", tanggalSelesai + " (" + jamSelesai + ")"); 
                    Utilitas.cetakBarisRangkuman("Layanan", menggunakanSupir ? "Pakai Supir" : "Lepas Kunci (SIM: " + nomorSim + ")");
                    Utilitas.cetakGarisPembatas("tengah"); 
                    Utilitas.cetakBarisRangkuman("Harga Sewa", "Rp " + String.format("%,.0f", biayaKotorSewa));
                    if (potonganDiskonMember > 0) Utilitas.cetakBarisRangkuman("Diskon", "-Rp " + String.format("%,.0f", potonganDiskonMember));
                    Utilitas.cetakGarisPembatas("tipis"); 
                    System.out.printf("║ TOTAL ESTIMASI : Rp %-24s ║\n", String.format("%,.0f", biayaKotorSewa - potonganDiskonMember)); 
                    Utilitas.cetakGarisPembatas("bawah");

                    int konfirmasiBayar = 0;
                    while (true) {
                        konfirmasiBayar = Utilitas.validasiPilihanAngka(input, "\n1. Konfirmasi Pemesanan dan Bayar\n2. Batalkan Pemesanan: ");
                        
                        if (konfirmasiBayar == 1 || konfirmasiBayar == 2) {
                            break;
                        } else {
                            System.out.println("\nError! Pilihan tidak valid. Pilih angka 1 atau 2.");
                        }
                    }

                    if (konfirmasiBayar == 1) {
                        Pemesanan pesananBaru = new Pemesanan(penggunaAktif, kendaraanDipilih, durasiSewa, tanggalAmbil, jamAmbil, tanggalSelesai, jamSelesai, menggunakanSupir, nomorSim);
                        pesananBaru.cetakStruk(); 
                        penggunaAktif.tambahRiwayat(pesananBaru);
                    } else {
                        System.out.println("\nPemesanan dibatalkan.");
                    }
                }
            }
        }
        input.close();
    }
}
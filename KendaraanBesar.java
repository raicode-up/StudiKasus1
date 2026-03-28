public class KendaraanBesar extends Kendaraan {
    private static final double TARIF_SUPIR_HARIAN = 600000;
    private static final double TARIF_SUPIR_SETENGAH_HARI = 350000;

    public KendaraanBesar(String namaKendaraan, int kapasitas, double hargaSewaDasar) {
        super(namaKendaraan, kapasitas, hargaSewaDasar);
    }

    @Override
    public double hitungBiayaSewa(int lamaSewa, boolean pakaiSupir) {
        if (lamaSewa <= 0) {
            return 0;
        }

        double totalMobil = getHargaSewaDasar() * lamaSewa;

        int hari = lamaSewa / 24;
        int sisa = lamaSewa % 24;

        double totalSupir = hari * TARIF_SUPIR_HARIAN;
        if (sisa > 0 && sisa <= 12) totalSupir += TARIF_SUPIR_SETENGAH_HARI;
        else if (sisa > 12) totalSupir += TARIF_SUPIR_HARIAN;

        return totalMobil + totalSupir;
    }

    @Override
    public boolean isSupirWajib() {
        return true;
    }
}
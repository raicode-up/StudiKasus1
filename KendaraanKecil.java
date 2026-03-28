public class KendaraanKecil extends Kendaraan {
    private static final double TARIF_SUPIR_HARIAN = 200000;
    private static final double TARIF_SUPIR_SETENGAH_HARI = 1500000;

    public KendaraanKecil(String namaKendaraan, int kapasitas, double hargaSewaDasar) {
        super(namaKendaraan, kapasitas, hargaSewaDasar);
    }

    @Override
    public double hitungBiayaSewa(int lamaSewa, boolean pakaiSupir) {
        if (lamaSewa <= 0) {
            return 0;
        }

        double totalMobil = getHargaSewaDasar() * lamaSewa;
        double totalSupir = 0;

        if (pakaiSupir) {
            int hari = lamaSewa / 24;
            int sisa = lamaSewa % 24;

            totalSupir = hari * TARIF_SUPIR_HARIAN;

            if (sisa > 0 && sisa <= 12) totalSupir += TARIF_SUPIR_SETENGAH_HARI;
            else if (sisa > 12) totalSupir += TARIF_SUPIR_HARIAN;
        }
        return totalMobil + totalSupir;
    }
}
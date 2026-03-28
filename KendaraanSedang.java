
public class KendaraanSedang extends Kendaraan {
    private static final double TARIF_SUPIR_HARIAN = 400000;
    private static final double TARIF_SUPIR_1_SAMPAI_6_JAM = 120000;
    private static final double TARIF_SUPIR_7_SAMPAI_12_JAM = 200000;

    public KendaraanSedang(String namaKendaraan, int kapasitas, double hargaSewaDasar) {
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

            if (sisa > 0 && sisa <= 6) totalSupir += TARIF_SUPIR_1_SAMPAI_6_JAM;
            else if (sisa > 6 && sisa <= 12) totalSupir += TARIF_SUPIR_7_SAMPAI_12_JAM;
            else if (sisa > 12) totalSupir += TARIF_SUPIR_HARIAN;
        }
        return totalMobil + totalSupir;
    }
}
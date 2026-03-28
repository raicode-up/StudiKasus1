public class Motor extends Kendaraan {

    public Motor(String namaKendaraan, int kapasitas, double hargaSewaDasar) {
        super(namaKendaraan, kapasitas, hargaSewaDasar);
    }

    @Override
    public double hitungBiayaSewa(int lamaSewa, boolean pakaiSupir) {
        return getHargaSewaDasar() * lamaSewa; 
    }

    @Override
    public boolean isSupirTersedia() {
        return false;
    }
}
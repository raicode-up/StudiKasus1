public abstract class Kendaraan {
    private String namaKendaraan;
    private int kapasitas;
    private double hargaSewaDasar;

    public Kendaraan(String namaKendaraan, int kapasitas, double hargaSewaDasar) {
        this.namaKendaraan = namaKendaraan;
        this.kapasitas = kapasitas;
        this.hargaSewaDasar = hargaSewaDasar;
    }

    public String getNamaKendaraan() {
        return namaKendaraan;
    }

    public int getKapasitas() {
        return kapasitas;
    }

    public double getHargaSewaDasar() {
        return hargaSewaDasar;
    }

    public abstract double hitungBiayaSewa(int lamaSewa, boolean pakaiSupir);

    public boolean isSupirTersedia() {
        return true; 
    }
    public boolean isSupirWajib() {
        return false; 
    }
}
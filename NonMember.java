public class NonMember extends Pengguna {

    public NonMember(String nama, String noTelepon, String nikKtp, String password) {
        super(nama, noTelepon, nikKtp, password); 
    }

    @Override
    public double hitungPotonganHarga(double totalSewa) {
        return 0; // Gak dapet diskon
    }

    @Override
    public String getStatusTipe() { return "Non-Member"; }
    
    @Override
    public String getDetailMember() { return "-"; }

    @Override
    public boolean isMember() { return false; }
}
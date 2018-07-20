public class Agricultor extends Thread {
    private int id;
    private int vendido;
    private Tienda t;

    public Agricultor(int id, Tienda t) {
        this.id = id;
        this.t = t;
        this.vendido = 0;
    }

    @Override
    public void run() {
        int kilos;
        boolean venta;
        for (int i = 0; i < 5; i++) {
            try {
                Thread.sleep(ValoresSimulacion.tiempoCosecha());
            } catch (Exception e) {
            }
            kilos = ValoresSimulacion.cantidadCosechada();
            venta = t.vender(this.id, kilos);
            if (venta) {
                vendido += kilos;
            }


        }


    }


    public int vendido() {
        // RETORNAR nº de Kg
        return vendido;
    }
}

public class Cliente extends Thread {
    private int id;
    private int comprado;
    private Tienda t;

    public Cliente(int id, Tienda t) {
        this.id = id;
        this.comprado = 0;
        this.t = t;
    }

    @Override
    public void run() {
        int kilos = ValoresSimulacion.cantidadAComprar();
        boolean compra = t.comprar(this.id, kilos);

        while (compra) {
            comprado = comprado + kilos;
            try {
                Thread.sleep(ValoresSimulacion.tiempoConsumoKilo() * kilos);
            } catch (Exception e) {
            }

            kilos = ValoresSimulacion.cantidadAComprar();
            compra = t.comprar(this.id, kilos);
        }

    }


    public int comprado() {
        return comprado;
    }
}

public class Tienda {

    private int capacidad;
    private int stock;

    public Tienda(int capacidad) {
        this.capacidad = capacidad;
        this.stock = 0;
    }

    public synchronized boolean vender(int idAgricultor, int sacos) { //El agricultor intenta vender N sacos de 20kg
        System.out.println("El Agricultor " + idAgricultor + " quiere vendernos " + sacos + " sacos de 20kg");

        if (stock+(sacos * 20) > capacidad) {
            System.out.println("El Agricultor " + idAgricultor + " no puede vendernos " + sacos + " sacos de 20kg. Stock = " + this.stock);
            try {
                wait(ValoresSimulacion.esperaVenta());
            } catch (Exception e) {
            }
        }

        if (stock+(sacos * 20) > capacidad) return false;

        stock = stock + (sacos * 20);
        System.out.println("El Agricultor " + idAgricultor + " nos vende " + sacos + " sacos de 20kg. Stock = " + this.stock);
        notifyAll();
        return true;

    }

    public synchronized boolean comprar(int idCliente, int kilos) {//El cliente intenta comprar N kilos
        System.out.println("El Cliente "+idCliente+" quiere compranos "+kilos+" kilos de comida");
        if(kilos>stock){
            System.out.println("El Cliente "+idCliente+" no puede compranos "+kilos+" kilos de comida.Stock = "+this.stock);
            try{
                wait(ValoresSimulacion.esperaCompra());
            }catch(Exception e){}
        }

        if(kilos>stock) return false;

        stock=stock-kilos;
        System.out.println("El Cliente "+idCliente+" nos compra "+kilos+" kilos de comida.Stock = "+this.stock);
        notifyAll();
        return true;

    }

    public int stock() { //kilos en stock
        return stock;
    }
}

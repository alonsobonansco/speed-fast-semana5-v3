package cl.duoc.speedfast.model;

import cl.duoc.speedfast.service.ZonaDeCarga;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

// Implementa Runnable
public class Repartidor implements Runnable {
    private final String nombreRepartidor;
    private final ZonaDeCarga zonaDeCarga;

    public Repartidor(String nombreRepartidor, ZonaDeCarga zonaDeCarga) {
        this.nombreRepartidor = nombreRepartidor;
        this.zonaDeCarga = zonaDeCarga;
    }

    @Override
    public void run() {
        while (true) {
            Pedido pedido = zonaDeCarga.retirarPedido();

            if (pedido == null) {
                break;
            }

            try {
                int tiempoPausas = 1000 + ThreadLocalRandom.current().nextInt(2500);

                TimeUnit.MILLISECONDS.sleep(tiempoPausas);

                System.out.println("Retirando pedido #" + pedido.getIdPedido() + "... [Repartidor " +
                        nombreRepartidor + "]");
                TimeUnit.MILLISECONDS.sleep(tiempoPausas);

                pedido.setEstadoPedido(EstadoPedido.EN_REPARTO);
                System.out.println("Estado pedido #" + pedido.getIdPedido() +
                        ": " + pedido.getEstadoPedido());

                TimeUnit.MILLISECONDS.sleep(tiempoPausas);

                System.out.println("Entregando pedido #" + pedido.getIdPedido() + "...");

                TimeUnit.MILLISECONDS.sleep(tiempoPausas);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Entrega interrumpida");
                break;
            }

            pedido.setEstadoPedido(EstadoPedido.ENTREGADO);
            System.out.println("Estado pedido #" +
                    pedido.getIdPedido() + ": " + pedido.getEstadoPedido() + " por [Repartidor " + nombreRepartidor
                    + "]");
        }
    }
}

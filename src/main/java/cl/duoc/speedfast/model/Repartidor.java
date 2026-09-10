package cl.duoc.speedfast.model;

import cl.duoc.speedfast.service.ZonaDeCarga;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

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
                TimeUnit.MILLISECONDS.sleep(calcularTiempoAleatorio(1000, 1000));

                System.out.println(
                        "Retirando pedido #" + pedido.getIdPedido() + "... [Repartidor " + nombreRepartidor + "]"
                );

                TimeUnit.MILLISECONDS.sleep(calcularTiempoAleatorio(1000, 1500));

                pedido.setEstadoPedido(EstadoPedido.EN_REPARTO);
                System.out.println(
                        "Estado pedido #" + pedido.getIdPedido() + ": " + pedido.getEstadoPedido()
                );

                TimeUnit.MILLISECONDS.sleep(calcularTiempoAleatorio(1500, 1000));

                System.out.println("Entregando pedido #" + pedido.getIdPedido() + "...");

                TimeUnit.MILLISECONDS.sleep(calcularTiempoAleatorio(500, 500));

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Entrega interrumpida");
                break;
            }

            pedido.setEstadoPedido(EstadoPedido.ENTREGADO);
            System.out.println(
                    "Estado pedido #" + pedido.getIdPedido() + ": "
                            + pedido.getEstadoPedido() + " por [Repartidor " + nombreRepartidor + "]"
            );
        }
    }

    private int calcularTiempoAleatorio(int baseMilisegundos, int rangoAleatorio) {
        return baseMilisegundos + ThreadLocalRandom.current().nextInt(rangoAleatorio);
    }
}

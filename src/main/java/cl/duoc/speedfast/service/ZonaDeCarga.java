package cl.duoc.speedfast.service;

import cl.duoc.speedfast.model.Pedido;

import java.util.Objects;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ZonaDeCarga {
    private final BlockingQueue<Pedido> pedidosPendientes = new LinkedBlockingQueue<>();

    // El uso de 'synchronized' no es necesario 'en este caso' porque BlockingQueue es internamente segura para hilos
    public void agregarPedido(Pedido pedido) {
        pedidosPendientes.add(
                Objects.requireNonNull(
                        pedido,
                        "No se puede agregar un pedido nulo a la zona de carga.")
        );

        System.out.println("Pedido #" + pedido.getIdPedido() + " agregado. Destino: " + pedido.getDireccionEntrega());
    }

    public Pedido retirarPedido() {
        return pedidosPendientes.poll();
    }
}

package cl.duoc.speedfast.service;

import cl.duoc.speedfast.model.Pedido;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ZonaDeCarga {
    private final BlockingQueue<Pedido> pedidosPendientes = new LinkedBlockingQueue<>();

    // El uso de 'synchronized' no es necesario porque BlockingQueue es internamente segura para hilos
    public void agregarPedido(Pedido pedido) {
        pedidosPendientes.add(pedido);
        System.out.println("Pedido #" + pedido.getIdPedido() + " agregado. Destino: " + pedido.getDireccionEntrega());
    }

    public Pedido retirarPedido() {
        return pedidosPendientes.poll();
    }
}

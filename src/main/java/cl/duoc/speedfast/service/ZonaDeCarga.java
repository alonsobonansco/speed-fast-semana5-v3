package cl.duoc.speedfast.service;

import cl.duoc.speedfast.model.Pedido;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ZonaDeCarga {
    private final BlockingQueue<Pedido> pedidosPendientes = new LinkedBlockingQueue<>();

    // El uso de 'synchronized' no es necesario
    public synchronized void agregarPedido(Pedido pedido) {
        pedidosPendientes.add(pedido);
    }

    public synchronized Pedido retirarPedido() {
        return pedidosPendientes.poll();
    }
}

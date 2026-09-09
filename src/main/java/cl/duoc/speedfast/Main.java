package cl.duoc.speedfast;

import cl.duoc.speedfast.model.Pedido;
import cl.duoc.speedfast.model.Repartidor;
import cl.duoc.speedfast.service.ZonaDeCarga;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        ZonaDeCarga zonaDeCarga = new ZonaDeCarga();
        System.out.println("[Zona de carga inicializada]");
        System.out.println();

        zonaDeCarga.agregarPedido(new Pedido(1, "calle 123"));
        zonaDeCarga.agregarPedido(new Pedido(2, "avenida 222"));
        zonaDeCarga.agregarPedido(new Pedido(3, "pasaje 555"));
        zonaDeCarga.agregarPedido(new Pedido(4, "calle 678"));
        zonaDeCarga.agregarPedido(new Pedido(5, "avenida 777"));

        System.out.println();

        List<Repartidor> listaRepartidores = List.of(
                new Repartidor("Luciano", zonaDeCarga),
                new Repartidor("María", zonaDeCarga),
                new Repartidor("José Luis", zonaDeCarga));

        try (ExecutorService executorService = Executors.newFixedThreadPool(listaRepartidores.size())) {
            listaRepartidores.forEach(executorService::execute);
            executorService.shutdown();
        }

        System.out.println();
        System.out.println("[Zona de carga vacía]");
        System.out.println("Todos los pedidos han sido entregados correctamente");
    }
}

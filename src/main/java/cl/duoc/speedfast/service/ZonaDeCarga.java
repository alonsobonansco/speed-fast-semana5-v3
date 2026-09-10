package cl.duoc.speedfast.service;

import cl.duoc.speedfast.model.Pedido;

import java.util.Objects;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Recurso compartido (Shared Resource) encargado de centralizar y administrar la
 * piscina común de pedidos pendientes en el sistema SpeedFast.
 * <p>
 * Esta clase actúa como el componente "Buffer" central en la arquitectura del patrón
 * Productor-Consumidor. Utiliza estructuras concurrentes de alta eficiencia para mitigar
 * condiciones de carrera (Race Conditions) y garantizar la exclusión mutua de manera nativa.
 *
 * @see BlockingQueue
 * @see LinkedBlockingQueue
 * @see Pedido
 */
public class ZonaDeCarga {
    private final BlockingQueue<Pedido> pedidosPendientes = new LinkedBlockingQueue<>();

    /**
     * Registra un nuevo pedido en la piscina común de distribución de forma segura y atómica.
     * <p>
     * NOTA ARQUITECTÓNICA: Este método no requiere la palabra clave 'synchronized' debido a que el método
     * '.add()' de la interfaz BlockingQueue gestiona internamente los cerrojos de concurrencia necesarios.
     *
     * @param pedido El objeto Pedido que ingresa al flujo logístico.
     * @throws NullPointerException Si el pedido proporcionado es nulo.
     */
    public void agregarPedido(Pedido pedido) {
        pedidosPendientes.add(
                Objects.requireNonNull(
                        pedido,
                        "No se puede agregar un pedido nulo a la zona de carga.")
        );

        System.out.println("Pedido #" + pedido.getIdPedido() + " agregado. Destino: " + pedido.getDireccionEntrega());
    }

    /**
     * Extrae y remueve el siguiente pedido disponible de la zona de carga siguiendo el orden de llegada.
     * <p>
     * El uso de '.poll()' realiza la verificación de existencia y la remoción en una única operación ATÓMICA
     * indivisible. Esto garantiza al 100% que ningún par de repartidores concurrentes puedan retirar el mismo
     * paquete al mismo tiempo, cumpliendo el requerimiento de control de doble retiro sin overhead de bloqueo.
     *
     * @return El {@link Pedido} extraído con éxito listo para ser confinado al hilo repartidor,
     * o 'null' si la bodega común ya se encuentra vacía.
     */
    public Pedido retirarPedido() {
        return pedidosPendientes.poll();
    }

    /**
     * Evalúa el estado actual de ocupación de la bodega o zona de carga.
     * Se utiliza al cierre del sistema para auditar y validar de forma defensiva que no hayan
     * quedado tareas huérfanas en el recurso compartido post-ejecución de los hilos.
     *
     * @return true si la cola de pedidos pendientes está completamente vacía;
     * false si aún quedan paquetes por procesar.
     */
    public boolean estaVacia() {
        return pedidosPendientes.isEmpty();
    }
}

package cl.duoc.speedfast.service;

import cl.duoc.speedfast.model.EstadoPedido;
import cl.duoc.speedfast.model.Pedido;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * Representa un hilo de trabajo (Worker Thread) encargado del procesamiento logístico de los pedidos.
 * Implementa la interfaz {@link Runnable} para permitir su ejecución en hilos independientes y paralelos.
 * <p>
 * Los objetos de esta clase interactúan de manera competitiva sobre un recurso compartido común,
 * extrayendo tareas de forma segura para mitigar condiciones de carrera y dobles asignaciones.
 *
 * @see Runnable
 * @see ZonaDeCarga
 */
public class Repartidor implements Runnable {
    private final String nombreRepartidor;
    private final ZonaDeCarga zonaDeCarga;

    /**
     * Construye una nueva instancia de Repartidor aplicando programación defensiva (Fail-Fast).
     * Garantiza que el hilo no inicie su ejecución con dependencias corruptas o nulas.
     *
     * @param nombreRepartidor El nombre del transportista.
     * @param zonaDeCarga      Instancia del recurso compartido thread-safe.
     * @throws IllegalArgumentException Si el nombre es inválido o si la zona de carga es nula.
     */
    public Repartidor(String nombreRepartidor, ZonaDeCarga zonaDeCarga) {
        if (nombreRepartidor == null || nombreRepartidor.isBlank()) {
            throw new IllegalArgumentException("El nombre del repartidor no puede estar vacío.");
        }

        if (zonaDeCarga == null) {
            throw new IllegalArgumentException("El repartidor debe tener asignada una zona de carga válida.");
        }

        this.nombreRepartidor = nombreRepartidor;
        this.zonaDeCarga = zonaDeCarga;
    }

    /**
     * Ciclo de vida principal del hilo trabajador.
     * Ejecuta un bucle infinito que consume de forma continua los pedidos de la {@link ZonaDeCarga}.
     * <p>
     * Cada iteración simula un flujo logístico real dividido en fases (Carga, Transporte, Entrega)
     * utilizando pausas dinámicas. El bucle se rompe de forma limpia cuando la cola común se vacía
     * o si el hilo recibe una señal de interrupción externa.
     */
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

    /**
     * Método auxiliar privado encargado de calcular tiempos dinámicos de simulación.
     * Utiliza {@link ThreadLocalRandom} para entornos multihilo, minimizando la contención
     * entre hilos en comparación con un generador global de números aleatorios.
     *
     * @param baseMilisegundos Cantidad mínima de tiempo fijo que durará la pausa.
     * @param rangoAleatorio   Límite superior del rango variable que se sumará a la base.
     * @return El tiempo total calculado en milisegundos listo para ser consumido por un sleep.
     */
    private int calcularTiempoAleatorio(int baseMilisegundos, int rangoAleatorio) {
        return baseMilisegundos + ThreadLocalRandom.current().nextInt(rangoAleatorio);
    }
}

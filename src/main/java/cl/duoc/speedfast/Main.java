package cl.duoc.speedfast;

import cl.duoc.speedfast.model.Pedido;
import cl.duoc.speedfast.service.Repartidor;
import cl.duoc.speedfast.service.ZonaDeCarga;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Punto de entrada principal (Entry Point) y director de orquesta del sistema SpeedFast.
 * Coordina de forma estructurada las etapas de inicialización de la infraestructura,
 * carga secuencial de datos de negocio en el buffer común, activación del motor de
 * hilos concurrentes y validación defensiva del cierre del proceso.
 * <p>
 * Además, actúa como la primera línea de defensa de la aplicación al implementar
 * políticas globales de seguridad de datos ante fallos.
 *
 * @see ExecutorService
 * @see Executors
 * @see ZonaDeCarga
 */
public class Main {
    /**
     * Inicializa y ejecuta la simulación logística concurrente.
     * <p>
     * El flujo de este método realiza las siguientes operaciones críticas:
     * <ol>
     *   <li> Configura un escudo global de ciberseguridad mediante un Uncaught Exception Handler
     *    para mitigar fugas de información. </li>
     *   <li> Instancia la infraestructura común de la ZonaDeCarga.</li>
     *   <li> Pobla la cola de tareas con modelos de datos válidos.</li>
     *   <li> Administra el ciclo de vida de los hilos trabajadores (Workers) utilizando un
     *    pool de tamaño fijo gestionado por un bloque try-with-resources.</li>
     *   <li> Realiza una auditoría final sobre el estado del buffer antes de confirmar la entrega.</li>
     * </ol>
     *
     * @param args Argumentos de la línea de comandos (no utilizados en esta simulación).
     */
    public static void main(String[] args) {
        /*
         * Captura cualquier excepción no controlada (Unchecked Exception) en cualquier hilo del sistema.
         * Mitiga el riesgo de 'Information Disclosure' (Vulnerabilidad OWASP) al interceptar el error
         * y ocultar el Stack Trace completo al usuario final, mostrando únicamente un mensaje semántico
         * sanitizado y cerrando la máquina virtual con un código de salida '1' (Failure).
         */
        Thread.setDefaultUncaughtExceptionHandler((thread, throwable) -> {
            System.err.println("Ha ocurrido un problema interno durante el proceso.");
            System.err.println("Motivo: " + throwable.getMessage());

            System.exit(1);
        });

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

        if (zonaDeCarga.estaVacia()) {
            System.out.println("[Zona de carga vacía]");
            System.out.println("Todos los pedidos han sido entregados correctamente");
        } else {
            System.out.println("Proceso terminado con pedidos pendientes.");
        }
    }
}

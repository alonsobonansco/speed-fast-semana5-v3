package cl.duoc.speedfast.model;

/**
 * Enumeración que define los estados válidos para el ciclo de vida de un pedido.
 * El uso de este Enum garantiza la seguridad de tipos (Type-Safety) y evita
 * errores de consistencia semántica o de tipeo en el flujo logístico del sistema.
 */
public enum EstadoPedido {
    /**
     * Estado inicial del pedido.
     * Indica que el pedido ha sido registrado con éxito en el sistema y se
     * encuentra en la zona de carga a la espera de ser reclamado por un repartidor.
     */
    PENDIENTE,

    /**
     * Estado de tránsito.
     * Indica que un repartidor ha retirado el paquete de la zona de carga de forma
     * exclusiva y se encuentra actualmente transportándolo hacia la dirección de destino.
     */
    EN_REPARTO,

    /**
     * Estado final del ciclo de vida.
     * Indica que el repartidor llegó exitosamente al destino y el paquete ha sido
     * entregado de forma conforme al cliente, finalizando su flujo en el sistema.
     */
    ENTREGADO
}

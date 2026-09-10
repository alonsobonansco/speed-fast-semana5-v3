package cl.duoc.speedfast.model;

import java.util.Objects;

/**
 * Representa un pedido dentro del sistema de distribución SpeedFast.
 */
public class Pedido {
    private final int idPedido;
    private String direccionEntrega;
    private EstadoPedido estadoPedido;

    /**
     * Construye una nueva instancia de un Pedido con un estado inicial PENDIENTE.
     * Aplica programación defensiva para asegurar que el ID sea válido antes de inicializar.
     *
     * @param idPedido         El identificador numérico único del pedido.
     * @param direccionEntrega La ubicación física de destino.
     * @throws IllegalArgumentException Si el idPedido es menor o igual a cero, o si la dirección es inválida.
     */
    public Pedido(int idPedido, String direccionEntrega) {
        if (idPedido <= 0) {
            throw new IllegalArgumentException("El ID del pedido debe ser válido.");
        }

        this.idPedido = idPedido;
        setDireccionEntrega(direccionEntrega);
        this.estadoPedido = EstadoPedido.PENDIENTE;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public String getDireccionEntrega() {
        return direccionEntrega;
    }

    public EstadoPedido getEstadoPedido() {
        return estadoPedido;
    }

    public void setDireccionEntrega(String direccionEntrega) {
        if (direccionEntrega == null || direccionEntrega.isBlank()) {
            throw new IllegalArgumentException("La dirección debe ser válida.");
        }

        this.direccionEntrega = direccionEntrega;
    }

    /**
     * Actualiza el estado logístico del pedido de forma segura.
     * Utiliza seguridad contra nulos para evitar la corrupción del estado del objeto.
     *
     * @param estadoPedido El nuevo estado enumerado a asignar.
     * @throws NullPointerException Si el estado proporcionado es nulo.
     */
    public void setEstadoPedido(EstadoPedido estadoPedido) {
        this.estadoPedido = Objects.requireNonNull(
                estadoPedido,
                "El estado del pedido no puede ser nulo."
        );
    }

    @Override
    public String toString() {
        return "ID Pedido: #" + idPedido + " | Dirección de Entrega: " + direccionEntrega +
                " | Estado Pedido: " + estadoPedido;
    }
}

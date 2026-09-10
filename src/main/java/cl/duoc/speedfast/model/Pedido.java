package cl.duoc.speedfast.model;

import java.util.Objects;

public class Pedido {
    private final int idPedido;
    private String direccionEntrega;
    private EstadoPedido estadoPedido;

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

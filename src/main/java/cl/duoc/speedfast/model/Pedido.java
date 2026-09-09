package cl.duoc.speedfast.model;

public class Pedido {
    private int idPedido;
    private String direccionEntrega;
    private EstadoPedido estadoPedido;

    public Pedido(int idPedido, String direccionEntrega) {
        this.idPedido = idPedido;
        this.direccionEntrega = direccionEntrega;
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
        this.direccionEntrega = direccionEntrega;
    }

    public void setEstadoPedido(EstadoPedido estadoPedido) {
        this.estadoPedido = estadoPedido;
    }

    @Override
    public String toString() {
        return "ID Pedido: #" + idPedido + " | Dirección de Entrega: " + direccionEntrega +
                " | Estado Pedido: " + estadoPedido;
    }
}

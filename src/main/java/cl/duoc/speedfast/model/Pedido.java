package cl.duoc.speedfast.model;

public class Pedido {
    private int idPedido;
    private String direccionEntrega;
    private EstadoPedido estadoPedido;

    public Pedido(int idPedido, String direccionEntrega, EstadoPedido estadoPedido) {
        this.idPedido = idPedido;
        this.direccionEntrega = direccionEntrega;
        this.estadoPedido = estadoPedido;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public String getDireccionEntrega() {
        return direccionEntrega;
    }

    /*
        getEstadoPedido?
     */

    public EstadoPedido getEstadoPedido() {
        return estadoPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
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

    /*
    setEstado(String nuevoEstado)
     */

    public void setEstado(String nuevoEstado) {
        System.out.println("\"Actualizar el nuevo estado.\"");
    }


}

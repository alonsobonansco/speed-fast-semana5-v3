package cl.duoc.speedfast.model;

import cl.duoc.speedfast.service.ZonaDeCarga;

// Implementa Runnable
public class Repartidor {
    private String nombreRepartidor;
    private ZonaDeCarga zonaDeCarga;

    public Repartidor(String nombreRepartidor, ZonaDeCarga zonaDeCarga) {
        this.nombreRepartidor = nombreRepartidor;
        this.zonaDeCarga = zonaDeCarga;
    }

    /*
        getters o setters?
     */

    /*
    @Override
    run →
        retirar un pedido
        cambiar estado a EN_REPARTO y mostrar un mensaje
        simular entrega con Thread.sleep
        cambiar estado a ENTREGADO y mostar un mensaje final
     */


}

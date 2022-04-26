package bo.edu.ucb.ingsoft.deliverybot.delivery.dto;

public class PlateInOrderDto {
    private PlateDto plato;
    private int cantidad;

    public PlateInOrderDto(PlateDto plato, int cantidad) {
        this.plato = plato;
        this.cantidad = cantidad;
    }

    public PlateDto getPlato() {
        return plato;
    }

    public void setPlato(PlateDto plato) {
        this.plato = plato;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "Nombre: " + plato.getNombre() + '\n' +
                "Precio: " + plato.getPrecio() +'\n' +
                "Descripción: " + plato.getDescripcion() + '\n'+
                "Cantidad: "+ cantidad +"\n";

    }
}

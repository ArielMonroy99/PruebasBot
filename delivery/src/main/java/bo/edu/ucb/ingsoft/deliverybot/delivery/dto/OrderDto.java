package bo.edu.ucb.ingsoft.deliverybot.delivery.dto;

import java.util.List;

public class OrderDto {
    private int id;
    private List<PlateInOrderDto> listaPlatos;
    private Double total;
    private String fecha;

    public OrderDto(List<PlateInOrderDto> listaPlatos, Double total, String fecha) {
        this.listaPlatos = listaPlatos;
        this.total = total;
        this.fecha = fecha;
    }

    public List<PlateInOrderDto> getListaPlatos() {
        return listaPlatos;
    }

    public void setListaPlatos(List<PlateInOrderDto> listaPlatos) {
        this.listaPlatos = listaPlatos;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        int c = 1;
        StringBuffer sb = new StringBuffer();
        for(PlateInOrderDto plate:listaPlatos){
            sb.append("Nro :").append(c).append("\n");
            sb.append(plate.toString());
            c++;
        }

        return "Lista de Platos:\n" + sb +"\n"+
                "Total: " + total +"\n"+
                "Fecha: " + fecha ;
    }
    public String toString2() {
        StringBuffer sb = new StringBuffer();
        for(PlateInOrderDto plate:listaPlatos){
            sb.append("-").append(plate.getPlato().getNombre()).append(" x ").append(plate.getCantidad()).append("\n");
        }
        return "Lista de Platos:\n" + sb +"\n"+
                "Total: " + total +"\n"+
                "Fecha: " + fecha ;
    }
}

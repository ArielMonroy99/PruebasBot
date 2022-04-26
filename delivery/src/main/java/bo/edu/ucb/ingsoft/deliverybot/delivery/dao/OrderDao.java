package bo.edu.ucb.ingsoft.deliverybot.delivery.dao;

import bo.edu.ucb.ingsoft.deliverybot.delivery.dto.OrderDto;
import bo.edu.ucb.ingsoft.deliverybot.delivery.dto.PlateDto;
import bo.edu.ucb.ingsoft.deliverybot.delivery.dto.PlateInOrderDto;

import java.util.ArrayList;
import java.util.List;

public class OrderDao {

    public OrderDto findLastOrder(Long chatId){
        List<PlateInOrderDto> list = new ArrayList<PlateInOrderDto>();
        list.add(new PlateInOrderDto(new PlateDto(1,"Sardinas al vapor",12.5,"platos de sardinas al vapor","https://dam.cocinafacil.com.mx/wp-content/uploads/2020/12/lasa%C3%B1a-receta.jpg"),1));
        list.add(new PlateInOrderDto(new PlateDto(2,"Hamburguejas",30.5,"hamburguesas de lentejas","https://dam.cocinafacil.com.mx/wp-content/uploads/2020/12/lasa%C3%B1a-receta.jpg"),1));
        list.add(new PlateInOrderDto(new PlateDto(3,"Lasaña de pollo",50.5,"Lasaña con salsa roja y trositos de pollo","https://dam.cocinafacil.com.mx/wp-content/uploads/2020/12/lasa%C3%B1a-receta.jpg"),1));

        OrderDto result = new OrderDto(list,93.5,"4-17-2020");
        return result;
    }
    public List<OrderDto> findLas10Orders(Long chatId){

        List<OrderDto> result = new ArrayList<OrderDto>();
        List<PlateInOrderDto> list = new ArrayList<PlateInOrderDto>();
        list.add(new PlateInOrderDto(new PlateDto(1,"Sardinas al vapor",12.5,"platos de sardinas al vapor","https://dam.cocinafacil.com.mx/wp-content/uploads/2020/12/lasa%C3%B1a-receta.jpg"),5));
        list.add(new PlateInOrderDto(new PlateDto(2,"Hamburguejas",30.5,"hamburguesas de lentejas","https://dam.cocinafacil.com.mx/wp-content/uploads/2020/12/lasa%C3%B1a-receta.jpg"),3));
        list.add(new PlateInOrderDto(new PlateDto(3,"Lasaña de pollo",50.5,"Lasaña con salsa roja y trositos de pollo","https://dam.cocinafacil.com.mx/wp-content/uploads/2020/12/lasa%C3%B1a-receta.jpg"),1));
        List<PlateInOrderDto> list1 = new ArrayList<PlateInOrderDto>();

        list1.add(new PlateInOrderDto(new PlateDto(1,"Papas fritas",15.5,"Como las papas normales pero fritas","https://dam.cocinafacil.com.mx/wp-content/uploads/2020/12/lasa%C3%B1a-receta.jpg"),4));
        list1.add(new PlateInOrderDto(new PlateDto(2,"Quesadilla",20.5,"Quesadillas con queso","https://dam.cocinafacil.com.mx/wp-content/uploads/2020/12/lasa%C3%B1a-receta.jpg"),2));
        list1.add(new PlateInOrderDto(new PlateDto(3,"Pollo al horno",50.5,"Pollo hecho al horno","https://dam.cocinafacil.com.mx/wp-content/uploads/2020/12/lasa%C3%B1a-receta.jpg"),6));

        List<PlateInOrderDto> list2 = new ArrayList<PlateInOrderDto>();
        list2.add(new PlateInOrderDto(new PlateDto(1,"Pan de Muerto",12.5,"Pan con cenizas","https://dam.cocinafacil.com.mx/wp-content/uploads/2020/12/lasa%C3%B1a-receta.jpg"),3));
        list2.add(new PlateInOrderDto(new PlateDto(2,"Locro de Zapallos",30.5,"Qué es locro?","https://dam.cocinafacil.com.mx/wp-content/uploads/2020/12/lasa%C3%B1a-receta.jpg"),1));
        list2.add(new PlateInOrderDto(new PlateDto(3,"Super sopa de Mani",50.5,"Deliciosa sopa de maní kriptoniana","https://dam.cocinafacil.com.mx/wp-content/uploads/2020/12/lasa%C3%B1a-receta.jpg"),5));

        result.add(new OrderDto(list,204.5,"4-17-2020") );
        result.add(new OrderDto(list1,406.0,"4-10-2020") );
        result.add(new OrderDto(list2,320.5,"11-2-2019") );

        return result;
    }
}

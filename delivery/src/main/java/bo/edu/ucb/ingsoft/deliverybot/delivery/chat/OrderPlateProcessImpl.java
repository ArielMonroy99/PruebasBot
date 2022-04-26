package bo.edu.ucb.ingsoft.deliverybot.delivery.chat;

import bo.edu.ucb.ingsoft.deliverybot.delivery.bl.PlateBl;
import bo.edu.ucb.ingsoft.deliverybot.delivery.dto.PlateDto;
import org.jvnet.hk2.annotations.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.security.Permission;
import java.util.List;
@Service
public class OrderPlateProcessImpl extends AbstractProcess {
    private PlateBl plateBl;
    private int orderSelecion;

    @Autowired
    public OrderPlateProcessImpl(int orderSelecion , PlateBl plateB){
        this.plateBl = plateBl;
        this.setName("Selecion");
        this.setDefault(true);
        this.setExpires(false);
        this.setStartDate(System.currentTimeMillis()/1000);
//        this.setUserData(new HashMap<>());
        this.setStatus("STARTED");
        this.orderSelecion = orderSelecion;
    }

    @Override
    public AbstractProcess handle(ApplicationContext context, Update update, DeliveryLongPollingBot bot) {
        AbstractProcess result = this;
        Long chatId = update.getMessage().getChatId();


        if (this.getStatus().equals("STARTED")) {

            showOrderPlateProcess(bot, chatId);
        } else if (this.getStatus().equals("AWAITING_USER_RESPONSE")) {
            // Estamos esperando por un numero 1 o 2
            Message message = update.getMessage();
            if ( message.hasText() ) {
                // Intentamos transformar en número
                String text = message.getText(); // El texto contiene asdasdas
                try {
                    int opcion = Integer.parseInt(text);
                    switch (opcion){
                        case 0 : result = context.getBean(ViewMenuProcessImpl.class);
                            break;
                        default: showOrderPlateProcess(bot, chatId);
                    }
                } catch (NumberFormatException ex) {
                    showOrderPlateProcess(bot, chatId);
                }
                // continuar con el proceso seleccionado
            } else { // Si me enviaron algo diferente de un texto.
                showOrderPlateProcess(bot, chatId);
            }
        }
        return result;
    }

    private void showOrderPlateProcess(DeliveryLongPollingBot bot, Long chatId){
        List<PlateDto> menuToday = plateBl.TodayMenu(chatId);
        StringBuffer sb = new StringBuffer();
        sb.append("Usted Selecciono \r\n");
        PlateDto selecion = menuToday.get(orderSelecion-1);
        sb.append(selecion.getId()+": "+"Nombre: "+ selecion.getNombre()).append("\n\r");
        sb.append("Precio: "+selecion.getPrecio() + " Bs").append("\n\r");
        sb.append("Descripcion: "+selecion.getDescripcion()).append("\n\r");
        sb.append("0: atras").append("\n\r");
        sb.append("\n");
        sb.append("Seleccione la cantidad que desea: ");
        sb.append("\n");

        sendStringBuffer(bot,chatId,sb);
        this.setStatus("AWAITING_USER_RESPONSE");
    }

    @Override
    public AbstractProcess onError() {
        return null;
    }

    @Override
    public AbstractProcess onSuccess() {
        return null;
    }

    @Override
    public AbstractProcess onTimeout() {
        return null;
    }
}

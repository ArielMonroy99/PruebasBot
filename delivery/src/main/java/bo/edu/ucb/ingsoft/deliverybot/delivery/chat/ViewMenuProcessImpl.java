package bo.edu.ucb.ingsoft.deliverybot.delivery.chat;

import bo.edu.ucb.ingsoft.deliverybot.delivery.bl.PlateBl;
import bo.edu.ucb.ingsoft.deliverybot.delivery.dto.PlateDto;
import org.jvnet.hk2.annotations.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;

@Service
public class ViewMenuProcessImpl extends AbstractProcess{
    @Autowired
    private PlateBl plateBl;
    ViewMenuProcessImpl(PlateBl plateBl){
        this.plateBl = plateBl;
        this.setName("Platos del menu");
        this.setDefault(true);
        this.setExpires(false);
        this.setStartDate(System.currentTimeMillis()/1000);
//        this.setUserData(new HashMap<>());
        this.setStatus("STARTED");
    }

    @Override
    public AbstractProcess handle(ApplicationContext context, Update update, DeliveryLongPollingBot bot) {
        AbstractProcess result = this;
        Long chatId = update.getMessage().getChatId();


        if (this.getStatus().equals("STARTED")) {

            showMenuRestaurant(bot, chatId);
        } else if (this.getStatus().equals("AWAITING_USER_RESPONSE")) {
            // Estamos esperando por un numero 1 o 2
            Message message = update.getMessage();
            if ( message.hasText() ) {
                // Intentamos transformar en número
                String text = message.getText(); // El texto contiene asdasda
                try {
                    int opcion = Integer.parseInt(text);
                    switch (opcion){
                        case 0 : result = new MenuProcessImpl();
                            break;
                        case 1 :
                            System.out.println(opcion);
                            result =  context.getBean(OrderPlateProcessImpl.class);

                            break;
                        case 2 :
                            System.out.println(opcion);
                            result = context.getBean(OrderPlateProcessImpl.class);

                            break;
                        case 3 :
                            System.out.println(opcion);
                            result = context.getBean(OrderPlateProcessImpl.class);
                            break;
                        case 4 :
                            System.out.println(opcion);
                            result = context.getBean(OrderPlateProcessImpl.class);
                            break;

                        default: showMenuRestaurant(bot, chatId);
                    }
                } catch (NumberFormatException ex) {
                    showMenuRestaurant(bot, chatId);
                }
                // continuar con el proceso seleccionado
            } else { // Si me enviaron algo diferente de un texto.
                showMenuRestaurant(bot, chatId);
            }
        }
        return result;
    }

    private void showMenuRestaurant(DeliveryLongPollingBot bot, Long chatId){
        List<PlateDto> menuToday = plateBl.TodayMenu(chatId);
        StringBuffer sb = new StringBuffer();
        sb.append("Menu del dia \r\n");
        sendStringBuffer(bot,chatId,sb);
        sb.setLength(0);
        menuToday.forEach(plate->{

            sb.append(plate.getId()+": "+"Nombre: "+ plate.getNombre()).append("\n\r");
            sb.append("Precio: "+plate.getPrecio() + " Bs").append("\n\r");
            sb.append("Descripcion: "+plate.getDescripcion()).append("\n\r");
            sendPhotoB(bot,chatId,plate.getImg(), String.valueOf(sb));
            sb.append("\n");
            sb.setLength(0);
        });

        sb.append("0: Salir").append("\n\r");

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

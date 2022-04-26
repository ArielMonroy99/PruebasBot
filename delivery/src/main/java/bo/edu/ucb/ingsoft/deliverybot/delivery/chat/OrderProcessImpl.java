package bo.edu.ucb.ingsoft.deliverybot.delivery.chat;

import bo.edu.ucb.ingsoft.deliverybot.delivery.bl.OrderBl;
import bo.edu.ucb.ingsoft.deliverybot.delivery.dto.OrderDto;
import org.springframework.context.ApplicationContext;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

public class OrderProcessImpl extends AbstractProcess{

    public OrderProcessImpl(){
        this.setName("Consultar Pedido actual");
        this.setDefault(false);
        this.setExpires(false);
        this.setStartDate(System.currentTimeMillis()/1000);
        this.setStatus("STARTED");
    }
    private boolean hasShowOrder = false;
    @Override
    public AbstractProcess handle(ApplicationContext context, Update update, DeliveryLongPollingBot bot) {
        AbstractProcess result = this;
        Long chatId = update.getMessage().getChatId();
       // sigo en el mismo proceso.
        if (this.getStatus().equals("STARTED")) {

            showMenu(bot, chatId);
        } else if (this.getStatus().equals("AWAITING_USER_RESPONSE")) {
            // Estamos esperando por un numero 1 o 2
            Message message = update.getMessage();
            if ( message.hasText() ) {
                // Intentamos transformar en número
                String text = message.getText(); // El texto contiene asdasdas
                try {
                    int opcion = Integer.parseInt(text);
                    switch (opcion){
                        case 1 :  this.sendStringBuffer(bot,chatId,new StringBuffer("Pedido Enviado"));
                                  result = new MenuProcessImpl();
                        break;
                        case 2 : result = new MenuOrderProcessImpl();
                        break;

                        default: showMenu(bot, chatId);
                    }
                } catch (NumberFormatException ex) {
                    showMenu(bot, chatId);
                }
                // continuar con el proceso seleccionado
            } else { // Si me enviaron algo diferente de un texto.
                showMenu(bot, chatId);
            }
        }
        return result;

    }
    private void showMenu(DeliveryLongPollingBot bot, Long chatId){
        StringBuffer sb = new StringBuffer();
        OrderBl orderBl = new OrderBl();
        OrderDto lastOrder = orderBl.findLastOrder(chatId);
        sb.append("---Pedido---\r\n");
        sb.append(lastOrder.toString()).append("\n\r");
        sendStringBuffer(bot,chatId,sb);
        sb.append("Bot delivery\r\n");
        sb.append("1. Enviar Pedido\r\n");
        sb.append("2. Volver \r\n");
        sb.append("Elija una opción:\r\n");
        sendStringBuffer(bot, chatId, sb);

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

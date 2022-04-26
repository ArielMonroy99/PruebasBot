package bo.edu.ucb.ingsoft.deliverybot.delivery;

import bo.edu.ucb.ingsoft.deliverybot.delivery.chat.DeliveryLongPollingBot;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import javax.sql.DataSource;

@SpringBootApplication(scanBasePackages = {"bo.edu.ucb.ingsoft.deliverybot.delivery.bl","bo.edu.ucb.ingsoft.deliverybot.delivery.chat","bo.edu.ucb.ingsoft.deliverybot.delivery.dao","bo.edu.ucb.ingsoft.deliverybot.delivery.dto"} )
public class DeliveryApplication {

	@Bean
	public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
		SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
		factoryBean.setDataSource(dataSource);
		return factoryBean.getObject();
	}
	public static void main(String[] args) {
		SpringApplication.run(DeliveryApplication.class, args);

		try {
			// Inicializamos libreria de bots
			TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
			// Registramos la implementación de nuestro BOT
			telegramBotsApi.registerBot(new DeliveryLongPollingBot());
		} catch (TelegramApiException e) {
			e.printStackTrace();
		}
	}

}

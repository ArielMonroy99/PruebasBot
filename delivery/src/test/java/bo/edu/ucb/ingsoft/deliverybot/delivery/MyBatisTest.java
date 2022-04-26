package bo.edu.ucb.ingsoft.deliverybot.delivery;

import bo.edu.ucb.ingsoft.deliverybot.delivery.dao.PlateDao;
import bo.edu.ucb.ingsoft.deliverybot.delivery.dto.PlateDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class MyBatisTest {
    private PlateDao plateDao;

    @Autowired
    public MyBatisTest(PlateDao plateDao) {
        this.plateDao = plateDao;
    }

    @Test
    public void testSelectAllPlates(){
        List<PlateDto> plateDtoList = plateDao.findAllPlates();
        for(PlateDto plate: plateDtoList ){
            System.out.println("plato" + plate);
        }
    }
}

package bo.edu.ucb.ingsoft.deliverybot.delivery.bl;

import bo.edu.ucb.ingsoft.deliverybot.delivery.dao.PlateDao;
import bo.edu.ucb.ingsoft.deliverybot.delivery.dto.OrderDto;
import bo.edu.ucb.ingsoft.deliverybot.delivery.dto.PlateDto;
import org.jvnet.hk2.annotations.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
@Service
public class PlateBl {
    private PlateDao plateDao;

    @Autowired
    public PlateBl(PlateDao plateDao){
        this.plateDao = plateDao;
    }
    public List<PlateDto> TodayMenu(Long chatID){
        return plateDao.findAllPlates();

    }

}

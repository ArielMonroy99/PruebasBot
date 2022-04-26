package bo.edu.ucb.ingsoft.deliverybot.delivery.dao;

import bo.edu.ucb.ingsoft.deliverybot.delivery.dto.PlateDto;
import org.apache.ibatis.annotations.Select;
import org.jvnet.hk2.annotations.Service;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public interface PlateDao {
    @Select("select * from plate")
    List<PlateDto> findAllPlates();
}

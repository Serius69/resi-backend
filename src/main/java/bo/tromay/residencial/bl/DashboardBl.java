package bo.tromay.residencial.bl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import bo.tromay.residencial.dao.*;
import bo.tromay.residencial.dto.*;

import java.util.List;

@Service
public class DashboardBl {
    @Autowired
    DashboardDao dasboardDao;


    public List<DataStringIntDto> getProductSells() {
        return dasboardDao.getProductSells();
    }
    public List<DataStringDoubleDto> getSellsbyMonth() {
        return dasboardDao.getSellsbyMonth();
    }

    public List<DataStringIntDto> getQuantitySellsbyMonth() {
        return dasboardDao.getQuantitySellsbyMonth();
    }

    public DataIntDto getquantityofProducts() {
        return dasboardDao.getquantityofProducts();
    }





}

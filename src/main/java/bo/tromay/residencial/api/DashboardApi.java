package bo.tromay.residencial.api;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ucb.edu.arqsoft.msdashboard.bl.DashboardBl;
import ucb.edu.arqsoft.msdashboard.dto.DataIntDto;
import ucb.edu.arqsoft.msdashboard.dto.DataStringDoubleDto;
import ucb.edu.arqsoft.msdashboard.dto.DataStringIntDto;
import ucb.edu.arqsoft.msdashboard.dto.DataStringStringIntDto;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "https://localhost:4200")
@RestController
@RequestMapping("/api/dashboard")
public class DashboardApi {

    @Autowired
    public DataSource dataSource;
    @Autowired
    private DashboardBl dashboardBl;

    @GetMapping(path = "/productsinstock")
    public DataIntDto getProductWithoutStock() {
        DataIntDto data = dashboardBl.getProductsWithoutstock();
        return data;
    }

    @GetMapping(path = "/productconstock")
    public DataIntDto getProductWithStock() {
        DataIntDto data = dashboardBl.getProductsWithstock();
        return data;
    }

    @GetMapping(path = "/productbycategory")
    public List<DataStringDoubleDto> getProductsCategory() {
        List<DataStringDoubleDto> dashboard = new ArrayList<>();
        dashboard= dashboardBl.getProductsCategory();
        return dashboard;
    }

    @GetMapping(path = "/productsells")
    public List<DataStringIntDto> getProductSells() {
        List<DataStringIntDto> dashboard = dashboardBl.getProductSells();
        return dashboard;
    }

    @GetMapping(path = "/moneysellsbymonth")
    public List<DataStringDoubleDto> getSellsbyMonth() {
        List<DataStringDoubleDto> dashboard = dashboardBl.getSellsbyMonth();
        return dashboard;
    }

    @GetMapping(path = "/productsellsbycategoryandmonth")
    public List<DataStringStringIntDto> getQuantitySellsbyMonthCategory() {
        List<DataStringStringIntDto> dashboard = dashboardBl.getQuantitySellsbyMonthCategory();
        return dashboard;
    }

    @GetMapping(path = "/productsellsbymonth")
    public List<DataStringIntDto> getQuantitySellsbyMonth() {
        List<DataStringIntDto> dashboard = dashboardBl.getQuantitySellsbyMonth();
        return dashboard;
    }


    @GetMapping(path = "/quantityoforders")
    public DataIntDto getquantityofProducts() {
        DataIntDto dashboard = dashboardBl.getquantityofProducts();
        return dashboard;
    }

}

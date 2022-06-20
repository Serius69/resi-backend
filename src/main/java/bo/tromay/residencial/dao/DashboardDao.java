package bo.tromay.residencial.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import bo.tromay.residencial.dto.*;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Service
public class DashboardDao {

    @Autowired
    private DataSource dataSource;

    //cantidad de habitaciones vendidas por mes
    public List<DataStringIntDto>  getProductSells() {
        List<DataStringIntDto>  result = new ArrayList<>();
        try(Connection conn = dataSource.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(
                    " select MONTHNAME(o.date_created), sum(o.total_quantity) " +
                            "from product as p " +
                            "join order_item as oi " +
                            "join orders as o " +
                            "where oi.product_id = p.id " +
                            "and o.order_tracking_number = oi.id " +
                            "GROUP BY YEAR(o.date_created), MONTH(o.date_created)" )

        ) {
            ResultSet rs = pstmt.executeQuery();
//            if (rs.next()) {
            while (rs.next()) {
                DataStringIntDto data = new DataStringIntDto();
                data.setCategory(rs.getString("MONTHNAME(o.date_created)"));
                data.setQuantity(rs.getInt("sum(o.total_quantity)"));
                result.add(data);
            }
//            } else { // si no hay valores de BBDD
//                data.setCategory(null);
//                data.setQuentity(0.0);
//                result.add(data);
//            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }
    // monto de ventas por mes
    public List<DataStringDoubleDto> getSellsbyMonth() {
        List<DataStringDoubleDto> result = new ArrayList<>();
        try(
                Connection conn = dataSource.getConnection();
                //arreglar query
                PreparedStatement pstmt = conn.prepareStatement(
                        " select MONTHNAME(date_created) ,sum(total_price) from orders " +
                                "GROUP BY YEAR(date_created), MONTH(date_created)" )
        )
        {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                DataStringDoubleDto data = new DataStringDoubleDto();
                data.setCategory(rs.getString("MONTHNAME(date_created)"));
                data.setQuantity(rs.getDouble("sum(total_price)"));
                result.add(data);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }
    // cantidad de prendas vendidas por mes
    public List<DataStringIntDto> getQuantitySellsbyMonth() {
        List<DataStringIntDto> result = new ArrayList<>();
        try(
                Connection conn = dataSource.getConnection();
                //arreglar query
                PreparedStatement pstmt = conn.prepareStatement(
                        " select MONTHNAME(o.date_created), sum(o.total_quantity) " +
                                "from product as p " +
                                "join product_category as pc " +
                                "join order_item as oi " +
                                "join orders as o " +
                                "where p.category_id = pc.id " +
                                "and oi.product_id = p.id " +
                                "and o.order_tracking_number = oi.id " +
                                "GROUP BY YEAR(o.date_created), MONTH(o.date_created) " +
                                "ORDER BY MONTH(o.date_created), pc.id " )
        )
        {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
//                System.out.println("llenando datos");
                DataStringIntDto data = new DataStringIntDto();
                data.setCategory(rs.getString("MONTHNAME(o.date_created)"));
                data.setQuantity(rs.getInt("sum(o.total_quantity)"));
                result.add(data);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    //Retornar la cantidad de ventas que se tiene
    public DataIntDto getquantityofProducts() {

        DataIntDto result = new DataIntDto();
        try(
                Connection conn = dataSource.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(
                        " select count(o.id) from orders as o " )
//                                "join product as p " +
//                                "join order_item as oi " +
//                                "where o.id = oi.order_id " +
//                                "and p.id = oi.product_id " +
//                                "group by o.id"
        )
        {
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                result.setQuantity(rs.getDouble("count(o.id)"));
            } else { // si no hay valores de BBDD
                result.setQuantity(0);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }


}

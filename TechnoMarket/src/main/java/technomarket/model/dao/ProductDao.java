package technomarket.model.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import technomarket.model.dto.requestDTO.AttributeDTO;
import technomarket.model.dto.requestDTO.FilterDTO;
import technomarket.model.dto.responseDTO.ResponseProductDTO;
import technomarket.utill.DBConnector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class ProductDao {

    @Autowired
    private DBConnector dbConnector;


    public List<Integer> findAllProductsByFilter(FilterDTO filterDTO) {
        StringBuilder sql = new StringBuilder("SELECT p.id FROM technomarket.products AS p\n" +
                "JOIN technomarket.sub_categories AS c \n" +
                "ON p.sub_category_id = c.id\n");
        if (filterDTO.getCategoryId() != null
                || filterDTO.getSubCategoryId() != null
                || filterDTO.getBrand() != null){
                sql.append("WHERE");
            if (filterDTO.getBrand() != null){
                sql.append(" p.brand LIKE \"").append(filterDTO.getBrand()).append("\" AND");
            }
            if (filterDTO.getSubCategoryId() != null){
                sql.append(" p.sub_category_id = ").append(filterDTO.getSubCategoryId()).append(" AND");
            }
            if (filterDTO.getCategoryId() != null){
                sql.append(" c.category_id = ").append(filterDTO.getCategoryId()).append(" AND");
            }
            sql.replace(sql.length()-3,sql.length(),"");
        }
        List<Integer> ids = new ArrayList<>();

        System.out.println(sql.toString());
        try (
                PreparedStatement preparedStatement = dbConnector.getConnection().prepareStatement(sql.toString())
                ){
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                ids.add(resultSet.getInt("id"));
            }
            return ids;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

}

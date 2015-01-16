package dao.sql;


import dao.Dao;
import model.Category;
import model.Food;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SqlDaoImpl implements Dao<Category, Food> {

    private ConnectionForDatabase connectionForDatabase = new ConnectionForDatabase();
    private PreparedStatement preparedStatement;

    private static final String GET_ALL = "SELECT * FROM category";
    private static final String INSERT_NEW = "INSERT INTO category (name) VALUES(?)";
    private static final String DELETE = "DELETE FROM category WHERE name=?";
    private static final String UPDATE = "UPDATE category SET name=? where name=?";

    @Override
    public List<Category> getAll() throws ParserConfigurationException, IOException, SAXException {
        Category category;
        ArrayList<Category> listOfCategory = new ArrayList<Category>();
        try {
            preparedStatement = connectionForDatabase.getConnection().prepareStatement(GET_ALL);
            ResultSet res = preparedStatement.executeQuery();
            while (res.next()) {
                category = new Category(res.getString("name"));
                listOfCategory.add(category);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listOfCategory;
    }

    @Override
    public void create(Category category) throws ParserConfigurationException, IOException, SAXException, TransformerException {
        try {
            preparedStatement = connectionForDatabase.getConnection().prepareStatement(INSERT_NEW);
            preparedStatement.setString(1, category.getName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Category category) throws ParserConfigurationException, IOException, SAXException, TransformerException {
        try {
            preparedStatement = connectionForDatabase.getConnection().prepareStatement(DELETE);
            preparedStatement.setString(1, category.getName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Category oldCategory, Category newCategory) throws ParserConfigurationException, IOException, SAXException, TransformerException {
        try {
            preparedStatement = connectionForDatabase.getConnection().prepareStatement(UPDATE);
            preparedStatement.setString(1, newCategory.getName());
            preparedStatement.setString(2, oldCategory.getName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public List<Category> getBy(Food food) throws ParserConfigurationException, IOException, SAXException {
        return null;
    }
}





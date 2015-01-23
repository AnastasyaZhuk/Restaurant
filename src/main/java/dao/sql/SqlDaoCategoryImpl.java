package dao.sql;


import dao.Dao;
import model.Category;
import model.Food;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SqlDaoCategoryImpl implements Dao<Category, Food> {

    private DBConnectorPool connectionForDatabase = new DBConnectorPool();
    private PreparedStatement preparedStatement;

    private static final String GET_ALL = "SELECT * FROM category";
    private static final String INSERT_NEW = "INSERT INTO category (category_name) VALUES(?)";
    private static final String DELETE = "DELETE FROM category WHERE name = ?";
    private static final String UPDATE = "UPDATE category SET name = ? where name = ?";

    @Override
    public List<Category> getAll() {
        Category category;
        ArrayList<Category> listOfCategory = new ArrayList<Category>();
        try {
            preparedStatement = connectionForDatabase.getConnection().prepareStatement(GET_ALL);
            ResultSet res = preparedStatement.executeQuery();
            while (res.next()) {
                category = new Category(res.getString("category_name"));
                listOfCategory.add(category);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listOfCategory;
    }

    @Override
    public List<Category> getBy(Food entity) throws ParserConfigurationException, IOException, SAXException {
        return null;
    }

    @Override
    public void create(Category category) {
        try {
            preparedStatement = connectionForDatabase.getConnection().prepareStatement(INSERT_NEW);
            preparedStatement.setString(1, firstUpperCase(category.getName()));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Category category) {
        try {
            preparedStatement = connectionForDatabase.getConnection().prepareStatement(DELETE);
            preparedStatement.setString(1, firstUpperCase(category.getName()));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Category oldCategory, Category newCategory) {
        try {
            preparedStatement = connectionForDatabase.getConnection().prepareStatement(UPDATE);
            preparedStatement.setString(1, firstUpperCase(newCategory.getName()));
            preparedStatement.setString(2, firstUpperCase(oldCategory.getName()));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static String firstUpperCase(String word) {
        if (word == null || word.isEmpty()) return "";
        return word.substring(0, 1).toUpperCase() + word.substring(1);
    }
}





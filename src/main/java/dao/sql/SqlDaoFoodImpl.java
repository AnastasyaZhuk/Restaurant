package dao.sql;

import com.mysql.fabric.jdbc.FabricMySQLDriver;
import dao.Dao;
import model.Category;
import model.Food;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SqlDaoFoodImpl implements Dao<Food, Category> {

    private Connection connection;
    private PreparedStatement preparedStatement;

    private static final String GET_ALL_BY_CATEGORY = "SELECT name, price FROM food, category WHERE category_name = ? AND food.id_category = category.id";
    private static final String GET_ALL = "SELECT category_name, name, price FROM category, food WHERE food.id_category = category.id";
    private static final String INSERT_NEW = "INSERT INTO food (id_category, name, price) VALUES(?, ?, ?)";
    private static final String UPDATE = "UPDATE food SET name = ? where name = ?";
    private static final String SELECT = "SELECT id FROM category WHERE category_name = ?";
    private static final String DELETE = "DELETE FROM food WHERE name = ?";

    public Connection getConnection() {
        if (connection == null) {
            try {
                connection = (Connection) DBConnectorPool.getInstance("jdbc:mysql://localhost:3306/Restaurant",
                        "root", "root", new FabricMySQLDriver());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    @Override
    public void update(Food oldFood, Food newFood) {
        try {
            preparedStatement = getConnection().prepareStatement(UPDATE);
            preparedStatement.setString(1, newFood.getName());
//            preparedStatement.setInt(2, newFood.getPrice());
            preparedStatement.setString(2, oldFood.getName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Food> getBy(Category category) {
        Food food;
        ArrayList<Food> listOfFood = new ArrayList<Food>();
        try {
            preparedStatement = getConnection().prepareStatement(GET_ALL_BY_CATEGORY);
            preparedStatement.setString(1, category.getName());
            ResultSet res = preparedStatement.executeQuery();
            while (res.next()) {
                food = new Food(res.getString("name"), res.getInt("price"));
                listOfFood.add(food);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listOfFood;
    }

    @Override
    public void create(Food food) {
        PreparedStatement preparedStatement2;
        try {
            preparedStatement2 = getConnection().prepareStatement(SELECT);
            preparedStatement2.setString(1, food.getCategory().getName());
            ResultSet resultSet = preparedStatement2.executeQuery();
            int id = 0;
            while (resultSet.next()) {
                id = resultSet.getInt("id");
            }
            preparedStatement = getConnection().prepareStatement(INSERT_NEW);
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, food.getName());
            preparedStatement.setInt(3, food.getPrice());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Food food) {
        try {
            preparedStatement = getConnection().prepareStatement(DELETE);
            preparedStatement.setString(1, food.getName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Food> getAll() {
        Food food;
        ArrayList<Food> fullList = new ArrayList<Food>();
        try {
            preparedStatement = getConnection().prepareStatement(GET_ALL);
            ResultSet res = preparedStatement.executeQuery();
            while (res.next()) {
                food = new Food(res.getString("name"), res.getString("category_name"), res.getInt("price"));
                fullList.add(food);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return fullList;
    }
}

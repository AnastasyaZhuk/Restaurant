package services;

import dao.Dao;
import dao.DaoFactory;
import dao.sql.SqlDaoFoodImpl;
import dao.xml.XmlDaoFoodImpl;
import model.Category;
import model.Food;
import org.xml.sax.SAXException;
import view.View;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.List;

public class FoodServiceImpl implements FoodService, DaoFactory {

    private View view = new View();
    private XmlDaoFoodImpl xmlDaoFood;
    private SqlDaoFoodImpl sqlDao;

    @Override
    public void updateFood() throws IOException, ParserConfigurationException, SAXException, TransformerException {
        String nameCategory = view.nameCategory();
        Category category = new Category(nameCategory);
        String nameFood = view.nameDishes();
        Food oldFood = new Food(nameFood, category.getName());
        String newNameOfFood = view.newNameForDish();
        Food newFood = new Food(newNameOfFood, category.getName());
        getDaoXml();
//        xmlDaoFood.update(oldFood, newFood);
        sqlDao.update(oldFood, newFood);
    }

    @Override
    public void removeFood() throws IOException, TransformerException, SAXException, ParserConfigurationException {
        String nameCategory = view.nameCategory();
        Category category = new Category(nameCategory);
        String nameFood = view.nameDishes();
        Food food = new Food(nameFood, category.getName());
        getDaoXml();
//        xmlDaoFood.delete(food);
        sqlDao.delete(food);
    }

    @Override
    public void getAllDishes() throws IOException, SAXException, ParserConfigurationException {
        String nameCategory = view.nameCategory();
        Category category = new Category(nameCategory);
        getDaoXml();
        // List<Food> listOfFood = xmlDaoFood.getBy(category);
        List<Food> listOfFood = sqlDao.getBy(category);
        view.showAllDishes(listOfFood);
    }

    @Override
    public void createFood() throws TransformerException, SAXException, ParserConfigurationException, IOException {
        String nameCategory = view.nameCategory();
        Category category = new Category(nameCategory);
        String nameFood = view.nameDishes();
        int price = view.priceOfDishes();
        Food food = new Food(nameFood, category.getName(), price);
        getDaoXml();
        // xmlDaoFood.create(food);
        sqlDao.create(food);
    }

    @Override
    public void getAllMenu() {
        getDaoXml();
        List<Food> fullList = sqlDao.getAll();
        view.showAllMenu(fullList);
    }

    @Override
    public Dao getDaoXml() {
        if (sqlDao == null) {
            this.sqlDao = new SqlDaoFoodImpl();
        }
        return sqlDao;
//        if (xmlDaoFood == null) {
//            this.xmlDaoFood = new XmlDaoFoodImpl();
//        }
//        return xmlDaoFood;
    }
}

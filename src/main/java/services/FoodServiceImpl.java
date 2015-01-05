package services;

import dao.Dao;
import dao.DaoFactory;
import dao.xml.XmlDaoCategoryImpl;
import dao.xml.XmlDaoFoodImpl;
import model.Category;
import model.Food;
import org.xml.sax.SAXException;
import view.View;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.List;

public class FoodServiceImpl implements FoodService, DaoFactory<XmlDaoCategoryImpl<Dao>, XmlDaoFoodImpl<Dao>> {

    private View view = new View();

    @Override
    public void updateFood() throws IOException, ParserConfigurationException, SAXException, TransformerException {
        String nameCategory = view.nameCategory();
        Category category = new Category(nameCategory);
        String nameFood = view.nameDishes();
        Food oldFood = new Food(nameFood, category.getName());
        String newNameOfFood = view.newNameForDish();
        Food newFood = new Food(newNameOfFood, category.getName());
        getFoodDao().update(oldFood, newFood);
    }

    @Override
    public void removeFood() throws IOException, TransformerException, SAXException, ParserConfigurationException {
        String nameCategory = view.nameCategory();
        Category category = new Category(nameCategory);
        String nameFood = view.nameDishes();
        Food food = new Food(nameFood, category.getName());
        getFoodDao().delete(food);
    }

    @Override
    public void getAllDishes() throws IOException, SAXException, ParserConfigurationException {
        String nameCategory = view.nameCategory();
        Category category = new Category(nameCategory);
        List<Food> listOfFood = getFoodDao().getBy(category);
        view.showAllDishes(listOfFood);
    }

    @Override
    public void createFood() throws TransformerException, SAXException, ParserConfigurationException, IOException {
        String nameCategory = view.nameCategory();
        Category category = new Category(nameCategory);
        String nameFood = view.nameDishes();
        int price = view.priceOfDishes();
        Food food = new Food(nameFood, category.getName(), price);
        getFoodDao().create(food);
    }

    @Override
    public XmlDaoCategoryImpl<Dao> getCategoryDao() {
        XmlDaoCategoryImpl<Dao> xmlDaoCategory = new XmlDaoCategoryImpl<Dao>();
        return xmlDaoCategory;
    }

    @Override
    public XmlDaoFoodImpl<Dao> getFoodDao() {
        XmlDaoFoodImpl<Dao> xmlDaoFood = new XmlDaoFoodImpl<Dao>();
        return xmlDaoFood;
    }
}

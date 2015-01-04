package model;

import DAO.XmlDaoImpl.XmlDaoFoodImpl;
import org.xml.sax.SAXException;
import view.View;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class FoodService implements IFoodService {

    private View view = new View();
    private XmlDaoFoodImpl xmlDaoFood = new XmlDaoFoodImpl();

    @Override
    public void updateFood() throws IOException, ParserConfigurationException, SAXException {

    }

    @Override
    public void removeFood() throws IOException, TransformerException, SAXException, ParserConfigurationException {

    }

    @Override
    public void getAllDishes() throws IOException, SAXException, ParserConfigurationException {
        List<Food> listOfFood = xmlDaoFood.getAll();
        view.showAllDishes(listOfFood);
    }

    @Override
    public void createFood() throws TransformerException, SAXException, ParserConfigurationException, IOException {
        view.nameCategory();
        String nameCategory = new BufferedReader(new InputStreamReader(System.in)).readLine();
        view.nameDishes();
        String nameFood = new BufferedReader(new InputStreamReader(System.in)).readLine();
        view.priceOfDishes();
        int price = Integer.parseInt(new BufferedReader(new InputStreamReader(System.in)).readLine());
        Food food = new Food(nameCategory, nameFood, price);
        xmlDaoFood.create(food);

    }
}

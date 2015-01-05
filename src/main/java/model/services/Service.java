package model.services;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;

public class Service {
    private CategoryServiceImpl categoryService = new CategoryServiceImpl();
    private FoodServiceImpl foodService = new FoodServiceImpl();

    public void getAllCategories() throws ParserConfigurationException, SAXException, IOException {
        categoryService.getAllCategories();
    }

    public void getAllDishes() throws ParserConfigurationException, SAXException, IOException {
        foodService.getAllDishes();
    }

    public void updateCategory() throws ParserConfigurationException, SAXException, IOException, TransformerException {
        categoryService.updateCategory();
    }

    public void updateFood() throws ParserConfigurationException, SAXException, IOException, TransformerException {
        foodService.updateFood();
    }

    public void removeCategory() throws SAXException, TransformerException, ParserConfigurationException, IOException {
        categoryService.removeCategory();
    }

    public void deleteDishes() throws SAXException, TransformerException, ParserConfigurationException, IOException {
        foodService.removeFood();
    }

    public void createCategory() throws SAXException, TransformerException, ParserConfigurationException, IOException {
        categoryService.createCategory();
    }

    public void createFood() throws SAXException, ParserConfigurationException, IOException, TransformerException {
        foodService.createFood();
    }
}

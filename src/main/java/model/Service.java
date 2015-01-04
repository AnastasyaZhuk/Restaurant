package model;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;

public class Service {
    private CategoryService categoryService = new CategoryService();
    private FoodService foodService = new FoodService();

    public void getAllCategories() throws ParserConfigurationException, SAXException, IOException {
        categoryService.getAllCategories();
    }

    public void updateCategory() throws ParserConfigurationException, SAXException, IOException {
        categoryService.updateCategory();
    }

    public void removeCategory() throws SAXException, TransformerException, ParserConfigurationException, IOException {
        categoryService.removeCategory();
    }

    public void createCategory() throws SAXException, TransformerException, ParserConfigurationException, IOException {
        categoryService.createCategory();
    }


}

package controller;

import org.xml.sax.SAXException;
import services.CategoryServiceImpl;
import services.FoodServiceImpl;
import view.View;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;


public class Controller {
    private FoodServiceImpl foodService = new FoodServiceImpl();
    private CategoryServiceImpl categoryService = new CategoryServiceImpl();
    private View view = new View();

    /**
     * Sends a request to perform Service
     */
    public void requestForService(String request) throws ParserConfigurationException, SAXException, IOException, TransformerException {
        if (request.equals("")) {
           view.nullRequest();
        } else if ("show category".equalsIgnoreCase(request)) {
            categoryService.getAllCategories();
        } else if ("show dishes".equalsIgnoreCase(request)) {
            foodService.getAllDishes();
        } else if ("new category".equalsIgnoreCase(request)) {
            categoryService.createCategory();
        } else if ("new food".equalsIgnoreCase(request)) {
            foodService.createFood();
        } else if ("update category".equalsIgnoreCase(request)) {
            categoryService.updateCategory();
        } else if ("update food".equalsIgnoreCase(request)) {
            foodService.updateFood();
        } else if ("delete category".equalsIgnoreCase(request)) {
            categoryService.removeCategory();
        } else if ("delete food".equalsIgnoreCase(request)) {
            foodService.removeFood();
        } else {
            view.showGhost();
        }
    }

    public void updateView(View view) {
    }
}

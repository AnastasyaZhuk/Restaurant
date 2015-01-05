package controller;

import org.xml.sax.SAXException;
import services.CategoryServiceImpl;
import services.FoodServiceImpl;
import view.View;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.logging.Logger;

public class Controller {
    private static Logger log = Logger.getLogger(Controller.class.getName());

    private FoodServiceImpl foodService;
    private CategoryServiceImpl categoryService;
    private View view;

    public View getView() {
        view = new View();
        return view;
    }

    public FoodServiceImpl getFoodService() {
        foodService = new FoodServiceImpl();
        return foodService;
    }

    public CategoryServiceImpl getCategoryService() {
        categoryService = new CategoryServiceImpl();
        return categoryService;
    }

    public Controller() {
    }


    /**
     * Sends a request to perform Service
     */
    public void requestForService(String request) throws ParserConfigurationException, SAXException, IOException, TransformerException {
        if (request.equals("")) {
            log.info("Запрос не введен!");
        } else if ("show category".equalsIgnoreCase(request)) {
            getCategoryService().getAllCategories();
        } else if ("show dishes".equalsIgnoreCase(request)) {
            getFoodService().getAllDishes();
        } else if ("new category".equalsIgnoreCase(request)) {
            getCategoryService().createCategory();
        } else if ("new food".equalsIgnoreCase(request)) {
            getFoodService().createFood();
        } else if ("update category".equalsIgnoreCase(request)) {
            getCategoryService().updateCategory();
        } else if ("update food".equalsIgnoreCase(request)) {
            getFoodService().updateFood();
        } else if ("delete category".equalsIgnoreCase(request)) {
            getCategoryService().removeCategory();
        } else if ("delete food".equalsIgnoreCase(request)) {
            getFoodService().removeFood();
        } else {
            view.showGhost();
        }
    }

    public void updateView(View view) {
    }
}

package controller;

import org.xml.sax.SAXException;
import services.CategoryServiceImpl;
import services.FoodServiceImpl;
import services.TestServicesForSaxParser;
import view.View;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Controller {
    private FoodServiceImpl foodService = new FoodServiceImpl();
    private CategoryServiceImpl categoryService = new CategoryServiceImpl();
    private View view = new View();

    private TestServicesForSaxParser testServicesForSaxParser = new TestServicesForSaxParser();

    public List<String> getListOfRequest() {
        Request[] list = Request.values();
        List<String> listOfRequest = new ArrayList<>();
        for (int i = 0; i < list.length; i++) {
            listOfRequest.add(String.valueOf(list[i]));
        }
        return listOfRequest;
    }

    /**
     * Sends a request to perform Service
     */
    public void requestForService(String request) throws ParserConfigurationException, SAXException, IOException, TransformerException {
        Request request1 = Request.getRequest(request);
        switch (request1) {
            case NULL_REQUEST:
                view.nullRequest();
                break;
            case SHOW_CATEGORY:
                //  categoryService.getAllCategories();

                /**
                 * TEST FOR SAX PARSER
                 */
                testServicesForSaxParser.getAllCategories();
                break;
            case SHOW_DISHES:
                foodService.getAllDishes();
                break;
            case NEW_CATEGORY:
                categoryService.createCategory();
                break;
            case NEW_FOOD:
                foodService.createFood();
                break;
            case UPDATE_CATEGORY:
                categoryService.updateCategory();
                break;
            case UPDATE_FOOD:
                foodService.updateFood();
                break;
            case DELETE_CATEGORY:
                categoryService.removeCategory();
                break;
            case DELETE_FOOD:
                foodService.removeFood();
                break;
            default:
                view.showGhost();
        }
    }

    public void updateView(View view) {
    }
}

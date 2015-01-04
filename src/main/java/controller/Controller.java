package controller;

import model.Service;
import org.xml.sax.SAXException;
import view.View;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Logger;

public class Controller {
    private static Logger log = Logger.getLogger(Controller.class.getName());

    private Service service = new Service();
    private View view = new View();

    public Controller() {
    }

    public Controller(Service model, View view) {
        this.service = model;
        this.view = view;
    }

    /**
     * Sends a request to perform Service
     */
    public void requestForService() throws IOException, TransformerException, ParserConfigurationException, SAXException {
        String request = new BufferedReader(new InputStreamReader(System.in)).readLine();
        if (request.equals("")) {
            log.info("Запрос не введен!");
        } else if (request.equalsIgnoreCase("show category")) {
            service.getAllCategories();
        } else if (request.equalsIgnoreCase("show dishes")) {
            service.getAllDishes();
        } else if (request.equalsIgnoreCase("new category")) {
            service.createCategory();
        } else if (request.equalsIgnoreCase("update category")) {
            service.updateCategory();
        } else if (request.equalsIgnoreCase("delete category")) {
            service.removeCategory();
        } else if (request.equalsIgnoreCase("new food")) {
            service.createFood();
        } else {
            view.showGhost();
        }
    }

    public void updateView(View view) {
    }
}

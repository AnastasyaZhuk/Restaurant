package view;

import controller.Controller;
import model.Category;
import model.Food;
import org.apache.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class View {
    private static Logger log = Logger.getLogger(View.class.getName());
    private Controller controller;

    /**
     * Display System
     */
    public void display() throws TransformerException, ParserConfigurationException, SAXException {
        log.info("Добро пожаловать в ресторан!");
        inputRequest();
    }

    public void displayListOfRequest() {
        controller = new Controller();
        List<String> list = controller.getListOfRequest();
        String listOfRequest = "\n\r" + "Список всех команд:";
        for (int i = 0; i < list.size(); i++) {
            listOfRequest = listOfRequest + "\n\r" + list.get(i);
        }
        log.info(listOfRequest);
    }

    public void inputRequest() throws TransformerException, ParserConfigurationException, SAXException {
        displayListOfRequest();
        log.info("Введите запрос:");
        String request = null;
        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
            request = bf.readLine();
        } catch (IOException e) {
            log.error(e.toString());
        }
        requestForController(request);
    }

    /**
     * Insert Name For Category
     */
    public String nameCategory() {
        log.info("Введите название категории:");
        String nameCategory = null;
        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
            nameCategory = bf.readLine();
        } catch (IOException e) {
            log.error(e.toString());
        }
        return nameCategory;
    }

    /**
     * Insert Name For Dish
     */
    public String nameDishes() {
        log.info("Введите название блюда");
        String nameForDishes = null;
        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
            nameForDishes = bf.readLine();
        } catch (IOException e) {
            log.error(e.toString());
        }
        return nameForDishes;
    }

    /**
     * Insert Price For Dish
     */
    public int priceOfDishes() {
        log.info("Установите цену:");
        int price = 0;
        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
            price = Integer.parseInt(bf.readLine());
        } catch (IOException e) {
            log.error(e.toString());
        }
        return price;
    }

    /**
     * Set New Name For Category
     */
    public String newNameForCategory() {
        log.info("Введите новое название категории:");
        String newName = null;
        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
            newName = bf.readLine();
        } catch (IOException e) {
            log.error(e.toString());
        }
        return newName;
    }

    /**
     * Set New Name For Dish
     */
    public String newNameForDish() {
        log.info("Введите новое название блюда");
        String newName = null;
        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
            newName = bf.readLine();
        } catch (IOException e) {
            log.error(e.toString());
        }
        return newName;
    }

    /**
     * Display All Categories
     */
    public void showAllCategory(List<Category> list) {
        for (int i = 0; i < list.size(); i++) {
            log.info(list.get(i).getName());
        }
    }

    /**
     * Display All Dishes
     */
    public void showAllDishes(List<Food> list) {
        for (int i = 0; i < list.size(); i++) {
            log.info(list.get(i).getName() + " - " + list.get(i).getPrice() + "rub");
        }
    }

    /**
     *
     */
    public void showGhost() throws TransformerException, ParserConfigurationException, SAXException {
        log.error("Такой запрос не поддерживается! Проверьте правильность запроса! ");
    }

    public void nullRequest() throws TransformerException, ParserConfigurationException, SAXException {
        log.error("Запрос не введен! Попробуйте ещё раз");
        inputRequest();
    }

    /**
     * Processes the user's request
     */
    public void requestForController(String request) throws TransformerException, ParserConfigurationException, SAXException {
        controller = new Controller();
        try {
            controller.requestForService(request);
        } catch (IOException e) {
            log.error(e.toString());
        }
    }
}

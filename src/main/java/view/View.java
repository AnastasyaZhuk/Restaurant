package view;

import controller.Controller;
import model.Category;
import model.Food;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.logging.Logger;

public class View {
    private static Logger log = Logger.getLogger(View.class.getName());
    private Controller controller;

    /**
     * Display System
     */
    public void display() throws IOException, TransformerException, ParserConfigurationException, SAXException {
        log.info("Добро пожаловать в ресторан!");
        log.info("Введите запрос:");
        requestForController();
    }

    public String nameCategory() throws IOException {
        log.info("Введите название категории:");
        String nameCategory = new BufferedReader(new InputStreamReader(System.in)).readLine();
        return nameCategory;
    }

    public String nameDishes() throws IOException {
        log.info("Введите название блюда");
        String nameForDishes = new BufferedReader(new InputStreamReader(System.in)).readLine();
        return nameForDishes;

    }

    public int priceOfDishes() throws IOException {
        log.info("Установите цену:");
        int price = Integer.parseInt(new BufferedReader(new InputStreamReader(System.in)).readLine());
        return price;
    }

    public String newNameForCategory() throws IOException {
        log.info("Введите новое название категории:");
        String newName = new BufferedReader(new InputStreamReader(System.in)).readLine();
        return newName;
    }

    public String newNameForDish() throws IOException {
        log.info("Введите новое название блюда");
        String newName = new BufferedReader(new InputStreamReader(System.in)).readLine();
        return newName;
    }

    public void showAllCategory(List<Category> list) {
        for (int i = 0; i < list.size(); i++) {
            log.info(list.get(i).getName());
        }
    }

    public void showAllDishes(List<Food> list) {
        for (int i = 0; i < list.size(); i++) {
            log.info(list.get(i).getName() + " - " + list.get(i).getPrice() + "rub");
        }
    }

    public void showGhost() {
        log.info("Такой запрос не поддерживается! Проверьте правильность запроса! ");
    }

    /**
     * Processes the user's request
     */
    public void requestForController() throws IOException, TransformerException, ParserConfigurationException, SAXException {
        controller = new Controller();
        controller.requestForService();
    }
}

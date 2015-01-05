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
    public void display() throws TransformerException, ParserConfigurationException, SAXException {
        log.info("Добро пожаловать в ресторан!");
        log.info("Введите запрос:");
        String request = null;
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        try {
            request = bf.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bf.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            requestForController(request);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Insert Name For Category
     */
    public String nameCategory() {
        log.info("Введите название категории:");
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String nameCategory = null;
        try {
            nameCategory = bf.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bf.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return nameCategory;
    }

    /**
     * Insert Name For Dish
     */
    public String nameDishes() {
        log.info("Введите название блюда");
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String nameForDishes = null;
        try {
            nameForDishes = bf.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bf.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return nameForDishes;
    }

    /**
     * Insert Price For Dish
     */
    public int priceOfDishes() {
        log.info("Установите цену:");
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int price = 0;
        try {
            price = Integer.parseInt(bf.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bf.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return price;
    }

    /**
     * Set New Name For Category
     */
    public String newNameForCategory() {
        log.info("Введите новое название категории:");
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String newName = null;
        try {
            newName = bf.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bf.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return newName;
    }

    /**
     * Set New Name For Dish
     */
    public String newNameForDish() {
        log.info("Введите новое название блюда");
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String newName = null;
        try {
            newName = bf.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bf.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
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
    public void showGhost() {
        log.info("Такой запрос не поддерживается! Проверьте правильность запроса! ");
    }

    /**
     * Processes the user's request
     */
    public void requestForController(String request) throws IOException, TransformerException, ParserConfigurationException, SAXException {
        controller = new Controller();
        controller.requestForService(request);
    }
}

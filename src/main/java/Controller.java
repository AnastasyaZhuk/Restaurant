import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.logging.Logger;


public class Controller {
    private static Logger log = Logger.getLogger(Controller.class.getName());

    Model model;
    View view;

    Controller() {
    }

    Controller(Model model, View view) {
        this.model = model;
        this.view = view;
    }

    /**
     * Sends a request to perform model
     */
    public void requestForModel() throws IOException, TransformerException, ParserConfigurationException, SAXException {
        String request = new BufferedReader(new InputStreamReader(System.in)).readLine();
        model = new Model();
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.parse(new File("src/menu.xml"));
        Category category;
        Food food;
        if (request.equals("")) {
            log.info("Запрос не введен!");
        } else if (request.equalsIgnoreCase("show category")) {
            List<Category> categories = model.getAllCategory(document);
            log.info("Категории:");
            for (int i = 0; i < categories.size(); i++) {
                log.info(String.valueOf(categories.get(i).getName()));
            }
        } else if (request.equalsIgnoreCase("show dishes")) {
            log.info("Введите категорию:");
            BufferedReader nameCategory = new BufferedReader(new InputStreamReader(System.in));
            String setNameCategory = nameCategory.readLine();
            List<Food> allFood = model.getAllFood(document, setNameCategory);
            for (int i = 0; i < allFood.size(); i++) {
                System.out.println(allFood.get(i).getName() + " - " + allFood.get(i).getPrice() + "rub");
            }
        } else if (request.equalsIgnoreCase("new category")) {
            log.info("Введите название категории:");
            String setNameForNewCategory = new BufferedReader(new InputStreamReader(System.in)).readLine();
            category = new Category(setNameForNewCategory);

            model.createCategory(document, category);
            model.reformatXmlFile(document, "src/menu.xml");
        } else if (request.equalsIgnoreCase("new food")) {
            log.info("В какую категорию добавить новое блюдо?");
            String nameOfCategory = new BufferedReader(new InputStreamReader(System.in)).readLine();
            log.info("Название блюда:");
            category = new Category(nameOfCategory);
            String nameDishes = new BufferedReader(new InputStreamReader(System.in)).readLine();
            log.info("Установите цену:");
            int priceOfDishes = Integer.parseInt(new BufferedReader(new InputStreamReader(System.in)).readLine());
            food = new Food(nameDishes, category.getName(), priceOfDishes);

            model.createFood(document, food);
            model.reformatXmlFile(document, "src/menu.xml");


        } else if (request.equalsIgnoreCase("update category")) {
            log.info("Введите название категории, которое будет изменено");
            String oldNameOfCategory = new BufferedReader(new InputStreamReader(System.in)).readLine();
            log.info("Установите новое название: ");
            String newNameOfCategory = new BufferedReader(new InputStreamReader(System.in)).readLine();
            category = new Category(oldNameOfCategory);
            Category newCategory = new Category(newNameOfCategory);

            model.updateCategory(document, category, newCategory);
            model.reformatXmlFile(document, "src/menu.xml");

        } else if (request.equalsIgnoreCase("update food")) {
            log.info("Блюдо какой категории нужно изменить?");
            String updateFoodForCategory = new BufferedReader(new InputStreamReader(System.in)).readLine();
            log.info("Старое название блюда:");
            category = new Category(updateFoodForCategory);
            String oldNameOfFood = new BufferedReader(new InputStreamReader(System.in)).readLine();
            food = new Food(oldNameOfFood, category.getName());
            log.info("Установите новое название:");
            String newNameOfFood = new BufferedReader(new InputStreamReader(System.in)).readLine();
            Food newFood = new Food(newNameOfFood, category.getName());

            model.updateFood(document, food, newFood);
            model.reformatXmlFile(document, "src/menu.xml");

        } else if (request.equalsIgnoreCase("update price")) {
            log.info("Название блюда: ");
            String nameFood = new BufferedReader(new InputStreamReader(System.in)).readLine();
            log.info("Установите новую цену: ");
            int priceOfDishes = Integer.parseInt(new BufferedReader(new InputStreamReader(System.in)).readLine());
            food = new Food(nameFood, priceOfDishes);

            model.updatePrice(document, food);
            model.reformatXmlFile(document, "src/menu.xml");

        } else if (request.equalsIgnoreCase("delete category")) {
            log.info("Название категории, которую необходимо удалить");
            String categoryForDelete = new BufferedReader(new InputStreamReader(System.in)).readLine();
            category = new Category(categoryForDelete);

            model.removeCategory(document, category);
            model.reformatXmlFile(document, "src/menu.xml");
        } else if (request.equalsIgnoreCase("delete food")) {
            log.info("Блюдо какой категории нужно удалить?");
            String nameCategory = new BufferedReader(new InputStreamReader(System.in)).readLine();
            log.info("Название блюда для удаления");
            String foodForDelete = new BufferedReader(new InputStreamReader(System.in)).readLine();
            food = new Food(foodForDelete, nameCategory);

            model.removeFood(document, food);
            model.reformatXmlFile(document, "src/menu.xml");

        } else {
            log.info("Такой запрос не поддерживается! Проверьте правильность запроса! ");
        }
    }

    public void updateView(View view) {
    }
}

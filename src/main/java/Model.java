import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class Model implements DatabaseOperation {
    View view;
    Food food;
    Category category;

    /**
     * Return name of category
     */
    @Override
    public List<Category> getAllCategory(Document document) {
        NodeList nodeList = document.getElementsByTagName("nameCategory");
        ArrayList<Category> listOfCategory = new ArrayList<Category>();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Element element = (Element) nodeList.item(i);
            category = new Category(element.getAttributes().getNamedItem("name").getTextContent());
            listOfCategory.add(category);
        }
        return listOfCategory;
    }

    /**
     * Return the dish from the category
     */
    @Override
    public List<Food> getAllFood(Document document, String category) {
        NodeList nodeList = document.getElementsByTagName(category);
        List<Food> listOfFood = new ArrayList<Food>();
        for (int i = 0; i < nodeList.getLength(); i++) {
            food = new Food();
            Element element = (Element) nodeList.item(i);
            food.setName(element.getElementsByTagName("name").item(0).getChildNodes().item(0).getNodeValue());
            food.setPrice(Integer.parseInt(element.getElementsByTagName("price").item(0).getChildNodes().item(0).getNodeValue()));
            listOfFood.add(food);
        }
        return listOfFood;
    }

    /**
     * Create New Category
     */
    @Override
    public void createCategory(Document document, Category nameCategory) {
        Node firstChild = document.getFirstChild();
        Element newCategory = document.createElement("nameCategory");
        firstChild.appendChild(newCategory);
        newCategory.setAttribute("name", firstUpperCase(nameCategory.getName()));
    }

    /**
     * Adding a new dish in the category
     */
    @Override
    public void createFood(Document document, Food food) {
        NodeList nodeList = document.getElementsByTagName("nameCategory");
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node namedItem = nodeList.item(i).getAttributes().getNamedItem("name");
            if (((namedItem.getNodeValue()).equals(firstUpperCase(food.getCategory().getName())))) {
                Node elementsByTagName = document.getElementsByTagName("nameCategory").item(i);
                Element tagOfNameCategory = document.createElement(food.getCategory().getName());
                elementsByTagName.appendChild(tagOfNameCategory);

                Element nameOfFood = document.createElement("name");
                nameOfFood.appendChild(document.createTextNode(firstUpperCase(food.getName())));
                tagOfNameCategory.appendChild(nameOfFood);

                Element priceOfFood = document.createElement("price");
                priceOfFood.appendChild(document.createTextNode(String.valueOf(food.getPrice())));
                tagOfNameCategory.appendChild(priceOfFood);
            }
        }
    }

    /**
     * Changes the name of the category
     */
    @Override
    public void updateCategory(Document document, Category oldCategory, Category newCategory) {
        NodeList nodeList = document.getElementsByTagName("nameCategory");
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node namedItem = nodeList.item(i).getAttributes().getNamedItem("name");
            if (((namedItem.getNodeValue()).equalsIgnoreCase(oldCategory.getName()))) {
                Node elementsByTagName = document.getElementsByTagName("nameCategory").item(i);
                org.w3c.dom.NamedNodeMap map = elementsByTagName.getAttributes();
                Node nodeAttr = map.getNamedItem("name");
                nodeAttr.setTextContent(firstUpperCase(newCategory.getName()));

                NodeList list = document.getElementsByTagName(oldCategory.getName());
                for (int j = 0; j < list.getLength(); ) {
                    document.renameNode(list.item(j), "", newCategory.getName().toLowerCase());
                }
            }
        }
    }

    /**
     * Replacement named dishes
     */
    @Override
    public void updateFood(Document document, Food oldFood, Food newFood) {
        NodeList list = document.getElementsByTagName("name");
        for (int i = 0; i < list.getLength(); i++) {
            Node node = list.item(i);
            if (oldFood.getName().equals(node.getTextContent())) {
                node.setTextContent(newFood.getName());
            }
        }
    }

    /**
     * Replacement price of the food
     */
    @Override
    public void updatePrice(Document document, Food food) {
        NodeList list = document.getElementsByTagName("name");
        for (int i = 0; i < list.getLength(); i++) {
            Node node = list.item(i);
            if (food.getName().equals(node.getTextContent())) {
                Node node2 = document.getElementsByTagName("price").item(i);
                node2.setTextContent(String.valueOf(food.getPrice()));
            }
        }
    }

    /**
     * Deleting a category
     */
    @Override
    public void removeCategory(Document document, Category category) {
        NodeList nodeList = document.getElementsByTagName("nameCategory");
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node namedItem = nodeList.item(i).getAttributes().getNamedItem("name");
            if (((namedItem.getNodeValue()).equals(firstUpperCase(category.getName())))) {
                Element element = (Element) nodeList.item(i);
                Node parent = element.getParentNode();
                parent.removeChild(element);
                parent.normalize();
            }
        }
    }

    /**
     * Deleting a dish
     */
    @Override
    public void removeFood(Document document, Food food) {
        NodeList nodeList = document.getElementsByTagName(food.getCategory().getName().toLowerCase());
        for (int i = 0; i < nodeList.getLength(); i++) {
            NodeList nodeList2 = document.getElementsByTagName("name");
            for (int j = 0; j < nodeList2.getLength(); j++) {
                if (nodeList2.item(j).getTextContent().equals(firstUpperCase(food.getName()))) {
                    Element element = (Element) nodeList.item(i);
                    element.getParentNode().removeChild(element);
                }
            }
        }
    }

    /**
     * Replacement of the first letter to uppercase
     */
    public String firstUpperCase(String word) {
        if (word == null || word.isEmpty()) return "";
        return word.substring(0, 1).toUpperCase() + word.substring(1);
    }

    /**
     * Format a Xml file
     */
    public void reformatXmlFile(Document document, String nameOfDocument) throws TransformerException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
        DOMSource source = new DOMSource(document);
        StreamResult result = new StreamResult(new File(nameOfDocument));
        transformer.transform(source, result);
    }

}

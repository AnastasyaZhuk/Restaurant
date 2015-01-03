package DAO.XmlDaoImpl;

import DAO.Dao;
import model.Category;
import model.Food;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class XmlDaoFoodImpl implements Dao<Food> {

    String fileName = "src/menu.xml";

    @Override
    public void create(Food food) throws ParserConfigurationException, IOException, SAXException, TransformerException {
        Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new File(fileName));
        NodeList nodeList = document.getElementsByTagName("nameCategory");
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node namedItem = nodeList.item(i).getAttributes().getNamedItem("name");
            if (((namedItem.getNodeValue()).equals(XmlDaoCategoryImpl.firstUpperCase(food.getCategory().getName())))) {
                Node elementsByTagName = document.getElementsByTagName("nameCategory").item(i);
                Element tagOfNameCategory = document.createElement(food.getCategory().getName());
                elementsByTagName.appendChild(tagOfNameCategory);

                Element nameOfFood = document.createElement("name");
                nameOfFood.appendChild(document.createTextNode(XmlDaoCategoryImpl.firstUpperCase(food.getName())));
                tagOfNameCategory.appendChild(nameOfFood);

                Element priceOfFood = document.createElement("price");
                priceOfFood.appendChild(document.createTextNode(String.valueOf(food.getPrice())));
                tagOfNameCategory.appendChild(priceOfFood);
            }
        }
      XmlDaoCategoryImpl.reformatXmlFile(document,fileName);
    }

    @Override
    public void delete(Food food) throws ParserConfigurationException, IOException, SAXException, TransformerException {
        Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new File("src/menu.xml"));
        NodeList nodeList = document.getElementsByTagName(food.getCategory().getName().toLowerCase());
        for (int i = 0; i < nodeList.getLength(); i++) {
            NodeList nodeList2 = document.getElementsByTagName("name");
            for (int j = 0; j < nodeList2.getLength(); j++) {
                if (nodeList2.item(j).getTextContent().equals(XmlDaoCategoryImpl.firstUpperCase(food.getName()))) {
                    Element element = (Element) nodeList.item(i);
                    element.getParentNode().removeChild(element);
                }
            }
        }
        XmlDaoCategoryImpl.reformatXmlFile(document,fileName);
    }

    @Override
    public void update(Food food) throws ParserConfigurationException, IOException, SAXException, TransformerException {
        Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new File(fileName));
        NodeList list = document.getElementsByTagName("name");
        //Запрос во View: ввести новое название блюда
        String newNameForFood =  new BufferedReader(new InputStreamReader(System.in)).readLine();
        Food newFood = new Food(newNameForFood,food.getCategory().getName(),food.getPrice());
        for (int i = 0; i < list.getLength(); i++) {
            Node node = list.item(i);
            if (food.getName().equals(node.getTextContent())) {
                node.setTextContent(newFood.getName());
            }
        }
        XmlDaoCategoryImpl.reformatXmlFile(document,fileName);
    }

    @Override
    public List<Food> getAll() throws ParserConfigurationException, IOException, SAXException {
        Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new File("src/menu.xml"));
        Food food;
        //Запрос во View: ввести название категории
        String nameCategory =  new BufferedReader(new InputStreamReader(System.in)).readLine();
        Category category = new Category(nameCategory);

        NodeList nodeList = document.getElementsByTagName(category.getName());
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
}

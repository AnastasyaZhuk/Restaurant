package dao.xml.sax;


import dao.Dao;
import model.Category;
import model.Food;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class XmlSaxDaoCategoryImpl implements Dao<Category, Food> {


    @Override
    public void create(Category entity) throws ParserConfigurationException, IOException, SAXException, TransformerException {

    }

    @Override
    public void delete(Category entity) throws ParserConfigurationException, IOException, SAXException, TransformerException {

    }

    @Override
    public void update(Category entity1, Category entity2) throws ParserConfigurationException, IOException, SAXException, TransformerException {

    }

    @Override
    public List<Category> getAll() throws ParserConfigurationException, IOException, SAXException {
        SAXParserFactory parserFactory = SAXParserFactory.newInstance();
        Handler handler = new Handler();
        SAXParser saxParser = parserFactory.newSAXParser();
        saxParser.parse(new File("src/menu.xml"), handler);
        List<String> list = handler.getList();
        Category category;
        List<Category> listOfCategory = new ArrayList<Category>();
        for (int i = 0; i < list.size(); i++) {
            category = new Category(list.get(i));
            listOfCategory.add(category);
        }
        return listOfCategory;
    }

    @Override
    public List<Category> getBy(Food entity) throws ParserConfigurationException, IOException, SAXException {
        return null;
    }
}

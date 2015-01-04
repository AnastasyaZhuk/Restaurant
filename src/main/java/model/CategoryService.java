package model;

import DAO.XmlDaoImpl.XmlDaoCategoryImpl;
import org.xml.sax.SAXException;
import view.View;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class CategoryService implements ICategoryService {

    private View view = new View();
    private XmlDaoCategoryImpl xmlDaoCategory = new XmlDaoCategoryImpl();

    @Override
    public void updateCategory() throws IOException, ParserConfigurationException, SAXException {
        view.nameCategory();
        String nameCategory = new BufferedReader(new InputStreamReader(System.in)).readLine();

        Category category = new Category(nameCategory);
        xmlDaoCategory.update(category);
    }

    @Override
    public void removeCategory() throws IOException, TransformerException, SAXException, ParserConfigurationException {
        view.nameCategory();
        String nameCategory = new BufferedReader(new InputStreamReader(System.in)).readLine();
        Category category = new Category(nameCategory);
        xmlDaoCategory.delete(category);
    }

    @Override
    public void getAllCategories() throws IOException, SAXException, ParserConfigurationException {
        List<Category> listOfCategories = xmlDaoCategory.getAll();
        view.showAllCategory(listOfCategories);
    }

    @Override
    public void createCategory() throws IOException, TransformerException, SAXException, ParserConfigurationException {
        view.nameCategory();
        String nameCategory = new BufferedReader(new InputStreamReader(System.in)).readLine();
        Category category = new Category(nameCategory);
        xmlDaoCategory.create(category);
    }
}

package services;

import dao.DaoFactory;
import dao.xml.XmlDaoCategoryImpl;
import model.Category;
import org.xml.sax.SAXException;
import view.View;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.List;

public class CategoryServiceImpl implements CategoryService, DaoFactory<XmlDaoCategoryImpl> {

    private View view = new View();

    @Override
    public void updateCategory() throws IOException, ParserConfigurationException, SAXException, TransformerException {
        String nameCategory = view.nameCategory();
        Category oldCategory = new Category(nameCategory);
        String newName = view.newNameForCategory();
        Category newCategory = new Category(newName);
        getDaoXml().update(oldCategory, newCategory);
    }

    @Override
    public void removeCategory() throws IOException, TransformerException, SAXException, ParserConfigurationException {
        String nameCategory = view.nameCategory();
        Category category = new Category(nameCategory);
        getDaoXml().delete(category);
    }

    @Override
    public void getAllCategories() throws IOException, SAXException, ParserConfigurationException {
        List<Category> listOfCategories = getDaoXml().getAll();
        view.showAllCategory(listOfCategories);
    }

    @Override
    public void createCategory() throws IOException, TransformerException, SAXException, ParserConfigurationException {
        String nameCategory = view.nameCategory();
        Category category = new Category(nameCategory);
        getDaoXml().create(category);
    }

    @Override
    public XmlDaoCategoryImpl getDaoXml() {
        return new XmlDaoCategoryImpl();
    }
}

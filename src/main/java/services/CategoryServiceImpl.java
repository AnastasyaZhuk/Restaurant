package services;

import dao.Dao;
import dao.DaoFactory;
import dao.xml.XmlDaoCategoryImpl;
import dao.xml.XmlDaoFoodImpl;
import model.Category;
import org.xml.sax.SAXException;
import view.View;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.List;

public class CategoryServiceImpl implements CategoryService, DaoFactory<XmlDaoCategoryImpl<Dao>, XmlDaoFoodImpl<Dao>> {

    private View view = new View();

    @Override
    public void updateCategory() throws IOException, ParserConfigurationException, SAXException, TransformerException {
        String nameCategory = view.nameCategory();
        Category oldCategory = new Category(nameCategory);
        String newName = view.newNameForCategory();
        Category newCategory = new Category(newName);
        getCategoryDao().update(oldCategory, newCategory);
    }

    @Override
    public void removeCategory() throws IOException, TransformerException, SAXException, ParserConfigurationException {
        String nameCategory = view.nameCategory();
        Category category = new Category(nameCategory);
        getCategoryDao().delete(category);
    }

    @Override
    public void getAllCategories() throws IOException, SAXException, ParserConfigurationException {
        List<Category> listOfCategories = getCategoryDao().getAll();
        view.showAllCategory(listOfCategories);
    }

    @Override
    public void createCategory() throws IOException, TransformerException, SAXException, ParserConfigurationException {
        String nameCategory = view.nameCategory();
        Category category = new Category(nameCategory);
        getCategoryDao().create(category);
    }


    @Override
    public XmlDaoCategoryImpl<Dao> getCategoryDao() {
        XmlDaoCategoryImpl<Dao> xmlDaoCategory = new XmlDaoCategoryImpl<Dao>();
        return xmlDaoCategory;
    }

    @Override
    public XmlDaoFoodImpl<Dao> getFoodDao() {
        XmlDaoFoodImpl<Dao> xmlDaoFood = new XmlDaoFoodImpl<Dao>();
        return xmlDaoFood;
    }
}

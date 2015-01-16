package services;

import dao.Dao;
import dao.DaoFactory;
import dao.sql.SqlDaoImpl;
import dao.xml.XmlDaoCategoryImpl;
import model.Category;
import org.xml.sax.SAXException;
import view.View;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.List;

public class CategoryServiceImpl implements CategoryService, DaoFactory {

    private View view = new View();
    private XmlDaoCategoryImpl xmlDaoCategory;
    private SqlDaoImpl sqlDao;

    @Override
    public void updateCategory() throws IOException, ParserConfigurationException, SAXException, TransformerException {
        String nameCategory = view.nameCategory();
        Category oldCategory = new Category(nameCategory);
        String newName = view.newNameForCategory();
        Category newCategory = new Category(newName);
        getDaoXml();
       // xmlDaoCategory.update(oldCategory, newCategory);
        sqlDao.update(oldCategory, newCategory);
    }

    @Override
    public void removeCategory() throws IOException, TransformerException, SAXException, ParserConfigurationException {
        String nameCategory = view.nameCategory();
        Category category = new Category(nameCategory);
        getDaoXml();
       // xmlDaoCategory.delete(category);
        sqlDao.delete(category);
    }

    @Override
    public void getAllCategories() throws IOException, SAXException, ParserConfigurationException {
        getDaoXml();
      //  List<Category> listOfCategories = xmlDaoCategory.getAll();
        List<Category> listOfCategories = sqlDao.getAll();
        view.showAllCategory(listOfCategories);
    }

    @Override
    public void createCategory() throws IOException, TransformerException, SAXException, ParserConfigurationException {
        String nameCategory = view.nameCategory();
        Category category = new Category(nameCategory);
        getDaoXml();
       // xmlDaoCategory.create(category);
        sqlDao.create(category);
    }

    @Override
    public Dao getDaoXml() {
        if(sqlDao == null) {
            this.sqlDao = new SqlDaoImpl();
        }
        return sqlDao;
    }
}

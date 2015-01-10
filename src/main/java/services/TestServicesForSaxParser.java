package services;

import dao.Dao;
import dao.DaoFactory;
import dao.xml.sax.XmlSaxDaoCategoryImpl;
import model.Category;
import org.xml.sax.SAXException;
import view.View;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.List;


public class TestServicesForSaxParser implements CategoryService, DaoFactory {
    private XmlSaxDaoCategoryImpl xmlSaxDaoCategory;
    private View view = new View();

    @Override
    public Dao getDaoXml() {
        if (xmlSaxDaoCategory == null) {
            this.xmlSaxDaoCategory = new XmlSaxDaoCategoryImpl();
        }
        return xmlSaxDaoCategory;
    }


    @Override
    public void updateCategory() throws IOException, ParserConfigurationException, SAXException, TransformerException {

    }

    @Override
    public void removeCategory() throws IOException, TransformerException, SAXException, ParserConfigurationException {

    }

    @Override
    public void getAllCategories() throws IOException, SAXException, ParserConfigurationException {
        getDaoXml();
        List<Category> listOfCategories = xmlSaxDaoCategory.getAll();
        view.showAllCategory(listOfCategories);
    }

    @Override
    public void createCategory() throws TransformerException, SAXException, ParserConfigurationException, IOException {

    }
}

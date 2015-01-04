package model;

import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;

public interface ICategoryService {
    public void updateCategory() throws IOException, ParserConfigurationException, SAXException;

    public void removeCategory() throws IOException, TransformerException, SAXException, ParserConfigurationException;

    public void getAllCategories() throws IOException, SAXException, ParserConfigurationException;

    public void createCategory() throws TransformerException, SAXException, ParserConfigurationException, IOException;
}

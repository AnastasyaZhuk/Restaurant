package services;

import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;

public interface CategoryService {
    public void updateCategory() throws IOException, ParserConfigurationException, SAXException, TransformerException;

    public void removeCategory() throws IOException, TransformerException, SAXException, ParserConfigurationException;

    public void getAllCategories() throws IOException, SAXException, ParserConfigurationException;

    public void createCategory() throws TransformerException, SAXException, ParserConfigurationException, IOException;
}

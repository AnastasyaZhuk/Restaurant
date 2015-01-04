package model;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;

public interface IFoodService
{

    public void updateFood() throws IOException, ParserConfigurationException, SAXException;

    public void removeFood() throws IOException, TransformerException, SAXException, ParserConfigurationException;

    public void getAllDishes() throws IOException, SAXException, ParserConfigurationException;

    public void createFood() throws TransformerException, SAXException, ParserConfigurationException, IOException;
}

package DAO;

import model.Entity;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.List;

public interface Dao<E extends Entity> {

    public void create(E entity) throws ParserConfigurationException, IOException, SAXException, TransformerException;

    public void delete(E entity) throws ParserConfigurationException, IOException, SAXException, TransformerException;

    public void update(E entity) throws ParserConfigurationException, IOException, SAXException, TransformerException;

    public List<E> getAll() throws ParserConfigurationException, IOException, SAXException;

}

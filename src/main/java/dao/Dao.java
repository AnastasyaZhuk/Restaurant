package dao;

import model.Entity;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.List;

public interface Dao<E,T extends Entity> {

    public void create(E entity) throws ParserConfigurationException, IOException, SAXException, TransformerException;

    public void delete(E entity) throws ParserConfigurationException, IOException, SAXException, TransformerException;

    public void update(E entity1,E entity2) throws ParserConfigurationException, IOException, SAXException, TransformerException;

    public List<E> getAll() throws ParserConfigurationException, IOException, SAXException;

    public List<E> getBy(T entity) throws ParserConfigurationException, IOException, SAXException;


}

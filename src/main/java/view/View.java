package view;

import controller.Controller;
import model.Model;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.logging.Logger;

public class View {
    private static Logger log = Logger.getLogger(View.class.getName());

   private Controller controller;
   private Model model;

    /**
     * Display System
     */
    public void display() throws IOException, TransformerException, ParserConfigurationException, SAXException {
        log.info("Добро пожаловать в ресторан!");
        log.info("Введите запрос:");
        requestForController();


    }

    /**
     * Processes the user's request
     */
    public void requestForController() throws IOException, TransformerException, ParserConfigurationException, SAXException {
        controller = new Controller();
        controller.requestForModel();

    }
}

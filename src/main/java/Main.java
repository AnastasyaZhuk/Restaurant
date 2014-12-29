import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, TransformerException, ParserConfigurationException, SAXException {
        View view = new View();
        view.display();
    }
}

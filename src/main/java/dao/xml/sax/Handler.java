package dao.xml.sax;

import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;


public class Handler extends DefaultHandler {
    private String nameCategory;
    private String element;
    private List<String> list = new ArrayList<String>();

    public void startElement(String uri, String localName, String qName, org.xml.sax.Attributes atts) {
        element = qName;
        if (element.equals("nameCategory")) {
            nameCategory = atts.getValue(0);
            list.add(nameCategory);
        }
    }

    public void endElement(String namespace, String localname, String qName) {
        element = "";
        nameCategory = "";
    }

    public void characters(char[] ch, int start, int end) {

    }

    public List<String> getList() {
        return list;
    }
}

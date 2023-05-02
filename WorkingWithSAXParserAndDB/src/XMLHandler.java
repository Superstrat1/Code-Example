import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class XMLHandler extends DefaultHandler {

    private boolean isVoterExist;
    private String voterName;
    private String voterBirthDate;


    public XMLHandler() {
        isVoterExist = false;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

        if (qName == "voter" && isVoterExist == false) {
            isVoterExist = true;
            voterName = attributes.getValue("name");
            voterBirthDate = attributes.getValue("birthDay");
        } else if (qName == "visit" && isVoterExist == true) {
            try {
                DBConnection.countVoter(voterName, voterBirthDate);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {

        if (qName == "voter") {
            isVoterExist = false;
            voterName = "";
            voterBirthDate = "";
        }

    }

    @Override
    public void endDocument() throws SAXException {
        try {
            DBConnection.executeMultiInsert();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}

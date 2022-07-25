package com.madlab.dataparser;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.madlab.dataparser.databinding.ActivityViewDataBinding;

import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class ViewDataActivity extends AppCompatActivity {
    ActivityViewDataBinding viewDataBinding;
    int mode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewDataBinding = ActivityViewDataBinding.inflate(getLayoutInflater());
        setContentView(viewDataBinding.getRoot());

        mode = getIntent().getIntExtra("mode", 0);
        if (mode == 1) {
            parseJSON();
        } else if (mode == 2) {
            parseXMLShortened();
        }
    }

    private void parseJSON() {
        try {
            InputStream inputStream = getAssets().open("weather.json");
            byte[] data = new byte[inputStream.available()];
            inputStream.read(data);
            String readData = new String(data);
            JSONObject jsonObject = new JSONObject(readData);
            JSONObject cityObject = jsonObject.getJSONObject("City");

            String city = "City Name:" + cityObject.getString("Name") + "\n" +
                    "Latitude:" + cityObject.getString("Latitude") + "\n" +
                    "Longitude:" + cityObject.getString("Longitude") + "\n" +
                    "Temperature:" + cityObject.getInt("Temperature") + "\n " +
                    "Humidity:" + cityObject.getString("Humidity") + "\n";

            viewDataBinding.jsonData.setText(city);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // For Only One "City" Node
    private void parseXMLShortened() {
        try {
            InputStream inputStream = getAssets().open("weather.xml");
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = builder.parse(inputStream);
            document.getDocumentElement().normalize();

            NodeList list = document.getElementsByTagName("City");
            Element item = (Element) list.item(0);
            String city = "City Name : " + getValue("Name", item) + "\n" +
                    "Latitude : " + getValue("Latitude", item) + "\n" +
                    "Longitude : " + getValue("Longitude", item) + "\n" +
                    "Temperature : " + getValue("Temperature", item) + "\n" +
                    "Humidity : " + getValue("Humidity", item) + "\n";
            viewDataBinding.xmlData.setText(city);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // For Multiple "City" Nodes
    private void parseXML() {
        try {
            InputStream inputStream = getAssets().open("weather.xml");
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = builder.parse(inputStream);
            document.getDocumentElement().normalize();

            NodeList list = document.getElementsByTagName("City");
            viewDataBinding.xmlData.setText("");
            for (int i = 0; i < list.getLength(); i++) {
                Node node = list.item(i);
                String city = "";
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Log.d("View", "I: " + i);
                    Element item = (Element) node;
                    city += "City Name : " + getValue("Name", item) + "\n" +
                            "Latitude : " + getValue("Latitude", item) + "\n" +
                            "Longitude : " + getValue("Longitude", item) + "\n" +
                            "Temperature : " + getValue("Temperature", item) + "\n" +
                            "Humidity : " + getValue("Humidity", item) + "\n";
                }
                city += "\n\n";
                viewDataBinding.xmlData.append(city);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String getValue(String tag, Element element) {
        return element.getElementsByTagName(tag).item(0).getChildNodes().item(0).getNodeValue();
    }

}
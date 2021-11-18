package com.Lab2.conveter;

import javax.swing.*;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class Converter {
    public static void convert(){
        try {
            TransformerFactory tFactory=TransformerFactory.newInstance();

            Source xslDoc=new StreamSource("src\\main\\java\\com\\files\\XSLPattern.xsl");
            Source xmlDoc=new StreamSource("src\\main\\java\\com\\files\\table.xml");

            String outputFileName="src\\main\\java\\com\\files\\HTMLFile.html";

            OutputStream htmlFile=new FileOutputStream(outputFileName);
            Transformer transform=tFactory.newTransformer(xslDoc);
            transform.transform(xmlDoc, new StreamResult(htmlFile));
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(null,"ERROR in converting");
        }
    }
}

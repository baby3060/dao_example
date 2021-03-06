package net.example.common;

import java.io.IOException;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import javax.xml.xpath.XPathExpressionException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

public class XMLParsingConfig {
    
    public ConnectionBean setConfig(final String fileName) throws Exception, XPathExpressionException{
        // 설정 파일 위치 읽어오기

        ConnectionBean bean = null;

        String className = "";
        String host = "";
        String databaseName = "";
        String userName = "";
        String userPass = "";
        try {
            URL url = ClassLoader.getSystemClassLoader().getResource(fileName);

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = factory.newDocumentBuilder();
    
            Document document = documentBuilder.parse(url.getFile());
    
            // xpath 생성
            XPath xpath = XPathFactory.newInstance().newXPath();
    
            Node classNameNode = (Node)xpath.evaluate("//*[@name='className']", document, XPathConstants.NODE);
            className = classNameNode.getAttributes().getNamedItem("value").getTextContent();
    
            Node hostNode = (Node)xpath.evaluate("//*[@name='host']", document, XPathConstants.NODE);
            host = hostNode.getAttributes().getNamedItem("value").getTextContent();
    
            Node databaseNameNode = (Node)xpath.evaluate("//*[@name='databaseName']", document, XPathConstants.NODE);
            databaseName = databaseNameNode.getAttributes().getNamedItem("value").getTextContent();
    
            Node userNameNode = (Node)xpath.evaluate("//*[@name='userName']", document, XPathConstants.NODE);
            userName = userNameNode.getAttributes().getNamedItem("value").getTextContent();
    
            Node userPassNode = (Node)xpath.evaluate("//*[@name='userPass']", document, XPathConstants.NODE);
            userPass = userPassNode.getAttributes().getNamedItem("value").getTextContent();

            bean = new ConnectionBean(className, host, databaseName, userName, userPass);
        } catch(ParserConfigurationException | SAXException | IOException e) {
            throw new Exception(e);
        } catch(XPathExpressionException ie) {
            ie.printStackTrace();
        }

        return bean;
    }
}
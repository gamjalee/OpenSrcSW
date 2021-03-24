package Week2;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.jsoup.Jsoup;
//import org.jsoup.Jsoup.1.13.1;
import org.jsoup.select.Elements;

public class Week2 {
//XML : element ��ü
    public static void main(String[] args)throws IOException{

        int num = 0;
        File path = new File("src/htmls");//src/?? html
        File[] files = path.listFiles();
        int fileNum = files.length;

        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

        Document documentt = docBuilder.newDocument();

        // book element -> docs element
        Element docs = documentt.createElement("docs");
        documentt.appendChild(docs);

        for(int i=0;i<fileNum;i++)
        {

            Element doc = documentt.createElement("doc");
            documentt.appendChild(doc);

            doc setAttribute("id", i);

            Element title = documentt.createElement("title");
            //title.appendChild(documentt.createTextNode("div id"));
            Element title2 = documentt.getElementById("content")
            //not working-> Element title2 = documentt.getElementById(content)
            //not working-> Element title2 = documentt.getElementByTag(String tag)
            title.appendChild(documentt.createTextNode(title2));
            //title.appendChild(documentt.createTextNode("div id"));
            doc.appendChild(title);

            Element body = documentt.createElement("body");
            Element body2 = documentt.getElementByTag("p");

            body.appendChild(documentt.createTextNode(body2));
            //body.appendChild(documentt.createTextNode("p태그 내용"));
            doc.appendChild(body);

            /*Element doc = doc.select("p");
            doc.appendChild(body);*/
        }


        // code element
        /*Element movie = doc.createElement("code");
        doc.appendChild(code);

        //�Ӽ��� type = SF
        code setAttribute("type","SF");

        // name Element
        Element movieName = doc.createElement("movieName");
        movieName.appendChild(doc.createTextNode("�׳�"));
        code.appendChild(movieName);

        // movieDirector element
        Element movieDirector = doc.createElement("movieDirector");
        movieDirector.appendChild(doc.createTextNode("ũ�������� ���"));
        code.appendChild(movieDirector);
        */
    /*
    <movie>
        <code type="SF">
            <movieName>�׳�</movieName>
            <movieDirector>ũ�������� ���</movieDirector>
        </code>
    </movie>
    */

    //XML : file write

        TransformerFactory TransformerFactory = TransformerFactory.newInstance();

        Transformer transformer = TransformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.ENCODING,"UTF-8");

        DOMSource source = new DOMSource(documentt);
    //StreamResult result = new StreamResult(new FileOutputStream(new File("src/data/book.xml")));

        StreamResult result = new StreamResult(new FileOutputStream(new File("src/collection.xml")));

        transformer.transform(source,result);
    }


}
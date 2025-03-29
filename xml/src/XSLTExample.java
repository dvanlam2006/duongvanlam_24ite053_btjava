

import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;

public class XSLTExample {
    public static void main(String[] args) {
        try {

            TransformerFactory factory = TransformerFactory.newInstance();

            Source xslt = new StreamSource(new File("product.xsl"));
            Transformer transformer = factory.newTransformer(xslt);

            Source xml = new StreamSource(new File("product.xml"));
            transformer.transform(xml, new StreamResult(new File("output.html")));
            System.out.println("Transformation completed.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}




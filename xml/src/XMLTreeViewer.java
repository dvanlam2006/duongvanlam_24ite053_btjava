

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XMLTreeViewer {
    public static void main(String[] args) {
        try {

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse("product.xml");


            DefaultMutableTreeNode root = createTree(document.getDocumentElement());

            JTree tree = new JTree(root);
            JFrame frame = new JFrame("XML Tree Viewer");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(new JScrollPane(tree));
            frame.setSize(300, 400);
            frame.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static DefaultMutableTreeNode createTree(Node node) {
        DefaultMutableTreeNode treeNode = new DefaultMutableTreeNode(node.getNodeName());
        NodeList nodeList = node.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node childNode = nodeList.item(i);
            if (childNode.getNodeType() == Node.ELEMENT_NODE) {
                treeNode.add(createTree(childNode));
            }
        }
        return treeNode;
    }
}



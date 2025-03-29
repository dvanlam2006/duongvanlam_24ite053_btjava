import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class LibraryManagementGUI {
    private JFrame frame;
    private JTable table;
    private DefaultTableModel tableModel;
    private JTextField txtTitle, txtAuthor, txtYear, txtPublisher, txtPages, txtGenre, txtPrice, txtId;
    private JButton btnAdd, btnUpdate, btnDelete;
    private File xmlFile = new File("library.xml");

    public LibraryManagementGUI() {
        frame = new JFrame("Library Management");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(new JLabel("Book ID:"), gbc);
        gbc.gridx = 1;
        panel.add(txtId = new JTextField(15), gbc);

        gbc.gridx = 2;
        panel.add(new JLabel("Title:"), gbc);
        gbc.gridx = 3;
        panel.add(txtTitle = new JTextField(15), gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(new JLabel("Author:"), gbc);
        gbc.gridx = 1;
        panel.add(txtAuthor = new JTextField(15), gbc);

        gbc.gridx = 2;
        panel.add(new JLabel("Publisher:"), gbc);
        gbc.gridx = 3;
        panel.add(txtPublisher = new JTextField(15), gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(new JLabel("Year:"), gbc);
        gbc.gridx = 1;
        panel.add(txtYear = new JTextField(15), gbc);

        gbc.gridx = 2;
        panel.add(new JLabel("Pages:"), gbc);
        gbc.gridx = 3;
        panel.add(txtPages = new JTextField(15), gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        panel.add(new JLabel("Genre:"), gbc);
        gbc.gridx = 1;
        panel.add(txtGenre = new JTextField(15), gbc);

        gbc.gridx = 2;
        panel.add(new JLabel("Price:"), gbc);
        gbc.gridx = 3;
        panel.add(txtPrice = new JTextField(15), gbc);

        gbc.gridx = 1; gbc.gridy = 4;
        panel.add(btnAdd = new JButton("Add"), gbc);
        gbc.gridx = 2;
        panel.add(btnUpdate = new JButton("Update"), gbc);
        gbc.gridx = 3;
        panel.add(btnDelete = new JButton("Delete"), gbc);

        tableModel = new DefaultTableModel(new String[]{"ID", "Title", "Author", "Year", "Publisher", "Pages", "Genre", "Price"}, 0);
        table = new JTable(tableModel);
        loadXMLData();

        btnAdd.addActionListener(e -> addBook());
        btnUpdate.addActionListener(e -> updateBook());
        btnDelete.addActionListener(e -> deleteBook());

        frame.setLayout(new BorderLayout());
        frame.add(panel, BorderLayout.NORTH);
        frame.add(new JScrollPane(table), BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private void addBook() {
        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc;
            Element root;

            if (!xmlFile.exists()) {
                doc = builder.newDocument();
                root = doc.createElement("library");
                doc.appendChild(root);
            } else {
                doc = builder.parse(xmlFile);
                root = doc.getDocumentElement();
            }

            Element book = doc.createElement("book");
            book.setAttribute("id", txtId.getText());

            Element title = doc.createElement("title");
            title.setTextContent(txtTitle.getText());
            book.appendChild(title);

            Element author = doc.createElement("author");
            author.setTextContent(txtAuthor.getText());
            book.appendChild(author);

            Element year = doc.createElement("year");
            year.setTextContent(txtYear.getText());
            book.appendChild(year);

            Element publisher = doc.createElement("publisher");
            publisher.setTextContent(txtPublisher.getText());
            book.appendChild(publisher);

            Element pages = doc.createElement("pages");
            pages.setTextContent(txtPages.getText());
            book.appendChild(pages);

            Element genre = doc.createElement("genre");
            genre.setTextContent(txtGenre.getText());
            book.appendChild(genre);

            Element price = doc.createElement("price");
            price.setTextContent(txtPrice.getText());
            book.appendChild(price);

            root.appendChild(book);
            saveXML(doc);
            loadXMLData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void loadXMLData() {
        try {
            if (!xmlFile.exists()) return;
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = builder.parse(xmlFile);
            NodeList list = doc.getElementsByTagName("book");

            tableModel.setRowCount(0);
            for (int i = 0; i < list.getLength(); i++) {
                Element book = (Element) list.item(i);
                tableModel.addRow(new Object[]{
                        book.getAttribute("id"),
                        book.getElementsByTagName("title").item(0).getTextContent(),
                        book.getElementsByTagName("author").item(0).getTextContent(),
                        book.getElementsByTagName("year").item(0).getTextContent(),
                        book.getElementsByTagName("publisher").item(0).getTextContent(),
                        book.getElementsByTagName("pages").item(0).getTextContent(),
                        book.getElementsByTagName("genre").item(0).getTextContent(),
                        book.getElementsByTagName("price").item(0).getTextContent()
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void deleteBook() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) return;
        String bookId = (String) tableModel.getValueAt(selectedRow, 0);

        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = builder.parse(xmlFile);
            NodeList list = doc.getElementsByTagName("book");
            for (int i = 0; i < list.getLength(); i++) {
                Element book = (Element) list.item(i);
                if (book.getAttribute("id").equals(bookId)) {
                    book.getParentNode().removeChild(book);
                    break;
                }
            }
            saveXML(doc);
            loadXMLData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateBook() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(frame, "Please select a book to update!", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String bookId = (String) tableModel.getValueAt(selectedRow, 0);

        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = builder.parse(xmlFile);
            NodeList list = doc.getElementsByTagName("book");

            Element selectedBook = null;

            for (int i = 0; i < list.getLength(); i++) {
                Element book = (Element) list.item(i);
                if (book.getAttribute("id").equals(bookId)) {
                    selectedBook = book;
                    break;
                }
            }

            if (selectedBook != null) {

                selectedBook.getElementsByTagName("title").item(0).setTextContent(txtTitle.getText());
                selectedBook.getElementsByTagName("author").item(0).setTextContent(txtAuthor.getText());
                selectedBook.getElementsByTagName("year").item(0).setTextContent(txtYear.getText());
                selectedBook.getElementsByTagName("publisher").item(0).setTextContent(txtPublisher.getText());
                selectedBook.getElementsByTagName("pages").item(0).setTextContent(txtPages.getText());
                selectedBook.getElementsByTagName("genre").item(0).setTextContent(txtGenre.getText());
                selectedBook.getElementsByTagName("price").item(0).setTextContent(txtPrice.getText());

                saveXML(doc);
                loadXMLData();
                JOptionPane.showMessageDialog(frame, "Book updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(frame, "Book ID not found in XML!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void saveXML(Document doc) throws TransformerException {
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.transform(new DOMSource(doc), new StreamResult(xmlFile));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(LibraryManagementGUI::new);
    }
}

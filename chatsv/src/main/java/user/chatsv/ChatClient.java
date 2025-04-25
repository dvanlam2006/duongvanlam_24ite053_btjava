package user.chatsv;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.*;
import java.net.Socket;

public class ChatClient extends Application {
    private TextArea chatArea;
    private TextField messageField, ipField, portField, nameField;
    private Button sendButton, connectButton, newWindowButton;
    private Socket socket;
    private PrintWriter out;
    private String clientName;

    @Override
    public void start(Stage primaryStage) {
        // UI Components
        chatArea = new TextArea();
        chatArea.setEditable(false);
        chatArea.setWrapText(true);

        messageField = new TextField();
        messageField.setPromptText("Nhập tin nhắn...");

        ipField = new TextField("127.0.0.1");
        portField = new TextField("12345");
        nameField = new TextField("User" + (int)(Math.random()*1000));
        nameField.setPromptText("Tên của bạn");

        sendButton = new Button("Gửi");
        sendButton.setDisable(true);
        sendButton.setOnAction(e -> sendMessage());

        connectButton = new Button("Kết nối");
        connectButton.setOnAction(e -> connectToServer());

        newWindowButton = new Button("Mở cửa sổ mới");
        newWindowButton.setOnAction(e -> openNewWindow());

        GridPane connectionPane = new GridPane();
        connectionPane.setHgap(10);
        connectionPane.setVgap(5);
        connectionPane.addRow(0, new Label("IP:"), ipField, new Label("Port:"), portField);
        connectionPane.addRow(1, new Label("Tên:"), nameField, connectButton, newWindowButton);

        HBox messagePane = new HBox(10, messageField, sendButton);

        VBox root = new VBox(10, connectionPane, chatArea, messagePane);
        root.setPadding(new Insets(10));

        Scene scene = new Scene(root, 500, 400);

        primaryStage.setTitle("Chat Client - " + nameField.getText());
        primaryStage.setScene(scene);
        primaryStage.setOnCloseRequest(e -> disconnectFromServer());
        primaryStage.show();
    }

    private void connectToServer() {
        String ip = ipField.getText();
        int port = Integer.parseInt(portField.getText());
        clientName = nameField.getText().isEmpty() ? "Khách" : nameField.getText();

        try {
            socket = new Socket(ip, port);
            out = new PrintWriter(socket.getOutputStream(), true);

            out.println(clientName);

            new Thread(this::receiveMessages).start();

            sendButton.setDisable(false);
            connectButton.setDisable(true);
            ipField.setDisable(true);
            portField.setDisable(true);
            nameField.setDisable(true);

            appendToChat("Bạn đã kết nối với tên: " + clientName);
        } catch (IOException e) {
            appendToChat("Lỗi kết nối: " + e.getMessage());
        }
    }

    private void disconnectFromServer() {
        try {
            if (out != null) {
                out.println("quit");
            }
            if (socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendMessage() {
        String msg = messageField.getText();
        if (out != null && !msg.isEmpty()) {
            out.println(msg);
            appendToChat("Bạn: " + msg);
            messageField.clear();
        }
    }

    private void receiveMessages() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            String line;
            while ((line = in.readLine()) != null) {
                appendToChat(line);
            }
        } catch (IOException e) {
            appendToChat("Mất kết nối server");
        } finally {
            Platform.runLater(() -> {
                sendButton.setDisable(true);
                connectButton.setDisable(false);
                ipField.setDisable(false);
                portField.setDisable(false);
                nameField.setDisable(false);
            });
        }
    }

    private void appendToChat(String message) {
        Platform.runLater(() -> chatArea.appendText(message + "\n"));
    }

    private void openNewWindow() {
        try {
            ChatClient newClient = new ChatClient();
            Stage newStage = new Stage();
            newClient.start(newStage);
        } catch (Exception e) {
            appendToChat("Lỗi mở cửa sổ: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
public class EncryptionApp extends JFrame {
    private AESEncryption aesEncryption;
    private RSAEncryption rsaEncryption;
    private ExecutorService executorService;

    // Tabs
    private JTabbedPane tabbedPane;
    private JPanel passwordHashPanel;
    private JPanel aesPanel;
    private JPanel rsaPanel;
    private JPanel threadedPanel;

    public EncryptionApp() {
        super("Ứng dụng Mã hóa");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 550);
        setLocationRelativeTo(null);

        try {
            // Khởi tạo các thuật toán mã hóa
            aesEncryption = new AESEncryption();
            rsaEncryption = new RSAEncryption();

            // Khởi tạo thread pool
            executorService = Executors.newFixedThreadPool(4);

            // Khởi tạo giao diện người dùng
            initUI();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi khởi tạo: " + e.getMessage(),
                    "Lỗi", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private void initUI() {
        tabbedPane = new JTabbedPane();

        // Tab mã hóa mật khẩu
        passwordHashPanel = createPasswordHashPanel();
        tabbedPane.addTab("Mã hóa Mật khẩu", passwordHashPanel);

        // Tab mã hóa AES
        aesPanel = createAESPanel();
        tabbedPane.addTab("Mã hóa AES", aesPanel);

        // Tab mã hóa RSA
        rsaPanel = createRSAPanel();
        tabbedPane.addTab("Mã hóa RSA", rsaPanel);

        // Tab mã hóa đa luồng
        threadedPanel = createThreadedPanel();
        tabbedPane.addTab("Mã hóa Đa luồng", threadedPanel);

        add(tabbedPane);
    }

    private JPanel createPasswordHashPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        // Nhập mật khẩu
        JLabel passwordLabel = new JLabel("Mật khẩu:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        inputPanel.add(passwordLabel, gbc);

        JTextField passwordField = new JTextField(30);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        inputPanel.add(passwordField, gbc);

        // Chọn thuật toán
        JLabel algorithmLabel = new JLabel("Thuật toán:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        inputPanel.add(algorithmLabel, gbc);

        String[] algorithms = {"MD5", "SHA-256", "SHA-512"};
        JComboBox<String> algorithmComboBox = new JComboBox<>(algorithms);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        inputPanel.add(algorithmComboBox, gbc);

        // Nút mã hóa
        JButton hashButton = new JButton("Mã hóa");
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        inputPanel.add(hashButton, gbc);

        // Kết quả
        JLabel resultLabel = new JLabel("Kết quả:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        inputPanel.add(resultLabel, gbc);

        JTextArea resultArea = new JTextArea(5, 30);
        resultArea.setEditable(false);
        resultArea.setLineWrap(true);
        resultArea.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(resultArea);
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        inputPanel.add(scrollPane, gbc);

        // Xử lý sự kiện mã hóa
        hashButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String password = passwordField.getText();
                String algorithm = (String) algorithmComboBox.getSelectedItem();

                if (password.isEmpty()) {
                    JOptionPane.showMessageDialog(panel, "Vui lòng nhập mật khẩu",
                            "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    String hashedPassword = "";
                    switch (algorithm) {
                        case "MD5":
                            hashedPassword = PasswordHasher.hashWithMD5(password);
                            break;
                        case "SHA-256":
                            hashedPassword = PasswordHasher.hashWithSHA256(password);
                            break;
                        case "SHA-512":
                            hashedPassword = PasswordHasher.hashWithSHA512(password);
                            break;
                    }
                    resultArea.setText(hashedPassword);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(panel, "Lỗi mã hóa: " + ex.getMessage(),
                            "Lỗi", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }
            }
        });

        panel.add(inputPanel, BorderLayout.CENTER);
        return panel;
    }

    private JPanel createAESPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        // Hiển thị key
        JLabel keyLabel = new JLabel("Key AES (Base64):");
        gbc.gridx = 0;
        gbc.gridy = 0;
        inputPanel.add(keyLabel, gbc);

        JTextField keyField = new JTextField(30);
        keyField.setText(aesEncryption.getKeyAsString());
        keyField.setEditable(false);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        inputPanel.add(keyField, gbc);

        // Nhập dữ liệu
        JLabel dataLabel = new JLabel("Dữ liệu gốc:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        inputPanel.add(dataLabel, gbc);

        JTextArea dataArea = new JTextArea(5, 30);
        dataArea.setLineWrap(true);
        dataArea.setWrapStyleWord(true);
        JScrollPane dataScrollPane = new JScrollPane(dataArea);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        inputPanel.add(dataScrollPane, gbc);

        // Nút mã hóa & giải mã
        JButton encryptButton = new JButton("Mã hóa");
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        inputPanel.add(encryptButton, gbc);

        JButton decryptButton = new JButton("Giải mã");
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        inputPanel.add(decryptButton, gbc);

        // Kết quả
        JLabel resultLabel = new JLabel("Kết quả:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        inputPanel.add(resultLabel, gbc);

        JTextArea resultArea = new JTextArea(5, 30);
        resultArea.setLineWrap(true);
        resultArea.setWrapStyleWord(true);
        JScrollPane resultScrollPane = new JScrollPane(resultArea);
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        inputPanel.add(resultScrollPane, gbc);

        // Xử lý sự kiện mã hóa
        encryptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String data = dataArea.getText();

                if (data.isEmpty()) {
                    JOptionPane.showMessageDialog(panel, "Vui lòng nhập dữ liệu",
                            "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    String encryptedData = aesEncryption.encrypt(data);
                    resultArea.setText(encryptedData);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(panel, "Lỗi mã hóa: " + ex.getMessage(),
                            "Lỗi", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }
            }
        });

        // Xử lý sự kiện giải mã
        decryptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String encryptedData = dataArea.getText();

                if (encryptedData.isEmpty()) {
                    JOptionPane.showMessageDialog(panel, "Vui lòng nhập dữ liệu đã mã hóa",
                            "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    String decryptedData = aesEncryption.decrypt(encryptedData);
                    resultArea.setText(decryptedData);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(panel, "Lỗi giải mã: " + ex.getMessage(),
                            "Lỗi", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }
            }
        });

        panel.add(inputPanel, BorderLayout.CENTER);
        return panel;
    }

    private JPanel createRSAPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        // Hiển thị public key
        JLabel publicKeyLabel = new JLabel("Public Key (Base64):");
        gbc.gridx = 0;
        gbc.gridy = 0;
        inputPanel.add(publicKeyLabel, gbc);

        JTextArea publicKeyArea = new JTextArea(2, 30);
        publicKeyArea.setText(rsaEncryption.getPublicKeyAsString());
        publicKeyArea.setEditable(false);
        publicKeyArea.setLineWrap(true);
        publicKeyArea.setWrapStyleWord(true);
        JScrollPane publicKeyScrollPane = new JScrollPane(publicKeyArea);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        inputPanel.add(publicKeyScrollPane, gbc);

        // Hiển thị private key
        JLabel privateKeyLabel = new JLabel("Private Key (Base64):");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        inputPanel.add(privateKeyLabel, gbc);

        JTextArea privateKeyArea = new JTextArea(2, 30);
        privateKeyArea.setText(rsaEncryption.getPrivateKeyAsString());
        privateKeyArea.setEditable(false);
        privateKeyArea.setLineWrap(true);
        privateKeyArea.setWrapStyleWord(true);
        JScrollPane privateKeyScrollPane = new JScrollPane(privateKeyArea);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        inputPanel.add(privateKeyScrollPane, gbc);

        // Nhập dữ liệu
        JLabel dataLabel = new JLabel("Dữ liệu gốc:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        inputPanel.add(dataLabel, gbc);

        JTextArea dataArea = new JTextArea(4, 30);
        dataArea.setLineWrap(true);
        dataArea.setWrapStyleWord(true);
        JScrollPane dataScrollPane = new JScrollPane(dataArea);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        inputPanel.add(dataScrollPane, gbc);

        // Nút mã hóa & giải mã
        JButton encryptButton = new JButton("Mã hóa");
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        inputPanel.add(encryptButton, gbc);

        JButton decryptButton = new JButton("Giải mã");
        gbc.gridx = 2;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        inputPanel.add(decryptButton, gbc);

        // Kết quả
        JLabel resultLabel = new JLabel("Kết quả:");
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        inputPanel.add(resultLabel, gbc);

        JTextArea resultArea = new JTextArea(4, 30);
        resultArea.setLineWrap(true);
        resultArea.setWrapStyleWord(true);
        JScrollPane resultScrollPane = new JScrollPane(resultArea);
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        inputPanel.add(resultScrollPane, gbc);

        // Xử lý sự kiện mã hóa
        encryptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String data = dataArea.getText();

                if (data.isEmpty()) {
                    JOptionPane.showMessageDialog(panel, "Vui lòng nhập dữ liệu",
                            "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    String encryptedData = rsaEncryption.encrypt(data);
                    resultArea.setText(encryptedData);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(panel, "Lỗi mã hóa: " + ex.getMessage(),
                            "Lỗi", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }
            }
        });

        // Xử lý sự kiện giải mã
        decryptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String encryptedData = dataArea.getText();

                if (encryptedData.isEmpty()) {
                    JOptionPane.showMessageDialog(panel, "Vui lòng nhập dữ liệu đã mã hóa",
                            "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    String decryptedData = rsaEncryption.decrypt(encryptedData);
                    resultArea.setText(decryptedData);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(panel, "Lỗi giải mã: " + ex.getMessage(),
                            "Lỗi", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }
            }
        });

        panel.add(inputPanel, BorderLayout.CENTER);
        return panel;
    }

    private JPanel createThreadedPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        // Chọn thuật toán
        JLabel algorithmLabel = new JLabel("Thuật toán:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        inputPanel.add(algorithmLabel, gbc);

        String[] algorithms = {"AES", "RSA"};
        JComboBox<String> algorithmComboBox = new JComboBox<>(algorithms);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        inputPanel.add(algorithmComboBox, gbc);

        // Nhập dữ liệu
        JLabel dataLabel = new JLabel("Dữ liệu gốc:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        inputPanel.add(dataLabel, gbc);

        JTextArea dataArea = new JTextArea(5, 30);
        dataArea.setLineWrap(true);
        dataArea.setWrapStyleWord(true);
        JScrollPane dataScrollPane = new JScrollPane(dataArea);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        inputPanel.add(dataScrollPane, gbc);

        // Nút thực hiện
        JButton processButton = new JButton("Mã hóa & Giải mã (Đa luồng)");
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        inputPanel.add(processButton, gbc);

        // Kết quả mã hóa
        JLabel encryptLabel = new JLabel("Kết quả mã hóa:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        inputPanel.add(encryptLabel, gbc);

        JTextArea encryptArea = new JTextArea(5, 30);
        encryptArea.setLineWrap(true);
        encryptArea.setWrapStyleWord(true);
        encryptArea.setEditable(false);
        JScrollPane encryptScrollPane = new JScrollPane(encryptArea);
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        inputPanel.add(encryptScrollPane, gbc);

        // Kết quả giải mã
        JLabel decryptLabel = new JLabel("Kết quả giải mã:");
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        inputPanel.add(decryptLabel, gbc);

        JTextArea decryptArea = new JTextArea(5, 30);
        decryptArea.setLineWrap(true);
        decryptArea.setWrapStyleWord(true);
        decryptArea.setEditable(false);
        JScrollPane decryptScrollPane = new JScrollPane(decryptArea);
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        inputPanel.add(decryptScrollPane, gbc);

        // Trạng thái xử lý
        JLabel statusLabel = new JLabel("Trạng thái: ");
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 1;
        inputPanel.add(statusLabel, gbc);

        JTextField statusField = new JTextField(30);
        statusField.setEditable(false);
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        inputPanel.add(statusField, gbc);

        // Xử lý sự kiện mã hóa & giải mã đa luồng
        processButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String data = dataArea.getText();
                String algorithm = (String) algorithmComboBox.getSelectedItem();

                if (data.isEmpty()) {
                    JOptionPane.showMessageDialog(panel, "Vui lòng nhập dữ liệu",
                            "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Xóa kết quả cũ
                encryptArea.setText("");
                decryptArea.setText("");
                statusField.setText("Đang xử lý...");

                // Lấy đối tượng mã hóa theo thuật toán đã chọn
                Encryptable encryptor = algorithm.equals("AES") ? aesEncryption : rsaEncryption;

                // Thread mã hóa
                executorService.submit(() -> {
                    try {
                        String encryptedData = encryptor.encrypt(data);

                        // Cập nhật UI trong EDT
                        SwingUtilities.invokeLater(() -> {
                            encryptArea.setText(encryptedData);
                            statusField.setText("Mã hóa hoàn tất. " + (decryptArea.getText().isEmpty() ? "Đang giải mã..." : "Hoàn tất."));
                        });

                    } catch (Exception ex) {
                        SwingUtilities.invokeLater(() -> {
                            JOptionPane.showMessageDialog(panel, "Lỗi mã hóa: " + ex.getMessage(),
                                    "Lỗi", JOptionPane.ERROR_MESSAGE);
                            statusField.setText("Lỗi mã hóa");
                        });
                        ex.printStackTrace();
                    }
                });

                // Thread giải mã (giải mã dữ liệu mã hóa sau 1 giây để mô phỏng xử lý đồng thời)
                executorService.submit(() -> {
                    try {
                        // Đợi 1 giây để mô phỏng xử lý đồng thời
                        Thread.sleep(1000);

                        String encryptedData = encryptor.encrypt(data);
                        String decryptedData = encryptor.decrypt(encryptedData);

                        // Cập nhật UI trong EDT
                        SwingUtilities.invokeLater(() -> {
                            decryptArea.setText(decryptedData);
                            statusField.setText("Hoàn tất");
                        });

                    } catch (Exception ex) {
                        SwingUtilities.invokeLater(() -> {
                            JOptionPane.showMessageDialog(panel, "Lỗi giải mã: " + ex.getMessage(),
                                    "Lỗi", JOptionPane.ERROR_MESSAGE);
                            statusField.setText("Lỗi giải mã");
                        });
                        ex.printStackTrace();
                    }
                });
            }
        });

        panel.add(inputPanel, BorderLayout.CENTER);
        return panel;
    }
}
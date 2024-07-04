import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class KalkulatorRumusBangunDatar extends JFrame implements ActionListener {
    private CardLayout cardLayout;
    private JPanel mainPanel, homePanel, calculationPanel;
    private JComboBox<String> comboBangunDatar;
    private JTextField tfInput1, tfInput2, tfInput3;
    private JLabel lblInput1, lblInput2, lblInput3, lblResult;
    private JButton btnHitung, btnKembali;
    private String selectedOperation;

    public KalkulatorRumusBangunDatar() {
        setTitle("Kalkulator Rumus Bangun Datar");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        createHomePanel();
        createCalculationPanel();

        mainPanel.add(homePanel, "Home");
        mainPanel.add(calculationPanel, "Calculation");

        add(mainPanel);
    }

    private void createHomePanel() {
        homePanel = new JPanel(new GridLayout(3, 1, 10, 10));

        JLabel lblTitle = new JLabel("Kalkulator Rumus Bangun Datar", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 20));
        homePanel.add(lblTitle);

        JButton btnLuas = new JButton("Hitung Luas");
        btnLuas.addActionListener(e -> {
            selectedOperation = "Luas";
            updateCalculationPanel();
            cardLayout.show(mainPanel, "Calculation");
        });
        homePanel.add(btnLuas);

        JButton btnKeliling = new JButton("Hitung Keliling");
        btnKeliling.addActionListener(e -> {
            selectedOperation = "Keliling";
            updateCalculationPanel();
            cardLayout.show(mainPanel, "Calculation");
        });
        homePanel.add(btnKeliling);
    }

    private void createCalculationPanel() {
        calculationPanel = new JPanel(new BorderLayout(10, 10));

        // Panel untuk pilihan bangun datar
        JPanel panelBangunDatar = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panelBangunDatar.add(new JLabel("Pilih Bangun Datar:"));
        String[] bangunDatar = {"persegi", "segitiga", "lingkaran", "trapesium", "layang-layang", "belah ketupat", "persegi panjang", "jajar genjang", "pentagon", "hexagon"};
        comboBangunDatar = new JComboBox<>(bangunDatar);
        comboBangunDatar.addActionListener(this);
        panelBangunDatar.add(comboBangunDatar);

        // Panel untuk input dengan GridBagLayout
        JPanel panelInput = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        lblInput1 = new JLabel("Input 1:");
        lblInput2 = new JLabel("Input 2:");
        lblInput3 = new JLabel("Input 3:");
        tfInput1 = new JTextField(10);
        tfInput2 = new JTextField(10);
        tfInput3 = new JTextField(10);

        // Baris pertama (Input 1)
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.3;
        panelInput.add(lblInput1, gbc);

        gbc.gridx = 1;
        gbc.weightx = 0.7;
        panelInput.add(tfInput1, gbc);

        // Baris kedua (Input 2)
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0.3;
        panelInput.add(lblInput2, gbc);

        gbc.gridx = 1;
        gbc.weightx = 0.7;
        panelInput.add(tfInput2, gbc);

        // Baris ketiga (Input 3)
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0.3;
        panelInput.add(lblInput3, gbc);

        gbc.gridx = 1;
        gbc.weightx = 0.7;
        panelInput.add(tfInput3, gbc);

        // Menambahkan label petunjuk
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        JLabel lblHint = new JLabel("Tekan Enter untuk menghitung");
        lblHint.setHorizontalAlignment(SwingConstants.CENTER);
        panelInput.add(lblHint, gbc);

        // Panel untuk tombol hitung dan kembali
        JPanel panelButton = new JPanel();
        btnHitung = new JButton("Hitung");
        btnHitung.addActionListener(this);
        panelButton.add(btnHitung);

        btnKembali = new JButton("Kembali");
        btnKembali.addActionListener(e -> cardLayout.show(mainPanel, "Home"));
        panelButton.add(btnKembali);

        // Panel untuk hasil
        JPanel panelResult = new JPanel();
        lblResult = new JLabel("Hasil: ");
        lblResult.setHorizontalAlignment(SwingConstants.CENTER);
        lblResult.setFont(new Font("Arial", Font.PLAIN, 16));
        panelResult.add(lblResult);

        // Tambahkan semua panel ke calculationPanel
        calculationPanel.add(panelBangunDatar, BorderLayout.NORTH);
        calculationPanel.add(panelInput, BorderLayout.CENTER);
        calculationPanel.add(panelButton, BorderLayout.SOUTH);
        calculationPanel.add(panelResult, BorderLayout.PAGE_END);

        // Menambahkan event listener untuk menangani tombol Enter
        tfInput1.addActionListener(this);
        tfInput2.addActionListener(this);
        tfInput3.addActionListener(this);
    }

    private void updateCalculationPanel() {
        String pilihan = (String) comboBangunDatar.getSelectedItem();
        lblInput1.setVisible(true);
        tfInput1.setVisible(true);
        lblInput2.setVisible(true);
        tfInput2.setVisible(true);
        lblInput3.setVisible(pilihan.equals("segitiga") || pilihan.equals("trapesium") || pilihan.equals("jajar genjang"));
        tfInput3.setVisible(pilihan.equals("segitiga") || pilihan.equals("trapesium") || pilihan.equals("jajar genjang"));

        if ("Luas".equals(selectedOperation)) {
            switch (pilihan) {
                case "persegi":
                    lblInput1.setText("Panjang Sisi:");
                    lblInput2.setVisible(false);
                    tfInput2.setVisible(false);
                    lblInput3.setVisible(false);
                    tfInput3.setVisible(false);
                    break;
                case "segitiga":
                    lblInput1.setText("Panjang Alas:");
                    lblInput2.setText("Tinggi:");
                    lblInput3.setVisible(false);
                    tfInput3.setVisible(false);
                    break;
                case "lingkaran":
                    lblInput1.setText("Jari-jari:");
                    lblInput2.setVisible(false);
                    tfInput2.setVisible(false);
                    lblInput3.setVisible(false);
                    tfInput3.setVisible(false);
                    break;
                case "trapesium":
                    lblInput1.setText("Sisi Atas:");
                    lblInput2.setText("Sisi Bawah:");
                    lblInput3.setText("Tinggi:");
                    break;
                case "layang-layang":
                case "belah ketupat":
                    lblInput1.setText("Diagonal 1:");
                    lblInput2.setText("Diagonal 2:");
                    lblInput3.setVisible(false);
                    tfInput3.setVisible(false);
                    break;
                case "persegi panjang":
                    lblInput1.setText("Panjang:");
                    lblInput2.setText("Lebar:");
                    lblInput3.setVisible(false);
                    tfInput3.setVisible(false);
                    break;
                case "jajar genjang":
                    lblInput1.setText("Alas:");
                    lblInput2.setText("Tinggi:");
                    lblInput3.setVisible(false);
                    tfInput3.setVisible(false);
                    break;
                case "pentagon":
                case "hexagon":
                    lblInput1.setText("Panjang Sisi:");
                    lblInput2.setVisible(false);
                    tfInput2.setVisible(false);
                    lblInput3.setVisible(false);
                    tfInput3.setVisible(false);
                    break;
            }
        } else if ("Keliling".equals(selectedOperation)) {
            switch (pilihan) {
                case "persegi":
                    lblInput1.setText("Panjang Sisi:");
                    lblInput2.setVisible(false);
                    tfInput2.setVisible(false);
                    lblInput3.setVisible(false);
                    tfInput3.setVisible(false);
                    break;
                case "segitiga":
                    lblInput1.setText("Sisi 1:");
                    lblInput2.setText("Sisi 2:");
                    lblInput3.setText("Sisi 3:");
                    break;
                case "lingkaran":
                    lblInput1.setText("Jari-jari:");
                    lblInput2.setVisible(false);
                    tfInput2.setVisible(false);
                    lblInput3.setVisible(false);
                    tfInput3.setVisible(false);
                    break;
                case "trapesium":
                    lblInput1.setText("Sisi Atas:");
                    lblInput2.setText("Sisi Bawah:");
                    lblInput3.setText("Sisi 1:");
                    break;
                case "layang-layang":
                    lblInput1.setText("Panjang Sisi 1:");
                    lblInput2.setText("Panjang Sisi 2:");
                    lblInput3.setVisible(false);
                    tfInput3.setVisible(false);
                    break;
                case "belah ketupat":
                    lblInput1.setText("Panjang Sisi:");
                    lblInput2.setVisible(false);
                    tfInput2.setVisible(false);
                    lblInput3.setVisible(false);
                    tfInput3.setVisible(false);
                    break;
                case "persegi panjang":
                    lblInput1.setText("Panjang:");
                    lblInput2.setText("Lebar:");
                    lblInput3.setVisible(false);
                    tfInput3.setVisible(false);
                    break;
                case "jajar genjang":
                    lblInput1.setText("Panjang Alas:");
                    lblInput2.setText("Panjang Sisi Miring:");
                    lblInput3.setVisible(false);
                    tfInput3.setVisible(false);
                    break;
                case "pentagon":
                case "hexagon":
                    lblInput1.setText("Panjang Sisi:");
                    lblInput2.setVisible(false);
                    tfInput2.setVisible(false);
                    lblInput3.setVisible(false);
                    tfInput3.setVisible(false);
                    break;
            }
        }

        tfInput1.setText("");
        tfInput2.setText("");
        tfInput3.setText("");
        lblResult.setText("Hasil: ");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnHitung || e.getSource() == tfInput1 || e.getSource() == tfInput2 || e.getSource() == tfInput3) {
            hitung();
        } else if (e.getSource() == comboBangunDatar) {
            updateCalculationPanel();
        }
    }

    private void hitung() {
        try {
            String pilihan = (String) comboBangunDatar.getSelectedItem();
            double input1 = Double.parseDouble(tfInput1.getText());
            double input2 = tfInput2.isVisible() ? Double.parseDouble(tfInput2.getText()) : 0;
            double input3 = tfInput3.isVisible() ? Double.parseDouble(tfInput3.getText()) : 0;
            double hasil = 0;

            if ("Luas".equals(selectedOperation)) {
                switch (pilihan) {
                    case "persegi":
                        hasil = input1 * input1;
                        break;
                    case "segitiga":
                        hasil = 0.5 * input1 * input2;
                        break;
                    case "lingkaran":
                        hasil = Math.PI * input1 * input1;
                        break;
                    case "trapesium":
                        hasil = 0.5 * (input1 + input2) * input3;
                        break;
                    case "layang-layang":
                    case "belah ketupat":
                        hasil = 0.5 * input1 * input2;
                        break;
                    case "persegi panjang":
                        hasil = input1 * input2;
                        break;
                    case "jajar genjang":
                        hasil = input1 * input2;
                        break;
                    case "pentagon":
                        hasil = 1.72 * input1 * input1;
                        break;
                    case "hexagon":
                        hasil = 2.598 * input1 * input1;
                        break;
                }
            } else if ("Keliling".equals(selectedOperation)) {
                switch (pilihan) {
                    case "persegi":
                        hasil = 4 * input1;
                        break;
                    case "segitiga":
                        hasil = input1 + input2 + input3;
                        break;
                    case "lingkaran":
                        hasil = 2 * Math.PI * input1;
                        break;
                    case "trapesium":
                        hasil = input1 + input2 + input3 + input3;
                        break;
                    case "layang-layang":
                        hasil = 2 * (input1 + input2);
                        break;
                    case "belah ketupat":
                        hasil = 4 * input1;
                        break;
                    case "persegi panjang":
                        hasil = 2 * (input1 + input2);
                        break;
                    case "jajar genjang":
                        hasil = 2 * (input1 + input2);
                        break;
                    case "pentagon":
                        hasil = 5 * input1;
                        break;
                    case "hexagon":
                        hasil = 6 * input1;
                        break;
                }
            }
            lblResult.setText("Hasil: " + hasil);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Masukkan nilai numerik yang valid.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new KalkulatorRumusBangunDatar().setVisible(true);
        });
    }
}

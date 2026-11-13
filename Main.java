import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {

    // theme colours (green and white)
    private final Color GREEN = new Color(46, 125, 50);
    private final Color LIGHT_GREEN = new Color(232, 245, 233);

    private CardLayout cardLayout = new CardLayout();
    private JPanel mainPanel = new JPanel(cardLayout);

    private StudentProgress student;
    private HealthContent content = new HealthContent();

    // home screen stuff that changes
    private int tipIndex;
    private JLabel tipCountLabel;
    private JTextArea tipArea;

    public Main(String userName) {
        student = new StudentProgress(userName);

        setTitle("Healthy Living");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        // more screens (learn, quiz) will be added here later
        mainPanel.add(createHomePanel(), "Home");
        add(mainPanel);

        // phone size for the content area, pack() so the title bar is not counted
        getContentPane().setPreferredSize(new Dimension(380, 680));
        pack();
        setLocationRelativeTo(null);

        cardLayout.show(mainPanel, "Home");
    }

    // ---------- Home screen ----------
    private JPanel createHomePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);

        // top part: app name + greeting
        JPanel header = new JPanel(new GridLayout(2, 1));
        header.setBackground(GREEN);
        header.setBorder(BorderFactory.createEmptyBorder(30, 20, 30, 20));

        JLabel title = new JLabel("Healthy Living");
        title.setFont(new Font("SansSerif", Font.BOLD, 28));
        title.setForeground(Color.WHITE);

        JLabel greeting = new JLabel("Hi, " + student.getName() + "! Let's take care of ourselves today.");
        greeting.setFont(new Font("SansSerif", Font.PLAIN, 13));
        greeting.setForeground(Color.WHITE);

        header.add(title);
        header.add(greeting);

        // middle part: tip of the day
        JPanel tipPanel = new JPanel(new BorderLayout(5, 10));
        tipPanel.setBackground(Color.WHITE);
        tipPanel.setBorder(BorderFactory.createEmptyBorder(25, 20, 10, 20));

        tipCountLabel = new JLabel();
        tipCountLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        tipCountLabel.setForeground(GREEN);

        tipArea = new JTextArea();
        tipArea.setEditable(false);
        tipArea.setLineWrap(true);
        tipArea.setWrapStyleWord(true);
        tipArea.setFont(new Font("SansSerif", Font.PLAIN, 16));
        tipArea.setBackground(LIGHT_GREEN);
        tipArea.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JButton btnNextTip = makeButton("Next tip");
        btnNextTip.addActionListener(e -> {
            tipIndex = (tipIndex + 1) % content.getTipCount();
            showTip();
        });

        tipPanel.add(tipCountLabel, BorderLayout.NORTH);
        tipPanel.add(tipArea, BorderLayout.CENTER);
        tipPanel.add(btnNextTip, BorderLayout.SOUTH);

        tipIndex = content.getTodayTipIndex();
        showTip();

        // bottom part: menu buttons
        JPanel menu = new JPanel(new GridLayout(3, 1, 0, 12));
        menu.setBackground(Color.WHITE);
        menu.setBorder(BorderFactory.createEmptyBorder(10, 20, 30, 20));

        JButton btnLearn = makeButton("Learn");
        JButton btnQuiz = makeButton("Take Quiz");
        JButton btnExit = makeButton("Exit");

        btnLearn.addActionListener(e -> comingSoon("Learning"));
        btnQuiz.addActionListener(e -> comingSoon("Quiz"));
        btnExit.addActionListener(e -> System.exit(0));

        menu.add(btnLearn);
        menu.add(btnQuiz);
        menu.add(btnExit);

        panel.add(header, BorderLayout.NORTH);
        panel.add(tipPanel, BorderLayout.CENTER);
        panel.add(menu, BorderLayout.SOUTH);
        return panel;
    }

    // ---------- helpers ----------
    private void showTip() {
        tipCountLabel.setText("Tip " + (tipIndex + 1) + " of " + content.getTipCount());
        tipArea.setText(content.getTip(tipIndex));
    }

    // makes a green button with white text
    private JButton makeButton(String text) {
        JButton btn = new JButton(text);
        btn.setBackground(GREEN);
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("SansSerif", Font.BOLD, 15));
        btn.setOpaque(true);            // needed on Mac so the colour shows
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        return btn;
    }

    private void comingSoon(String screenName) {
        JOptionPane.showMessageDialog(this, screenName + " screen is not ready yet.");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            String name = JOptionPane.showInputDialog(null, "What is your name?", "Welcome",
                    JOptionPane.QUESTION_MESSAGE);

            // if they cancel or leave it empty
            if (name == null || name.trim().isEmpty()) {
                name = "Guest";
            }
            new Main(name.trim()).setVisible(true);
        });
    }
}
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MazeGUI extends JFrame {
    private JTextPane eventsTextPane;
    private Maze lab;
    private JButton[][] przyciski;
    private JPanel mazePanel;

    public void wypelnij() {
        mazePanel.updateUI();
        mazePanel.removeAll();
        int maxi = lab.get_n() > lab.get_m() ? lab.get_n() : lab.get_m();
        if (maxi == 0) maxi = 32;
        int rozm = 1024 / maxi;
        for (int i = 0; i < lab.get_n(); i++) {
            for (int j = 0; j < lab.get_m(); j++) {
                if (lab.get_tab(i, j) == 'X') {
                    przyciski[i][j].setBackground(Color.BLACK);
                } else if (lab.get_tab(i, j) == 'S') {
                    przyciski[i][j].setBackground(Color.RED);
                }
                else przyciski[i][j].setBackground(Color.WHITE);

                if (lab.get_pocz().first() == i && lab.get_pocz().second() == j) {
                    przyciski[i][j].setBackground(Color.BLUE);
                 } else if (lab.get_kon().first() == i && lab.get_kon().second() == j) {
                    przyciski[i][j].setBackground(Color.GREEN);
                }
                przyciski[i][j].setPreferredSize(new Dimension(rozm, rozm));
                przyciski[i][j].setBorder(null);
                przyciski[i][j].setEnabled(false);
                mazePanel.add(przyciski[i][j]);
            }
        }
        if (lab.get_n() != 0) mazePanel.setLayout(new GridLayout(lab.get_n(), lab.get_m()));
        mazePanel.revalidate();
        mazePanel.repaint();
    }


    public MazeGUI() {
        przyciski = new JButton[500][500];
        mazePanel = new JPanel(new GridLayout(300, 300));
        JScrollPane scrollPane = new JScrollPane(mazePanel);
        scrollPane.setPreferredSize(new Dimension(1024, 1024));
        add(scrollPane, BorderLayout.CENTER);

        for (int i = 0; i < 300; i++) {
            for (int j = 0; j < 300; j++) {
                przyciski[i][j] = new JButton();
                final int row = i;
                final int column = j;
                przyciski[i][j].addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if (SwingUtilities.isLeftMouseButton(e)) {

                            if(lab.get_tab(lab.get_pocz().first(),lab.get_pocz().second())=='X') przyciski[lab.get_pocz().first()][lab.get_pocz().second()].setBackground(Color.BLACK);
                            else przyciski[lab.get_pocz().first()][lab.get_pocz().second()].setBackground(Color.WHITE);
                            przyciski[row][column].setBackground(Color.BLUE);
                            lab.ustaw_pocz(row, column);
                            Notification.addText("-Ustawiono punkt startowy!", true,eventsTextPane);

                        }
                        else if (SwingUtilities.isRightMouseButton(e)) {

                            if(lab.get_tab(lab.get_kon().first(),lab.get_kon().second())=='X') przyciski[lab.get_kon().first()][lab.get_kon().second()].setBackground(Color.BLACK);
                            else przyciski[lab.get_kon().first()][lab.get_kon().second()].setBackground(Color.WHITE);
                            przyciski[row][column].setBackground(Color.GREEN);
                            lab.ustaw_kon(row, column);
                            Notification.addText("-Ustawiono punkt końcowy!", true,eventsTextPane);

                        }
                    }
                });
            }
        }

        lab = new Maze();
        setResizable(false);
        setTitle("Labirynt - rozwiązanie");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("Plik");
        JMenuItem loadMenuItem = new JMenuItem("Wczytaj labirynt");

        loadMenuItem.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter(
                    "Pliki tekstowe i binarne", "txt", "bin");
            fileChooser.setFileFilter(filter);
            int returnValue = fileChooser.showOpenDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                lab.wczytaj(fileChooser.getSelectedFile());
                String filename = fileChooser.getSelectedFile().getName();
                wypelnij();
                Notification.addText("-Wczytano plik " + filename, true,eventsTextPane);
            }
        });

        fileMenu.add(loadMenuItem);
        menuBar.add(fileMenu);


        JMenu saveMenu = new JMenu("Zapisz");
        JMenuItem saveGraphicalMenuItem = new JMenuItem("Zapisz labirynt w formie graficznej");
        JMenuItem saveTextMenuItem = new JMenuItem("Zapisz labirynt w formie tekstowej");


        saveGraphicalMenuItem.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter(
                    "Pliki graficzne", "png");
            fileChooser.setFileFilter(filter);
            int returnValue = fileChooser.showSaveDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                java.io.File selectedFile = fileChooser.getSelectedFile();
            }
        });

        saveTextMenuItem.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter(
                    "Pliki tekstowe", "txt");
            fileChooser.setFileFilter(filter);
            int returnValue = fileChooser.showSaveDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                java.io.File selectedFile = fileChooser.getSelectedFile();
            }
        });

        saveMenu.add(saveGraphicalMenuItem);
        saveMenu.add(saveTextMenuItem);
        menuBar.add(saveMenu);

        setJMenuBar(menuBar);


        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        JPanel toolPanel = new JPanel();
        toolPanel.setLayout(new BoxLayout(toolPanel, BoxLayout.Y_AXIS));
        toolPanel.setBackground(Color.LIGHT_GRAY);
        toolPanel.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(Color.BLACK), new EmptyBorder(10, 10, 10, 10)));

        JButton shortestPathButton = new JButton("Znajdź najkrótszą ścieżkę");
        shortestPathButton.addActionListener(e -> {
            if(!lab.isLoaded())
            {
                Notification.addText("Labirynt niezaładowany!", false,eventsTextPane);
            }
            else {
                lab.najkrotsza(lab.get_pocz().first(), lab.get_pocz().second());
                Notification.addText("-Znaleziono najkrótszą ścieżkę o długości "+lab.odzyskaj()+"!", true,eventsTextPane);
                wypelnij();
            }

        });

        eventsTextPane = new JTextPane();
        JButton setPointsButton = new JButton("Lewym/prawym przyciskiem myszy na planszę");
        setPointsButton.setPreferredSize(new Dimension(200, 50));
        setPointsButton.setEnabled(false);


        shortestPathButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        setPointsButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        toolPanel.add(shortestPathButton);
        toolPanel.add(Box.createRigidArea(new Dimension(0, 5))); 

        JLabel eventsLabel = new JLabel("Wydarzenia:");
        eventsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        toolPanel.add(eventsLabel);
        toolPanel.add(Box.createRigidArea(new Dimension(0, 5)));

        eventsTextPane = new JTextPane();
        eventsTextPane.setEditable(false);
        eventsTextPane.setFocusable(false);
        eventsTextPane.setBackground(new Color(230, 230, 230));
        JScrollPane scrollPaneEvents = new JScrollPane(eventsTextPane);
        scrollPaneEvents.setPreferredSize(new Dimension(200, 200));
        toolPanel.add(scrollPaneEvents);

        toolPanel.setMaximumSize(new Dimension(300, toolPanel.getPreferredSize().height));

        menuPanel.add(toolPanel);

        mazePanel.setPreferredSize(new Dimension(1024, 1024));
        mazePanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                }
            }
        });
        wypelnij();

        Container container = getContentPane();
        container.setLayout(new BorderLayout());
        container.add(menuPanel, BorderLayout.NORTH);
        container.add(toolPanel, BorderLayout.WEST);
        container.add(mazePanel, BorderLayout.CENTER);



        Notification.addText("Poniżej będą wyświetlane powiadomienia dotyczące działania programu:", true,eventsTextPane);
        pack();
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MazeGUI mazeGUI = new MazeGUI();
            mazeGUI.setVisible(true);
        });
    }
}

package com.Lab2.scene;

import com.Lab2.conveter.Converter;
import com.Lab2.conveter.TableToXML;
import com.Lab2.parsers.patterns.Context;
import com.Lab2.parsers.patterns.DOMStrategy;
import com.Lab2.parsers.patterns.SAXStrategy;
import com.Lab2.student.Student;

import javax.swing.*;
import javax.swing.border.AbstractBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.List;
import java.util.Vector;

public class Scene extends JFrame {

    private String[] columnNames = new String[]{"   П.І.Б", "Факультет",
            "Кафедра", "Дисципліни"};
    private List<Student> listOfStudents;
    private JRadioButton dombutton = new JRadioButton("DOM", true);
    private JRadioButton saxbutton = new JRadioButton("SAX", false);
    private final ImageIcon icon1 = new ImageIcon("sticker.jpg");
    private final ImageIcon icon2 = new ImageIcon("unnamed.jpg");
    private JScrollPane pane;
    private JPanel background2 = new JPanel( new BorderLayout() ) {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(icon2.getImage(), 0, 0, getWidth(), getHeight(), this);
        }
    };

    private JLabel thumb = new JLabel();
    private ButtonGroup group = new ButtonGroup();
    private String[] columns = new String[]{};
    private Object[][] data = new Object[][]{{}};
    private DefaultTableModel dm;
    private JTable table;
    private JPanel jPanel1;
    private JPanel jPanel2;
    private JPanel jPanel3;
    private JPanel jPanel4;
    private JTextField jTextField;
    private JButton btnSearch;
    private JButton btnConvert;
    private JButton btnHelp;

    boolean activate = false;

    public Scene(){
        super("Шукач (╯ ° □ °) ╯ (┻━┻) ");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(900, 800);
        setLocationRelativeTo((Component) null);

        initTextField();
        initBtn();
        initJPanels();
        add(jPanel1, "North");
        initTable();
        add(background2, "South");
        setResizable(true);
        setVisible(true);

        btnHelp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String temp = "       Wellcome to Help ( ͡° ͜ʖ ͡°)\n";
                temp += "По-перше треба ввести пошуковий запит\n";
                temp += "По-перше треба обрати парсер\n";
                temp += "Натискаємо на кнопку пошуку\n";
                temp += "\n";
                temp += "Кнопка конвертувати - конвертує відкриту\n";
                temp += "таблицю у html файл\n";
                temp += "\n";
                temp += "Нумо, скористуйтесь ж додатком ( ͡° ͜ʖ ͡°)\n";
                JOptionPane.showMessageDialog(null, temp);
            }
        });

        btnConvert.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TableToXML.updateXML(listOfStudents);
                Converter.convert();
            }
        });

        btnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearTable();
                Context context;
                String toSearch = jTextField.getText();
                if (saxbutton.isSelected()) {
                    context = new Context(new SAXStrategy());
                }else {
                    context = new Context(new DOMStrategy());
                }
                listOfStudents = context.executeStrategy(new File("src\\main\\java\\com\\files\\ToRead.xml"), toSearch);
                addColumns(columnNames);
                for (int i = 0; i < listOfStudents.size(); i++) {
                    setTable(i);
                }
            }
        });
    }

    private void setTable(int index){
        addRow();
        table.setValueAt(listOfStudents.get(index).getFullname(), index, 0);
        table.setValueAt(listOfStudents.get(index).getFaculty(), index, 1);
        table.setValueAt(listOfStudents.get(index).getCathedra(), index, 2);
        table.setValueAt(listOfStudents.get(index).getDisciplines(), index, 3);
    }

    private void clearTable(){
        dm.setColumnCount(0);
        dm.setRowCount(0);
    }

    private void addRow(){
        Scene.this.dm.addRow(new Vector());
    }

    private void addColumns(String[] array){
        for (int i = 0; i < array.length; i++) {
            String name = array[i];
            Scene.this.dm.addColumn(name);

        }
    }

    private void initBtn(){
        group.add(dombutton);
        group.add(saxbutton);
        Font FontF = new Font("Algerian", Font.PLAIN, 16);
        saxbutton.setBackground(Color.LIGHT_GRAY);
        dombutton.setBackground(Color.lightGray);
        saxbutton.setFont(FontF);
        dombutton.setFont(FontF);
        btnHelp = new JButton("Я не знаю що робити :(   ");
        btnConvert = new JButton("Конвертувати");
        btnSearch = new JButton("Пошук ( ͡° ͜ʖ ͡°)");
        Font F = new Font("Bahnschrift SemiBold", Font.BOLD, 15);
        btnHelp.setFont(F);
        btnSearch.setFont(F);
        btnConvert.setFont(F);
        btnHelp.setBackground(Color.GRAY);
        btnSearch.setBackground(Color.GRAY);
        btnConvert.setBackground(Color.GRAY);
    }

    private void initJPanels(){
        jPanel1 = new JPanel();
        jPanel2 = new JPanel(new GridLayout(2, 1));
        jPanel3 = new JPanel();
        jPanel4 = new JPanel(new GridLayout(1,2));

        jPanel2.setPreferredSize(new Dimension(60, 35));
        jPanel2.add(saxbutton);
        jPanel2.add(dombutton);
        jPanel2.setBackground(Color.LIGHT_GRAY);

        jPanel3.setPreferredSize(new Dimension(900, 50));
        jPanel3.add(jTextField);
        jPanel3.add(jPanel2);
        thumb.setIcon(icon1);
        thumb.setPreferredSize(new Dimension(100, 50));
        jPanel3.add(thumb);
        jPanel3.setBackground(Color.LIGHT_GRAY);

        jPanel4.setPreferredSize(new Dimension(900, 50));
        jPanel4.add(btnSearch);
        jPanel4.add(btnConvert);
        jPanel4.add(btnHelp);
        jPanel4.setBackground(Color.LIGHT_GRAY);

        jPanel1.setPreferredSize(new Dimension(800, 110));
        jPanel1.add(jPanel3);
        jPanel1.add(jPanel4);
        jPanel1.setBackground(Color.LIGHT_GRAY);
    }

    private void initTable(){
        dm = new DefaultTableModel(data, columns);
        table = new JTable(dm);
        table.setShowGrid(true);
        table.setGridColor(Color.BLACK);
        table.setColumnSelectionAllowed(false);
        table.setPreferredSize(new Dimension(800,450));
        table.setBackground(Color.LIGHT_GRAY);
        table.setRowHeight(30);
        //table.setFont(new Font("Serif", Font.BOLD, 20));
        add(new JScrollPane(table), "Center");
    }

    private void initTextField(){
        jTextField = new JTextField("Тиць ( ͡° ͜ʖ ͡°)");
        Font fieldFont = new Font("Arial", Font.BOLD, 15);
        jTextField.setFont(fieldFont);
        jTextField.setPreferredSize(new Dimension(700, 40));
        jTextField.setBorder(BorderFactory.createCompoundBorder(
                new CustomeBorder(),
                new EmptyBorder(new Insets(10, 20, 10, 20))));
        jTextField.addActionListener(new FieldListener());
        jTextField.addMouseListener(new FieldMouseListener());
        background2.setPreferredSize(new Dimension(800, 200));
    }

    @SuppressWarnings("serial")
    class CustomeBorder extends AbstractBorder {
        @Override
        public void paintBorder(Component c, Graphics g, int x, int y,
                                int width, int height) {
            // TODO Auto-generated method stubs
            super.paintBorder(c, g, x, y, width, height);
            Graphics2D g2d = (Graphics2D)g;
            g2d.setStroke(new BasicStroke(10));
            g2d.setColor(Color.lightGray);
            g2d.drawRoundRect(x, y, width -1 , height - 1 , 20, 20);
        }
    }

    class FieldListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub
            System.out.println(jTextField.getText());
        }

    }

    class FieldMouseListener implements MouseListener{
        @Override
        public void mouseClicked(MouseEvent e) {
            // TODO Auto-generated method stub
            if(activate == false){
                jTextField.setText("");
            }
            activate = true;
            jTextField.setForeground(Color.black);
        }

        @Override
        public void mousePressed(MouseEvent e) {
            jTextField.setText("");
        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {
            if (jTextField.getText().equals("")) {
                jTextField.setText("Тиць ( ͡° ͜ʖ ͡°)");
            }
        }
    }
}
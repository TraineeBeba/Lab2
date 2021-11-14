package com.Lab2.scene;
//
//import javax.swing.*;
//import javax.swing.table.DefaultTableModel;
//import java.awt.*;
//
//public class App extends JFrame {
//
//    private JTable table = new JTable();
//    private JTable table1 = new JTable();
//    private DefaultTableModel dm = new DefaultTableModel();
//    private JButton help = new JButton("Help");
//
//    public App(){
//        super("MyApp");
//        setSize(500, 500);
//        setDefaultCloseOperation(EXIT_ON_CLOSE);
//        setLocationRelativeTo((Component) null);
//        JPanel panel = new JPanel();
//        panel.add(help);
//        setContentPane(panel);
//        add(new JScrollPane(table));
//        add(new JScrollPane(table1), );
////        setContentPane(table);
//        setVisible(true);
//    }
//}

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Scanner;

//Main app Frame
public class Scene extends JFrame {
    private final String XSLPATH = "src\\main\\resources\\db.xsl";
    private final String XMLPATH = "src\\main\\resources\\tempdb.xml";
    private String[] columnNames = new String[]{"Повне ім'я", "Вік", "Факультет", "Кафедра", "Курс", "Адрес" ,"Проплата"};
    //private List<Inhabitant> listOfInhabitants;
    JRadioButton dombutton = new JRadioButton("DOM", true);
    JRadioButton saxbutton = new JRadioButton("SAX", false);
    private final ImageIcon icon1 = new ImageIcon("sticker.jpg");
    private final ImageIcon icon2 = new ImageIcon("unnamed.jpg");
    private JPanel background2 = new JPanel( new BorderLayout() ) {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(icon2.getImage(), 0, 0, getWidth(), getHeight(), this);
        }
    };
    private ButtonGroup group = new ButtonGroup();
    private Object[] columns = new String[] {"П.І.Б", "Факультет",
            "Кафедра", "Дисципліни"};
    private Object[][] data = new Object[][]{{}};
    private DefaultTableModel model;
    private JTable table;
    private JTable table1 = new JTable();
    private JTable table2 = new JTable();
    private JPanel jPanel1;
    private JPanel jPanel2;
    private JPanel jPanel3;
    private JPanel jPanel4;
    private JPanel jPanel5;
    private JTextField jTextField;
    private JLabel thumb = new JLabel();
    private JButton jbtSearch;
    private JButton jbtHelp;
    //private JLabel importLabel;
    private boolean SAX;

    //Constructor
    public Scene(){

        group.add(dombutton);
        group.add(saxbutton);

        model = new DefaultTableModel(data, columns);
        table = new JTable(model);
        JScrollPane scroll = new JScrollPane(table);
        table.setShowGrid(true);
        table.setGridColor(Color.BLACK);
        table.setColumnSelectionAllowed(false);

        //init methods
        initOther();
        initButtons();
        initJPanels();
//        initMainTable();
        setTitle("Парсер XML");
        setSize(800, 700);
        setLocationRelativeTo((Component) null);
        setDefaultCloseOperation(3);
        add(jPanel5);
        add(scroll, "Center");
        add(background2);
//        table1.add(jPanel5, "North");
//        table2.add(background2);
//        Box box = new Box(BoxLayout.Y_AXIS);
//        //box.add(new JScrollPane(table1));
//        box.add((new JScrollPane(table)));
//        box.add(new JScrollPane (table2));
        //setContentPane(box);
        setResizable(true);
        setVisible(true);



        //action listener for button help
        this.jbtHelp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                File file = new File("src\\main\\resources\\help.txt");
                try {
                    Scanner in = new Scanner(new FileReader(file));
                    StringBuilder sb = new StringBuilder();
                    while (in.hasNextLine()) {
                        sb.append(in.nextLine());
                        sb.append('\n');
                    }
                    in.close();
                    String finalText = sb.toString();
                    JOptionPane.showMessageDialog(table, finalText);
                } catch (FileNotFoundException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                }
            }
        });

//        //action listener for button search
//        this.jbtSearch.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                clearTable();
//                String keyword = jTextField.getText();
//                if (SAX){
//                    //listOfInhabitants = ParserXML.parseXML("SAX", selectedFile.toString(), keyword);
//                    System.out.println("using sax");
//                }else {
//                    //listOfInhabitants = ParserXML.parseXML("DOM", selectedFile.toString(), keyword);
//                    System.out.println("using dom");
//                }
//                addColumns(columnNames);
////                for (int i = 0; i < listOfInhabitants.size(); i++) {
////                    setTable(i);
////                }
//            }
//        });
   }

//    private void setTable(int index){
//        addRow();
//    }

        //clear table method
//    private void clearTable(){
//        model.setColumnCount(0);
//        model.setRowCount(0);
//    }

        //method to add single row
//    private void addRow(){
//        Scene.this.model.addRow(new Vector());
//    }

        //method to add columns
//    private void addColumns(String[] array){
//        for (int i = 0; i < array.length; i++) {
//            String name = array[i];
//            Scene.this.model.addColumn(name);
//        }
//    }

    //method to init buttons
    private void initButtons(){
        jbtHelp = new JButton("Допомога");
        jbtSearch = new JButton("Пошук");
    }

    //method to init Jpanels
    private void initJPanels(){
        jPanel1 = new JPanel();
        jPanel2 = new JPanel(new GridLayout(2, 1));
        jPanel3 = new JPanel();
        jPanel4 = new JPanel(new GridLayout(1,2));
        jPanel5 = new JPanel(new GridLayout(2,1));

        jTextField.setPreferredSize(new Dimension(600,40));
        jPanel1.setPreferredSize(new Dimension(600,40));
        jPanel1.add(jTextField);

        jPanel2.setPreferredSize(new Dimension(55, 40));
        jPanel2.add(saxbutton);
        jPanel2.add(dombutton);

        jPanel3.add(jPanel1);
        jPanel3.add(jPanel2);

        thumb.setPreferredSize(new Dimension(100, 50));
        thumb.setIcon(icon1);
        jPanel3.add(thumb);

        jPanel4.add(jbtSearch);
        jPanel4.add(jbtHelp);

        jPanel5.add(jPanel3);
        jPanel5.add(jPanel4);
    }
    private void initOther(){
        jTextField = new JTextField();
    }

    //method to init JTable
//    private void initMainTable(){
//
//    }
}


//import javax.swing.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//public class Scene extends JFrame {
//    JButton proceed;
//    JLabel pathLabel;
//    JLabel searchLabel;
//    JTextField filePath;
//    JTextField search;
//    JRadioButton dombutton;
//    JRadioButton saxbutton;
//
//
//    public Scene() {
//        dombutton = new JRadioButton("DOM", true);
//        saxbutton = new JRadioButton("SAX", false);
//        ButtonGroup group = new ButtonGroup();
//        group.add(dombutton);
//        group.add(saxbutton);
//        pathLabel = new JLabel("Enter XML file path");
//        filePath = new JTextField("");
//        searchLabel = new JLabel("Enter keyword");
//        search = new JTextField("");
//        proceed = new JButton("Proceed");
//        proceed.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
////                Context context;
////                if (dombutton.isSelected()){
////                    context = new Context(new DOMStrategy());
////                }
////                else{
////                    context = new Context(new SAXStrategy());
////                }
////                List<Article> articles = context.executeStrategy(new File(filePath.getText()),search.getText());
////                for (Article article:articles)
////                    System.out.println(article.getTitle());
////                XMLUpdate updater = new XMLUpdate();
////                updater.updateXML(articles);
////                HTMLOutput toHTML = new HTMLOutput();
////                toHTML.convert();
////                articles.clear();
//            }
//        });
//
//        setDefaultCloseOperation(EXIT_ON_CLOSE);
//        Box contents = new Box(BoxLayout.Y_AXIS);
//        contents.add(pathLabel);
//        contents.add(filePath);
//        contents.add(searchLabel);
//        contents.add(search);
//        contents.add(dombutton);
//        contents.add(saxbutton);
//        contents.add(proceed);
//        setContentPane(contents);
//        setSize(400, 200);
//        setVisible(true);
//    }
//}

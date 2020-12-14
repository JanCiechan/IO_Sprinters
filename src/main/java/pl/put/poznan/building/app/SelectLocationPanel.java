package pl.put.poznan.building.app;

import pl.put.poznan.building.logic.ConnectionProvider;

import javax.swing.*;
import java.awt.event.ActionListener;

public class SelectLocationPanel extends JPanel {

    private final JButton get;
    private final JButton post;
    private final JButton back;
    private final JTextArea ta;
    private final JTextField tf;

    public SelectLocationPanel(){
        JPanel upperPanel = new JPanel();
        JPanel centerPanel = new JPanel();
        JPanel bottomPanel = new JPanel();
        JLabel labelEnter = new JLabel("Enter JSON Text");
        ta = new JTextArea();
        JLabel labelInfo = new JLabel("Output from server:");

        ta.setLineWrap(true);
        ta.setColumns(50);
        ta.setRows(15);
        ta.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        tf = new JTextField(45);
        tf.setText("Location");

        tf.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        get = new JButton("GET");
        post = new JButton("POST");
        back = new JButton("Back");

        upperPanel.add(labelInfo);
        centerPanel.add(labelEnter);
        centerPanel.add(tf);
        centerPanel.add(get);
        centerPanel.add(post);
        bottomPanel.add(back);

        this.add(upperPanel);
        this.add(ta);
        this.add(centerPanel);
        this.add(bottomPanel);
    }

    public void addGetActionListener(ActionListener actionListener){
        get.addActionListener(actionListener);
    }

    public void addPostActionListener(ActionListener actionListener){
        post.addActionListener(actionListener);
    }

    public void addBackActionListener(ActionListener actionListener){
        back.addActionListener(actionListener);
    }

    public void setTa(String text){
        ta.setText(text);
    }

    public String getTf(){
        return tf.getText();
    }

}

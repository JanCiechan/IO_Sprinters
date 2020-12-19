package pl.put.poznan.building.app;

import pl.put.poznan.building.logic.ConnectionProvider;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ServerOutputPanel extends JPanel {

    private final JButton get;
    private final JButton post;
    private final JButton delete;
    private final JButton put;
    private final JButton back;
    private final JTextArea ta;
    private final JTextField tf;

    public ServerOutputPanel(){
        JPanel upperPanel = new JPanel();
        JPanel centerPanel = new JPanel();
        JPanel  optionPanel=new JPanel();
        JPanel bottomPanel = new JPanel();
        JLabel labelEnter = new JLabel("Enter JSON Text");
        GridBagConstraints gbc = new GridBagConstraints();
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

        put = new JButton("PUT");
        delete = new JButton("DELETE");
        back = new JButton("Back");

        upperPanel.add(labelInfo);
        centerPanel.add(labelEnter);

        centerPanel.add(tf);
        optionPanel.add(get);
        optionPanel.add(put);

        optionPanel.add(delete);

        optionPanel.add(post);

        bottomPanel.add(back);
        this.add(upperPanel);
        this.add(ta);
        this.add(centerPanel);
        this.add(optionPanel);
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
    public void addDeleteActionListener(ActionListener actionListener){
        delete.addActionListener(actionListener);
    }
    public void addPutActionListener(ActionListener actionListener){
        put.addActionListener((actionListener));
    }
    public void setTa(String text){
        ta.setText(text);
    }

    public String getTf(){
        return tf.getText();
    }

}

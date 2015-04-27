package test;

import javax.swing.*;

public class test2 {
   public static void main(String[] args) {
      JTextField xField = new JTextField(5);
      JTextField yField = new JTextField(5);

      JPanel myPanel = new JPanel();
      myPanel.add(new JLabel("x:"));
      myPanel.add(xField);
      myPanel.add(Box.createHorizontalStrut(15)); // a spacer
      myPanel.add(new JLabel("y:"));
      myPanel.add(yField);

      int result = JOptionPane.showConfirmDialog(null, myPanel, 
               "Please Enter X and Y Values", JOptionPane.OK_CANCEL_OPTION);
      if (result == JOptionPane.OK_OPTION) {
         System.out.println("x value: " + xField.getText());
         System.out.println("y value: " + yField.getText());
      }
   }
}
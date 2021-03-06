package 简易计算器;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Listener implements ActionListener {
    private String jt = "";
    private static JLabel JLabelshow;           //Create background display

    public Listener(JLabel jLabel){
        JLabelshow = jLabel;
    }

    //The ActionPerformed method is an rewrite method in an ActionListener listening event
    public void actionPerformed(ActionEvent event){

        //getActionCommand() depends on the string on the button
        String command = event.getActionCommand();
        if(command == "C"){
            jt = "";
            JLabelshow.setText("0");
        }else if(command == "="){
            RPN rpn = new RPN(jt);
            int result = 0;
            rpn.Transition();
            result = rpn.rpnCalculation();
            rpn = null;
            jt = String.valueOf(result);            //Converts the result to a string
            JLabelshow.setText(String.valueOf(result));
            System.out.println("\n" + jt);
        }else {
            jt += command;
            System.out.println(jt);
            JLabelshow.setText(jt);
        }
    }
}

package 简易计算器;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;
import java.util.StringTokenizer;

public class RPN {

    private Stack<String> operator = new Stack<>();                 //Create a Stack to hold the operator
    private ArrayList<String> expression = new ArrayList<>();       //Create an ArrayList to hold the expression
    private ArrayList<String> rpnexpression = new ArrayList<>();    //Create an ArrayList to hold the rpnexpression
    private static final int One = 1;
    private static final int Two = 2;                               //Define priority
    private static Stack<String> operation = new Stack<>();         //Create a Stack to operation

    //Initializes, splits strings.
    public RPN(String Str){
        StringTokenizer stringTokenizer = new StringTokenizer(Str,"+-*/",true);
        while (stringTokenizer.hasMoreElements()){
            expression.add(stringTokenizer.nextToken());
        }       //Output all elements
    }

    //Use a regular expression to determine if it is a numeric string
    public boolean Number(String str){
        if(str.matches("[0-9]+")){
            return true;
        }else {
            return false;
        }
    }

    //Determine if it is an operator
    public boolean Operator(String str){
        if(str.matches("[\\+\\-\\*\\/]")){
            return true;
        }else {
            return false;
        }
    }

    //Get priority
    public int Priority(String str){

        switch (str){
            case "*": case "/":return Two;
            case "+": case "-":return One;

            default:return -1;
        }
    }

    //Judge priority
    public boolean Precedence(String str1,String str2){
        return Priority(str1) > Priority(str2);
    }

    //operator judgment
    public void Operator_Stack(String str){
        if(operator.isEmpty()){
            operator.push(str);
            return;
        }
        if(Precedence(str,operator.peek())){
            operator.push(str);
            return;
        }       //Determines the priority of elements on the top of the stack
        if(!Precedence(str,operator.peek())){
            rpnexpression.add(operator.pop());
            Operator_Stack(str);
        }       //Call the function itself and pass the arguments
    }

    //Infix expression to postfix expression
    public void Transition(){
        //traverse expression
        for(String str:expression){
            if(Number(str)){
                rpnexpression.add(str);
            }else if(Operator(str)){
                Operator_Stack(str);
            }
        }

        //After the traversal,adds all elements in the operator to the rpnexpression
        while (!operator.isEmpty()){
            rpnexpression.add(operator.pop());
        }
    }

    //Implementation method
    public int Calculation(String str1,String str2,String str3){
        int a = Integer.parseInt(str2);
        int b = Integer.parseInt(str1);                         //Converts a string to an integer
        switch (str3){
            case "+":return a+b;
            case "-":return a-b;
            case "*":return a*b;
            case "/":return a/b;
            default:return 0;
        }
    }

    //Evaluation rpnexpression
    public int rpnCalculation(){
        for(String str:rpnexpression){
            if(Number(str)){
                operation.push(str);
            }else {
                operation.push(String.valueOf(Calculation(operation.pop(), operation.pop(), str)));
            }
        }
        return Integer.parseInt(operation.pop());               //The element in the stack is the result
    }

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        RPN rpn = new RPN(input);
        rpn.Transition();
    }
}

package com.ads.stack;
import java.util.Scanner;
interface StackADT {
 void push(String item);
 String pop();
 boolean isEmpty();
}
class ArrayStack implements StackADT {
 private final String[] stack;
 private int top;
 public ArrayStack(int size) {
 stack = new String[size];
 top = -1;
 }
 @Override
 public void push(String item) {
 if (top == stack.length - 1) {
 System.out.println("Stack Overflow!");
 } else {
 stack[++top] = item;
 }
 }
 @Override
 public String pop() {
 if (isEmpty()) {
 System.out.println("Stack Underflow!");
 return "";
 } else {
 return stack[top--];
 }
 }
 @Override
 public boolean isEmpty() {
 return top == -1;
 }
}
class TextEditor {
 private String text = "";
 private final StackADT undoStack;
 public TextEditor(StackADT stack) {
 undoStack = stack;
 }
 public void type(String newText) {
 undoStack.push(text); // save current state before change
 text += newText;
 }
 public void delete(int count) {
 if (count > text.length()) count = text.length();
 undoStack.push(text); // save state
 text = text.substring(0, text.length() - count);
 }
 public void undo() {
 if (!undoStack.isEmpty()) {
 text = undoStack.pop();
 } else {
 System.out.println("Nothing to undo!");
 }
 }
 public void display() {
 System.out.println("Current Text: " + text);
 }
}
public class Stack {
 public static void main(String[] args) {
 Scanner sc = new Scanner(System.in);
 TextEditor editor = new TextEditor(new ArrayStack(100));
 while (true) {
 System.out.println("\n1. Type Text");
 System.out.println("2. Delete Characters");
 System.out.println("3. Undo");
 System.out.println("4. Display Text");
 System.out.println("5. Exit");
 System.out.print("Enter choice: ");
 int choice = sc.nextInt();
 sc.nextLine(); // consume newline
 switch (choice) {
 case 1:
 System.out.print("Enter text to type: ");
 String t = sc.nextLine();
 editor.type(t);
 break;
 case 2:
 System.out.print("Enter number of characters to delete: ");
 int n = sc.nextInt();
 editor.delete(n);
 break;
 case 3:
 editor.undo();
 break;
 case 4:
 editor.display();
 break;
 case 5:
 System.out.println("Exiting...");
 sc.close();
 return;
 default:
 System.out.println("Invalid choice!");
 }
 }
 }
}


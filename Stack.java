//Becky Fox
//Section: AB
//Data Abstractions Assignment# 2
//Stack Class

//Stack class that can  be used to store a list of
//Binary sorting trees.
//This version implements arrays and has a fixed capacity
//of 1000 nodes.

public class Stack {

   //list of Binary sorting tree nodes
   private BinarySortingTreeNode[] elementData;
    
   //current number of elements in list
   private int size;
      
   public static final int DEFAULT_CAPACITY = 1000;
      
   //Post: constructs an empty stack of default capacity
   public Stack() {
      this(DEFAULT_CAPACITY);
   }
      
   //Param: capacity = the maximum number of items the stack
   //can hold.
   //Exception: throws an illegal argument exception if the 
   //capacity is less than 0.
   //Post: constructs an empty stack with the given capacity.
   public Stack(int capacity) {
      if (capacity < 0)
         throw new IllegalArgumentException("Capacity cannot be negative!");
      elementData = new BinarySortingTreeNode[capacity];
      size = 0;
   }
         
   //Param: node = the BinarySortingTreeNode that we are
   //placing ontop of the stack.
   //Post: puts the given element at the top of the stack. 
   public void push(BinarySortingTreeNode node) {
      elementData[size] = node;
      size++;
   }
         
   //Exception: if the stack is empty, this throws an
   //illegal state exception.
   //Post: returns the top element of stack and removes it
   //from the stack.
   public BinarySortingTreeNode pop() {
      if (size != 0) {
         size--;
         return elementData[size];
      }
      throw new IllegalStateException("There's nothing in this stack!");
   }
         
   //Exception: if the stack is empty then this throws an
   //illegal state exception.
   //Post: returns the top element of the stack.
   public BinarySortingTreeNode top() {
      if (size != 0)
         return elementData[size - 1];
      throw new IllegalStateException("There's nothing in this stack!");
   }
         
   //Post: returns true if the stack is empty, and false otherwise.
   public boolean isEmpty() {
      return size == 0;
   }
}
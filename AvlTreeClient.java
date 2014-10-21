//program to mess around with Avl trees.

public class AvlTreeClient {
   public static void main(String[] args) {
      AvlTree t = new AvlTree();
      int[] input = {1, 10, 2, 9, 3};
      t.buildTree(input);
      System.out.println("Avl Tree 1: \n");
      t.printSideways();
      System.out.println("\n\nIs tree 1 an Avl tree?");
      t.isAvl();
      
      System.out.println();
      System.out.println("Avl Tree 2: \n");
      AvlTree bad = new AvlTree();
      bad.badInsert(1);
      bad.badInsert(10);
      bad.badInsert(2);
      bad.badInsert(-1);
      bad.printSideways();
      System.out.println("\n\nIs tree 2 an Avl tree?");
      bad.isBadAvl();
   }
}
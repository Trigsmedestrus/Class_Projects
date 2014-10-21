import java.util.*;

public class AvlTree {
   private AvlNode root;
   
   private static final int ALLOWED_IMBALANCE = 1;
   
   public AvlTree() {
      root = null;
   }
   
   public AvlNode getRoot() {
      return root;
   }
   
   public int getRootKey() {
      if (root != null)
         return root.key;
      throw new IllegalStateException("The tree is empty!");
   }
      
   //Returns the height of node t, or -1, if null.
   public int height(AvlNode t) {
      return t == null ? -1 : t.height;
   }
   
   //builds a tree from a given set of keys.
   public void buildTree(int[] input) {
      for (int i = 0; i < input.length; i++)
         root = this.insert(input[i], root); 
   }
   
   //inserts new key into the tree
   //param key the item to insert
   //param t the node that roots the tree
   //return new root of tree.
   public AvlNode insert(int key, AvlNode t) {
      if (t == null)
         return new AvlNode(key, null, null);
      if (key < t.key) 
         t.left = insert(key, t.left);
      else if (key > t.key)
         t.right = insert(key, t.right);
      else
         ; //no duplicates!
      return balance(t);
   }
   
   public void badInsert(int key) {
      root = badInsert(key, root, 0);
   }
   
   public AvlNode badInsert(int key, AvlNode t, int height) {
      if (t == null)
         return new AvlNode(key, null, null, height);
      if (key < t.key)
         t.left = badInsert(key, t.left, height + 1);
      else if (key > t.key)
         t.right = badInsert(key, t.right, height + 1);
      else
         ;
      return t;
   }
   
   //Assume t is either balanced or within 1 of being balanced.
   public AvlNode balance(AvlNode t) {
      if (t == null)
         return t;
         
      if (height(t.left) - height(t.right) > ALLOWED_IMBALANCE) {
         if (height(t.left.left) >= height(t.left.right))
            t = rotateWithLeftChild(t);
         else
            t = doubleWithLeftChild(t);
      } else {
         if (height(t.right) - height(t.left) > ALLOWED_IMBALANCE) {
            if (height(t.right.right) >= height(t.right.left))
               t = rotateWithRightChild(t);
            else
               t = doubleWithRightChild(t);
         }
      }
      t.height = Math.max(height(t.left), height(t.right)) + 1;
      return t;
   }
   
   //rotate binary tree node with left child
   //for AVL trees, this is a single rotation for case 1.
   //Updates heights, then returns new root.
   public AvlNode rotateWithLeftChild(AvlNode k2) {
      AvlNode k1 = k2.left;
      k2.left = k1.right;
      k1.right = k2;
      k2.height = Math.max(height(k2.left), height(k2.right)) + 1;
      k1.height = Math.max(height(k1.left), k2.height) +1;
      return k1;
   }
   
   public AvlNode rotateWithRightChild(AvlNode k2) {
      AvlNode k1 = k2.right;
      k2.right = k1.left;
      k1.left = k2;
      k2.height = Math.max(height(k2.right), height(k2.left)) + 1;
      k1.height = Math.max(height(k1.right), k2.height) +1;
      return k1;
   }
   
   //Double roate binary tree node: first left child
   //with it's right child, then node k3 with the new
   //left child.
   //For Avl trees, this is a double rotation for case 2.
   //Updates heights, then returns the new root.
   public AvlNode doubleWithLeftChild(AvlNode k3) {
      k3.left = rotateWithRightChild(k3.left);
      return rotateWithLeftChild(k3);
   }
   
   public AvlNode doubleWithRightChild(AvlNode k3) {
      k3.right = rotateWithLeftChild(k3.right);
      return rotateWithRightChild(k3);
   }
   
   // post: prints the tree contents, one per line, following an inorder
   //       traversal and using indentation to indicate node depth; prints
   //       right to left so that it looks correct when the output is rotated.
   public void printSideways() {
       printSideways(root, 0);
   }

   // post: prints in reversed preorder the tree with given root, indenting
   //       each line to the given level
   private void printSideways(AvlNode root, int level) {
       if (root != null) {
           printSideways(root.right, level + 1);
           for (int i = 0; i < level; i++) {
               System.out.print("    ");
           }
           System.out.println(root.key);
           printSideways(root.left, level + 1);
       }
   }
   
   //helper method for remove
   public void remove(int key) {
      root = remove(key, root);
   }
   
   //Removes a key from a tree
   //param key the item to remove
   //param t the node that roots the tree
   //returns the new root of the tree
   public AvlNode remove(int key, AvlNode t) {
      if (t == null)
         return t; //item not found; do nothing.
      if (key < t.key)
         t.left = remove(key, t.left);
      else if (key > t.key)
         t.right = remove(key, t.right);
      else if (t.left != null && t.right != null) { //2 children
         t.key = findMin(t.right).key;
         t.right = remove(t.key, t.right);
      } else {
         t = (t.left != null) ? t.left : t.right;
      }
      return balance(t);
   }
   
   //finds the smallest item in a tree
   //param t the node that roots the tree
   //return node containing the smallest item
   public AvlNode findMin(AvlNode t) {
      if (t == null)
         return null;
      else if (t.left == null)
         return t;
      return findMin(t.left);
   }
   
   public void isAvl() {
      isAvl(root);
      System.out.println("Yup. It's an AVL!");
   }
      
   public void isAvl(AvlNode t) {
      if (t != null) {      
         //System.out.println("Working on: " + t.key + ", with height: " + t.height); 
         if (t.left != null && t.right == null) {
            //System.out.println("Left child height: " + t.left.height);
            assert ((t.key > t.left.key) && (t.height == (t.left.height + 1)) && (t.left.right == null && t.left.left == null));
         }
         else if (t.right != null && t.left == null) {
            //System.out.println("Right child height: " + t.right.height);
            assert ((t.key < t.right.key) && (t.height == (t.right.height + 1)) && (t.right.right == null && t.right.left == null));
         }
         else if (t.right != null && t.left != null) {
            //System.out.println("Right child height: " + t.right.height);
            //System.out.println("Left child height: " + t.left.height);
            assert ((t.key < t.right.key) && (t.key > t.left.key) && (t.height == Math.max(t.right.height, t.left.height) + 1) && (Math.abs(t.right.height - t.left.height) <= 1));
            isAvl(t.left);
            isAvl(t.right);
         }
      }
   }
   
   public void isBadAvl() {
      isBadAvl(root);
      System.out.println("Yup. It's an AVL!");
   }
   
   public void isBadAvl(AvlNode t) {
      if (t != null) {      
         if (t.left != null && t.right == null) {
            //System.out.println("Left child height: " + t.left.height);
            assert ((t.key > t.left.key) && (t.height == (t.left.height - 1)) && (t.left.right == null && t.left.left == null));
         }
         else if (t.right != null && t.left == null) {
            //System.out.println("Right child height: " + t.right.height);
            assert ((t.key < t.right.key) && (t.height == (t.right.height - 1)) && (t.right.right == null && t.right.left == null));
         }
         else if (t.right != null && t.left != null) {
            //System.out.println("Right child height: " + t.right.height);
            //System.out.println("Left child height: " + t.left.height);
            assert ((t.key < t.right.key) && (t.key > t.left.key) && (t.height == Math.max(t.right.height, t.left.height) - 1) && (Math.abs(t.right.height - t.left.height) <= 1));
            isAvl(t.left);
            isAvl(t.right);
         }
      }
   }

   private static class AvlNode {
      int key; //node's data field.
      AvlNode left; //reference to left child.
      AvlNode right; //reference to right child.
      int height; //height of node.
      
      //Constructors.
      public AvlNode(int key) {
         this(key, null, null);
      }
      
      private AvlNode(int key, AvlNode left, AvlNode right) {
         this.key = key;
         this.left = left;
         this.right = right;
         height = 0;
      }
      
      private AvlNode(int key, AvlNode left, AvlNode right, int height) {
         this.key = key;
         this.left = left;
         this.right = right;
         this.height = height;
      }
   }
}
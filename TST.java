import java.util.Iterator;
import java.util.LinkedList;

public class TST<T extends Comparable<T>> implements Iterable<T>{
	// root node of the tree
    TSTNode<T> root;
    
    
    public static LinkedList myList = new LinkedList();

    // constructor
    public TST() {
        this.root = null;
    }
    
    public void insert(T element){
    	if(root != null) {	// if tree is not empty
    		this.root.insert(this.root, element);	// calling the helper method
    	}else{	// if tree is empty
    		root = new TSTNode<T>(element);
    	}
    } 
 
    public void remove(T element){
        myList.remove(element);
        remove(this.root, element);  
    }
    
    public TSTNode<T> remove(TSTNode<T> root, T element) {	// helper method	
        if(root == null) return null;
        TSTNode<T> foundNode = find(root, element);
        if(foundNode == null) return null;
        int compareResult = element.compareTo(root.element);
        if(compareResult < 0) {
        	root.left = remove(root.left, element);
        } else if (compareResult > 0) {
        	root.right = remove(root.right, element);
        }else {	// if element == root.element
        	if((root.left == null) && (root.right == null)) {
        		if(this.root.equals(root)) {
        			this.root = null;
        		}else {
        			root = null;
        		}
        	} else if((root.left == null) && (root.right != null)) {
        		root.element = root.right.element;
        		root.right = remove(root.right, root.element);
        	} else if((root.right == null) && (root.left != null)) {
        		root.element = root.left.element;
        		root.left = remove(root.left, root.element);
        	}else {	// if root has both left and right children
        		root.element = root.left.findMax().element;
        		root.left = remove(root.left, root.element);
        	}
        }
    	return root;
    }
    
    public TSTNode<T> find (TSTNode<T> root, T element){	// helper method
    	if(root == null) return null;
    	int compareResult = root.element.compareTo(element);
    	if(compareResult == 0)	// if equal
    		return root;
    	else if (compareResult > 0)	// if element < root.element
    		return find(root.left, element);
    	else	// if element > root.element
    		return find(root.right, element);
       }

    public boolean contains(T element){
    	return this.root.contains(this.root, element);	// calling the helper method
    }
    
	LinkedList<T> myList2 = new LinkedList<T>();		
    public void rebalance(){      

    	inOrderTraversal(root);
    	root = null;	// try to remove the old tree
    	rebalance(0,myList2.size());	// call a helper method with parameters
    }
    
    public void rebalance(int low, int high) {
    	if(high == low) {
    		return;
    	}
    	int midIndex = (high + low) / 2;
    	T node = (T) myList2.get(midIndex);
    	insert(node);
        rebalance(midIndex+1, high);
        rebalance(low, midIndex);	
    }
    

    public void inOrderTraversal(TSTNode<T> root) {		
   		if(root == null) {
   			return;
   		}	
   		inOrderTraversal(root.left);    		
   		inOrderTraversal(root.mid);
    	myList2.add(root.element);
    	inOrderTraversal(root.right);
   	}
    	
    
    
    /**
     * Calculate the height of the tree.
     * You need to implement the height() method in the TSTNode class.
     *
     * @return -1 if the tree is empty otherwise the height of the root node
     */
    public int height(){
        if (this.root == null)
            return -1;
        return this.root.height();
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator iterator() {
        return new TSTIterator();
    }

    // --------------------PROVIDED METHODS--------------------
    // The code below is provided to you as a simple way to visualize the tree
    // This string representation of the tree mimics the 'tree' command in Unix
    // with the first child being the left child, the second being the middle child, and the last being the right child.
    // The left child is connect by ~~, the middle child by -- and the right child by __.
    // e.g. consider the following tree
    //               5
    //            /  |  \
    //         2     5    9
    //                   /
    //                  8
    // the tree will be printed as
    // 5
    // |~~ 2
    // |   |~~ null
    // |   |-- null
    // |   |__ null
    // |-- 5
    // |   |~~ null
    // |   |-- null
    // |   |__ null
    // |__ 9
    //     |~~ 8
    //     |   |~~ null
    //     |   |-- null
    //     |   |__ null
    //     |-- null
    //     |__ null
    @Override
    public String toString() {
        if (this.root == null)
            return "empty tree";
        // creates a buffer of 100 characters for the string representation
        StringBuilder buffer = new StringBuilder(100);
        // build the string
        stringfy(buffer, this.root,"", "");
        return buffer.toString();
    }

    /**
     * Build a string representation of the tertiary tree.
     * @param buffer String buffer
     * @param node Root node
     * @param nodePrefix The string prefix to add before the node's data (connection line from the parent)
     * @param childrenPrefix The string prefix for the children nodes (connection line to the children)
     */
    private void stringfy(StringBuilder buffer, TSTNode<T> node, String nodePrefix, String childrenPrefix) {
        buffer.append(nodePrefix);
        buffer.append(node.element);
        buffer.append('\n');
        if (node.left != null)
            stringfy(buffer, node.left,childrenPrefix + "|~~ ", childrenPrefix + "|   ");
        else
            buffer.append(childrenPrefix + "|~~ null\n");
        if (node.mid != null)
            stringfy(buffer, node.mid,childrenPrefix + "|-- ", childrenPrefix + "|   ");
        else
            buffer.append(childrenPrefix + "|-- null\n");
        if (node.right != null)
            stringfy(buffer, node.right,childrenPrefix + "|__ ", childrenPrefix + "    ");
        else
            buffer.append(childrenPrefix + "|__ null\n");
    }

    /**
     * Print out the tree as a list using an enhanced for loop.
     * Since the Iterator performs an in-order traversal, the printed list will also be in-order.
     */
    public void inorderPrintAsList(){
        String buffer = "[";
        for (T element: this) {
            buffer += element + ", ";
        }
        int len = buffer.length();
        if (len > 1)
            buffer = buffer.substring(0,len-2);
        buffer += "]";
        System.out.println(buffer);
    }
}

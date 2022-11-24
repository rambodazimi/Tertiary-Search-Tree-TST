
class TSTNode<T extends Comparable<T>>{
    T element;     	            // data in the node
    TSTNode<T>  left;   		// left child (smaller values)
    TSTNode<T>  mid;   		    // middle child (same values)
    TSTNode<T>  right;  		// right child (bigger values)
    
    TSTNode(T element){	// constructor assigns the given element to this node; the children are automatically initialized to null.
    	this.element = element;
    	this.left = null;
    	this.mid = null;
    	this.right = null;
    	TST.myList.add(element);
    } 
    
    TSTNode<T> findMax(){	// don't need to worry about the mid node, because it has the similar value. So, neglect it!
    	if((this == null) || (right == null)) {
    		return this;
    	}else {
    		return this.right.findMax();
    	}
    }

    TSTNode<T> findMin(){	// don't need to worry about the mid node, because it has the similar value. So, neglect it!
    	if((this == null) || (left == null)) {
    		return this;
    	}else {
    		return this.left.findMin();
    	}
    }
    
    int height(){	// returns an integer which is the height of the subtree, whose root is this TSTNode
    	if(this == null) {
    		return -1;
    	}else {
	    	int leftHeight = 0;
	    	int rightHeight = 0;
	    	int midHeight = 0;
	    	if(this.left != null) {
	    		leftHeight = this.left.height();
	    	}
	    	if(this.right != null) {
	    		rightHeight = this.right.height();
	    	}
	    	if(this.mid != null) {
	    		midHeight = this.mid.height();
	    	}
	    	if((this.left == null) && (this.mid == null) && (this.right == null)) {
	    		return 0;
	    	}
	    	
	   		// finding the max between 3 values
	   		return (Math.max(Math.max(rightHeight, leftHeight), midHeight))+1;
    	}
    }

	public TSTNode<T> insert(TSTNode<T> root, T element) {	// helper method with parameters
		if(root == null) {
			return new TSTNode<T>(element);
		}else {
			int compareResult = element.compareTo(root.element);	//compare element with the root element to decide which children to go
			if(compareResult < 0) {	// if key < root.key
				root.left = insert(root.left, element);
			}else if(compareResult > 0) {	// if key > root.key
				root.right = insert(root.right, element);
			}else {	// if key == root.key
				root.mid = insert(root.mid, element);
			}
			return root;
		}
	}

	public boolean contains(TSTNode<T> root, T element) {	// helper method with parameters
		boolean isFound = false;
		if(root == null) {	// if tree is empty
			return false;
		}else{	// if tree is not empty
	        if(root.element.equals(element)){
	           isFound = true;
	           return isFound;
	        }else{	// if not equal
	            isFound = contains(root.right, element);	// check the right child
	            if(!isFound) {
	                isFound = contains(root.left, element);	// check the left child
	            }
	            return isFound;	// note that we don't need to check the mid nodes at all!
	         }
	    }
	}


    
}

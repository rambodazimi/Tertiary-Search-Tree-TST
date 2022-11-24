# Tertiary-Search-Tree-TST

<h3>Description</h3>

You will work with a data structure that we will call a tertiary search tree or TST.1 This is like a binary search tree (BST), except that each node in a TST has three children rather than two. We can call the three children left, middle, and right. Each node stores a element whose class type implements Comparable, so you can assume access to a compareTo() method. The subtrees defined by the left, middle, and right children of a node have elements that are less than, equal to, or greater than the element at that node, respectively.

<h4>TSTNode class</h4>

A TSTNode has four fields: an element, and three children (left, middle, right). For this class, you will write:

• a constructor TSTNode(T element) - assigns the given element to this node; the children are automatically initialized to null.

• height() – returns an int which is the height of the subtree, whose root is this TSTNode

• findMin() – returns the TSTNode containing the minimum element in the tree; you can use this as a helper method.

• findMax() – return the TSTNode containing the maximum element in the tree; you can use this as a helper method.

<h4>TST class</h4>

A TST has just one field: the root TSTNode of the tree. A TST has several methods that are provided to you:

• height() – returns an int which is the height of the tree; it depends on the height() helper method in the TSTNode class

• toString() – toString() can be used to visualize the tree structure; recall that you can ’call’ the toString() method using System.out.print(tree) where tree is of type TST;

stringify(...) – a helper method used by toString()

• inorderPrintAsList() – prints the elements in order using an enhanced for loop; the enhanced for loop implicitly uses an Iterator, and so you will need to implement the Iterator (see below) before you can use this print method;

Write the following methods for the TST class.

• insert(T element ) – void method; it creates a new node in the tree and assigns the element of that node; the node should be placed in the correct position; duplicates are allowed.

• contains(Telement) – returns a boolean(true if the tree contains a node with this element, and false otherwise)

• iterator() – returns an TSTIterator for the TST. See the TSTIterator class below. This will be evalu- ated as part of the tests for TSTIterator.

• rebalance() – void method; specifications are as follows.

Just as a BST can become imbalanced as discussed in the lecture, so can a TST.

We define rebalance() for a TST as follows. Traverse the tree in-order and make an arraylist of the sorted elements. If there are duplicates (multiple instances of the same element), then include them all in the list. Next, recursively partition the list similar to what is done in binary search: consider the element at the middle position size/2; this element will be the one at the root node of the rebalanced TST; all elements that are less than, equal to, or greater than the root element will be stored in the left, middle, or right child subtree, respectively. You may use the List.subList() method here.

Note that this is not necessarily going to give a “balanced” tree in the intuitive sense of balance. For example, if the tree has elements 1, 1, 1, 1, 5, 5, 5, 5, 5, 5 then the “rebalanced” tree would have element 5 at the root node, the root would have no right child, and the root node’s left subtree would have height 3. For TST’s that have relatively few duplicates, the rebalance() method would do a better job of rebalancing.

• remove(T element) – returns nothing (void); it finds a node containing this element and removes it; if there are duplicate elements, then exactly one instance must be removed; which one to remove is up to you, and indeed we have no way to distinguish which one you remove without manually examining your code;

<h4>TSTIterator class</h4>

This class implements Iterator. Write the following methods: 

• a TSTIterator constructor

• hasNext()

• next()

The constructor should create an ordered list of all the elements in the tree. The hasNext() and next() methods then use this list. For grading, we won’t test the constructor directly. Rather we will test that the Iterator works as it should, namely iterating through the list should yield the elements in their proper order.

Note: since TST implements Iterable, you can use the Java enhanced for loop, once you have success- fully implemented the TSTIterator class.



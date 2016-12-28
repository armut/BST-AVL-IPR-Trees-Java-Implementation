# BST/AVL/IPR Tree Java Implementation

I tried to implement binary search tree, Adelson-Velsky Landis(AVL) tree and internal path reduction(IPR) tree in Java.
The original paper of IPR tree can be found [here](https://www.researchgate.net/publication/220420554_Balancing_Binary_Trees_by_Internal_Path_Reduction).
Also you can find a C code for the algorithm [here](https://users.dcc.uchile.cl/~rbaeza/handbook/algs/3/3415.chckr.c.html).

AVL and IPR classes inherit BST class. Both of them uses binary insertion first and after that verifies its specific property. Same occurs in the deletion.

Key features in my implementation are:
* Insertion
* Deletion and
* Search

Feel free to correct my errors, either by mailing or just commenting.

## Drawing the Tree

You can draw the tree graphically with the help of graphviz. The method is drawTree. This method writes the tree in **DOT** format. You can generate the graphic issuing the command below:

```
# if you haven't installed yet,
$ sudo apt-get install graphviz

# draw the tree,
$ dot -Tpng tree.dot -o tree.png
```

I managed to do it with the help of Eli Bendersky's [this](http://eli.thegreenplace.net/2009/11/23/visualizing-binary-trees-with-graphviz) helpful post. Here is an example of generated **dot** file:

```
digraph tree {
   node [fontname="Arial"];
   Greenville -> Ahoskie;
   null0 [shape=point];
   Ahoskie -> null0;
   Ahoskie -> Boone;
   null1 [shape=point];
   Boone -> null1;
   null2 [shape=point];
   Boone -> null2;
   Greenville -> Hampton;
   Hampton -> Gusey;
   Gusey -> Gurnsey;
   null3 [shape=point];
   Gurnsey -> null3;
   null4 [shape=point];
   Gurnsey -> null4;
   Gusey -> Hamilton;
   null5 [shape=point];
   Hamilton -> null5;
   null6 [shape=point];
   Hamilton -> null6;
   Hampton -> Jefferson;
   null7 [shape=point];
   Jefferson -> null7;
   null8 [shape=point];
   Jefferson -> null8;
}
```

## Screenshots

Here is an example tree with string values:

![Tree with string keys](/ss/tree1.png?raw=true)

Keys are added in this order: "Jefferson", "Hampton", "Greenville", "Ahoskie", "Gurnsey", "Boone", "Gusey", "Hamilton"

Another example with integer values:

![Tree with integer keys](/ss/tree2.png?raw=true)

Keys are added in this order: 60, 30, 90, 15, 35, 70, 95

## Compiling/Running the Program

For a quick test, you can cd into src/ folder and compile them all and run Main:

```
$ javac Node.java BST.java AVL.java IPR.java Main.java
$ java Main
```

That's all.

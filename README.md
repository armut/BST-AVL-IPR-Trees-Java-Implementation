# BST/AVL/IPR Tree Java Implementation

I tried to implement binary search tree, Adelson-Velsky Landis(AVL) tree and internal path reduction(IPR) tree in Java.
The original paper of IPR tree can be found [here](https://www.researchgate.net/publication/220420554_Balancing_Binary_Trees_by_Internal_Path_Reduction).
Also you can find a c code for the algorithm [here](https://users.dcc.uchile.cl/~rbaeza/handbook/algs/3/3415.chckr.c.html).

AVL and IPR classes inherit BST class. Both of them uses binary insertion first and after that verifies its specific property. Same occurs in the deletion.

Key features in my implementation are:
* Insertion
* Deletion and
* Search

Feel free to correct my errors, either by mailing or just commenting.

## Compiling/Running the Program

For a quick test, you can cd into src/ folder and compile them all and run Main:

```
$ javac Node.java BST.java AVL.java IPR.java Main.java
$ java Main
```

That's all.

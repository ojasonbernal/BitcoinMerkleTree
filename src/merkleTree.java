/*
File: merkleTree.java
Project: CSCE314 Project, Fall 2020
Author: Jason Bernal, Warren Zhou
Date: 11/7/2020
Section: 501, 502 (respectively)
Email: ojasonbernal@tamu.edu, warrenzliu@tamu.edu (respectively)

This file contains the merkleTree object for our project. This will be our implementation of the data structure Merkle Tree.
We will use this to store and verify transactions.
 */



//COLLECTIONS USED HERE (ARRAYLIST)
import java.util.ArrayList;

public abstract class merkleTree{

    //class variables
    private ArrayList<Transaction> transactions;
    private ArrayList<ArrayList<Transaction>> completedMerkleTree;
    private String root;

    //default constructor
    public merkleTree(){
        //COLLECTIONS USED HERE (ARRAYLIST)
        transactions = new ArrayList<Transaction>();
        completedMerkleTree = new ArrayList<>();
        root = "";
    }

    //parameterized constructor
    public merkleTree(ArrayList<Transaction> transactions){
        this.transactions = transactions;
    }

    //getters and setters functions
    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }

    public ArrayList<ArrayList<Transaction>> getCompletedMerkleTree() {
        return completedMerkleTree;
    }

    public String getRoot() {
        return root;
    }

    public void setTransactions(ArrayList<Transaction> transactions) {
        this.transactions = transactions;
    }

    public void setCompletedMerkleTree(ArrayList<ArrayList<Transaction>> completedMerkleTree) {
        this.completedMerkleTree = completedMerkleTree;
    }

    public void setRoot(String root) {
        this.root = root;
    }

    //-------------------------------------------------------
    // Name: performHash
    // PreCondition:  passed in transaction needs to not be null
    // PostCondition: performs a hash function on the transaction and returns the value of the hash
    // GENERICS, STUBS, AND ABSTRACTION USED HERE
    //---------------------------------------------------------
    public abstract <T> Transaction performHash(T value);

    //Since java doesn't have a log2 function, this function does that
    static int log2(int x){
        return (int) (Math.log(x)/Math.log(2));
    }

    //createMerkleTree
    //Given a list of hashed transactions, create the merkle tree and store it inside the variable createMerkleTree
    public void createMerkleTree(){
        //Write code to create the merkle tree from the array of hashedTransactions
        //set variable completedMerkleTree to the created merkle tree
        ArrayList<Transaction> hashedTransactions = new ArrayList<>();
        for (Transaction transaction : this.transactions) {
            Transaction hashedTrans = performHash(transaction);
            hashedTransactions.add(hashedTrans);
        }
        int counter = log2(hashedTransactions.size());

        ArrayList<ArrayList<Transaction>> completedTree = new ArrayList<>();
        completedTree.add(hashedTransactions);

        for (int i = 0; i < counter; i++){
            ArrayList<Transaction> newLevel = new ArrayList<>();
            for (int j = 0; j < hashedTransactions.size(); j+=2){
                String combinedId = hashedTransactions.get(j).getId() + hashedTransactions.get(j + 1).getId();
                Transaction trans = new Transaction(combinedId);
                trans = performHash(trans);
                newLevel.add(trans);
            }
            completedTree.add(newLevel);
            hashedTransactions = newLevel;
        }

        this.completedMerkleTree = completedTree; //placeholder
    }


    //findRoot
    //given a completedMerkleTree, set the root variable to the root
    public void findRoot(){
        //Write code to get the root based on the array of hashed transactions
        int sz = this.completedMerkleTree.size();
        this.root = this.completedMerkleTree.get(sz-1).get(0).getId();
    }

    public boolean verifyTransaction(Transaction transaction, int index){
        findRoot();
        ArrayList<Transaction> arr = new ArrayList<>();//change variable name
        int numRun = log2(transactions.size())+1;
        Transaction hashedOriginal = performHash(transaction);
        arr.add(hashedOriginal);
        if (numRun == 2){
            if (index%2==0){
                arr.add(completedMerkleTree.get(0).get(index+1));
            }
            else{
                arr.add(completedMerkleTree.get(0).get(index-1));
            }
            Transaction root = new Transaction(this.root);
            arr.add(root);
        }
        else if (numRun == 3){
            if (index%2==0){
                arr.add(completedMerkleTree.get(0).get(index+1));
            }
            else{
                arr.add(completedMerkleTree.get(0).get(index-1));
            }
            if (index%4<2){
                arr.add(completedMerkleTree.get(1).get(1));
            }
            else{
                arr.add(completedMerkleTree.get(1).get(0));
            }
            Transaction root = new Transaction(this.root);
            arr.add(root);
        }
        else{
            if (index%2==0){
                arr.add(completedMerkleTree.get(0).get(index+1));
            }
            else{
                arr.add(completedMerkleTree.get(0).get(index-1));
            }
            if (index%4<2 && index<4){
                arr.add(completedMerkleTree.get(1).get(1));
            }
            else if(index%4>=2 && index<4){
                arr.add(completedMerkleTree.get(1).get(0));
            }
            else if(index%4<2){
                arr.add(completedMerkleTree.get(1).get(3));
            }
            else{
                arr.add(completedMerkleTree.get(1).get(2));
            }
            if (index<4){
                arr.add(completedMerkleTree.get(2).get(0));
            }
            else{
                arr.add(completedMerkleTree.get(2).get(1));
            }
            Transaction root = new Transaction(this.root);
            arr.add(root);
        }
        int sz = arr.size();
        while(sz>2){
            String combinedId = arr.get(0).getId() + arr.get(1).getId();
            Transaction combinedTrans = new Transaction(combinedId);
            combinedTrans=performHash(combinedTrans);
            arr.remove(0);
            arr.remove(0);
            arr.add(0,combinedTrans);
            sz--;
        }
        return arr.get(0).getId().equals(arr.get(1).getId());
    }
}

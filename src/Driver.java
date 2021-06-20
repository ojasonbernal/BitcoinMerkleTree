/*
I plan to use merkle trees to implement a way to store and verify Bitcoin transactions. 
Each merkle tree will contain a set of transactions made by the user.The maximum amount of transactions 
a merkle tree can have is eight. Finally, I plan to allow for verification for a merkle tree by checking 
the hash root. This will allow the user to check if any transactions has been modified. 
I will also use a hash algorithm to create the leaves and the rest of the tree from the original transaction ids.

For stubs, I instantiated the function performHash in merkleTree but I defined it in bitcoinMerkleTree.
For Collections, I am using the ArrayList library for our arrays
For Generics, it could be possible to change the Transaction data type to a more general data type

*/

import java.util.ArrayList;

public class Driver {
    public static void main(String[] args){

        ArrayList<Transaction> transArr = new ArrayList<>();
        Transaction trans1 = new Transaction("1", 532.5 , "5/2/2020", "bob");
        Transaction trans2 = new Transaction("2", 1632.3 , "7/20/2009", "robbie");
        Transaction trans3 = new Transaction("3", 123.0 , "1/15/2015", "john");
        Transaction trans4 = new Transaction("4", 1792.0 , "1/15/2017", "pal");
        transArr.add(trans1);
        transArr.add(trans2);
        transArr.add(trans3);
        transArr.add(trans4);
        merkleTree testMerkle = new bitcoinMerkleTree(transArr);
        testMerkle.createMerkleTree();
        bitcoinMerkleTree bcmt = new bitcoinMerkleTree();


        //Test transaction1
        if(testMerkle.verifyTransaction(trans1,0)){
            System.out.println("Transaction has been verified to be the original data block.\n");
        }
        else{
            System.out.println("Nope");
        }

        //verify transaction 1 for user
        System.out.println(trans1);
        System.out.println(testMerkle.getTransactions().get(0));
        System.out.println("\n\n");
        System.out.println(bcmt.performHash(trans1));
        System.out.println(testMerkle.getCompletedMerkleTree().get(0).get(0));


    }
}

/*
File: bitCoinMerkleTree.java
Project: CSCE314 Project, Fall 2020
Author: Jason Bernal, Warren Zhou
Date: 11/7/2020
Section: 501, 502 (respectively)
Email: ojasonbernal@tamu.edu, warrenzliu@tamu.edu (respectively)

This file extends the merkle tree class and needs to define the hash function since that can't be different across
different types of merkle trees
 */


import java.util.ArrayList;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

//INHERITANCE USED HERE
public class bitCoinMerkleTree extends merkleTree{

    //default constructor
    public bitCoinMerkleTree(){
        super();
    }

    //parameterized constructor
    public bitCoinMerkleTree(ArrayList<Transaction> transactions){
        super(transactions);
    }

    //performHash
    //performs the hash on a given transaction and returns that hash
    public <T> Transaction performHash(T value) {
        //perform hash for bitcoin specifically
        //This code was taken and modified slightly from GeeksForGeeks (We are not passing this hash as ours)
        //We modified this slightly by changing the output of the function to be a transaction object instead of a string
        //we also used SHA-256 instead of 512 and outputted 16 bit instead of 32
        //https://www.geeksforgeeks.org/sha-512-hash-in-java/?ref=rp
        {
            try {
                // getInstance() method is called with algorithm SHA-256
                MessageDigest md = MessageDigest.getInstance("SHA-256");

                // digest() method is called
                // to calculate message digest of the input string
                // returned as array of byte
                byte[] messageDigest;
                if (value instanceof Transaction){
                    messageDigest = md.digest(((Transaction) value).getId().getBytes());
                }
                else{
                    //only works on primitive data types
                    messageDigest = md.digest(String.valueOf(value).getBytes());
                }
                // Convert byte array into signum representation

                BigInteger no = new BigInteger(1, messageDigest);

                // Convert message digest into hex value
                String hashtext = no.toString(16);

                // Add preceding 0s to make it 16 bit
                while (hashtext.length() < 16) {
                    hashtext = "0" + hashtext;
                }
                //return transaction of hashtext
                return new Transaction(hashtext);
            }

            catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

/*
This file extends the merkle tree class and needs to define the hash function since that can't be different across
different types of merkle trees
*/


import java.util.ArrayList;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

//INHERITANCE USED HERE
public class bitcoinMerkleTree extends merkleTree{

    //default constructor
    public bitcoinMerkleTree(){
        super();
    }

    //parameterized constructor
    public bitcoinMerkleTree(ArrayList<Transaction> transactions){
        super(transactions);
    }

    //performHash
    //performs the hash on a given transaction and returns that hash
    public <T> Transaction performHash(T value) {
        //perform hash for bitcoin specifically
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

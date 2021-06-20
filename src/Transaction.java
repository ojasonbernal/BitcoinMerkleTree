/*
This class is used to store the information that could happen inside of a transaction for example,
the value of the transaction, the buyer, the id, etc.
*/


public class Transaction {
    //data members
    private String id;
    private Double value;
    private String dateOfTransaction;
    private String buyerName;

    //default constructor
    public Transaction(){
        this.id = "";
        this.value = 0.0;
        this.dateOfTransaction = "";
        this.buyerName = "";
    }

    //parameterized constructor
    public Transaction(String id){
        this.id = id;
        this.value = 0.0;
        this.dateOfTransaction = "";
        this.buyerName = "";
    }
    public Transaction(String id, Double value, String dateOfTransaction, String buyerName){
        this.id = id;
        this.value = value;
        this.dateOfTransaction = dateOfTransaction;
        this.buyerName = buyerName;
    }

    //getId
    //returns id
    public String getId(){
        return id;
    }

    //getValue
    //returns value
    public Double getValue(){
        return value;
    }

    //getDateOfTransaction
    //returns dateOfTransaction
    public String getDateOfTransaction(){
        return dateOfTransaction;
    }

    //getBuyerName
    //returns buyerName
    public String getBuyerName(){
        return buyerName;
    }

    //setId
    //sets the id to String passed in
    public void setId(String id){
        this.id = id;
    }

    //setValue
    //sets value to Double passed in
    public void setValue(Double value){
        this.value = value;
    }

    //setDateOfTransaction
    //sets dateOfTransaction to String passed in
    public void setDateOfTransaction(String dateOfTransaction){
        this.dateOfTransaction = dateOfTransaction;
    }

    //setBuyerName
    //sets buyerName to String passed in
    public void setBuyerName(String buyerName){
        this.buyerName = buyerName;
    }

    //ToString
    public String toString(){
        return "Transaction id: " + getId() + "\nvalue: " + getValue() + "\nDate of Transaction: " + getDateOfTransaction() + "\nBuyer Name: " + getBuyerName();
    }


}

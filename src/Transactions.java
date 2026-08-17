public class Transactions {
    
    private TransactionNode head;
    private TransactionNode current;

    public Transactions(){
        this.head = null;
        this.current = null;
    }

    public void add(Transaction tran){
        TransactionNode newNode = new TransactionNode(tran);
        newNode.next = head;
        head = newNode;

    }
        // ITERATOR METHODS
    public void reset(){
        current = head;
    }

    public boolean hasNext(){
        return current != null;
    }

    public Transaction getNext(){
        if(!hasNext()){
            return null;
        }
        Transaction temp = current.data;
        current = current.next;
        return temp;
    }
}

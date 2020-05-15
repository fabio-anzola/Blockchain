import blockchain.Blockchain;

public class Main {
    public static void main(String[] args) {
        Blockchain bc = new Blockchain(10);
        System.out.println(bc);
        //bc.validate();
        //System.out.println(bc.validate());
    }
}

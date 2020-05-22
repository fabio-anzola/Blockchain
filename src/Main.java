import blockchain.Blockchain;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter how many zeros the hash must starts with: ");
        int zeros = scanner.nextInt();
        Blockchain bc = new Blockchain(5, zeros);
        System.out.println(bc);
        System.out.println(bc.validate());
        //System.out.println(bc.validate());
    }
}

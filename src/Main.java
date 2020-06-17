import blockchain.Blockchain;
import miner.Miner;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
       Blockchain bc = new Blockchain();
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < 1; i++) {
            new Miner(bc).start();
        }
        while (true) {
            bc.messageCache.add(sc.nextLine());
            System.out.println("now");
        }
    }
}

import blockchain.Blockchain;
import miner.Miner;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Blockchain bc = new Blockchain();
        Scanner sc = new Scanner(System.in);
        new Miner(bc).start();
        while (true) {
            bc.messageCache.add(sc.nextLine());
        }
//        bc.messageCache.add("miner13 sent 10 VC to Bob");
//        bc.messageCache.add("miner13 sent 10 VC to Carshop");
//        while (bc.getSize() < 15) {
//        }
//        System.exit(0);
    }
}
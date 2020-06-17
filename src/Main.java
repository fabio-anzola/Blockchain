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
        Thread.sleep(1000);
        bc.messageCache.add("Tom: Hey, I'm first!");
        bc.messageCache.add("Sarah: It's not fair!");
        Thread.sleep(2000);
        bc.messageCache.add("Sarah: You always will be first because it is your blockchain!");
        Thread.sleep(1000);
        bc.messageCache.add("Sarah: Anyway, thank you for this amazing chat.");
        Thread.sleep(2000);
        bc.messageCache.add("Tom: You're welcome :)");
        bc.messageCache.add("Nick: Hey Tom, nice chat");

        System.exit(0);
    }
}

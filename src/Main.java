import blockchain.Blockchain;
import miner.Miner;

public class Main {
    public static void main(String[] args) throws Exception {

        Blockchain bc = new Blockchain(0, 0);

        for (int i = 0; i < 1; i++) {
            new Miner(bc).start();
        }

    }
}

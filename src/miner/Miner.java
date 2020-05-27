package miner;

import blockchain.Block;
import blockchain.Blockchain;

public class Miner extends Thread{
    private volatile Blockchain bc;

    //TODO create Thread(s) with miner functionality

    //TODO update Javadoc

    public Miner(Blockchain bc) {
        this.bc = bc;
    }

    @Override
    public void run() {
        while (true) {
            try {
                bc.appendFromMiner(new Block(bc.getLastBlock(), bc.getRequiredZeros()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

package miner;

import blockchain.Block;
import blockchain.Blockchain;

public class Miner extends Thread{
    private volatile Blockchain bc;

    //TODO update Javadoc

    public Miner(Blockchain bc) {
        this.bc = bc;
    }

    @Override
    public void run() {
        while (true) {
            try {
                if (bc.getSize() > 0) {
                    Block temp = new Block(bc.getLastBlock(), bc.getRequiredZeros());
                    temp.setMinerID(Thread.currentThread().getId());
                    bc.appendFromMiner(temp);
                } else {
                    Block temp = new Block(null, bc.getRequiredZeros());
                    temp.setMinerID(Thread.currentThread().getId());
                    bc.appendFromMiner(temp);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

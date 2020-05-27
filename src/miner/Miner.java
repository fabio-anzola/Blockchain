package miner;

import blockchain.Block;
import blockchain.Blockchain;

/**
 * Creates a miner
 *
 * @author fabioanzola richardkrikler tobiasrigler
 */
public class Miner extends Thread {

    /**
     * Stores the Blockchain
     */
    private final Blockchain bc;

    /**
     * Constructor of Miner
     *
     * @param bc current Blockchain
     */
    public Miner(Blockchain bc) {
        this.bc = bc;
    }

    /**
     * If this thread was constructed using a separate
     * {@code Runnable} run object, then that
     * {@code Runnable} object's {@code run} method is called;
     * otherwise, this method does nothing and returns.
     * <p>
     * Subclasses of {@code Thread} should override this method.
     *
     * @see #start()
     */
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

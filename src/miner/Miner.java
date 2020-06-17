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
     * Store the amount of money of a Miner
     */
    private long money;

    /**
     * Constructor of Miner
     *
     * @param bc current Blockchain
     */
    public Miner(Blockchain bc) {
        this.money = 100;
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
        System.out.print("");
        while (true) {
            try {
                if (bc.getSize() > 0) {
                    Block temp = new Block(bc.getLastBlock(), bc.getRequiredZeros());
                    temp.setMinerID(Thread.currentThread().getId());
                    addMoney(100);
                    bc.appendFromMiner(temp);
                } else {
                    Block temp = new Block(null, bc.getRequiredZeros());
                    temp.setMinerID(Thread.currentThread().getId());
                    addMoney(100);
                    bc.appendFromMiner(temp);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Add a specific amount of money to the current miner
     * @param amount of money
     */
    public void addMoney(long amount) {
        this.money += amount;
    }
}

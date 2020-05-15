package blockchain;

import java.util.ArrayList;
import java.util.List;

/**
 * The Blockchain class
 *
 * @author fabioanzola
 */
public class Blockchain {

    /**
     * Stores all of the Blocks from the Blockchain
     */
    List<Block> chain;

    /**
     * Creates a Blockchain with a specified number of Blocks
     *
     * @param numberOfBlocks The number of Blocks which should be added to the Blockchain
     */
    public Blockchain(long numberOfBlocks) {
        this.chain = new ArrayList<>();
        appendChain(numberOfBlocks);
    }

    /**
     * Appends multiple Blocks to the Blockchain
     *
     * @param nrOfBlocks The number of Blocks which should be added
     */
    private void appendChain(long nrOfBlocks) {
        int size = this.chain.size();
        for (int i = size; i < size + nrOfBlocks; i++) {
            if (i == 0) {
                this.chain.add(new Block(null));
            } else {
                this.chain.add(new Block(this.chain.get(i - 1)));
            }
        }
    }

    /**
     * Shows Information about all the Blocks
     *
     * @return The Output of all Blocks
     */
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Block block : this.chain) {
            stringBuilder.append(block);
        }
        return stringBuilder.toString();
    }

    /**
     * Checks the integrity of the Blockchain (Checks if it is valid)
     *
     * @return If the Blockchain is valid
     */
    public boolean validate() {
        Block previousBlock = null;
        for (int i = 0; i < this.chain.size(); i++) {
            Block currentBlock = this.chain.get(i);
            if (i == 0) {
                if (!currentBlock.getPreviousHash().equals("0")) {
                    return false;
                }
            } else {
                if (!currentBlock.getPreviousHash().equals(previousBlock.getHash())) {
                    return false;
                }
            }
            previousBlock = this.chain.get(i);
        }
        return true;
    }
}

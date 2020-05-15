package blockchain;

import java.util.ArrayList;
import java.util.List;

/**
 * The Blockchain class
 *
 * @author fabioanzola
 */
public class Blockchain {

    List<Block> chain;

    public Blockchain(long numberOfBlocks) {
        this.chain = new ArrayList<>();
        appendChain(numberOfBlocks);
    }

    private void appendChain(long nrOfBlocks) {
        for (int i = 0; i < nrOfBlocks; i++) {
            appendChain();
        }
    }

    private void appendChain() {
        this.chain.add(generateBlock());
    }

    public Block generateBlock() {
        return new Block();
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Block block : this.chain) {
            stringBuilder.append(block);
        }
        return stringBuilder.toString();
    }

    //TODO Method to validate the blockchain

    //TODO update Javadoc

}


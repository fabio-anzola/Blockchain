package blockchain;

import java.util.Date;

/**
 * Creates a Block
 *
 * @author fabioanzola richardkrikler tobiasrigler
 */
public class Block {
    /**
     * Block before current Block
     */
    private static Block previousBlock;

    /**
     * Hash Value of the previous Block
     */
    private final String previousHash;

    /**
     * id of current Block
     */
    private final long id;

    /**
     * Time of the creation
     */
    private final long timestamp;

    /**
     * Hash value of the Block
     */
    private final String hash;

    /**
     * Constructor for creating a Block
     */
    public Block() {
        if (previousBlock == null) {
            previousHash = "0";
            id = 1;
        } else {
            previousHash = previousBlock.getHash();
            this.id = this.getPreviousBlock().getId() + 1;
        }

        this.timestamp = new Date().getTime();
        if (previousBlock == null) {
            this.hash = StringUtil.applySha256((this.timestamp + id) + previousHash);
        } else {
            this.hash = StringUtil.applySha256((this.timestamp + id) + previousBlock.getHash());
        }
        previousBlock = this;
    }

    /**
     * get the Hash value
     *
     * @return Hash value
     */
    public String getHash() {
        return hash;
    }

    /**
     * get the previous Block
     *
     * @return previous Block
     */
    public Block getPreviousBlock() {
        return previousBlock;
    }

    /**
     * get the current Id
     *
     * @return id
     */
    public long getId() {
        return this.id;
    }

    /**
     * Create formatted output String
     * @return output String
     */
    @Override
    public String toString() {
        String s = "Block:\n";
        s += "Id: " + id + "\n";
        s += "Timestamp: " + this.timestamp + "\n";
        s += "Hash of the previous block: \n" + previousHash + "\n";
        s += "Hash of the block: \n" + this.hash + "\n\n";

        return s;
    }

}
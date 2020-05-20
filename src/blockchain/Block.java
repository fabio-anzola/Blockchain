package blockchain;

import java.util.Date;
import java.util.Random;

/**
 * Creates a Block
 *
 * @author fabioanzola richardkrikler tobiasrigler
 */
public class Block {

    /**
     * Link to the previous Block
     */
    private final Block previousBlock;

    /**
     * Hash of the previous Block
     */
    private final String previousHash;

    /**
     * ID of current Block
     */
    private final long id;

    /**
     * Time of the creation
     */
    private final long timestamp;

    /**
     * Hash value of the Block
     */
    private String hash;

    /**
     * Constructor for creating a Block
     */
    public Block(Block previousBlock) {
        this.timestamp = new Date().getTime();
        this.previousBlock = previousBlock;
        if (this.previousBlock == null) {
            this.previousHash = "0";
            id = 1;
            hash = StringUtil.applySha256(Long.toString(this.timestamp + id));
        } else {
            this.previousHash = this.previousBlock.getHash();
            id = this.previousBlock.getId() + 1;
            hash = StringUtil.applySha256((this.timestamp + id) + this.previousBlock.getHash());
        }
    }


    /**
     * Constructor for creating a Block with specified Number of zeros
     */
    public Block(Block previousBlock, int requiredZeros) {
        this.timestamp = new Date().getTime();
        this.previousBlock = previousBlock;
        if (this.previousBlock == null) {
            this.previousHash = "0";
            id = 1;
        } else {
            this.previousHash = this.previousBlock.getHash();
            id = this.previousBlock.getId() + 1;
        }
        calculateHash(requiredZeros);
    }

    /**
     * Calculates the Hash for the current Block
     *
     * @param requiredZeros Nr. of zeros for the hash
     */
    private void calculateHash(int requiredZeros) {
        long magicNumber = new Random().nextLong();
        hash = StringUtil.applySha256(Long.toString(this.timestamp + id + magicNumber));
        while (!(hash.substring(0, requiredZeros).replaceAll("0", "").isBlank())) {
            magicNumber = new Random().nextLong();
            hash = StringUtil.applySha256(Long.toString(this.timestamp + id + magicNumber));
        }
    }

    /**
     * Get the Hash value
     *
     * @return Hash value
     */
    public String getHash() {
        return hash;
    }

    public String getPreviousHash() {
        return this.previousHash;
    }

    /**
     * Get the current Id
     *
     * @return id
     */
    public long getId() {
        return id;
    }

    /**
     * Create formatted output String
     *
     * @return output String
     */
    @Override
    public String toString() {
        //TODO add information on for how long the Block generated
        //TODO output magic number
        String s = "Block:\n";
        s += "Id: " + id + "\n";
        s += "Timestamp: " + this.timestamp + "\n";
        s += "Hash of the previous block: \n" + this.previousHash + "\n";
        s += "Hash of the block: \n" + hash + "\n\n";

        return s;
    }

}
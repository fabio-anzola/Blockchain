package blockchain;

import java.util.Date;

/**
 * Creates a Block
 *
 * @author fabioanzola richardkrikler tobiasrigler
 */
public class Block {
    /**
     * Hash Value of the previous Block
     */
    private static String previousHash;

    /**
     * id of current Block
     */
    private static long id;

    /**
     * Time of the creation
     */
    private final long timestamp;

    /**
     * Hash value of the Block
     */
    private static String hash;

    /**
     * Constructor for creating a Block
     */
    public Block() {
        if (previousHash == null) {
            previousHash = "0";
            id = 1;
        } else {
            previousHash = hash;
            id++;
        }
        this.timestamp = new Date().getTime();
        hash = StringUtil.applySha256((this.timestamp + id) + previousHash);
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
     * get the previous hash value
     *
     * @return previous hash value
     */
    public String getPreviousHash() {
        return previousHash;
    }

    /**
     * get the current Id
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
        String s = "Block:\n";
        s += "Id: " + id + "\n";
        s += "Timestamp: " + this.timestamp + "\n";
        s += "Hash of the previous block: \n" + previousHash + "\n";
        s += "Hash of the block: \n" + hash + "\n";

        return s;
    }

}
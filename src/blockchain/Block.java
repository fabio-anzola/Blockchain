package blockchain;

import java.util.Date;

/**
 * Creates a Block
 *
 * @author fabioanzola richardkrikler tobiasrigler
 */
public class Block {

    private Block previousBlock;
    private String previousHash;

    /**
     * id of current Block
     */
    private long id;

    /**
     * Time of the creation
     */
    private long timestamp;

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
     * get the Hash value
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
        s += "Hash of the previous block: \n" + this.previousHash + "\n";
        s += "Hash of the block: \n" + hash + "\n\n";

        return s;
    }

}
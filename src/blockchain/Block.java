package blockchain;

import java.io.Serializable;
import java.util.Date;
import java.util.Random;

/**
 * Creates a Block
 *
 * @author fabioanzola richardkrikler tobiasrigler
 */
public class Block implements Serializable {

    //TODO field for messages

    //TODO Javadoc

    /**
     * The difficulty state
     */
    private String difficultyState;

    /**
     * Time for creation
     */
    private final long generatingTime;

    /**
     * The magic number for the hash
     */
    private long magicNumber;

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
     * The miner ID, which created the Block
     */
    private long minerID;

    /**
     * Constructor for creating a Block with specified Number of zeros
     */
    public Block(Block previousBlock, int requiredZeros) {
        long startTime = System.currentTimeMillis();
        this.timestamp = new Date().getTime();
        if (previousBlock == null) {
            this.previousHash = "0";
            id = 1;
        } else {
            this.previousHash = previousBlock.getHash();
            id = previousBlock.getId() + 1;
        }
        calculateHash(requiredZeros);
        this.generatingTime = (System.currentTimeMillis() - startTime) / 1000L;
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
        this.magicNumber = magicNumber;
    }

    /**
     * Get the Hash value
     *
     * @return Hash value
     */
    public String getHash() {
        return hash;
    }

    /**
     * Get the previous Hash value
     *
     * @return hash value
     */
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
        String s = "Block:\n";
        s += "Created by miner # " + this.minerID + "\n";
        s += "Id: " + id + "\n";
        s += "Timestamp: " + this.timestamp + "\n";
        s += "Magic number: " + this.magicNumber + "\n";
        s += "Hash of the previous block: \n" + this.previousHash + "\n";
        s += "Hash of the block: \n" + hash + "\n";
        s += "Block was generating for " + this.generatingTime + " seconds" + "\n";
        s += this.difficultyState + "\n";
        return s;
    }

    /**
     * Get the time it took to generate the Block
     *
     * @return time in seconds
     */
    public long getGeneratingTime() {
        return generatingTime;
    }

    /**
     * Set the miner ID
     *
     * @param minerID for the miner
     */
    public void setMinerID(long minerID) {
        this.minerID = minerID;
    }

    /**
     * Set the difficulty state of the Block
     *
     * @param difficultyState of the Block
     */
    public void setDifficultyState(String difficultyState) {
        this.difficultyState = difficultyState;
    }

}
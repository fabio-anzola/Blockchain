package blockchain;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The Blockchain class
 *
 * @author fabioanzola richardkrikler tobiasrigler
 */
public class Blockchain implements Serializable {

    //TODO field for message cache

    //TODO continually check for messages

    //TODO once Block is confirmed add all messages to Block and clear cache

    //TODO Javadoc

    /**
     * The VID for Serialization
     */
    private static final long serialVersionUID = 1L;

    /**
     * The number of zeros at the beginning of the hash
     */
    int requiredZeros;

    /**
     * Stores all of the Blocks from the Blockchain
     */
    volatile List<Block> chain;

    /**
     * Creates a Blockchain with a specified number of Blocks and specified number of zeros
     *
     * @throws Exception If an error occurs
     */
    public Blockchain() throws Exception {
        this.chain = new ArrayList<>();
        //if (Files.exists(Paths.get("resources/blockchain.file"))) {
        //    this.chain = ((Blockchain) Objects.requireNonNull(deserialize("resources/blockchain.file"))).chain;
        //}
        this.requiredZeros = 0;
    }

    /**
     * Shows information about all the Blocks
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

    /**
     * Serializes file to Object
     *
     * @param obj      Object to serialize
     * @param fileName Path to ObjectFile
     * @throws Exception If an error occurs
     */
    public static void serialize(Object obj, String fileName) throws Exception {
        FileOutputStream fos = new FileOutputStream(fileName);
        BufferedOutputStream bos = new BufferedOutputStream(fos);
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(obj);
        oos.close();
    }

    /**
     * @param fileName Path to ObjectFile
     * @return The deserialized Object or null
     * @throws Exception If an error occurs
     */
    public static Object deserialize(String fileName) throws Exception {
        FileInputStream fis = new FileInputStream(fileName);
        BufferedInputStream bis = new BufferedInputStream(fis);
        ObjectInputStream ois = new ObjectInputStream(bis);
        Object obj = ois.readObject();
        ois.close();
        if (((Blockchain) obj).validate()) {
            return obj;
        }
        return null;
    }

    /**
     * Appends a Block to a chain
     *
     * @param block given Block
     */
    private void appendBlock(Block block) {
        this.chain.add(block);
    }

    /**
     * Append to a chain from a miner
     *
     * @param block from a miner
     */
    public synchronized void appendFromMiner(Block block) {
        if (block.getId() != this.getLastID() && block.getId() > this.getLastID()) {
            Blockchain temp = this;
            if (temp.validate()) {
                this.appendBlock(block);
                regulateDifficulty();
                System.out.println(block);
            }
        }
    }

    /**
     * Get the last Block of the chain
     *
     * @return last Block
     */
    public Block getLastBlock() {
        return this.chain.get(this.chain.size() - 1);
    }

    /**
     * Get the ID of the last Block
     *
     * @return ID
     */
    public long getLastID() {
        if (this.chain.size() == 0) {
            return -1L;
        }
        return this.chain.get(this.chain.size() - 1).getId();
    }

    /**
     * Get the amount of required zeros
     *
     * @return amount of zeros
     */
    public int getRequiredZeros() {
        return requiredZeros;
    }

    /**
     * Regulate the time of computing the next Block
     */
    private void regulateDifficulty() {
        if (getLastBlock().getGeneratingTime() > 60) {
            if (this.requiredZeros > 0) {
                this.requiredZeros--;
                getLastBlock().setDifficultyState("N was decreased by 1");
            }
        } else if (getLastBlock().getGeneratingTime() <= 5) {
            this.requiredZeros++;
            getLastBlock().setDifficultyState("N was increased to " + this.requiredZeros);
        } else {
            getLastBlock().setDifficultyState("N stays the same");
        }
    }

    /**
     * Get the size of the chain
     *
     * @return size of chain
     */
    public int getSize() {
        return this.chain.size();
    }
}

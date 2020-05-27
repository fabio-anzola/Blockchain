package blockchain;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * The Blockchain class
 *
 * @author fabioanzola richardkrikler tobiasrigler
 */
public class Blockchain implements Serializable {

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
    List<Block> chain;

    /**
     * Creates a Blockchain with a specified number of Blocks and specified number of zeros
     *
     * @param numberOfBlocks The number of Blocks which should be added to the Blockchain
     * @throws Exception If an error occurs
     */
    public Blockchain(long numberOfBlocks, int requiredZeros) throws Exception {
        this.chain = new ArrayList<>();
        //if (Files.exists(Paths.get("resources/blockchain.file"))) {
        //    this.chain = ((Blockchain) Objects.requireNonNull(deserialize("resources/blockchain.file"))).chain;
        //}
        this.requiredZeros = requiredZeros;
        appendChain(numberOfBlocks);
    }

    /**
     * Appends multiple Blocks to the Blockchain
     *
     * @param nrOfBlocks The number of Blocks which should be added
     */
    public void appendChain(long nrOfBlocks) throws Exception {
        int size = this.chain.size();
        for (int i = size; i < size + nrOfBlocks; i++) {
            if (i == 0) {
                this.chain.add(new Block(null, this.requiredZeros));
            } else {
                this.chain.add(new Block(this.chain.get(i - 1), this.requiredZeros));
            }
            //serialize(this, "resources/blockchain.file");
        }
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

    private void appendBlock(Block block) {
        this.chain.add(block);
    }

    public void appendFromMiner(Block block) {
        Blockchain temp = this;
        temp.appendBlock(block);
        if (temp.validate()) {
            this.appendBlock(block);
        }
    }

    public Block getLastBlock() {
        return this.chain.get(this.chain.size()-1);
    }

    public int getRequiredZeros() {
        return requiredZeros;
    }

    //TODO regulate MagicNumber based on creation speed

    //TODO check the validity of an incoming block

}

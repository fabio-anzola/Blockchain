package blockchain;

public class Block {

    Block previousBlock;

    long id;

    long timestamp;

    String hash;

    //TODO create constructor
    //  should include:
    //      If block is first previousblock = null, but previous hash should be 0
    //      Set continuous id every time a block is created
    //      Set timestamp --> long timeStamp = new Date().getTime(); // 1539795682545 represents 17.10.2018, 20:01:22.545
    //      Create hash of block with sha256

    //TODO update Javadoc

    //TODO toString Method

}

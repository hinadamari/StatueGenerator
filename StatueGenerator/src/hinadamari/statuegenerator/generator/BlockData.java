package hinadamari.statuegenerator.generator;

/**
 * BlockData (BlockData.java)
 * @author syam(syamn)
 */
public class BlockData {
    private int id;
    private byte data;

    public BlockData(final int id, final byte data){
        this.id = id;
        this.data = data;
    }

    public int getID(){
        return this.id;
    }
    public byte getData(){
        return this.data;
    }
}

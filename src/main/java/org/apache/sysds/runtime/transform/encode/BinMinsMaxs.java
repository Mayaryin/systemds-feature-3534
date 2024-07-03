package org.apache.sysds.runtime.transform.encode;

public class BinMinsMaxs {

    private final double[] _binMins;

    private final double[] _binMaxs;

    private final long _size;

    public static BinMinsMaxs create(final double[] binMins, final double[] binMaxs) {
        if (binMins.length != binMaxs.length) {
            throw new IllegalArgumentException("");
        }
        long size = computeSize(binMins, binMaxs);
        return new BinMinsMaxs(binMins, binMaxs, size);
    }

    private BinMinsMaxs(final double[] binMins, final double[] binMaxs, final long size) {
        this._binMins = binMins;
        this._binMaxs = binMaxs;
        this._size = size;
    }

    public double[] get_binMaxs() {
        return this._binMaxs;
    }

    public double[] get_binMins() {
        return this._binMins;
    }

    public long getSize(){
        return this._size;
    }

    private static long computeSize(final double[] binMins, final double[] binMaxs){
        // object header: 16 bytes, reference to _binMins, _binMaxs: each 4 bytes,
        // array header: each 12 bytes, storage of array length: each 4 bytes
        // the constant object overhead is a minimum estimation as exact storage behaviour cannot be predicted
        int size = 56;
        if (binMins != null && binMaxs != null) {
            size += 2 *  8 * binMins.length; // size of double: 8 bytes
        }
        return size;
    }

}

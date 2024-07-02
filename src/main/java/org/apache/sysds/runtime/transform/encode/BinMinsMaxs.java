package org.apache.sysds.runtime.transform.encode;

public class BinMinsMaxs {

    private final double[] _binMins;
    private final double[] _binMaxs;

    public BinMinsMaxs(double[] binMins, double[] binMaxs) {
        this._binMins = binMins;
        this._binMaxs = binMaxs;
    }

    public double[] get_binMaxs() {
        return _binMaxs;
    }

    public double[] get_binMins() {
        return _binMins;
    }

    public long getSize(){
        // object header: 16 bytes, reference to _binMins, _binMaxs: each 4 bytes,
        // array header: each 12 bytes, storage of array length: each 4 bytes
        // the constant object overhead is a minimum estimation as exact storage behaviour cannot be predicted
        int size = 56;
        if (_binMins != null && _binMaxs != null) {
            size += 2 *  8 * _binMins.length; // size of double: 8 bytes
        }
        return size;
    }

}

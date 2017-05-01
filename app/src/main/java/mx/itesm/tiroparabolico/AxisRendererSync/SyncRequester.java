package mx.itesm.tiroparabolico.AxisRendererSync;

/**
 * Created by javier on 4/30/17.
 */

public interface SyncRequester {
    int TICKS_MIN = 1;
    int TICKS_MAX = 2;

    void requestSync(int fieldCode, int val);
    void registerSync(SyncRequester requester);
    boolean deregisterSync(SyncRequester requester);
}

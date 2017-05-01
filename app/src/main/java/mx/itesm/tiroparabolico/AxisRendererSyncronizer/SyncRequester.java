package mx.itesm.tiroparabolico.AxisRendererSyncronizer;

/**
 * Created by javier on 4/30/17.
 */

public interface SyncRequester {
    void requestSync(int fieldCode, int val);
    void registerSync(SyncRequester requester);
    boolean deregisterSync(SyncRequester requester);
}

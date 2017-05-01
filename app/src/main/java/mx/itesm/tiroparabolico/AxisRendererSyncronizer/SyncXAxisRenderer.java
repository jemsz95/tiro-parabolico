package mx.itesm.tiroparabolico.AxisRendererSyncronizer;

import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.renderer.XAxisRenderer;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.util.ArrayList;
import java.util.List;

public class SyncXAxisRenderer extends XAxisRenderer implements SyncRequester {
    private List<SyncRequester> syncPartners = new ArrayList<>();

    public SyncXAxisRenderer(ViewPortHandler viewPortHandler, XAxis xAxis, Transformer transformer) {
        super(viewPortHandler, xAxis, transformer);
    }

    @Override
    public void requestSync(int fieldCode, int val) {
    }

    @Override
    public void registerSync(SyncRequester requester) {
        syncPartners.add(requester);
    }

    @Override
    public boolean deregisterSync(SyncRequester requester) {
        return syncPartners.remove(requester);
    }
}

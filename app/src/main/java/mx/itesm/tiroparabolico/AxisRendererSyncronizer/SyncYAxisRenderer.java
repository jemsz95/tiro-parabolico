package mx.itesm.tiroparabolico.AxisRendererSyncronizer;

import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.renderer.YAxisRenderer;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.util.List;

public class SyncYAxisRenderer extends YAxisRenderer implements SyncRequester {
    private List<SyncRequester> syncPartners;

    public SyncYAxisRenderer(ViewPortHandler viewPortHandler, YAxis yAxis, Transformer transformer) {
        super(viewPortHandler, yAxis, transformer);
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

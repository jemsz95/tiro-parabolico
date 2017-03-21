package mx.itesm.tiroparabolico;


import android.content.Context;
import android.support.v4.app.ListFragment;


public class HistoryListFragment extends ListFragment {
    private static final String DEBUG_TAG = "HistoryListFragment";

    private OnHistoryListItemClickListener listener;

    public HistoryListFragment() { }

    @Override
    public void onAttach(Context context) {
        if(context instanceof OnHistoryListItemClickListener) {
            listener = (OnHistoryListItemClickListener) context;
        } else {
            throw new RuntimeException("Parent activity of HistoryListFragment must implement OnHistoryListItemClickListener");
        }

        super.onAttach(context);
    }

    public interface OnHistoryListItemClickListener {
        void onHistoryListItemClick();
    }
}

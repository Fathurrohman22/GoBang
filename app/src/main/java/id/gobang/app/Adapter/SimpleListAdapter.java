package id.gobang.app.Adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

import gr.escsoft.michaelprimez.searchablespinner.interfaces.ISpinnerSelectedView;
import id.gobang.app.R;

public class SimpleListAdapter extends ArrayAdapter<String> implements Filterable, ISpinnerSelectedView {

    private Context mContext;
    private ArrayList<String> mBackupStrings;
    private ArrayList<String> mStrings;
    private StringFilter mStringFilter = new StringFilter();
    private String keterangan = "Pilih item";

    public SimpleListAdapter(Context context, ArrayList<String> strings) {
        super(context, R.layout.view_list_item);
        mContext = context;
        mStrings = strings;
        mBackupStrings = strings;
    }

    public SimpleListAdapter(Context context, ArrayList<String> strings, String Keterangan) {
        super(context, R.layout.view_list_item);
        mContext = context;
        mStrings = strings;
        mBackupStrings = strings;
        this.keterangan = Keterangan;
    }

    @Override
    public int getCount() {
        return mStrings == null ? 0 : mStrings.size() + 1;
    }

    @Override
    public String getItem(int position) {
        if (mStrings != null && position > 0) {
            return mStrings.get(position - 1);
        } else {
            return null;
        }
    }

    @Override
    public long getItemId(int position) {
        if (mStrings == null && position > 0) {
            return mStrings.get(position).hashCode();
        } else {
            return -1;
        }
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = null;
        if (position == 0) {
            view = getNoSelectionView();
        } else {
            view = View.inflate(mContext, R.layout.view_list_item, null);
            TextView dispalyName = view.findViewById(R.id.TxtVw_DisplayName);
            dispalyName.setText(mStrings.get(position - 1));
        }
        return view;
    }

    @Override
    public View getNoSelectionView() {
        View view = View.inflate(mContext, R.layout.view_list_no_selection_item, null);
        TextView TxtVw_NoSelection = view.findViewById(R.id.TxtVw_NoSelection);
        TxtVw_NoSelection.setText(keterangan);
        return view;
    }

    @Override
    public View getSelectedView(int position) {
        View view = null;
        if (position == 0) {
            view = getNoSelectionView();
        } else {
            view = View.inflate(mContext, R.layout.view_list_item, null);
            TextView dispalyName = (TextView) view.findViewById(R.id.TxtVw_DisplayName);
            dispalyName.setText(mStrings.get(position - 1));
        }
        return view;
    }

    @Override
    public Filter getFilter() {
        return mStringFilter;
    }

    private class StringFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            final FilterResults filterResults = new FilterResults();
            if (TextUtils.isEmpty(constraint)) {
                filterResults.count = mBackupStrings.size();
                filterResults.values = mBackupStrings;
                return filterResults;
            }
            final ArrayList<String> filterStrings = new ArrayList<>();
            for (String text : mBackupStrings) {
                if (text.toLowerCase().contains(constraint)) {
                    filterStrings.add(text);
                }
            }
            filterResults.count = filterStrings.size();
            filterResults.values = filterStrings;
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            mStrings = (ArrayList) results.values;
            notifyDataSetChanged();
        }
    }

    private class ItemView {
        public TextView mTextView;
    }

    public enum ItemViewType {
        ITEM, NO_SELECTION_ITEM;
    }
}

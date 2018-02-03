package org.androidluckyguys.searchviewExample.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.androidluckyguys.searchviewExample.R;
import org.androidluckyguys.searchviewExample.adapter.models.ExampleModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with Android Studio
 * User: Xaver
 * Date: 24/05/15
 */
public class ExampleAdapter extends RecyclerView.Adapter<ExampleAdapter.ExampleViewHolder> {

    private Context context;
    private final LayoutInflater mInflater;
    private final List<ExampleModel> mModels;

    public ExampleAdapter(Context context, List<ExampleModel> models) {
        this.context = context;
        mInflater = LayoutInflater.from(context);
        mModels = new ArrayList<>(models);
    }

    @Override
    public ExampleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        final View itemView = mInflater.inflate(R.layout.item_example, parent, false);
        return new ExampleViewHolder(itemView);
    }

    public class ExampleViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvText;

        public ExampleViewHolder(View itemView) {
            super(itemView);

            tvText = (TextView) itemView.findViewById(R.id.tvText);
        }

        public void bind(final ExampleModel model) {

            tvText.setText(model.getText());
            tvText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    /* get or pass model data from here */
                    Toast.makeText(context,model.getText(),Toast.LENGTH_SHORT).show();
                }
            });

        }
    }



    @Override
    public void onBindViewHolder(ExampleViewHolder holder, int position) {
        final ExampleModel model = mModels.get(position);
        holder.bind(model);
    }

    @Override
    public int getItemCount() {
        return mModels.size();
    }

    public void animateTo(List<ExampleModel> models) {
        applyAndAnimateRemovals(models);
        applyAndAnimateAdditions(models);
        applyAndAnimateMovedItems(models);
    }

    private void applyAndAnimateRemovals(List<ExampleModel> newModels) {
        for (int i = mModels.size() - 1; i >= 0; i--) {
            final ExampleModel model = mModels.get(i);
            if (!newModels.contains(model)) {
                removeItem(i);
            }
        }
    }

    private void applyAndAnimateAdditions(List<ExampleModel> newModels) {
        for (int i = 0, count = newModels.size(); i < count; i++) {
            final ExampleModel model = newModels.get(i);
            if (!mModels.contains(model)) {
                addItem(i, model);
            }
        }
    }

    private void applyAndAnimateMovedItems(List<ExampleModel> newModels) {
        for (int toPosition = newModels.size() - 1; toPosition >= 0; toPosition--) {
            final ExampleModel model = newModels.get(toPosition);
            final int fromPosition = mModels.indexOf(model);
            if (fromPosition >= 0 && fromPosition != toPosition) {
                moveItem(fromPosition, toPosition);
            }
        }
    }

    public ExampleModel removeItem(int position) {
        final ExampleModel model = mModels.remove(position);
        notifyItemRemoved(position);
        return model;
    }

    public void addItem(int position, ExampleModel model) {
        mModels.add(position, model);
        notifyItemInserted(position);
    }

    public void moveItem(int fromPosition, int toPosition) {
        final ExampleModel model = mModels.remove(fromPosition);
        mModels.add(toPosition, model);
        notifyItemMoved(fromPosition, toPosition);
    }
}

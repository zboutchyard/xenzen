package net.androidbootcamp.xenzen;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class FirestoreAdapter extends FirestoreRecyclerAdapter <JournalModel, FirestoreAdapter.JournalViewHolder>{
    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */

    private OnListItemClick onListItemClick;

    public FirestoreAdapter(@NonNull FirestoreRecyclerOptions<JournalModel> options, OnListItemClick onListItemClick) {
        super(options);
        this.onListItemClick= onListItemClick;
    }

    @Override
    protected void onBindViewHolder(@NonNull JournalViewHolder journalViewHolder, int i, @NonNull JournalModel model) {
        journalViewHolder.list_title.setText(model.getTitle());
    }

    @NonNull
    @Override
    public JournalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_single, parent,false);
        return new JournalViewHolder(view);
    }

    public void deleteItem(int position){
        getSnapshots().getSnapshot(position).getReference().delete();
    }

    public class JournalViewHolder extends  RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView list_title;

        public JournalViewHolder (@NonNull View itemView) {
            super(itemView);

            list_title = itemView.findViewById(R.id.list_title);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onListItemClick.onItemClick(getItem(getAdapterPosition()), getAdapterPosition());
        }
    }
    public interface OnListItemClick {
        void onItemClick(JournalModel snapshot, int position);
    }
}

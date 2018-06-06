package kenabis.myaudio.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import kenabis.myaudio.R;
import kenabis.myaudio.record.Record;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder>
{
    private List<Record> data;

    public RecyclerAdapter(List<Record> records)
    {
        this.data = records;
    }

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder
    {
        public CardView card;
        public TextView title;
        public TextView date;

        public RecyclerViewHolder(View item)
        {
            super(item);

            //  get views of item
            this.card = (CardView) item.findViewById(R.id.cardView);
            this.title = (TextView) item.findViewById(R.id.title);
            this.date = (TextView) item.findViewById(R.id.date);
        }
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        //  get view to recycle
        View card = LayoutInflater.from(parent.getContext()).inflate(R.layout.gallery_card_item, parent, false);

        RecyclerViewHolder holder = new RecyclerViewHolder(card);
        return(holder);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int index)
    {
        //  define content
        holder.title.setText(this.data.get(index).getTitle());
        holder.date.setText(this.data.get(index).getDate());
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView)
    {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount()
    {
        return(this.data.size());
    }
}
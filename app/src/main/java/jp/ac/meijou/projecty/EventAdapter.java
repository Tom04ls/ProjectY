package jp.ac.meijou.projecty;
//イベントを
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import jp.ac.meijou.projecty.EventAdapter.viewbinding.EventItemBinding; // 自動生成されたバインディングクラス

import java.util.List;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.ViewHolder> {

    private List<Event> eventList;

    // ViewHolderクラスもViewBindingを使用
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final EventItemBinding binding;

        public ViewHolder(EventItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Event event) {
            binding.dateTextView.setText(event.getYear() + "/" + event.getMonth() + "/" + event.getDay());
            binding.eventNameTextView.setText(event.getEventName());
        }
    }

    public EventAdapter(List<Event> eventList) {
        this.eventList = eventList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        EventItemBinding binding = EventItemBinding.inflate(inflater, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Event event = eventList.get(position);
        holder.bind(event);
    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }
}

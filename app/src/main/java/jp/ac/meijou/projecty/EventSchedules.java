package jp.ac.meijou.projecty;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import jp.ac.meijou.projecty.EventSchedules.viewbinding.ActivityMainBinding; // 生成されたバインディングクラス

import java.util.ArrayList;
import java.util.List;

public class EventSchedules extends AppCompatActivity {

    private ActivityMainBinding binding; // ViewBindingのインスタンス
    private List<Event> eventList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // ViewBindingを使ってレイアウトを設定
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // RecyclerViewの設定
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // サンプルデータの生成
        eventList = new ArrayList<>();
        eventList.add(new Event(1, 2024, 8, 9, "ライブ"));
        eventList.add(new Event(2, 2024, 9, 21, "誕生祭"));

        // アダプターのセット
        binding.recyclerView.setAdapter(new SimpleEventAdapter(eventList));
    }

    // シンプルなイベントクラス
    static class Event {
        int id, year, month, day;
        String eventName;

        Event(int id, int year, int month, int day, String eventName) {
            this.id = id;
            this.year = year;
            this.month = month;
            this.day = day;
            this.eventName = eventName;
        }

        String getFormattedDate() {
            return year + "/" + month + "/" + day;
        }
    }

    // 内部アダプタークラス
    class SimpleEventAdapter extends RecyclerView.Adapter<SimpleEventAdapter.EventViewHolder> {

        private List<Event> events;

        SimpleEventAdapter(List<Event> events) {
            this.events = events;
        }

        @NonNull
        @Override
        public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            // シンプルなTextViewを表示
            TextView textView = new TextView(parent.getContext());
            textView.setLayoutParams(new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
            textView.setPadding(16, 16, 16, 16);
            return new EventViewHolder(textView);
        }

        @Override
        public void onBindViewHolder(@NonNull EventViewHolder holder, int position) {
            Event event = events.get(position);
            holder.textView.setText(event.getFormattedDate() + " - " + event.eventName);
        }

        @Override
        public int getItemCount() {
            return events.size();
        }

        // ViewHolderクラス
        class EventViewHolder extends RecyclerView.ViewHolder {
            TextView textView;

            EventViewHolder(@NonNull TextView itemView) {
                super(itemView);
                textView = itemView;
            }
        }
    }
}

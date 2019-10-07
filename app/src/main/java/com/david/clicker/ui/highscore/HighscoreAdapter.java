package com.david.clicker.ui.highscore;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.david.clicker.R;
import com.david.clicker.data.entities.Profile;

public class HighscoreAdapter extends ListAdapter<Profile, HighscoreAdapter.ProfileHolder> {

    private onItemClickListener listener;

    public HighscoreAdapter() {
        super(DIFF_CALLBACK);
    }

    private static final DiffUtil.ItemCallback<Profile> DIFF_CALLBACK = new DiffUtil.ItemCallback<Profile>() {
        @Override
        public boolean areItemsTheSame(@NonNull Profile oldItem, @NonNull Profile newItem) {
            return oldItem.getUsername().equals(newItem.getUsername());
        }

        @Override
        public boolean areContentsTheSame(@NonNull Profile oldItem, @NonNull Profile newItem) {
            return (oldItem.getUsername().equals(newItem.getUsername())
                            && oldItem.getEmail().equals(newItem.getEmail())
                            && oldItem.getPassword().equals(newItem.getPassword())
            );
        }
    };

    @NonNull
    @Override
    public ProfileHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.profile_item, parent, false);
        return new ProfileHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ProfileHolder holder, int position) {
        Profile currentProfile = getItem(position);
        holder.textViewUsername.setText(currentProfile.getUsername());
        holder.textViewScore.setText(String.valueOf(currentProfile.getScore()));
        holder.textViewEmail.setText(currentProfile.getEmail());
    }

    public Profile getProfileAt(int position) {
        return getItem(position);
    }

    class ProfileHolder extends RecyclerView.ViewHolder {

        private TextView textViewUsername;

        private TextView textViewScore;

        private TextView textViewEmail;

        public ProfileHolder(@NonNull View itemView) {
            super(itemView);
            textViewUsername = itemView.findViewById(R.id.text_view_username);
            textViewScore = itemView.findViewById(R.id.text_view_score);
            textViewEmail = itemView.findViewById(R.id.text_view_email);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        Profile profile = getItem(position);
                        listener.onItemClick(profile);
                    }
                }
            });
        }
    }

    public interface onItemClickListener {
        void onItemClick(Profile profile);
    }

    public void setOnItemClickListener(onItemClickListener listener) {
        this.listener = listener;
    }
}

package com.example.xx.beatboxactivity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.List;

/**
 * Created by XX on 2017/3/24.
 */

public class BeatBoxFragment extends Fragment {

    private BeatBox mBeatBox;

    public static BeatBoxFragment newInstance() {

        Bundle args = new Bundle();

        BeatBoxFragment fragment = new BeatBoxFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private class SoundHolder extends RecyclerView.ViewHolder
    {
        private Button mButton;
        private Sound mSound;

        public SoundHolder(LayoutInflater inflater,ViewGroup viewGroup)
        {
            super(inflater.inflate(R.layout.list_item_sound,viewGroup,false));

            mButton=(Button)itemView .findViewById(R.id.list_item_soundbox);
        }

        public void bindSound(Sound sound)
        {
            mSound=sound;
            mButton.setText(mSound.getName());
        }
    }

    private class SoundAdapter extends RecyclerView.Adapter<SoundHolder>
    {
        private List<Sound> mSounds;

        public SoundAdapter(List<Sound> sounds)
        {
            mSounds=sounds;
        }

        @Override
        public SoundHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater=LayoutInflater.from(getActivity());
            return new SoundHolder(inflater,parent);
        }

        @Override
        public void onBindViewHolder(SoundHolder holder, int position) {
            holder.bindSound(mSounds.get(position));
        }

        @Override
        public int getItemCount() {
            return mSounds.size();
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBeatBox=new BeatBox(getActivity());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_beat_box,container,false);

        RecyclerView recyclerView=(RecyclerView) view.findViewById(R.id.fragment_beatbox_recyclerview);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),3));
        recyclerView.setAdapter(new SoundAdapter(mBeatBox.getSounds()));

        return view;
    }
}

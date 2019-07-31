package com.example.githubuser;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class GitAdapter extends RecyclerView.Adapter<GitAdapter.RecycleViewHolder> {

    private final String TAB = GitAdapter.class.getSimpleName();
    private Context context;
    private List<GitUsers> mlist;
    private OnClickListener mOnItemClickListener;


    public GitAdapter(List<GitUsers> list) {

        this.mlist = list;

    }

    //
    public void SetOnItemClickListener(OnClickListener OnClickListener) {
        this.mOnItemClickListener = OnClickListener;
    }


    @Override
    public RecycleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new RecycleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecycleViewHolder holder, int position) {
        GitUsers currentitem = mlist.get(position);
        holder.mId.setText("Id: " + currentitem.getId());
        holder.mLogin.setText("Login: " + currentitem.getLogin());
        holder.mMid.setText("Node id: " + currentitem.getNodeId());

        holder.bind(mlist.get(position), mOnItemClickListener);


    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }


    static class RecycleViewHolder extends RecyclerView.ViewHolder {

        private TextView mId, mLogin, mMid;


        RecycleViewHolder(View itemView) {
            super(itemView);
            mId = itemView.findViewById(R.id.tvId);
            mLogin = itemView.findViewById(R.id.tvLogin);
            mMid = itemView.findViewById(R.id.tvmId);


        }

        void bind(final GitUsers users, final OnClickListener OnItemListener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    OnItemListener.OnItemClicked(users, getAdapterPosition());
                }
            });
        }

    }
}

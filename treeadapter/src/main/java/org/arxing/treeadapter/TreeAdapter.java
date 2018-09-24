package org.arxing.treeadapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public abstract class TreeAdapter extends RecyclerView.Adapter<TreeViewHolder> {

    public abstract TreeViewHolder onCreateTreeViewHolder(@NonNull ViewGroup parent, int viewType);


    @NonNull @Override public final TreeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return onCreateTreeViewHolder(parent, viewType);
    }

    @Override public final void onBindViewHolder(@NonNull TreeViewHolder holder, int position) {

    }

    @Override public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override public final int getItemCount() {
        return 0;
    }


}

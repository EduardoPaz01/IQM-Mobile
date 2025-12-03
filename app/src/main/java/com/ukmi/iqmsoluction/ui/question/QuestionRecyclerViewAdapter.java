package com.ukmi.iqmsoluction.ui.question;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ukmi.iqmsoluction.databinding.FragmentConQuestionBinding;
import com.ukmi.iqmsoluction.model.Question;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Question}.
 * TODO: Replace the implementation with code for your data type.
 */
public class QuestionRecyclerViewAdapter extends RecyclerView.Adapter<QuestionRecyclerViewAdapter.ViewHolder> {

    private final List<Question> mValues;

    public QuestionRecyclerViewAdapter(List<Question> items) {
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(FragmentConQuestionBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mIdView.setText(mValues.get(position).getStatement());
        holder.mContentView.setText(mValues.get(position).getAnswer());
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView mIdView;
        public final TextView mContentView;
        public Question mItem;

        public ViewHolder(FragmentConQuestionBinding binding) {
            super(binding.getRoot());
            mIdView = binding.itemNumber;
            mContentView = binding.content;
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
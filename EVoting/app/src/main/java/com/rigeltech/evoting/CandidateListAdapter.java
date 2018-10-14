package com.rigeltech.evoting;

import android.content.Context;
import android.opengl.Visibility;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.rigeltech.evoting.model.voting.VotingResultModel;

import java.util.List;

/**
 * Created by Mahesh on 2018-09-23.
 */

public class CandidateListAdapter extends RecyclerView.Adapter<CandidateListAdapter.ViewHolder> {

    private final OnClickListener listener;
    public List<VotingResultModel> candidateModelList;


    Context context;

    public CandidateListAdapter(List<VotingResultModel> candidateModelList, OnClickListener listener) {
        this.candidateModelList = candidateModelList;
        this.listener = listener;

    }

    public boolean remove(int index) {
        try {
            candidateModelList.remove(index);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public interface OnClickListener {
        void onItemClick(VotingResultModel item);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_candidate_list, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.candidate_name.setText(candidateModelList.get(position).getCandId() + ". " + candidateModelList.get(position).getName());
        holder.candidate_symbol.setText("Symbol - " + candidateModelList.get(position).getSymbol());

        if(CandidateListActivity.isResult) {
            holder.vote_count.setText("Total Vote - " + candidateModelList.get(position).getVote());
        }
        else{
            holder.vote_count.setVisibility(View.GONE);
        }

        String symbol_drawable = candidateModelList.get(position).getSymbol().replace(" ", "_").toLowerCase();


        holder.img_symbol.setImageDrawable(context.getResources().getDrawable(context.getResources().getIdentifier(symbol_drawable, "drawable", context.getPackageName())));

        holder.bind(candidateModelList.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return candidateModelList != null ? candidateModelList.size() : 0;
    }


    public VotingResultModel getItem(int position) {
        return candidateModelList.get(position);
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img_symbol;
        TextView candidate_name, candidate_symbol,vote_count;
        CardView cardViewCandidate;

        public ViewHolder(View view) {
            super(view);
            img_symbol = (ImageView) view.findViewById(R.id.img_symbol);
            candidate_name = (TextView) view.findViewById(R.id.candidate_name);
            candidate_symbol = (TextView) view.findViewById(R.id.candidate_symbol);
            cardViewCandidate = (CardView) view.findViewById(R.id.cardViewCandidate);
            vote_count = (TextView) view.findViewById(R.id.vote_count);
            context = view.getContext();
        }

        //added new method
        public void bind(final VotingResultModel item, final OnClickListener listener) {

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });
        }
    }
}

package com.jeeva.calc.adapter;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jeeva.calc.databinding.CalcNoViewBinding;
import com.jeeva.calc.listener.CalcNoListener;

import java.util.Arrays;
import java.util.List;

public class CalcNo extends RecyclerView.Adapter<CalcNo.ViewHolder> {

    List<Integer> calNos;
    CalcNoListener calcNoListener;

    public CalcNo(List<Integer> calNos,CalcNoListener calcNoListener) {
        this.calNos = calNos;
        this.calcNoListener = calcNoListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CalcNoViewBinding calcNoViewBinding = CalcNoViewBinding.inflate(
                LayoutInflater.from(parent.getContext()),parent,false
        );
        return new ViewHolder(calcNoViewBinding);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (calNos.get(position) != null) holder.calcNoViewBinding.btnNo.setText(calNos.get(position).toString());
        else if(position == 9){
            holder.calcNoViewBinding.btnNo.setText("C");
            holder.calcNoViewBinding.btnNo.setTextColor(Color.parseColor("#F05047"));
        } else if(position == calNos.size()-1){
            holder.calcNoViewBinding.btnNo.setText("X");
            holder.calcNoViewBinding.btnNo.setTextColor(Color.parseColor("#F05047"));
        }
    }

    @Override
    public int getItemCount() {
        return calNos.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        CalcNoViewBinding calcNoViewBinding;

        public ViewHolder(@NonNull CalcNoViewBinding calcNoViewBinding) {
            super(calcNoViewBinding.getRoot());
            this.calcNoViewBinding = calcNoViewBinding;
            calcNoViewBinding.btnNo.setOnClickListener(v -> calcNoListener.noBtnOnClick(getAdapterPosition()));
        }
    }

}

package com.jeeva.calc.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.jeeva.calc.databinding.CalcSimpleOpBinding;
import com.jeeva.calc.listener.CalcNoListener;

import java.util.List;

public class CalcSimpleOp extends RecyclerView.Adapter<CalcSimpleOp.ViewHolder> {

    List<Integer> calOps;
    CalcNoListener calcNoListener;

    public CalcSimpleOp(List<Integer> calOps,CalcNoListener calcNoListener) {
        this.calOps = calOps;
        this.calcNoListener = calcNoListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CalcSimpleOpBinding calcOp = CalcSimpleOpBinding.inflate(
                LayoutInflater.from(parent.getContext()),parent,false);
        return new ViewHolder(calcOp);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.calOp.imgOp.setImageResource(calOps.get(position));
    }

    @BindingAdapter("layout_height")
    public static void setLayoutHeight(View view, float height) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.height = (int) height;
        view.setLayoutParams(layoutParams);
    }

    @Override
    public int getItemCount() {
        return calOps.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        CalcSimpleOpBinding calOp;
        public ViewHolder(@NonNull CalcSimpleOpBinding calOp) {
            super(calOp.getRoot());
            this.calOp = calOp;
            calOp.imgOp.setOnClickListener(v -> {
                calcNoListener.opImgOnClick(getAdapterPosition());
            });
        }
    }
}

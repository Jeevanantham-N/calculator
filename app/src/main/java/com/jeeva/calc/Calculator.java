package com.jeeva.calc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.jeeva.calc.adapter.CalcNo;
import com.jeeva.calc.adapter.CalcSimpleOp;
import com.jeeva.calc.databinding.ActivityMainBinding;
import com.jeeva.calc.databinding.CalcNoViewBinding;
import com.jeeva.calc.listener.CalcNoListener;

import org.mariuszgromada.math.mxparser.Expression;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Calculator extends AppCompatActivity implements CalcNoListener {
    ActivityMainBinding calc ;
    List<Integer> calNos;
    CalcNo calcNo;
    CalcSimpleOp calcSimpleOp;
    List<Integer> imgOps;
    RecyclerView.LayoutManager layoutManager;
    String question;
    Double answer;
    String operation;
    Expression expression;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        calc = ActivityMainBinding.inflate(getLayoutInflater());
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        setContentView(calc.getRoot());
        setImageOps();
        setCalNos();
        question = "";
        calcNo = new CalcNo(calNos,this);
        calc.calcNo.setAdapter(calcNo);
        calc.calcNo.setLayoutManager(new GridLayoutManager(this,3));
        calc.calcNo.setHasFixedSize(true);
        calcSimpleOp = new CalcSimpleOp(imgOps,this);
        calc.calcOp1.setAdapter(calcSimpleOp);
        layoutManager = new LinearLayoutManager(this);
        calc.calcOp1.setLayoutManager(layoutManager);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        calc.calcOp1.setLayoutParams(layoutParams);
        calc.calcOp1.setHasFixedSize(true);
    }

    private void setCalNos() {
        calNos = Arrays.asList(
                1,2,3,4,5,6,7,8,9,null,0,null
        );
    }

    private void setImageOps() {
        imgOps = Arrays.asList(
                R.drawable.ic_plus_svgrepo_com,R.drawable.ic_substract_svgrepo_com,R.drawable.ic_multiply_svgrepo_com,R.drawable.ic_division_svgrepo_com
        );
    }

    @Override
    public void noBtnOnClick(int position) {
        if (position == 9){
            clearScreen();
            return;
        }
        if(position == calNos.size()-1 && question.length() != 0){
            question = calc.question.getText().toString().substring(0,question.length()-1);
            calc.question.setText(question);
            disableBtnOps(true);
        }
        else if (calNos.get(position) != null) {
            question = calc.question.getText().toString().concat(calNos.get(position).toString());
            calc.question.setText(question);
            disableBtnOps(true);
        }
        if (question.length() != 0) {
            evaluateExpression();
        } else {
            calc.answer.setText("");
        }
    }

    private void clearScreen() {
        question = "";
        calc.question.setText(question);
        calc.answer.setText("");
    }

    @SuppressLint("SetTextI18n")
    private void evaluateExpression() {
        answer = new Expression(question).calculate();
        if (!answer.isNaN()){
            calc.answer.setText(answer.toString());
        }
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void opImgOnClick(int position) {
        switch (imgOps.get(position)){
            case R.drawable.ic_plus_svgrepo_com:
                operation = "+";
                break;
            case R.drawable.ic_substract_svgrepo_com:
                operation = "-";
                break;
            case R.drawable.ic_multiply_svgrepo_com:
                operation = "*";
                break;
            case R.drawable.ic_division_svgrepo_com:
                operation = "/";
                break;
        }
        disableBtnOps(false);
        question = question.concat(operation);
        calc.question.setText(question);
    }

    private void disableBtnOps(boolean val) {
        for (int position = 0; position < imgOps.size(); position++) {
            Objects.requireNonNull(calc.calcOp1.findViewHolderForAdapterPosition(position)).itemView.setEnabled(val);
        }
    }
}
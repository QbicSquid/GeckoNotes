package com.geckolabs.notes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
/*
public class QuizAdapter extends RecyclerView.Adapter<QuizAdapter.ViewHolder>{

    Context context;
    QuizList<Quiz> quizzesList;
    RecyclerView quizList;
    final View.OnClickListener onClickListener = new MyOnClickListener();

    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView rowSelect;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            rowSelect = itemView.findViewById(R.id.select);
        }
    }

    public QuizAdapter(Context context, QuizList<Quiz> quizzesList, RecyclerView quizList){
        this.context =context;
        this.quizzesList = quizzesList;
        this.quizList = quizList;
    }

    @NonNull
    @Override
    public QuizAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.select_element, parent, false);
        view.setOnClickListener(onClickListener);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull QuizAdapter.ViewHolder holder, int position) {
        Quiz quiz = quizzesList.get(position);
        holder.rowSelect.setText(""+quiz.getName());

    }

    @Override
    public int getItemCount() {
        return quizzesList.size();
    }

    private class MyOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            int itemPosition = quizList.getChildLayoutPosition(view);
            String item = quizzesList.get(itemPosition).getName();
            Toast.makeText(context, item, Toast.LENGTH_SHORT).show();
        }
    }

}
*/

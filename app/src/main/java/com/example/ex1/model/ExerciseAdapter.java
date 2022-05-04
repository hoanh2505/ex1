package com.example.ex1.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ex1.R;

import java.util.ArrayList;
import java.util.List;

public class ExerciseAdapter extends RecyclerView.Adapter<ExerciseAdapter.ExerciseViewHolder>  {

    private ExerciseItemListener listener;
    private List<Exercise> exerciseList;
    private List<Exercise> bfExerciseList;
    private Context context;
    private View lastView;

    public ExerciseAdapter(Context context) {
        this.context = context;
        exerciseList = new ArrayList<>();
    }

    public Exercise getItem(int position) { return exerciseList.get(position); }

    public void setClickListener(ExerciseItemListener listener) { this.listener = listener; };

    @NonNull
    @Override
    public ExerciseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new ExerciseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExerciseViewHolder holder, int position) {
        Exercise exercise = exerciseList.get(position);
        holder.image.setImageResource(exercise.getImg());
        holder.unit.setText(exercise.getUnit());
        holder.tvPrice.setText(exercise.getPrice());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(lastView == null) lastView = v;
                listener.onItemClick(v, holder.getAdapterPosition());
                holder.itemView.setBackgroundColor(context.getResources().getColor(R.color.teal_200));
                lastView.setBackgroundColor(context.getResources().getColor(R.color.white));
                lastView = v;
            }
        });
    }

    @Override
    public int getItemCount() {
        if (exerciseList != null) return exerciseList.size();
        return 0;
    }

    public void add (Exercise exercise) {
        if (bfExerciseList != null) {
            bfExerciseList.add(exercise);
            exerciseList.clear();
            for (Exercise i: bfExerciseList) {
                exerciseList.add(i);
            }
        }

        if (bfExerciseList == null) {
            exerciseList.add(exercise);
            bfExerciseList = new ArrayList<>();
            for (Exercise i: exerciseList) {
                bfExerciseList.add(i);
            }
        }
//        exerciseList.add(exercise);
        notifyDataSetChanged();
    }

    public void update(int position, Exercise exercise) {
        if (bfExerciseList != null) {
            for (int i = 0; i < bfExerciseList.size(); i++) {
                if (exerciseList.get(position).equals(bfExerciseList.get(i))){
                    bfExerciseList.set(i,exercise);
                    break;
                }
            }
        }
        exerciseList.set(position, exercise);
        notifyDataSetChanged();
    }

    public void search(String name) {
        exerciseList.clear();
        for (Exercise i: bfExerciseList) {
            if (i.getUnit().toLowerCase().contains(name.toLowerCase())) {
                exerciseList.add(i);
            }
            if (exerciseList.isEmpty()) {
                exerciseList = bfExerciseList;
                Toast.makeText(context.getApplicationContext(), "Khong co du lieu", Toast.LENGTH_SHORT).show();

            }
            notifyDataSetChanged();
        }
    }

    public void delete(int p) {
        if(exerciseList.size() > p)
        {
            exerciseList.remove(p);
            notifyItemRemoved(p);
        }
    }



    public class ExerciseViewHolder extends RecyclerView.ViewHolder  {

        private ImageView image;
        private TextView unit, tvPrice;
        public ExerciseViewHolder(@NonNull View view) {
            super(view);
            image = view.findViewById(R.id.img);
            unit = view.findViewById(R.id.unit);
            tvPrice = view.findViewById(R.id.tv_price);
        }

    }

    public interface ExerciseItemListener {
        public void onItemClick(View view, int position);
    }
}

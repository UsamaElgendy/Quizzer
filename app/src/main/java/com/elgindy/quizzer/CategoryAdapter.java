package com.elgindy.quizzer;


import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.elgindy.quizzer.model.CategoryModel;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    private List<CategoryModel> categoryModels;

    public CategoryAdapter(List<CategoryModel> categoryModels) {
        this.categoryModels = categoryModels;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setData(categoryModels.get(position).getUrl(), categoryModels.get(position).getName(), categoryModels.get(position).getSets());
    }

    @Override
    public int getItemCount() {
        return categoryModels.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private CircleImageView imgView;
        private TextView title;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgView = itemView.findViewById(R.id.image_view);
            title = itemView.findViewById(R.id.title);
        }

        private void setData(String url, final String title, final int sets) {
            Glide.with(imgView.getContext()).load(url).into(imgView);
            this.title.setText(title);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(itemView.getContext(), SetsActivity.class);
                    intent.putExtra("title", title);
                    intent.putExtra("sets", sets);
                    itemView.getContext().startActivity(intent);
                }
            });
        }
    }
}

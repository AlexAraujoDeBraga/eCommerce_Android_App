package com.leklab.alex.lojabraga.ui.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.leklab.alex.lojabraga.CategoryAdapter;
import com.leklab.alex.lojabraga.R;

import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {

    private List<HomeViewModel> categoryHomeModelList;

    public HomeAdapter(List<HomeViewModel> categoryHomeModelList){
        this.categoryHomeModelList = categoryHomeModelList;
    }

    @NonNull
    @Override
    public HomeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_item, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeAdapter.ViewHolder holder, int position) {
        String icone = categoryHomeModelList.get(position).getCategoryIconeLink();
        String nome = categoryHomeModelList.get(position).getCategoryNome();
        holder.setCategoryNome(nome);
    }

    @Override
    public int getItemCount() {
        return categoryHomeModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView categoryIcone;
        private TextView categoryNome;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryIcone = itemView.findViewById(R.id.category_icon);
            categoryNome = itemView.findViewById(R.id.category_name);
        }

        private void setCategoryIcon(){

        }
        private void setCategoryNome(String nome){
            categoryNome.setText(nome);
        }

    }

}

package com.leklab.alex.lojabraga.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel  {

    private String categoryIconeLink;
    private String categoryNome;

    public HomeViewModel(String categoryIconeLink, String categoryNome) {
        this.categoryIconeLink = categoryIconeLink;
        this.categoryNome = categoryNome;
    }

    public String getCategoryIconeLink() {
        return categoryIconeLink;
    }

    public void setCategoryIconeLink(String categoryIconeLink) {
        this.categoryIconeLink = categoryIconeLink;
    }

    public String getCategoryNome() {
        return categoryNome;
    }

    public void setCategoryNome(String categoryNome) {
        this.categoryNome = categoryNome;
    }
}
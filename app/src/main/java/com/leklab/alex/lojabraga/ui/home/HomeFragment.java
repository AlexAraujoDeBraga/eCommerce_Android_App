package com.leklab.alex.lojabraga.ui.home;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.leklab.alex.lojabraga.GridProductViewAdapter;
import com.leklab.alex.lojabraga.HomePageAdapter;
import com.leklab.alex.lojabraga.HomePageModel;
import com.leklab.alex.lojabraga.HorizontalProductScrollAdapter;
import com.leklab.alex.lojabraga.HorizontalProductScrollModel;
import com.leklab.alex.lojabraga.R;
import com.leklab.alex.lojabraga.SliderAdapter;
import com.leklab.alex.lojabraga.SliderModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class HomeFragment extends Fragment {

    public HomeFragment() {
        // Required empty public constructor
    }

    private RecyclerView categoryRecyclerView;
    private HomeAdapter homeAdapter;

    //Banner Slider
    private ViewPager bannerSliderViewPager;
    private List<SliderModel> sliderModelList;
    private int currentPage = 2;
    private Timer timer;
    final private long DELAY_TIME = 3000;
    final private long PERIOD_TIME = 3000;
    //Ad
    private ImageView stripAdImage;
    private ConstraintLayout stripAdContainer;
    //Horizontal product
    private TextView horizontalLayoutTitle;
    private Button horizontalLayoutViewAllBtn;
    private RecyclerView horizontalRecyclerView;
    //product layout
    private TextView gridLayoutTitle;
    private Button gridLayoutViewAllBtn;
    private GridView gridView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        categoryRecyclerView = view.findViewById(R.id.category_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        categoryRecyclerView.setLayoutManager(layoutManager);

        List<HomeViewModel> categoryHomeModelList = new ArrayList<HomeViewModel>();
        categoryHomeModelList.add(new HomeViewModel("link","Home"));
        categoryHomeModelList.add(new HomeViewModel("link","Cozinha"));
        categoryHomeModelList.add(new HomeViewModel("link","Brinquedos"));
        categoryHomeModelList.add(new HomeViewModel("link","Banho"));
        categoryHomeModelList.add(new HomeViewModel("link","Jarros"));
        categoryHomeModelList.add(new HomeViewModel("link","Relógio"));
        categoryHomeModelList.add(new HomeViewModel("link","Travesseiro"));

        homeAdapter = new HomeAdapter(categoryHomeModelList);
        categoryRecyclerView.setAdapter(homeAdapter);
        homeAdapter.notifyDataSetChanged();

        bannerSliderViewPager = view.findViewById(R.id.banner_slider_view_pager);

        sliderModelList = new ArrayList<SliderModel>();

        sliderModelList.add(new SliderModel(R.drawable.promocao3,"#E7E7E7"));
        sliderModelList.add(new SliderModel(R.mipmap.ic_launcher,"#E7E7E7"));
        sliderModelList.add(new SliderModel(R.drawable.promocao3,"#E7E7E7"));

        sliderModelList.add(new SliderModel(R.mipmap.ic_launcher,"#E7E7E7"));
        sliderModelList.add(new SliderModel(R.mipmap.ic_logo,"#E7E7E7"));
        sliderModelList.add(new SliderModel(R.mipmap.ic_launcher_round,"#E7E7E7"));
        sliderModelList.add(new SliderModel(R.mipmap.ic_logo_round,"#E7E7E7"));
        sliderModelList.add(new SliderModel(R.drawable.promocao3,"#E7E7E7"));
        sliderModelList.add(new SliderModel(R.mipmap.ic_launcher_round,"#E7E7E7"));

        sliderModelList.add(new SliderModel(R.mipmap.ic_logo_round,"#E7E7E7"));
        sliderModelList.add(new SliderModel(R.drawable.promocao3,"#E7E7E7"));
        sliderModelList.add(new SliderModel(R.mipmap.ic_launcher_round,"#E7E7E7"));

        SliderAdapter sliderAdpater = new SliderAdapter(sliderModelList);
        bannerSliderViewPager.setAdapter(sliderAdpater);
        bannerSliderViewPager.setClipToPadding(false);
        bannerSliderViewPager.setPageMargin(20);

        bannerSliderViewPager.setCurrentItem(currentPage);

        ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                currentPage = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if (state == ViewPager.SCROLL_STATE_IDLE){
                    pageLooper();
                }
            }
        };
        bannerSliderViewPager.addOnPageChangeListener(onPageChangeListener);

        startBannerSliderShow();

        bannerSliderViewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                pageLooper();
                stopBannerSliderShow();
                if (event.getAction() == MotionEvent.ACTION_UP){
                    startBannerSliderShow();
                }
                return false;
            }
        });

        //strip ad
        stripAdImage = view.findViewById(R.id.strip_ad_image);
        stripAdContainer = view.findViewById(R.id.strip_ad_container);

        stripAdImage.setImageResource(R.drawable.extra);
        stripAdContainer.setBackgroundColor(Color.parseColor("#D63426"));
        //strip ad
        //horizontal product layout
        horizontalLayoutTitle = view.findViewById(R.id.horizontal_scroll_layout_title);
        horizontalLayoutViewAllBtn = view.findViewById(R.id.horizontal_scroll_view_all_btn);
        horizontalRecyclerView = view.findViewById(R.id.horizontal_scroll_layout_recyclerview);

        List<HorizontalProductScrollModel> horizontalProductScrollModelList = new ArrayList<>();
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.pressao2,"Panela de pressão","Traomontina","R$: 128,99"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.vasinho,"Vaso","Jarros & Enfeites","R$: 47,75"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.bolsaescolar2,"Bolsa escolar","Mais Mania","R$: 82,99"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.pressao2,"Panela de pressão","Traomontina","R$: 128,99"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.vasinho,"Vaso","Jarros & Enfeites","R$: 47,75"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.bolsaescolar2,"Bolsa escolar","Mais Mania","R$: 82,99"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.vestido,"Vestido","Lá Marca","R$: 81,75"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.casaco,"Jaqueta Jeans","Jeans Beaultiful","R$: 99,99"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.tenis,"Tênis Sport","Adidas","R$: 127,99"));

        HorizontalProductScrollAdapter horizontalProductScrollAdapter = new HorizontalProductScrollAdapter(horizontalProductScrollModelList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        horizontalRecyclerView.setLayoutManager(linearLayoutManager);

        horizontalRecyclerView.setAdapter(horizontalProductScrollAdapter);
        horizontalProductScrollAdapter.notifyDataSetChanged();

        //grid product layout

        gridLayoutTitle = view.findViewById(R.id.grid_product_layout_title);
        gridLayoutViewAllBtn = view.findViewById(R.id.grid_product_layout_viewall_btn);
        gridView = view.findViewById(R.id.grid_product_layout_gridview);

        gridView.setAdapter(new GridProductViewAdapter(horizontalProductScrollModelList));

        //multiple layout

        RecyclerView testing = view.findViewById(R.id.testing);
        LinearLayoutManager testingLayoutManager = new LinearLayoutManager(getContext());
        testingLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        testing.setLayoutManager(testingLayoutManager);

        List<HomePageModel> homePageModelList = new ArrayList<>();
        homePageModelList.add(new HomePageModel(0,sliderModelList));
        homePageModelList.add(new HomePageModel(1,R.drawable.extra, "#D63426"));
        homePageModelList.add(new HomePageModel(2,"Desconto do dia!", horizontalProductScrollModelList));
        homePageModelList.add(new HomePageModel(3,"Desconto do dia!", horizontalProductScrollModelList));
        homePageModelList.add(new HomePageModel(1,R.drawable.lblogo, "#D63426"));
        homePageModelList.add(new HomePageModel(3,"Desconto do dia!", horizontalProductScrollModelList));
        homePageModelList.add(new HomePageModel(2,"Desconto do dia!", horizontalProductScrollModelList));
        homePageModelList.add(new HomePageModel(1,R.drawable.extra, "#D63426"));
        homePageModelList.add(new HomePageModel(0,sliderModelList));

        HomePageAdapter adapter = new HomePageAdapter(homePageModelList);
        testing.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        return view;
    }

    //banner slide
    private void pageLooper(){
        if (currentPage == sliderModelList.size() -2){
            currentPage = 2;
            bannerSliderViewPager.setCurrentItem(currentPage, false);
        }
        if (currentPage == 1){
            currentPage = sliderModelList.size() - 3;
            bannerSliderViewPager.setCurrentItem(currentPage, false);
        }
    }

    private void startBannerSliderShow(){
        final Handler handler = new Handler();
        final Runnable update = new Runnable() {
            @Override
            public void run() {
                if (currentPage >= sliderModelList.size()){
                    currentPage = 1;
                }
                bannerSliderViewPager.setCurrentItem(currentPage++, true);
            }
        };
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(update);
            }
        },DELAY_TIME, PERIOD_TIME);
    }

    private void stopBannerSliderShow(){
        timer.cancel();
    }

}

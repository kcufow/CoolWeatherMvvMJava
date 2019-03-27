package com.ldw.coolweathermvvmjava.ui;

import android.content.Intent;
import android.os.Bundle;

import com.ldw.coolweathermvvmjava.R;
import com.ldw.coolweathermvvmjava.ui.area.ChooseAreaFragment;
import com.ldw.coolweathermvvmjava.ui.weather.WeatherActivity;
import com.ldw.coolweathermvvmjava.util.InjectionUtil;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProviders;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

    }

    private void initView() {
            MainViewModel viewModel = obtainViewModel(this);
            if (viewModel.isWeatherCached()){
                Intent intent = new Intent(this, WeatherActivity.class);
                startActivity(intent);
            }else {

                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.container,
                        ChooseAreaFragment.newInstance()).commit();


            }
    }

    private MainViewModel obtainViewModel(AppCompatActivity mainActivity) {
        MainViewModelFactory factory = InjectionUtil.getMainViewFactory();
        return ViewModelProviders.of(mainActivity,factory).get(MainViewModel.class);
    }
}

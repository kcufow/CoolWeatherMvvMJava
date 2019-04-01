package com.ldw.coolweathermvvmjava.ui.area;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.ldw.coolweathermvvmjava.R;
import com.ldw.coolweathermvvmjava.app.Constant;
import com.ldw.coolweathermvvmjava.databinding.AreaFragBinding;
import com.ldw.coolweathermvvmjava.ui.MainActivity;
import com.ldw.coolweathermvvmjava.ui.weather.WeatherActivity;
import com.ldw.coolweathermvvmjava.util.InjectionUtil;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;


public class ChooseAreaFragment extends Fragment {

    public static final int LEVEL_PROVINCE = 0;
    public static final int LEVEL_CITY = 1;
    public static final int LEVEL_COUNTY = 2;

    private AreaFragBinding areaFragBinding;
    private ChooseAreaViewModel viewModel;
    private ChooseAreaAdapter adapter;
    private ProgressDialog progressDialog;
    private FragmentActivity activity;

    public static ChooseAreaFragment newInstance() {
        return new ChooseAreaFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        activity = getActivity();

        View rootView = inflater.inflate(R.layout.area_frag, container, false);
        areaFragBinding = AreaFragBinding.bind(rootView);
        viewModel = obtainViewModel();
        areaFragBinding.setViewModel(viewModel);
        return rootView;
    }

    private ChooseAreaViewModel obtainViewModel() {
        return ViewModelProviders.of(this
                ,InjectionUtil.getChooseAreaModelFactory()).get(ChooseAreaViewModel.class);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ListView listView = areaFragBinding.listView;
        adapter = new ChooseAreaAdapter(view.getContext()
                ,R.layout.simple_item,viewModel.dataList);
        listView.setAdapter(adapter);
        observe();
    }

    private void observe() {
        viewModel.currentLevel.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                String title="";
                switch (integer){
                    case LEVEL_PROVINCE:
                        title = "中国";
                        areaFragBinding.backButton.setVisibility(View.GONE);
                        break;
                    case LEVEL_CITY:
                         title = viewModel.selectedProvince == null ? "名称异常" : viewModel.selectedProvince.provinceName;
                        areaFragBinding.backButton.setVisibility(View.VISIBLE);
                        break;
                    case LEVEL_COUNTY:
                        title = viewModel.selectedCity == null ? "名称异常" : viewModel.selectedCity.cityName;

                        areaFragBinding.backButton.setVisibility(View.VISIBLE);
                        break;

                }
                areaFragBinding.titleText.setText(title);
            }
        });

        viewModel.dataLoading.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean){
                    showProgressDialog();
                }else {
                    hideProgressDialog();
                }
            }
        });
        viewModel.dataSetChange.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                adapter.notifyDataSetChanged();
                areaFragBinding.listView.setSelection(0);
            }
        });

        viewModel.areaSelected.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (!aBoolean || viewModel.selectedCounty==null)return;
                if (activity instanceof MainActivity){
                    //跳转到weatherActivity中
                    Intent intent = new Intent(activity,WeatherActivity.class);
                    intent.putExtra(Constant.WEATHER_ID,viewModel.selectedCounty.weatherId);
                    startActivity(intent);
                    activity.finish();


                }else if (activity instanceof WeatherActivity){
                    //更新weatherActivity的天气信息
                    WeatherActivity weatherActivity = (WeatherActivity) activity;
                    weatherActivity.refreshWeather(Constant.KEY,viewModel.selectedCounty.weatherId
                            ,true);
                }
                viewModel.areaSelected.setValue(false);
            }
        });

        if (viewModel.dataList .isEmpty()) {
            viewModel.getProvinceData();
        }



    }

    private void showProgressDialog() {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(activity);
            progressDialog.setMessage("正在加载。。。");
            progressDialog.setCanceledOnTouchOutside(false);
        }
        progressDialog.show();


    }
    private void hideProgressDialog(){
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }
}

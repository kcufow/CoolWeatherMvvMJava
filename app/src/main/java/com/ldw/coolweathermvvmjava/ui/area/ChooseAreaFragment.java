package com.ldw.coolweathermvvmjava.ui.area;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.ldw.coolweathermvvmjava.R;
import com.ldw.coolweathermvvmjava.databinding.AreaFragBinding;
import com.ldw.coolweathermvvmjava.util.InjectionUtil;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;


public class ChooseAreaFragment extends Fragment {

    public static final int LEVEL_PROVINCE = 0;
    public static final int LEVEL_CITY = 1;
    public static final int LEVEL_COUNTY = 2;

    private AreaFragBinding areaFragBinding;
    private ChooseAreaViewModel viewModel;
    private ChooseAreaAdapter adapter;

    public static ChooseAreaFragment newInstance() {
        return new ChooseAreaFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
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

    }
}

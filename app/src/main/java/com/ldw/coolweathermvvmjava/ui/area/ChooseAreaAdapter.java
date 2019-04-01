package com.ldw.coolweathermvvmjava.ui.area;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.ldw.coolweathermvvmjava.DataBinderMapperImpl;
import com.ldw.coolweathermvvmjava.databinding.SimpleItemBinding;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

/**
 * Created by ldw
 */
public class ChooseAreaAdapter extends ArrayAdapter<String> {
    private  List<String> datas;
    private  int resId;
    private  Context context;

    public ChooseAreaAdapter(@NonNull Context context, int resource, @NonNull List<String> datas) {
        super(context, resource, datas);
        this.resId = resource;
        this.context = context;
        this.datas = datas;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        SimpleItemBinding bind;
        if (convertView==null){
            convertView= LayoutInflater.from(context).inflate(resId,parent,false);
             bind = SimpleItemBinding.bind(convertView);
            convertView.setTag(bind);
        }else {
            bind = (SimpleItemBinding) convertView.getTag();
        }
        bind.setData(datas.get(position));
        return bind.simpleTextView;
    }
}

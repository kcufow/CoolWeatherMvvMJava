package com.ldw.coolweathermvvmjava.data.network;

import java.util.List;

/**
 * Created by ldw
 */
public interface DataFetchCallback<T> {

    void onSuccess(List<T> datas);
    void onFailed();
}

package com.mcy.mtravel.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.mcy.mtravel.App;
import com.mcy.mtravel.R;
import com.mcy.mtravel.entity.other.SqlSearchBean;
import com.zjf.core.impl.OnAsyncModelListener;
import com.zjf.core.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by jifengZhao on 2017/5/3.
 */

public class SqliteUtils {

    private final SQLiteDatabase mDatabase;
    private SqliteHelper mHelper;

    public SqliteUtils(Context context) {
        mHelper = new SqliteHelper(context);
        mDatabase = mHelper.getReadableDatabase();
    }

    public void saveSearchData(final SqlSearchBean searchBean) {
        Observable.create(new ObservableOnSubscribe<Boolean>() {
            @Override
            public void subscribe(ObservableEmitter<Boolean> emitter) throws Exception {
                ContentValues values = new ContentValues();
                values.put("keyword", searchBean.getKeyword());
                values.put("time", searchBean.getTime());
                long result = mDatabase.insert(SqliteHelper.TABLE_SEARCH_HISTORY, null, values);
                emitter.onNext(result != -1);
                emitter.onComplete();
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        LogUtils.e("SqliteUtils", "saveSearchData success:" + aBoolean);
                    }
                });
    }


    public void getSearchHistory(final OnAsyncModelListener<List<SqlSearchBean>> listener) {
        Observable.create(new ObservableOnSubscribe<SqlSearchBean>() {
            @Override
            public void subscribe(ObservableEmitter<SqlSearchBean> emitter) throws Exception {
                Cursor cursor = mDatabase.rawQuery("select * from "
                        + SqliteHelper.TABLE_SEARCH_HISTORY + " order by time desc", null);
                while (cursor.moveToNext()) {
                    int _id = cursor.getInt(cursor.getColumnIndex("_id"));
                    String time = cursor.getString(cursor.getColumnIndex("time"));
                    String keyword = cursor.getString(cursor.getColumnIndex("keyword"));
                    SqlSearchBean bean = new SqlSearchBean(_id, time, keyword);
                    emitter.onNext(bean);
                }
                emitter.onComplete();
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SqlSearchBean>() {
                    List<SqlSearchBean> mList;

                    @Override
                    public void onSubscribe(Disposable disposable) {
                        mList = new ArrayList<>();
                    }

                    @Override
                    public void onNext(SqlSearchBean searchBean) {
                        mList.add(searchBean);
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        listener.onFailure(App.getStringRes(R.string.error_sql), FinalParams.ERROR_INFO);
                    }

                    @Override
                    public void onComplete() {
                        listener.onSuccess(mList);
                    }
                });
    }


    public void onDestroy() {
        if (mDatabase != null) {
            mDatabase.close();
        }
    }
}

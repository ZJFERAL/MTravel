package com.mcy.mtravel.presenter;

import com.mcy.mtravel.entity.travel.AttractionContentsBean;
import com.mcy.mtravel.entity.travel.AttractionTripTagsBean;
import com.mcy.mtravel.entity.travel.AttractionsBean;
import com.mcy.mtravel.entity.travel.HotelsBean;
import com.mcy.mtravel.entity.travel.TravelBean;
import com.mcy.mtravel.model.TravelDetialModel;
import com.mcy.mtravel.view.impl.TravelDetialView;
import com.zjf.core.impl.OnAsyncModelListener;
import com.zjf.core.presenter.Presenter_temp;

import java.util.ArrayList;
import java.util.List;

/**
 * @author :ZJF
 * @version : 2017-05-21 上午 10:07
 */

public class TravelDetialPresenter extends Presenter_temp<TravelDetialView, TravelDetialModel> {

    private String mID;

    public TravelDetialPresenter(String ID) {
        mID = ID;
        mModel = new TravelDetialModel(mID);
    }

    @Override
    protected void onViewCreated() {
        mModel.getData(new OnAsyncModelListener<TravelBean>() {
            @Override
            public void onFailure(String msg, int type) {
                mView.onFailure(msg, type);
            }

            @Override
            public void onSuccess(TravelBean msg) {
                TravelBean headTravelBean = getHeadTravelBean(msg);
                List<AttractionContentsBean> list = getMainDataList(msg);
                List<HotelsBean> hotels = msg.getHotels();
                List<AttractionsBean> attractions = msg.getAttractions();
                mView.getData(headTravelBean,
                        list,
                        hotels,
                        attractions);
            }
        });
    }

    private List<AttractionContentsBean> getMainDataList(TravelBean msg) {
        ArrayList<AttractionContentsBean> list = new ArrayList<>();
        AttractionContentsBean contentsBean = new AttractionContentsBean();
        contentsBean.setTitle("实用贴士");
        contentsBean.setDescription(msg.getTips_html());
        list.add(contentsBean);

        List<AttractionTripTagsBean> tags = msg.getAttraction_trip_tags();
        int size = tags.size();
        for (int i = 0; i < size; i++) {
            AttractionTripTagsBean bean = tags.get(i);
            String name = bean.getName();
            List<AttractionContentsBean> contents = bean.getAttraction_contents();
            int sizeContents = contents.size();
            for (int j = 0; j < sizeContents; j++) {
                AttractionContentsBean attractionContentsBean = contents.get(j);
                attractionContentsBean.setTitle(name);
                list.add(attractionContentsBean);
            }
        }
        return list;
    }

    private TravelBean getHeadTravelBean(TravelBean msg) {
        TravelBean headTravelBean = new TravelBean();
        headTravelBean.setName_zh_cn(msg.getName_zh_cn());
        headTravelBean.setName_en(msg.getName_en());
        headTravelBean.setPhotos_count(msg.getPhotos_count());
        headTravelBean.setAttraction_trips_count(msg.getAttraction_trips_count());
        headTravelBean.setDescription(msg.getDescription());
        headTravelBean.setImage_url(msg.getImage_url());
        return headTravelBean;
    }
}

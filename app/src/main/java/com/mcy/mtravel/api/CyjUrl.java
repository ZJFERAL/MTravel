package com.mcy.mtravel.api;

import com.mcy.mtravel.entity.CBannerBean;
import com.mcy.mtravel.entity.TargetPlaceBean;
import com.mcy.mtravel.entity.TripNoteBean;
import com.mcy.mtravel.entity.TripsBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by zhaojifeng on 2017/4/18.
 */

public interface CyjUrl {

    @GET(value = "trips/featured.json")
    Observable<List<TripsBean>> getTrips(@Query("page") int page);

    @GET(value = "adverts.json?name=app_featured_banner_android")
    Observable<List<CBannerBean>> getCBanner();

    @GET(value = "destinations.json")
    Observable<List<TargetPlaceBean>> getTargetZone();

    @GET(value = "trips/{id}.json")
    Observable<TripNoteBean> getTripsNote(@Path("id") String id);
}

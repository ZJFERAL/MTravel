package com.mcy.mtravel.api;

import com.mcy.mtravel.entity.index.CBannerBean;
import com.mcy.mtravel.entity.index.TargetPlaceBean;
import com.mcy.mtravel.entity.index.TripsBean;
import com.mcy.mtravel.entity.tip.SpecialListBean;
import com.mcy.mtravel.entity.tip.StrategyBean;
import com.mcy.mtravel.entity.tip.TipTripsBean;
import com.mcy.mtravel.entity.tip.TravelListBean;
import com.mcy.mtravel.entity.tiptrips.TipTripsDetialBean;
import com.mcy.mtravel.entity.tipwiki.StrategyDetialBean;
import com.mcy.mtravel.entity.trips.TripNoteBean;
import com.mcy.mtravel.entity.user.UserWithTripsBean;

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

    @GET(value = "users/{id}.json")
    Observable<UserWithTripsBean> getUser(@Path("id") String id, @Query("page") int page);

    @GET(value = "destinations/{id}.json")
    Observable<List<StrategyBean>> getStrategy(@Path("id") String id);

    @GET(value = "wiki/destinations/{id}.json")
    Observable<List<StrategyDetialBean>> getWikiTips(@Path("id") String id);

    @GET(value = "destinations/plans/{id}.json")
    Observable<List<TipTripsBean>> getTipTripsList(@Path("id") String id, @Query("page") String index);

    @GET(value = "destinations/attractions/{id}.json")
    Observable<List<TravelListBean>> getTravelList(@Path("id") String id, @Query("page") String index);

    @GET(value = "articles.json")
    Observable<List<SpecialListBean>> getSpecialList(@Query("destination_id") String id, @Query("page") String index);

    @GET(value = "plans/{id}.json")
    Observable<TipTripsDetialBean> getTipTripDetial(@Path("id") String id);
}

package com.mcy.mtravel.entity.travel;

import com.mcy.mtravel.entity.tip.DestinationBean;

import java.util.List;

/**
 * Created by zhaojifeng on 2017/5/12.
 */

public class TravelBean {


    private int id;
    private String name_zh_cn;
    private String name_en;
    private String description;
    private String tips_html;
    private String user_score;
    private int photos_count;
    private int attraction_trips_count;
    private String address;
    private int ctrip_id;
    private int updated_at;
    private String image_url;
    private int attractions_count;
    private int activities_count;
    private int restaurants_count;
    private DestinationBean destination;
    private boolean current_user_favorite;
    private List<AttractionTripTagsBean> attraction_trip_tags;
    private List<AttractionsBean> attractions;
    private List<HotelsBean> hotels;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName_zh_cn() {
        return name_zh_cn;
    }

    public void setName_zh_cn(String name_zh_cn) {
        this.name_zh_cn = name_zh_cn;
    }

    public String getName_en() {
        return name_en;
    }

    public void setName_en(String name_en) {
        this.name_en = name_en;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTips_html() {
        return tips_html;
    }

    public void setTips_html(String tips_html) {
        this.tips_html = tips_html;
    }

    public String getUser_score() {
        return user_score;
    }

    public void setUser_score(String user_score) {
        this.user_score = user_score;
    }

    public int getPhotos_count() {
        return photos_count;
    }

    public void setPhotos_count(int photos_count) {
        this.photos_count = photos_count;
    }

    public int getAttraction_trips_count() {
        return attraction_trips_count;
    }

    public void setAttraction_trips_count(int attraction_trips_count) {
        this.attraction_trips_count = attraction_trips_count;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getCtrip_id() {
        return ctrip_id;
    }

    public void setCtrip_id(int ctrip_id) {
        this.ctrip_id = ctrip_id;
    }

    public int getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(int updated_at) {
        this.updated_at = updated_at;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public int getAttractions_count() {
        return attractions_count;
    }

    public void setAttractions_count(int attractions_count) {
        this.attractions_count = attractions_count;
    }

    public int getActivities_count() {
        return activities_count;
    }

    public void setActivities_count(int activities_count) {
        this.activities_count = activities_count;
    }

    public int getRestaurants_count() {
        return restaurants_count;
    }

    public void setRestaurants_count(int restaurants_count) {
        this.restaurants_count = restaurants_count;
    }

    public DestinationBean getDestination() {
        return destination;
    }

    public void setDestination(DestinationBean destination) {
        this.destination = destination;
    }

    public boolean isCurrent_user_favorite() {
        return current_user_favorite;
    }

    public void setCurrent_user_favorite(boolean current_user_favorite) {
        this.current_user_favorite = current_user_favorite;
    }

    public List<AttractionTripTagsBean> getAttraction_trip_tags() {
        return attraction_trip_tags;
    }

    public void setAttraction_trip_tags(List<AttractionTripTagsBean> attraction_trip_tags) {
        this.attraction_trip_tags = attraction_trip_tags;
    }


    public List<AttractionsBean> getAttractions() {
        return attractions;
    }

    public void setAttractions(List<AttractionsBean> attractions) {
        this.attractions = attractions;
    }

    public List<HotelsBean> getHotels() {
        return hotels;
    }

    public void setHotels(List<HotelsBean> hotels) {
        this.hotels = hotels;
    }
}

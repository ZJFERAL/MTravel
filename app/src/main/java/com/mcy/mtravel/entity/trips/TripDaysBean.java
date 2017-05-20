package com.mcy.mtravel.entity.trips;

import com.mcy.mtravel.entity.tip.DestinationBean;

import java.util.List;

/**
 * Created by jifengZhao on 2017/4/20.
 */

public class TripDaysBean {
    private int id;
    private String trip_date;
    private int day;
    private int updated_at;
    private DestinationBean destination;
    private List<NodesBean> nodes;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTrip_date() {
        return trip_date;
    }

    public void setTrip_date(String trip_date) {
        this.trip_date = trip_date;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(int updated_at) {
        this.updated_at = updated_at;
    }

    public DestinationBean getDestination() {
        return destination;
    }

    public void setDestination(DestinationBean destination) {
        this.destination = destination;
    }

    public List<NodesBean> getNodes() {
        return nodes;
    }

    public void setNodes(List<NodesBean> nodes) {
        this.nodes = nodes;
    }
}

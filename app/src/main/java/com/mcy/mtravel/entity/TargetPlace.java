package com.mcy.mtravel.entity;

import java.util.List;

/**
 * Created by zhaojifeng on 2017/4/19.
 */

public class TargetPlace {

    /**
     * category : 1
     * destinations : [{"id":55,"name_zh_cn":"日本","name_en":"Japan","poi_count":1010,"lat":36.2048,"lng":138.253,"image_url":"http://m.chanyouji.cn/destinations/55-portrait.jpg","updated_at":1489875643},{"id":45,"name_zh_cn":"泰国","name_en":"Thailand","poi_count":572,"lat":15.87,"lng":100.993,"image_url":"http://m.chanyouji.cn/destinations/45-portrait.jpg","updated_at":1491949272},{"id":47,"name_zh_cn":"韩国","name_en":"South Korea","poi_count":309,"lat":35.9078,"lng":127.767,"image_url":"http://m.chanyouji.cn/destinations/47-portrait.jpg","updated_at":1489098226},{"id":71,"name_zh_cn":"马来西亚","name_en":"Malaysia","poi_count":298,"lat":4.21048,"lng":101.976,"image_url":"http://m.chanyouji.cn/destinations/71-portrait.jpg","updated_at":1479248253},{"id":53,"name_zh_cn":"新加坡","name_en":"Singapore","poi_count":177,"lat":1.35208,"lng":103.82,"image_url":"http://m.chanyouji.cn/destinations/53-portrait.jpg","updated_at":1480803666},{"id":54,"name_zh_cn":"柬埔寨","name_en":"Cambodia","poi_count":96,"lat":12.5657,"lng":104.991,"image_url":"http://m.chanyouji.cn/destinations/54-portrait.jpg","updated_at":1491690082},{"id":49,"name_zh_cn":"越南","name_en":"Vietnam","poi_count":269,"lat":14.0583,"lng":108.277,"image_url":"http://m.chanyouji.cn/destinations/49-portrait.jpg","updated_at":1471299048},{"id":76,"name_zh_cn":"印度尼西亚","name_en":"Indonesia","poi_count":206,"lat":-0.789275,"lng":113.921,"image_url":"http://m.chanyouji.cn/destinations/76-portait.jpg","updated_at":1480803668},{"id":73,"name_zh_cn":"菲律宾","name_en":"Philippines","poi_count":268,"lat":12.8797,"lng":121.774,"image_url":"http://m.chanyouji.cn/destinations/73-portrait.jpg","updated_at":1453754720},{"id":48,"name_zh_cn":"斯里兰卡","name_en":"Sri Lanka","poi_count":173,"lat":7.87305,"lng":80.7718,"image_url":"http://cyjm.qiniudn.com/destinations/48-portrait.jpg","updated_at":1458939484},{"id":56,"name_zh_cn":"尼泊尔","name_en":"Nepal","poi_count":96,"lat":28.3949,"lng":84.124,"image_url":"http://m.chanyouji.cn/destinations/56-portrait.png","updated_at":1458333971},{"id":50,"name_zh_cn":"缅甸","name_en":"Myanmar","poi_count":158,"lat":21.914,"lng":95.9562,"image_url":"http://m.chanyouji.cn/destinations/50-portrait1.jpg","updated_at":1433737537},{"id":75,"name_zh_cn":"老挝","name_en":"Laos","poi_count":69,"lat":19.8563,"lng":102.495,"image_url":"http://m.chanyouji.cn/destinations/75-portrait.jpg","updated_at":1448657987},{"id":51,"name_zh_cn":"朝鲜","name_en":"North Korea","poi_count":21,"lat":40.3399,"lng":127.51,"image_url":"http://m.chanyouji.cn/destinations/51-portrait.jpg","updated_at":1401437750}]
     */

    private String category;
    private List<DestinationsBean> destinations;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<DestinationsBean> getDestinations() {
        return destinations;
    }

    public void setDestinations(List<DestinationsBean> destinations) {
        this.destinations = destinations;
    }

    public static class DestinationsBean {
        /**
         * id : 55
         * name_zh_cn : 日本
         * name_en : Japan
         * poi_count : 1010
         * lat : 36.2048
         * lng : 138.253
         * image_url : http://m.chanyouji.cn/destinations/55-portrait.jpg
         * updated_at : 1489875643
         */

        private int id;
        private String name_zh_cn;
        private String name_en;
        private int poi_count;
        private double lat;
        private double lng;
        private String image_url;
        private int updated_at;

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

        public int getPoi_count() {
            return poi_count;
        }

        public void setPoi_count(int poi_count) {
            this.poi_count = poi_count;
        }

        public double getLat() {
            return lat;
        }

        public void setLat(double lat) {
            this.lat = lat;
        }

        public double getLng() {
            return lng;
        }

        public void setLng(double lng) {
            this.lng = lng;
        }

        public String getImage_url() {
            return image_url;
        }

        public void setImage_url(String image_url) {
            this.image_url = image_url;
        }

        public int getUpdated_at() {
            return updated_at;
        }

        public void setUpdated_at(int updated_at) {
            this.updated_at = updated_at;
        }
    }
}

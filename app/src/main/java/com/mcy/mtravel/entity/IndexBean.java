package com.mcy.mtravel.entity;

import java.util.List;

/**
 * Created by zhaojifeng on 2017/4/13.
 */

public class IndexBean extends BaseBean {

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * slide : [{"type":"url","image":"https://pic.qyer.com/public/guide/config/2017/04/10/14917908299334","url":"qyerguide://page/?id=29262","title":"4月玩耍手册"},{"type":"url","image":"https://pic.qyer.com/public/guide/config/2017/04/10/14917908493918","url":"qyerguide://page/?id=29140","title":"一场源自伦敦的穿越"},{"type":"url","image":"https://pic.qyer.com/public/guide/config/2017/04/10/14917908898339","url":"qyerguide://page/?id=29253","title":"梵高的普罗旺斯"},{"type":"url","image":"https://pic.qyer.com/public/guide/config/2017/04/10/14917909516627","url":"qyerguide://page/?id=29162","title":"新西兰打工签证"}]
         * feeds : {"list":[{"title":"穷游锦囊签证频道上线","page_url":"http://m.qyer.com/guide/page/UKHPp_cxQlw/","page_cover":"http://pic.qyer.com/guide/a8de/c69f/384c/b608/4a88/bd3f/2291/5278/680x","page_desc":"73个出发地，135个目的地，全球签证快速查询。","authors":[{"name":"锦囊编辑部"}],"tags":["干货"],"ctime":1492080027},{"title":"吃货必收｜《孤独的美食家》60家餐厅超全觅食攻略","page_url":"http://m.qyer.com/guide/page/i1e-FldYutU/","page_cover":"http://pic.qyer.com/guide/3810/7e78/00ef/120a/4a4b/690b/3ad2/0c14/680x","page_desc":"建议在食物的陪同下观看","authors":[{"name":"穷游新媒体"}],"tags":["吃万里路"],"ctime":1491991200},{"title":"航班不正常时，这样维权有理有据","page_url":"http://m.qyer.com/guide/page/Qv-B7yX73Y8/","page_cover":"http://pic.qyer.com/guide/39a7/b61d/bc47/933d/37bf/cc4d/318a/2b4b/680x","page_desc":"遇上航班不正常时，普通旅客如何合理的维护自身的权益。","authors":[{"name":"比利白"}],"tags":["干货"],"ctime":1491936160},{"title":"展讯 | 漫威\u201c英雄时代\u201d展览在日本东京举行","page_url":"http://m.qyer.com/guide/page/FNwlMoK2J7E/","page_cover":"http://pic.qyer.com/guide/252c/f95d/7ee3/ff95/f7a9/eaa2/bb73/6848/680x","page_desc":"漫威迷们不可错过的超级英雄盛会！","authors":[{"name":"锦囊编辑部"}],"tags":["兴趣"],"ctime":1491904667},{"title":"色彩｜召唤热爱的世界","page_url":"http://m.qyer.com/guide/page/5ysZT_z7pLg/","page_cover":"http://pic.qyer.com/guide/b883/75a8/5447/347d/7157/60fc/c50d/8ed6/680x","page_desc":"寻找色彩，召唤你对世界的热爱","authors":[],"tags":["兴趣"],"ctime":1491819153},{"title":"西班牙这些精彩的世界遗产，你听说过吗？","page_url":"http://m.qyer.com/guide/page/gT9MWfERT8w/","page_cover":"http://pic.qyer.com/guide/4ab8/c312/f844/df9a/9c84/f337/adcd/3758/680x","page_desc":"西班牙，可不只是马德里和巴塞罗那！","authors":[{"name":"锦囊编辑部"}],"tags":["目的地"],"ctime":1491732000},{"title":"时空穿越探寻最英伦｜英国传统老酒吧","page_url":"http://m.qyer.com/guide/page/5qQXo8J14D8/","page_cover":"http://pic.qyer.com/guide/db49/1211/6f43/a108/bc61/df5e/a051/71ff/680x","page_desc":"时光机再次开启，欢迎走进维多利亚时期驿路酒吧。","authors":[{"name":"红茶在伦敦"}],"tags":["兴趣"],"ctime":1491645600},{"title":"荷兰 | 谁说博物馆都是艺术范","page_url":"http://m.qyer.com/guide/page/671k2NQ0ZJY/","page_cover":"http://pic1.qyer.com/guide/69c1/a1b2/1d60/69b1/93cc/dc16/2201/8614/680x","page_desc":"伦勃朗、维米尔、梵高\u2026\u2026嘿嘿嘿，还有重口味、暗黑系的博物馆在等着你哦","authors":[{"name":"橙色诱惑"},{"name":"呆毛飘不过沧海"},{"name":"马君怡"}],"tags":["兴趣"],"ctime":1491559200},{"title":"米兰国际家具展｜世界顶尖家居设计师已集结完毕","page_url":"http://m.qyer.com/guide/page/Tmtfw91si1Y/","page_cover":"http://pic1.qyer.com/guide/96e6/636b/15b5/c1a9/5165/ce83/1791/437a/680x","page_desc":"如果这周末恰巧在米兰，千万别错过这个感受世界顶尖设计魅力的机会！","authors":[{"name":"迷你小蛋糕"}],"tags":["兴趣"],"ctime":1491488665},{"title":"升龙道｜哆啦A梦的故乡","page_url":"http://m.qyer.com/guide/page/T2PTO8vYE8Y/","page_cover":"http://pic.qyer.com/guide/d2e6/fd77/9369/6b0e/6870/926a/e00f/dd5c/680x","page_desc":"这只名叫\u201c哆啦A梦\u201d的蓝胖子承载了几代人的童年回忆。","authors":[{"name":"锦囊编辑部"}],"tags":["兴趣"],"ctime":1491472800},{"title":"2017北美春季赏花指南","page_url":"http://m.qyer.com/guide/page/ONfc7d2wbJs/","page_cover":"http://pic1.qyer.com/guide/9244/2feb/6608/34d5/dca2/a108/cb19/52f7/680x","page_desc":"春风十里，莫负花期","authors":[{"name":"吃货小分队"}],"tags":["兴趣"],"ctime":1491386400}],"total":185}
         * adverts : [{"name":"旅行安全","image":"https://pic.qyer.com/public/guide/config/2017/04/10/14918169981000","link":"qyerguide://safety/"},{"name":"签证频道","image":"https://pic.qyer.com/public/guide/config/2017/04/10/14918230355824","link":"qyerguide://visa/"}]
         */

        private FeedsBean feeds;
        private List<SlideBean> slide;

        public FeedsBean getFeeds() {
            return feeds;
        }

        public void setFeeds(FeedsBean feeds) {
            this.feeds = feeds;
        }

        public List<SlideBean> getSlide() {
            return slide;
        }

        public void setSlide(List<SlideBean> slide) {
            this.slide = slide;
        }


        public static class FeedsBean {
            /**
             * list : [{"title":"穷游锦囊签证频道上线","page_url":"http://m.qyer.com/guide/page/UKHPp_cxQlw/","page_cover":"http://pic.qyer.com/guide/a8de/c69f/384c/b608/4a88/bd3f/2291/5278/680x","page_desc":"73个出发地，135个目的地，全球签证快速查询。","authors":[{"name":"锦囊编辑部"}],"tags":["干货"],"ctime":1492080027},{"title":"吃货必收｜《孤独的美食家》60家餐厅超全觅食攻略","page_url":"http://m.qyer.com/guide/page/i1e-FldYutU/","page_cover":"http://pic.qyer.com/guide/3810/7e78/00ef/120a/4a4b/690b/3ad2/0c14/680x","page_desc":"建议在食物的陪同下观看","authors":[{"name":"穷游新媒体"}],"tags":["吃万里路"],"ctime":1491991200},{"title":"航班不正常时，这样维权有理有据","page_url":"http://m.qyer.com/guide/page/Qv-B7yX73Y8/","page_cover":"http://pic.qyer.com/guide/39a7/b61d/bc47/933d/37bf/cc4d/318a/2b4b/680x","page_desc":"遇上航班不正常时，普通旅客如何合理的维护自身的权益。","authors":[{"name":"比利白"}],"tags":["干货"],"ctime":1491936160},{"title":"展讯 | 漫威\u201c英雄时代\u201d展览在日本东京举行","page_url":"http://m.qyer.com/guide/page/FNwlMoK2J7E/","page_cover":"http://pic.qyer.com/guide/252c/f95d/7ee3/ff95/f7a9/eaa2/bb73/6848/680x","page_desc":"漫威迷们不可错过的超级英雄盛会！","authors":[{"name":"锦囊编辑部"}],"tags":["兴趣"],"ctime":1491904667},{"title":"色彩｜召唤热爱的世界","page_url":"http://m.qyer.com/guide/page/5ysZT_z7pLg/","page_cover":"http://pic.qyer.com/guide/b883/75a8/5447/347d/7157/60fc/c50d/8ed6/680x","page_desc":"寻找色彩，召唤你对世界的热爱","authors":[],"tags":["兴趣"],"ctime":1491819153},{"title":"西班牙这些精彩的世界遗产，你听说过吗？","page_url":"http://m.qyer.com/guide/page/gT9MWfERT8w/","page_cover":"http://pic.qyer.com/guide/4ab8/c312/f844/df9a/9c84/f337/adcd/3758/680x","page_desc":"西班牙，可不只是马德里和巴塞罗那！","authors":[{"name":"锦囊编辑部"}],"tags":["目的地"],"ctime":1491732000},{"title":"时空穿越探寻最英伦｜英国传统老酒吧","page_url":"http://m.qyer.com/guide/page/5qQXo8J14D8/","page_cover":"http://pic.qyer.com/guide/db49/1211/6f43/a108/bc61/df5e/a051/71ff/680x","page_desc":"时光机再次开启，欢迎走进维多利亚时期驿路酒吧。","authors":[{"name":"红茶在伦敦"}],"tags":["兴趣"],"ctime":1491645600},{"title":"荷兰 | 谁说博物馆都是艺术范","page_url":"http://m.qyer.com/guide/page/671k2NQ0ZJY/","page_cover":"http://pic1.qyer.com/guide/69c1/a1b2/1d60/69b1/93cc/dc16/2201/8614/680x","page_desc":"伦勃朗、维米尔、梵高\u2026\u2026嘿嘿嘿，还有重口味、暗黑系的博物馆在等着你哦","authors":[{"name":"橙色诱惑"},{"name":"呆毛飘不过沧海"},{"name":"马君怡"}],"tags":["兴趣"],"ctime":1491559200},{"title":"米兰国际家具展｜世界顶尖家居设计师已集结完毕","page_url":"http://m.qyer.com/guide/page/Tmtfw91si1Y/","page_cover":"http://pic1.qyer.com/guide/96e6/636b/15b5/c1a9/5165/ce83/1791/437a/680x","page_desc":"如果这周末恰巧在米兰，千万别错过这个感受世界顶尖设计魅力的机会！","authors":[{"name":"迷你小蛋糕"}],"tags":["兴趣"],"ctime":1491488665},{"title":"升龙道｜哆啦A梦的故乡","page_url":"http://m.qyer.com/guide/page/T2PTO8vYE8Y/","page_cover":"http://pic.qyer.com/guide/d2e6/fd77/9369/6b0e/6870/926a/e00f/dd5c/680x","page_desc":"这只名叫\u201c哆啦A梦\u201d的蓝胖子承载了几代人的童年回忆。","authors":[{"name":"锦囊编辑部"}],"tags":["兴趣"],"ctime":1491472800},{"title":"2017北美春季赏花指南","page_url":"http://m.qyer.com/guide/page/ONfc7d2wbJs/","page_cover":"http://pic1.qyer.com/guide/9244/2feb/6608/34d5/dca2/a108/cb19/52f7/680x","page_desc":"春风十里，莫负花期","authors":[{"name":"吃货小分队"}],"tags":["兴趣"],"ctime":1491386400}]
             * total : 185
             */

            private int total;
            private List<ListBean> list;

            public int getTotal() {
                return total;
            }

            public void setTotal(int total) {
                this.total = total;
            }

            public List<ListBean> getList() {
                return list;
            }

            public void setList(List<ListBean> list) {
                this.list = list;
            }

            public static class ListBean {
                /**
                 * title : 穷游锦囊签证频道上线
                 * page_url : http://m.qyer.com/guide/page/UKHPp_cxQlw/
                 * page_cover : http://pic.qyer.com/guide/a8de/c69f/384c/b608/4a88/bd3f/2291/5278/680x
                 * page_desc : 73个出发地，135个目的地，全球签证快速查询。
                 * authors : [{"name":"锦囊编辑部"}]
                 * tags : ["干货"]
                 * ctime : 1492080027
                 */

                private String title;
                private String page_url;
                private String page_cover;
                private String page_desc;
                private long ctime;
                private List<AuthorsBean> authors;
                private List<String> tags;

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public String getPage_url() {
                    return page_url;
                }

                public void setPage_url(String page_url) {
                    this.page_url = page_url;
                }

                public String getPage_cover() {
                    return page_cover;
                }

                public void setPage_cover(String page_cover) {
                    this.page_cover = page_cover;
                }

                public String getPage_desc() {
                    return page_desc;
                }

                public void setPage_desc(String page_desc) {
                    this.page_desc = page_desc;
                }

                public long getCtime() {
                    return ctime;
                }

                public void setCtime(int ctime) {
                    this.ctime = ctime;
                }

                public List<AuthorsBean> getAuthors() {
                    return authors;
                }

                public void setAuthors(List<AuthorsBean> authors) {
                    this.authors = authors;
                }

                public List<String> getTags() {
                    return tags;
                }

                public void setTags(List<String> tags) {
                    this.tags = tags;
                }

                public static class AuthorsBean {
                    /**
                     * name : 锦囊编辑部
                     */

                    private String name;

                    public String getName() {
                        return name;
                    }

                    public void setName(String name) {
                        this.name = name;
                    }
                }
            }
        }

        public static class SlideBean {
            /**
             * type : url
             * image : https://pic.qyer.com/public/guide/config/2017/04/10/14917908299334
             * url : qyerguide://page/?id=29262
             * title : 4月玩耍手册
             */

            private String type;
            private String image;
            private String url;
            private String title;

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }
        }

    }
}

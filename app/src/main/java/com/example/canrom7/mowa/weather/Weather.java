package com.example.canrom7.mowa.weather;

/**
 * Created by Canrom7 on 2016/8/6 .
 * Mailbox canrom7@163.com .
 */
public class Weather {

    /**
     * reason : successed!
     * result : {"data":{"realtime":{"wind":{"windspeed":"10.0","direct":"北风","power":"2级","offset":null},"time":"10:00:00","weather":{"humidity":"73","img":"4","info":"雷阵雨","temperature":"31"},"dataUptime":1470450844,"date":"2016-08-06","city_code":"101210701","city_name":"温州","week":6,"moon":"七月初四"},"life":{"date":"2016-8-6","info":{"kongtiao":["部分时间开启","您将感到些燥热，建议您在适当的时候开启制冷空调来降低温度，以免中暑。"],"yundong":["较不宜","有降水，推荐您在室内进行健身休闲运动；若坚持户外运动，须注意携带雨具并注意避雨防滑。"],"ziwaixian":["中等","属中等强度紫外线辐射天气，外出时建议涂擦SPF高于15、PA+的防晒护肤品，戴帽子、太阳镜。"],"ganmao":["少发","各项气象条件适宜，发生感冒机率较低。但请避免长期处于空调房间中，以防感冒。"],"xiche":["不宜","不宜洗车，未来24小时内有雨，如果在此期间洗车，雨水和路上的泥水可能会再次弄脏您的爱车。"],"wuran":["良","气象条件有利于空气污染物稀释、扩散和清除，可在室外正常活动。"],"chuanyi":["炎热","天气炎热，建议着短衫、短裙、短裤、薄型T恤衫等清凉夏季服装。"]}},"weather":[{"date":"2016-08-06","info":{"night":["3","阵雨","26","东北风","微风","18:43"],"day":["3","阵雨","34","东北风","微风","05:22"]},"week":"六","nongli":"七月初四"},{"date":"2016-08-07","info":{"dawn":["3","阵雨","26","东北风","微风","18:43"],"night":["3","阵雨","26","东北风","微风","18:42"],"day":["3","阵雨","34","东北风","微风","05:23"]},"week":"日","nongli":"七月初五"},{"date":"2016-08-08","info":{"dawn":["3","阵雨","26","东北风","微风","18:42"],"night":["3","阵雨","26","东北风","微风","18:42"],"day":["3","阵雨","33","东北风","微风","05:23"]},"week":"一","nongli":"七月初六"},{"date":"2016-08-09","info":{"dawn":["3","阵雨","26","东北风","微风","18:42"],"night":["3","阵雨","26","东北风","微风","18:41"],"day":["3","阵雨","33","东北风","微风","05:24"]},"week":"二","nongli":"七月初七"},{"date":"2016-08-10","info":{"dawn":["3","阵雨","26","东北风","微风","18:41"],"night":["3","阵雨","26","东北风","微风","18:40"],"day":["3","阵雨","33","东北风","微风","05:25"]},"week":"三","nongli":"七月初八"},{"date":"2016-08-11","info":{"night":["4","雷阵雨","26","","微风","19:30"],"day":["4","雷阵雨","33","","微风","07:30"]},"week":"四","nongli":"七月初九"},{"date":"2016-08-12","info":{"night":["0","晴","25","","微风","19:30"],"day":["1","多云","33","","微风","07:30"]},"week":"五","nongli":"七月初十"}],"pm25":{"key":"","show_desc":0,"pm25":{"curPm":"71","pm25":"45","pm10":"71","level":2,"quality":"良","des":"今天的空气质量是可以接受的，除少数异常敏感体质的人群外，大家可在户外正常活动。"},"dateTime":"2016年08月06日10时","cityName":"温州"},"date":null,"isForeign":0}}
     * error_code : 0
     */

    private String reason;
    /**
     * data : {"realtime":{"wind":{"windspeed":"10.0","direct":"北风","power":"2级","offset":null},"time":"10:00:00","weather":{"humidity":"73","img":"4","info":"雷阵雨","temperature":"31"},"dataUptime":1470450844,"date":"2016-08-06","city_code":"101210701","city_name":"温州","week":6,"moon":"七月初四"},"life":{"date":"2016-8-6","info":{"kongtiao":["部分时间开启","您将感到些燥热，建议您在适当的时候开启制冷空调来降低温度，以免中暑。"],"yundong":["较不宜","有降水，推荐您在室内进行健身休闲运动；若坚持户外运动，须注意携带雨具并注意避雨防滑。"],"ziwaixian":["中等","属中等强度紫外线辐射天气，外出时建议涂擦SPF高于15、PA+的防晒护肤品，戴帽子、太阳镜。"],"ganmao":["少发","各项气象条件适宜，发生感冒机率较低。但请避免长期处于空调房间中，以防感冒。"],"xiche":["不宜","不宜洗车，未来24小时内有雨，如果在此期间洗车，雨水和路上的泥水可能会再次弄脏您的爱车。"],"wuran":["良","气象条件有利于空气污染物稀释、扩散和清除，可在室外正常活动。"],"chuanyi":["炎热","天气炎热，建议着短衫、短裙、短裤、薄型T恤衫等清凉夏季服装。"]}},"weather":[{"date":"2016-08-06","info":{"night":["3","阵雨","26","东北风","微风","18:43"],"day":["3","阵雨","34","东北风","微风","05:22"]},"week":"六","nongli":"七月初四"},{"date":"2016-08-07","info":{"dawn":["3","阵雨","26","东北风","微风","18:43"],"night":["3","阵雨","26","东北风","微风","18:42"],"day":["3","阵雨","34","东北风","微风","05:23"]},"week":"日","nongli":"七月初五"},{"date":"2016-08-08","info":{"dawn":["3","阵雨","26","东北风","微风","18:42"],"night":["3","阵雨","26","东北风","微风","18:42"],"day":["3","阵雨","33","东北风","微风","05:23"]},"week":"一","nongli":"七月初六"},{"date":"2016-08-09","info":{"dawn":["3","阵雨","26","东北风","微风","18:42"],"night":["3","阵雨","26","东北风","微风","18:41"],"day":["3","阵雨","33","东北风","微风","05:24"]},"week":"二","nongli":"七月初七"},{"date":"2016-08-10","info":{"dawn":["3","阵雨","26","东北风","微风","18:41"],"night":["3","阵雨","26","东北风","微风","18:40"],"day":["3","阵雨","33","东北风","微风","05:25"]},"week":"三","nongli":"七月初八"},{"date":"2016-08-11","info":{"night":["4","雷阵雨","26","","微风","19:30"],"day":["4","雷阵雨","33","","微风","07:30"]},"week":"四","nongli":"七月初九"},{"date":"2016-08-12","info":{"night":["0","晴","25","","微风","19:30"],"day":["1","多云","33","","微风","07:30"]},"week":"五","nongli":"七月初十"}],"pm25":{"key":"","show_desc":0,"pm25":{"curPm":"71","pm25":"45","pm10":"71","level":2,"quality":"良","des":"今天的空气质量是可以接受的，除少数异常敏感体质的人群外，大家可在户外正常活动。"},"dateTime":"2016年08月06日10时","cityName":"温州"},"date":null,"isForeign":0}
     */

    private ResultBean result;
    private int error_code;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public static class ResultBean {
        /**
         * realtime : {"wind":{"windspeed":"10.0","direct":"北风","power":"2级","offset":null},"time":"10:00:00","weather":{"humidity":"73","img":"4","info":"雷阵雨","temperature":"31"},"dataUptime":1470450844,"date":"2016-08-06","city_code":"101210701","city_name":"温州","week":6,"moon":"七月初四"}
         * life : {"date":"2016-8-6","info":{"kongtiao":["部分时间开启","您将感到些燥热，建议您在适当的时候开启制冷空调来降低温度，以免中暑。"],"yundong":["较不宜","有降水，推荐您在室内进行健身休闲运动；若坚持户外运动，须注意携带雨具并注意避雨防滑。"],"ziwaixian":["中等","属中等强度紫外线辐射天气，外出时建议涂擦SPF高于15、PA+的防晒护肤品，戴帽子、太阳镜。"],"ganmao":["少发","各项气象条件适宜，发生感冒机率较低。但请避免长期处于空调房间中，以防感冒。"],"xiche":["不宜","不宜洗车，未来24小时内有雨，如果在此期间洗车，雨水和路上的泥水可能会再次弄脏您的爱车。"],"wuran":["良","气象条件有利于空气污染物稀释、扩散和清除，可在室外正常活动。"],"chuanyi":["炎热","天气炎热，建议着短衫、短裙、短裤、薄型T恤衫等清凉夏季服装。"]}}
         * weather : [{"date":"2016-08-06","info":{"night":["3","阵雨","26","东北风","微风","18:43"],"day":["3","阵雨","34","东北风","微风","05:22"]},"week":"六","nongli":"七月初四"},{"date":"2016-08-07","info":{"dawn":["3","阵雨","26","东北风","微风","18:43"],"night":["3","阵雨","26","东北风","微风","18:42"],"day":["3","阵雨","34","东北风","微风","05:23"]},"week":"日","nongli":"七月初五"},{"date":"2016-08-08","info":{"dawn":["3","阵雨","26","东北风","微风","18:42"],"night":["3","阵雨","26","东北风","微风","18:42"],"day":["3","阵雨","33","东北风","微风","05:23"]},"week":"一","nongli":"七月初六"},{"date":"2016-08-09","info":{"dawn":["3","阵雨","26","东北风","微风","18:42"],"night":["3","阵雨","26","东北风","微风","18:41"],"day":["3","阵雨","33","东北风","微风","05:24"]},"week":"二","nongli":"七月初七"},{"date":"2016-08-10","info":{"dawn":["3","阵雨","26","东北风","微风","18:41"],"night":["3","阵雨","26","东北风","微风","18:40"],"day":["3","阵雨","33","东北风","微风","05:25"]},"week":"三","nongli":"七月初八"},{"date":"2016-08-11","info":{"night":["4","雷阵雨","26","","微风","19:30"],"day":["4","雷阵雨","33","","微风","07:30"]},"week":"四","nongli":"七月初九"},{"date":"2016-08-12","info":{"night":["0","晴","25","","微风","19:30"],"day":["1","多云","33","","微风","07:30"]},"week":"五","nongli":"七月初十"}]
         * pm25 : {"key":"","show_desc":0,"pm25":{"curPm":"71","pm25":"45","pm10":"71","level":2,"quality":"良","des":"今天的空气质量是可以接受的，除少数异常敏感体质的人群外，大家可在户外正常活动。"},"dateTime":"2016年08月06日10时","cityName":"温州"}
         * date : null
         * isForeign : 0
         */

        private DataBean data;

        public DataBean getData() {
            return data;
        }

        public void setData(DataBean data) {
            this.data = data;
        }

        public static class DataBean {
            /**
             * wind : {"windspeed":"10.0","direct":"北风","power":"2级","offset":null}
             * time : 10:00:00
             * weather : {"humidity":"73","img":"4","info":"雷阵雨","temperature":"31"}
             * dataUptime : 1470450844
             * date : 2016-08-06
             * city_code : 101210701
             * city_name : 温州
             * week : 6
             * moon : 七月初四
             */

            private RealtimeBean realtime;
            /**
             * key :
             * show_desc : 0
             * pm25 : {"curPm":"71","pm25":"45","pm10":"71","level":2,"quality":"良","des":"今天的空气质量是可以接受的，除少数异常敏感体质的人群外，大家可在户外正常活动。"}
             * dateTime : 2016年08月06日10时
             * cityName : 温州
             */

            private Pm25Bean pm25;

            public RealtimeBean getRealtime() {
                return realtime;
            }

            public void setRealtime(RealtimeBean realtime) {
                this.realtime = realtime;
            }

            public Pm25Bean getPm25() {
                return pm25;
            }

            public void setPm25(Pm25Bean pm25) {
                this.pm25 = pm25;
            }

            public static class RealtimeBean {
                /**
                 * windspeed : 10.0
                 * direct : 北风
                 * power : 2级
                 * offset : null
                 */

                private WindBean wind;
                private String time;
                /**
                 * humidity : 73
                 * img : 4
                 * info : 雷阵雨
                 * temperature : 31
                 */

                private WeatherBean weather;
                private int dataUptime;
                private String date;
                private String city_code;
                private String city_name;
                private int week;
                private String moon;

                public WindBean getWind() {
                    return wind;
                }

                public void setWind(WindBean wind) {
                    this.wind = wind;
                }

                public String getTime() {
                    return time;
                }

                public void setTime(String time) {
                    this.time = time;
                }

                public WeatherBean getWeather() {
                    return weather;
                }

                public void setWeather(WeatherBean weather) {
                    this.weather = weather;
                }

                public int getDataUptime() {
                    return dataUptime;
                }

                public void setDataUptime(int dataUptime) {
                    this.dataUptime = dataUptime;
                }

                public String getDate() {
                    return date;
                }

                public void setDate(String date) {
                    this.date = date;
                }

                public String getCity_code() {
                    return city_code;
                }

                public void setCity_code(String city_code) {
                    this.city_code = city_code;
                }

                public String getCity_name() {
                    return city_name;
                }

                public void setCity_name(String city_name) {
                    this.city_name = city_name;
                }

                public int getWeek() {
                    return week;
                }

                public void setWeek(int week) {
                    this.week = week;
                }

                public String getMoon() {
                    return moon;
                }

                public void setMoon(String moon) {
                    this.moon = moon;
                }

                public static class WindBean {
                    private String direct;
                    private String power;

                    public String getDirect() {
                        return direct;
                    }

                    public void setDirect(String direct) {
                        this.direct = direct;
                    }

                    public String getPower() {
                        return power;
                    }

                    public void setPower(String power) {
                        this.power = power;
                    }
                }

                public static class WeatherBean {
                    private String humidity;
                    private String info;
                    private String temperature;

                    public String getHumidity() {
                        return humidity;
                    }

                    public void setHumidity(String humidity) {
                        this.humidity = humidity;
                    }

                    public String getInfo() {
                        return info;
                    }

                    public void setInfo(String info) {
                        this.info = info;
                    }

                    public String getTemperature() {
                        return temperature;
                    }

                    public void setTemperature(String temperature) {
                        this.temperature = temperature;
                    }
                }
            }

            public static class Pm25Bean {
                /**
                 * curPm : 71
                 * pm25 : 45
                 * pm10 : 71
                 * level : 2
                 * quality : 良
                 * des : 今天的空气质量是可以接受的，除少数异常敏感体质的人群外，大家可在户外正常活动。
                 */

                private Pm25Item pm25;
                private String dateTime;
                private String cityName;

                public Pm25Item getPm25() {
                    return pm25;
                }

                public void setPm25(Pm25Item pm25) {
                    this.pm25 = pm25;
                }

                public String getDateTime() {
                    return dateTime;
                }

                public void setDateTime(String dateTime) {
                    this.dateTime = dateTime;
                }

                public String getCityName() {
                    return cityName;
                }

                public void setCityName(String cityName) {
                    this.cityName = cityName;
                }

                public static class Pm25Item {
                    private String pm25;
                    private String quality;

                    public String getPm25() {
                        return pm25;
                    }

                    public void setPm25(String pm25) {
                        this.pm25 = pm25;
                    }

                    public String getQuality() {
                        return quality;
                    }

                    public void setQuality(String quality) {
                        this.quality = quality;
                    }
                }
            }
        }
    }
}

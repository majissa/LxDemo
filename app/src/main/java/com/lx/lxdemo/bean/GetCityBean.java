package com.lx.lxdemo.bean;

import com.lx.lxlibrary.bean.SortModel;

import java.util.List;

/**
 * 创建人：LX
 * 创建日期：2016/8/5
 * 描述：
 */
public class GetCityBean {
    private int total;
    private Data data;
    private String message;
    private int codeState;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCodeState() {
        return codeState;
    }

    public void setCodeState(int codeState) {
        this.codeState = codeState;
    }

    public class Data {
        private List<CityData> cityData;

        public List<CityData> getCityData() {
            return cityData;
        }

        public void setCityData(List<CityData> cityData) {
            this.cityData = cityData;
        }

        public class CityData extends SortModel {
            private String cityName;
            private String py;
            private int cityID;

            public String getCityName() {
                return cityName;
            }

            public void setCityName(String cityName) {
                this.cityName = cityName;
            }

            public String getPy() {
                return py;
            }

            public void setPy(String py) {
                this.py = py;
            }

            public int getCityID() {
                return cityID;
            }

            public void setCityID(int cityID) {
                this.cityID = cityID;
            }

            @Override
            public String getName() {
                return getCityName();
            }


            public void setName(String name) {
                setCityName(name);
            }

            @Override
            public String getSortLetters() {
                return py;
            }

            @Override
            public void setSortLetters(String sortLetters) {
                setPy(sortLetters);
            }
        }
    }
}

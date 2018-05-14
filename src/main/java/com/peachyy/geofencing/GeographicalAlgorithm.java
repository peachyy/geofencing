package com.peachyy.geofencing;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface GeographicalAlgorithm {
    String LNG_ = "lng";
    String LAT_ = "lat";

    /**
     * 判断点是否在polygon内
     * @param pointX
     * @param pointY
     * @param polygon
     * @return
     */
    boolean isPointInPolygon(double pointX, double pointY,
                             List<Map<String, Object>> polygon);

    /**
     * 判断点是否在polygon内
     * @param pointX
     * @param pointY
     * @param polygonXA
     * @param polygonYA
     * @return
     */
    boolean isPointInPolygon(double pointX, double pointY,
                             ArrayList<Double> polygonXA, ArrayList<Double> polygonYA);
}

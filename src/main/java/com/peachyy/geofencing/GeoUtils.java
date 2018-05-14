package com.peachyy.geofencing;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GeoUtils {

    private static String                GEOGRAPHICAL_ALGORITHM_TYPE = System.getProperty("geoAlgorithm", "jdkAwt");
    private static GeographicalAlgorithm algorithm                   = null;

    static {
        if (GEOGRAPHICAL_ALGORITHM_TYPE.equalsIgnoreCase("jdkAwt")) {
            algorithm = new JdkAwtImpl();
        } else {
            algorithm = new CustomHalfLineImpl();
        }
    }

    public static boolean isPointInPolygon(double pointX, double pointY,
                             List<Map<String, Object>> polygon){
        return algorithm.isPointInPolygon(pointX,pointY,polygon);
    }

    /**
     * 判断点是否在polygon内
     * @param pointX
     * @param pointY
     * @param polygonXA
     * @param polygonYA
     * @return
     */
    public static boolean isPointInPolygon(double pointX, double pointY,
                             ArrayList<Double> polygonXA, ArrayList<Double> polygonYA){
        return algorithm.isPointInPolygon(pointX,pointY,polygonXA,polygonYA);
    }



}

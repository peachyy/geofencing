package com.peachyy.geofencing;

import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class JdkAwtImpl implements GeographicalAlgorithm {

        @Override
        public boolean isPointInPolygon(double pointX, double pointY, List<Map<String, Object>> polygon) {
            Point2D.Double       geoPoint   = buildPoint(pointX, pointY);
            List<Point2D.Double> geoPolygon = buildPolygon(polygon);
            return isPointInPolygon(geoPoint, geoPolygon);
        }

        @Override
        public boolean isPointInPolygon(double pointX, double pointY,
                                        ArrayList<Double> polygonXA, ArrayList<Double> polygonYA) {
            if (polygonXA.size() != polygonYA.size()) {
                throw new IllegalArgumentException("参数错误");
            }
            List<Map<String, Object>> polygonLst = new ArrayList<>();
            for (int i = 0, len = polygonXA.size(); i < len; i++) {
                Map<String, Object> map = new HashMap<>();
                map.put(LNG_, polygonXA.get(i));
                map.put(LAT_, polygonYA.get(i));
                polygonLst.add(map);
            }
            return isPointInPolygon(pointX, pointY, polygonLst);
        }

        private boolean isPointInPolygon(Point2D.Double point, List<Point2D.Double> polygon) {
            GeneralPath p = new GeneralPath();

            Point2D.Double first = polygon.get(0);
            p.moveTo(first.x, first.y);
            polygon.remove(0);
            for (Point2D.Double pg : polygon) {
                p.lineTo(pg.x, pg.y);
            }
            p.lineTo(first.x, first.y);
            p.closePath();
            return p.contains(point);
        }

        public Point2D.Double buildPoint(double x, double y) {
            return new Point2D.Double(x, y);
        }

        public List<Point2D.Double> buildPolygon(List<Map<String, Object>> polygonPoints) {
            List<Point2D.Double> geoPolygon = new ArrayList<>();
            for (Map<String, Object> map : polygonPoints) {
                double lng = Double.parseDouble(Objects.toString(map.get(LNG_)));
                double lat = Double.parseDouble(Objects.toString(map.get(LAT_)));
                geoPolygon.add(buildPoint(lng, lat));
            }
            return geoPolygon;
        }
    }
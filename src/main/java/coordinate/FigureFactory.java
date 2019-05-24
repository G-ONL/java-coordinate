package coordinate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class FigureFactory {
    static Figure getInstance(List<Point> points) {
        Map<Integer, Supplier<Figure>> map = new HashMap<>();
        map.put(2, () -> new Line(points));
        map.put(3, () -> new Triangle(points));
        map.put(4, () -> new Rectangle(points));
        return map.get(points.size()).get();
    }

}

package coordinate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

public class FigureFactory {
    static Figure getInstance(List<Point> points) {
        Map<Integer, Supplier<Figure>> map = new HashMap<>();
        map.put(2, () -> new Line(points));
        map.put(3, () -> new Triangle(points));
        map.put(4, () -> new Rectangle(points));
        return map.get(points.size()).get();
    }

    //getInstance랑 같은 기능
    private static Figure getInstatnce2(List<Point> points) {
        Map<Integer, Function<List<Point>, Figure>> map2 = new HashMap<>();
        map2.put(2, Line::new);
        map2.put(2, Triangle::new);
        map2.put(2, Rectangle::new);
        return map2.get(points.size()).apply(points);
    }

}

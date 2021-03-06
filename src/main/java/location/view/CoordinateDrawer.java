package location.view;

import location.domain.Points;

import java.util.List;
import java.util.stream.Collectors;

class CoordinateDrawer {
    private static final int HORIZONTAL_CORRECTION_VALUE = 6;
    private static final int MAX_HEIGHT_WIDTH = 24;
    private static final String SPACE = " ";
    private static final String DOUBLE_SPACE = " ";
    private static final String TWO_DIGITS_FORMATTING = "%2s";
    private static final String POINT = "●";
    private static final String EM_DASH = "—";
    private static final String HORIZONTAL_CORRECTION_VALUE_DIGIT = "%" + HORIZONTAL_CORRECTION_VALUE + "s";
    private static final String VERTICAL_LINE = "|";
    private static final String ORIGIN_POINT = "  +";
    private static final String ORIGIN_HORIZONTAL_NUMBER = "  0";

    CoordinateDrawer(Points points) {
        drawMap(points);
    }

    private void drawMap(Points points) {
        for (int i = 0; i < MAX_HEIGHT_WIDTH; i++) {
            drawVertical(MAX_HEIGHT_WIDTH - i, points);
            System.out.println();
        }
        drawHorizontal(MAX_HEIGHT_WIDTH);
    }

    private void drawVertical(int currentY, Points points) {
        String numOrBlank = DOUBLE_SPACE;
        if (currentY % 2 == 0) {
            numOrBlank = String.format(TWO_DIGITS_FORMATTING, Integer.toString(currentY));
        }
        List<Integer> currentXGroup = points.getPoints()
                .stream()
                .filter(x -> x.getY() == currentY && x.getY() > 0)
                .map(e -> e.getX()).collect(Collectors.toList());
        drawVerticalHead(numOrBlank, currentXGroup);
        drawDotX(currentXGroup);
    }

    private void drawVerticalHead(String numOrBlank, List<Integer> currentXGroup) {
        if (currentXGroup.size() > 0 && currentXGroup.get(0) == 0) {
            System.out.print(numOrBlank + POINT);
            currentXGroup.remove(0);
            return;
        }
        System.out.print(numOrBlank + VERTICAL_LINE);

    }

    private void drawDotX(List<Integer> currentXGroup) {
        StringBuilder sb = new StringBuilder();
        int beforeX = 0;
        for (int currentX : currentXGroup) {
            beforeX = drawFormattingPoint(sb, beforeX, currentX);
        }
        System.out.print(sb.toString());
    }

    private int drawFormattingPoint(StringBuilder sb, int beforeX, int currentX) {
        for (int i = 0; i < ((currentX - beforeX) * (HORIZONTAL_CORRECTION_VALUE) / 2) - 1; i++) {
            sb.append(SPACE);
        }
        sb.append(POINT);
        beforeX = currentX;
        return beforeX;
    }

    void drawHorizontal(int num) {
        String firstLine = drawFirstLine(num);
        String secondLine = drawSecondLine(num);
        System.out.println(firstLine);
        System.out.println(secondLine);
    }

    private String drawFirstLine(int num) {
        String firstLine = ORIGIN_POINT;
        for (int i = 0; i < num * HORIZONTAL_CORRECTION_VALUE / 2; i++) {
            firstLine += EM_DASH;
        }
        return firstLine;
    }

    private String drawSecondLine(int num) {
        String secondLine = ORIGIN_HORIZONTAL_NUMBER;
        for (int i = 0; i < num / 2; i++) {
            secondLine += String.format(HORIZONTAL_CORRECTION_VALUE_DIGIT, Integer.toString((i + 1) * 2));
        }
        return secondLine;
    }
}

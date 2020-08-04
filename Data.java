import java.awt.image.BufferedImage;
import java.lang.reflect.Array;
import java.util.*;

public class Data {

    private Deque<Double> data;
    private int rightAnswer;

    Data(BufferedImage image) { data = convertTo2DUsingGetRGB(image); }

    private Deque<Double> convertTo2DUsingGetRGB(BufferedImage image) {

        int width = image.getWidth();
        int height = image.getHeight();
        Deque<Double> result = new LinkedList<>();

        int[] rgb = new int[3];

        for (int row = 0; row < height; row += 2) {
            for (int col = 0; col < width; col += 2) {

                double answer = 0;

                image.getData().getPixel(row, col, rgb);
                answer += (rgb[0] + rgb[1] + rgb[2]);
                image.getData().getPixel((row + 1), (col + 1), rgb);
                answer += (rgb[0] + rgb[1] + rgb[2]);
                image.getData().getPixel((row + 1), col, rgb);
                answer += (rgb[0] + rgb[1] + rgb[2]);
                image.getData().getPixel(row, (col + 1), rgb);
                answer += (rgb[0] + rgb[1] + rgb[2]);

                result.add(answer);
            }
        }

        return result;
    }

    public void setRightAnswer(int answer) { rightAnswer = answer; }

    public Deque<Double> getData() { return data; }

    public Deque<Double> getRightAnswers() {

        Deque<Double> answers = new LinkedList<>();

        for(int i = 0; i < 10; i++)
            answers.add((i == rightAnswer) ? 1.0 : 0);

        return answers;
    }
}
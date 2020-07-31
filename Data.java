import java.awt.image.BufferedImage;
import java.util.*;

public class Data {

    private List<Double> data;
    private int rightAnswer;

    Data(BufferedImage image) { data = convertTo2DUsingGetRGB(image); }

    private List<Double> convertTo2DUsingGetRGB(BufferedImage image) {

        int width = image.getWidth();
        int height = image.getHeight();
        List<Double> result = new LinkedList<>();

        int[] rgb = new int[3];

        for (int row = 0; row < height; row++)
            for (int col = 0; col < width; col++)
                Arrays.stream(image.getData().getPixel(row, col, rgb))
                        .forEach(p -> result.add((double) (rgb[0] + rgb[1] + rgb[2]))
                            );

        return result;
    }

    public void setRightAnswer(int answer) { rightAnswer = answer; }

    public List<Double> getData() { return data; }

    public List<Double> getRightAnswers() {

        List<Double> answers = new ArrayList<>();

        for(int i = 0; i < 10; i++)
            answers.add((i == rightAnswer) ? 1.0 : 0);

        return answers;
    }
}
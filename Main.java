import Net.Neurons.Web;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

public class Main {

    private static final int BORDER = 28;

    private Web web;

    private Main(){

        web = new Web(14 * 14, 10);
    }

    public static void main(String[] args) {

        new Main().start();
    }

    private void start() {

        try {

           long rightAnswers = Files.walk(Paths.get("C:\\Users\\Kuzmi\\train"))
                    .filter(Files::isRegularFile)
                    .map(Path::toFile)
                    .filter(f -> {

                        try {

                            Data data = new Data(ImageIO.read(f));
                            data.setRightAnswer(Integer.parseInt(String.valueOf(f.getName().charAt(10))));

                            List<Double> result = web.think(data.getData());

                            double answer = result.stream().max(Comparator.naturalOrder()).get();

                            web.learn(data.getRightAnswers());

                            return (result.indexOf(answer) == Integer.parseInt(String.valueOf(f.getName().charAt(10))));
                        } catch (IOException e) { e.printStackTrace(); }
                        return false;
                    }).count();

            System.out.println("% right answers: " + (rightAnswers / 599.99));
        } catch (IOException e) { e.printStackTrace(); }

    }

}


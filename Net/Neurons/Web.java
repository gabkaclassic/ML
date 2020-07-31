package Net.Neurons;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Web {

    private final int IN;
    private final int HIDDEN;
    private final int OUT;

    private final List<InputNeuron> input;
    private final List<HiddenNeuron> hidden;
    private final List<OutputNeuron> output;

    private List<Double> answers;

    public Web(final int input, final int output) {

        IN = input;
        HIDDEN = input * 3;
        OUT = output;

        this.input = Stream.generate(() -> new InputNeuron(1)).limit(IN).collect(Collectors.toList());
        hidden = Stream.generate(() -> new HiddenNeuron(IN)).limit(HIDDEN).collect(Collectors.toList());
        this.output = Stream.generate(() -> new OutputNeuron(HIDDEN)).limit(OUT).collect(Collectors.toList());
    }

    public List<Double> think(List<Double> in) {

        Iterator<Double> iterator = in.iterator();

        input.stream().forEach(n -> n.setInput(iterator.next()));

        answers = input.stream().map(n -> n.getOutput()).collect(Collectors.toList());

        answers.stream().forEach(a -> {
            hidden.stream().forEach(n -> n.setInput(a));
        });

        answers = hidden.stream().map(n -> n.getOutput()).collect(Collectors.toList());

        answers.stream().forEach(a -> {
            output.stream().forEach(n -> n.setInput(a));
        });

        return (answers = output.stream().map(n -> n.getOutput()).collect(Collectors.toList()));
    }

    public void learn(List<Double> rightAnswers) {

        Iterator<Double> iterator = rightAnswers.iterator();

        output.stream().forEach(n -> {
            n.setRightAnswer(iterator.next());
            n.changeWeights();
            n.getDeltaWeights().stream().forEachOrdered(dw -> {
                hidden.stream().forEach(h -> h.addDeltaWeightInput(dw));
                    });
            hidden.stream().forEach(h -> h.changeWeights());
                });
    }

}

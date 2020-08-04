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

    public Web(final int input, final int output) {

        IN = input;
        HIDDEN = input * 3;
        OUT = output;

        this.input = Stream.generate(() -> new InputNeuron(1)).limit(IN).collect(Collectors.toList());
        hidden = Stream.generate(() -> new HiddenNeuron(IN)).limit(HIDDEN).collect(Collectors.toList());
        this.output = Stream.generate(() -> new OutputNeuron(HIDDEN)).limit(OUT).collect(Collectors.toList());
    }

    public List<Double> think(Deque<Double> in) {

        List<Double> list = new ArrayList<>();

       for(InputNeuron n: input) {

           n.setInput(in.poll());
           n.setOutput();
       }


        for(HiddenNeuron n: hidden) {

            for(Neuron i: input)
                n.setInput(i);

            n.setOutput();
        }

        for(OutputNeuron n: output) {

            for(Neuron h: hidden)
                n.setInput(h);

            n.setOutput();
            list.add(n.getOutput());
        }

        return list;
    }

    public void learn(Deque<Double> rightAnswers) {

        for(OutputNeuron n: output) {

            n.setRightAnswer(rightAnswers.pollFirst());
            n.changeWeights();
        }

        for(HiddenNeuron n: hidden) {

            for(OutputNeuron o: output)
                n.setDeltaWeights(o);
            n.changeWeights();
        }

        for(InputNeuron n: input) {

            for(HiddenNeuron h: hidden)
                n.setDeltaWeights(h);
            n.changeWeights();
        }
    }
}

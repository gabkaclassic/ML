package Net.Neurons;

import java.util.LinkedList;
import java.util.stream.Collectors;

public class OutputNeuron extends Neuron {

    private double rightAnswer;

    public OutputNeuron(int countPreviewLay) { super(countPreviewLay); }

    public void setRightAnswer(double answer) { rightAnswer = answer; }

    public void setDeltaWeights() {

        deltaWeights = weights.stream().map(w -> {

            double e = (rightAnswer - getOutput());

            return (e * derivativeFunction(e));
        }).collect(Collectors.toCollection(LinkedList:: new));

    }

}

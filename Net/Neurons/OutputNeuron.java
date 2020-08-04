package Net.Neurons;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.stream.Collectors;

public class OutputNeuron extends Neuron {

    private double rightAnswer;

    public OutputNeuron(int countPreviewLay) { super(countPreviewLay); }

    protected void setRightAnswer(double answer) { rightAnswer = answer; }

    protected void calculateDeltaWeights() {

        deltaWeights = weights.stream().map(w -> {

            double e = Math.abs(rightAnswer - getOutput());

            return (e * derivativeFunction(e));
        }).collect(Collectors.toCollection(LinkedList:: new));
    }

    protected void setOutput() { output = sumBlock(); }

    protected double sumBlock() {

        Iterator<Double> in = input.iterator();

       return weights.stream().mapToDouble(w -> w * in.next()).sum();
    }

}

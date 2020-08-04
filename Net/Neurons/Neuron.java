package Net.Neurons;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class Neuron {

    protected static final double BORDER_ACTIVATION = 0.00003;
    protected static final double LEARNING_RATE = 0.1;

    protected static final Random random = new Random();

    protected double output;

    protected List<Double> weights;
    protected Queue<Double> input;
    protected Queue<Double> deltaWeights;

    public Neuron(int countPreviewLay) {

        input = new LinkedList<>();
        deltaWeights = new LinkedList<>();

        setStartedWeights(countPreviewLay);
    }

    protected void setStartedWeights(int countPreviewLay) { weights = Stream.generate(() -> (new Random()).nextDouble()).limit(countPreviewLay).collect(Collectors.toList()); }

    public void setInput(Neuron neuron) { input.add(neuron.getOutput()); }

    protected double getOutput() { return output; }

    protected void setOutput() { output = derivativeFunction(sumBlock()); }

    public void changeWeights() {

        calculateDeltaWeights();

        Iterator<Double> dw = deltaWeights.iterator();

        weights = weights.stream().map(w -> {

            if(input.isEmpty() || !dw.hasNext()) return w ;

            return (w + (LEARNING_RATE * dw.next() * input.poll()));
        }).collect(Collectors.toList());

    }

    protected void calculateDeltaWeights() {

        double deltaWeightInput = (deltaWeights.stream().mapToDouble(d -> d).sum() / deltaWeights.size());

        deltaWeights = weights.stream().map(w -> {

            double e = w * deltaWeightInput;

            return (e * derivativeFunction(e));
        }).collect(Collectors.toCollection(LinkedList:: new));
    }

    protected double sumBlock() {

        Iterator<Double> in = input.iterator();

         double answer = weights.stream().map(w -> w * in.next()).mapToDouble(d -> d).sum();

         if(answer > BORDER_ACTIVATION) return answer;
         return 0;
    }

    private double functionActivation(final double x) { return (2 / (1 + Math.pow(Math.E, (-2 * x))) - 1); }

    protected double derivativeFunction(double x) {

        double d = functionActivation(x);

        return (d * (1 - d));
    }

    protected void setDeltaWeights(Neuron neuron) { deltaWeights.add(neuron.getDeltaWeights()); }

    private double getDeltaWeights() { return deltaWeights.poll(); }
}
package Net.Neurons;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class Neuron {

    protected static final double BORDER_ACTIVATION = 0.5;
    protected static final double LEARNING_RATE = 0.5;

    protected static final Random random = new Random();

    protected List<Double> weights;
    protected Queue<Double> input;
    protected Queue<Double> deltaWeights;

    public Neuron(int countPreviewLay) {

        input = new LinkedList<>();
        deltaWeights = new LinkedList<>();

        setWeights(countPreviewLay);
    }

    protected void setWeights(int countPreviewLay) { weights = Stream.generate(() -> random.nextDouble()).limit(countPreviewLay).collect(Collectors.toList()); }

    public void setInput(double in) { input.add(in); }

    public double getOutput() { return derivativeFunction(sumBlock()); }

    public void changeWeights() {

        setDeltaWeights();

        Iterator<Double> dw = deltaWeights.iterator();

        weights = weights.stream().map(w -> {

            while(dw.hasNext())

            if(!dw.hasNext() || input.isEmpty()) return w ;

            return (w + (LEARNING_RATE * dw.next() * input.poll()));
        }).collect(Collectors.toList());

    }

    public void addDeltaWeightInput(double dw) { deltaWeights.add(dw); }

    protected void setDeltaWeights() {

        double deltaWeightInput = (deltaWeights.stream().mapToDouble(d -> d).sum() / deltaWeights.size());

        deltaWeights = weights.stream().map(w -> {

            double e = w * deltaWeightInput;

            return (e * derivativeFunction(e));
        }).collect(Collectors.toCollection(LinkedList:: new));
    }

    private double sumBlock() {

        Iterator<Double> in = input.iterator();

         double answer = weights.stream().map(w -> w * in.next()).mapToDouble(d -> d).sum();

         if(answer > BORDER_ACTIVATION) return answer;
         return -answer;
    }

    private double functionActivation(final double x) {

        double f = (2 / (1 + Math.pow(Math.E, (-2 * x))) - 1);

        return f;
    }

    protected double derivativeFunction(double x) {

        double d = functionActivation(x);

        return (d * (1 - d));
    }

    public List<Double> getDeltaWeights() {

        List<Double> dw = new LinkedList<>();

        while(!deltaWeights.isEmpty())
            dw.add(deltaWeights.poll());

        return dw;
    }
}
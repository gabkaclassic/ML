package Net.Neurons;

import java.util.List;

public class InputNeuron extends Neuron {

    public InputNeuron(int countPreviewLay) { super(countPreviewLay); }

    protected void setWeights(int countPreviewLay) {  weights = List.of(1.0); }
}

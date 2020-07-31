package Graphics;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class Calculator {

    private double X;
    private static String argument;
    private String enter;
    private static final ScriptEngine calculator = new ScriptEngineManager().getEngineByName("nashorn");

    public Calculator() {
        try {
            functions(calculator);
        } catch (ScriptException e) {
            e.printStackTrace();
        }
    }

    public void setArgument(String arg) { argument = arg; }

    public void setEnter(String s) { enter = s; }

    public void setX(double x) { X = x; }

    public double getY() { return calc(enter); }

    private double calc(String enter) {

        char[] c = enter.toCharArray();

        StringBuilder s = new StringBuilder();
        String input = "";

        for(char ch: c) {

            if(ch == argument.toCharArray()[0]) s.append(X);
            else if(ch == 'P') s.append(Math.PI);
            else if(ch == 'E') s.append(Math.E);
            else s.append(ch);
        }

        input = s.toString();

        try { return (double) calculator.eval(input); }
        catch (ScriptException e) { throw new IllegalArgumentException(e); }
    }

    private static void functions(ScriptEngine script) throws ScriptException {

        for(String function: new String[] {"sin", "cos", "tan", "abs", "sqrt", "acos", "asin", "atan"})
            script.eval("function " + function + "(x) { return Java.type('java.lang.Math')." + function + "(x); }");

        script.eval("function ctg(x) { return 1 / Java.type('java.lang.Math')." + "tan" + "(x); }");
        script.eval("function actg(x) { return 1 / Java.type('java.lang.Math')." + "atan" + "(x); }");
        script.eval("function pow(x, y) { return Math.pow(x, y); }");
    }


}

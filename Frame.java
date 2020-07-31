package Graphics;

import javax.sound.sampled.Line;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.List;

public class Frame extends JFrame {

    private Panel panel;
    private JLabel lblTime;
    private Dimension sSize;

    private static String function;
    private static String function2;

    private static String val_X;
    private static String val_Y;

    private static int MAX_X;
    private static int MIN_X;
    private static int MAX_Y;
    private static int MIN_Y;
    private static int MAX_X2;
    private static int MIN_X2;
    private static int MAX_Y2;
    private static int MIN_Y2;

    public Frame() {

        sSize = Toolkit.getDefaultToolkit().getScreenSize();
        panel = new Panel();
    }

    public void initialize() {

        setBounds(100, 100, 880, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        JTextField input_Y = new JTextField();
        input_Y.setBounds(10, 48, 46, 26);
        getContentPane().add(input_Y);
        input_Y.setColumns(10);

        JLabel label_Y = new JLabel("\u0412\u0432\u0435\u0434\u0438\u0442\u0435 \u0437\u0430\u0432\u0438\u0441\u0438\u043C\u0443\u044E \u0432\u0435\u043B\u0438\u0447\u0438\u043D\u0443:");
        label_Y.setFont(new Font("Sylfaen", Font.BOLD | Font.ITALIC, 14));
        label_Y.setBounds(0, 0, 210, 45);
        getContentPane().add(label_Y);

        JLabel label_X = new JLabel("\u0412\u0432\u0435\u0434\u0438\u0442\u0435 \u0432\u0435\u043B\u0438\u0447\u0438\u043D\u0443-\u0430\u0440\u0433\u0443\u043C\u0435\u043D\u0442: ");
        label_X.setFont(new Font("Sylfaen", Font.BOLD | Font.ITALIC, 14));
        label_X.setBounds(0, 85, 204, 32);
        getContentPane().add(label_X);

        JTextField input_X = new JTextField();
        input_X.setColumns(10);
        input_X.setBounds(10, 128, 46, 26);
        getContentPane().add(input_X);

        JLabel lbln = new JLabel("\u0412\u0432\u0435\u0434\u0438\u0442\u0435 \u0443\u0440\u0430\u0432\u043D\u0435\u043D\u0438\u0435 \u0437\u0430\u0432\u0438\u0441\u0438\u043C\u043E\u0441\u0442\u0438:");
        lbln.setFont(new Font("Sylfaen", Font.BOLD | Font.ITALIC, 12));
        lbln.setBounds(0, 169, 210, 45);
        getContentPane().add(lbln);

        JTextField input_function = new JTextField();
        input_function.setColumns(10);
        input_function.setBounds(10, 213, 188, 26);
        getContentPane().add(input_function);

        JTextField input_function2 = new JTextField();
        input_function2.setColumns(10);
        input_function2.setBounds(249, 213, 188, 26);
        getContentPane().add(input_function2);

        JLabel getY = new JLabel();
        getY.setText("null");
        getY.setForeground(Color.GRAY);
        getY.setFont(new Font("Sylfaen", Font.BOLD, 16));
        getY.setBounds(113, 299, 72, 45);
        getContentPane().add(getY);


        lblTime = new JLabel();
        lblTime.setText("\u0412\u0440\u0435\u043C\u044F \u043F\u043E\u0441\u0442\u0440\u043E\u0435\u043D\u0438\u044F: " + 0);
        lblTime.setFont(new Font("Sylfaen", Font.BOLD | Font.ITALIC, 14));
        lblTime.setBounds(231, 539, 282, 45);
        getContentPane().add(lblTime);

        JLabel lblX = new JLabel();
        lblX.setText("\u0412\u0432\u0435\u0434\u0438\u0442\u0435 \u0437\u043D\u0430\u0447\u0435\u043D\u0438\u0435  : ");
        lblX.setFont(new Font("Sylfaen", Font.BOLD | Font.ITALIC, 14));
        lblX.setBounds(10, 250, 163, 45);
        getContentPane().add(lblX);

        JLabel lblY = new JLabel();
        lblY.setText("\u0417\u043D\u0430\u0447\u0435\u043D\u0438\u0435  : ");
        lblY.setFont(new Font("Sylfaen", Font.BOLD | Font.ITALIC, 14));
        lblY.setBounds(10, 294, 107, 45);
        getContentPane().add(lblY);

        JLabel lblX2 = new JLabel();
        lblX2.setText("\u0412\u0432\u0435\u0434\u0438\u0442\u0435 \u0437\u043D\u0430\u0447\u0435\u043D\u0438\u0435  : ");
        lblX2.setFont(new Font("Sylfaen", Font.BOLD | Font.ITALIC, 14));
        lblX2.setBounds(249, 250, 163, 45);
        getContentPane().add(lblX2);

        JLabel lblY2 = new JLabel();
        lblY2.setText("\u0417\u043D\u0430\u0447\u0435\u043D\u0438\u0435  : ");
        lblY2.setFont(new Font("Sylfaen", Font.BOLD | Font.ITALIC, 14));
        lblY2.setBounds(249, 294, 107, 45);
        getContentPane().add(lblY2);

        JTextField SetX = new JTextField();
        SetX.setColumns(10);
        SetX.setBounds(155, 262, 46, 26);
        getContentPane().add(SetX);

        JButton but_ask = new JButton("?");
        but_ask.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                JOptionPane.showMessageDialog(null, "1.	Каждое математическое обозначение должно обособляться от остальных пробелом. Пример ввода строки (где _ - пробел): X_*_sin_(_P_/_X_)" + "\n" +
                        "2.	Каждая ось координатной плоскости должна быть обозначена." + "\n" +
                        "3.	Оси координатной плоскости не могут быть названы обозначением констант, содержащихся в списке констант программы." + "\n" +
                        "4.	Необходимо ввести нужные точки ограничения функции (для использования функции построения второго графика для него также отдельно нужно вводить точки ограничения)." + "\n" +
                        "5.	Обозначение аргумента в записи уравнения зависимости должно совпадать с его обозначением на соответствующей оси координат." + "\n" +
                        "6.	Нахождение значения ординаты по введённому значению аргумента определённой функции возможно лишь после построения графика этой самой функции." + "\n" +
                        "7.	Разделением дробной и целой частью числа всегда должен являться символ «.» (точка)." + "\n" +
                        "8. Значение аргуента функций нужно указывать в скобках"
                );
            }
        });

        but_ask.setForeground(Color.BLACK);
        but_ask.setFont(new Font("Tahoma", Font.BOLD, 15));
        but_ask.setBounds(450, 530, 50, 50);
        getContentPane().add(but_ask);

        JButton but_const = new JButton("Список значений");
        but_const.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                JOptionPane.showMessageDialog(null, "Операторы и функции: " + "\n" +
                        "+ - сложение" + "\n" +
                        "- - вычитание" + "\n" +
                        "* - умножение" + "\n" +
                        "/ - деление" + "\n" +
                        "% - остаток от деления" + "\n" +
                        "pow() - возведение в степень" + "\n" +
                        "() - разграничительные скобки" + "\n" +
                        "sin - синус (рад) " + "\n" +
                        "cos - косинус (рад) " + "\n" +
                        "tan - тангенс (рад) " + "\n" +
                        "ctg - катангенс (рад) " + "\n" +
                        "asin - арксинус выражения " + "\n" +
                        "acos - арккосинус выражения " + "\n" +
                        "atan - арктангенс выражения " + "\n" +
                        "actg - арккатангнс выражения " + "\n" +
                        "abs - модуль" + "\n" +
                        "sqrt - квадратный корень" + "\n" +
                        "Константы: " + "\n" +
                        "P - число 'пи' " + "\n" +
                        "E - экспонента" + "\n"
                );
            }
        });

        but_const.setForeground(Color.BLACK);
        but_const.setFont(new Font("Tahoma", Font.BOLD, 12));
        but_const.setBounds(530, 530, 150, 50);
        getContentPane().add(but_const);

        JLabel lblXmax = new JLabel("X_Max");
        lblXmax.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblXmax.setBounds(10, 417, 46, 14);
        getContentPane().add(lblXmax);

        JLabel lblXmin = new JLabel("X_Min");
        lblXmin.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblXmin.setBounds(10, 442, 46, 14);
        getContentPane().add(lblXmin);

        JLabel lblYmax = new JLabel("Y_Max");
        lblYmax.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblYmax.setBounds(10, 477, 46, 14);
        getContentPane().add(lblYmax);

        JLabel lblYmin = new JLabel("Y_Min");
        lblYmin.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblYmin.setBounds(10, 502, 46, 14);
        getContentPane().add(lblYmin);

        JTextField X_Min = new JTextField();
        X_Min.setColumns(10);
        X_Min.setBounds(68, 440, 38, 20);
        getContentPane().add(X_Min);

        JTextField Y_Max = new JTextField();
        Y_Max.setColumns(10);
        Y_Max.setBounds(68, 471, 38, 20);
        getContentPane().add(Y_Max);

        JTextField X_Max = new JTextField();
        X_Max.setBounds(66, 411, 38, 20);
        getContentPane().add(X_Max);
        X_Max.setColumns(10);

        JTextField Y_Min = new JTextField();
        Y_Min.setColumns(10);
        Y_Min.setBounds(68, 500, 38, 20);
        getContentPane().add(Y_Min);

        JLabel lblXmax2 = new JLabel("X_Max");
        lblXmax2.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblXmax2.setBounds(231, 417, 46, 14);
        getContentPane().add(lblXmax2);

        JLabel lblXmin2 = new JLabel("X_Min");
        lblXmin2.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblXmin2.setBounds(231, 442, 46, 14);
        getContentPane().add(lblXmin2);

        JLabel lblYmax2 = new JLabel("Y_Max");
        lblYmax2.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblYmax2.setBounds(231, 473, 46, 14);
        getContentPane().add(lblYmax2);

        JLabel lblYmin2 = new JLabel("Y_Min");
        lblYmin2.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblYmin2.setBounds(231, 502, 46, 14);
        getContentPane().add(lblYmin2);

        JTextField X_Max2 = new JTextField();
        X_Max2.setColumns(10);
        X_Max2.setBounds(287, 415, 38, 20);
        getContentPane().add(X_Max2);

        JTextField X_Min2 = new JTextField();
        X_Min2.setColumns(10);
        X_Min2.setBounds(287, 440, 38, 20);
        getContentPane().add(X_Min2);

        JTextField Y_Max2 = new JTextField();
        Y_Max2.setColumns(10);
        Y_Max2.setBounds(287, 471, 38, 20);
        getContentPane().add(Y_Max2);

        JTextField Y_Min2 = new JTextField();
        Y_Min2.setColumns(10);
        Y_Min2.setBounds(287, 500, 38, 20);
        getContentPane().add(Y_Min2);


        JButton but_paint = new JButton("\u041D\u0430\u0440\u0438\u0441\u043E\u0432\u0430\u0442\u044C \u0433\u0440\u0430\u0444\u0438\u043A(\u0438)");
        but_paint.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                function = input_function.getText();
                function2 = input_function2.getText();

                try {

                    MAX_X = Integer.parseInt(X_Max.getText());
                    MIN_X = Integer.parseInt(X_Min.getText());
                    MAX_Y = Integer.parseInt(Y_Max.getText());
                    MIN_Y = Integer.parseInt(Y_Min.getText());
                } catch (Exception e1) {
                }

                try {

                    MAX_X2 = Integer.parseInt(X_Max2.getText());
                    MIN_X2 = Integer.parseInt(X_Min2.getText());
                    MAX_Y2 = Integer.parseInt(Y_Max2.getText());
                    MIN_Y2 = Integer.parseInt(Y_Min2.getText());
                } catch (Exception e1) {
                }

                val_X = input_X.getText();
                val_Y = input_Y.getText();

                JFrame frame = new JFrame();
                frame.setVisible(true);

                lblX.setText("Введите значение " + input_X.getText() + " : ");
                lblX2.setText("Значение " + input_X.getText() + " : ");

                lblY.setText("Введите значение " + input_Y.getText() + " : ");
                lblY2.setText("Значение " + input_Y.getText() + " : ");

                frame.setSize(sSize);
                Panel panel = new Panel();
                frame.getContentPane().add(panel);

                panel.repaint();
            }
        });

        but_paint.setForeground(Color.GREEN);
        but_paint.setFont(new Font("Tahoma", Font.BOLD, 15));
        but_paint.setBounds(10, 537, 210, 45);
        getContentPane().add(but_paint);

        JButton but_calc = new JButton("\u041D\u0430\u0439\u0442\u0438");
        but_calc.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {

                double X = Double.parseDouble(SetX.getText());
                panel.calculator.setEnter(function);
                getY.setText("" + panel.calculator.getY());
            }
        });

        but_calc.setForeground(Color.CYAN);
        but_calc.setFont(new Font("Tahoma", Font.BOLD, 15));
        but_calc.setBounds(10, 346, 118, 45);
        getContentPane().add(but_calc);

        JLabel label = new JLabel("\u0412\u0432\u0435\u0434\u0438\u0442\u0435 \u0432\u0442\u043E\u0440\u043E\u0435 \u0443\u0440\u0430\u0432\u043D\u0435\u043D\u0438\u0435 \u0437\u0430\u0432\u0438\u0441\u0438\u043C\u043E\u0441\u0442\u0438:");
        label.setFont(new Font("Sylfaen", Font.BOLD | Font.ITALIC, 12));
        label.setBounds(239, 169, 252, 45);
        getContentPane().add(label);

        JTextField SetX2 = new JTextField();
        SetX2.setColumns(10);
        SetX2.setBounds(394, 262, 46, 26);
        getContentPane().add(SetX2);

        JLabel getY2 = new JLabel();
        getY2.setText("null");
        getY2.setForeground(Color.GRAY);
        getY2.setFont(new Font("Sylfaen", Font.BOLD, 16));
        getY2.setBounds(366, 299, 72, 45);
        getContentPane().add(getY2);

        JButton but_calc2 = new JButton("\u041D\u0430\u0439\u0442\u0438");
        but_calc2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {

                double X = Double.parseDouble(SetX.getText());
                panel.calculator2.setEnter(function2);
                getY.setText("" + panel.calculator2.getY());
            }
        });

        but_calc2.setForeground(Color.MAGENTA);
        but_calc2.setFont(new Font("Tahoma", Font.BOLD, 15));
        but_calc2.setBounds(238, 346, 118, 45);
        getContentPane().add(but_calc2);
    }

    private class Panel extends JPanel {

        private final int OX = (sSize.width / 2);
        private final int OY = (sSize.height / 2);

        private int step_X;
        private int step_Y;
        private double X;
        private double Y;
        private double X2;
        private double Y2;

        private Calculator calculator;
        private Calculator calculator2;

        public Panel() {

            calculator = new Calculator();
            calculator2 = new Calculator();
        }

        public void paintComponent(Graphics g) {

            float begin = System.nanoTime();

            calculator.setArgument(val_X);

            List<Integer> coordinats = new ArrayList<>();
            List<Integer> coordinats2= new ArrayList<>();

            Graphics2D gr = (Graphics2D) g;
            gr.setColor(Color.BLACK);

            gr.setFont(new Font("consolas", Font.BOLD, 18));
            gr.drawString(val_X, sSize.width - 50, (sSize.height / 2) + 20);
            gr.drawString(val_Y, (sSize.width / 2) - 40, 20);

            gr.drawLine(0, OY - 1, sSize.width, OY - 1);   //ось X
            gr.drawLine(0, OY, sSize.width, OY);
            gr.drawLine(0, OY + 1, sSize.width, OY + 1);

            gr.drawLine(OX - 1, 0, OX - 1, sSize.height);   //ось Y
            gr.drawLine(OX, 0, OX, sSize.height);
            gr.drawLine(OX + 1, 0, OX + 1, sSize.height);

            if ((Math.abs(MAX_X)) + (Math.abs(MIN_X)) != 0) {

                step_X = ((sSize.width / ((Math.abs(MAX_X)) + (Math.abs(MIN_X)))));
                step_Y = ((sSize.height / ((Math.abs(MAX_Y)) + (Math.abs(MIN_Y)))));
            } else {

                step_X = ((sSize.width / ((Math.abs(MAX_X2)) + (Math.abs(MIN_X2)))));
                step_Y = ((sSize.height / ((Math.abs(MAX_Y2)) + (Math.abs(MIN_Y2)))));
            }

            for (int i = 0; i < sSize.height; i += step_Y) gr.drawLine(OX - 5, i, OX + 5, i);
            for (int i = 0; i < sSize.width; i += step_X) gr.drawLine(i, OY - 5, i, OY + 5);

            if (function2.isEmpty()) {

                calculator.setEnter(function);

                gr.setColor(Color.RED);

                int o = 1;

                for (double x = MIN_X; x <= MAX_X; x += 0.1) {

                    o += 2;

                    X = x;
                    calculator.setX(X);
                    Y = calculator.getY();

                    int CX;
                    int CY;

                    if (x == MIN_X) {

                        coordinats.add((int) (OX + (step_X * (X))));
                        coordinats.add((int) (OY - (step_Y * Y)));
                    }

                    if ((Y > MIN_Y) && (Y < MAX_Y)) {

                        CX = (int) (OX + (step_X * X));
                        CY = (int) (OY - (step_Y * Y));
                    } else {

                        CX = coordinats.get(coordinats.size() - 2);
                        CY = coordinats.get(coordinats.size() - 1);
                    }

                    coordinats.add(CX);
                    coordinats.add(CY);

                    gr.fillOval(CX, CY, 4, 4);

                    if (x != MAX_X) {

                        gr.setColor(Color.PINK);
                        gr.drawLine(coordinats.get(o - 3), coordinats.get(o - 2), coordinats.get(o - 1), coordinats.get(o));
                        gr.setColor(Color.RED);
                    }
                }
            } else if (function.isEmpty()) {

                calculator.setEnter(function2);

                gr.setColor(Color.BLUE);

                int o = 1;

                for (double x = MIN_X2; x <= MAX_X2; x += 0.1) {

                    o += 2;

                    X = x;
                    calculator2.setX(X);
                    Y2 = (int)calculator.getY();

                    int CX;
                    int CY;

                    if (x == MIN_X2) {

                        coordinats2.add(OX + (int) (step_X * (X)));
                        coordinats2.add(OY - (int) (step_Y * Y2));
                    }

                    if ((Y2 > MIN_Y2) && (Y2 < MAX_Y2)) {

                        CX = OX + (int) (step_X * X);
                        CY = OY - (int) (step_Y * Y2);
                    } else {

                        CX = coordinats2.get(coordinats2.size() - 2);
                        CY = coordinats2.get(coordinats2.size() - 1);
                    }

                    coordinats2.add(CX);
                    coordinats2.add(CY);

                    gr.fillOval(CX, CY, 4, 4);

                    if ((x != MAX_X2)) {

                        gr.setColor(Color.CYAN);
                        gr.drawLine(coordinats2.get(o - 3), coordinats2.get(o - 2), coordinats2.get(o - 1), coordinats2.get(o));
                        gr.setColor(Color.BLUE);
                    }
                }
            } else {

                calculator.setEnter(function);
                calculator2.setEnter(function2);

                int o = 1;

                for (double x = MIN_X; x <= MAX_X; x += 0.1) {

                    o += 2;

                    X = x;
                    calculator.setX(X);
                    Y = (int)calculator.getY();

                    int CX;
                    int CY;

                    if (x == MIN_X) {

                        coordinats.add(OX + (int) (step_X * (X)));
                        coordinats.add(OY - (int) (step_Y * Y));
                    }

                    if ((Y > MIN_Y) && (Y < MAX_Y)) {

                        CX = OX + (int) (step_X * X);
                        CY = OY - (int) (step_Y * Y);
                    } else {

                        CX = coordinats.get(coordinats.size() - 2);
                        CY = coordinats.get(coordinats.size() - 1);
                    }

                    coordinats.add(CX);
                    coordinats.add(CY);

                    gr.setColor(Color.RED);
                    gr.fillOval(CX, CY, 4, 4);

                    if ((x != MAX_X)) {

                        gr.setColor(Color.PINK);
                        gr.drawLine(coordinats.get(o - 3), coordinats.get(o - 2), coordinats.get(o - 1), coordinats.get(o));
                    }

                }

                o = 1;

                for (double x = MIN_X2; x <= MAX_X2; x += 0.1) {

                    o += 2;

                    X = x;
                    calculator2.setX(X);
                    Y2 = calculator2.getY();

                    int CX;
                    int CY;

                    if (x == MIN_X2) {

                        coordinats2.add(OX + (int) (step_X * X));
                        coordinats2.add(OY - (int) (step_Y * Y2));
                    }

                    if ((Y2 > MIN_Y2) && (Y2 < MAX_Y2)) {

                        CX = OX + (int) (step_X * X);
                        CY = OY - (int) (step_Y * Y2);
                    } else {

                        CX = coordinats2.get(coordinats2.size() - 2);
                        CY = coordinats2.get(coordinats2.size() - 1);
                    }

                    coordinats2.add(CX);
                    coordinats2.add(CY);

                    gr.setColor(Color.BLUE);
                    gr.fillOval(CX, CY, 4, 4);

                    if ((x != MAX_X2)) {

                        gr.setColor(Color.CYAN);
                        gr.drawLine(coordinats2.get(o - 3), coordinats2.get(o - 2), coordinats2.get(o - 1), coordinats2.get(o));
                    }
                }
            }

            float end = System.nanoTime();

            lblTime.setText("Время построения (ns): " + ((int) (end - begin)));
        }
    }
}

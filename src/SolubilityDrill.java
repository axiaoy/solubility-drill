import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class SolubilityDrill implements ActionListener {
    int guess; // 0 is null, 1 is true, 2 is false
    String compound;
    int answer;
    int correct_questions = 0;
    int seconds =60;
    int num_answered=0;
    JFrame frame = new JFrame();
    JTextField textField = new JTextField();
    JButton button_sol = new JButton();
    JButton button_ins = new JButton();
    JLabel timeLabel = new JLabel();
    JLabel secondsLeft = new JLabel();
    JButton reloadButton = new JButton();
    JTextField percentScore = new JTextField();

    JLabel percentMark = new JLabel();

    public static HashMap<String, Integer> CompoundSoluble = new HashMap<String, Integer>();
    public static String[] compoundList = {
            "AgBr", "AgCl", "AgI", "Ag₂O", "AgOH", "Ag₃PO₄", "BaCO₃", "BaO", "Ba₃(PO₄)₂", "BaSO₄", "CaCO₃", "CaO", "Ca₃(PO₄)₂", "HgBr₂", "HgCl₂", "HgCO₃", "HgI₂", "Hg(OH)₂", "Hg₃(PO₄)₂", "MgCO₃", "MgO", "Mg(OH)₂", "Mg₃(PO₄)₂", "PbBr₂", "PbCl₂", "PbCO₃", "PbI₂", "PbO", "Pb(OH)₂", "Pb₃(PO₄)₂", "PbSO₄", "CaSO₄", "AgC₂H₃O₂", "AgClO₃", "AgClO₄", "AgNO₃", "Ag₂SO₄", "BaBr₂", "Ba(C₂H₃O₂)₂", "BaCl₂", "Ba(ClO₃)₂", "Ba(ClO₄)₂", "BaI₂", "Ba(NO₃)₂", "Ba(OH)₂", "CaBr₂", "Ca(C₂H₃O₂)₂", "CaCl₂", "Ca(ClO₃)₂", "Ca(ClO₄)₂", "CaI₂", "Ca(NO₃)₂", "Ca(OH)₂", "Hg(C₂H₃O₂)₂", "Hg(ClO₃)₂", "Hg(ClO₄)₂", "Hg(NO₃)₂", "HgSO₄", "KBr", "KC₂H₃O₂", "KCl", "KClO₃", "KClO₄", "K₂CO₃", "KI", "KNO₃", "K₂O", "KOH", "K₃PO₄", "K₂SO₄", "LiBr", "LiC₂H₃O₂", "LiCl", "LiClO₃", "LiClO₄", "Li₂CO₃", "LiI", "LiNO₃", "Li₂O", "LiOH", "Li₃PO₄", "Li₂SO₄", "MgBr₂", "Mg(C₂H₃O₂)₂", "MgCl₂", "Mg(ClO₃)₂", "Mg(ClO₄)₂", "MgI₂", "Mg(NO₃)₂", "MgSO₄", "NaBr", "NaC₂H₃O₂", "NaCl", "NaClO₃", "NaClO₄", "NaI", "NaNO₃", "Na₂O", "NaOH", "Na₃PO₄", "Na₂SO₄", "NH₄Br", "NH₄C₂H₃O₂", "NH₄ClO₃", "NH₄ClO₄", "(NH₄)₂CO₃", "NH₄I", "NH₄NO₃", "(NH₄)₂O", "NH₄OH", "(NH₄)₃PO₄", "(NH₄)₂SO₄", "Pb(C₂H₃O₂)₂", "Pb(ClO₃)₂", "Pb(ClO₄)₂", "PbNO₃"
    };

    Timer timer = new Timer(1000, new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            seconds--;
            secondsLeft.setText(String.valueOf(seconds));
            if(seconds<=0) {
                timer.stop();
                showResults();
                button_sol.setEnabled(false);
                button_ins.setEnabled(false);
            }
        }
    });

    public SolubilityDrill() {
        frame.setTitle("Solubility Drill Quiz");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420,420);
        frame.setResizable(false);
        frame.getContentPane().setBackground(new Color(54,57,62));
        frame.setLayout(null);

        percentScore.setBounds(290,0,110,25);
        percentScore.setBackground(new Color(54,57,62));
        percentScore.setForeground(new Color(255,135,135));
        percentScore.setFont(new Font("Arial",Font.BOLD,17));
        percentScore.setHorizontalAlignment(JTextField.CENTER);
        percentScore.setEditable(false);

        percentMark.setBounds(190,0,80,25);
        percentMark.setForeground(Color.WHITE);
        percentMark.setFont(new Font("Arial",Font.BOLD,17));
        percentMark.setText("accuracy:");
        percentMark.setHorizontalAlignment(JTextField.CENTER);

        textField.setBounds(0,25,420,200);
        textField.setBackground(new Color(54,57,62));
        textField.setForeground(new Color(255,255,255));
        textField.setFont(new Font("Arial",Font.BOLD,75));
        textField.setBorder(BorderFactory.createLineBorder(new Color(63,69,73)));
        textField.setHorizontalAlignment(JTextField.CENTER);
        textField.setEditable(false);


        button_sol.setBounds(0,220,210,180);
        button_sol.setFont(new Font("Arial", Font.BOLD,30));
        button_sol.setBackground(new Color(63,69,73));
        button_sol.setFocusable(false);
        button_sol.addActionListener(this);
        button_sol.setText("soluble");

        secondsLeft.setBounds(125,0,30,25);
        secondsLeft.setBackground(new Color(54,57,62));
        secondsLeft.setForeground(new Color(255,135,135));
        secondsLeft.setFont(new Font("Arial", Font.BOLD,17));
        secondsLeft.setOpaque(true);
        secondsLeft.setHorizontalAlignment(JTextField.CENTER);
        secondsLeft.setText(String.valueOf(seconds));

        button_ins.setBounds(210,220,210,180);
        button_ins.setFont(new Font("Arial", Font.BOLD,30));
        button_ins.setBackground(new Color(63,69,73));
        button_ins.setFocusable(false);
        button_ins.addActionListener(this);
        button_ins.setText("insoluble");

        timeLabel.setBounds(65,0,50,25);
        timeLabel.setBackground(new Color(54,57,62));
        timeLabel.setForeground(Color.WHITE);
        timeLabel.setFont(new Font("Arial",Font.BOLD,17));
        timeLabel.setHorizontalAlignment(JTextField.CENTER);
        timeLabel.setText("timer:");

        reloadButton.setText("\uD83D\uDD04");
        reloadButton.setFont(new Font("Arial",Font.BOLD,27));
        reloadButton.addActionListener(this);
        reloadButton.setHorizontalAlignment(JTextField.CENTER);
        reloadButton.setBounds(10,0,40,40);
        reloadButton.setVisible(true);

        frame.add(reloadButton);
        frame.add(timeLabel);
        frame.add(secondsLeft);
        frame.add(textField);
        frame.add(button_sol);
        frame.add(button_ins);
        frame.add(percentMark);
        frame.add(percentScore);


        frame.setVisible(true);

        nextQuestion();
        timer.start();
    }

    public void nextQuestion() {
        GenerateQuestion();
        textField.setText(compound);
        showResults();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        button_sol.setEnabled(false);
        button_ins.setEnabled(false);
        if (e.getSource()==reloadButton) {
            guess=0;
            correct_questions=0;
            num_answered=-1;
            showResults();
            seconds=60;
            timer.start();

        }
        if(e.getSource()==button_sol) {
            guess = 1;
            if(answer==guess) {
                correct_questions++;
            }
        }
        if(e.getSource()==button_ins) {
            guess = 2;
            if(answer==guess) {
                correct_questions++;
            }
        }
        guess=0;
        secondsLeft.setText(String.valueOf(seconds));
        button_sol.setEnabled(true);
        button_ins.setEnabled(true);
        num_answered++;
        nextQuestion();
    }


    public void showResults() {;
        int percentage = (int)((correct_questions/(double)num_answered)*100);
        percentScore.setText(correct_questions+"/"+num_answered+" = "+percentage+"%");

        frame.add(percentScore);

    }

    public static void GenerateHashMap() {
        for (int i = 0; i < compoundList.length; i++) {
            if (i < 32) {
                CompoundSoluble.put(compoundList[i], 2);
            } else {
                CompoundSoluble.put(compoundList[i], 1);
            }
        }


    }

    public void GenerateQuestion() {
        Random rand = new Random();
        compound = compoundList[rand.nextInt(compoundList.length)];
        answer = CompoundSoluble.get(compound);
    }
}

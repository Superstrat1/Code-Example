import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainPanel {
    private JPanel panel1;
    private JButton button;
    private SplitInput splitForm = new SplitInput();
    private JointInput jointForm = new JointInput();

    private final String splitInputErrorMessage = "Заполните поля используя только буквы латинского или греческого алфавита" +
            "\nПримечание: Отчество указывать необязательно";
    private final String jointInputErrorMessage = "Заполните поле через пробел используя только буквы латинского или греческого алфавита" +
            "\nПримечание: Отчество указывать необязательно";

    private String[] textArr;

    public MainPanel() {
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (panel1.getComponent(1).getName().equals(new SplitInput().getSplitInputForm().getName())){
                    splitInputAction();
                } else {
                    jointInputAction();
                }
            }
        });
    }

    public JPanel getTitlePanel() {
        panel1.add(splitForm.getSplitInputForm(), BorderLayout.CENTER);
        return panel1;
    }

    private void splitInputAction() {
        if (splitForm.inputCheck()) {
            panel1.remove(1);
            textArr = splitForm.getTextArr();
            String inputLine = textArr[0] + " " + textArr[1] + " " + textArr[2];
            jointForm.getTextField1().setText(inputLine.trim());
            panel1.add(jointForm.getJointInputForm(), BorderLayout.CENTER);
            button.setText("Expand");
            panel1.revalidate();
            panel1.repaint();
        } else {
            JOptionPane.showMessageDialog(panel1, splitInputErrorMessage);
        }
    }

    private void jointInputAction() {
        if (jointForm.inputCheck()) {
            panel1.remove(1);
            textArr = jointForm.getTextField1().getText().split("\\s");
            splitForm.getTextField1().setText(textArr[0]);
            splitForm.getTextField2().setText(textArr[1]);
            if (textArr.length == 2) {
                splitForm.getTextField3().setText("");
            } else {
                splitForm.getTextField3().setText(textArr[2]);
            }
            panel1.add(splitForm.getSplitInputForm(), BorderLayout.CENTER);
            button.setText("Collapse");
            panel1.revalidate();
            panel1.repaint();
        } else {
            JOptionPane.showMessageDialog(panel1, jointInputErrorMessage);
        }
    }

}

import javax.swing.*;

public class JointInput {
    private JTextField textField1;
    private JPanel JointInputForm;
    private String[] textArr = new String[3];

    public JPanel getJointInputForm() {
        return JointInputForm;
    }

    public JTextField getTextField1() {
        return textField1;
    }

    public void setTextField1(JTextField textField1) {
        this.textField1 = textField1;
    }

    public boolean inputCheck() {
        textArr = textField1.getText().trim().split("\\s");
        if (textArr.length > 3 || textArr.length < 2 || !arrElementsCheking(textArr)) {
            return false;
        }
        return true;
    }

    private boolean arrElementsCheking(String[] arr) {
        boolean isTrueFormat = true;
        String regex = "[A-Za-zА-Яа-я]+";
        for (String s : arr) {
            if (!s.matches(regex)) {
                isTrueFormat = false;
            }
        }
        return isTrueFormat;
    }
}

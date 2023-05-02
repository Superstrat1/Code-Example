import javax.swing.*;

public class SplitInput {
    private JTextField textField1;
    private JTextField textField2;
    private JPanel SplitInputForm;
    private JTextField textField3;
    private String[] textArr = new String[3];

    public JPanel getSplitInputForm() {
        return SplitInputForm;
    }

    public JTextField getTextField1() {
        return textField1;
    }

    public void setTextField1(JTextField textField1) {
        this.textField1 = textField1;
    }

    public JTextField getTextField2() {
        return textField2;
    }

    public void setTextField2(JTextField textField2) {
        this.textField2 = textField2;
    }

    public JTextField getTextField3() {
        return textField3;
    }

    public void setTextField3(JTextField textField3) {
        this.textField3 = textField3;
    }

    public String[] getTextArr() {
        textArr[0] = textField1.getText().trim();
        textArr[1] = textField2.getText().trim();
        textArr[2] = textField3.getText().trim();
        return textArr;
    }

    public boolean inputCheck() {
        String regex = "[A-Za-zА-Яа-я]+";
        if (textField1.getText().trim().matches(regex)
                && textField2.getText().trim().matches(regex)
                && (textField3.getText().trim().matches(regex) || textField3.getText().trim().matches(""))) {
            return true;
        } else {
            return false;
        }
    }

}

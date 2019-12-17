package BeltLineApplication.java.limiter;

import javafx.scene.control.PasswordField;

public class PasswordFieldLimit extends PasswordField {
    private int maxLength;

    public int getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(int maxLength) {
        this.maxLength = maxLength;
    }

    @Override
    public void replaceText(int start, int end, String text) {
        super.replaceText(start, end, text);
        verify();
    }

    @Override
    public void replaceSelection(String text) {
        super.replaceSelection(text);
        verify();
    }

    private void verify() {
        if (getText().length() > maxLength) {
            setText(getText().substring(0, maxLength));
        }

    }
}

package Container;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class ShowPanel extends JPanel{
    private final JLabel jtext = new JLabel();

    public ShowPanel() {
    }

    {
        add(jtext);
        setPreferredSize(new java.awt.Dimension(200, 200));
        setBackground(java.awt.Color.CYAN);
    }

    public ShowPanel(String text) {
        this.jtext.setText(text);
    }


    public JLabel getJtext() {
        return this.jtext;
    }
    

}

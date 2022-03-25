package Container;

public class DRoundPanel extends javax.swing.JPanel {
    private int radius;

    public DRoundPanel() {
        super();
    }
    
    public DRoundPanel(int radius) {
        this.radius = radius;
    }

    //Initialize instance block, this will apply for all instance create by any constructor
    {
        setOpaque(false);
    }


    public int getRadius() {
        return this.radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    @Override
    protected void paintComponent(java.awt.Graphics g) {
        //Create a coppy and take it by g2d variable
        java.awt.Graphics2D g2d = (java.awt.Graphics2D) g.create();
        //Set rule to draw for g2d - in this way mean we can prevent antialias when drawing
        g2d.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING, java.awt.RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(getBackground());
        g2d.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);
        //This look like mean free memory for g2d
        g2d.dispose();
        //Call parent class
        super.paintComponent(g);
    }
}

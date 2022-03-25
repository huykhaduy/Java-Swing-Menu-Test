import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Container.MenuItem;
import Container.ShowPanel;

import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.MouseEvent;


public class App {
    public static void main(String[] args) throws Exception {
        //Create a new instance of JFrame
        JFrame frame = new JFrame();
        //Set title for JFrame
        frame.setTitle("DRoundPanel");
        //Set size for JFrame
        frame.setSize(500, 500);
        //Set default close operation for JFrame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Set JFrame to center
        frame.setLocationRelativeTo(null);

        frame.setLayout(new BorderLayout());
        //Add panel to JFrame
        ShowPanel showChild1 = new ShowPanel("Đây là nội dung của child 1");
        MenuItem itemChild = new MenuItem();
        itemChild.setShowPanel(showChild1);
        itemChild.getLabel().setText("Child1");
        itemChild.setPreferredSize(new Dimension(100, 40));
        itemChild.setBackground(Color.YELLOW);
        itemChild.setRadius(25);

        ShowPanel showChild2 = new ShowPanel("Đây là nội dung của child 2");
        MenuItem itemChild2 = new MenuItem();
        itemChild2.setShowPanel(showChild2);
        itemChild2.setPreferredSize(new Dimension(100, 40));
        itemChild2.setBackground(Color.YELLOW);
        itemChild2.getLabel().setText("Child2");
        itemChild2.setRadius(25);

        ShowPanel showMenu1 = new ShowPanel("Đây là nội dung của Menu 1");
        MenuItem itemMenu1 = new MenuItem(itemChild, itemChild2);
        itemMenu1.getLabel().setText("Menu 1");
        itemMenu1.setShowPanel(showMenu1);
        // itemMenu1.getLabel().setLocation(0, 0);
        itemMenu1.setPreferredSize(new Dimension(100, 40));
        itemMenu1.setBackground(Color.BLUE);
        itemMenu1.setRadius(25);

        ShowPanel showChild3 = new ShowPanel("Đây là nội dung của child 3");
        MenuItem itemChild3 = new MenuItem();
        itemChild3.setShowPanel(showChild3);
        itemChild3.getLabel().setText("Child3");
        itemChild3.setPreferredSize(new Dimension(100, 40));
        itemChild3.setBackground(Color.YELLOW);
        itemChild3.setRadius(25);

        ShowPanel showChild4 = new ShowPanel("Đây là nội dung của child 4");
        MenuItem itemChild4 = new MenuItem();
        itemChild4.setShowPanel(showChild4);
        itemChild4.getLabel().setText("Child4");
        itemChild4.setPreferredSize(new Dimension(100, 40));
        itemChild4.setBackground(Color.YELLOW);
        itemChild4.setRadius(25);

        ShowPanel showMenu2 = new ShowPanel("Đây là nội dung của Menu 2");
        MenuItem itemMenu2 = new MenuItem(itemChild3, itemChild4);
        itemMenu2.getLabel().setText("Menu 2");
        itemMenu2.setShowPanel(showMenu2);
        // itemMenu2.getLabel().setLocation(0, 0);
        itemMenu2.setPreferredSize(new Dimension(100, 40));
        itemMenu2.setBackground(Color.BLUE);
        itemMenu2.setRadius(25);

        JPanel jp = new JPanel();
        jp.setLayout(new FlowLayout());
        jp.setBackground(Color.GREEN);
        jp.setPreferredSize(new Dimension(100, 500));
        frame.add(jp, BorderLayout.WEST);

        jp.add(itemMenu1);
        itemMenu1.addSubItemTo(jp);
        jp.add(itemMenu2);
        itemMenu2.addSubItemTo(jp);

        //Frame ở giữa để show nội dung

        JPanel jp2 = new JPanel();
        frame.add(jp2, BorderLayout.CENTER);
        // jp2.setPreferredSize(new Dimension(300, 300));

        itemMenu1.addShowItemsTo(jp2);
        itemMenu2.addShowItemsTo(jp2);

        // itemMenu1.add(new JLabel("Hello wrold"));
       
        // System.out.println("Number of components: " + itemMenu1.getComponentCount());
        // jp.addMouseListener(new java.awt.event.MouseAdapter() {
        //     @Override
        //     public void mousePressed(MouseEvent e) {
        //         // TODO Auto-generated method stub
        //         System.out.println("Mouse Pressed");

        //         super.mousePressed(e);
        //     }
        // });

        


        //Show JFrame
        frame.setVisible(true);
    }
}

class MousePressedUpdate extends java.awt.event.MouseAdapter{
    private JPanel panel;
    //constructor
    public MousePressedUpdate(JPanel panel) {
        this.panel = panel;
    }

    //set getLabel
    public void setPanel(JPanel panel) {
        this.panel = panel;
    }

    public JPanel getPanel() {
        return panel;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
        System.out.println("Mouse Pressed");

        super.mousePressed(e);
    }

}

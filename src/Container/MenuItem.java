package Container;

import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

// import java.awt.FlowLayout;
import java.awt.CardLayout;

public class MenuItem extends DRoundPanel {
    private List<MenuItem> items;
    private ActionListener actionListener;
    private boolean isShowing;
    private final ImageIcon icon = new ImageIcon();
    private final JLabel label = new JLabel();
    private JPanel showPanel;
    private JPanel whereToShow;
    private MenuItem menuParent;


    public MenuItem() {
        super();
    }

    public MenuItem(int radius) {
        super(radius);
    }

    public MenuItem(List<MenuItem> items) {
        this.items = items;
        reLoadItems();
    }

    public MenuItem(MenuItem... items) {
        this.items = new ArrayList<MenuItem>();
        this.items = java.util.Arrays.asList(items);
        reLoadItems();
    }

    public ImageIcon getIcon() {
        return this.icon;
    }

    public JLabel getLabel() {
        return this.label;
    }

    {
        // setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        setLayout(new CardLayout());
        this.add(label);
        // this.revalidate();
        // this.repaint();
        this.label.setHorizontalAlignment(JLabel.CENTER);
        this.label.setVerticalAlignment(JLabel.CENTER);

        addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                // System.out.println("Clicked");
                if (isShowing) {
                    hideItems();
                } else {
                    showItems();
                }

                if (actionListener != null) {
                    actionListener.actionPerformed(null);
                }
            }
        });

        // add(icon.getImage());
       
        
    }


    public List<MenuItem> getItems() {
        return items;
    }

    public void setItems(List<MenuItem> items) {
        this.items = items;
        reLoadItems();
    }

    public boolean isShowing() {
        return this.isShowing;
    }

    public void setShowing(boolean isShowing) {
        this.isShowing = isShowing;
    }


    public ActionListener getActionListener() {
        return this.actionListener;
    }

    public void setActionListener(ActionListener actionListener) {
        this.actionListener = actionListener;
    }

    public JPanel getShowPanel() {
        return this.showPanel;
    }

    public void setShowPanel(JPanel showPanel) {
        this.showPanel = showPanel;
    }

    public void addSubItemTo(JPanel frame) {
        if (this.items == null) {
            return;
        }
        for (int i = 0; i < items.size(); i++) {
            frame.add(items.get(i));
            frame.revalidate();
            if (items.get(i).getItems() != null)
                items.get(i).addSubItemTo(frame);
        }

    }

    public MenuItem getActiveItem() {
        if (isShowing == true)
            return this;
        for (MenuItem item : items) {
            item.getActiveItem();
        }
        return null;
    }

    public JPanel getWhereToShow() {
        return whereToShow;
    }

    public void setWhereToShow(JPanel whereToShow) {
        this.whereToShow = whereToShow;
    }

    public void addShowItemsTo(JPanel whereToShow) {
        if (this.whereToShow == null) {
            this.whereToShow = whereToShow;
        }
        if (this.items == null) {
            return;
        }
        for (MenuItem item : this.items) {
            item.addShowItemsTo(whereToShow);
        }
    }
    
    protected void reLoadItems() {
        if (items != null) {
            for (int i = 0; i < items.size(); i++) {
                // this.add(items.get(i));
                items.get(i).setVisible(false);
                items.get(i).menuParent = this;
            }
            this.revalidate();
        }
    }

    protected void showItems() {
        if (items != null) {
            for (int i = 0; i < items.size(); i++) {
                items.get(i).setVisible(true);
            }
            isShowing = true;
            repaint();
            revalidate();
            if (getParent() != null) {
                getParent().repaint();
                getParent().revalidate();
            }
        }
        if (whereToShow != null) {
            whereToShow.removeAll();
            if (this.showPanel != null)
                whereToShow.add(this.showPanel);
            whereToShow.revalidate();
            whereToShow.repaint();
        }
        
    }

    protected void hideItems() {
        if (items != null) {
            for (int i = 0; i < items.size(); i++) {
                items.get(i).setVisible(false);
                items.get(i).hideItems();
            }
            isShowing = false;
            getParent().repaint();
            getParent().revalidate();
        }
        if (menuParent != null && whereToShow != null) {
            whereToShow.removeAll();
            whereToShow.add(menuParent.getShowPanel());
            whereToShow.revalidate();
            whereToShow.repaint();
        }
    }


}

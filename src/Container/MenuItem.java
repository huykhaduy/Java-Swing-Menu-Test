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
    //Lưu danh sách con
    private List<MenuItem> items;

    //Hàm thực hiện chức năng khi được click, do người dùng truyền vào
    private ActionListener actionListener;

    //Hàm kiểm tra xem form đang hiện hoặc không hiện
    private boolean isShowing;

    //Icon của item
    private final ImageIcon icon = new ImageIcon();

    //Text hiện ra của item
    private final JLabel label = new JLabel();

    //Panel chứa nội dung khi click vào item
    private JPanel showPanel;

    //Lưu pannel nơi để hiện ra nội dung của pannel trên
    private JPanel whereToShow;

    //Lưu item cha (nếu có) để truy cập về sau
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


    //Instance initalize block
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

    //Hàm thực hiện thêm menu item vào panel
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

    //Lấy menu item đang active
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

    //Thêm panel hiện nội dung khi click vào menuitem (cho cả các thành phần con)
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
    
    //Reload lại menu item nếu thay đổi danh sách con
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

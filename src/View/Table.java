package View;

import Database.Login;

import javax.swing.*;

/**
 * @author serum
 * @create 2022/1/2 22:52
 */
public class Table extends JFrame {
    // 显示界面
    private JTable t1;
    private JScrollPane s;
    // 背景
    private JLabel l;
    Object[][] tableDate;
    String[] name;

    public Table(String[] name,Object[][] tableDate) {
        // 设置登录窗口标题
        this.setTitle("查询结果");
        // 窗体组件初始化
        init(name,tableDate);
        // 设置后台退出
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // 设置布局为绝对定位
        this.setLayout(null);
        this.setBounds(0, 0, 810, 810);
        // 窗体大小不能改变
        this.setResizable(false);
        // 居中显示
        this.setLocationRelativeTo(null);
        // 窗体显示
        this.setVisible(true);

    }

    public void init(String[] name,Object[][] tableDate) {
        // 将panel设置为frame的内容面板
        JPanel panel = new JPanel();
        this.setContentPane(panel);
        // 设置背景色
        l = new JLabel();
        l.setBounds(0, 0, 810, 810);
        // 显示界面
        this.name = name;
        this.tableDate = tableDate;
        t1 = new JTable(tableDate,name);
        s = new JScrollPane(t1, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        s.setBounds(0,0,800,800);

        panel.add(s);
        panel.add(l);
    }
}

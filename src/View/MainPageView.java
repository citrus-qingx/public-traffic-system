package View;

import Database.Login;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

/**
 * @author serum
 * @create 2022/1/2 12:59
 */
public class MainPageView extends JFrame{
    // 背景
    private JLabel l;
    // 查询信息
    private JButton b1;
    // 录入信息
    private JButton b2;
    // 数据库信息
    Login m;

    public MainPageView(Login m) {
        this.m = m;
        // 设置登录窗口标题
        this.setTitle("公交系统");
        // 窗体组件初始化
        init();
        // 设置后台退出
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // 设置布局为绝对定位
        this.setLayout(null);
        this.setBounds(0, 0, 355, 265);
        // 窗体大小不能改变
        this.setResizable(false);
        // 居中显示
        this.setLocationRelativeTo(null);
        // 窗体显示
        this.setVisible(true);

        b1.addActionListener(new searchlistener());
        b2.addActionListener(new inputlistener());

    }

    // 窗体组件初始化
    public void init() {
        // 将panel设置为frame的内容面板
        JPanel panel = new JPanel();
        this.setContentPane(panel);
        // 设置背景色
        l = new JLabel();
        l.setBounds(0, 0, 355, 265);
        // 查询信息
        b1 = new JButton("查询信息");
        b1.setFont(new Font("楷体", Font.BOLD, 14));
        b1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        b1.setBounds(120, 70, 100, 30);
        // 录入信息
        b2 = new JButton("录入信息");
        b2.setFont(new Font("楷体", Font.BOLD, 14));
        b2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        b2.setBounds(120, 140, 100, 30);

        l.add(b1);
        l.add(b2);
        panel.add(l);

    }

    private class searchlistener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            SearchView s =new SearchView(m);
            s.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        }
    }

    private class inputlistener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            InputView i = new InputView(m);
            i.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        }
    }

    public static void main(String[] args) {
    }
}

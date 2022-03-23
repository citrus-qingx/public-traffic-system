package View;

import Database.Login;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @author serum
 * @create 2022/1/2 13:14
 */
public class SearchView extends JFrame{
    // 背景
    private JLabel l;
    // 司机信息
    private JButton b1;
    // 违章信息
    private JButton b3;

    Login m;

    public SearchView(Login m) {
        this.m = m;
        // 设置登录窗口标题
        this.setTitle("查询信息");
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

        b1.addActionListener(new SearchView.driverlistener());
        b3.addActionListener(new SearchView.breakruleslistener());

    }

    // 窗体组件初始化
    public void init() {
        // 将panel设置为frame的内容面板
        JPanel panel = new JPanel();
        this.setContentPane(panel);
        // 设置背景色
        l = new JLabel();
        l.setBounds(0, 0, 355, 265);
        // 司机信息
        b1 = new JButton("司机信息");
        b1.setFont(new Font("楷体", Font.BOLD, 14));
        b1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        b1.setBounds(120, 70, 100, 30);
        // 违章信息
        b3 = new JButton("违章信息");
        b3.setFont(new Font("楷体", Font.BOLD, 14));
        b3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        b3.setBounds(120, 140, 100, 30);

        l.add(b1);
        l.add(b3);
        panel.add(l);

    }

    private class driverlistener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            SearchDriverView sd = new SearchDriverView(m);
            sd.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        }
    }

    private class breakruleslistener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            SearchBreakRulesView sbr = new SearchBreakRulesView(m);
            sbr.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        }
    }

    public static void main(String[] args) {
    }
}

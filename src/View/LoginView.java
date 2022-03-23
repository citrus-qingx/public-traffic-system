package View;

import Database.Login;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author serum
 * @create 2022/1/2 11:19
 */
public class LoginView extends JFrame {

    private static final long serialVersionUID = -7433459675363575266L;

    // 背景
    private JLabel l;
    // 账号
    private JLabel l1;
    private JTextField lo_number;
    // 密码
    private JLabel l2;
    private JPasswordField lo_passwd;
    // 登陆按钮
    private JButton b1;

    public LoginView() {
        // 设置登录窗口标题
        this.setTitle("公交系统_登陆");
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

        b1.addActionListener(new loginlistener());

    }

    // 窗体组件初始化
    public void init() {
        // 将panel设置为frame的内容面板
        JPanel panel = new JPanel();
        this.setContentPane(panel);
        // 设置背景色
        l = new JLabel();
        l.setBounds(0, 0, 355, 265);
        // 账号
        l1 = new JLabel("账号");
        l1.setFont(new Font("楷体", Font.BOLD, 14));
        l1.setBounds(60, 70, 70, 20);
        // 账号输入框
        lo_number = new JTextField();
        lo_number.setBounds(110, 70, 150, 20);
        // 密码
        l2 = new JLabel("密码");
        l2.setFont(new Font("楷体", Font.BOLD, 14));
        l2.setBounds(60, 110, 70, 20);
        // 密码输入框
        lo_passwd = new JPasswordField();
        lo_passwd.setBounds(110, 110, 150, 20);
        // 登陆按钮
        b1 = new JButton("登录");
        b1.setFont(new Font("楷体", Font.BOLD, 14));
        b1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        b1.setBounds(140, 160, 65, 30);

        l.add(l1);
        l.add(l2);
        l.add(b1);
        panel.add(lo_number);
        panel.add(lo_passwd);
        panel.add(l);

    }

    private class loginlistener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String number = lo_number.getText();
            String password = lo_passwd.getText();
            Login m = null;
            try {
                m = new Login(number,password);
                new MainPageView(m);
                dispose();
            } catch (Exception se) {
                // 处理 JDBC 错误
                se.printStackTrace();
                System.out.println("用户名密码错误！");
                JOptionPane.showConfirmDialog(null, "用户名密码有误！");
            }

        }
    }


    public static void main(String[] args) {
        new LoginView();
    }

}


package View;

import Database.Login;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * @author serum
 * @create 2022/1/2 13:34
 */
public class InputDriverView extends JFrame{
    // 背景
    private JLabel l;
    // 工号
    private JLabel l1;
    private JTextField lo_gonghao;
    // 姓名
    private JLabel l2;
    private JTextField lo_name;
    // 性别
    private JLabel l3;
    private JTextField lo_gender;
    // 出生年份
    private JLabel l4;
    private JTextField lo_birth;
    // 职位
    private JLabel l5;
    private JTextField lo_zhiwei;
    // 线路号
    private JLabel l6;
    private JTextField lo_xianlu;
    // 提交按钮
    private JButton b1;

    Login m;

    public InputDriverView(Login m) {
        this.m = m;
        // 设置登录窗口标题
        this.setTitle("录入司机信息");
        // 窗体组件初始化
        init();
        // 设置后台退出
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // 设置布局为绝对定位
        this.setLayout(null);
        this.setBounds(0, 0, 600, 500);
        // 窗体大小不能改变
        this.setResizable(false);
        // 居中显示
        this.setLocationRelativeTo(null);
        // 窗体显示
        this.setVisible(true);

        b1.addActionListener(new inputDriverListener());

    }

    // 窗体组件初始化
    public void init() {
        // 将panel设置为frame的内容面板
        JPanel panel = new JPanel();
        this.setContentPane(panel);
        // 设置背景色
        l = new JLabel();
        l.setBounds(0, 0, 600, 700);
        // 工号
        l1 = new JLabel("工号");
        l1.setFont(new Font("楷体", Font.BOLD, 14));
        l1.setBounds(165, 70, 70, 20);
        // 工号输入框
        lo_gonghao = new JTextField();
        lo_gonghao.setBounds(250, 70, 150, 20);
        // 姓名
        l2 = new JLabel("姓名");
        l2.setFont(new Font("楷体", Font.BOLD, 14));
        l2.setBounds(165, 120, 70, 20);
        // 姓名输入框
        lo_name = new JTextField();
        lo_name.setBounds(250, 120, 150, 20);
        // 性别
        l3 = new JLabel("性别");
        l3.setFont(new Font("楷体", Font.BOLD, 14));
        l3.setBounds(165, 170, 70, 20);
        // 性别输入框
        lo_gender = new JTextField();
        lo_gender.setBounds(250, 170, 150, 20);
        // 出生年份
        l4 = new JLabel("出生年份");
        l4.setFont(new Font("楷体", Font.BOLD, 14));
        l4.setBounds(150, 220, 70, 20);
        // 出生年份输入框
        lo_birth = new JTextField();
        lo_birth.setBounds(250, 220, 150, 20);
        // 职位
        l5 = new JLabel("职位");
        l5.setFont(new Font("楷体", Font.BOLD, 14));
        l5.setBounds(165, 270, 70, 20);
        // 职位输入框
        lo_zhiwei = new JTextField();
        lo_zhiwei.setBounds(250, 270, 150, 20);
        // 线路号
        l6 = new JLabel("线路号");
        l6.setFont(new Font("楷体", Font.BOLD, 14));
        l6.setBounds(160, 320, 70, 20);
        // 线路号输入框
        lo_xianlu = new JTextField();
        lo_xianlu.setBounds(250, 320, 150, 20);
        // 提交按钮
        b1 = new JButton("录入");
        b1.setFont(new Font("楷体", Font.BOLD, 14));
        b1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        b1.setBounds(220, 370, 100, 30);

        l.add(l1);
        l.add(l2);
        l.add(l3);
        l.add(l4);
        l.add(l5);
        l.add(l6);
        l.add(b1);
        panel.add(lo_gonghao);
        panel.add(lo_name);
        panel.add(lo_gender);
        panel.add(lo_birth);
        panel.add(lo_zhiwei);
        panel.add(lo_xianlu);
        panel.add(l);

    }

    private class inputDriverListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String gonghao = lo_gonghao.getText();
            String name = lo_name.getText();
            String gender = lo_gender.getText();
            String birth = lo_birth.getText();
            String zhiwei = lo_zhiwei.getText();
            int xianlu = Integer.parseInt(lo_xianlu.getText());
            try{
                boolean b = false;
                ResultSet rs = m.searchMysql("SELECT 线路号 FROM 队长_司机表;");
                while(rs.next()){
                    int xl = Integer.parseInt(rs.getString("线路号"));
                    if(xl == xianlu){
                        b = true;
                        break;
                    }
                }
                if(b == false){
                    throw new Exception();
                }

                String sql = "INSERT INTO 司机表 VALUES (?,?,?,?,?);";
                PreparedStatement p = (PreparedStatement) m.conn.prepareStatement(sql);
                p.setString(1,gonghao);
                p.setString(2,name);
                p.setString(3,gender);
                p.setString(4,birth);
                p.setString(5,zhiwei);
                p.executeUpdate();
                String sql2 = "INSERT INTO 司机线路表 VALUES (?,?);";
                PreparedStatement p2 = (PreparedStatement) m.conn.prepareStatement(sql2);
                p2.setString(1,gonghao);
                p2.setInt(2,xianlu);
                p2.executeUpdate();
                System.out.println("录入成功！");
                JOptionPane.showConfirmDialog(null, "录入成功！");
            }catch(Exception e1){
                System.out.println("您没有权限或输入不规范！");
                JOptionPane.showConfirmDialog(null, "您没有权限或输入不规范！");
                e1.printStackTrace();
            }


        }
    }

    public static void main(String[] args) {
    }
}

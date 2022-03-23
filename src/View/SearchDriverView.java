package View;

import Database.Login;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * @author serum
 * @create 2022/1/2 13:32
 */
public class SearchDriverView extends JFrame{
    // 背景
    private JLabel l;
    // 车队按钮
    private JButton b1;
    // 线路按钮
    private JButton b2;
    // 司机按钮
    private JButton b3;

    String[] name = {"工号","姓名","性别","出生年份","职位","线路号"};

    Login m;
    public SearchDriverView(Login m) {
        this.m = m;
        // 设置登录窗口标题
        this.setTitle("查询司机信息");
        // 窗体组件初始化
        init();
        // 设置后台退出
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // 设置布局为绝对定位
        this.setLayout(null);
        this.setBounds(0, 0, 600, 300);
        // 窗体大小不能改变
        this.setResizable(false);
        // 居中显示
        this.setLocationRelativeTo(null);
        // 窗体显示
        this.setVisible(true);

        b1.addActionListener(new searchCheduiListener());
        b2.addActionListener(new searchXianluListener());
        b3.addActionListener(new searchDriverListener());

    }

    // 窗体组件初始化
    public void init() {
        // 将panel设置为frame的内容面板
        JPanel panel = new JPanel();
        this.setContentPane(panel);
        // 设置背景色
        l = new JLabel();
        l.setBounds(0, 0, 600, 200);
        // 按车队搜索
        b1 = new JButton("队长按车队搜索");
        b1.setFont(new Font("楷体", Font.BOLD, 14));
        b1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        b1.setBounds(60, 100, 140, 30);
        // 按线路搜索
        b2 = new JButton("路队长按线路搜索");
        b2.setFont(new Font("楷体", Font.BOLD, 14));
        b2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        b2.setBounds(210, 100, 180, 30);
        // 按司机搜索
        b3 = new JButton("司机按工号搜索");
        b3.setFont(new Font("楷体", Font.BOLD, 14));
        b3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        b3.setBounds(400, 100, 140, 30);

        l.add(b1);
        l.add(b2);
        l.add(b3);
        panel.add(l);

    }

    private class searchDriverListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try{
                ResultSet rs = m.searchMysql("SELECT * FROM 司机_司机表;");
                ArrayList<Object[]> data = new ArrayList<Object[]>();
                while(rs.next()) {
//                匹配属性类型，从 rs 中取结果
                    Object[] temp = {rs.getString("工号"),
                            rs.getString("姓名"),
                            rs.getString("性别"),
                            rs.getInt("出生年份"),
                            rs.getString("职位"),
                            rs.getInt("线路号")};
                    data.add(temp);
                }
                Object[][] tableDate = new Object[data.size()][6];
                data.toArray(tableDate);
                Table i = new Table(name,tableDate);
                i.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            }catch(Exception e1){
                System.out.println("您没有权限！");
                JOptionPane.showConfirmDialog(null, "您没有权限！");
                e1.printStackTrace();
            }

        }
    }

    private class searchCheduiListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try{
                ResultSet rs = m.searchMysql("SELECT * FROM 队长_司机表;");
                ArrayList<Object[]> data = new ArrayList<Object[]>();
                while(rs.next()) {
//                匹配属性类型，从 rs 中取结果
                    Object[] temp = {rs.getString("工号"),
                            rs.getString("姓名"),
                            rs.getString("性别"),
                            rs.getInt("出生年份"),
                            rs.getString("职位"),
                            rs.getInt("线路号")};
                    data.add(temp);
                }
                Object[][] tableDate = new Object[data.size()][6];
                data.toArray(tableDate);
                Table i = new Table(name,tableDate);
                i.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            }catch(Exception e1){
                System.out.println("您没有权限！");
                JOptionPane.showConfirmDialog(null, "您没有权限！");
                e1.printStackTrace();
            }

        }
    }

    private class searchXianluListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try{
                ResultSet rs = m.searchMysql("SELECT * FROM 路队长_司机表;");
                ArrayList<Object[]> data = new ArrayList<Object[]>();
                while(rs.next()) {
//                匹配属性类型，从 rs 中取结果
                    Object[] temp = {rs.getString("工号"),
                            rs.getString("姓名"),
                            rs.getString("性别"),
                            rs.getInt("出生年份"),
                            rs.getString("职位"),
                            rs.getInt("线路号")};
                    data.add(temp);
                }
                Object[][] tableDate = new Object[data.size()][6];
                data.toArray(tableDate);
                Table i = new Table(name,tableDate);
                i.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            }catch(Exception e1){
                System.out.println("您没有权限！");
                JOptionPane.showConfirmDialog(null, "您没有权限！");
                e1.printStackTrace();
            }

        }
    }


    public static void main(String[] args) {

    }
}

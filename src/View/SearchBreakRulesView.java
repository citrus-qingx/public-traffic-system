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
 * @create 2022/1/2 13:34
 */
public class SearchBreakRulesView extends JFrame{
    // 背景
    private JLabel l;
    // 开始时间
    private JLabel l1;
    private JTextField lo_start;
    // 结束时间
    private JLabel l2;
    private JTextField lo_end;
    // 车队按钮
    private JButton b1;
    // 车队统计按钮
    private JButton b3;
    // 司机按钮
    private JButton b2;
    // 司机统计按钮
    private JButton b4;
    // 线路按钮
    private JButton b5;
    // 线路统计按钮
    private JButton b6;
    Login m;

    public SearchBreakRulesView(Login m) {
        this.m = m;
        // 设置登录窗口标题
        this.setTitle("查询违章信息");
        // 窗体组件初始化
        init();
        // 设置后台退出
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // 设置布局为绝对定位
        this.setLayout(null);
        this.setBounds(0, 0, 600, 700);
        // 窗体大小不能改变
        this.setResizable(false);
        // 居中显示
        this.setLocationRelativeTo(null);
        // 窗体显示
        this.setVisible(true);

        b1.addActionListener(new searchCheduiListener());
        b2.addActionListener(new searchDriverListener());
        b3.addActionListener(new searchCheduiAllListener());
        b4.addActionListener(new searchDriverAllListener());
        b5.addActionListener(new searchXianluListener());
        b6.addActionListener(new searchXianluAllListener());

    }

    // 窗体组件初始化
    public void init() {
        // 将panel设置为frame的内容面板
        JPanel panel = new JPanel();
        this.setContentPane(panel);
        // 设置背景色
        l = new JLabel();
        l.setBounds(0, 0, 600, 700);
        // 开始时间
        l1 = new JLabel("开始时间");
        l1.setFont(new Font("楷体", Font.BOLD, 14));
        l1.setBounds(180, 40, 70, 20);
        // 开始时间输入框
        lo_start = new JTextField();
        lo_start.setBounds(270, 40, 150, 20);
        // 结束时间
        l2 = new JLabel("结束时间");
        l2.setFont(new Font("楷体", Font.BOLD, 14));
        l2.setBounds(180, 80, 70, 20);
        // 结束时间输入框
        lo_end = new JTextField();
        lo_end.setBounds(270, 80, 150, 20);
        // 按车队搜索
        b1 = new JButton("队长按车队搜索");
        b1.setFont(new Font("楷体", Font.BOLD, 14));
        b1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        b1.setBounds(130, 120, 140, 30);
        // 按司机搜索
        b2 = new JButton("司机按工号搜索");
        b2.setFont(new Font("楷体", Font.BOLD, 14));
        b2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        b2.setBounds(130, 200, 140, 30);
        // 按线路搜索
        b5 = new JButton("路队长按线路搜索");
        b5.setFont(new Font("楷体", Font.BOLD, 14));
        b5.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        b5.setBounds(120, 160, 160, 30);
        // 车队统计
        b3 = new JButton("车队统计");
        b3.setFont(new Font("楷体", Font.BOLD, 14));
        b3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        b3.setBounds(330, 120, 140, 30);
        // 司机统计
        b4 = new JButton("司机统计");
        b4.setFont(new Font("楷体", Font.BOLD, 14));
        b4.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        b4.setBounds(330, 200, 140, 30);
        // 线路统计
        b6 = new JButton("线路统计");
        b6.setFont(new Font("楷体", Font.BOLD, 14));
        b6.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        b6.setBounds(330, 160, 140, 30);

        l.add(l1);
        l.add(l2);
        l.add(b1);
        l.add(b2);
        l.add(b3);
        l.add(b4);
        l.add(b5);
        l.add(b6);
        panel.add(lo_start);
        panel.add(lo_end);
        panel.add(l);

    }

    private class searchDriverListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String t1 = lo_start.getText();
            String t2 = lo_end.getText();
            try{
                ResultSet rs = m.searchMysql("SELECT * FROM 司机_违章记录表 " +
                        "WHERE 违章时间 BETWEEN '" + t1 + "' AND '" + t2 + "';");
                ArrayList<Object[]> data = new ArrayList<Object[]>();
                while(rs.next()) {
//                匹配属性类型，从 rs 中取结果
                    Object[] temp = {rs.getInt("违章编号"),
                            rs.getString("违章司机工号"),
                            rs.getString("违章汽车车牌号"),
                            rs.getString("违章名"),
                            rs.getString("违章时间"),
                            rs.getString("违章地点")};
                    data.add(temp);
                }
                Object[][] tableDate = new Object[data.size()][6];
                data.toArray(tableDate);
                String[] name = {"违章编号","违章司机工号","违章汽车车牌号","违章名","违章时间","违章地点"};
                Table i = new Table(name,tableDate);
                i.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            }catch(Exception e1){
                System.out.println("您没有权限！");
                JOptionPane.showConfirmDialog(null, "您没有权限！");
                e1.printStackTrace();
            }

        }
    }

    private class searchDriverAllListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String t1 = lo_start.getText();
            String t2 = lo_end.getText();
            try{
                ResultSet rs = m.searchMysql("SELECT 违章名,COUNT(违章名) " +
                        "FROM 司机_违章记录表 WHERE 违章时间 BETWEEN '"
                        + t1 + "' AND '" + t2 + "' GROUP BY 违章名;");
                ArrayList<Object[]> data = new ArrayList<Object[]>();
                while(rs.next()) {
//                匹配属性类型，从 rs 中取结果
                    Object[] temp = {rs.getString("违章名"),
                            rs.getInt("COUNT(违章名)")};
                    data.add(temp);
                }
                Object[][] tableDate = new Object[data.size()][2];
                data.toArray(tableDate);
                String[] name = {"违章名","违章次数"};
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
            String t1 = lo_start.getText();
            String t2 = lo_end.getText();
            try{
                ResultSet rs = m.searchMysql("SELECT * FROM 队长_违章记录表 " +
                        "WHERE 违章时间 BETWEEN '" + t1 + "' AND '" + t2 + "';");
                ArrayList<Object[]> data = new ArrayList<Object[]>();
                while(rs.next()) {
//                匹配属性类型，从 rs 中取结果
                    Object[] temp = {rs.getInt("违章编号"),
                            rs.getString("违章司机工号"),
                            rs.getString("违章汽车车牌号"),
                            rs.getString("违章名"),
                            rs.getString("违章时间"),
                            rs.getString("违章地点")};
                    data.add(temp);
                }
                Object[][] tableDate = new Object[data.size()][6];
                data.toArray(tableDate);
                String[] name = {"违章编号","违章司机工号","违章汽车车牌号","违章名","违章时间","违章地点"};
                Table i = new Table(name,tableDate);
                i.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            }catch(Exception e1){
                System.out.println("您没有权限！");
                JOptionPane.showConfirmDialog(null, "您没有权限！");
                e1.printStackTrace();
            }

        }
    }

    private class searchCheduiAllListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String t1 = lo_start.getText();
            String t2 = lo_end.getText();
            try{
                ResultSet rs = m.searchMysql("SELECT 违章名,COUNT(违章名) " +
                        "FROM 队长_违章记录表 WHERE 违章时间 BETWEEN '"
                        + t1 + "' AND '" + t2 + "' GROUP BY 违章名;");
                ArrayList<Object[]> data = new ArrayList<Object[]>();
                while(rs.next()) {
//                匹配属性类型，从 rs 中取结果
                    Object[] temp = {rs.getString("违章名"),
                            rs.getInt("COUNT(违章名)")};
                    data.add(temp);
                }
                Object[][] tableDate = new Object[data.size()][2];
                data.toArray(tableDate);
                String[] name = {"违章名","违章次数"};
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
            String t1 = lo_start.getText();
            String t2 = lo_end.getText();
            try{
                ResultSet rs = m.searchMysql("SELECT * FROM 路队长_违章记录表 WHERE 违章时间 BETWEEN '" + t1 + "' AND '" + t2 + "';");
                ArrayList<Object[]> data = new ArrayList<Object[]>();
                while(rs.next()) {
//                匹配属性类型，从 rs 中取结果
                    Object[] temp = {rs.getInt("违章编号"),
                            rs.getString("违章司机工号"),
                            rs.getString("违章汽车车牌号"),
                            rs.getString("违章名"),
                            rs.getString("违章时间"),
                            rs.getString("违章地点")};
                    data.add(temp);
                }
                Object[][] tableDate = new Object[data.size()][6];
                data.toArray(tableDate);
                String[] name = {"违章编号","违章司机工号","违章汽车车牌号","违章名","违章时间","违章地点"};
                Table i = new Table(name,tableDate);
                i.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            }catch(Exception e1){
                System.out.println("您没有权限！");
                JOptionPane.showConfirmDialog(null, "您没有权限！");
                e1.printStackTrace();
            }

        }
    }

    private class searchXianluAllListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String t1 = lo_start.getText();
            String t2 = lo_end.getText();
            try{
                ResultSet rs = m.searchMysql("SELECT 违章名,COUNT(违章名) FROM 路队长_违章记录表 WHERE 违章时间 BETWEEN '" + t1 + "' AND '" + t2 + "' GROUP BY 违章名;");
                ArrayList<Object[]> data = new ArrayList<Object[]>();
                while(rs.next()) {
//                匹配属性类型，从 rs 中取结果
                    Object[] temp = {rs.getString("违章名"),
                            rs.getInt("COUNT(违章名)")};
                    data.add(temp);
                }
                Object[][] tableDate = new Object[data.size()][2];
                data.toArray(tableDate);
                String[] name = {"违章名","违章次数"};
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

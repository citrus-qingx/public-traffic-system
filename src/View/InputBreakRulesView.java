package View;

import Database.Login;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author serum
 * @create 2022/1/2 13:34
 */
public class InputBreakRulesView extends JFrame{
    // 背景
    private JLabel l;
    // 违章编号
    private JLabel l1;
    private JTextField lo_bianhao;
    // 违章司机工号
    private JLabel l2;
    private JTextField lo_gonghao;
    // 违章汽车车牌号
    private JLabel l3;
    private JTextField lo_chepai;
    // 违章名
    private JLabel l4;
    private JTextField lo_name;
    // 违章时间
    private JLabel l5;
    private JTextField lo_time;
    // 违章地点
    private JLabel l6;
    private JTextField lo_didian;
    // 提交按钮
    private JButton b1;

    Login m;

    public InputBreakRulesView(Login m) {
        this.m = m;
        // 设置登录窗口标题
        this.setTitle("录入违章记录");
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

        b1.addActionListener(new inputBreakRulesListener());

    }

    // 窗体组件初始化
    public void init() {
        // 将panel设置为frame的内容面板
        JPanel panel = new JPanel();
        this.setContentPane(panel);
        // 设置背景色
        l = new JLabel();
        l.setBounds(0, 0, 600, 700);
        // 违章编号
        l1 = new JLabel("违章编号");
        l1.setFont(new Font("楷体", Font.BOLD, 14));
        l1.setBounds(160, 70, 70, 20);
        // 违章编号输入框
        lo_bianhao = new JTextField();
        lo_bianhao.setBounds(250, 70, 150, 20);
        // 违章司机工号
        l2 = new JLabel("违章司机工号");
        l2.setFont(new Font("楷体", Font.BOLD, 14));
        l2.setBounds(140, 120, 100, 20);
        // 违章司机工号输入框
        lo_gonghao = new JTextField();
        lo_gonghao.setBounds(250, 120, 150, 20);
        // 违章汽车车牌号
        l3 = new JLabel("违章汽车车牌号");
        l3.setFont(new Font("楷体", Font.BOLD, 14));
        l3.setBounds(135, 170, 120, 20);
        // 违章汽车车牌号输入框
        lo_chepai = new JTextField();
        lo_chepai.setBounds(250, 170, 150, 20);
        // 违章名
        l4 = new JLabel("违章名");
        l4.setFont(new Font("楷体", Font.BOLD, 14));
        l4.setBounds(170, 220, 70, 20);
        // 违章名输入框
        lo_name = new JTextField();
        lo_name.setBounds(250, 220, 150, 20);
        // 违章时间
        l5 = new JLabel("违章时间");
        l5.setFont(new Font("楷体", Font.BOLD, 14));
        l5.setBounds(160, 270, 70, 20);
        // 违章时间输入框
        lo_time = new JTextField();
        lo_time.setBounds(250, 270, 150, 20);
        // 违章地点
        l6 = new JLabel("违章地点");
        l6.setFont(new Font("楷体", Font.BOLD, 14));
        l6.setBounds(160, 320, 70, 20);
        // 违章地点输入框
        lo_didian = new JTextField();
        lo_didian.setBounds(250, 320, 150, 20);
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
        panel.add(lo_bianhao);
        panel.add(lo_gonghao);
        panel.add(lo_chepai);
        panel.add(lo_name);
        panel.add(lo_time);
        panel.add(lo_didian);
        panel.add(l);

    }

    private class inputBreakRulesListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String bianhao = lo_bianhao.getText();
            String gonghao = lo_gonghao.getText();
            String chepai = lo_chepai.getText();
            String name = lo_name.getText();
            String time = lo_time.getText();
            String didian = lo_didian.getText();
            try{
                boolean b = false;
                ResultSet rs = m.searchMysql("SELECT 工号 FROM 队长_司机表;");
                while(rs.next()){
                    String xl = rs.getString("工号");
                    if(xl.equals(gonghao)){
                        b = true;
                        break;
                    }
                }
                if(b == false){
                    throw new Exception();
                }

                String sql = "INSERT INTO 违章记录表 VALUES (?,?,?,?,?,?);";
                PreparedStatement p = (PreparedStatement) m.conn.prepareStatement(sql);
                p.setString(1,bianhao);
                p.setString(2,gonghao);
                p.setString(3,chepai);
                p.setString(4,name);
                p.setString(5,time);
                p.setString(6,didian);
                p.executeUpdate();
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

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
public class InputBusView extends JFrame{
    // 背景
    private JLabel l;
    // 车牌号
    private JLabel l1;
    private JTextField lo_chepai;
    // 车龄
    private JLabel l2;
    private JTextField lo_cheling;
    // 车型
    private JLabel l3;
    private JTextField lo_chexing;
    // 座位数
    private JLabel l4;
    private JTextField lo_zuowei;
    // 年检月份
    private JLabel l5;
    private JTextField lo_yuefen;
    // 线路号
    private JLabel l6;
    private JTextField lo_xianlu;
    // 提交按钮
    private JButton b1;

    Login m;

    public InputBusView(Login m) {
        this.m = m;
        // 设置登录窗口标题
        this.setTitle("录入车辆信息");
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

        b1.addActionListener(new inputBusListener());

    }

    // 窗体组件初始化
    public void init() {
        // 将panel设置为frame的内容面板
        JPanel panel = new JPanel();
        this.setContentPane(panel);
        // 设置背景色
        l = new JLabel();
        l.setBounds(0, 0, 600, 700);
        // 车牌号
        l1 = new JLabel("车牌号");
        l1.setFont(new Font("楷体", Font.BOLD, 14));
        l1.setBounds(160, 70, 70, 20);
        // 车牌号输入框
        lo_chepai = new JTextField();
        lo_chepai.setBounds(250, 70, 150, 20);
        // 车龄
        l2 = new JLabel("车龄");
        l2.setFont(new Font("楷体", Font.BOLD, 14));
        l2.setBounds(165, 120, 70, 20);
        // 车龄输入框
        lo_cheling = new JTextField();
        lo_cheling.setBounds(250, 120, 150, 20);
        // 车型
        l3 = new JLabel("车型");
        l3.setFont(new Font("楷体", Font.BOLD, 14));
        l3.setBounds(165, 170, 70, 20);
        // 车型输入框
        lo_chexing = new JTextField();
        lo_chexing.setBounds(250, 170, 150, 20);
        // 座位数
        l4 = new JLabel("座位数");
        l4.setFont(new Font("楷体", Font.BOLD, 14));
        l4.setBounds(160, 220, 70, 20);
        // 座位数输入框
        lo_zuowei = new JTextField();
        lo_zuowei.setBounds(250, 220, 150, 20);
        // 年检月份
        l5 = new JLabel("年检月份");
        l5.setFont(new Font("楷体", Font.BOLD, 14));
        l5.setBounds(160, 270, 70, 20);
        // 年检月份输入框
        lo_yuefen = new JTextField();
        lo_yuefen.setBounds(250, 270, 150, 20);
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
        panel.add(lo_chepai);
        panel.add(lo_cheling);
        panel.add(lo_chexing);
        panel.add(lo_zuowei);
        panel.add(lo_yuefen);
        panel.add(lo_xianlu);
        panel.add(l);

    }

    private class inputBusListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String chepai = lo_chepai.getText();
            int cheling = Integer.parseInt(lo_cheling.getText());
            String chexing = lo_chexing.getText();
            int zuowei = Integer.parseInt(lo_zuowei.getText());
            int yuefen = Integer.parseInt(lo_yuefen.getText());
            int xianlu = Integer.parseInt(lo_xianlu.getText());
            try{
                boolean b = false;
                ResultSet rs = m.searchMysql("SELECT 线路号 FROM 队长_汽车表;");
                while(rs.next()){
                    int xl = rs.getInt("线路号");
                    if(xl == xianlu){
                        b = true;
                        break;
                    }
                }
                if(b == false){
                    throw new Exception();
                }

                String sql = "INSERT INTO 汽车表 VALUES (?,?,?,?,?,?);";
                PreparedStatement p = (PreparedStatement) m.conn.prepareStatement(sql);
                p.setString(1,chepai);
                p.setInt(2,cheling);
                p.setString(3,chexing);
                p.setInt(4,zuowei);
                p.setInt(5,yuefen);
                p.setInt(6,xianlu);
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

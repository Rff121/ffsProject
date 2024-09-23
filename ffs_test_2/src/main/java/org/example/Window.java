package org.example;
import javax.swing.*;
import java.awt.*;

import static com.tangosol.util.Processors.update;

/*
窗口的定义以及背景的绘制
*/

public class Window {
    private static JFrame main_window;//主窗口
    private int weight;
    private int height;
    private int type;

    Window(int x, int y, int type)//构造窗口函数(窗口宽度,窗口高度)
    {
        this.weight = x;
        this.height = y;
        this.type = type;
        main_window = new JFrame();//窗口实例化
        main_window.setSize(x, y);//窗口大小定义
        main_window.setLayout(null);//关闭布局管理器
        main_window.setResizable(false);//窗口是否可以被拖拉定义大小(否)
        if (type == 1) {
            main_window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//窗口关闭后终止程序
        }
        main_window.setLocationRelativeTo(null);//当前窗口默认居中显示系统

    }

    public void set_Window_Title(String s)//设置窗口标题
    {
        main_window.setTitle(s);
    }

    public void show()//窗口显示
    {
        main_window.setVisible(true);
    }

    public void insert_Button(JButton a, int xx, int yy, int ww, int hh)//窗口插入按钮(Button类,位置x，位置y,大小宽，大小高)
    {
        a.setBounds(xx, yy, ww, hh);
        a.setLayout(null);
        System.out.println(a.getText() + "*");
        main_window.add(a);
    }

    public static void remove_Button(JButton a) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                main_window.remove(a);
                main_window.repaint();
                //System.out.println("removing");
            }
        });
    }

    public void mid_insert_Button(JButton a, int xx, int yy, int ww, int hh) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {

                JFrame copiedFrame = new JFrame();
                copiedFrame.setSize(weight, height);//窗口大小定义
                copiedFrame.setLayout(null);//关闭布局管理器
                copiedFrame.setResizable(false);//窗口是否可以被拖拉定义大小(否)
                if (type == 1) {
                    copiedFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//窗口关闭后终止程序
                }
                copiedFrame.setLocationRelativeTo(null);//当前窗口默认居中显示系统
                for (Component component : main_window.getContentPane().getComponents()) {
                    copiedFrame.add(component);
                }

                copiedFrame.add(a,1);
                a.setBounds(xx, yy, ww, hh);
                a.setLayout(null);
                main_window.setVisible(false);
                main_window=null;
                main_window=copiedFrame;
                main_window.setVisible(true);
            }
        });
    }

    public void insert_ready_Button(JButton a) {
        main_window.add(a);
    }

    public void draw_fight_back_ground()//绘制战斗背景
    {
        JPanel jpanel = new MyPanel_fight();
        jpanel.setLayout(null);
        main_window.add(jpanel);
        jpanel.setBounds(0, 0, weight, height);
    }

    public static void windowFlash() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                main_window.revalidate();
                main_window.repaint();
                main_window.setVisible(true);
            }
        });
    }


    class MyPanel_fight extends JPanel {//战斗背景具体内容(内部定义)

        /**
         * 说明：
         * 1. MyPanel 对象就是一个画板
         * 2. Graphics g 把 g 理解成一支画笔
         * 3. Graphics 提供了很多绘图的方法，可以画出各种图形以及填充颜色
         */
        @Override
        public void paint(Graphics g) {// 绘图方法
            super.paint(g);// 调用父类的方法完成初始化
            Font font = new Font("宋体", Font.BOLD, 30); //new一个Font对象
            g.setFont(font);//通过Font对象设置字体的风格样式
            g.drawRect(5, 5, 1000, 245);
            g.drawString("行动墙", 600, 40);
            g.drawRect(50, 255, 700, 300);
            g.drawString("出牌区", 820, 300);
            g.drawRect(0, 560, 1005, 395);
            g.drawString("手牌区", 400, 600);
            g.drawRect(1010, 100, 770, 400);
            g.drawString("战斗区", 1300, 50);
            g.drawRect(1010, 505, 770, 400);
            g.drawString("敌方阵地", 1300, 480);
            g.drawString("我方阵地", 1300, 540);
            for (int i = 0; i < 3; i++) g.drawRect(50 + i * (170), 25, 120, 200);//行动墙
            for (int i = 0; i < 3; i++) g.drawRect(100 + i * (170), 300, 120, 200);//出牌区
            //g.drawRect(100+2*(170)+90, 300, 120, 200);
            for (int i = 0; i < 8; i++) g.drawRect(5 + i * (125), 730, 120, 200);//战斗区
            for (int i = 0; i < 2; i++)
                for (int j = 0; j < 4; j++) {
                    if (i == 0) g.setColor(Color.RED);
                    else g.setColor(Color.BLUE);
                    g.fillRoundRect(1070 + j * 170, 150 + i * 150, 150, 100, 40, 40);
                }
            for (int i = 0; i < 2; i++)
                for (int j = 0; j < 4; j++) {
                    if (i == 0) g.setColor(Color.RED);
                    else g.setColor(Color.BLUE);
                    g.fillRoundRect(1070 + j * 170, 590 + i * 150, 150, 100, 40, 40);
                }
            g.setColor(Color.BLACK);
        }
    }


}


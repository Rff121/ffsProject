package org.example;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.String;
import java.awt.Image;

/*
定义了按钮的基本内容，是卡牌和人物的基类
*/

public class Button extends JButton {
    private JButton btn;
    private ImageIcon icon_first,icon_second,icon_isChoosed;
    String content;
    int x,y;
    int height,weight;
    Icon logo;
    String Image_filename_1,Image_filename_2,Image_filename_isChoosed;
    boolean is_click=false;
    boolean ableToClick=false;
    private int state=1;

    Button(int xx,int yy,int w,int h,String name,String s1,String s2)//构造一个新的按钮(位置，大小，名字，按下前后的图片)
    {
        this.x=xx;this.y=yy;this.weight=w;this.height=h;this.content=name;this.Image_filename_1=s1;this.Image_filename_2=s2;
        Image_filename_isChoosed="/imagin/isChooseTest1.png";//先初步定一个确定的选中背景，接下来在构造函数中会传入相应的常数
        //System.out.println(s1);
        //System.out.println(s2);
        btn=new JButton(content);
        btn.setBounds(x,y,weight,height);
        btn.setLayout(null);
        btn.setOpaque(false);
        btn.setContentAreaFilled(false);//按键背景变化
        btn.setBorderPainted(false);//去边框
        btn.setFocusPainted(false);
        btn.setBorder(null);
        System.out.println(Image_filename_1);
        System.out.println(Image_filename_2);
        icon_first=new ImageIcon(getClass().getResource(Image_filename_1));//初始样式
        icon_second=new ImageIcon(getClass().getResource(Image_filename_2));//点击样式
        icon_isChoosed=new ImageIcon(getClass().getResource(Image_filename_isChoosed));
        Image img_first = icon_first.getImage();
        Image img_second=icon_second.getImage();
        Image img_isChoosed=icon_isChoosed.getImage();
        img_first=img_first.getScaledInstance(w,h,Image.SCALE_DEFAULT);
        img_second=img_second.getScaledInstance(w,h,Image.SCALE_DEFAULT);
        img_isChoosed=img_isChoosed.getScaledInstance(w,h,Image.SCALE_DEFAULT);
        icon_first.setImage(img_first);
        icon_second.setImage(img_second);
        icon_isChoosed.setImage(img_isChoosed);
        btn.setIcon(icon_first);
        System.out.println("))))))))))))))))))))))))))");

        // 添加鼠标监听器
        btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                // 按钮被按下时改变颜色
                if(ableToClick)
                {
                    btn.setIcon(icon_second);
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                // 按钮释放时恢复颜色
                if(ableToClick)
                {
                    changeChoosedState();
                    change_Image_not_is_Click();
                    is_click=true;
                }

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                //System.out.println("Mouse Entered");
            }

            @Override
            public void mouseExited(MouseEvent e) {
                //System.out.println("Mouse Exited");
            }
        });
    }

    public boolean IsClick()
    {
        return is_click;
    }

    public void flashClick()
    {
        is_click=false;
    }

    public int getState(){return state;}

    public JButton Get_Button()//获取按钮对象
    {
        return btn;
    }

    public void change_Image_not_is_Click()//如果按钮没有被按下，头像由当前状态决定
    {
        switch (state)
        {
            case 1:
                btn.setIcon(icon_first);
                break;
            case 2:
                btn.setIcon(icon_isChoosed);
                break;
        }

    }

    public void change_Image_is_Click()//如果按钮被按下，头像为第二张图片
    {
        btn.setIcon(icon_second);
    }

    public void setBackGround(int state)
    {
        if(state==1)btn.setIcon(icon_first);
        else if(state==2)btn.setIcon(icon_second);
    }

    public void changeChoosedState()
    {
        state=3-state;
    }
    public void setClickable(){ableToClick=true;}
    public void setUnClickable(){ableToClick=false;}

    public void unitState()
    {
        state=1;
        btn.setIcon(icon_first);
    }

}


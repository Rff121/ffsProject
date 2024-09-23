package org.example;
import hand_card.*;
import javax.swing.*;

public class fighting_hand_cards extends Button{

    /*_________显示设置_________*/
    private JButton btn=new JButton();
    private ImageIcon icon_first,icon_second;//按下前后的背景图片文件
    String content;
    int x,y;//位置
    int height,weight;//大小
    Icon logo;//当前头像
    String Image_filename_1,Image_filename_2;//按下前后的背景图片文件路径
    /*_________显示设置_________*/

    /*_________卡牌属性_________*/
    private int belong_type=0;
    private int limit_role=-1;
    private int identifier=0;

    private double distance=0;
    private double make_range=0;
    private double cure=0;
    private int make_state=0;
    private int attact=0;
    private int presision=0;

    private int from;
    private int to;
    /*_________卡牌属性_________*/

    /*_________卡牌初始化_________*/
    base_hand_cards_Data[] all_cards = (base_hand_cards_Data[]) new base_hand_cards_Data[]
            {
                    new Rabbi_common_attact(),//垫一位，下标从1开始
                    new Rabbi_common_attact(),
                    new Bowman_common_attact(),
                    new Fighter_common_attact(),
                    new Shield_common_attact(),
                    new Assassin_common_attact()
            };
    /*_________卡牌初始化_________*/

    public void unitHandCard()//卡牌属性
    {
        this.belong_type=all_cards[identifier].getBelong_type();
        this.limit_role=all_cards[identifier].getLimit_role();
        System.out.println(identifier+"^^^^^^^^^^^^^^^^^^^^^^^^^^^"+belong_type+"^^^^^^^^^^^^^^^^^^^^^^^^^^^"+limit_role);

    }

    fighting_hand_cards(int xx, int yy, int w, int h,int from,int role_part,String name, String s1, String s2) {
        super(xx,yy,w,h,name,s1,s2);
        this.from=from;this.identifier=role_part;this.x=xx;this.y=yy;this.weight=w;this.height=h;this.content=name;this.Image_filename_1=s1;this.Image_filename_2=s2;
        btn=this.Get_Button();
        btn.setBounds(xx,yy,w,h);
        unitHandCard();
    }

    public JButton getButton(){return this.Get_Button();}
    public int getBelongType(){return this.belong_type;}
    public int getLimit_role(){return this.limit_role;}
    public void setBonous(int x,int y,int w,int h){
        this.x=x;this.y=y;this.weight=w;this.height=h;
        btn.setBounds(x,y,w,h);
    }
    public int getIdentifier(){return identifier;}

}

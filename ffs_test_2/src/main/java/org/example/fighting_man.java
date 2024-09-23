package org.example;
/*_________角色池导入_________*/
import base_card.*;
/*_________角色池导入_________*/

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.util.AbstractMap.SimpleEntry;

/*
 战斗系统中人物卡牌(状态显示)和头像,作为一个按钮类，可以对人物卡牌进行相应操作
*/
public class fighting_man extends Button{

    /*_________显示设置_________*/
    private JButton btn;
    private ImageIcon icon_first,icon_second;//按下前后的背景图片文件
    String content;
    int x,y;//位置
    int height,weight;//大小
    Icon logo;//当前头像
    String Image_filename_1,Image_filename_2;//按下前后的背景图片文件路径
    /*_________显示设置_________*/

    /*_________角色属性_________*/
    private int from=0;//输入
    private int role_idex;//具体角色编号
    private int type=0;//职业
    private double HP=0;//血量
    private double attact=0;//攻击力
    private double define=0;//防御力
    private double sneer=0;//仇恨值
    private int state=0;//是否被眩晕
    private double speed=0;//速度
    private double accuracy=0;
    private int positionX=0;
    private int positionY=0;
    private int[] cards;
    /*_________角色属性_________*/

    /*_________角色初始化_________*/
    base_role_data[] all_role = (base_role_data[]) new base_role_data[]
    {
        new Rabbi_A(),
        new Rabbi_A(),
        new Bowman_A(),
        new Fighter_A(),
        new Shield_A(),
        new assassin_A()
    };
    /*_________角色初始化_________*/



    public void unitCharacter()//角色属性初始化
    {
        base_role_data role_Data=all_role[role_idex];
        this.HP=role_Data.getBaseHP();
        this.define=role_Data.getBaseDefine();
        this.sneer=role_Data.getBaseSneer();
        this.state=role_Data.getBaseDebuff();
        this.speed=role_Data.getBaseSpeed();
        this.accuracy=role_Data.getBaseAccuracy();
        this.attact=role_Data.getBaseDamage();
        cards=role_Data.getCards().clone();
    }

    public fighting_man(int xx,int yy,int w,int h,String name,int from,int role_part,int px,int py,String s1,String s2)//构造函数
    {
        super(xx,yy,w,h,name,s1,s2);
        btn=this.Get_Button();
        this.positionX=px;
        this.positionY=py;
        this.from=from;this.role_idex=role_part;this.x=xx;this.y=yy;this.weight=w;this.height=h;this.content=name;this.Image_filename_1=s1;this.Image_filename_2=s2;
        this.type=role_part;
        btn=this.Get_Button();
        btn.setBounds(xx,yy,w,h);
        unitCharacter();//初始化角色信息
    }

    public int[] getCards()
    {
        return cards.clone();
    }

    public JButton getButton(){return this.Get_Button();}
    public int getRole_idex(){return role_idex;}

    public int getType(){return type;}

}


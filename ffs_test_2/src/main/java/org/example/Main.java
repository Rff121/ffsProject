package org.example;


import javax.swing.JButton;
import org.example.Button;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JFrame;
import javax.swing.JPanel;
//import org.example.base_quality.*;
import javax.swing.ImageIcon;
//import oracle.jrockit.jfr.JFR;

/*正式主函数*/

public class Main {
    public static void mainn(String[] args) throws Exception {
        Button a=new Button(100,100,120,200,"按钮","/Btton_test1.png","/Bottn_test1.png");
        JButton aa=a.Get_Button();
        Window s=new Window(1800, 1000,0);
        s.insert_Button(aa,100,100,200,200);
        s.draw_fight_back_ground();
        s.show();
    }
}


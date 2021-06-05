package snake;
import javax.swing.*;
public class startGame {
   public static void main(String[] args) {
       //1.新建一个窗口
        JFrame frame =new JFrame();
        frame.setBounds(10,10,900,720);// 设置窗口的位置和大小
        frame.setResizable(false); //窗口大小不可调整,即固定窗口大小
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // 设置关闭事件，游戏可以关闭
       //2.添加编写的画布背景
        frame.add(new Gameplay());
       //将窗口展示出来
        frame.setVisible(true);
    }
}
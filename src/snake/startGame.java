package snake;
import javax.swing.*;
public class startGame {
   public static void main(String[] args) {
       //1.�½�һ������
        JFrame frame =new JFrame();
        frame.setBounds(10,10,900,720);// ���ô��ڵ�λ�úʹ�С
        frame.setResizable(false); //���ڴ�С���ɵ���,���̶����ڴ�С
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // ���ùر��¼�����Ϸ���Թر�
       //2.��ӱ�д�Ļ�������
        frame.add(new Gameplay());
       //������չʾ����
        frame.setVisible(true);
    }
}
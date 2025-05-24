package controller;


import BeAn.DanhMucBean;
import DoiTuong.User;
import View.BaoCao_View;
import View.DangKy_View;
import View.HocVien_View;
import View.NhanVien_View;
import View.ThongKe_View;
import View.TrangChu_View;
import java.awt.BorderLayout;
import java.awt.Color;
import java.util.List;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JLabel;
import javax.swing.JPanel;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author nhuyc
 */
public class ChuyenManHinhController {
      private JPanel root;
      private String kindSelected = "";
      private User user;
      List<DanhMucBean> listItem = null;
      
    public ChuyenManHinhController(JPanel root) {
        this.root = root;
    }
      
    public void setView(JPanel jpnItem, JLabel jlbItem, User user)
    {
        this.user = user;
        kindSelected = "TrangChu";
        jpnItem.setBackground(new Color(96,100,191));
        jlbItem.setBackground(new Color(96,100,191));
        
        root.removeAll();
        root.setLayout(new BorderLayout());
        root.add(new TrangChu_View(user));
        root.validate();
        root.repaint();
    }
    
    public void setEvent(List<DanhMucBean> listItem){
        this.listItem = listItem;
        for(DanhMucBean it : listItem){
            it.getJlb().addMouseListener(new LabelEvent(it.getKind(),it.getJpn(),it.getJlb(),it.getUser()));
            
        }
    }
    
    class LabelEvent implements MouseListener{
        private JPanel node;
        private String kind;
        
        private JPanel jpnItem;
        private JLabel jbItem;
        
        public LabelEvent(String kind, JPanel jpnItem, JLabel jbItem,User user) {
            this.kind = kind;
            this.jpnItem = jpnItem;
            this.jbItem = jbItem;
        }
        
        @Override
        public void mouseClicked(MouseEvent e) {
            switch(kind)
            {
                case "TrangChu":
                    node = new TrangChu_View(user);
                    break;
                case "HocVien":
                    node = new HocVien_View();
                    break;
                case "NhanVien":
                    node = new NhanVien_View();
                    break;
                case "DangKy":
                    node = new DangKy_View();
                    break;
                case "BaoCao":
                    node = new BaoCao_View();
                    break;
                case "ThongKe":
                    node = new ThongKe_View();
                    break;
                default:
                    break;
            }
            root.removeAll();
            root.setLayout(new BorderLayout());
            root.add(node);
            root.validate();
            root.repaint();
            setChangeBackground(kind);
         }

        @Override
        public void mousePressed(MouseEvent e) {
            kindSelected = kind;
            jpnItem.setBackground(new Color(96,100,191));
            jbItem.setBackground(new Color(96,100,191));
        }
         

        @Override
        public void mouseReleased(MouseEvent e) {
             
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            jpnItem.setBackground(new Color(96,100,191));
            jbItem.setBackground(new Color(96,100,191));
        }
           

        @Override
        public void mouseExited(MouseEvent e) {
            if(!kindSelected.equalsIgnoreCase(kind))
            {
                jpnItem.setBackground(new Color(0,102,204));
                jbItem.setBackground(new Color(0,102,204));
            }
        }
    }
    
    private void setChangeBackground(String kind){
        for(DanhMucBean it : listItem){
            if(it.getKind().equalsIgnoreCase(kind)){
                it.getJpn().setBackground(new Color(96,100,191));
                it.getJlb().setBackground(new Color(96,100,191));
            }
            else
            {
                it.getJpn().setBackground(new Color(0,102,204));
                it.getJlb().setBackground(new Color(0,102,204));
            }
        }
     
    }
}

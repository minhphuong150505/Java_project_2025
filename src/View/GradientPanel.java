package View;

import javax.swing.*;
import java.awt.*;

public class GradientPanel extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Hiệu ứng loang màu từ xanh ngọc sang trắng
        Color startColor = new Color(0, 255, 255); // Cyan
        Color endColor = Color.WHITE;

        GradientPaint gradient = new GradientPaint(
                0, 0, startColor,
                getWidth(), 0, endColor // loang theo chiều ngang
        );

        g2d.setPaint(gradient);
        g2d.fillRect(0, 0, getWidth(), getHeight());
    }
}
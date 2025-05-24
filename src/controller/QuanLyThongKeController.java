/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import BeAn.DangKyBean;
import BeAn.LopHocBean;
import Service.ThongKeService;
import Service.ThongKeServiceimpl;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.util.List;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.gantt.Task;
import org.jfree.data.gantt.TaskSeries;
import org.jfree.data.gantt.TaskSeriesCollection;
import org.jfree.data.time.SimpleTimePeriod;

public class QuanLyThongKeController {

    private ThongKeService thongKeService = null;

    public QuanLyThongKeController() {
        this.thongKeService = new ThongKeServiceimpl();
    }

    public void setDataToChart1(JPanel jpnItem) {
        List<DangKyBean> listItem = thongKeService.getListByDangKy();

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        if (listItem != null) {
            for (DangKyBean item : listItem) {
                dataset.addValue(item.getSoLuongHocVien(), "Học viên", item.getNgayDangKy());
            }
        }

        JFreeChart barChart = ChartFactory.createBarChart(
                "Biểu đồ thống kê số lượng học viên đăng ký".toUpperCase(),
                "Thời gian", "Số lượng",
                dataset, PlotOrientation.VERTICAL, false, true, false);

        ChartPanel chartPanel = new ChartPanel(barChart);
        //chartPanel.setPreferredSize(new Dimension(jpnItem.getWidth(), 321));

        jpnItem.removeAll();
        jpnItem.setLayout(new CardLayout());
        jpnItem.add(chartPanel);
        jpnItem.validate();
        jpnItem.repaint();
        jpnItem.setLayout(new BorderLayout());
        jpnItem.add(chartPanel, BorderLayout.CENTER);
    }

    public void setDataToChart2(JPanel jpnItem) {
        List<LopHocBean> listItem = thongKeService.getListByLopHoc();

        TaskSeriesCollection ds = new TaskSeriesCollection();
        JFreeChart ganttChart = ChartFactory.createGanttChart(
                "BIỂU ĐỒ THEO DÕI TÌNH TRẠNG KHÓA HỌC",
                "Lớp Học", "Thời gian", ds, true, false, false
        );

        TaskSeries taskSeries;
        Task task;

        if (listItem != null) {
            for (LopHocBean item : listItem) {
                taskSeries = new TaskSeries(item.getTenLopHoc());
                task = new Task(item.getTenLopHoc(), new SimpleTimePeriod(item.getNgayBatDau(), item.getNgayKetThuc()));
                taskSeries.add(task);
                ds.add(taskSeries);
            }
        }

        ChartPanel chartPanel = new ChartPanel(ganttChart);
        //chartPanel.setPreferredSize(new Dimension(jpnItem.getWidth(), 321));

        jpnItem.removeAll();
        jpnItem.setLayout(new CardLayout());
        jpnItem.add(chartPanel);
        jpnItem.validate();
        jpnItem.repaint();
        jpnItem.setLayout(new BorderLayout());
        jpnItem.add(chartPanel, BorderLayout.CENTER);
    }
}
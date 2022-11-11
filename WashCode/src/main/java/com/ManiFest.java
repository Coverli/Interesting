package com;

import com.Hash.GetHash;
import com.Hash.SplitMerge;
import com.Hash.ModifyHash;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

/**
 * @author Coverli
 */
public class ManiFest extends JFrame implements ActionListener {
    public static void main(String[] args) {
        new ManiFest().win();
    }

    private String[] list = {"MD5", "SHA-1", "SHA-256", "SHA-512"};
    private JComboBox<String> select = new JComboBox<>(list);
    private JButton check = new JButton("查 看");
    private JButton wash = new JButton("洗 码");
    private JLabel warm = new JLabel("文件名：");
    private JLabel fileName = new JLabel();
    private JTextArea details = new JTextArea();
    private JScrollPane scrollPane = new JScrollPane(details);

    public void win () {
        this.setTitle("洗码和校验");
        this.setSize(515, 325);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(false);
        this.setLocation(200, 100);
        URL url = ManiFest.class.getResource("/hash.png");
        String fileUrl = url.getPath();
        this.setIconImage(new ImageIcon(fileUrl).getImage());

        JPanel panel1 = new JPanel();
        panel1.setLayout(null);
        panel1.setLocation(0, 0);
        panel1.setSize(500,300);
        this.getContentPane().add(panel1);

        Font defaultFont = new Font("宋体", Font.PLAIN, 14);

        select.setFont(defaultFont);
        check.setFont(defaultFont);
        wash.setFont(defaultFont);
        warm.setFont(defaultFont);
        fileName.setFont(defaultFont);
        details.setFont(defaultFont);

        select.setBounds(200, 30, 100, 30);
        check.setBounds(100, 90, 100, 30);
        wash.setBounds(300, 90, 100, 30);
        warm.setBounds(20, 160, 56, 15);
        fileName.setBounds(76,160, 404, 15);
        details.setBounds(20, 175, 460, 105);
        scrollPane.setBounds(20, 175, 460, 105);

        panel1.add(select);
        panel1.add(check);
        panel1.add(wash);
        panel1.add(warm);
        panel1.add(fileName);
        panel1.add(details);
        panel1.add(scrollPane);

        check.addActionListener(this);
        wash.addActionListener(this);
        details.setEditable(true);
        details.setLineWrap(true);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setViewportView(details);
    }

    public String openFile () {
        String path = null;
        // 打开文件
        try {
            //设置界面风格
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            JFileChooser fileChooser = new JFileChooser();

            //设置选择路径模式
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

            //过滤文件类型
            //FileNameExtensionFilter filter = new FileNameExtensionFilter("All file","All file");
            //fileChooser.setFileFilter(filter);

            //设置对话框标题
            fileChooser.setDialogTitle("请选择文件路径");
            //用户点击了确定
            if (JFileChooser.APPROVE_OPTION == fileChooser.showOpenDialog(null)) {
                //获取文件路径和文件名
                path = fileChooser.getSelectedFile().getAbsolutePath();
                fileName.setText(fileChooser.getSelectedFile().getName());
                details.append("文件路径：" + String.format(path) + "\n");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return path;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String path;
        if (e.getSource() == check) {
            int selectIndex = select.getSelectedIndex();
            if (selectIndex == 0) {
                try {
                    path = openFile();
                    String result = new GetHash().getHash(path, 1, "MD5");
                    details.append("MD5值为：" + String.format(result) + "\n");
                    details.append("\n");
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            if (selectIndex == 1) {
                try {
                    path = openFile();
                    String result = new GetHash().getHash(path, 1, "SHA-1");
                    details.append("SHA-1值为：" + String.format(result) + "\n");
                    details.append("\n");
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            if (selectIndex == 2) {
                try {
                    path = openFile();
                    String result = new GetHash().getHash(path, 1, "SHA-256");
                    details.append("SHA-256值为：" + String.format(result) + "\n");
                    details.append("\n");
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            if (selectIndex == 3) {
                try {
                    path = openFile();
                    String result = new GetHash().getHash(path, 1, "SHA-512");
                    details.append("SHA-512值为：" + String.format(result) + "\n");
                    details.append("\n");
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
        if (e.getSource() == wash) {
            try {
                path = openFile();
                ModifyHash.modifyHash(path, 0);
                details.append("洗码完成！" + "\n");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}

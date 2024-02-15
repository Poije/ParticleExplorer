import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ParticleSimulation extends JFrame{
    //private final ExecutorService executorService;
    private final SimulationPanel simulationPanel;
    private final JPanel particleControlPanel = new JPanel(null);
    private final JPanel controlPanel = new JPanel(null);
    private JComboBox<String> addParticleDropdown;
    private JButton particleAddButton;
    private JButton wallAddButton;
    private final JPanel particleFieldControlPanel = new JPanel(null);
    private final JPanel wallControlPanel = new JPanel(null);
    private final JPanel wallFieldControlPanel = new JPanel(null);
    private JTextField opt1X = new JTextField();
    private JTextField opt1Y = new JTextField();
    private JTextField opt1V = new JTextField();
    private JTextField opt1A = new JTextField();
    private JTextField opt2N = new JTextField();
    private JTextField opt2V = new JTextField();
    private JTextField opt2A = new JTextField();
    private JTextField opt2X1 = new JTextField();
    private JTextField opt2Y1 = new JTextField();
    private JTextField opt2X2 = new JTextField();
    private JTextField opt2Y2 = new JTextField();
    private JTextField opt3N = new JTextField();
    private JTextField opt3X = new JTextField();
    private JTextField opt3Y = new JTextField();
    private JTextField opt3V = new JTextField();
    private JTextField opt3A1 = new JTextField();
    private JTextField opt3A2 = new JTextField();
    private JTextField opt4N = new JTextField();
    private JTextField opt4X = new JTextField();
    private JTextField opt4Y = new JTextField();
    private JTextField opt4A = new JTextField();
    private JTextField opt4V1 = new JTextField();
    private JTextField opt4V2 = new JTextField();
    private JTextField wallX1 = new JTextField();
    private JTextField wallY1 = new JTextField();
    private JTextField wallX2 = new JTextField();
    private JTextField wallY2 = new JTextField();

    public ParticleSimulation(){
        super("Particle Simulation");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1680, 820);
        setResizable(false);
        setBackground(Color.BLACK);
        setLayout(null);
        //executorService = Executors.newFixedThreadPool(THREAD_COUNT);
        simulationPanel = new SimulationPanel();
        add(simulationPanel);
        createControlPanel();
        add(controlPanel);
    }

    public void createControlPanel(){
        controlPanel.setBounds(1380, 50, 250, 720);
        controlPanel.setLayout(new GridLayout(2, 1, 0, 10));
        controlPanel.setBackground(Color.WHITE);
        
        addParticleDropdown = new JComboBox<>(new String[]{"Single Particle", "Multiple Particle, Varying Start", "Multiple Particle, Varying Angle", "Multiple Particle, Varying Velocity"});
        addParticleDropdown.setMinimumSize(new Dimension(250, 20));
        addParticleDropdown.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                updateParticleFieldControlPanel();
                particleFieldControlPanel.revalidate();
                particleFieldControlPanel.repaint();
            }
        });
        particleControlPanel.add(addParticleDropdown);
        particleControlPanel.setLayout(new BoxLayout(particleControlPanel, BoxLayout.Y_AXIS));
        particleFieldControlPanel.setLayout(new CardLayout());
        //particleFieldControlPanel.setMinimumSize(new Dimension(250, 320));
        //particleFieldControlPanel.setMaximumSize(new Dimension(250, 320));
        particleFieldControlPanel.add(createOpt1Panel(), "Single Particle");
        particleFieldControlPanel.add(createOpt2Panel(), "Multiple Particle, Varying Start");
        particleFieldControlPanel.add(createOpt3Panel(), "Multiple Particle, Varying Angle");
        particleFieldControlPanel.add(createOpt4Panel(), "Multiple Particle, Varying Velocity");
        particleControlPanel.add(particleFieldControlPanel);
        particleAddButton = new JButton("Add Particle");
        particleAddButton.setMinimumSize(new Dimension(250, 20));
        particleAddButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e){
                String selectedOption = (String) addParticleDropdown.getSelectedItem();

                switch (selectedOption) {
                    case "Single Particle":
                        double o1x = Double.parseDouble(opt1X.getText());
                        double o1y = Double.parseDouble(opt1Y.getText());
                        double o1angle = Double.parseDouble(opt1A.getText());
                        double o1velocity = Double.parseDouble(opt1V.getText());

                        simulationPanel.opt1Add(o1x, o1y, o1angle, o1velocity);
                        break;
                    case "Multiple Particle, Varying Start":
                        int o2n = Integer.parseInt(opt2N.getText());
                        double o2x1 = Double.parseDouble(opt2X1.getText());
                        double o2y1 = Double.parseDouble(opt2Y1.getText());
                        double o2x2 = Double.parseDouble(opt2X2.getText());
                        double o2y2 = Double.parseDouble(opt2Y2.getText());
                        double o2angle = Double.parseDouble(opt2A.getText());
                        double o2velocity = Double.parseDouble(opt2V.getText());

                        simulationPanel.opt2Add(o2n, o2x1, o2y1, o2x2, o2y2, o2angle, o2velocity);
                        break;
                    case "Multiple Particle, Varying Angle":
                        int o3n = Integer.parseInt(opt3N.getText());
                        double o3x = Double.parseDouble(opt3X.getText());
                        double o3y = Double.parseDouble(opt3Y.getText());
                        double o3angle1 = Double.parseDouble(opt3A1.getText());
                        double o3angle2 = Double.parseDouble(opt3A2.getText());
                        double o3velocity = Double.parseDouble(opt3V.getText());

                        simulationPanel.opt3Add(o3n, o3x, o3y, o3angle1, o3angle2, o3velocity);
                        break;
                        case "Multiple Particle, Varying Velocity":
                        int o4n = Integer.parseInt(opt4N.getText());
                        double o4x = Double.parseDouble(opt4X.getText());
                        double o4y = Double.parseDouble(opt4Y.getText());
                        double o4angle = Double.parseDouble(opt4A.getText());
                        double o4velocity1 = Double.parseDouble(opt4V1.getText());
                        double o4velocity2 = Double.parseDouble(opt4V2.getText());

                        simulationPanel.opt4Add(o4n, o4x, o4y, o4angle, o4velocity1, o4velocity2);
                        break;
                }
                clearAllParticleFields();
            }
        });
        particleControlPanel.add(particleAddButton);
        controlPanel.add(particleControlPanel);
        wallControlPanel.setLayout(new BoxLayout(wallControlPanel, BoxLayout.Y_AXIS));
        wallFieldControlPanel.setLayout(new GridLayout(0,2));
        wallFieldControlPanel.add(new JLabel("X1:"));
        wallFieldControlPanel.add(wallX1);
        wallFieldControlPanel.add(new JLabel("Y1:"));
        wallFieldControlPanel.add(wallY1);
        wallFieldControlPanel.add(new JLabel("X2:"));
        wallFieldControlPanel.add(wallX2);
        wallFieldControlPanel.add(new JLabel("Y2:"));
        wallFieldControlPanel.add(wallY2);

        wallControlPanel.add(wallFieldControlPanel);
        wallAddButton = new JButton("Add Particle");
        wallAddButton.setSize(230, 20);
        wallAddButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e){
                double x1 = Integer.parseInt(wallX1.getText());
                double y1 = Double.parseDouble(wallY1.getText());
                double x2 = Integer.parseInt(wallX2.getText());
                double y2 = Double.parseDouble(wallY2.getText());

                simulationPanel.addWall(x1, y1, x2, y2);

                wallX1.setText("");
                wallY1.setText("");
                wallX2.setText("");
                wallY2.setText("");
            }
        });
        wallControlPanel.add(wallAddButton);
        controlPanel.add(wallControlPanel);
    }

    public void clearAllParticleFields(){
        opt1X.setText("");
        opt1Y.setText("");
        opt1A.setText("");
        opt1V.setText("");
        opt2N.setText("");
        opt2X1.setText("");
        opt2Y1.setText("");
        opt2X2.setText("");
        opt2Y2.setText("");
        opt2A.setText("");
        opt2V.setText("");
        opt3N.setText("");
        opt3X.setText("");
        opt3Y.setText("");
        opt3A1.setText("");
        opt3A2.setText("");
        opt3V.setText("");
        opt4N.setText("");
        opt4X.setText("");
        opt4Y.setText("");
        opt4A.setText("");
        opt4V1.setText("");
        opt4V2.setText("");
    }

    public JPanel createOpt1Panel(){
        JPanel panel = new JPanel(new GridLayout(0,2));
        panel.add(new JLabel("X:"));
        panel.add(opt1X);
        panel.add(new JLabel("Y:"));
        panel.add(opt1Y);
        panel.add(new JLabel("Velocity:"));
        panel.add(opt1V);
        panel.add(new JLabel("Angle:"));
        panel.add(opt1A);

        return panel;
    }

    public JPanel createOpt2Panel(){
        JPanel panel = new JPanel(new GridLayout(0,2));
        panel.add(new JLabel("Number of Particles:"));
        panel.add(opt2N);
        panel.add(new JLabel("Velocity:"));
        panel.add(opt2V);
        panel.add(new JLabel("Angle:"));
        panel.add(opt2A);
        panel.add(new JLabel("Start X:"));
        panel.add(opt2X1);
        panel.add(new JLabel("Start Y:"));
        panel.add(opt2Y1);
        panel.add(new JLabel("End X:"));
        panel.add(opt2X2);
        panel.add(new JLabel("End Y:"));
        panel.add(opt2Y2);

        return panel;
    }

    public JPanel createOpt3Panel(){
        JPanel panel = new JPanel(new GridLayout(0,2));
        panel.add(new JLabel("Number of Particles:"));
        panel.add(opt3N);
        panel.add(new JLabel("X:"));
        panel.add(opt3X);
        panel.add(new JLabel("Y:"));
        panel.add(opt3Y);
        panel.add(new JLabel("Velocity:"));
        panel.add(opt3V);
        panel.add(new JLabel("Start Angle:"));
        panel.add(opt3A1);
        panel.add(new JLabel("End Angle:"));
        panel.add(opt3A2);

        return panel;
    }

    public JPanel createOpt4Panel(){
        JPanel panel = new JPanel(new GridLayout(0,2));
        panel.add(new JLabel("Number of Particles:"));
        panel.add(opt4N);
        panel.add(new JLabel("X:"));
        panel.add(opt4X);
        panel.add(new JLabel("Y:"));
        panel.add(opt4Y);
        panel.add(new JLabel("Angle:"));
        panel.add(opt4A);
        panel.add(new JLabel("Start Velocity:"));
        panel.add(opt4V1);
        panel.add(new JLabel("End Velocity:"));
        panel.add(opt4V2);

        return panel;
    }
    
    public void updateParticleFieldControlPanel(){
        String selectedMethod = (String) addParticleDropdown.getSelectedItem();
        CardLayout cardLayout = (CardLayout) particleFieldControlPanel.getLayout();
        cardLayout.show(particleFieldControlPanel, selectedMethod);
        particleFieldControlPanel.revalidate();
        particleFieldControlPanel.repaint();
    }

    public static void main(String[] args) {
        new ParticleSimulation().setVisible(true);
    }
}

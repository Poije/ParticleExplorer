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
    private final int THREAD_COUNT = 8;
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
    private JTextField wallX = new JTextField();
    private JTextField wallY = new JTextField();
    private JTextField wallLength = new JTextField();
    private JTextField wallAngle = new JTextField();

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
                revalidate();
                repaint();
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
                int numParticles = Integer.parseInt(opt2N.getText());
                double x = Double.parseDouble(opt1X.getText());
                double y = Double.parseDouble(opt1Y.getText());
                double velocity = Double.parseDouble(opt1V.getText());
                double angle = Double.parseDouble(opt1A.getText());

                opt2N.setText("");
                opt1X.setText("");
                opt1Y.setText("");
                opt1V.setText("");
                opt1A.setText("");
            }
        });
        particleControlPanel.add(particleAddButton);
        controlPanel.add(particleControlPanel);
        wallControlPanel.setLayout(new BoxLayout(wallControlPanel, BoxLayout.Y_AXIS));
        wallFieldControlPanel.setLayout(new GridLayout(0,2));
        wallFieldControlPanel.add(new JLabel("X:"));
        wallFieldControlPanel.add(wallX);
        wallFieldControlPanel.add(new JLabel("Y:"));
        wallFieldControlPanel.add(wallY);
        wallFieldControlPanel.add(new JLabel("Length:"));
        wallFieldControlPanel.add(wallLength);
        wallFieldControlPanel.add(new JLabel("Angle:"));
        wallFieldControlPanel.add(wallAngle);

        wallControlPanel.add(wallFieldControlPanel);
        wallAddButton = new JButton("Add Particle");
        wallAddButton.setSize(230, 20);
        wallAddButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e){
                int numParticles = Integer.parseInt(wallX.getText());
                double x = Double.parseDouble(wallY.getText());
                double y = Double.parseDouble(wallLength.getText());
                double velocity = Double.parseDouble(wallAngle.getText());

                opt2N.setText("");
                opt1X.setText("");
                opt1Y.setText("");
                opt1V.setText("");
                opt1A.setText("");
            }
        });
        wallControlPanel.add(wallAddButton);
        controlPanel.add(wallControlPanel);
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
        revalidate();
        repaint();
    }

    public static void main(String[] args) {
        new ParticleSimulation().setVisible(true);
    }
}

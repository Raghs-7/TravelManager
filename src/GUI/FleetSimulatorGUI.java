package GUI;

import Simulation.*;
import Vehicle.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.border.*;

public class FleetSimulatorGUI extends JFrame {
    private HighwaySimulator simulator;
    private ArrayList<VehicleThread> vehicleThreads;
    private ArrayList<VehiclePanel> vehiclePanels;

    private JLabel highwayDistanceLabel;
    private JLabel totalMileageLabel;
    private JLabel syncStatusLabel;
    private JButton startButton;
    private JButton pauseButton;
    private JButton resumeButton;
    private JButton stopButton;
    private JButton toggleSyncButton;
    private JButton resetButton;
    private JPanel vehicleContainer;

    private Timer updateTimer;
    private boolean simulationRunning = false;

    public FleetSimulatorGUI() {
        simulator = new HighwaySimulator();
        vehicleThreads = new ArrayList<>();
        vehiclePanels = new ArrayList<>();

        initializeGUI();
        initializeVehicles();
        setupUpdateTimer();
    }

    private void initializeGUI() {
        setTitle("Fleet Highway Simulator - Multithreading & Race Condition Demo");
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // Top Panel - Controls
        JPanel controlPanel = createControlPanel();
        add(controlPanel, BorderLayout.NORTH);

        // Center Panel - Vehicle Display
        vehicleContainer = new JPanel();
        vehicleContainer.setLayout(new BoxLayout(vehicleContainer, BoxLayout.Y_AXIS));
        vehicleContainer.setBorder(new EmptyBorder(10, 10, 10, 10));
        vehicleContainer.setBackground(new Color(240, 240, 240));

        JScrollPane scrollPane = new JScrollPane(vehicleContainer);
        scrollPane.setBorder(new TitledBorder("Vehicle Status - Real-time Updates"));
        add(scrollPane, BorderLayout.CENTER);

        // Bottom Panel - Statistics
        JPanel statsPanel = createStatsPanel();
        add(statsPanel, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
    }

    private JPanel createControlPanel() {
        JPanel panel = new JPanel(new GridLayout(2, 1, 5, 5));
        panel.setBorder(new TitledBorder("Simulation Controls"));
        panel.setBackground(new Color(230, 240, 255));

        // First row - Main controls
        JPanel row1 = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        row1.setBackground(new Color(230, 240, 255));

        startButton = new JButton("Start Simulation");
        startButton.setBackground(new Color(100, 200, 100));
        startButton.setFont(new Font("Arial", Font.BOLD, 14));
        startButton.addActionListener(e -> startSimulation());

        pauseButton = new JButton("Pause All");
        pauseButton.setBackground(new Color(255, 200, 100));
        pauseButton.setFont(new Font("Arial", Font.BOLD, 14));
        pauseButton.setEnabled(false);
        pauseButton.addActionListener(e -> pauseSimulation());

        resumeButton = new JButton("Resume All");
        resumeButton.setBackground(new Color(100, 200, 255));
        resumeButton.setFont(new Font("Arial", Font.BOLD, 14));
        resumeButton.setEnabled(false);
        resumeButton.addActionListener(e -> resumeSimulation());

        stopButton = new JButton("Stop Simulation");
        stopButton.setBackground(new Color(255, 100, 100));
        stopButton.setFont(new Font("Arial", Font.BOLD, 14));
        stopButton.setEnabled(false);
        stopButton.addActionListener(e -> stopSimulation());

        row1.add(startButton);
        row1.add(pauseButton);
        row1.add(resumeButton);
        row1.add(stopButton);

        // Second row - Sync control and reset
        JPanel row2 = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        row2.setBackground(new Color(230, 240, 255));

        toggleSyncButton = new JButton("Enable Synchronization (Fix Race Condition)");
        toggleSyncButton.setBackground(new Color(255, 150, 150));
        toggleSyncButton.setFont(new Font("Arial", Font.BOLD, 14));
        toggleSyncButton.addActionListener(e -> toggleSynchronization());

        resetButton = new JButton("Reset Simulation");
        resetButton.setFont(new Font("Arial", Font.BOLD, 14));
        resetButton.addActionListener(e -> resetSimulation());

        row2.add(toggleSyncButton);
        row2.add(resetButton);

        panel.add(row1);
        panel.add(row2);

        return panel;
    }

    private JPanel createStatsPanel() {
        JPanel panel = new JPanel(new GridLayout(1, 3, 10, 5));
        panel.setBorder(new TitledBorder("Simulation Statistics"));
        panel.setBackground(new Color(255, 255, 230));
        panel.setPreferredSize(new Dimension(0, 80));

        highwayDistanceLabel = new JLabel("Shared Highway Distance: 0 km", SwingConstants.CENTER);
        highwayDistanceLabel.setFont(new Font("Arial", Font.BOLD, 16));
        highwayDistanceLabel.setForeground(new Color(0, 100, 0));

        totalMileageLabel = new JLabel("Total Individual Mileage: 0 km", SwingConstants.CENTER);
        totalMileageLabel.setFont(new Font("Arial", Font.BOLD, 16));
        totalMileageLabel.setForeground(new Color(0, 0, 150));

        syncStatusLabel = new JLabel("Synchronization: DISABLED (Race Condition Active)", SwingConstants.CENTER);
        syncStatusLabel.setFont(new Font("Arial", Font.BOLD, 16));
        syncStatusLabel.setForeground(Color.RED);

        panel.add(highwayDistanceLabel);
        panel.add(totalMileageLabel);
        panel.add(syncStatusLabel);

        return panel;
    }

    private void initializeVehicles() {
        // Create at least 3 vehicles as per requirement
        Car car1 = new Car("V001", "Honda Civic", 180, 4, 100.0, 5, 0);
        Car car2 = new Car("V002", "Toyota Corolla", 175, 4, 100.0, 5, 0);
        Truck truck1 = new Truck("V003", "Volvo FH", 120, 6, 150.0, 0.0);
        Bus bus1 = new Bus("V004", "Tata Bus", 100, 8, 200.0, 0, 0.0);
        AirPlane plane1 = new AirPlane("V005", "Boeing 747", 900, 10000, 500.0, 0, 0.0);

        addVehicleToSimulation(car1);
        addVehicleToSimulation(car2);
        addVehicleToSimulation(truck1);
        addVehicleToSimulation(bus1);
        addVehicleToSimulation(plane1);
    }

    private void addVehicleToSimulation(Vehicle vehicle) {
        VehicleThread thread = new VehicleThread(vehicle, simulator);
        vehicleThreads.add(thread);

        VehiclePanel panel = new VehiclePanel(vehicle, thread);
        vehiclePanels.add(panel);
        vehicleContainer.add(panel);
        vehicleContainer.add(Box.createRigidArea(new Dimension(0, 5)));
    }

    private void setupUpdateTimer() {
        updateTimer = new Timer(500, e -> updateDisplay());
    }

    private void startSimulation() {
        if (!simulationRunning) {
            for (VehicleThread thread : vehicleThreads) {
                thread.start();
            }
            updateTimer.start();
            simulationRunning = true;

            startButton.setEnabled(false);
            pauseButton.setEnabled(true);
            resumeButton.setEnabled(false);
            stopButton.setEnabled(true);

            // Enable individual vehicle controls
            for (VehiclePanel panel : vehiclePanels) {
                panel.enableControls(true);
            }
        }
    }

    private void pauseSimulation() {
        for (VehicleThread thread : vehicleThreads) {
            thread.pauseVehicle();
        }
        pauseButton.setEnabled(false);
        resumeButton.setEnabled(true);
    }

    private void resumeSimulation() {
        for (VehicleThread thread : vehicleThreads) {
            thread.resumeVehicle();
        }
        pauseButton.setEnabled(true);
        resumeButton.setEnabled(false);
    }

    private void stopSimulation() {
        for (VehicleThread thread : vehicleThreads) {
            thread.stopVehicle();
        }
        updateTimer.stop();
        simulationRunning = false;

        startButton.setEnabled(false);
        pauseButton.setEnabled(false);
        resumeButton.setEnabled(false);
        stopButton.setEnabled(false);
    }

    private void toggleSynchronization() {
        if (simulator.isSynchronizationEnabled()) {
            simulator.disableSynchronization();
            toggleSyncButton.setText("Enable Synchronization (Fix Race Condition)");
            toggleSyncButton.setBackground(new Color(255, 150, 150));
            syncStatusLabel.setText("Synchronization: DISABLED (Race Condition Active)");
            syncStatusLabel.setForeground(Color.RED);
        } else {
            simulator.enableSynchronization();
            toggleSyncButton.setText("Disable Synchronization (Show Race Condition)");
            toggleSyncButton.setBackground(new Color(150, 255, 150));
            syncStatusLabel.setText("Synchronization: ENABLED (Race Condition Fixed)");
            syncStatusLabel.setForeground(new Color(0, 150, 0));
        }
    }

    private void resetSimulation() {
        if (simulationRunning) {
            JOptionPane.showMessageDialog(this,
                    "Please stop the simulation before resetting.",
                    "Cannot Reset",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Clear everything
        for (VehicleThread thread : vehicleThreads) {
            if (thread.isAlive()) {
                thread.stopVehicle();
            }
        }

        vehicleThreads.clear();
        vehiclePanels.clear();
        vehicleContainer.removeAll();
        simulator.resetHighwayDistance();

        // Reinitialize
        initializeVehicles();
        vehicleContainer.revalidate();
        vehicleContainer.repaint();

        startButton.setEnabled(true);
        pauseButton.setEnabled(false);
        resumeButton.setEnabled(false);
        stopButton.setEnabled(false);

        updateDisplay();
    }

    private void updateDisplay() {
        // Update highway distance
        int sharedDistance = simulator.getHighwayDistance();
        highwayDistanceLabel.setText("Shared Highway Distance: " + sharedDistance + " km");

        // Calculate total individual mileage
        double totalMileage = 0;
        for (VehicleThread thread : vehicleThreads) {
            totalMileage += thread.getVehicle().getCurrentMileage();
        }
        totalMileageLabel.setText("Total Individual Mileage: " + String.format("%.0f", totalMileage) + " km");

        // Highlight discrepancy if race condition is active
        if (!simulator.isSynchronizationEnabled() && Math.abs(sharedDistance - totalMileage) > 5) {
            highwayDistanceLabel.setForeground(Color.RED);
            totalMileageLabel.setForeground(Color.RED);
        } else {
            highwayDistanceLabel.setForeground(new Color(0, 100, 0));
            totalMileageLabel.setForeground(new Color(0, 0, 150));
        }

        // Update vehicle panels
        for (VehiclePanel panel : vehiclePanels) {
            panel.updateDisplay();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            FleetSimulatorGUI gui = new FleetSimulatorGUI();
            gui.setVisible(true);
        });
    }

    // Inner class for individual vehicle display panel
    class VehiclePanel extends JPanel {
        private Vehicle vehicle;
        private VehicleThread thread;
        private JLabel statusLabel;
        private JLabel mileageLabel;
        private JLabel fuelLabel;
        private JButton refuelButton;
        private JButton pauseButton;
        private JButton resumeButton;
        private JButton stopButton;

        public VehiclePanel(Vehicle vehicle, VehicleThread thread) {
            this.vehicle = vehicle;
            this.thread = thread;

            setLayout(new BorderLayout(5, 5));
            setBorder(new TitledBorder(vehicle.getClass().getSimpleName() + " - " + vehicle.getId()));
            setBackground(Color.WHITE);
            setMaximumSize(new Dimension(Integer.MAX_VALUE, 120));
            setPreferredSize(new Dimension(0, 120));

            // Left panel - Info
            JPanel infoPanel = new JPanel(new GridLayout(3, 1));
            infoPanel.setBackground(Color.WHITE);

            JLabel idLabel = new JLabel("  Model: " + vehicle.getModel() + " | Max Speed: " + vehicle.getMaxSpeed() + " km/h");
            statusLabel = new JLabel("  Status: Ready");
            mileageLabel = new JLabel("  Mileage: 0.0 km");

            infoPanel.add(idLabel);
            infoPanel.add(statusLabel);
            infoPanel.add(mileageLabel);

            add(infoPanel, BorderLayout.CENTER);

            // Right panel - Controls
            JPanel rightPanel = new JPanel(new BorderLayout(5, 5));
            rightPanel.setBackground(Color.WHITE);

            // Top: Fuel info
            fuelLabel = new JLabel("Fuel: N/A", SwingConstants.CENTER);
            fuelLabel.setFont(new Font("Arial", Font.BOLD, 12));
            rightPanel.add(fuelLabel, BorderLayout.NORTH);

            // Center: Control buttons
            JPanel buttonPanel = new JPanel(new GridLayout(4, 1, 3, 3));
            buttonPanel.setBackground(Color.WHITE);

            refuelButton = new JButton("⛽ Refuel");
            refuelButton.setFont(new Font("Arial", Font.PLAIN, 11));
            refuelButton.setBackground(new Color(100, 200, 255));
            refuelButton.addActionListener(e -> handleRefuel());

            pauseButton = new JButton("⏸ Pause");
            pauseButton.setFont(new Font("Arial", Font.PLAIN, 11));
            pauseButton.setBackground(new Color(255, 200, 100));
            pauseButton.setEnabled(false);
            pauseButton.addActionListener(e -> {
                thread.pauseVehicle();
                pauseButton.setEnabled(false);
                resumeButton.setEnabled(true);
            });

            resumeButton = new JButton("▶ Resume");
            resumeButton.setFont(new Font("Arial", Font.PLAIN, 11));
            resumeButton.setBackground(new Color(100, 255, 100));
            resumeButton.setEnabled(false);
            resumeButton.addActionListener(e -> {
                thread.resumeVehicle();
                resumeButton.setEnabled(false);
                pauseButton.setEnabled(true);
            });

            stopButton = new JButton("⏹ Stop");
            stopButton.setFont(new Font("Arial", Font.PLAIN, 11));
            stopButton.setBackground(new Color(255, 100, 100));
            stopButton.setEnabled(false);
            stopButton.addActionListener(e -> {
                thread.stopVehicle();
                stopButton.setEnabled(false);
                pauseButton.setEnabled(false);
                resumeButton.setEnabled(false);
                refuelButton.setEnabled(false);
            });

            buttonPanel.add(refuelButton);
            buttonPanel.add(pauseButton);
            buttonPanel.add(resumeButton);
            buttonPanel.add(stopButton);

            rightPanel.add(buttonPanel, BorderLayout.CENTER);

            // Check if vehicle uses fuel
            if (!(vehicle instanceof Car || vehicle instanceof Truck ||
                    vehicle instanceof Bus || vehicle instanceof AirPlane ||
                    vehicle instanceof CargoShipFuel)) {
                fuelLabel.setText("No Fuel System");
                refuelButton.setEnabled(false);
            }

            add(rightPanel, BorderLayout.EAST);

            updateDisplay();
        }

        private void handleRefuel() {
            // Show dialog to ask for fuel amount
            String input = JOptionPane.showInputDialog(
                    this,
                    "Enter fuel amount to add (liters):",
                    "Refuel " + vehicle.getId(),
                    JOptionPane.QUESTION_MESSAGE
            );

            if (input != null && !input.trim().isEmpty()) {
                try {
                    double amount = Double.parseDouble(input.trim());
                    if (amount <= 0) {
                        JOptionPane.showMessageDialog(
                                this,
                                "Please enter a positive amount.",
                                "Invalid Amount",
                                JOptionPane.ERROR_MESSAGE
                        );
                        return;
                    }
                    if (amount > 500) {
                        JOptionPane.showMessageDialog(
                                this,
                                "Maximum refuel amount is 500 liters.",
                                "Amount Too Large",
                                JOptionPane.WARNING_MESSAGE
                        );
                        return;
                    }
                    thread.refuelVehicle(amount);
                    JOptionPane.showMessageDialog(
                            this,
                            String.format("Successfully added %.1f liters of fuel!", amount),
                            "Refuel Complete",
                            JOptionPane.INFORMATION_MESSAGE
                    );
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(
                            this,
                            "Please enter a valid number.",
                            "Invalid Input",
                            JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        }

        public void enableControls(boolean enable) {
            if (enable) {
                pauseButton.setEnabled(true);
                stopButton.setEnabled(true);
                if (vehicle instanceof Car || vehicle instanceof Truck ||
                        vehicle instanceof Bus || vehicle instanceof AirPlane ||
                        vehicle instanceof CargoShipFuel) {
                    refuelButton.setEnabled(true);
                }
            }
        }

        public void updateDisplay() {
            statusLabel.setText("  Status: " + thread.getStatus());
            mileageLabel.setText("  Mileage: " + String.format("%.1f", vehicle.getCurrentMileage()) + " km");

            // Update fuel label
            double fuelLevel = 0;
            if (vehicle instanceof Car) {
                fuelLevel = ((Car) vehicle).getFuelLevel();
            } else if (vehicle instanceof Truck) {
                fuelLevel = ((Truck) vehicle).getFuelLevel();
            } else if (vehicle instanceof Bus) {
                fuelLevel = ((Bus) vehicle).getFuelLevel();
            } else if (vehicle instanceof AirPlane) {
                fuelLevel = ((AirPlane) vehicle).getFuelLevel();
            } else if (vehicle instanceof CargoShipFuel) {
                fuelLevel = ((CargoShipFuel) vehicle).getFuelLevel();
            }

            if (vehicle instanceof Car || vehicle instanceof Truck ||
                    vehicle instanceof Bus || vehicle instanceof AirPlane ||
                    vehicle instanceof CargoShipFuel) {
                fuelLabel.setText(String.format("Fuel: %.1f L", fuelLevel));

                if (fuelLevel <= 0) {
                    fuelLabel.setForeground(Color.RED);
                    statusLabel.setForeground(Color.RED);
                } else if (fuelLevel < 20) {
                    fuelLabel.setForeground(new Color(255, 140, 0));
                    statusLabel.setForeground(Color.BLACK);
                } else {
                    fuelLabel.setForeground(new Color(0, 150, 0));
                    statusLabel.setForeground(Color.BLACK);
                }
            }

            // Color code status
            String status = thread.getStatus();
            if (status.equals("Running")) {
                setBackground(new Color(230, 255, 230));
                if (!pauseButton.isEnabled() && thread.isAlive()) {
                    pauseButton.setEnabled(true);
                    resumeButton.setEnabled(false);
                }
            } else if (status.equals("Paused")) {
                setBackground(new Color(255, 255, 200));
                if (!resumeButton.isEnabled() && thread.isAlive()) {
                    pauseButton.setEnabled(false);
                    resumeButton.setEnabled(true);
                }
            } else if (status.equals("Out-of-Fuel")) {
                setBackground(new Color(255, 230, 230));
                pauseButton.setEnabled(false);
                resumeButton.setEnabled(false);
            } else if (status.equals("Stopped")) {
                setBackground(new Color(220, 220, 220));
                pauseButton.setEnabled(false);
                resumeButton.setEnabled(false);
                stopButton.setEnabled(false);
                refuelButton.setEnabled(false);
            }
        }
    }
}
package controller;

import view.AdminDashboardFrame;
import view.GestioneEventiFrame;

import javax.swing.*;

public class AdminDashboardController {

    public void apriGestioneEventi(int adminId, JFrame currentFrame) {
        GestioneEventiFrame gestioneEventiFrame = new GestioneEventiFrame(adminId);
        gestioneEventiFrame.setVisible(true);
        currentFrame.dispose();
    }
}

package test;

import entities.Machine;
import entities.Salle;
import services.MachineService;
import services.SalleService;

import java.util.Date;

public class Test {

    public static void main(String[] args) {
        SalleService salleService = new SalleService();
        MachineService machineService = new MachineService();

        // Create and insert rooms
        Salle salle1 = new Salle("A1");
        Salle salle2 = new Salle("B2");
        salleService.create(salle1);
        salleService.create(salle2);

        // Create and insert machines
        Machine machine1 = new Machine("M123", new Date(), salleService.findById(salle1.getId()));
        Machine machine2 = new Machine("M124", new Date(), salleService.findById(salle2.getId()));
        machineService.create(machine1);
        machineService.create(machine2);

        // Display rooms and their machines
        for (Salle salle : salleService.findAll()) {
            System.out.println("Salle: " + salle.getCode());
            for (Machine machine : salle.getMachines()) {
                System.out.println("  Machine: " + machine.getRef());
            }
        }

        // Use findBetweenDate
        Date d1 = new Date(110, 0, 1); // January 1st 2010
        Date d2 = new Date(); // Current date
        System.out.println("Machines purchased between " + d1 + " and " + d2 + ":");
        for (Machine m : machineService.findBetweenDate(d1, d2)) {
            System.out.println(m.getRef() + " purchased on " + m.getDateAchat());
        }
    }
}

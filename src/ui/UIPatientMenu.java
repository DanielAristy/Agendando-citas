package ui;

import model.Doctor;

import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class UIPatientMenu {

    public static void showPatientMenu(){
        int response = 0;
        do {
            System.out.println("\n\n");
            System.out.println("Patient");
            System.out.println("Welcome: " + UIMenu.patientLogued.getName());
            System.out.println("1. Book an appointment");
            System.out.println("2. My appointments");
            System.out.println("0. Logout");

            Scanner sc = new Scanner(System.in);
            response = Integer.valueOf(sc.nextLine());

            switch (response){
                case 1:
                    showBookAppointmentMenu();
                    break;
                case 2:
                    showPatientMyAppointment();
                    break;
                case 0:
                    UIMenu.showMenu();
                    break;
            }
        }while (response != 0);
    }
    private static void showBookAppointmentMenu(){
        int response = 0;
        do {
            System.out.println("::Book Appointment");
            System.out.println(":: Select Date");

            //Numeracion de la lista de fechas
            //Indice fecha seleccionada
            // [doctors]
            // 1. doctor 1
                    // 1. fecha1
                    // 2. fecha2
            // 2. doctor 2
                    // 1. fecha1
                    // 2. fecha2
            Map<Integer, Map<Integer, Doctor>> doctors = new TreeMap<>();
            int k = 0;
            for (int i = 0; i < UIDoctorMenu.doctorsAvailableAppointments.size(); i++) {
                ArrayList<Doctor.AvailableAppointment> availableAppointments
                        = UIDoctorMenu.doctorsAvailableAppointments.get(i).getAvailableAppointments();

                Map<Integer, Doctor> doctorAppointments = new TreeMap<>();

                for (int j = 0; j < availableAppointments.size(); j++) {
                    k++;
                    System.out.println(k + ". " + availableAppointments.get(j).getDate() + " Time: " +
                            availableAppointments.get(j).getTime());
                    doctorAppointments.put(Integer.valueOf(j), UIDoctorMenu.doctorsAvailableAppointments.get(i));

                    doctors.put(Integer.valueOf(k),doctorAppointments);
                }
            }

            Scanner sc = new Scanner(System.in);
            int responseDateSelected = Integer.valueOf(sc.nextLine());
            Map<Integer, Doctor> doctorAvailableSelected = doctors.get(responseDateSelected);
            Integer indexDate = 0;
            Doctor doctorSelected = new Doctor("","");
            for (Map.Entry<Integer, Doctor> doctor :doctorAvailableSelected.entrySet()) {
                indexDate = doctor.getKey();
                doctorSelected = doctor.getValue();
            }

            System.out.println(doctorSelected.getName() +
                    ". Date: " +
                    doctorSelected.getAvailableAppointments().get(indexDate).getDate() +
                    ". Time: " +
                    doctorSelected.getAvailableAppointments().get(indexDate).getTime());

            System.out.println("Confirm your appointment: \n1. Yes \n2. Changes Data");
            response = Integer.valueOf(sc.nextLine());

            if (response == 1){
              UIMenu.patientLogued.addAppointmentDoctors(
                    doctorSelected,
                    doctorSelected.getAvailableAppointments().get(indexDate).getDate(null),
                    doctorSelected.getAvailableAppointments().get(indexDate).getTime());
                showPatientMenu();
            }
        }while (response != 0);
    }

    private static void showPatientMyAppointment(){
        int response = 0;

        do {
            System.out.println("::My Appointments");
            if (UIMenu.patientLogued.getAppointmentDoctors().size() == 0){
                System.out.println("Don't have appointments");
                break;
            }

            for (int i = 0; i < UIMenu.patientLogued.getAppointmentDoctors().size(); i++) {
                int j = i + 1;
                System.out.println(j + ". "+
                "Date: " + UIMenu.patientLogued.getAppointmentDoctors().get(i).getDate() +
                "Time: " + UIMenu.patientLogued.getAppointmentDoctors().get(i).getTime() +
                "\nDoctor: " + UIMenu.patientLogued.getAppointmentDoctors().get(i).getDoctor().getName());
            }
            System.out.println("0. Return");
            
        }while (response !=0);
    }
}

package ui;

import model.Doctor;
import model.Patient;

import java.util.ArrayList;
import java.util.Scanner;

public class UIMenu {

    public final static String[] MONTHS = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
    public static Doctor doctorLogued;
    public static Patient patientLogued;
    public static void showMenu(){
        System.out.println("Welcome to My Appointments");
        System.out.println("Selecciona la opción deseada");

        int response = 0;
        do {
            System.out.println("1. Doctor");
            System.out.println("2. Patient");
            System.out.println("0. Salir");

            Scanner sc = new Scanner(System.in);
            response = Integer.valueOf(sc.nextLine());

            switch (response){
                case 1:
                    System.out.println("Doctor");
                    response = 0;
                    authUser(1);
                    break;
                case 2:
                    System.out.println("Paciente");
                    response = 0;
                    authUser(2);

                    break;
                case 0:
                    System.out.println("Thank you for you visit");
                    break;
                default:
                    System.out.println("Please select a correct answer");
            }
        }while (response != 0);
    }

    private static void authUser(int userType){
        // userType = 1 -> Doctor
        // userType = 2 -> Patient
        //Base de datos
        ArrayList<Doctor> doctors = Doctor.doctorArrayList();
        ArrayList<Patient> patients = Patient.patientArrayList();
        boolean emailCorrect = false;

        do {
            System.out.println("Insert your email: [example@gmail.com]");
            Scanner sc = new Scanner(System.in);
            String email = sc.nextLine();
            if (userType == 1){
                for (Doctor doctor: doctors) {
                    if (doctor.getEmail().equals(email)) {
                        emailCorrect = true;
                        //Obtener el usuario logueado
                        doctorLogued = doctor;
                        //ShowDoctorMenu
                        UIDoctorMenu.showDoctorMenu();
                    }
                }
            }

            if (userType == 2){
                for (Patient patient: patients) {
                    if (patient.getEmail().equals(email)) {
                        emailCorrect = true;
                        //Obtener el usuario logueado
                        patientLogued = patient;
                        //ShowPatientMenu
                        UIPatientMenu.showPatientMenu();
                    }
                }
            }

        }while (!emailCorrect);
    }

}

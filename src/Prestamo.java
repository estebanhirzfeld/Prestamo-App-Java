import javax.swing.*;

public class Prestamo {

    public static int[] loanInput(int income) {
        // App
        String[] avalaible_months = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};
        String[] avalaible_years = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30"};
        String[] options = {"Modificar Monto", "Modificar Años", "Modificar Meses", "Salir"};
        int option;
        boolean exit = false;

        // User
        int years = 0;
        int months = 0;
        int loan = 0;

        while (!exit) {
            option = customOptionsDialog(options, "Prestamo App - Calculadora", showMonthYearTerm(loan, years, months));
            switch (options[option]) {
                case "Modificar Monto":
                    do {
                        loan = Math.abs(Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese monto, MAX (" + income * 10 + ")")));
                        if (loan <= 0) {
                            JOptionPane.showMessageDialog(null, "El monto debe ser mayor que 0. Por favor, ingrese un monto válido.");
                        } else if (loan > income * 10) {
                            JOptionPane.showMessageDialog(null, "El monto no puede ser mayor a " + (income * 10) + ". Por favor, ingrese un monto válido.");
                        }
                    } while (loan <= 0 || loan > income * 10);
                    break;
                case "Modificar Meses":
                    String month = (String) JOptionPane.showInputDialog(null,
                            "Cant de Meses",
                            "Prestamo App - Calculadora", JOptionPane.INFORMATION_MESSAGE, null, avalaible_months, avalaible_months[0]);
                    months = Integer.parseInt(month);
                    break;
                case "Modificar Años":
                    String year = (String) JOptionPane.showInputDialog(null,
                            "Cant de Años",
                            "Prestamo App - Calculadora", JOptionPane.INFORMATION_MESSAGE, null, avalaible_years, avalaible_years[0]);
                    years = Integer.parseInt(year);
                    break;
                case "Salir":
                    if ((years <= 0 && months <= 0) && (loan > 0)) {
                        int response = JOptionPane.showConfirmDialog(null,
                                "No ha ingresado un valor válido para años o meses. ¿Desea continuar ingresando datos?",
                                "Desea Continuar?",
                                JOptionPane.YES_NO_OPTION,
                                JOptionPane.WARNING_MESSAGE);

                        if (response == JOptionPane.NO_OPTION) {
                            loan = 0;
                            exit = true;
                        }
                    } else {
                        exit = true;
                    }
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opcion no valida");
            }
        }
        return new int[]{loan, years, months};
    }

    public static int customOptionsDialog(String[] options, String title, String msg) {
        if (title == null) {
            title = "Prestamo App";
        }
        return JOptionPane.showOptionDialog(null, msg, title, JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
    }

    public static String showIncomeLoan(int income, int[] term) {
        String msgTern = "";
        if (term[0] != 0) {
            msgTern = " (" + term[1] + " Años y " + term[2] + " Meses)";
        }
        return "Ingresos: " + income + "\n\nPrestamo actual: " + term[0] + msgTern;
    }

    public static String showMonthYearTerm(int loan, int years, int months) {
        return "Monto: " + loan + "\n\nAños: " + years + "\nMeses: " + months;
    }

    public static void main(String[] args) {
        // App
        boolean exit = false;
        int option;
        int input;

        // User
        int income = 0;
        int[] loan = new int[3];


        String[] options = {"Modificar Ingreso", "Solicitar Prestamo", "Salir"};


        while (!exit) {
            option = customOptionsDialog(options, null, showIncomeLoan(income, loan));

            switch (options[option]) {
                case "Modificar Ingreso":
                    if (loan[0] > 0) {
                        JOptionPane.showMessageDialog(null, "No es posible modificar el ingreso porque ya existe un préstamo activo.");
                    } else {
                        input = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese monto"));
                        income = Math.abs(input);
                    }
                    break;
                case "Solicitar Prestamo":
                    if (income <= 0) {
                        JOptionPane.showMessageDialog(null, "El ingreso debe ser mayor que 0 para solicitar un préstamo. Por favor, ingrese un valor de ingreso válido.");
                    } else {
                        loan = loanInput(income);
                    }
                    break;
                case "Salir":
                    exit = true;
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opcion Incorrecta");
                    break;
            }

        }
    }
}

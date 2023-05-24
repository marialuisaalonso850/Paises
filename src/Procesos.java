import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import javax.swing.JOptionPane;

public class Procesos {

    private ArrayList<String> ciudades;
    private ArrayList<String> paises;

    public Procesos() {
        ciudades = new ArrayList<>();
        paises = new ArrayList<>();
        iniciar();
    }

    public void iniciar() {
        String msg = "MENU*\n";
        msg += "1) Registrar Pais\n";
        msg += "2) Registrar Ciudades\n";
        msg += "3) Consultar Ciudades por pais\n";
        msg += "4) Consultar Pais por ciudad\n";
        msg += "5) Imprimir Paises con Ciudades\n";
        msg += "6) Imprimir Array de Ciudades\n";
        msg += "7) Salir\n ";
        int opt = 0;
        do {
            String input = JOptionPane.showInputDialog(msg);
            if (input.isEmpty()) {
                JOptionPane.showMessageDialog(null, "opcion invalida");
                continue;
            }
            opt = Integer.parseInt(input);

            menu(opt);
        } while (opt != 7);
    }

    private void menu(int opt) {
        switch (opt) {
            case 1:
                registrarPais();
                break;
            case 2:
                if (paises.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Registra al menos un país");
                } else {
                    registrarCiudad();
                }
                break;
            case 3:
                consultarPaisPorCiudades();
                break;
            case 4:
                consultarCiudadesPorPais();
                break;
            case 5:
                imprimirPaisesConCiudades();
                break;
            case 6:
                imprimirArrayCiudades();
                break;
            default:
                break;
        }
    }

    private void registrarPais() {
        String pais = JOptionPane.showInputDialog("Nombre del país:");
        paises.add(pais);
    }

    private void registrarCiudad() {
        String validar = "";
        do {
            String ciudad = JOptionPane.showInputDialog("Registrar ciudad:");
            String paisCiudad = JOptionPane.showInputDialog("Ingrese el país al que pertenece la ciudad:");
            ciudades.add(ciudad);
            paises.add(paisCiudad);
            validar = JOptionPane.showInputDialog("Presione 'si' para salir");
        } while (!validar.equalsIgnoreCase("si"));
    }

    private void consultarPaisPorCiudades() {
        String resul = JOptionPane.showInputDialog("Ingrese la ciudad para buscar el país:");
        if (resul == null) {
            return;
        }

        String paisEncontrado = null;
        for (int i = 0; i < ciudades.size(); i++) {
            String ciudad = ciudades.get(i);
            String pais = paises.get(i);
            if (ciudad.equalsIgnoreCase(resul)) {
                if (paisEncontrado == null) {
                    paisEncontrado = pais;
                } else {
                    JOptionPane.showMessageDialog(null, "Hay varios países que contienen la ciudad especificada.");
                    return;
                }
            }
        }

        if (paisEncontrado == null) {
            JOptionPane.showMessageDialog(null, "No se encontró ningún país que contenga la ciudad especificada.");
        } else {
            JOptionPane.showMessageDialog(null, "El país que contiene la ciudad especificada es: " + paisEncontrado);
        }
    }

    private void consultarCiudadesPorPais() {
        String conPais = JOptionPane.showInputDialog("Buscar país para listar ciudades:");
        if (conPais == null) {
            return;
        }

        boolean encontrado = false;
        for (int i = 0; i < paises.size(); i++) {
            String pais = paises.get(i);
            if (pais.equalsIgnoreCase(conPais)) {
                encontrado = true;
                JOptionPane.showMessageDialog(null, ciudades.get(i));
            }
        }

        if (!encontrado) {
            JOptionPane.showMessageDialog(null, "No se encuentra ningún registro para el país especificado.");
        }
    }

    private void imprimirPaisesConCiudades() {
        HashMap<String, ArrayList<String>> paisesCiudades = new HashMap<>();
        for (int i = 0; i < ciudades.size(); i++) {
            String pais = paises.get(i);
            String ciudad = ciudades.get(i);
            if (paisesCiudades.containsKey(pais)) {
                ArrayList<String> ciudadesPais = paisesCiudades.get(pais);
                ciudadesPais.add(ciudad);
            } else {
                ArrayList<String> ciudadesPais = new ArrayList<>();
                ciudadesPais.add(ciudad);
                paisesCiudades.put(pais, ciudadesPais);
            }
        }

        for (String pais : paisesCiudades.keySet()) {
            ArrayList<String> ciudadesPais = paisesCiudades.get(pais);
            System.out.println("País: " + pais);
            System.out.println("Ciudades: " + ciudadesPais);
        }
    }

    private void imprimirArrayCiudades() {
        ArrayList<String> todasLasCiudades = new ArrayList<>();
        todasLasCiudades.addAll(ciudades);

        String[] arrayCiudades = todasLasCiudades.toArray(new String[0]);
        System.out.println("Array de Ciudades: " + Arrays.toString(arrayCiudades));
    }
}
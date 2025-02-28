import java.util.*;

public class LibretaNotas {

    private static HashMap<String, ArrayList<Double>> libreta = new HashMap<>();

    public static double notaMax(ArrayList<Double> notas) {
        return Collections.max(notas);
    }

    public static double notaMin(ArrayList<Double> notas) {
        return Collections.min(notas);
    }

    public static double promedio(ArrayList<Double> notas) {
        return notas.stream().mapToDouble(n -> n).average().orElse(1.0);
    }

    public static double promedioCurso() {
        double acumulador = 0;
        for (ArrayList<Double> alumno : libreta.values()) {
            acumulador = +promedio(alumno);
        }
        return acumulador / libreta.size();
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int cantidadAlumnos, cantidadNotas;
        String nombreAlumno;
        int opt = 0, notaAl;
        String nombre;

        System.out.println("Introduzca la cantidad de alumnos");
        cantidadAlumnos = sc.nextInt();

        System.out.println("Introduzca la cantidad de notas por alumno");
        cantidadNotas = sc.nextInt();

        sc.nextLine();

        for (int i = 0; i < cantidadAlumnos; i++) {
            System.out.println("Introduzca el nombre del alumno " + (i + 1));
            nombreAlumno = sc.nextLine();
            libreta.put(nombreAlumno, new ArrayList<>());
            for (int j = 0; j < cantidadNotas; j++) {
                System.out.println("Introduzca la nota " + (j + 1) + " de " + nombreAlumno);
                double nota = sc.nextDouble();
                libreta.get(nombreAlumno).add(nota);
            }
            sc.nextLine();
        }


        do {
            System.out.println("Que desea Hacer:");
            System.out.println("1. Mostrar el Promedio de Notas por Estudiante.");
            System.out.println("2. Mostrar si la Nota es Aprobatoria o Reprobatoria por Estudiante.");
            System.out.println("3. Mostrar si la Nota está por Sobre o por Debajo del Promedio del Curso por Estudiante.");
            System.out.println("0. Salir del Menú.");
            opt = sc.nextInt();
            sc.nextLine();

            switch (opt) {
                case 1:
                    libreta.forEach((alumno, notas) -> {
                        System.out.println("nombre alumno: " + alumno);
                        System.out.println("promedio alumno: " + promedio(notas));
                    });
                    break;
                case 2:
                    System.out.println("Ingrese el nombre del alumno");
                    nombre = sc.nextLine();

                    for (int i = 0; i <= libreta.size(); i++) {
                        System.out.println((i + 1) + "." + "nota " + libreta.get(nombre).get(i));
                    }

                    System.out.println("Que nota desea consultar");
                    notaAl = sc.nextInt();

                    if (libreta.get(nombre).get((notaAl - 1)) >= 40) {
                        System.out.println("La nota es aprobatoria");
                    } else {
                        System.out.println("La nota es reprobatoria");
                    }
                    break;
                case 3:
                    double promCurso = promedioCurso();
                    System.out.println("El promedio del curso es: " + promCurso);
                    System.out.println("Ingrese el nombre del alumno");
                    nombre = sc.nextLine();

                    if (libreta.containsKey(nombre)) {
                        System.out.println("Estas son las notas:");
                        for (int i = 0; i <= libreta.size(); i++) {
                            System.out.println((i + 1) + "." + "nota " + libreta.get(nombre).get(i));
                        }

                        System.out.println("Cual nota desea comparar");
                        notaAl = sc.nextInt();

                        String evalNota = "La nota del alumno es igual al promedio del curso";

                        if (libreta.get(nombre).get((notaAl - 1)) > promCurso) {
                            evalNota = "La nota del alumno es superior al promedio del curso";
                        } else {
                            evalNota = "La nota del alumno es inferior al promedio del curso";
                        }

                        System.out.println(evalNota);

                    } else {
                        System.out.println("No existe el nombre del alumno");
                    }
            }
        } while (opt != 0);


    }
}

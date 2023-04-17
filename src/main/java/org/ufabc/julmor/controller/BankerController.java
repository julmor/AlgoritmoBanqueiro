package org.ufabc.julmor.controller;

public class BankerController {
    private int totalProcessos;
    private int totalRecursos;
    private int[][] recursosAtualmenteAlocados;
    private int[][] recursosMaximo;
    private int[] recursosDisponveis;


    public BankerController(int[] recursosDisponiveis, int[][] recursosMaximo, int[][] recursosAtualmenteAlocados, int totalProcessos, int totalRecursos) {
        this.recursosDisponveis =  new int[totalRecursos];
        this.recursosMaximo = new int[totalProcessos][totalRecursos];
        this.recursosAtualmenteAlocados = new int[totalProcessos][totalRecursos];
        this.totalProcessos = totalProcessos;
        this.totalRecursos = totalRecursos;
    }



    static void calculaNecessidadeProcesso(int necessidadesProcesso[][], int recursosMaximo[][], int recursosAtualmenteAlocados[][], int totalProcessos, int totalRecursos) {
        for (int i = 0 ; i < totalProcessos ; i++){
            for (int j = 0 ; j < totalRecursos ; j++){
                necessidadesProcesso[i][j] = recursosMaximo[i][j] - recursosAtualmenteAlocados[i][j];
            }
        }
    }

    public static boolean verificaEstado(int recursosDisponiveis[], int recursosMaximo[][], int recursosAtualmenteAlocados[][], int totalProcessos, int totalRecursos) {
        int [][]necessidadesProcesso = new int[totalProcessos][totalRecursos];

        calculaNecessidadeProcesso(necessidadesProcesso, recursosMaximo, recursosAtualmenteAlocados, totalProcessos, totalRecursos);

        boolean []processosTerminados = new boolean[totalProcessos];

        int []sequenciaSegura = new int[totalProcessos];

        int []copiaDeRecursosDisponiveis = new int[totalRecursos];

        for (int i = 0; i < totalRecursos ; i++)
            copiaDeRecursosDisponiveis[i] = recursosDisponiveis[i];

        int contador = 0;

        while (contador < totalProcessos) {
            boolean encontraEstadoSeguro = false;
            for (int m = 0; m < totalProcessos; m++) {
                if (processosTerminados[m] == false){
                    int j;
                    for (j = 0; j < totalRecursos; j++)
                        if (recursosMaximo[m][j] > copiaDeRecursosDisponiveis[j])
                            break;

                    if (j == totalRecursos) {
                        for (int k = 0 ; k < totalRecursos ; k++)
                            copiaDeRecursosDisponiveis[k] += recursosAtualmenteAlocados[m][k];

                        sequenciaSegura[contador++] = m;

                        processosTerminados[m] = true;

                        encontraEstadoSeguro = true;
                    }
                }
            }

            if (encontraEstadoSeguro == false)
            {
            System.out.println("--------- ESTADO INSEGURO ----------------");
            System.out.println("MOTIVO: FALTA DE RECURSOS");
                return false;
            }
        }

        System.out.println("--------- ESTADO SEGURO ----------");
        System.out.println("SEQUÊNCIA SEGURA: ");
        for (int i = 0; i < totalProcessos ; i++)
            System.out.print("P"+sequenciaSegura[i] + " ");

        return true;
    }
}

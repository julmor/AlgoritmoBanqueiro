package org.ufabc.julmor.model;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.ufabc.julmor.controller.BankerController;

import java.util.Date;
import java.util.Random;
import java.util.TimerTask;
@Component
public class BankerModel extends TimerTask {

    @Override
    @Scheduled(fixedRate = 1000)
    public void run() {
        System.out.println("Task performed on: " + new Date() + "n");
        System.out.println("Thread's name: " + Thread.currentThread().getName());

        int totalRecursos = 6, totalProcessos = 5, min = 0, max = 9;

        int[] processo = new int[totalProcessos];
        for (int i = 0; i < totalProcessos; i++) {
            processo[i] = i;
        }

        int[] recursosDisponiveis = new int[totalRecursos];
        int[][] recursosAtualmenteAlocados = new int[totalProcessos][totalRecursos];
        int[][] recursosMaximo = new int[totalProcessos][totalRecursos];

        Random randVal = new Random();
        try {
            int i, j;
            for (i = 0; i < totalProcessos; i++) {
                for (j = 0; j < totalRecursos; j++) {
                    recursosAtualmenteAlocados[i][j] = randVal.nextInt((max - min) + 1);
                }
            }

            System.out.println("Elementos da matriz de recursos atualmente alocados:");
            for (i = 0; i < totalProcessos; i++) {
                for (j = 0; j < totalRecursos; j++) {
                    System.out.print(recursosAtualmenteAlocados[i][j] + "  ");
                }
                System.out.println();
            }

            for (i = 0; i < totalProcessos; i++) {
                for (j = 0; j < totalRecursos; j++) {
                    recursosMaximo[i][j] = randVal.nextInt((max - min) + 1);
                }
            }

            System.out.println("Elementos da matriz de máximo de recursos:");
            for (i = 0; i < totalProcessos; i++) {
                for (j = 0; j < totalRecursos; j++) {
                    System.out.print(recursosMaximo[i][j] + "  ");
                }
                System.out.println();
            }

            for (i = 0; i < totalRecursos; i++) {
                recursosDisponiveis[i] = randVal.nextInt((max - min) + 1);
            }

            System.out.println("Elementos do vetor de Recursos Disponiveis:");
            for (i = 0; i < totalRecursos; i++) {
                System.out.println(recursosDisponiveis[i] + " ");
            }
            BankerController banker = new BankerController(recursosDisponiveis, recursosAtualmenteAlocados, recursosMaximo, totalProcessos, totalRecursos);
            banker.verificaEstado(recursosDisponiveis, recursosAtualmenteAlocados, recursosMaximo, totalProcessos, totalRecursos);
        } catch (Exception e) {
        }
    }
}


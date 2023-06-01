package pl.tabaka.sklep.game;

import pl.tabaka.sklep.GUI.IGUI;

import java.util.Random;
import java.util.Scanner;

import static java.lang.Math.min;

public class Game implements IGame{
    private Random rng = new Random();
    private IGUI gui;
    private final String alphabet = "abcdefgh";
    private int max;

    public Game(IGUI gui) {
        this.gui=gui;
    }

    @Override
    public boolean play(int kwota){
        String combination = randomize(String.valueOf(kwota).length()+1);
        System.out.println("Podaj kobinacje " + (String.valueOf(kwota).length()+1) + " liter " + alphabet.substring(0,this.max));
        int counter=0;
        while(counter<combination.length()*2+3){
            if(check(askPlayer(combination.length()) ,combination)) return true;
            counter++;
            continue;
        }
        gui.showGamePoprawnaKombinacja(combination);
        return false;
    }

    private String randomize(int n){
        this.max = n+2;
        StringBuilder build =new StringBuilder();
        for(int i=0;i<n;i++){
            build.append(alphabet.charAt(rng.nextInt(min(n,alphabet.length()))));
        }
        return build.toString();
    }

    private String askPlayer(int length){

        Scanner scan = new Scanner(System.in);
        while(true){
            scan.reset();
            String result = scan.next();
            if(result.length()!=length) {
                gui.showGameError();
                continue;
            }
            boolean problem = false;
            for (int i=0;i<result.length();i++){
                char c = result.charAt(i);
                if(alphabet.substring(0,this.max).indexOf(c)==-1){
                    problem = true;
                    gui.showGameError();
                    break;
                }
            }
            if(!problem) return result;
        }
    }

    private boolean check(String s1, String s2) {
        if(s1.equals(s2)){
            gui.showGameCorrect(s2.length());
            return true;
        }
        StringBuffer combinationPlayer = new StringBuffer(s1);
        StringBuffer combination = new StringBuffer(s2);
        int black = 0;
        int white = 0;

        for(int i=combinationPlayer.length()-1;i>=0;i--){
            if(combinationPlayer.charAt(i)==combination.charAt(i)){
                combinationPlayer.deleteCharAt(i);
                combination.deleteCharAt(i);
                black++;
            }
        }
        for (int i=0;i<combination.length();i++){
            int index = combinationPlayer.indexOf(combination.substring(i,i+1));
            if(index!=-1){
                combinationPlayer.deleteCharAt(index);
                white++;
            }
        }

        gui.showGameProgress(black,white,s2.length());
        return false;
    }
}

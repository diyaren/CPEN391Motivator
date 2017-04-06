package com.firebase.uidemo.auth;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Navjashan on 06/04/2017.
 */

public class quotesProvider {

    public static ArrayList<String> quotes = new ArrayList<String>();

    public static void addQuotes(){
        quotes.add("I can't think of any better representation of beauty than someone who is unafraid to be herself\n\n\t\t\t\t- Emma Stone");
        quotes.add("Working hard is important. But there is something that matters even more: believing in yourself.\n\n\t\t\t\t- Harry Potter");
        quotes.add("If you don't like something, change it. If you can't change it, change your attitude.\n\n\t\t\t\t -Dr. Maya Angelou-");
        quotes.add("In the right light, at the right time, everything is extraordinary.\n\n\t\t\t\t - Aaron Rose");
        quotes.add("Even if you're on the right track, you'll get run over if you just sit there.\n\n\t\t\t\t -Will Rogers");
        quotes.add("At one point in your life you either have the thing you want or the reasons why you don't.\n\n\t\t\t\t -Andy Roddick");
        quotes.add("We could never learn to be brave & patient, if there were only joys in the world.\n\n\t\t\t\t -Helen Keller");
        quotes.add("Anger is an acid that can do more harm to the vessel in which it is stored than to anything on which it is poured.\n\n\t\t\t\t -Mark Twain");
        quotes.add("Knowing is not enough; we must apply. Wishing is not enough; we must do.\n\n\t\t\t\t -Johann Von Goethe");
        quotes.add("Nothing you wear is more important than your smile.\n\n\t\t\t\t -Connie Stevens");
        quotes.add("The man who does not read good books has no advantage over the man who can't read them.\n\n\t\t\t\t - Mark Twain");
        quotes.add("Perfection is not attainable, but if we chase perfection we can catch excellence.\n\n\t\t\t\t -Vince Lombardi");
        quotes.add("If you want something different You Gotta do something different.\n\n\t\t\t\t - Nathaniel Williams");
        quotes.add("You have enemies? Good. That means you've stood up for something, sometime in your life.\n\n\t\t\t\t -Churchill");
        quotes.add("Do just once what others say you can't do, and you will never pay attention to their limitations again.\n\n\t\t\t\t - James R. Cook");
        quotes.add("In order to make anything a reality, you have to dream about it first.\n\n\t\t\t\t - Adora Svitak");
        quotes.add("You will never change your life until you change something you do daily.\n\n\t\t\t\t - Mike Murdoch");
        quotes.add("Don't go around saying the world owes you a living. The world owes you nothing. It was here first.\n\n\t\t\t\t  - Mark Twain");
        quotes.add("Too many people spend money they haven't earned, to buy things they don't want, to impress people they don't like.\n\n\t\t\t\t -Will Smith\n");
        quotes.add("It isn't sufficient just to want - you've got to ask yourself what you are going to do to get the things you want.\n\n\t\t\t\t - Richard D. Rosen");
        quotes.add("Success is not final, failure is not fatal: it is the courage to continue that counts.\n\n\t\t\t\t - Winston Churchill");
    }

    public static String getQuotes(){
        Random rand = new Random();
        return quotes.get(rand.nextInt(quotes.size() - 1));
    }

}

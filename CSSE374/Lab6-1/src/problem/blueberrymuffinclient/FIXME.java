package problem.blueberrymuffinclient;

import java.util.concurrent.Executor;

import problem.blueberrymuffin.MuffinThreadExecutor;
import problem.blueberrymuffin.RealThread;
import problem.blueberrymuffin.VirtualThread;

public class FIXME {
	public static void main(String[] args) {
		// This code CRASHES because there are more than 4 threads running at
		// the same time, including the main thread.
		// TODO: implement a java.util.concurrent.Executor to make at most only
		// 4 threads simultaneously, including the main thread.
		Executor muffinThreadExecutor = MuffinThreadExecutor.getInstance();
		
		VirtualThread t1 = new VirtualThread(() -> {
			for (int i = 0; i < 100; i++) {
				System.out.println("t1 " + i);
			}
		}, muffinThreadExecutor);
		VirtualThread t2 = new VirtualThread(() -> {
			for (int i = 0; i < 100; i++) {
				System.out.println("t2 " + i);
			}
		}, muffinThreadExecutor);
		VirtualThread t3 = new VirtualThread(() -> {
			for (int i = 0; i < 100; i++) {
				System.out.println("t3 " + i);
			}
		}, muffinThreadExecutor);
		VirtualThread t4 = new VirtualThread(() -> {
			for(int i=0; i<100; i++){
				System.out.println("t4 "+i);
			}
		}, muffinThreadExecutor);
		

		t1.start();
		RealThread.printTotalThreads();

		t2.start();
		RealThread.printTotalThreads();

		t3.start();
		RealThread.printTotalThreads();

		t4.start(); // BOOM: Muffin CPU blows up here
		RealThread.printTotalThreads();
		
		// DONE: Uncomment and fix this exciting test after your code works.
		VirtualThread t5 = new VirtualThread(() -> {
			for(int i=0; i<100; i++){
				launchPrintingThread(i);
			}
		}, muffinThreadExecutor);
		t5.start();
		RealThread.printTotalThreads();
	}
	
	public static void launchPrintingThread(final int i){
		new VirtualThread(new Runnable(){
			public void run(){
				System.out.println("t5 "+ i);
			}
		}, MuffinThreadExecutor.getInstance()).start();
	}
}

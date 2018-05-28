package com.jeeplus.modules.demo;

import org.junit.Test;


public class Demotest{
	public static int a=0;
	@Test
	public void test1(){
		Thread t1=new Thread(){
			public void run(){
				for(int i=1;i<26;i++){
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					a+=i;
					
					System.out.println("t1:"+a);
				}
			}
		};
		Thread t2=new Thread(){
			public void run(){
				for(int i=26;i<51;i++){	
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					a+=i;						
					System.out.println("t2:"+a);
				}
			}
		};
		Thread t3=new Thread(){
			public void run(){
				for(int i=51;i<76;i++){
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					a+=i;	
					
					System.out.println("t3:"+a);
				}
			}
		};
		Thread t4=new Thread(){
			public void run(){
				for(int i=76;i<101;i++){
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					a+=i;
					
					System.out.println("t4:"+a);
				}
			}
		};
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		int b=0;
		for(int i=1;i<101;i++){
			b+=i;
		}
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(b);
	}
	@Test
	public void test2(){
		for(int i=11;i<100;i++){
			if(i/10>=i%10 && i%10!=0){
				System.out.print(i%10+"*"+i/10+"="+(i/10)*(i%10)+"   ");
			}
			if(i%10==0){
				System.out.print("\n");
			}
			
		}
		
		System.out.println(test3(8));
	}
	
	public  int test3(int n){
		if(n<=1){
			return 5;
		}		
		return (test3(n-1)+1)*2;
		
	}

}

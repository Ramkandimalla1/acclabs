package analysis;

import java.util.Arrays;
import java.util.Random;

public class preindex {
	
	public static int[] preindexlinear(int a[])
	{
		int s;
	   int pre[]=new int[a.length];
	   s=0;
	   for(int i=0;i<a.length;i++)
	   {
		   s=s+a[i];
		   pre[i]=s/(i+1);
	   }
	   return pre;
	   
	}
	public static int[] preindexquad(int a[])
	{
		int s;
		int pre[]=new int[a.length];
		  for(int i=0;i<a.length;i++)
		  {
			  s=a[0];
			  for(int j=1;j<=i;j++)
			  {
				  s=s+a[j];
			  }
			  pre[i]=s/(i+1);
		  }
		  return pre;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Random aR = new Random();
		long start,end;
		int n=0;
		
		 for(int i=600;i<1000;i++)
		 {
			 n=(int) Math.pow(2, i);
			 int s=-n/2;
			 int e=(3*n)/2;
			 if(s<e)
			 {
	 	 int a[] = aR.ints(100,s,e).toArray();
	     //int a[ ] = { 21, 23, 25, 31, 20, 18, 16 };
	 	 //System.out.println("array a = " + a.toString());
	     System.out.println(Arrays.toString(a));
			 
	     start = System.currentTimeMillis( );
	     System.out.println(start);
	     int ab[]=preindexlinear(a);
	     System.out.println(Arrays.toString(ab));
	    
	     end = System.currentTimeMillis( ); 
	     System.out.println(end);
	     System.out.println( "Time used: " + ( end - start ) );
	    
	     start = System.currentTimeMillis( );
	     
	     int abc[]=preindexlinear(a);
	     System.out.println(Arrays.toString(abc));
	     end = System.currentTimeMillis( ); 
	     System.out.println( "Time used: " + ( end - start ) );
	     
			 }
	}
	}
}

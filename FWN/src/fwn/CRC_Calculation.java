/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fwn;

import java.util.Arrays;

/**
 *
 * @author Omkar/ Shipra
 */
public class CRC_Calculation

{
 
    
public  int[] CRC_divide(int dividend [],int [] divisor, int [] rem)
    {
        int tot_length = dividend.length + divisor.length-1;
        int[] div = new int[tot_length];
        int[] rem_frame = new int[tot_length];
        int[]CRC =new int[tot_length];
        
      
        
        for(int i=0;i<dividend.length;i++)
        {
            div[i]=dividend[i];
        }
        
        for(int j=0; j<div.length; j++)
        {
            rem_frame[j] = div[j];
        }
      
        rem_frame=divide(div, divisor, rem_frame);
        
        for(int i=0;i<div.length;i++)           //append dividend and ramainder
        {
            CRC[i]=(div[i]^rem_frame[i]);
        }

        return CRC; 
    }   

    static int[] divide(int div[],int divisor[], int rem[])
    {
        int cur=0;
        while(true)
        {
            for(int i=0;i<divisor.length;i++)
            {
                rem[cur+i]=(rem[cur+i]^divisor[i]);
            }
                
            while(rem[cur]==0 && cur!=rem.length-1)
            {
                cur++;
            }
            
            if((rem.length-cur)<divisor.length)
            {
                break;
            }
        }
        return rem;
     }
    
 public  int[] CRC_error_detect(int dividend [],int [] divisor, int [] rem,int error_Option)
 {
     
     //Error_Addition adderror = new Error_Addition();
          
     if(error_Option!=0)
     { 
         double x = (int)(Math.random()*((8-0)+0))+0;
         int   rbit= (int)x;
         if(dividend[rbit]==1)
         {
            dividend[rbit]=0;
            for(int j=0; j<dividend.length-2; j++)
            {
              rem[j] = dividend[j];
            }
            
            rem=divide(dividend, divisor,rem);
         }
         else
         {
            dividend[rbit]=1;
            for(int j=0; j<dividend.length-2; j++)
            {
              rem[j] = dividend[j];
            
              rem=divide(dividend, divisor,rem);
            }
         }
         
         if(dividend.length==10)
         {
            dividend[8]=rem[8];
            dividend[9]=rem[9];
         }
         else if(dividend.length==12)
         {
            dividend[8]=rem[8];
            dividend[9]=rem[9];
            dividend[10]=rem[10];
            dividend[11]=rem[11];
         }
         
         for(int i=0; i< rem.length; i++)
         {
            if(rem[i]!=0)
            {   
                System.out.println("\tCRC Check fail : TX and RX Do not Match"+"\t");
                break;
            }
            if(i==rem.length-1)
                System.out.println("\tCRC check success  : No error");
         }
     }
     else
     {
         for(int j=0; j<dividend.length; j++)
         {
             rem[j] = dividend[j];
         }
         
         rem=divide(dividend, divisor,rem);
         
         for(int i=0; i< rem.length; i++)
         {
             if(rem[i]!=0)
             {
                 System.out.println("\tCRC ERRROR : TX and RX Do not Match");
                 break;
             }
             
             if(i==rem.length-1)
                 System.out.println("\tCRC check success  : No error");
         }
     }
     return dividend;
 }}

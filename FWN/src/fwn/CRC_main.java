/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fwn;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Omkar/ Shipra
 */
public class CRC_main {
    
    public static void main(String[] args) throws IOException 
    {
        
        Random frame = new Random();
        int[]Divisor_Frame=new int[8];
        int[]Dividend_Frame=new int[8];
        int size =0;
        int random_frame_no2=-1;
        
      
        System.out.println("-------------------------------------CRC------------------------------- ");

        String userHomePath = System.getProperty("user.dir");
        File simulation = new File(userHomePath, "Animation.gif");
   
        try {
            
            Desktop.getDesktop().open(simulation);
            
        } catch (Exception e) {
        }
 
        System.out.println("******************************CRC Logic******************************************** :\n  ");
        System.out.println("T = n-bit frame to be transmitted\n" +
            "\n K = k-bit block of data; the first k bits of T\n" +
            "\n (n – k) = (n-k)bit FCS; the last (n – k) bits of T\n" +
            "\n Divisor = pattern of n–k+1 bits; this is the predetermined divisor\n" +
            "\n Q = Quotient\n" +
            "\n R = Remainder\n\n");
        System.out.println("******************************CRC Logic******************************************** :\n  ");
        System.out.println("General Description:\n");
        System.out.println("Frame Size   :                   8   bits \n");
        System.out.println("No of Frames :                   128 frames \n");
        System.out.println("Total TX/RX Data Size :          1024 bits\n");
            
        System.out.println("Select A Comfiguration: \n");
        System.out.println("1.CRC-2 generator Error Rate 10^-3 (1 bit error in every 1000 bits) \n");            
        System.out.println("2.CRC-4 generator Error Rate 10^-2 (1 bit error in every 100 bits) \n");
        System.out.println("3.Exit");
        System.out.println(" *** Note : The generator works on Pseudorandom mechanism");
        System.out.println("-------------------------------------CRC------------------------------- ");
          Scanner reader = new Scanner(System.in);  // Reading from System.in
        
        int input = reader.nextInt(); // Scans
        
        if (input==3)
        {
            System.out.println("Thank you");
            return;
        }
            
        int[]CRC_Data=new int[10];
        int[]CRC_Data_check=new int[10];
        int[][]CRC_data= new int[130][11];
        
        CRC_Frame_Generator Frame=new CRC_Frame_Generator();
        CRC_Calculation Calculate=new CRC_Calculation();
        CRC_Calculation CRCCheck=new CRC_Calculation();
//        Error_Addition AddError = new Error_Addition();
        
        int l=0;
        int frame_range = 0;
        
        if(input==1)
        {
            Divisor_Frame=Frame.CRC_generate_divisor(3);
            size=10;
            frame_range = 0;
        }
        else if(input==2)
        {
            Divisor_Frame=Frame.CRC_generate_divisor(5);
            size=12;
            random_frame_no2=(int)(Math.random()*((128-1)+1))+1;
            frame_range =10 ;
        }
        
        int[]remainder_Frame=new int[size];
        System.out.println("Predecided Pattern"+Arrays.toString(Divisor_Frame)+"\n");
        System.out.print(" Message without CRC\t");
        System.out.print("\t\tMessage+CRC added\t");
        System.out.print("\t\t\tCRC Check Status \t");
        
        double x = (int)(Math.random()*((128-1)+1))+1;
        int random_frame_no1 = (int)x;
        int Min=0;
        int [] Error_Frame = new int[11];
        
        for(int i=1;i<=frame_range;i++)
        {
            Error_Frame[i] = (Min + ((int)(Math.random() * (((12*i) - Min) + 1))));
            Min +=12;
            System.out.println("");
        }
        
        int error_high ;
        for(int k = 1;k<=128;k++)
        {
            error_high = Arrays.binarySearch(Error_Frame,k);
            Dividend_Frame=Frame.CRC_generate_dividend();
            
            System.out.print("\n"+Arrays.toString(Dividend_Frame));
            
            CRC_Data=Calculate.CRC_divide(Dividend_Frame, Divisor_Frame, remainder_Frame);
            System.out.print("\t\tTX:"+Arrays.toString(CRC_Data));
            System.out.print("\t");
            
            if(((error_high>0)) ||(k == random_frame_no1))
            {
                CRC_Data_check=CRCCheck.CRC_error_detect(CRC_Data, Divisor_Frame, remainder_Frame,1);
            }
            else
            {
                CRC_Data_check=CRCCheck.CRC_error_detect(CRC_Data, Divisor_Frame, remainder_Frame,0);
            }
            
            System.out.print("\t\t\t\t\tRX:"+Arrays.toString(CRC_Data_check)+"\n");
        }
    }

  
    }  

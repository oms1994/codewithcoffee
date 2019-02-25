/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fwn;

import java.util.Random;

/**
 *
 * @author Omkar/ Shipra
 */
public class CRC_Frame_Generator {
    
    public int[] CRC_generate_dividend()
    {
        int fbit=0;
        Random  sequence = new Random();
        int [] dividend_frame = new int[8];
        
        for (int j=0;j<=7;j++)
        {
            boolean i = sequence.nextBoolean();
            if(i==true)
            {
                fbit=1;
                dividend_frame[j]= fbit;
            }
            else
            {
                fbit=0;
                dividend_frame[j]= fbit;
            }
        }
        
        return dividend_frame;
    }
    
    public  int[] CRC_generate_divisor(int div_len)
    {
        int fbit=0;
        Random  sequence = new Random();
        int [] divisor_frame  = new int[div_len];
        
        for (int j=0;j<=div_len-1;j++)
        {
            boolean i = sequence.nextBoolean();
            if(i==true)
            {
                fbit=1;
                divisor_frame[j]= fbit;
            }
            else
            {
                fbit=0;
                divisor_frame[j]= fbit;
            }
        }
        
        if(divisor_frame[0]==0)
        {
            divisor_frame[0]=1;
        }
        
        return divisor_frame;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cryptography.project.decryption;

import com.letter.cryptography.LetterInNumber;

/**
 *
 * @author ahsan
 */
public class CryptographyProjectDecryption 
{
    public static void main(String[] args) 
    {
        //String cipherText = "EESMSIMODT PTQLLLJZR UH YGZPTH 12#@&";
        System.out.println("Padd the Z with '.' character");
        String cipherText = "EGIKMOQSUWYZABCD GIKMOQSUWY.....";
        int lengthOfPlaintext = cipherText.length();
        System.out.println("Input Ciphertext is "+cipherText);
        System.out.println("Length of plaintext is "+lengthOfPlaintext);
        LetterInNumber letterInNumberCheck = new LetterInNumber(cipherText.charAt(1));
        System.out.println("Character number conversion is "+letterInNumberCheck.getNumber());
        
        LetterInNumber numberInLetterCheck = new LetterInNumber(26);
        System.out.println("Number conversion is in Character "+numberInLetterCheck.getLetter());
        
        
        int i,j,k,row=0,column=16;
        
        if(cipherText.length() % 16 == 0) 
        {
            row = cipherText.length() / 16;
        }
        else 
        {
            row = cipherText.length() / 16 + 1;
        }
        
       
        
        char ciphertextRotor [][] = new char [row][column];
        int pointer = 0;
        int padding = cipherText.length() % 16;
        
        for(i=0;i<row;i++)
        {
            
                for(j=0;j<column;j++)
                {
                    
                    if(pointer < cipherText.length() && cipherText.charAt(pointer) != ' ')
                    {
                        ciphertextRotor [i][j] = cipherText.charAt(pointer);
                        pointer++;
                    }
                    else 
                    {
                        break;
                    }
                }
                pointer++;
            //System.out.println(pointer);
        }
        
        for(i=0;i<row;i++)
        {
            for(j=0;j<column;j++)
            {
                //ciphertextRotor [row][column] = plaintext.charAt(j);
                 System.out.print(" ("+i+" "+j+")"+ciphertextRotor [i][j]+"   ");
            }
            System.out.println();
        }
        
        for(i=0;i<row;i++)
        {
            for(j=0;j<column;j++)
            {
                if(Character.isLetter(ciphertextRotor[i][j]) && ciphertextRotor[i][j] != ' ')
                {
                    LetterInNumber cipherInNumber = new LetterInNumber(ciphertextRotor[i][j]);
                    
                    int cipherValue =  (cipherInNumber.getNumber() - i) % 26;       //(c-rowNum) mod 26
                    if(cipherValue < 0)
                    {
                        cipherValue = cipherValue + 26;
                    }    
                    if(cipherValue == 0)
                    {
                        cipherValue = 26;
                    }
                    LetterInNumber cipherInLetter = new LetterInNumber(cipherValue);
                    ciphertextRotor[i][j] = cipherInLetter.getLetter();
                }
            }
        }
        
        
        System.out.println();System.out.println();
        for(i=0;i<row;i++)
        {
            for(j=0;j<column;j++)
            {
                //ciphertextRotor [row][column] = plaintext.charAt(j);
                 System.out.print(" ("+i+" "+j+")"+ciphertextRotor [i][j]+"   ");
            }
            System.out.println();
        }
        
        
        String CipherTextToPlaintext = "";
        String generatePlaintext = "";
        for(i=0;i<row;i++)
        {
            for(j=0;j<column;j++)
            {
                CipherTextToPlaintext = CipherTextToPlaintext + ciphertextRotor [i][j];
            }
            CipherTextToPlaintext = CipherTextToPlaintext + ' ';
        }
        
        System.out.println("Generate Ciphertext is : " +CipherTextToPlaintext);
        
        pointer = 0;
        String exactPlaintext = "";
        for(i=0;i<column;i++)
        {
            for(j=0;j<row;j++)
            {
                if(pointer != cipherText.length())
                {
                    if(ciphertextRotor[j][i] != '.')
                    {
                        exactPlaintext = exactPlaintext + ciphertextRotor[j][i];
                        pointer++;
                    }
                }
            }
            //System.out.println(pointer);
        }
        
        System.out.println("\nGenerate exactCipherext is : " +exactPlaintext);
        System.out.println();
        System.out.println("Assume our key is 4 ");
        
        for(i=0;i<exactPlaintext.length();i++)
        {
            if(Character.isLetter(exactPlaintext.charAt(i)) && exactPlaintext.charAt(i) != ' ')
            {
                LetterInNumber letterInNumber = new LetterInNumber(exactPlaintext.charAt(i));
                int cipherValue =  (letterInNumber.getNumber() - 4) % 26;       //(p+n) mod 26
                
                if(cipherValue == 0)
                {
                    cipherValue = 26;
                }
                if(cipherValue < 0)
                {
                    cipherValue = cipherValue + 26;
                }
                    
                LetterInNumber numberInLetter = new LetterInNumber(cipherValue);
                
                char cipherCharacter = numberInLetter.getLetter();
                generatePlaintext = generatePlaintext + cipherCharacter;
            }
            else if(exactPlaintext.charAt(i) != ' ' || Character.isWhitespace(exactPlaintext.charAt(i)))
            {
                generatePlaintext = generatePlaintext + exactPlaintext.charAt(i);
            }
        }
        System.out.println("\nGenerate Final Plaintext is : " +generatePlaintext);
        

    }    
}

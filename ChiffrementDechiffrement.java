import java.math.BigInteger;

import static java.lang.System.out;

public class ChiffrementDechiffrement
{
    public static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";

    /*********************************************************************\
     *****CHIFFREMENT ET DECHIFFREMENT AFFINE*****
     *////////////////////////////////////////////////////////////////////*

    public static String AffineCrypt(String msg, int a, int b)
    {
        StringBuilder c = new StringBuilder();
        msg = msg.toLowerCase();
        for (int i = 0; i < msg.length(); i++)
        {
            char caract = msg.charAt(i);

            if(!ALPHABET.contains(msg.subSequence(i, i+1)))

            {
                c.append(caract);
            }
            else
            {
                caract = (char) ((a * (caract - 97) + b) % 26 + 97);
                c.append(caract);
            }
        }
        return (c.toString());
    }

    public static String AffineDecrypt(String msg,int a,int b)
    {
        msg=msg.toLowerCase();
        StringBuilder p = new StringBuilder();
        BigInteger inverse = BigInteger.valueOf(a).modInverse(BigInteger.valueOf(26));
        for (int i = 0; i < msg.length(); i++)
        {
            char caract = msg.charAt(i);
            if(!ALPHABET.contains(msg.subSequence(i, i+1)))
            {
                p.append(caract);
            }
            else
            {
                int decoder = inverse.intValue() * (caract - 'a' - b + 26);
                caract = (char) (decoder % 26 + 'a');
                p.append(caract);
            }
        }
        return (p.toString());
    }



    /*********************************************************************\
     *****CHIFFREMENT ET DECHIFFREMENT DE CESAR*****
     *////////////////////////////////////////////////////////////////////*
    public static void CesarCrypt(String msg, int decalage)
    {
        msg = msg.toLowerCase();
        String msgChiffrer= "";
        for(int i=0;i<msg.length();i++)
        {
            if( !ALPHABET.contains(msg.subSequence(i, i+1)) )
            {
                msgChiffrer+= msg.charAt(i);
            }
            else
            {
                int positionCaract = ALPHABET.indexOf(msg.charAt(i));
                int valeurCle = (decalage + positionCaract) % 26;
                char remplaceValeur = ALPHABET.charAt(valeurCle);
                msgChiffrer += remplaceValeur;
            }
        }
        System.out.println("Votre message chiffrer est : " + msgChiffrer);
    }

    public static void CesarDecrypt(String msg,int decalage)
    {
        msg = msg.toLowerCase();
        String msgDechiffrer = "";
        for (int i = 0; i < msg.length(); i++)
        {
            if(!ALPHABET.contains(msg.subSequence(i, i+1)))
            {
                msgDechiffrer+= msg.charAt(i);
            }
            else
            {
                int positionCaract = ALPHABET.indexOf(msg.charAt(i));
                int valeutrCle = (positionCaract - decalage) % 26;
                if (valeutrCle < 0)
                {
                    valeutrCle = ALPHABET.length() + valeutrCle;
                }
                char replaceVal = ALPHABET.charAt(valeutrCle);
                msgDechiffrer += replaceVal;
            }
        }

        System.out.println(msgDechiffrer);
    }


    /*********************************************************************\
     *****CHIFFREMENT ET DECHIFFREMENT DE VIGENERE*****
     *////////////////////////////////////////////////////////////////////*
    public static char[][] table = new char[26][26];

    public static void TableVigenere()
    {
        //Construction de la table de vigenere
        char ch = 'a';

        for (int i = 0; i < 26; i++)
        {
            for (int j = 0; j < 26; j++)
            {
                int x = ((((int) ch - 97) + j) % 26) + 97;
                table[i][j] = (char) x;
            }

            ch++;
        }
    }

    public static void printTable()
    {
        //Pour afficher la table de vigenere
        for (int i = 0; i < 26; i++)
        {
            for (int j = 0; j < 26; j++)
            {
                out.print(table[i][j] + " ");
            }
        }
    }

    public static char getCharDeTable(int ligne, int colonne)
    {
        //recuperation d'un caractere dans la table de vigenere
        return table[ligne][colonne];
    }

    public static String VigenereCrypt(String msg, String cle)
    {
        String msgChiffrer = "";
        msg = msg.toLowerCase();
        cle = cle.toLowerCase().replaceAll(" ", "");

        int longueurCle  = cle.length();

        for (int i = 0; i < msg.length(); i++)
        {

            if( !ALPHABET.contains(msg.subSequence(i, i+1)) )
            {
                msgChiffrer+= msg.charAt(i);
            }
            else
            {

                int x = ((int) (msg.charAt(i)) - 97);
                int y = (((int) (cle.charAt((i % longueurCle )))) - 97);

                msgChiffrer += getCharDeTable(x, y);
            }

        }
        return msgChiffrer;
    }

    public static String VigenereDecrypt(String msg, String cle)
    {
        String msgDechiffrer = "";
        msg = msg.toLowerCase();
        cle = cle.toLowerCase().replaceAll(" ", "");
        int longueurCle = cle.length();

        for (int i = 0; i < msg.length(); i++)
        {
            if( !ALPHABET.contains(msg.subSequence(i, i+1)) )
            {
                msgDechiffrer+= msg.charAt(i);
            }
            else
            {
                int x = (((int) msg.charAt(i)) - 97);
                int y = (((int) (cle.charAt((i % longueurCle)))) - 97);

                char ch = msg.charAt(i);
                int j;
                for (j = 0; j < 26; j++)
                {
                    if (getCharDeTable(y, j) == ch)
                    {
                        break;
                    }
                }

                msgDechiffrer += getCharDeTable(0, j);
            }
        }
        return msgDechiffrer;
    }


    /*********************************************************************\
     *****CHIFFREMENT ET DECHIFFREMENT PAR SUBTITUTION*****
     *////////////////////////////////////////////////////////////////////*
    private static String SubtitutionCrypt(String msg, String cle) {
        String msgChiffrer = "";
        msg = msg.toLowerCase();
        cle = cle.toLowerCase();
        for (int i = 0; i < msg.length(); i++) {
            if(!ALPHABET.contains(msg.subSequence(i, i+1)))
            {
                msgChiffrer += msg.charAt(i);
            }
            else
            {
                int x = msg.charAt(i) - 97;
                msgChiffrer  += cle.charAt(x);
            }
        }
        return msgChiffrer;
    }

    private static String SubtitutionDecrypt(String msg, String cle)
    {
        String msgDechiffrer = "";
        msg = msg.toLowerCase();
        cle = cle.toLowerCase();
        for (int i = 0; i < msg.length(); i++)
        {
            if(!ALPHABET.contains(msg.subSequence(i, i+1)))
            {
                msgDechiffrer += msg.charAt(i);
            }
            else
            {
                char y = (char) (cle.indexOf(msg.charAt(i)) + 97);
                msgDechiffrer  += y;
            }
        }
        return msgDechiffrer;
    }


}

package Questao1;

import java.io.IOException;
import java.util.*;

public class Data implements Comparable<String>, Comparator<Data> 
{
    
    public enum DiasPorMes // Enum dos Meses com os limites dos dias
    {
        Jan(31),
        Fev(28),
        Mar(31),
        Abr(30),
        Mai(31),
        Jun(30),
        Jul(31),
        Ago(31),
        Set(30),
        Out(31),
        Nov(30),
        Dez(31);
        private final Integer getDias;

        DiasPorMes(Integer dias_) 
        {
            getDias = dias_;
        }
    }

    private String dia; // Dia
    private String mes; // Mes
    private String ano; // Ano

    public Data(String dia_, String mes_, String ano_) // Construtor
    {     
        verificaDataInvalida(dia_, mes_, ano_); // Checar a data antes de inserir de construir realmente
        
        dia = dia_;
        mes = mes_;
        ano = ano_;
    }       

    public int verificaDataInvalida(String dia_, String mes_, String ano_)  // Verificar data
    {
        int retorno = 0; // Aux para retornar
        String valores = dia_ + " " + mes_ + " " + ano_; // Padrão das datas para mandar para o comparador e escrever no arquivo
        
        try
        {
            if(!isInt(dia_) || !isInt(mes_) || !isInt(ano_)) // Se a data não for inteira da throw na excessão
            {
                retorno = 1;
                throw new DadosIncorretosException();
            } 
            else if(compareTo(valores) == 1) // Manda o padrão para o comparador se a data for invalida da throw na excessão
            {
                retorno = 1;
                throw new DataInvalidaException();
            }
        }
        catch(DadosIncorretosException e) //Escreve a data com o erro no arquivo
        {
            Principal1.escreverNoArquivo(valores + " " + "DadosIncorretosException: " + e.getMessage() + "\n", Principal1.caminho); 
        }
        catch(DataInvalidaException e) //Escreve a data com o erro no arquivo
        {
            try 
            {
                Principal1.escreverNoArquivo(valores + " " + "DataInvalidaExceptio: " + e.getMessage() + "\n", Principal1.caminho);
            } 
            catch(IOException ex) 
            {
                throw new RuntimeException(ex);
            }
        }
        finally 
        {
            return retorno;
        }
    }

    @Override
    public int compareTo(String data_)
    {
        String[] valores = data_.split(" "); // Pega o padrão das datas e tira os espaços salvando cada valor em uma parte do vetor
        
        Integer dia = stringParaInteiro(valores[0]); //Transforma string do dia para int
        Integer mes = stringParaInteiro(valores[1]); //Transforma string do mes para int
        Integer ano = stringParaInteiro(valores[2]); //Transforma string do ano para int
        
        if( dia < 1 || dia > 31 || mes < 1 || mes > 12 || ano < 1) // Checa pra ver se os dados estão entre 1 a 31 / 1 a 12 / maior que 1
        {
            return 1;
        }
        
        else if(DiasPorMes.values()[mes-1].getDias < dia) // Checa os limites dos dias dos meses
        {
            return 1;
        }
        
        return 0;
    }


    @Override
    public int compare(Data dataUm_, Data dataDois_) 
    {
        String dataUm = dataUm_.dia + " " + dataUm_.mes + " " + dataUm_.ano; // Transforma para o padrão das datas
        String dataDois = dataDois_.dia + " " + dataDois_.mes + " " + dataDois_.ano; // Transforma para o padrão das datas
        
        if(compareTo(dataUm) == 1 || compareTo(dataDois) == 1) // Primeiro compara pra ver se as datas estão corretas
        {
            return -10;
        }
        
        Integer anoMenor = compareInteger(stringParaInteiro(dataUm_.ano) , stringParaInteiro(dataDois_.ano)); // Compara se os anos estão corretos
        
        if(anoMenor == 0)
        {
            Integer mesMenor = compareInteger(stringParaInteiro(dataUm_.mes) , stringParaInteiro(dataDois_.mes)); // Compara se os meses estão corretos se os anos forem iguais
            if(mesMenor == 0)
            {
                return compareInteger(stringParaInteiro(dataUm_.dia) , stringParaInteiro(dataDois_.dia)); // Compara se os dias estão corretos se os meses forem iguais
            }
            else 
            {
                return mesMenor; 
            }
        }
        else 
        {
            return anoMenor;
        }
    }

    public int compareInteger(int i, int j) // Compara os inteiros
    {
        if(i > j)
        {
            return 1; // Retorna 1 se i for maior
        }
        else if(i < j) 
        {
            return -1; // Retorna -1 se j for maior
        }
        else 
        {
            return 0; // Retorna 0 se for igual
        }
    }

    public int stringParaInteiro(String string) // Transforma string para um valor Inteiro
    {
        if(isInt(string)) // Se for um inteiro transforma para inteiro
        {
            return Integer.parseInt(string);
        }
        return 0;
    }
    
    private static boolean isInt(String string)  // Checa para ver se é um valor inteiro
    {
        if(string != null && string.matches("[0-9]*"))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

}

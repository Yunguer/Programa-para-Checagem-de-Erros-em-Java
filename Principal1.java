package Questao1;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Principal1 
{   
    static String caminho = "C:\\Users\\Usuário\\Documents\\NetBeansProjects\\Questao1\\src\\Questao1\\"; // Caminho para a pasta
    public static void main(String[] args) throws IOException 
    {
        File aux = new File(caminho + "Exceções.txt"); // Caminho + TxT
        aux.delete(); // Limpa o que tem no arquivo
        
        String arq = caminho + "Arquivo.txt"; // Caminho + TxT
        BufferedReader b = new BufferedReader(new FileReader(arq)); // Cria o buffer para o arquivo
        
        List<DatasCidadao> listaCidadaos = new ArrayList<>(); // Lista para os Cidadaõs
        String linha = b.readLine(); // Lê a linha

        while (linha != null)  // Enquanto a linha não for nula
        {
            String[] datas = linha.split(" "); // Separa as datas por espaço
            Data dataNascimento = new Data(datas[0], datas[1], datas[2]); // Salva a primeira data
            Data dataCasamento = new Data(datas[3], datas[4], datas[5]); // Salva a segunda data
            Data dataObito = new Data(datas[6], datas[7], datas[8]); // Salva a terceira data

            verificaDatas(dataNascimento, dataCasamento, dataObito, linha); // Verifica as datas pra ver se não existe casos da data de obito ser antes da de nascimento etc ...

            DatasCidadao cidadao = new DatasCidadao(dataNascimento,dataCasamento,dataObito); // Cria cidadão
            listaCidadaos.add(cidadao); // Adiciona a lista
            
            linha = b.readLine(); // Lê a linha e recomeça 
        }
        
        b.close(); // Fecha o buffer
    }
    
    public static void escreverNoArquivo(String l, String caminho) throws IOException // Função para escrever no arquivo
    {
        BufferedWriter b = new BufferedWriter(new FileWriter(caminho + "Exceções.txt", true)); // Cria um buffer com Caminho + TxT

        b.write(l + "\n"); // Escreve no arquivo
        b.close(); // Fecha o buffer
    }
    
    public static void verificaDatas(Data nasc, Data cas, Data obt, String linha) throws IOException  // Verifica as datas
    {
        try
        {
            isDataAnterior(nasc,cas); // Verifica o caso de DataAnteriorException
            isDataAnterior(nasc,obt); // Verifica o caso de DataAnteriorException
            isDataAnterior(cas,obt); // Verifica o caso de DataAnteriorException
        }
        catch(DataAnteriorException e)
        {
            escreverNoArquivo(linha + " " + "DataAnteriorException: " + e.getMessage() + "\n", caminho); // Escreve as Datas com o Tipo de Erro
        }
    }
    
    public static void isDataAnterior(Data pdata, Data sdata) throws DataAnteriorException //Compara pra ver se as datas não infligem a lógica
    {
        if(pdata.compare(pdata, sdata) == 1)
        {
            throw new DataAnteriorException();
        }
    }
    
}


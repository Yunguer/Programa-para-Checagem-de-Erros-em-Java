package Questao1;

public class DatasCidadao 
{
    private final Data dataNascimento; // Data de nascimento do Cidadão
    private final Data dataCasamento; // Data de casamento do Cidadão
    private final Data dataObito; // Data de obito do Cidadão

    public DatasCidadao(Data dataNascimento_, Data dataCasamento_, Data dataObito_) // Construtor do Cidadão
    {
        dataNascimento = dataNascimento_;
        dataCasamento = dataCasamento_;
        dataObito = dataObito_;
    }
}

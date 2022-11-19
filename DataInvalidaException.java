package Questao1;

public class DataInvalidaException extends Exception // Excessão de não ser uma data valida
{
    @Override
    public String getMessage() // Mensagem da excessão
    {
        return "O Valor da data é invalido";
    }
}

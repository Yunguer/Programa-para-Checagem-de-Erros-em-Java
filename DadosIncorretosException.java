package Questao1;

public class DadosIncorretosException extends Exception // Excessão de não ser um Inteiro
{

    @Override
    public String getMessage() // Mensagem da excessão
    {
        return "O Valor não é do tipo Inteiro";
    }
}

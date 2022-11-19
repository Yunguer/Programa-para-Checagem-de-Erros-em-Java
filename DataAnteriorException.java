package Questao1;

public class DataAnteriorException extends Exception // Excessão de datas conflitantes
{
    @Override
    public String getMessage() // Mensagem da excessão
    {
        return "A segunda data é anterior à primeira";
    }
}

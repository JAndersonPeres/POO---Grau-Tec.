using System;
using System.Collections.Generic;

namespace ATV5
{
    public class AcessoNegadoException : Exception
    {
        public AcessoNegadoException(string msg) : base(msg){}
    }

    public class SistemaAuth
    {
        private Dictionary<string,string> bancoDeDados = new Dictionary<string, string>();

        public SistemaAuth()
        {
            bancoDeDados.Add("admin","admin");
            bancoDeDados.Add("Aluno","gAluno123");
            bancoDeDados.Add("Professor","gProf456");
        }

        public void FazerLogin(string usuario, string senha)
        {
            if (!bancoDeDados.ContainsKey(usuario))
            {
                throw new AcessoNegadoException("Usuário não Cadastrado.");
            }

            if(bancoDeDados[usuario] != senha)
            {
                throw new AcessoNegadoException("Senha Inválida|");
            }

            Console.WriteLine($"[SUCESSO] Bem-vindo, {usuario}!");
        }
    }

    class Program
    {
        static void Main(string[] args)
        {
            SistemaAuth sistema = new SistemaAuth();

            Console.WriteLine("---TENTATIVA 1(Correta)---");
            try
            {
                sistema.FazerLogin("admin","admin");
            }
            catch (Exception ex)
            {
                Console.WriteLine("Erro: " + ex.Message);
            }

            Console.WriteLine("---TENTATIVA 2(Senha Errada)---");
            try
            {
                sistema.FazerLogin("Aluno","Aluno@GrauT");
            }
            catch (Exception ex)
            {
                Console.WriteLine($"[ALERTA DE SEGURANÇA] {ex.Message}");
            }

            Console.WriteLine("---TENTATIVA 3(Usuário Inexistente)---");
            try
            {
                sistema.FazerLogin("Edvaldo","Edv@25");
            }
            catch (Exception ex)
            {
                Console.WriteLine($"[ERRO] {ex.Message}");
            }
            finally
            {
                Console.WriteLine("\nProcesso de validação finalizado");
            }

            Console.ReadKey();
        }
    }
}
using System;
using System.IO;

namespace ATV6
{
    public static class Logger
    {
        private static string arquivoLog = "sistema_log.txt";

        public static void RegistrarAcao(string acao)
        {
            using (StreamWriter sw = File.AppendText(arquivoLog))
            {
                sw.WriteLine($"{DateTime.Now}-{acao}");
            }
            Console.WriteLine("Log salvo no disco.");
        }

        public static void LerLogs()
        {
            Console.WriteLine("\n--- LENDO HISTÓRICO DE LOGS ---");
            if (File.Exists(arquivoLog))
            {
                using (StreamReader sr = File.OpenText(arquivoLog))
                {
                    string linha;
                    while ((linha = sr.ReadLine()) != null)
                    {
                        Console.WriteLine(linha);
                    }
                }
            } 
            else
            {
                Console.WriteLine("Nenhum log encontrado.");
            }
        }
    }

    class Program
    {
        static void Main(string[] args)
        {
            Logger.RegistrarAcao("Sistema Inicializado.");
            Logger.RegistrarAcao("Usuário 'admin' logou.");
            Logger.RegistrarAcao("Erro de conexão simulado.");

            Logger.LerLogs();

            Console.WriteLine("\n Verifique a pasta do executável para encontra \"sistema_log.txt\"");
            Console.ReadKey();
        }
    }
}
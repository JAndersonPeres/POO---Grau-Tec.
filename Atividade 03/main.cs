using System;
using System.Collections.Generic;

namespace ATV3
{
    public class Funcionario
    {
        public string Name {get; set;}
        public double Salary {get; set;}

        public virtual double CalcularBonificacao()
        {
            return this.Salary * 0.10;
        }
    }

    public class Gerente : Funcionario
    {
        public override double CalcularBonificacao()
        {
            return base.CalcularBonificacao() + 500.00 + (this.Salary * 0.10);
        }
    }

    public class Estagiario : Funcionario
    {
        public override double CalcularBonificacao()
        {
            return 100.00;
        }
    }

    class Program
    {
        public static void Main(string[] args)
        {
            Console.WriteLine("=== SISTEMA DE RH - ATV3 ===\n");

            List<Funcionario> folhaDePagamento = new List<Funcionario>();

            Funcionario f1 = new Funcionario{Name = "Anderson", Salary = 1518.00};
            Gerente g1 = new Gerente{Name = "Cefras", Salary = 2518.00};
            Estagiario e1 = new Estagiario{Name = "Felipe", Salary = 800.00};

            folhaDePagamento.Add(f1);
            folhaDePagamento.Add(g1);
            folhaDePagamento.Add(e1);

            double totalBonificacoes = 0;

            foreach (var funcionario in folhaDePagamento)
            {
                double bonus = funcionario.CalcularBonificacao();
                totalBonificacoes += bonus;

                Console.WriteLine($"Funcionário: {funcionario.Name}");
                Console.WriteLine($"Salário Base: R$ {funcionario.Salary:F2}");
                Console.WriteLine($"Bônus Calculado: R$ {bonus:F2}");;
                Console.WriteLine("-----------------------------------------");

                Console.WriteLine($"TOTAL PAGO EM BÔNUS: R$ {totalBonificacoes:F2}");
                Console.WriteLine("-----------------------------------------");
            }

            Console.WriteLine("\nPressione qualquer tecla para encerrar...");
            Console.ReadKey();
        }
    }
}
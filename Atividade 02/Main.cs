using System;

namespace MyCompiler {

    public class ContaBancaria {
        public string NumeroDaConta {get; private set;}
        public string Titular {get; private set;}
        public double Saldo {get; private set;}

        public ContaBancaria(string numero, string titular){
            this.NumeroDaConta = numero;
            this.Titular = titular;
            this.Saldo = 0.0;
        }

        public void Depositar(double valor){
            if (valor > 0){
                this.Saldo += valor;
                Console.WriteLine($"[SUCESSO] Depósito de R$ {valor} realizado. Novo saldo: R$ {this.Saldo}");
            } else {
                Console.WriteLine("[ERRO] O valor do depósito é inválido! (menor ou igual a zero)");
            }
        }

        public bool Sacar(double valor){
            if (valor > 0 && this.Saldo >= valor) {
                this.Saldo -= valor;
                Console.WriteLine($"[SUCESSO] Saque de R$ {valor} realizado. Novo saldo: R$ {this.Saldo}");
                return true;
            }
            Console.WriteLine("[ERRO] Saldo Insuficiente ou Valor Inválido");
            return false;
        }

        public void ExibirExtrato(){
            Console.WriteLine($"--- Extrato: {Titular} (Conta: {this.NumeroDaConta}) ---");
            Console.WriteLine($"Saldo Atual: R$ {this.Saldo:F2}");
            Console.WriteLine("-----------------------------------------------");
        }
    }
    
    class Program {
        public static void Main(string[] args) {
            Console.WriteLine("=== INICIANDO SISTEMA BANCÁRIO (AULA 02) ===\n");

            ContaBancaria minhaConta = new ContaBancaria("45612-3", "Anderson");

            minhaConta.ExibirExtrato();

            minhaConta.Depositar(500.00);
            minhaConta.Sacar(200.00);
            minhaConta.ExibirExtrato();

            Console.WriteLine("Tentando sacar valor maior que o saldo...");
            minhaConta.Sacar(1000.00);

            //minhaConta.Saldo = 1000.00;
        }
    }
}
# Laboratório 1 - Sistemas Distribuídos (UTFPR Apucarana - 2025.1)
Este diretório contém a implementação do Laboratório 1 da disciplina de Sistemas Distribuídos, ministrada pelo Prof. Dr. Lúcio Rocha no semestre 2025.1 na UTFPR Apucarana.
---

## 📌 Objetivo

O objetivo deste laboratório foi **estender o código do Lab1**, introduzindo novas funcionalidades de **leitura e escrita** em um arquivo de fortunas (`fortune-br.txt`).  
As operações implementadas são:  

1. **Ler uma fortuna** (selecionada aleatoriamente do arquivo).  
2. **Escrever uma nova fortuna** (adicionando-a ao arquivo).  

---

## 📂 Estrutura do projeto 
/
├── src/
│   ├── Principal_v0.java   # Código principal com as operações de leitura e escrita
│   └── fortune-br.txt      # Arquivo com a base de fortunas
└── README.md               # Este arquivo

--- 

## 🚀 Execução

Para executar o projeto, siga os passos abaixo a partir do diretório raiz.

**Compilar o arquivo Java:**
```bash
javac src/Principal_v0.java
```
**Executar o programa**
```bash
java -cp src Principal_v0
```
O programa irá executar as operações de leitura e escrita na sequência, conforme implementado no método iniciar().

---

## 👥 Equipe

João Pedro Neigri Heleno - RA: 2270323
Maria Eduarda Soares Romana Silva - RA: 2408830

---

## 📖 Disciplina

Nome: Sistemas Distribuídos
Professor: Prof. Dr. Lúcio Rocha
Instituição: UTFPR - Câmpus Apucarana
Período: 2025.1

---

## Referências
Este código foi desenvolvido a partir do esqueleto disponibilizado pelo professor no repositório de referência da atividade:

Repositório do Laboratório 1: https://github.com/sdco8a/lab1

# Laboratório 2 - Sistemas Distribuídos (UTFPR Apucarana - 2025.1)  
Este diretório contém a implementação do Laboratório 2 da disciplina de Sistemas Distribuídos, ministrada pelo Prof. Dr. Lúcio Rocha no semestre 2025.1 na UTFPR Apucarana.  

---

## 📌 Objetivo  

O objetivo deste laboratório foi desenvolver um **servidor concorrente**, capaz de atender múltiplos clientes de forma simultânea. Além disso, o servidor manipula o arquivo de fortunas (`fortune-br.txt`), permitindo operações de leitura e escrita.  

As operações implementadas são:  

1. **Servidor**: inicializa e gerencia conexões com múltiplos clientes.  
2. **Cliente**: envia requisições ao servidor.  
3. **Ler uma fortuna** (selecionada aleatoriamente do arquivo).  
4. **Escrever uma nova fortuna** (adicionando-a ao arquivo).  

---

## 📂 Estrutura do projeto  

```
/
├── src/
│   ├── Servidor.java      # Código principal do servidor concorrente
│   ├── Cliente.java       # Código para simulação e envio de requisições ao servidor
│   └── fortune-br.txt     # Arquivo com a base de fortunas
└── README.md              # Este arquivo
```

---

## 🚀 Execução  

Para executar o projeto, siga os passos abaixo a partir do diretório raiz.  

**1. Compilar os arquivos Java:**  
```bash
javac src/Servidor.java src/Cliente.java
```  

**2. Executar o servidor:**  
```bash
java -cp src Servidor
```  

**3. Executar um ou mais clientes em terminais separados:**  
```bash
java -cp src Cliente
```  

O servidor tratará múltiplas conexões em paralelo, cada uma executada em uma **thread independente**.  

---

## 👥 Equipe  

- João Pedro Neigri Heleno - RA: 2270323  
- Maria Eduarda Soares Romana Silva - RA: 2408830  

---

## 📖 Disciplina  

- **Nome**: Sistemas Distribuídos  
- **Professor**: Prof. Dr. Lúcio Rocha  
- **Instituição**: UTFPR - Câmpus Apucarana  
- **Período**: 2025.1  

---

## Referências  

Este código foi desenvolvido a partir do esqueleto disponibilizado pelo professor no repositório de referência da atividade:  

Repositório do Laboratório 2: [https://github.com/sdco8a/lab2](https://github.com/sdco8a/lab2)  

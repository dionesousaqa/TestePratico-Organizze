# 🧪 Teste Prático — Organizze | Automação de Testes Web

Projeto de automação de testes web desenvolvido como parte de um desafio prático envolvendo a aplicação Organizze.

A solução utiliza **Java, Selenium WebDriver, JUnit 4 e Maven** para estruturar e executar testes automatizados de interface, buscando validar o comportamento da aplicação por meio da interação com elementos da interface gráfica.

O projeto tem como objetivo aplicar conhecimentos de Quality Assurance (QA), automação de testes funcionais, gerenciamento de WebDrivers e organização de suítes de testes.

---

## 🎯 Objetivos do projeto

- Desenvolver testes automatizados para aplicações web.
- Aplicar Selenium WebDriver na interação com elementos da interface.
- Estruturar e organizar a execução dos testes utilizando JUnit.
- Automatizar o gerenciamento dos drivers dos navegadores.
- Utilizar Maven para gerenciamento de dependências e execução dos testes.
- Praticar conceitos de automação de testes funcionais.

## 🛠️ Tecnologias e ferramentas

| Tecnologia | Aplicação |
|---|---|
| Java | Linguagem de programação |
| Selenium WebDriver 4.35.0 | Automação da interface web |
| JUnit 4.13.2 | Estruturação e execução dos testes |
| Maven | Gerenciamento de dependências e build |
| WebDriverManager 5.9.2 | Gerenciamento automático dos WebDrivers |
| Maven Surefire 2.22.2 | Execução dos testes automatizados |
| Git e GitHub | Versionamento e hospedagem do projeto |

---

## 🧪 Estratégia de automação

A automação utiliza Selenium WebDriver para executar interações automatizadas com a interface web da aplicação.

A estrutura contempla:

- **Automação de interface:** interação programática com elementos da aplicação.
- **Testes funcionais:** verificação do comportamento das funcionalidades implementadas nos cenários automatizados.
- **Organização de suítes:** centralização da execução por meio da classe `SuiteTeste`.
- **Gerenciamento de navegadores:** utilização do WebDriverManager para facilitar a configuração dos drivers.

## ⚙️ Gerenciamento e execução dos testes

O projeto utiliza Maven Surefire Plugin para integrar a execução dos testes ao ciclo de build.

A configuração do `pom.xml` define a classe `SuiteTeste` como referência para a execução da suíte automatizada.

Essa organização facilita a centralização e manutenção dos cenários de teste.

---

## 💻 Como executar o projeto localmente

### Pré-requisitos

- Java JDK instalado.
- Apache Maven instalado.
- Git instalado.
- Navegador compatível com a configuração dos testes.
- IDE de sua preferência (IntelliJ IDEA ou Eclipse).

### 1. Clonar o repositório

```bash
git clone https://github.com/dionesousaqa/TestePratico-Organizze.git
```

### 2. Acessar o diretório

```bash
cd TestePratico-Organizze
```

### 3. Verificar as instalações

Java:

```bash
java -version
```

Maven:

```bash
mvn -version
```

### 4. Executar os testes

Na raiz do projeto, execute:

```bash
mvn clean test
```

O Maven realizará a resolução das dependências e acionará a configuração de execução dos testes.

---

## 📁 Estrutura e configuração

O projeto utiliza Maven como ferramenta de build e gerenciamento de dependências.

Principais arquivos e diretórios:

| Arquivo / Diretório | Responsabilidade |
|---|---|
| `pom.xml` | Dependências e configurações do Maven |
| `src/` | Código-fonte e estrutura de testes |
| `SuiteTeste` | Classe de referência para execução da suíte |
| `.github/workflows/` | Diretório destinado aos workflows de automação |

---

## 🔗 Links úteis

- **Repositório:** [TestePratico-Organizze](https://github.com/dionesousaqa/TestePratico-Organizze)
- **Selenium:** [Documentação oficial](https://www.selenium.dev/documentation/)
- **JUnit 4:** [Documentação oficial](https://junit.org/junit4/)
- **Apache Maven:** [Documentação oficial](https://maven.apache.org/)
- **WebDriverManager:** [GitHub](https://github.com/bonigarcia/webdrivermanager)

---

## 👨‍💻 Autor

**Dione Sousa**

Estudante e profissional em desenvolvimento na área de Quality Assurance (QA), com foco em automação de testes, qualidade de software e melhoria contínua.

Este projeto faz parte do meu portfólio de estudos e prática em automação de testes de software.

- **GitHub:** [@dionesousaqa](https://github.com/dionesousaqa)
- **LinkedIn:** [Dione Sousa QA](https://www.linkedin.com/in/dione-sousa-qa/)

---

⭐ Se este projeto foi útil para você, considere deixar uma estrela no repositório!

# GymApp - Sistema de Academia em JavaFX

Este é um projeto JavaFX simples que simula um sistema de academia com login por CPF e interface principal com abas e menu.

---

## 🏗 Estrutura do Projeto

- `LoginApp.java`: Tela de login com validação de CPF (apenas 11 dígitos numéricos).
- `GymApp.java`: Tela principal da aplicação com logomarca, data/hora atual e abas de conteúdo.
- `README.md`: Este arquivo.
- `pom.xml` (opcional): Caso esteja utilizando Maven.

---

## 🖥 Requisitos

- Java 11 ou superior
- JavaFX SDK instalado (caso não use Maven)
- IDE recomendada: IntelliJ, Eclipse ou VS Code com suporte a JavaFX

---

## 🛠 Execução (sem Maven)

1. Baixe o [JavaFX SDK](https://openjfx.io/)
2. Compile e execute usando os módulos JavaFX:

```bash
# Compile
javac --module-path /caminho/para/javafx-sdk/lib --add-modules javafx.controls LoginApp.java GymApp.java

# Execute
java --module-path /caminho/para/javafx-sdk/lib --add-modules javafx.controls LoginApp

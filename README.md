## PRÉ-REQUISITOS

* Jre v11 instalada
   - Verifique a versão com o comando ```javac -version ```  

* Maven instalado
   - Verifique a instalação com o comando ``` mvn --version ``` 
* docker instalado ( caso queira executar a aplicação via docker)
    - Verifique a instalação com o comando ``` docker --version ``` 
* docker-compose instalado ( caso queira executar a aplicação via docker-compose)
    - Verifique a instalação com o comando ``` docker-compose --version ``` 
<br>


## INSTRUÇÕES DE USO

### CLI


 1. instale as dependências e gere o arquivo .jar com o seguinte comando

    ```sh
      mvn clean install
    ```   
 2. execute o arquivo jar
    ```sh
      java -jar target/hardemic-1.0-jar-with-dependencies.jar 
    ``` 
 3. Aproveite os recursos da aplicação :)

### DOCKER

 1. Na pasta raíz do projeto faça o build da imagem

    ```bash
      docker build -t hardemic/monitor .
    ``` 

  2. Esse passo varia de acordo com o SO
  
      ### LINUX

       ```bash
         docker run -it --hostname $HOSTNAME hardemic/monitor
       ``` 
  
      ### WINDOWS 

        #### Powershel

      ```powershell
        docker run -it --hostname $env:COMPUTERNAME hardemic/monitor 
      ``` 

        #### CMD

       ```cmd
         docker run -it --hostname %COMPUTERNAME% hardemic/monitor 
       ``` 

  3. Aproveite os recursos da aplicação :)

### DOCKER-COMPOSE

1. Na pasta raíz do projeto execute o seguinte comando
    ### Primeiro uso

    ```sh
      docker-compose build && docker-compose run app
    ```
       
    ### Caso já tenha a imagem buildada uma vez

    ```sh
      docker-compose run app 
    ```
2. Aproveite os recursos da aplicação :)

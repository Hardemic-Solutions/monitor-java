# !bin/bash

echo "ASSISTENTE DE INSTALAÇÃO HARDEMIC"
echo ""

# sudo apt update -y

menu(){
echo "====== Opções de uso: ======"
echo ""
echo "1. CLI"
echo "2. GUI"
echo "3. DOCKER"
echo "4. DOCKER-COMPOSE"
echo ""
echo "============================"

read -p "Escolha uma opção: " opcao

installjava(){
  which java | grep /usr/bin/java 

  if [ $? -ne 0 ]
  then
    sudo apt install zip -y

    sdk v

    if [ $? -ne 0 ]
    then
      curl -s "https://get.sdkman.io" | bash

      source "/home/$USER/.sdkman/bin/sdkman-init.sh"

      sdk install java  11.0.14.10.1-amzn

    fi
  fi

  mvn -v

  if [ $? -ne 0 ]
  then
    sudo apt install maven -y
  fi
}

installdocker(){

  docker -v

  if [ $? -ne 0 ]
  then
     sudo apt-get install \
    ca-certificates \
    curl \
    gnupg \
    lsb-release

    curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo gpg --dearmor -o /usr/share/keyrings/docker-archive-keyring.gpg -y

    echo \
    "deb [arch=$(dpkg --print-architecture) signed-by=/usr/share/keyrings/docker-archive-keyring.gpg] https://download.docker.com/linux/ubuntu \
    $(lsb_release -cs) stable" | sudo tee /etc/apt/sources.list.d/docker.list > /dev/null

    sudo apt-get update

    sudo apt-get install docker-ce docker-ce-cli containerd.io docker-compose-plugin

    sudo docker run hello-world

    sleep 1

    sudo usermod -aG docker $USER

    clear
  fi

  sudo systectl start docker

  sudo service docker start
  
}

case $opcao in
1) echo "=== CLI ==="
   
  installjava

  cd hardemic-pi-test

  mvn clean install

  java -jar target/hardemic-1.0-jar-with-dependencies.jar cli

  clear

  java -jar hardemic-1.0-jar-with-dependencies.jar cli
  ;;
2) echo "=== GUI ===" 
   installjava

   cd hardemic-pi-test

   mvn clean install

   java -jar target/hardemic-1.0-jar-with-dependencies.jar

  clear

  java -jar hardemic-1.0-jar-with-dependencies.jar
  ;;
3) echo "=== DOCKER ===" 
   installdocker

   cd hardemic-pi-test

   sudo docker build -t hardemic/monitor .
   sudo docker run -it --hostname $HOSTNAME hardemic/monitor
   
  ;;
4) echo "=== DOCKER-COMPOSE ===" 
   installdocker

   docker-compose -v 

   if [ $? -ne 0 ]
   then
    sudo apt-get install docker-compose -y
   fi

   cd hardemic-pi-test

   sudo docker-compose build && sudo docker-compose run app
  ;;
*) echo "Opção $opcao Inválida!" 
   sleep 1
   menu
  ;;
esac
}

menu

FROM ubuntu:latest

RUN apt-get update \
    && apt-get install -y openjdk-21-jdk

WORKDIR /app

COPY . .

CMD [ "mvn","test" ]

ENV JAVA_HOME /usr/lib/jvm/java-21-openjdk-amd64
ENV PATH $JAVA_HOME/bin:$PATH

RUN  apt-get install -y wget  unzip
RUN  wget https://mirrors.estointernet.in/apache/maven/maven-3/3.6.3/binaries/apache-maven-3.6.3-bin.tar.gz
RUN  tar -xvf apache-maven-3.6.3-bin.tar.gz





ENV MAVEN_HOME /app/apache-maven-3.6.3
ENV PATH $MAVEN_HOME/bin:$PATH

RUN apt-get update && apt-get install -y sudo gnupg
RUN wget -q -O - https://dl.google.com/linux/linux_signing_key.pub | sudo apt-key add -
RUN sh -c 'echo "deb http://dl.google.com/linux/chrome/deb/ stable main" >> /etc/apt/sources.list.d/google.list'
RUN apt-get update
RUN apt-get install -y google-chrome-stable

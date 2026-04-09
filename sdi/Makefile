# GNU Makefile
JAR=/usr/local/jdk1.8.0_131/bin/jar
JAVA=/usr/local/jdk1.8.0_131/bin/java
JAVAC=/usr/local/jdk1.8.0_131/bin/javac

JFLAGS = -g 
.SUFFIXES: .java .class
.java.class:
	$(JAVAC) $(JFLAGS) $*.java

CLASSES = \
	PortoServer.java\
	PortoClient.java\
	IServico.java\
	Carga.java\
	Navio.java\
	Embarque.java

default: classes

classes: $(CLASSES:.java=.class)

clean:
	rm -f *.class 
